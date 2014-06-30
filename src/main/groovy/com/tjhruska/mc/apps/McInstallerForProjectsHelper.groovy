package com.tjhruska.mc.apps

import groovy.util.logging.Slf4j;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.tjhruska.mc.util.BeanNameAwareRunnable;
import com.tjhruska.mc.util.Duo;
import com.tjhruska.mc.util.spring.Context;
import com.tjhruska.mc.util.tagReplacement.SQLTemplate;

@Slf4j
class McInstallerForProjectsHelper extends McInstaller implements BeanPostProcessor {

  static <T extends McInstaller> T configure(String baseName){
    String configName = "${baseName}_context.xml"
    ApplicationContext context = Context.getClassPathXmlApplicationContext("conf/mcInstaller/${configName}")
    
    if (projectInstaller != null){
      baseInstaller.getInstallProperties().putAll(projectInstaller.getInstallProperties())
	    baseInstaller.getInstalls().addAll(projectInstaller.getInstalls())
	    projectInstaller.setBaseInstaller(baseInstaller)
	    return projectInstaller
    }else{
	    return baseInstaller
    }
  }
  
  Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    return bean
  }
  
  Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if (bean instanceof McInstallerForProjects)
      projectInstaller = (McInstallerForProjects) bean
    else if (bean instanceof McInstaller)
      baseInstaller = (McInstaller) bean
    return bean
  }

  static McInstaller baseInstaller
  static McInstallerForProjects projectInstaller
  
}
