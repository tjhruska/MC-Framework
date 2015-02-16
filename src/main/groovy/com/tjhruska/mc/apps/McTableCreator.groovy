package com.tjhruska.mc.apps

import java.sql.ResultSet
import java.sql.SQLException

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.RowCallbackHandler
import org.springframework.jdbc.core.RowMapper

import com.tjhruska.mc.util.BeanNameAwareRunnable
import com.tjhruska.mc.util.FileUtils
import com.tjhruska.mc.util.tagReplacement.SQLTemplate


class McTableCreator extends BeanNameAwareRunnable {
  private static final Logger log = LoggerFactory.getLogger(McTableCreator)

  SQLTemplate buildTableCreatorCommands
  SQLTemplate getExistingTablesAndViews
  Map<String, String> installProperties

  void run() {
    installProperties.each { key, value ->
      log.info("installProperty: ${key}, value: ${value}")
    }

    log.info('Found {} query.', buildTableCreatorCommands.getBeanName())

    //prep the backupDirectory name to include our application name, and the datetime
    StringBuilder backupDirectory = buildBackupDirectoryName(installProperties.get('backupDirectory'))

    final Map<String,String> existingTablesAndViews = new HashMap<String,String>()
    try{
      getExistingTablesAndViews.query(installProperties,
          new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
              existingTablesAndViews.put(rs.getString('key'), rs.getString('table_type'))
            }
          })
    } catch (Exception e){
      log.error('Failed to retrieve list of exisitng tables and views using {}', getExistingTablesAndViews.getBeanName())
      log.error('exception:', e)
      throw e
    }

    //run the query to get back a list of CommandFragments
    List<CommandFragment> commandFragments = null
    try{
      commandFragments = buildTableCreatorCommands.query(installProperties,
          new RowMapper<CommandFragment> (){
            public CommandFragment mapRow(ResultSet rs, int rowNum) throws SQLException {
              CommandFragment commandFragment = new CommandFragment()
              commandFragment.mcTableId = rs.getLong('mc_table_id')
              commandFragment.schemaName = rs.getString('schema_name')
              commandFragment.tableName = rs.getString('table_name')
              commandFragment.suppressTableChangesFlag = rs.getBoolean('suppress_table_changes_flag')
              commandFragment.section = rs.getInt('section')
              commandFragment.sequence = rs.getInt('sequence')
              commandFragment.postCreateSql = rs.getString('post_create_sql')
              commandFragment.sql = rs.getString('sql')
              return commandFragment
            }})
    } catch (Exception e){
      log.error('Failed to retrieve command fragements using {}', buildTableCreatorCommands.getBeanName())
      log.error('exception:', e)
      throw e
    }

    if (commandFragments == null || commandFragments.size() == 0){
      log.info('No changes in mc tables to propagate during mcTableCreator run.')
    }else{
      log.info('Retrieved {} command fragments, converting them into commands.', commandFragments.size())

      List<Command> commands = CommandBuilder.buildCommands(buildTableCreatorCommands, commandFragments, existingTablesAndViews)

      log.info('Built {} commands from {} command fragments', commands.size(), commandFragments.size())

      //assemble commands together for execution
      StringBuilder postCreateSql = new StringBuilder()
      StringBuilder postCreateSqlSuppressed = new StringBuilder()
      StringBuilder combinedCommand = new StringBuilder()

      for (Command command : commands){
        if (command.suppressFlag && command.postCreateSql.length() > 0){
          postCreateSqlSuppressed.append(command.postCreateSql)
        }else if (!command.suppressFlag){
          combinedCommand.append(command.sql)
          if (command.postCreateSql.length() > 0)
            postCreateSql.append(command.postCreateSql)
        }
      }

      combinedCommand.append(postCreateSql)

      //write out commands to backup directory
      CommandBuilder.writeOutPendingCommands(backupDirectory, commands,
          installProperties, postCreateSql, postCreateSqlSuppressed, combinedCommand)

      if (combinedCommand.length() == 0){
        log.info('All commands suppressed.  Nothing to run at this time.')
      }else{

        SQLTemplate combinedCommandSqlTemplate = new SQLTemplate(combinedCommand.toString())
        combinedCommandSqlTemplate.setBeanName('combinedCommandSqlTemplate')
        combinedCommandSqlTemplate.setDataSource(getExistingTablesAndViews.getDataSource())
        combinedCommandSqlTemplate.setJdbcTemplate(getExistingTablesAndViews.getJdbcTemplate())

        try {
          combinedCommandSqlTemplate.jdbcQuery(installProperties)
          log.info('Finished running commands into database.')
        } catch (SQLException e) {
          log.error('Failed to run command {} into database.', combinedCommandSqlTemplate.getBeanName())
          log.error('With error {}', e)
          log.error('Using query: {}', combinedCommandSqlTemplate.applyTags(installProperties))
          throw e
        }
      }
    }
  }

  class CommandFragment{
    //mc_table_id, schema_name, table_name, suppress_table_changes_flag, section, sequence, post_create_sql, sql
    public long mcTableId
    public String schemaName
    public String tableName
    public boolean suppressTableChangesFlag
    public int section
    public int sequence
    public String postCreateSql
    public String sql
  }

  static class Command{
    private static final Logger log = LoggerFactory.getLogger(Command)

    public Command(SQLTemplate buildTableCreatorCommands, CommandFragment commandFragment, Map<String,String> existingTablesAndViews){
      sqlTemplate = new SQLTemplate(buildTableCreatorCommands.getDataSource(),
          buildTableCreatorCommands.getJdbcTemplate())
      sqlTemplate.setBeanName('createTable(mcTableId:' + commandFragment.mcTableId + ')')
      sql = new StringBuilder()
      postCreateSql = new StringBuilder()
      this.mcTableId = commandFragment.mcTableId
      this.schemaName = commandFragment.schemaName
      this.tableName = commandFragment.tableName
      this.suppressTableChangesFlag = commandFragment.suppressTableChangesFlag
      if (this.suppressTableChangesFlag && existingTablesAndViews.containsKey(this.schemaName + '.' + this.tableName))
        this.suppressFlag = true
      else
        this.suppressFlag = false
    }
    public long mcTableId
    public String schemaName
    public String tableName
    public boolean suppressTableChangesFlag
    public boolean suppressFlag
    public StringBuilder sql
    public StringBuilder postCreateSql
    public SQLTemplate sqlTemplate
    public StringBuilder fileName
  }

  static class CommandBuilder{
    private static final Logger log = LoggerFactory.getLogger(CommandBuilder)

    public static List<Command> buildCommands(SQLTemplate buildTableCreatorCommands,
        List<CommandFragment> commandFragments, Map<String, String> existingTablesAndViews)
    {
      List<Command> commands = new ArrayList<Command>()
      //ListIterator<CommandFragment> i = commandFragments.listIterator()
      //if (!i.hasNext())
      //	return commands

      Command currentCommand = null
      Long prevItem = 0L

      commandFragments.eachWithIndex { commandFragment, i ->
        boolean hasNext = (i + 1 < commandFragments.size)

        //log.debug("${commandFragment.mcTableId}-${commandFragment.mcTableId}-${commandFragment.sequence}")

        //store a link to the old command in-case we are changing
        Command prevCommand = currentCommand

        //current item not equal to prev item, ready a new currentCommand
        if (hasNext && (!prevItem.equals(commandFragment.mcTableId))){
          log.debug('changed so creating new stringBuilder')
          currentCommand = new Command(buildTableCreatorCommands, commandFragment, existingTablesAndViews)
        }

        //copy current fragment out - if on last record it is also copied into previous... :)
        currentCommand.sql.append(commandFragment.sql)
        //same thing for the post command
        if (commandFragment.postCreateSql != null && !commandFragment.postCreateSql.equals(''))
          currentCommand.postCreateSql.append(commandFragment.postCreateSql)

        //if we are done with command fragments, or onto a new one write out previous one
        if (prevCommand != null &&
        (!hasNext || !prevItem.equals(commandFragment.mcTableId)))
        {
          log.debug('More rows=${hasNext}, if false or record changed, then save off ${prevItem.toString()}')
          //finished with sqlTemplate, add it to list
          prevCommand.sqlTemplate.setTemplate(prevCommand.sql.toString())
          if (!existingTablesAndViews.containsKey(commandFragment.schemaName + '.' + commandFragment.tableName))
            commands.add(prevCommand)
          else
            log.info(commandFragment.schemaName + '.' + commandFragment.tableName + ' exists, skipping create.')
        }

        //set id for comparison in next iteration
        prevItem = commandFragment.mcTableId
      }
      return commands
    }

    static void writeOutPendingCommands(StringBuilder directoryName,
        List<Command> commands, Map<String,String> parms, StringBuilder postCreateSql,
        StringBuilder postCreateSqlSuppressed, StringBuilder combinedCommand)
    {

      StringBuilder suppressedDirectoryName = new StringBuilder(directoryName).append('suppressed/')
      StringBuilder fileName = null
      for (Command command : commands){
        fileName = new StringBuilder(command.schemaName).append('.')
            .append(command.tableName).append('(').append(command.mcTableId)
            .append(')').append('.sql')
        try {
          FileUtils.writeFile((command.suppressFlag ? suppressedDirectoryName : directoryName),
              fileName, command.sqlTemplate.applyTags(parms))
          log.debug('Wrote out file: {}{}', directoryName, fileName)
          command.fileName = fileName
        } catch (IOException e) {
          log.error('failed to write out file: {}{}', directoryName, fileName)
          log.error('with exception:', e)
          throw e
        }
      }
      //write out the postSQL file
      fileName = new StringBuilder('_post.create.sql')
      try {
        FileUtils.writeFile(directoryName, fileName, postCreateSql)
        log.debug('Wrote out file: {}{}', directoryName, fileName)
      } catch (IOException e) {
        log.error('failed to write out file: {}{}', directoryName, fileName)
        log.error('with exception:', e)
        throw e
      }

      //write out suppressed postSQL file
      try {
        FileUtils.writeFile(suppressedDirectoryName, fileName, postCreateSqlSuppressed)
        log.debug('Wrote out file: {}{}', directoryName, fileName)
      } catch (IOException e) {
        log.error('failed to write out file: {}{}', directoryName, fileName)
        log.error('with exception:', e)
        throw e
      }

      //write out the combined file
      fileName = new StringBuilder('_combined.sql')
      try {
        FileUtils.writeFile(directoryName, fileName, combinedCommand)
        log.debug('Wrote out file: {}{}', directoryName, fileName)
      } catch (IOException e) {
        log.error('failed to write out file: {}{}', directoryName, fileName)
        log.error('with exception:', e)
        throw e
      }
    }
  }
}
