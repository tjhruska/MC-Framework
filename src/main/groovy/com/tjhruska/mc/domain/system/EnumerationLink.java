
/**
 *   Copyright 2011 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.mc.domain.system
 *   EnumerationLink.java
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
import com.tjhruska.mc.domain.system.EnumerationLinkValue;
import com.tjhruska.mc.enums.Datatype;
import com.tjhruska.mc.enums.DbIndex;
import com.tjhruska.mc.enums.LinkCreationRule;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class EnumerationLink extends BaseDomain {


    @Resource (name="enumerationDao")
    private DaoDomain<Enumeration> enumerationDao;

    private LinkCreationRule linkCreationRule;
    private Integer enumerationAId;
    private Integer enumerationBId;
    private DbIndex enumerationAIndex;
    private DbIndex enumerationBIndex;
    private Boolean uniqueFlag;
    private Boolean notNullFlag;
    private String name;
    private String tableName;
    private String tableNameCamelCase;
    private String schemaName;
    private String postCreateSQL;
    private Integer childTableSynchMethod;
    private String column1Name;
    private String column1NameCamelCase;
    private Datatype column1DataType;
    private DbIndex column1DBIndex;
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
    private List<EnumerationLinkValue> linkValues;

    /**
     * @return LinkCreationRule, Type of link to create, and which table holds the linking column.
     */
    public LinkCreationRule getLinkCreationRule() {
        return linkCreationRule;
    }

    /**
     * @param linkCreationRule
     */
    public void setLinkCreationRule(LinkCreationRule linkCreationRule) {
        this.linkCreationRule = linkCreationRule;
    }
    
    /**
     * @return Enumeration, Table that is part of the link.
     */
    @JsonManagedReference("linksAsA")
    @JsonIgnore
    public Enumeration getEnumerationA() {
        return enumerationDao.findByPK(enumerationAId);
    }

    /**
     * @param enumerationA
     */
    @JsonManagedReference("linksAsA")
    @JsonIgnore
    public void setEnumerationA(Enumeration enumerationA) {
        this.enumerationAId = enumerationA.getId();
    }

    /**
     * @return Integer, Id for Table that is part of the link.
     */
    public Integer getEnumerationAId() {
        return enumerationAId;
    }

    /**
     * @param enumerationAId
     */
    public void setEnumerationAId(Integer enumerationAId) {
        this.enumerationAId = enumerationAId;
    }
    
    /**
     * @return Enumeration, Table that is part of the link.
     */
    @JsonManagedReference("linksAsB")
    @JsonIgnore
    public Enumeration getEnumerationB() {
        return enumerationDao.findByPK(enumerationBId);
    }

    /**
     * @param enumerationB
     */
    @JsonManagedReference("linksAsB")
    @JsonIgnore
    public void setEnumerationB(Enumeration enumerationB) {
        this.enumerationBId = enumerationB.getId();
    }

    /**
     * @return Integer, Id for Table that is part of the link.
     */
    public Integer getEnumerationBId() {
        return enumerationBId;
    }

    /**
     * @param enumerationBId
     */
    public void setEnumerationBId(Integer enumerationBId) {
        this.enumerationBId = enumerationBId;
    }

    /**
     * @return DbIndex, Index to create on the table to speed look up on this link.
     */
    public DbIndex getEnumerationAIndex() {
        return enumerationAIndex;
    }

    /**
     * @param enumerationAIndex
     */
    public void setEnumerationAIndex(DbIndex enumerationAIndex) {
        this.enumerationAIndex = enumerationAIndex;
    }

    /**
     * @return DbIndex, Index to create on the table to speed look up on this link.
     */
    public DbIndex getEnumerationBIndex() {
        return enumerationBIndex;
    }

    /**
     * @param enumerationBIndex
     */
    public void setEnumerationBIndex(DbIndex enumerationBIndex) {
        this.enumerationBIndex = enumerationBIndex;
    }

    /**
     * @return Boolean, indicates if instances of this link are unique.
     */
    public Boolean isUniqueFlag() {
        return uniqueFlag;
    }

    /**
     * @param uniqueFlag
     */
    public void setUniqueFlag(Boolean uniqueFlag) {
        this.uniqueFlag = uniqueFlag;
    }

    /**
     * @return Boolean, Indicates if a fk whether or not it can be null.
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
     * @return String, Name of the link.
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
     * @return String, Name of the linking table if a table is created.
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
     * @return String, Name of the link if created within java.
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
     * @return Integer, Rules to follow on when to synchronize to child tables.
     */
    public Integer getChildTableSynchMethod() {
        return childTableSynchMethod;
    }

    /**
     * @param childTableSynchMethod
     */
    public void setChildTableSynchMethod(Integer childTableSynchMethod) {
        this.childTableSynchMethod = childTableSynchMethod;
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
    public DbIndex getColumn1DBIndex() {
        return column1DBIndex;
    }

    /**
     * @param column1DBIndex
     */
    public void setColumn1DBIndex(DbIndex column1DBIndex) {
        this.column1DBIndex = column1DBIndex;
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
     * @return List<EnumerationLinkValue> 
     */
    @JsonBackReference("linkValues")
    public List<EnumerationLinkValue> getLinkValues() {
        if (linkValues == null){
            linkValues = new ArrayList<EnumerationLinkValue>();
        } else {

            short i = 0;
            for (EnumerationLinkValue EnumerationLinkValue : linkValues){
                if (EnumerationLinkValue != null){
                    if (!EnumerationLinkValue.getSequence().equals(i))
                        EnumerationLinkValue.setSequence(i);
                    i++;
                }
            }
        }

        return linkValues;
    }

    /**
     * @param linkValues
     */
    @JsonBackReference("linkValues")
    @SuppressWarnings("unused")
    private void setLinkValues(List<EnumerationLinkValue> linkValues) {
        this.linkValues = linkValues;
    }

}