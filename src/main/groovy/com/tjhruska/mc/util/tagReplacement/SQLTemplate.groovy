/**
Copyright 2011-2015 Timothy James Hruska (tjhruska@yahoo.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.tjhruska.mc.util.tagReplacement

import java.sql.SQLException
import java.util.List
import java.util.Map

import org.apache.tomcat.jdbc.pool.DataSource
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowCallbackHandler
import org.springframework.jdbc.core.RowMapper
import org.springframework.transaction.annotation.Transactional
import com.tjhruska.mc.util.tagReplacement.TemplateImpl

interface SQLTemplate extends Template {

  DataSource getDataSource()
  void setDataSource(DataSource dataSource)

  JdbcTemplate getJdbcTemplate()
  void setJdbcTemplate(JdbcTemplate jdbcTemplate)

  abstract <T> List<T> query(Map<String, String> parameters, RowMapper<T> rm)

  void query(Map<String, String> parameters, RowCallbackHandler rch)

  int update(Map<String, String> parameters)

  void jdbcQuery(Map<String, String> parameters) throws SQLException
}