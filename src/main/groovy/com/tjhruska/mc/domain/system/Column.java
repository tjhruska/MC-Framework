
/**
 *   Copyright  2011 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.mc.domain.system
 *   Column.java
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the LGNU Lesser General Public License as 
 *   published by the Free Software Foundation, either version 3 of the 
 *   License, or (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   LGNU Lesser General Public License for more details.
 *
 *   You should have received a copy of the LGNU Lesser General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.tjhruska.mc.domain.system;


import com.tjhruska.mc.database.BaseDomain;
import com.tjhruska.mc.database.DaoDomain;
import javax.annotation.Resource;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tjhruska.mc.enums.Container;
import com.tjhruska.mc.enums.Datatype;
import com.tjhruska.mc.enums.DbIndex;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class Column extends BaseDomain {

    public Column (){
    }


    @Resource (name="columnDao")
    private DaoDomain<Column> columnDao;


    @Resource (name="tableDao")
    private DaoDomain<Table> tableDao;

    private Integer tableId;
    private Short sequence;
    private String name;
    private String description;
    private String nameCamelCase;
    private Datatype datatype;
    private DbIndex dbIndex;
    private Boolean primaryKeyFlag;
    private Boolean foreignKeyFlag;
    private Integer foreignTableId;
    private Container foreignPogoCollectionDatatype;
    private String foreignPojoCollectionNameCamelCase;
    private Integer foreignPojoCollectionLocalKeyColumnId;
    private Boolean foreignOrmLazyFlag;
    private Integer foreignKeyLinkThruToTableId;
    private Boolean foreignKeyOrmInverseFlag;
    private String fkGuiColumnFilterForeign;
    private String fkGuiColumnFilterLocal;
    private Boolean notNullFlag;
    private String defaultValue;
    private String constraintSQL;
    private Boolean pojoConstructorFieldFlag;
    private String guiHeader;
    private String guiToolTip;
    private Boolean guiSortableFlag;
    private String guiFieldGroupName;
    private Short guiFieldGroupSequence;
    private Integer guiFieldGroupGuardColumnId;
    private Boolean guiFieldGroupFieldRequiredFlag;
    private List<Column> guardedColumns;
    
    /**
     * @return Table, Table to which this Column entry belongs.
     */
    @JsonManagedReference("columns")
    @JsonIgnore
    public Table getTable() {
        return tableDao.findByPK(tableId);
    }

    /**
     * @param table
     */
    @JsonManagedReference("columns")
    @JsonIgnore
    public void setTable(Table table) {
        this.tableId = table.getId();
    }

    /**
     * @return Integer, Id for Table to which this Column entry belongs.
     */
    public Integer getTableId() {
        return tableId;
    }

    /**
     * @param tableId
     */
    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    /**
     * @return Short, Order of fields on Table.
     */
    public Short getSequence() {
        return sequence;
    }

    /**
     * @param sequence
     */
    public void setSequence(Short sequence) {
        this.sequence = sequence;
    }

    /**
     * @return String, Column Name on the Table.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String, Description of the Column for documentation purposes.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return String, Name of how the column would appear in a POJO using fieldCamelCaseSyntax.
     */
    public String getNameCamelCase() {
        return nameCamelCase;
    }

    /**
     * @param nameCamelCase
     */
    public void setNameCamelCase(String nameCamelCase) {
        this.nameCamelCase = nameCamelCase;
    }

    /**
     * @return Datatype, Data type of the column, withing the database, java, orm, and js.
     */
    public Datatype getDatatype() {
        return datatype;
    }

    /**
     * @param datatype
     */
    public void setDatatype(Datatype datatype) {
        this.datatype = datatype;
    }

    /**
     * @return DbIndex, Database index to create on this column.
     */
    public DbIndex getDbIndex() {
        return dbIndex;
    }

    /**
     * @param dbIndex
     */
    public void setDbIndex(DbIndex dbIndex) {
        this.dbIndex = dbIndex;
    }

    /**
     * @return Boolean, This column contributes to the unique PK index for this table.
     */
    public Boolean isPrimaryKeyFlag() {
        return primaryKeyFlag;
    }

    /**
     * @param primaryKeyFlag
     */
    public void setPrimaryKeyFlag(Boolean primaryKeyFlag) {
        this.primaryKeyFlag = primaryKeyFlag;
    }

    /**
     * @return Boolean, This column is a foreign key to another table.
     */
    public Boolean isForeignKeyFlag() {
        return foreignKeyFlag;
    }

    /**
     * @param foreignKeyFlag
     */
    public void setForeignKeyFlag(Boolean foreignKeyFlag) {
        this.foreignKeyFlag = foreignKeyFlag;
    }

    /**
     * @return Integer, Id for Table to which we are linking.
     */
    public Integer getForeignTableId() {
        return foreignTableId;
    }

    /**
     * @param foreignTableId
     */
    public void setForeignTableId(Integer foreignTableId) {
        this.foreignTableId = foreignTableId;
    }

    /**
     * @return Container, Type of collection on foreign table
     */
    public Container getForeignPogoCollectionDatatype() {
        return foreignPogoCollectionDatatype;
    }

    /**
     * @param foreignPogoCollectionDatatype
     */
    public void setForeignPogoCollectionDatatype(Container foreignPogoCollectionDatatype) {
        this.foreignPogoCollectionDatatype = foreignPogoCollectionDatatype;
    }

    /**
     * @return String, Name of collection on foreign table
     */
    public String getForeignPojoCollectionNameCamelCase() {
        return foreignPojoCollectionNameCamelCase;
    }

    /**
     * @param foreignPojoCollectionNameCamelCase
     */
    public void setForeignPojoCollectionNameCamelCase(String foreignPojoCollectionNameCamelCase) {
        this.foreignPojoCollectionNameCamelCase = foreignPojoCollectionNameCamelCase;
    }

    /**
     * @return Integer, Id for If map/list the column on current table is the key/index
     */
    public Integer getForeignPojoCollectionLocalKeyColumnId() {
        return foreignPojoCollectionLocalKeyColumnId;
    }

    /**
     * @param foreignPojoCollectionLocalKeyColumnId
     */
    public void setForeignPojoCollectionLocalKeyColumnId(Integer foreignPojoCollectionLocalKeyColumnId) {
        this.foreignPojoCollectionLocalKeyColumnId = foreignPojoCollectionLocalKeyColumnId;
    }

    /**
     * @return Boolean, Should collection on foreign table be lazy loaded
     */
    public Boolean isForeignOrmLazyFlag() {
        return foreignOrmLazyFlag;
    }

    /**
     * @param foreignOrmLazyFlag
     */
    public void setForeignOrmLazyFlag(Boolean foreignOrmLazyFlag) {
        this.foreignOrmLazyFlag = foreignOrmLazyFlag;
    }

    /**
     * @return Integer, Id for Is current table linking table only? if so point to referenced table
     */
    public Integer getForeignKeyLinkThruToTableId() {
        return foreignKeyLinkThruToTableId;
    }

    /**
     * @param foreignKeyLinkThruToTableId
     */
    public void setForeignKeyLinkThruToTableId(Integer foreignKeyLinkThruToTableId) {
        this.foreignKeyLinkThruToTableId = foreignKeyLinkThruToTableId;
    }

    /**
     * @return Boolean, True on side of link that is responsible for persistance; true = local, false = foreign
     */
    public Boolean isForeignKeyOrmInverseFlag() {
        return foreignKeyOrmInverseFlag;
    }

    /**
     * @param foreignKeyOrmInverseFlag
     */
    public void setForeignKeyOrmInverseFlag(Boolean foreignKeyOrmInverseFlag) {
        this.foreignKeyOrmInverseFlag = foreignKeyOrmInverseFlag;
    }

    /**
     * @return String, Foreign column to use for URL filter to limit drop down list results in GUI.
     */
    public String getFkGuiColumnFilterForeign() {
        return fkGuiColumnFilterForeign;
    }

    /**
     * @param fkGuiColumnFilterForeign
     */
    public void setFkGuiColumnFilterForeign(String fkGuiColumnFilterForeign) {
        this.fkGuiColumnFilterForeign = fkGuiColumnFilterForeign;
    }

    /**
     * @return String, Local Id or filter test to use for URL filter to limit drop down list results in GUI.
     */
    public String getFkGuiColumnFilterLocal() {
        return fkGuiColumnFilterLocal;
    }

    /**
     * @param fkGuiColumnFilterLocal
     */
    public void setFkGuiColumnFilterLocal(String fkGuiColumnFilterLocal) {
        this.fkGuiColumnFilterLocal = fkGuiColumnFilterLocal;
    }

    /**
     * @return Boolean, Whether nulls will be allowed for this Table.Column field.
     */
    public Boolean isNotNullFlag() {
        return notNullFlag;
    }

    /**
     * @param notNullFlag
     */
    public void setNotNullFlag(Boolean notNullFlag) {
        this.notNullFlag = notNullFlag;
    }

    /**
     * @return String, Default value for this column
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * @param defaultValue
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * @return String, This constraint code will be within the create table syntax during table creation.
     */
    public String getConstraintSQL() {
        return constraintSQL;
    }

    /**
     * @param constraintSQL
     */
    public void setConstraintSQL(String constraintSQL) {
        this.constraintSQL = constraintSQL;
    }

    /**
     * @return Boolean, Put this field into the POJO constructor list if indicated on by Table.ConstructorFromFieldFlag.
     */
    public Boolean isPojoConstructorFieldFlag() {
        return pojoConstructorFieldFlag;
    }

    /**
     * @param pojoConstructorFieldFlag
     */
    public void setPojoConstructorFieldFlag(Boolean pojoConstructorFieldFlag) {
        this.pojoConstructorFieldFlag = pojoConstructorFieldFlag;
    }

    /**
     * @return String, Name of this field that should appear in a GUI editor as either a Column Header, or Field Name.
     */
    public String getGuiHeader() {
        return guiHeader;
    }

    /**
     * @param guiHeader
     */
    public void setGuiHeader(String guiHeader) {
        this.guiHeader = guiHeader;
    }

    /**
     * @return String, Helpful data that shall appear within a tooltip upon mouse hover/click for this field.
     */
    public String getGuiToolTip() {
        return guiToolTip;
    }

    /**
     * @param guiToolTip
     */
    public void setGuiToolTip(String guiToolTip) {
        this.guiToolTip = guiToolTip;
    }

    /**
     * @return Boolean, Whether this field can be used to sort the table in a grid view.
     */
    public Boolean isGuiSortableFlag() {
        return guiSortableFlag;
    }

    /**
     * @param guiSortableFlag
     */
    public void setGuiSortableFlag(Boolean guiSortableFlag) {
        this.guiSortableFlag = guiSortableFlag;
    }

    /**
     * @return String, Name of the field group that this column belongs to in editor, can be null if not part of a group.
     */
    public String getGuiFieldGroupName() {
        return guiFieldGroupName;
    }

    /**
     * @param guiFieldGroupName
     */
    public void setGuiFieldGroupName(String guiFieldGroupName) {
        this.guiFieldGroupName = guiFieldGroupName;
    }

    /**
     * @return Short, Sequence in which this field should appear withing group.
     */
    public Short getGuiFieldGroupSequence() {
        return guiFieldGroupSequence;
    }

    /**
     * @param guiFieldGroupSequence
     */
    public void setGuiFieldGroupSequence(Short guiFieldGroupSequence) {
        this.guiFieldGroupSequence = guiFieldGroupSequence;
    }
    
    /**
     * @return Column, Column which activates/deactivates this group.  (I know this is currently not 3rd Normal Form�)
     */
    @JsonManagedReference("guardedColumns")
    @JsonIgnore
    public Column getGuiFieldGroupGuardColumn() {
        return columnDao.findByPK(guiFieldGroupGuardColumnId);
    }

    /**
     * @param guiFieldGroupGuardColumn
     */
    @JsonManagedReference("guardedColumns")
    @JsonIgnore
    public void setGuiFieldGroupGuardColumn(Column guiFieldGroupGuardColumn) {
        this.guiFieldGroupGuardColumnId = guiFieldGroupGuardColumn.getId();
    }

    /**
     * @return Integer, Id for Column which activates/deactivates this group.  (I know this is currently not 3rd Normal Form�)
     */
    public Integer getGuiFieldGroupGuardColumnId() {
        return guiFieldGroupGuardColumnId;
    }

    /**
     * @param guiFieldGroupGuardColumnId
     */
    public void setGuiFieldGroupGuardColumnId(Integer guiFieldGroupGuardColumnId) {
        this.guiFieldGroupGuardColumnId = guiFieldGroupGuardColumnId;
    }

    /**
     * @return Boolean, If this field is Not Null when the group is active.
     */
    public Boolean isGuiFieldGroupFieldRequiredFlag() {
        return guiFieldGroupFieldRequiredFlag;
    }

    /**
     * @param guiFieldGroupFieldRequiredFlag
     */
    public void setGuiFieldGroupFieldRequiredFlag(Boolean guiFieldGroupFieldRequiredFlag) {
        this.guiFieldGroupFieldRequiredFlag = guiFieldGroupFieldRequiredFlag;
    }

    /**
     * @return List<Column> 
     */
    @JsonBackReference("guardedColumns")
    public List<Column> getGuardedColumns() {
        if (guardedColumns == null){
            guardedColumns = new ArrayList<Column>();
        } else {

            short i = 0;
            for (Column Column : guardedColumns){
                if (Column != null){
                    if (!Column.getGuiFieldGroupSequence().equals(i))
                        Column.setGuiFieldGroupSequence(i);
                    i++;
                }
            }
        }

        return guardedColumns;
    }

    /**
     * @param guardedColumns
     */
    @JsonBackReference("guardedColumns")
    @SuppressWarnings("unused")
    private void setGuardedColumns(List<Column> guardedColumns) {
        this.guardedColumns = guardedColumns;
    }

}