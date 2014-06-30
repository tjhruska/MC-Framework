
/**
 *   Copyright  2011 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.mc.domain.system
 *   Child.java
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

public class Child extends BaseDomain {

    public Child (){
    }

    private Integer tableId;
    private Integer parentId;
    private String description;
    private String nameCamelCase;
    private String ormDiscriminatorValue;
    private Boolean createConstraintUsingDiscriminatorFlag;
    private String extraJavaCode;
    private String extraORMCode;

    /**
     * @return Integer, Id for Original ancestor in which this child is inherited from.  (Always filled.)
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
     * @return Integer, Id for Immediate parent, if not the original ancestor.  (Don't fill this in on first gen children.)
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return String, Child table/POJO which can add columns/indexes.
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
     * @return String, Used to map records to subclasses within mapping file.
     */
    public String getOrmDiscriminatorValue() {
        return ormDiscriminatorValue;
    }

    /**
     * @param ormDiscriminatorValue
     */
    public void setOrmDiscriminatorValue(String ormDiscriminatorValue) {
        this.ormDiscriminatorValue = ormDiscriminatorValue;
    }

    /**
     * @return Boolean, Create constraint on table to limit fields to match introduction by subclass.
     */
    public Boolean isCreateConstraintUsingDiscriminatorFlag() {
        return createConstraintUsingDiscriminatorFlag;
    }

    /**
     * @param createConstraintUsingDiscriminatorFlag
     */
    public void setCreateConstraintUsingDiscriminatorFlag(Boolean createConstraintUsingDiscriminatorFlag) {
        this.createConstraintUsingDiscriminatorFlag = createConstraintUsingDiscriminatorFlag;
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

}