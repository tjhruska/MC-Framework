
package com.tjhruska.mc.domain.system

import org.springframework.beans.factory.annotation.Autowired
import static org.junit.Assert.*
import org.junit.Before
import org.junit.Ignore

import com.tjhruska.mc.database.BaseDomain
import com.tjhruska.mc.database.DaoDomain
import com.tjhruska.mc.database.test.GeneratedDomainAndDaoTest
import com.tjhruska.mc.domain.system.Table
import com.tjhruska.mc.domain.system.TableTest
import com.tjhruska.mc.enums.Container
import com.tjhruska.mc.enums.Datatype
import com.tjhruska.mc.enums.DbIndex
import java.util.ArrayList
import java.util.List
import com.tjhruska.mc.domain.system.Column
import com.tjhruska.mc.domain.system.ColumnTest
  

//    Class contains database constraints which can't be accomadated in autogenerated code.
//    To activate test: extend test class, and tweak fields with constraints to match database expectations.
//    (Extended class won't be wiped out on regeneration, and must continue to match database expectations.)
@Ignore 
class ColumnTest extends GeneratedDomainAndDaoTest {
  
  @Autowired
  DaoDomain<Column> columnDao

  @Override
  public DaoDomain getDao() {
    columnDao
  }

  Table table
  Column guiFieldGroupGuardColumn

  @Override
  public BaseDomain getTestObject(Integer number, Integer sequence) {
    Column column = new Column()
    
    if (table == null) {
      column.setTable(new TableTest(tableDao : getDao()).persistTestObject(number + 30))
    } else {
      column.setTable(table)
    }
    column.setSequence((Short)sequence)
    column.setName("name${number}")
    column.setDescription("description${number}")
    column.setNameCamelCase("name_camel_case${number}")
    column.setDatatype(Datatype.getDatatypeBySequence(1))
    column.setDatatypeRef1("datatype_ref1${number}")
    column.setDatatypeRef2("datatype_ref2${number}")
    column.setDatatypeRef3("datatype_ref3${number}")
    column.setDbIndex(DbIndex.getDbIndexBySequence(1))
    column.setPrimaryKeyFlag(13%number == 0)
    column.setForeignKeyFlag(14%number == 0)
    column.setForeignTableId(number)
    column.setForeignPogoCollectionDatatype(Container.getContainerBySequence(1))
    column.setForeignPojoCollectionNameCamelCase("fk_pojo_collection_name_camel_case${number}")
    column.setForeignPojoCollectionLocalKeyColumnId(number)
    column.setForeignOrmLazyFlag(19%number == 0)
    column.setForeignKeyLinkThruToTableId(number)
    column.setForeignKeyOrmInverseFlag(21%number == 0)
    column.setFkGuiColumnFilterForeign("fk_gui_column_filter_foreign${number}")
    column.setFkGuiColumnFilterLocal("fk_gui_column_filter_local${number}")
    column.setNotNullFlag(24%number == 0)
    column.setDefaultValue("default_value${number}")
    column.setConstraintSQL("constraint_sql${number}")
    column.setPojoConstructorFieldFlag(27%number == 0)
    column.setGuiHeader("gui_header${number}")
    column.setGuiToolTip("gui_tool_tip${number}")
    column.setGuiSortableFlag(30%number == 0)
    column.setGuiFieldGroupName("gui_field_group_name${number}")
    column.setGuiFieldGroupSequence((Short)(32 * number))
    if (guiFieldGroupGuardColumn == null) {
      column.setGuiFieldGroupGuardColumn(null)
    } else {
      column.setGuiFieldGroupGuardColumn(guiFieldGroupGuardColumn)
    }
    column.setGuiFieldGroupFieldRequiredFlag(34%number == 0)
    
    List<Column> guardedColumns = new ArrayList()
    if (columnTest != null) {
      columnTest.guiFieldGroupGuardColumn = column
      (1..(10-number)).each { i ->
      guardedColumns.add(columnTest.getTestObject(i+(10*number), i-1))
      }
    }
    column.setGuardedColumns(guardedColumns)

    column
  }

