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