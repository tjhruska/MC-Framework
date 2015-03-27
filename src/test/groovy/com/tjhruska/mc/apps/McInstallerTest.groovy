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

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import([ McInstallerTestConfig.class ])
public class McInstallerTest {
  private static final Logger log = LoggerFactory.getLogger(McInstallerTest.class)

  static void main(String[] args) {
    log.info('Starting up McInstallerTest')
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
        McInstallerTestConfig.class)

    System.out.println('Let\'s inspect the beans:')

    String[] beanNames = applicationContext.getBeanDefinitionNames()
    Arrays.sort(beanNames)
    for (String beanName : beanNames) {
      System.out.println(beanName)
    }

    McInstaller projectMcInstaller = (McInstaller) applicationContext.getBean('projectMcInstaller')
    try {
      projectMcInstaller.run()
    } finally {
      log.info('Shutting down McInstallerTest')
      applicationContext.close()
    }
  }
}