  @Override
  public BaseDomain updateDomainObject(Integer number, BaseDomain domain) {
    Column source = getTestObject(number, 0)
    Column target = (Column)domain
    target.setTable(source.getTable())
    target.setSequence(source.getSequence())
    target.setName(source.getName())
    target.setDescription(source.getDescription())
    target.setNameCamelCase(source.getNameCamelCase())
    target.setDatatype(source.getDatatype())
    target.setDatatypeRef1(source.getDatatypeRef1())
    target.setDatatypeRef2(source.getDatatypeRef2())
    target.setDatatypeRef3(source.getDatatypeRef3())
    target.setDbIndex(source.getDbIndex())
    target.setPrimaryKeyFlag(source.getPrimaryKeyFlag())
    target.setForeignKeyFlag(source.getForeignKeyFlag())
    target.setForeignTableId(source.getForeignTableId())
    target.setForeignPogoCollectionDatatype(source.getForeignPogoCollectionDatatype())
    target.setForeignPojoCollectionNameCamelCase(source.getForeignPojoCollectionNameCamelCase())
    target.setForeignPojoCollectionLocalKeyColumnId(source.getForeignPojoCollectionLocalKeyColumnId())
    target.setForeignOrmLazyFlag(source.getForeignOrmLazyFlag())
    target.setForeignKeyLinkThruToTableId(source.getForeignKeyLinkThruToTableId())
    target.setForeignKeyOrmInverseFlag(source.getForeignKeyOrmInverseFlag())
    target.setFkGuiColumnFilterForeign(source.getFkGuiColumnFilterForeign())
    target.setFkGuiColumnFilterLocal(source.getFkGuiColumnFilterLocal())
    target.setNotNullFlag(source.getNotNullFlag())
    target.setDefaultValue(source.getDefaultValue())
    target.setConstraintSQL(source.getConstraintSQL())
    target.setPojoConstructorFieldFlag(source.getPojoConstructorFieldFlag())
    target.setGuiHeader(source.getGuiHeader())
    target.setGuiToolTip(source.getGuiToolTip())
    target.setGuiSortableFlag(source.getGuiSortableFlag())
    target.setGuiFieldGroupName(source.getGuiFieldGroupName())
    target.setGuiFieldGroupSequence(source.getGuiFieldGroupSequence())
    target.setGuiFieldGroupGuardColumn(source.getGuiFieldGroupGuardColumn())
    target.setGuiFieldGroupFieldRequiredFlag(source.getGuiFieldGroupFieldRequiredFlag())

    target.guardedColumns.each {
      columnTest.getDao().delete(it)
    }
    target.guardedColumns.clear()
    source.guardedColumns.each {
      it.guiFieldGroupGuardColumn = target
    }
    target.guardedColumns.addAll(source.guardedColumns)

    return source
  }

