
/**
 *   Copyright  2011 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.mc.domain.system
 *   Index.java
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
import com.tjhruska.mc.enums.DbIndex;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class Index extends BaseDomain {

    public Index (){
    }


    @Resource (name="tableDao")
    private DaoDomain<Table> tableDao;

    private Integer tableId;
    private DbIndex index;
    private String indexName;
    private List<Column> columns;
    
    /**
     * @return Table, Table/POJO on which to create the index/lookup.
     */
    @JsonManagedReference("indexes")
    @JsonIgnore
    public Table getTable() {
        return tableDao.findByPK(tableId);
    }

    /**
     * @param table
     */
    @JsonManagedReference("indexes")
    @JsonIgnore
    public void setTable(Table table) {
        this.tableId = table.getId();
    }

    /**
     * @return Integer, Id for Table/POJO on which to create the index/lookup.
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
     * @return DbIndex, Type of Index.
     */
    public DbIndex getIndex() {
        return index;
    }

    /**
     * @param index
     */
    public void setIndex(DbIndex index) {
        this.index = index;
    }

    /**
     * @return String, Name for the index.
     */
    public String getIndexName() {
        return indexName;
    }

    /**
     * @param indexName
     */
    public void setIndexName(String indexName) {
        this.indexName = indexName;
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

}