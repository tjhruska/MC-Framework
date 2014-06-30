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
class McInstallerForProjects extends McInstaller {

  //this class is split from the McInstallerForProjects class to keep down the spring warnings for the post processor if it also loaded itself as a bean
  McInstaller baseInstaller
  
  void run(){
    baseInstaller.run()
  }
  
}
