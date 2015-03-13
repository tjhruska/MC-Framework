package com.tjhruska.mc.util.tagReplacement

import org.apache.tomcat.jdbc.pool.DataSource

import groovy.util.logging.Slf4j

import java.sql.Connection
import java.sql.SQLException
import java.sql.Statement
import java.util.List
import java.util.Map

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowCallbackHandler
import org.springframework.jdbc.core.RowMapper
import org.springframework.transaction.annotation.Transactional

@Slf4j
public class SQLTemplateImpl extends TemplateImpl implements SQLTemplate {

  DataSource dataSource
  JdbcTemplate jdbcTemplate

  public SQLTemplateImpl() {
    super()
  }

  public SQLTemplateImpl(String template) {
    super(template)
  }

  public SQLTemplateImpl(String template, Boolean keepUnusedTags, Integer maxIterations){
    super(template, keepUnusedTags, maxIterations)
  }

  public SQLTemplateImpl(DataSource dataSource, JdbcTemplate jdbcTemplate){
    super()
    this.dataSource = dataSource
    this.jdbcTemplate = jdbcTemplate
  }

  /* (non-Javadoc)
   * @see com.tjhruska.mc.util.tagReplacement.SQLTemplate#query(java.util.Map, org.springframework.jdbc.core.RowMapper)
   */
  @Override
  @Transactional
  public <T> List<T> query(Map<String, String> parameters, RowMapper<T> rm) {
    return jdbcTemplate.query(applyTags(parameters).toString(), rm)
  }

  /* (non-Javadoc)
   * @see com.tjhruska.mc.util.tagReplacement.SQLTemplate#query(java.util.Map, org.springframework.jdbc.core.RowCallbackHandler)
   */
  @Override
  @Transactional
  public void query(Map<String, String> parameters, RowCallbackHandler rch){
    jdbcTemplate.query(applyTags(parameters).toString(), rch)
  }

  /* (non-Javadoc)
   * @see com.tjhruska.mc.util.tagReplacement.SQLTemplate#update(java.util.Map)
   */
  @Override
  @Transactional
  public int update(Map<String, String> parameters) {
    return jdbcTemplate.update(applyTags(parameters).toString())
  }

  /* (non-Javadoc)
   * @see com.tjhruska.mc.util.tagReplacement.SQLTemplate#jdbcQuery(java.util.Map)
   */
  @Override
  public void jdbcQuery(Map<String, String> parameters) throws SQLException{
    String sql = applyTags(parameters).toString()
    Connection connection = dataSource.getConnection()
    Statement statement = null
    try {
      statement = connection.createStatement()
      statement.execute(sql)
    } catch (SQLException e) {
      log.error("query " + getBeanName()
          + " failed with the following exception:", e)
      //log.error("query {} sql:\n{}", getBeanName(), sql);
      throw e
    } finally {
      statement.close()
      connection.close()
    }
  }
}
