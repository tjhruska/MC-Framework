/**
 * Copyright 2011-2015 Timothy James Hruska (tjhruska@yahoo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tjhruska.mc.database.test

import groovy.util.logging.Slf4j

import org.hibernate.SessionFactory
import org.hibernate.metadata.ClassMetadata
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.BeansException
import org.springframework.beans.MutablePropertyValues
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.config.ConstructorArgumentValues
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.beans.factory.support.RootBeanDefinition
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.core.io.Resource

import com.tjhruska.mc.util.Duo

@Slf4j
public class GeneratedDomainAndDaoTestLoader implements BeanFactoryPostProcessor, ApplicationContextAware {

  private ApplicationContext applicationContext
  private Resource hibernateMappingDirectory

  public GeneratedDomainAndDaoTestLoader() {
  }

  public void setHibernateMappingDirectory(Resource hibernateMappingDirectory) {
    this.hibernateMappingDirectory = hibernateMappingDirectory
  }

  private List<Duo<String,Class>> getTestClassNames () {
    List<Duo<String,Class>> testClassNames = new ArrayList<Duo<String,Class>>()

    if (hibernateMappingDirectory != null) {
      try {
        File directory = hibernateMappingDirectory.getFile()
        File[] fList = directory.listFiles()
        for (File file : fList){
          addTestClassName(file.getName().substring(0, file.getName().length() - 8), testClassNames)
        }
      } catch (Exception e) {
        throw new RuntimeException("Failed to find list of hibernate mapping files", e)
      }
    } else {
      log.info("hibernateMappingDirectory not provided to GeneratedDomainAndDaoTestLoader so requesting sessionFactory bean.  (May have issues as this is a BeanFactoryPostProcessor).")
      SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory")
      Map<String, ClassMetadata> mappedDtoClasses = sessionFactory.getAllClassMetadata()
      log.info("found {} mappedDtoClasses using sessionFactory.", mappedDtoClasses.size())
      for (String key : mappedDtoClasses.keySet()){
        addTestClassName(key, testClassNames)
      }
    }
    return testClassNames
  }

  void addTestClassName(String baseName, List<Duo<String,Class>> testClassNames) {
    int index = baseName.lastIndexOf('.') + 1
    String beanId = baseName.substring(index, index + 1).toLowerCase() + baseName.substring(index + 1) + 'Test'
    try {
      String className = baseName + 'TestImplExtended'
      testClassNames.add(new Duo<String,Class>(beanId, Class.forName(className)))
      log.info("{} for mapping {}", beanId, className)
    } catch (ClassNotFoundException e) {
      String className = baseName + 'TestImpl'
      try {
        testClassNames.add(new Duo<String,Class>(beanId, Class.forName(className)))
        log.info("{} for mapping {}", beanId, className)
      } catch (ClassNotFoundException e2) {
        throw new RuntimeException("Failed to convert class name to Class object", e2)
      }
    }
  }

  /* (non-Javadoc)
   * @see org.springframework.beans.factory.config.BeanFactoryPostProcessor#postProcessBeanFactory(org.springframework.beans.factory.config.ConfigurableListableBeanFactory)
   */
  public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    BeanDefinitionRegistry registry = (BeanDefinitionRegistry)configurableListableBeanFactory

    for (Duo<String,Class> beanIdAndClass : getTestClassNames()) {
      //build constructor which is the DTO class
      ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues()
      //constructorArgumentValues.addGenericArgumentValue(beanIdAndClass.getTwo())

      //need mutable prop for the bean definition constructor
      MutablePropertyValues mutablePropertyValues = new MutablePropertyValues()

      //define the bean
      RootBeanDefinition beanDefinition = new RootBeanDefinition(beanIdAndClass.getTwo(), constructorArgumentValues, mutablePropertyValues)
      beanDefinition.setAutowireMode(RootBeanDefinition.AUTOWIRE_BY_NAME)

      //register it
      registry.registerBeanDefinition(beanIdAndClass.getOne(), beanDefinition)
    }
  }

  /* (non-Javadoc)
   * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
   */
  public void setApplicationContext(ApplicationContext applicationContext)
  throws BeansException {
    this.applicationContext = applicationContext
  }
}