
/**
 *   Copyright  2011 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.mc.domain.system
 *   Table.java
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
import com.tjhruska.mc.domain.system.Column;
import com.tjhruska.mc.domain.system.Index;
import com.tjhruska.mc.enums.TableCreationRule;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class Table extends BaseDomain {

    public Table (){
    }

    private Boolean systemNoModifyFlag;
    private String name;
    private String description;
    private String nameCamelCase;
    private TableCreationRule tableCreationRule;
    private Boolean suppressTableChangesFlag;
    private Boolean createIUDTableFlag;
    private String schemaName;
    private String withinCreateSQL;
    private String postCreateSQL;
    private Boolean createPOJOFlag;
    private String srcFolderTag;
    private Boolean suppressPOJOChangesFlag;
    private String javaPackage;
    private String javaFullyQualifiedSuperClass;
    private Boolean constructorFromFieldsFlag;
    private String extraJavaCode;
    private Boolean createORMFlag;
    private Boolean suppressORMChangesFlag;
    private String extraORMCode;
    private Integer ormDiscriminatorColumnId;
    private Boolean createGUIDataServiceFlag;
    private Boolean suppressGUIDataServiceChangesFlag;
    private Boolean guiPagifyFlag;
    private Short guiRecordsPerPage;
    private Integer guiPickerDescriptionColumnId;
    private List<Column> columns;
    private Set<Index> indexes;

    /**
     * @return Boolean, System Table - no schema changes.
     */
    public Boolean isSystemNoModifyFlag() {
        return systemNoModifyFlag;
    }

    /**
     * @param systemNoModifyFlag
     */
    public void setSystemNoModifyFlag(Boolean systemNoModifyFlag) {
        this.systemNoModifyFlag = systemNoModifyFlag;
    }

    /**
     * @return String, Table Name
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
     * @return String, Description of Table Usage
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
     * @return String, Name of "object" using ClassCamelCaseRules.
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
     * @return TableCreationRule, Rule to use on how to create tables.
     */
    public TableCreationRule getTableCreationRule() {
        return tableCreationRule;
    }

    /**
     * @param tableCreationRule
     */
    public void setTableCreationRule(TableCreationRule tableCreationRule) {
        this.tableCreationRule = tableCreationRule;
    }

    /**
     * @return Boolean, If True don't alter data model automatically.  If false program may modify.
     */
    public Boolean isSuppressTableChangesFlag() {
        return suppressTableChangesFlag;
    }

    /**
     * @param suppressTableChangesFlag
     */
    public void setSuppressTableChangesFlag(Boolean suppressTableChangesFlag) {
        this.suppressTableChangesFlag = suppressTableChangesFlag;
    }

    /**
     * @return Boolean, Generate Insert/Update/Deletion tracking table to track data changes, along with associated triggers.
     */
    public Boolean isCreateIUDTableFlag() {
        return createIUDTableFlag;
    }

    /**
     * @param createIUDTableFlag
     */
    public void setCreateIUDTableFlag(Boolean createIUDTableFlag) {
        this.createIUDTableFlag = createIUDTableFlag;
    }

    /**
     * @return String, Schema to use when generating sql for generation/altering the table.
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
     * @return String, When creating this table insert this sql after column list.
     */
    public String getWithinCreateSQL() {
        return withinCreateSQL;
    }

    /**
     * @param withinCreateSQL
     */
    public void setWithinCreateSQL(String withinCreateSQL) {
        this.withinCreateSQL = withinCreateSQL;
    }

    /**
     * @return String, When creating the model, run this sql after all tables have been created.  (Allows for circular fks to be created after model is in place.)
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
     * @return Boolean, Generate Plain Old Java Object (POJO).
     */
    public Boolean isCreatePOJOFlag() {
        return createPOJOFlag;
    }

    /**
     * @param createPOJOFlag
     */
    public void setCreatePOJOFlag(Boolean createPOJOFlag) {
        this.createPOJOFlag = createPOJOFlag;
    }

    /**
     * @return String, Root folder for java packages that will house generated pojos.
     */
    public String getSrcFolderTag() {
        return srcFolderTag;
    }

    /**
     * @param srcFolderTag
     */
    public void setSrcFolderTag(String srcFolderTag) {
        this.srcFolderTag = srcFolderTag;
    }

    /**
     * @return Boolean, If True don't create/recreate the POJO, if false rename old if exists, and create new.
     */
    public Boolean isSuppressPOJOChangesFlag() {
        return suppressPOJOChangesFlag;
    }

    /**
     * @param suppressPOJOChangesFlag
     */
    public void setSuppressPOJOChangesFlag(Boolean suppressPOJOChangesFlag) {
        this.suppressPOJOChangesFlag = suppressPOJOChangesFlag;
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
     * @return String, Fully Qualified java path to super class for POJO.
     */
    public String getJavaFullyQualifiedSuperClass() {
        return javaFullyQualifiedSuperClass;
    }

    /**
     * @param javaFullyQualifiedSuperClass
     */
    public void setJavaFullyQualifiedSuperClass(String javaFullyQualifiedSuperClass) {
        this.javaFullyQualifiedSuperClass = javaFullyQualifiedSuperClass;
    }

    /**
     * @return Boolean, Create a constructor utilizing columns as inputs, will be mared with a TODO.
     */
    public Boolean isConstructorFromFieldsFlag() {
        return constructorFromFieldsFlag;
    }

    /**
     * @param constructorFromFieldsFlag
     */
    public void setConstructorFromFieldsFlag(Boolean constructorFromFieldsFlag) {
        this.constructorFromFieldsFlag = constructorFromFieldsFlag;
    }

    /**
     * @return String, Add this java code to POJO after last method.
     */
    public String getExtraJavaCode() {
        return extraJavaCode;
    }

    /**
     * @param extraJavaCode
     */
    public void setExtraJavaCode(String extraJavaCode) {
        this.extraJavaCode = extraJavaCode;
    }

    /**
     * @return Boolean, Create Object Relational Mapping (ORM) files within same package as POJO.
     */
    public Boolean isCreateORMFlag() {
        return createORMFlag;
    }

    /**
     * @param createORMFlag
     */
    public void setCreateORMFlag(Boolean createORMFlag) {
        this.createORMFlag = createORMFlag;
    }

    /**
     * @return Boolean, If True don't create/recreate the ORM file, if False rename old, and create new.
     */
    public Boolean isSuppressORMChangesFlag() {
        return suppressORMChangesFlag;
    }

    /**
     * @param suppressORMChangesFlag
     */
    public void setSuppressORMChangesFlag(Boolean suppressORMChangesFlag) {
        this.suppressORMChangesFlag = suppressORMChangesFlag;
    }

    /**
     * @return String, Extra ORM code to be added to the end of the mapping file.
     */
    public String getExtraORMCode() {
        return extraORMCode;
    }

    /**
     * @param extraORMCode
     */
    public void setExtraORMCode(String extraORMCode) {
        this.extraORMCode = extraORMCode;
    }

    /**
     * @return Integer, Id for If the POJO shall be subclassed via the mc_child table use this descriminator column to manage subclassing within mapping file.
     */
    public Integer getOrmDiscriminatorColumnId() {
        return ormDiscriminatorColumnId;
    }

    /**
     * @param ormDiscriminatorColumnId
     */
    public void setOrmDiscriminatorColumnId(Integer ormDiscriminatorColumnId) {
        this.ormDiscriminatorColumnId = ormDiscriminatorColumnId;
    }

    /**
     * @return Boolean, Create Graphical User Inteface methods to allow for the online editing of this object utilizing created POJO, ORM and gui library.
     */
    public Boolean isCreateGUIDataServiceFlag() {
        return createGUIDataServiceFlag;
    }

    /**
     * @param createGUIDataServiceFlag
     */
    public void setCreateGUIDataServiceFlag(Boolean createGUIDataServiceFlag) {
        this.createGUIDataServiceFlag = createGUIDataServiceFlag;
    }

    /**
     * @return Boolean, If True don't create/recreate the GUI library for this object, if False rename old, and create new.
     */
    public Boolean isSuppressGUIDataServiceChangesFlag() {
        return suppressGUIDataServiceChangesFlag;
    }

    /**
     * @param suppressGUIDataServiceChangesFlag
     */
    public void setSuppressGUIDataServiceChangesFlag(Boolean suppressGUIDataServiceChangesFlag) {
        this.suppressGUIDataServiceChangesFlag = suppressGUIDataServiceChangesFlag;
    }

    /**
     * @return Boolean, If true GUI library for this object should provide pagification widgets.
     */
    public Boolean isGuiPagifyFlag() {
        return guiPagifyFlag;
    }

    /**
     * @param guiPagifyFlag
     */
    public void setGuiPagifyFlag(Boolean guiPagifyFlag) {
        this.guiPagifyFlag = guiPagifyFlag;
    }

    /**
     * @return Short, Requires gui_pagify_flag=true, show this many records per page.
     */
    public Short getGuiRecordsPerPage() {
        return guiRecordsPerPage;
    }

    /**
     * @param guiRecordsPerPage
     */
    public void setGuiRecordsPerPage(Short guiRecordsPerPage) {
        this.guiRecordsPerPage = guiRecordsPerPage;
    }

    /**
     * @return Integer, Id for Field to use as a unique descripter for record within containers like comboboxes.
     */
    public Integer getGuiPickerDescriptionColumnId() {
        return guiPickerDescriptionColumnId;
    }

    /**
     * @param guiPickerDescriptionColumnId
     */
    public void setGuiPickerDescriptionColumnId(Integer guiPickerDescriptionColumnId) {
        this.guiPickerDescriptionColumnId = guiPickerDescriptionColumnId;
    }

    /**
     * @return List<Column> 
     */
    @JsonBackReference("columns")
    public List<Column> getColumns() {
        if (columns == null){
            columns = new ArrayList<Column>();
        } else {

            short i = 0;
            for (Column Column : columns){
                if (Column != null){
                    if (!Column.getSequence().equals(i))
                        Column.setSequence(i);
                    i++;
                }
            }
        }

        return columns;
    }

    /**
     * @param columns
     */
    @JsonBackReference("columns")
    @SuppressWarnings("unused")
    private void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    /**
     * @return Set<Index> 
     */
    @JsonBackReference("indexes")
    public Set<Index> getIndexes() {
        if (indexes == null){
            indexes = new HashSet<Index>();
        }

        return indexes;
    }

    /**
     * @param indexes
     */
    @JsonBackReference("indexes")
    @SuppressWarnings("unused")
    private void setIndexes(Set<Index> indexes) {
        this.indexes = indexes;
    }

}