  @Override
  public void assertDomainUpdates(BaseDomain expected, BaseDomain actual) {
    Column expectedD = (Column)expected
    Column actualD = (Column)actual
    new TableTest().assertDomainUpdates(expectedD.getTable(), actualD.getTable())
    assertEquals("sequence is different than expected", expectedD.getSequence(), actualD.getSequence())
    assertEquals("name is different than expected", expectedD.getName(), actualD.getName())
    assertEquals("description is different than expected", expectedD.getDescription(), actualD.getDescription())
    assertEquals("nameCamelCase is different than expected", expectedD.getNameCamelCase(), actualD.getNameCamelCase())
    assertEquals("datatype is different than expected", expectedD.getDatatype(), actualD.getDatatype())
    assertEquals("datatypeRef1 is different than expected", expectedD.getDatatypeRef1(), actualD.getDatatypeRef1())
    assertEquals("datatypeRef2 is different than expected", expectedD.getDatatypeRef2(), actualD.getDatatypeRef2())
    assertEquals("datatypeRef3 is different than expected", expectedD.getDatatypeRef3(), actualD.getDatatypeRef3())
    assertEquals("dbIndex is different than expected", expectedD.getDbIndex(), actualD.getDbIndex())
    assertEquals("primaryKeyFlag is different than expected", expectedD.getPrimaryKeyFlag(), actualD.getPrimaryKeyFlag())
    assertEquals("foreignKeyFlag is different than expected", expectedD.getForeignKeyFlag(), actualD.getForeignKeyFlag())
    assertEquals("foreignTable is different than expected", expectedD.getForeignTableId(), actualD.getForeignTableId())
    assertEquals("foreignPogoCollectionDatatype is different than expected", expectedD.getForeignPogoCollectionDatatype(), actualD.getForeignPogoCollectionDatatype())
    assertEquals("foreignPojoCollectionNameCamelCase is different than expected", expectedD.getForeignPojoCollectionNameCamelCase(), actualD.getForeignPojoCollectionNameCamelCase())
    assertEquals("foreignPojoCollectionLocalKeyColumn is different than expected", expectedD.getForeignPojoCollectionLocalKeyColumnId(), actualD.getForeignPojoCollectionLocalKeyColumnId())
    assertEquals("foreignOrmLazyFlag is different than expected", expectedD.getForeignOrmLazyFlag(), actualD.getForeignOrmLazyFlag())
    assertEquals("foreignKeyLinkThruToTable is different than expected", expectedD.getForeignKeyLinkThruToTableId(), actualD.getForeignKeyLinkThruToTableId())
    assertEquals("foreignKeyOrmInverseFlag is different than expected", expectedD.getForeignKeyOrmInverseFlag(), actualD.getForeignKeyOrmInverseFlag())
    assertEquals("fkGuiColumnFilterForeign is different than expected", expectedD.getFkGuiColumnFilterForeign(), actualD.getFkGuiColumnFilterForeign())
    assertEquals("fkGuiColumnFilterLocal is different than expected", expectedD.getFkGuiColumnFilterLocal(), actualD.getFkGuiColumnFilterLocal())
    assertEquals("notNullFlag is different than expected", expectedD.getNotNullFlag(), actualD.getNotNullFlag())
    assertEquals("defaultValue is different than expected", expectedD.getDefaultValue(), actualD.getDefaultValue())
    assertEquals("constraintSQL is different than expected", expectedD.getConstraintSQL(), actualD.getConstraintSQL())
    assertEquals("pojoConstructorFieldFlag is different than expected", expectedD.getPojoConstructorFieldFlag(), actualD.getPojoConstructorFieldFlag())
    assertEquals("guiHeader is different than expected", expectedD.getGuiHeader(), actualD.getGuiHeader())
    assertEquals("guiToolTip is different than expected", expectedD.getGuiToolTip(), actualD.getGuiToolTip())
    assertEquals("guiSortableFlag is different than expected", expectedD.getGuiSortableFlag(), actualD.getGuiSortableFlag())
    assertEquals("guiFieldGroupName is different than expected", expectedD.getGuiFieldGroupName(), actualD.getGuiFieldGroupName())
    assertEquals("guiFieldGroupSequence is different than expected", expectedD.getGuiFieldGroupSequence(), actualD.getGuiFieldGroupSequence())
    new ColumnTest().assertDomainUpdates(expectedD.getGuiFieldGroupGuardColumn(), actualD.getGuiFieldGroupGuardColumn())
    assertEquals("guiFieldGroupFieldRequiredFlag is different than expected", expectedD.getGuiFieldGroupFieldRequiredFlag(), actualD.getGuiFieldGroupFieldRequiredFlag())
    assertEquals("size of guardedColumns is different than expected", expectedD.guardedColumns.size(), actualD.guardedColumns.size())
  }

  @Override
  public void deleteChildrenIfNeeded(BaseDomain domain) {
    Column target = (Column)domain
    target.guardedColumns.each {
      columnTest.getDao().delete(it)
    }
  }
}