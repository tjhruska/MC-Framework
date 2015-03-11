package com.tjhruska.mc.util.tagReplacement

import java.sql.SQLException
import java.util.List
import java.util.Map

import org.apache.tomcat.jdbc.pool.DataSource
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowCallbackHandler
import org.springframework.jdbc.core.RowMapper
import org.springframework.transaction.annotation.Transactional

interface SQLTemplate {

  void setDataSource(DataSource dataSource)

  void setJdbcTemplate(JdbcTemplate jdbcTemplate)

  public abstract <T> List<T> query(Map<String, String> parameters, RowMapper<T> rm)

  void query(Map<String, String> parameters, RowCallbackHandler rch)

  int update(Map<String, String> parameters)

  void jdbcQuery(Map<String, String> parameters) throws SQLException
}