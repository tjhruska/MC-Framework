
/**
 *   Copyright  2011 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.mc.domain.system
 *   EnumerationValue.java
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

public class EnumerationValue extends BaseDomain {


    @Resource (name="enumerationDao")
    private DaoDomain<Enumeration> enumerationDao;

    private Integer enumerationId;
    private String name;
    private String description;
    private Short sequence;
    private String column1Value;
    private String column2Value;
    private String column3Value;
    private String column4Value;
    private String column5Value;
    
    /**
     * @return Enumeration, Link back to parent enumeration definition.
     */
    @JsonManagedReference("enumerationValues")
    @JsonIgnore
    public Enumeration getEnumeration() {
        return enumerationDao.findByPK(enumerationId);
    }

    /**
     * @param enumeration
     */
    @JsonManagedReference("enumerationValues")
    @JsonIgnore
    public void setEnumeration(Enumeration enumeration) {
        this.enumerationId = enumeration.getId();
    }

    /**
     * @return Integer, Id for Link back to parent enumeration definition.
     */
    public Integer getEnumerationId() {
        return enumerationId;
    }

    /**
     * @param enumerationId
     */
    public void setEnumerationId(Integer enumerationId) {
        this.enumerationId = enumerationId;
    }

    /**
     * @return String, Name of the enumeration instance for this value.
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
     * @return String, Description for this value.
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
     * @return Short, Sequence within the enuemration/class for this value.
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
     * @return String, Value for column/field.
     */
    public String getColumn1Value() {
        return column1Value;
    }

    /**
     * @param column1Value
     */
    public void setColumn1Value(String column1Value) {
        this.column1Value = column1Value;
    }

    /**
     * @return String, Value for column/field.
     */
    public String getColumn2Value() {
        return column2Value;
    }

    /**
     * @param column2Value
     */
    public void setColumn2Value(String column2Value) {
        this.column2Value = column2Value;
    }

    /**
     * @return String, Value for column/field.
     */
    public String getColumn3Value() {
        return column3Value;
    }

    /**
     * @param column3Value
     */
    public void setColumn3Value(String column3Value) {
        this.column3Value = column3Value;
    }

    /**
     * @return String, Value for column/field.
     */
    public String getColumn4Value() {
        return column4Value;
    }

    /**
     * @param column4Value
     */
    public void setColumn4Value(String column4Value) {
        this.column4Value = column4Value;
    }

    /**
     * @return String, Value for column/field.
     */
    public String getColumn5Value() {
        return column5Value;
    }

    /**
     * @param column5Value
     */
    public void setColumn5Value(String column5Value) {
        this.column5Value = column5Value;
    }

}