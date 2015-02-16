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