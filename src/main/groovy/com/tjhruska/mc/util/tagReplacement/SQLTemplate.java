package com.tjhruska.mc.util.tagReplacement;

import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;


public class SQLTemplate extends Template {
	private static final Logger log
		= LoggerFactory.getLogger(SQLTemplate.class);

//	private DriverManagerDataSource dataSource;
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}

	public DataSource getDataSource() {
		return dataSource;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public SQLTemplate() {
		super();
	}

	public SQLTemplate(String template) {
		super(template);
	}

	public SQLTemplate(String template, Boolean keepUnusedTags, Integer maxIterations){
		super(template, keepUnusedTags, maxIterations);
	}
	
	public SQLTemplate(DataSource dataSource, JdbcTemplate jdbcTemplate){
		super();
		this.dataSource = dataSource;
		this.jdbcTemplate = jdbcTemplate;
	}

	public <T> List<T> query(Map<String, String> parameters, RowMapper<T> rm) {
		return jdbcTemplate.query(applyTags(parameters).toString(), rm);
	}
	
	public void query(Map<String, String> parameters, RowCallbackHandler rch){
		jdbcTemplate.query(applyTags(parameters).toString(), rch);
	}
	
	public int update(Map<String, String> parameters) {
		return jdbcTemplate.update(applyTags(parameters).toString());
	}
	
	public void jdbcQuery(Map<String, String> parameters) throws SQLException{
		String sql = applyTags(parameters).toString();
		Connection connection = dataSource.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.execute(sql);
		} catch (SQLException e) {
			log.error("query " + getBeanName() 
				+ " failed with the following exception:", e);
			//log.error("query {} sql:\n{}", getBeanName(), sql);
			throw e;
		} finally {
		  statement.close();
		}
	}
}
