package com.tjhruska.mc.apps

import javax.annotation.Resource

import org.apache.tomcat.jdbc.pool.DataSource

import org.hibernate.SessionFactory

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource
import org.springframework.context.annotation.PropertySource
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.core.JdbcOperations
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.orm.hibernate4.LocalSessionFactoryBean

import com.tjhruska.mc.database.restrictions.RestrictionHelperHibernate
import com.tjhruska.mc.util.spring.DaoLoader


@Configuration
@ImportResource( [ 'classpath:hibernateConfig.xml' ] )
class DataResourcesConfig {

  @Value('${spring.datasource.driverClassName}')
  private String driverClassName

  @Value('${spring.datasource.url}')
  private String url

  @Value('${spring.datasource.username}')
  private String username

  @Value('${spring.datasource.password}')
  private String password

  @Bean
  public DataSource dataSource(){
    org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource()
    ds.setDriverClassName(driverClassName)
    ds.setUrl(url)
    ds.setUsername(username)
    ds.setPassword(password)
    ds.setInitialSize(5)
    ds.setMaxActive(10)
    ds.setMaxIdle(5)
    ds.setMinIdle(2)
    return ds
  }

  @Bean
  public JdbcOperations jdbcTemplate() {
    new JdbcTemplate(dataSource())
  }

  @Bean
  public RestrictionHelperHibernate restrictionHelper() {
    new RestrictionHelperHibernate()
  }
}