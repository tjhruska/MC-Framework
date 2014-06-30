
/**
 *   Copyright 2011 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.mc.domain.system
 *   Enumeration.java
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
import com.tjhruska.mc.domain.system.EnumerationLink;
import com.tjhruska.mc.domain.system.EnumerationValue;
import com.tjhruska.mc.enums.AutoCreationMethod;
import com.tjhruska.mc.enums.ChildTableSynchMethod;
import com.tjhruska.mc.enums.Datatype;
import com.tjhruska.mc.enums.DbIndex;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class Enumeration extends BaseDomain {

    private String name;
    private String tableName;
    private String tableNameCamelCase;
    private String schemaName;
    private String postCreateSQL;
    private ChildTableSynchMethod childTableSynchMethod;
    private AutoCreationMethod autoCreationMethod;
    private String folderTag;
    private String javaPackage;
    private String column1Name;
    private String column1NameCamelCase;
    private Datatype column1DataType;
    private DbIndex column1DbIndex;
    private Boolean column1NotNullFlag;
    private String column1Default;
    private String column2Name;
    private String column2NameCamelCase;
    private Datatype column2DataType;
    private DbIndex column2DBIndex;
    private Boolean column2NotNullFlag;
    private String column2Default;
    private String column3Name;
    private String column3NameCamelCase;
    private Datatype column3DataType;
    private DbIndex column3DBIndex;
    private Boolean column3NotNullFlag;
    private String column3Default;
    private String column4Name;
    private String column4NameCamelCase;
    private Datatype column4DataType;
    private DbIndex column4DBIndex;
    private Boolean column4NotNullFlag;
    private String column4Default;
    private String column5Name;
    private String column5NameCamelCase;
    private Datatype column5DataType;
    private DbIndex column5DBIndex;
    private Boolean column5NotNullFlag;
    private String column5Default;
    private List<EnumerationValue> enumerationValues;
    private Set<EnumerationLink> linksAsA;
    private Set<EnumerationLink> linksAsB;

    /**
     * @return String, Name to reference within code for lookups.
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
     * @return String, Name of table to create if synchronized.
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return String, Name of enumeration for use to name java enumeration/class.
     */
    public String getTableNameCamelCase() {
        return tableNameCamelCase;
    }

    /**
     * @param tableNameCamelCase
     */
    public void setTableNameCamelCase(String tableNameCamelCase) {
        this.tableNameCamelCase = tableNameCamelCase;
    }

    /**
     * @return String, Name of schema in database to create table.
     */
    public String getSchemaName() {
        return schemaName;
    }

    /**
     * @param schemaName
     */
    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    /**
     * @return String, SQL to run after table creation.
     */
    public String getPostCreateSQL() {
        return postCreateSQL;
    }

    /**
     * @param postCreateSQL
     */
    public void setPostCreateSQL(String postCreateSQL) {
        this.postCreateSQL = postCreateSQL;
    }

    /**
     * @return ChildTableSynchMethod, Rules to follow on when to synchronize to child tables.
     */
    public ChildTableSynchMethod getChildTableSynchMethod() {
        return childTableSynchMethod;
    }

    /**
     * @param childTableSynchMethod
     */
    public void setChildTableSynchMethod(ChildTableSynchMethod childTableSynchMethod) {
        this.childTableSynchMethod = childTableSynchMethod;
    }

    /**
     * @return AutoCreationMethod, Rules on when to create java enumerations/classes.
     */
    public AutoCreationMethod getAutoCreationMethod() {
        return autoCreationMethod;
    }

    /**
     * @param autoCreationMethod
     */
    public void setAutoCreationMethod(AutoCreationMethod autoCreationMethod) {
        this.autoCreationMethod = autoCreationMethod;
    }

    /**
     * @return String, Tag that identifies what source folder to use to create java code.
     */
    public String getFolderTag() {
        return folderTag;
    }

    /**
     * @param folderTag
     */
    public void setFolderTag(String folderTag) {
        this.folderTag = folderTag;
    }

    /**
     * @return String, Java package in which to place generated POJO.
     */
    public String getJavaPackage() {
        return javaPackage;
    }

    /**
     * @param javaPackage
     */
    public void setJavaPackage(String javaPackage) {
        this.javaPackage = javaPackage;
    }

    /**
     * @return String, Column Name on the Table.
     */
    public String getColumn1Name() {
        return column1Name;
    }

    /**
     * @param column1Name
     */
    public void setColumn1Name(String column1Name) {
        this.column1Name = column1Name;
    }

    /**
     * @return String, Field name within class/enumeration.
     */
    public String getColumn1NameCamelCase() {
        return column1NameCamelCase;
    }

    /**
     * @param column1NameCamelCase
     */
    public void setColumn1NameCamelCase(String column1NameCamelCase) {
        this.column1NameCamelCase = column1NameCamelCase;
    }

    /**
     * @return Datatype, Data type of the column, withing the database, java, orm, and js.
     */
    public Datatype getColumn1DataType() {
        return column1DataType;
    }

    /**
     * @param column1DataType
     */
    public void setColumn1DataType(Datatype column1DataType) {
        this.column1DataType = column1DataType;
    }

    /**
     * @return DbIndex, Index to create on the table to speed look up on this column.
     */
    public DbIndex getColumn1DbIndex() {
        return column1DbIndex;
    }

    /**
     * @param column1DbIndex
     */
    public void setColumn1DbIndex(DbIndex column1DbIndex) {
        this.column1DbIndex = column1DbIndex;
    }

    /**
     * @return Boolean, Nullness rule on this column.
     */
    public Boolean isColumn1NotNullFlag() {
        return column1NotNullFlag;
    }

    /**
     * @param column1NotNullFlag
     */
    public void setColumn1NotNullFlag(Boolean column1NotNullFlag) {
        this.column1NotNullFlag = column1NotNullFlag;
    }

    /**
     * @return String, Default value for this column
     */
    public String getColumn1Default() {
        return column1Default;
    }

    /**
     * @param column1Default
     */
    public void setColumn1Default(String column1Default) {
        this.column1Default = column1Default;
    }

    /**
     * @return String, Column Name on the Table.
     */
    public String getColumn2Name() {
        return column2Name;
    }

    /**
     * @param column2Name
     */
    public void setColumn2Name(String column2Name) {
        this.column2Name = column2Name;
    }

    /**
     * @return String, Field name within class/enumeration.
     */
    public String getColumn2NameCamelCase() {
        return column2NameCamelCase;
    }

    /**
     * @param column2NameCamelCase
     */
    public void setColumn2NameCamelCase(String column2NameCamelCase) {
        this.column2NameCamelCase = column2NameCamelCase;
    }

    /**
     * @return Datatype, Data type of the column, withing the database, java, orm, and js.
     */
    public Datatype getColumn2DataType() {
        return column2DataType;
    }

    /**
     * @param column2DataType
     */
    public void setColumn2DataType(Datatype column2DataType) {
        this.column2DataType = column2DataType;
    }

    /**
     * @return DbIndex, Index to create on the table to speed look up on this column.
     */
    public DbIndex getColumn2DBIndex() {
        return column2DBIndex;
    }

    /**
     * @param column2DBIndex
     */
    public void setColumn2DBIndex(DbIndex column2DBIndex) {
        this.column2DBIndex = column2DBIndex;
    }

    /**
     * @return Boolean, Nullness rule on this column.
     */
    public Boolean isColumn2NotNullFlag() {
        return column2NotNullFlag;
    }

    /**
     * @param column2NotNullFlag
     */
    public void setColumn2NotNullFlag(Boolean column2NotNullFlag) {
        this.column2NotNullFlag = column2NotNullFlag;
    }

    /**
     * @return String, Default value for this column
     */
    public String getColumn2Default() {
        return column2Default;
    }

    /**
     * @param column2Default
     */
    public void setColumn2Default(String column2Default) {
        this.column2Default = column2Default;
    }

    /**
     * @return String, Column Name on the Table.
     */
    public String getColumn3Name() {
        return column3Name;
    }

    /**
     * @param column3Name
     */
    public void setColumn3Name(String column3Name) {
        this.column3Name = column3Name;
    }

    /**
     * @return String, Field name within class/enumeration.
     */
    public String getColumn3NameCamelCase() {
        return column3NameCamelCase;
    }

    /**
     * @param column3NameCamelCase
     */
    public void setColumn3NameCamelCase(String column3NameCamelCase) {
        this.column3NameCamelCase = column3NameCamelCase;
    }

    /**
     * @return Datatype, Data type of the column, withing the database, java, orm, and js.
     */
    public Datatype getColumn3DataType() {
        return column3DataType;
    }

    /**
     * @param column3DataType
     */
    public void setColumn3DataType(Datatype column3DataType) {
        this.column3DataType = column3DataType;
    }

    /**
     * @return DbIndex, Index to create on the table to speed look up on this column.
     */
    public DbIndex getColumn3DBIndex() {
        return column3DBIndex;
    }

    /**
     * @param column3DBIndex
     */
    public void setColumn3DBIndex(DbIndex column3DBIndex) {
        this.column3DBIndex = column3DBIndex;
    }

    /**
     * @return Boolean, Nullness rule on this column.
     */
    public Boolean isColumn3NotNullFlag() {
        return column3NotNullFlag;
    }

    /**
     * @param column3NotNullFlag
     */
    public void setColumn3NotNullFlag(Boolean column3NotNullFlag) {
        this.column3NotNullFlag = column3NotNullFlag;
    }

    /**
     * @return String, Default value for this column
     */
    public String getColumn3Default() {
        return column3Default;
    }

    /**
     * @param column3Default
     */
    public void setColumn3Default(String column3Default) {
        this.column3Default = column3Default;
    }

    /**
     * @return String, Column Name on the Table.
     */
    public String getColumn4Name() {
        return column4Name;
    }

    /**
     * @param column4Name
     */
    public void setColumn4Name(String column4Name) {
        this.column4Name = column4Name;
    }

    /**
     * @return String, Field name within class/enumeration.
     */
    public String getColumn4NameCamelCase() {
        return column4NameCamelCase;
    }

    /**
     * @param column4NameCamelCase
     */
    public void setColumn4NameCamelCase(String column4NameCamelCase) {
        this.column4NameCamelCase = column4NameCamelCase;
    }

    /**
     * @return Datatype, Data type of the column, withing the database, java, orm, and js.
     */
    public Datatype getColumn4DataType() {
        return column4DataType;
    }

    /**
     * @param column4DataType
     */
    public void setColumn4DataType(Datatype column4DataType) {
        this.column4DataType = column4DataType;
    }

    /**
     * @return DbIndex, Index to create on the table to speed look up on this column.
     */
    public DbIndex getColumn4DBIndex() {
        return column4DBIndex;
    }

    /**
     * @param column4DBIndex
     */
    public void setColumn4DBIndex(DbIndex column4DBIndex) {
        this.column4DBIndex = column4DBIndex;
    }

    /**
     * @return Boolean, Nullness rule on this column.
     */
    public Boolean isColumn4NotNullFlag() {
        return column4NotNullFlag;
    }

    /**
     * @param column4NotNullFlag
     */
    public void setColumn4NotNullFlag(Boolean column4NotNullFlag) {
        this.column4NotNullFlag = column4NotNullFlag;
    }

    /**
     * @return String, Default value for this column
     */
    public String getColumn4Default() {
        return column4Default;
    }

    /**
     * @param column4Default
     */
    public void setColumn4Default(String column4Default) {
        this.column4Default = column4Default;
    }

    /**
     * @return String, Column Name on the Table.
     */
    public String getColumn5Name() {
        return column5Name;
    }

    /**
     * @param column5Name
     */
    public void setColumn5Name(String column5Name) {
        this.column5Name = column5Name;
    }

    /**
     * @return String, Field name within class/enumeration.
     */
    public String getColumn5NameCamelCase() {
        return column5NameCamelCase;
    }

    /**
     * @param column5NameCamelCase
     */
    public void setColumn5NameCamelCase(String column5NameCamelCase) {
        this.column5NameCamelCase = column5NameCamelCase;
    }

    /**
     * @return Datatype, Data type of the column, withing the database, java, orm, and js.
     */
    public Datatype getColumn5DataType() {
        return column5DataType;
    }

    /**
     * @param column5DataType
     */
    public void setColumn5DataType(Datatype column5DataType) {
        this.column5DataType = column5DataType;
    }

    /**
     * @return DbIndex, Index to create on the table to speed look up on this column.
     */
    public DbIndex getColumn5DBIndex() {
        return column5DBIndex;
    }

    /**
     * @param column5DBIndex
     */
    public void setColumn5DBIndex(DbIndex column5DBIndex) {
        this.column5DBIndex = column5DBIndex;
    }

    /**
     * @return Boolean, Nullness rule on this column.
     */
    public Boolean isColumn5NotNullFlag() {
        return column5NotNullFlag;
    }

    /**
     * @param column5NotNullFlag
     */
    public void setColumn5NotNullFlag(Boolean column5NotNullFlag) {
        this.column5NotNullFlag = column5NotNullFlag;
    }

    /**
     * @return String, Default value for this column
     */
    public String getColumn5Default() {
        return column5Default;
    }

    /**
     * @param column5Default
     */
    public void setColumn5Default(String column5Default) {
        this.column5Default = column5Default;
    }

    /**
     * @return List<EnumerationValue> 
     */
    @JsonBackReference("enumerationValues")
    public List<EnumerationValue> getEnumerationValues() {
        if (enumerationValues == null){
            enumerationValues = new ArrayList<EnumerationValue>();
        } else {

            short i = 0;
            for (EnumerationValue EnumerationValue : enumerationValues){
                if (EnumerationValue != null){
                    if (!EnumerationValue.getSequence().equals(i))
                        EnumerationValue.setSequence(i);
                    i++;
                }
            }
        }

        return enumerationValues;
    }

    /**
     * @param enumerationValues
     */
    @JsonBackReference("enumerationValues")
    @SuppressWarnings("unused")
    private void setEnumerationValues(List<EnumerationValue> enumerationValues) {
        this.enumerationValues = enumerationValues;
    }

    /**
     * @return Set<EnumerationLink> 
     */
    @JsonBackReference("linksAsA")
    public Set<EnumerationLink> getLinksAsA() {
        if (linksAsA == null){
            linksAsA = new HashSet<EnumerationLink>();
        }

        return linksAsA;
    }

    /**
     * @param linksAsA
     */
    @JsonBackReference("linksAsA")
    @SuppressWarnings("unused")
    private void setLinksAsA(Set<EnumerationLink> linksAsA) {
        this.linksAsA = linksAsA;
    }

    /**
     * @return Set<EnumerationLink> 
     */
    @JsonBackReference("linksAsB")
    public Set<EnumerationLink> getLinksAsB() {
        if (linksAsB == null){
            linksAsB = new HashSet<EnumerationLink>();
        }

        return linksAsB;
    }

    /**
     * @param linksAsB
     */
    @JsonBackReference("linksAsB")
    @SuppressWarnings("unused")
    private void setLinksAsB(Set<EnumerationLink> linksAsB) {
        this.linksAsB = linksAsB;
    }

}