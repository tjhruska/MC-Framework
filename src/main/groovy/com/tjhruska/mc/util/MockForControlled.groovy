package com.tjhruska.mc.util

import groovy.lang.MetaClass
import groovy.mock.interceptor.MockFor;

class MockForControlled extends MockFor {

  MockForControl control
  
  MockForControlled(MockForControl control, Class clazz, boolean interceptConstruction = false) {
    super(clazz, interceptConstruction)
    this.control = control
  }
  
  GroovyObject proxyInstance(args=null) {
    def proxy = makeProxyInstance(args, false)
    control.registerProxy(this, proxy)
  }

  GroovyObject proxyDelegateInstance(args=null) {
    def proxy = makeProxyInstance(args, true)
    control.registerProxy(this, proxy)
  }
}
