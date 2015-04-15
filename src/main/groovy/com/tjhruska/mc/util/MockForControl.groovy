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

package com.tjhruska.mc.util

import static org.junit.Assert.*

import org.apache.log4j.Level
import static org.apache.log4j.Level.*
import org.apache.log4j.Logger

import groovy.mock.interceptor.MockFor

class MockForControl {

  List<Duo<MockFor, GroovyObject>> proxies
  VerifyAppender verifyAppender

  MockForControl(Class classUnderTest, Level level) {
    this.proxies = []
    verifyAppender = new VerifyAppenderLog4j(classUnderTest, level)
  }

  MockForControl(Class classUnderTest, ch.qos.logback.classic.Level level) {
    this.proxies = []
    verifyAppender = new VerifyAppenderLogback(classUnderTest, level)
  }

  def createMock(Class clazz, boolean interceptConstruction = false) {
    new MockForControlled(this, clazz, interceptConstruction)
  }

  def registerProxy(MockFor mock, GroovyObject proxy) {
    proxies.add(new Duo(mock, proxy))
    proxy
  }

  void verify() {
    try {
      proxies.each { proxyDuo ->
        MockFor mock = proxyDuo.one
        GroovyObject proxy = proxyDuo.two
        mock.verify(proxy)
      }
    } finally {
      verifyAppender.verify()
    }
  }

  void demandLog(Object level, String message, Boolean substringMatch=false) {
    verifyAppender.demandLog(level, message, substringMatch)
  }

  void demandLog(Object level, String message, Exception e, Boolean substringMatch=false) {
    verifyAppender.demandLog(level, message, e, substringMatch)
  }

  /*
   *  Usage:
   *  import static MockForControl.*
   *
   *    jesqueClientMock.demand.acquireLock expects(true, [eq('lockString', 'LOCK-PendingNRJobQueueLoader'), eq('threadName', uniqueThreadName), {actual-> assertEquals('lockTime was not equal', 600, actual)}])
   *
   *  -the expectation in the expectations list needs to be a closure that takes the actual argument
   *  -they can either be static methods defined on this class, or any other closure intended to validate the argument
   */
  static Closure expects(Object returnObject, List expectations) { {
      Object... args ->
      assertEquals("argument list size didn't match expectations", expectations.size(), args.size())
      args.size().times { i ->
        expectations[i] (args[i])
      }
      returnObject
    }
  }

  static Closure eq(String name, Object expectedObject) { {
      actualObject ->
      assertEquals("${name} was not equal:", expectedObject, actualObject)
    }
  }

  static Closure notNull(String name) { {
      actualObject ->
      assertNotNull("${name} was null:", actualObject)
    }
  }

  static Closure expNull(String name) { {
      actualObject ->
      assertNull("${name} was not null:", actualObject)
    }
  }
}