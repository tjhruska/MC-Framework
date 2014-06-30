package com.tjhruska.mc.util

import groovy.transform.CompileStatic;
import groovy.util.logging.Slf4j;

import java.util.ArrayList
import java.util.Collections
import java.util.List

import org.junit.Assert
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.LogbackException;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.status.Status;

import com.google.common.base.Objects

@Slf4j
public class VerifyAppenderLogback implements Appender, VerifyAppender {
 
  Class classUnderTest 
  private Level verifyLevel
  private Level originalLevel
  private List<LogMessage> loggingToVerify
  Logger logger
  
  public VerifyAppenderLogback(Class classUnderTest, Level verifyLevel) {
    this.classUnderTest = classUnderTest
    this.verifyLevel = verifyLevel
    loggingToVerify = Collections.synchronizedList(new ArrayList<VerifyAppenderLogback.LogMessage>())
   
    Logger root = (Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    if (!originalLevel.equals(verifyLevel)) {
      originalLevel = root.getLevel()
	    log.info("VerifyAppenderLogback setting logging level to {}, original was {}", verifyLevel, originalLevel)
	    root.setLevel(verifyLevel);
    }
    
    //setup logback
    logger = (Logger) LoggerFactory.getLogger(classUnderTest);
    logger.addAppender(this);
  }
  
  public void demandLog(Object level, String message, Boolean substringMatch=false) {
    if(((Level)level).levelInt >= verifyLevel.levelInt)
      loggingToVerify.add(new LogMessage(level, message, substringMatch))
  }
  
  public void demandLog(Object level, String message, Exception e, Boolean substringMatch=false) {
    if(((Level)level).levelInt >= verifyLevel.levelInt)
      loggingToVerify.add(new LogMessage(level, message, e, substringMatch))
  }
  
  public void verify() {
    if (originalLevel != null) {
	    log.info("VerifyAppenderLogback restoring logging level to {}", originalLevel)
      Logger root = (Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
      root.setLevel(originalLevel);
    }
    
    logger.detachAppender(this)
    if (!loggingToVerify.isEmpty()) {
      throw new AssertionError("Logging demand registered, but not observed at time of verify: " + loggingToVerify.remove(0))
    }
  }
  
  //logback methods
  @Override
  public void doAppend(Object event) throws LogbackException {
    LoggingEvent le = (ch.qos.logback.classic.spi.LoggingEvent)event
    
    LogMessage actualMessage = new LogMessage(le)
    if (actualMessage.level.levelInt >= verifyLevel.levelInt) {
      if (loggingToVerify.isEmpty()) {
        throw new AssertionError("Missing demand for logger: " + actualMessage)
      }
      LogMessage expectedMessage = loggingToVerify.remove(0)
    
      Assert.assertEquals("Log message didn't match demanded message.", expectedMessage, actualMessage)
    }
  }

  private class LogMessage {
    private Level level
    private Object message
    private Object throwable
    private Boolean substringMatch
    
    public LogMessage(LoggingEvent loggingEvent) {
      this(loggingEvent.getLevel(), loggingEvent.getFormattedMessage(), loggingEvent.getThrowableProxy()?.getThrowable(), false)
    }
    
    public LogMessage(Level level, String message, Boolean substringMatch) {
      this(level, message, (Throwable)null, substringMatch)
    }
    
    public LogMessage(Level level, Object message, Throwable throwable, Boolean substringMatch) {
      this.level = level
      this.message = message
      this.throwable = throwable
      this.substringMatch = substringMatch
    }
    
    @Override
    public boolean equals(Object object) {
      if (object == null || !(object instanceof LogMessage))
        return false
      LogMessage other = (LogMessage) object
      return Objects.equal(level, other.level) && Objects.equal(throwable, other.throwable) && 
        ((substringMatch== true && other.message.startsWith(message)) || Objects.equal(message, other.message) )
    }
    
    @Override
    public String toString() {
      return level.toString() + " " + message + (throwable == null ? "" : " (" + throwable.getClass().getName() + ")")
    }
  }
  
  @Override
  public void start() {
    //no-op
  }

  @Override
  public void stop() {
    //no-op
  }

  @Override
  public boolean isStarted() {
    //no-op
    return false;
  }

  @Override
  public void setContext(Context context) {
    //no-op
  }

  @Override
  public Context getContext() {
    //no-op
    return null;
  }

  @Override
  public void addStatus(Status status) {
    //no-op
  }

  @Override
  public void addInfo(String msg) {
    //no-op
  }

  @Override
  public void addInfo(String msg, Throwable ex) {
    //no-op
  }

  @Override
  public void addWarn(String msg) {
    //no-op
  }

  @Override
  public void addWarn(String msg, Throwable ex) {
    //no-op
  }

  @Override
  public void addError(String msg) {
    //no-op
  }

  @Override
  public void addError(String msg, Throwable ex) {
    //no-op
  }

  @Override
  public void addFilter(Filter newFilter) {
    //no-op
  }

  @Override
  public void clearAllFilters() {
    //no-op
  }

  @Override
  public List getCopyOfAttachedFiltersList() {
    //no-op
    return null;
  }

  @Override
  public FilterReply getFilterChainDecision(Object event) {
    //no-op
    return null;
  }
  
  @Override
  public String getName() {
    //no-op
    return null;
  }
  
  @Override
  public void setName(String name) {
    //no-op
  }
}
