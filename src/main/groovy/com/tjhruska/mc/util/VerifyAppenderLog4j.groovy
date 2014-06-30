package com.tjhruska.mc.util

import java.util.ArrayList
import java.util.Collections
import java.util.List

import org.junit.Assert
import org.slf4j.LoggerFactory;

import org.apache.log4j.AppenderSkeleton
import org.apache.log4j.Level
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent

import com.google.common.base.Objects

public class VerifyAppenderLog4j extends AppenderSkeleton implements VerifyAppender {
 
  Class classUnderTest 
  Logger logger
  private Level verifyLevel
  private List<LogMessage> loggingToVerify
  public void close() {}
  public boolean requiresLayout() { return false }
  
  public VerifyAppenderLog4j(Class classUnderTest, Level verifyLevel) {
    this.classUnderTest = classUnderTest
    this.verifyLevel = verifyLevel
    loggingToVerify = Collections.synchronizedList(new ArrayList<VerifyAppenderLog4j.LogMessage>())
    
    logger = Logger.getLogger(classUnderTest)
    logger.setLevel(verifyLevel)
    logger.addAppender(this)
  }
  
  public void demandLog(Object level, String message, Boolean substringMatch=false) {
    if(((Level)level).getSyslogEquivalent() <= verifyLevel.getSyslogEquivalent())
      loggingToVerify.add(new LogMessage(level, message, substringMatch))
  }
  
  public void demandLog(Object level, String message, Exception e, Boolean substringMatch=false) {
    if(((Level)level).getSyslogEquivalent() <= verifyLevel.getSyslogEquivalent())
      loggingToVerify.add(new LogMessage(level, message, e, substringMatch))
  }
  
  public void verify() {
    logger.removeAppender(this)
    if (!loggingToVerify.isEmpty()) {
      throw new AssertionError("Logging demand registered, but not observed at time of verify: " + loggingToVerify.remove(0))
    }
  }
  
  @Override
  protected void append(LoggingEvent event) {
    LogMessage actualMessage = new LogMessage(event)
    if (actualMessage.level.getSyslogEquivalent() <= verifyLevel.getSyslogEquivalent()) {
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
      this(loggingEvent.getLevel(), loggingEvent.getMessage(), 
        (loggingEvent.getThrowableInformation() == null ? null : loggingEvent.getThrowableInformation().getThrowable()), false)
    }
    
    public LogMessage(Level level, String message, Boolean substringMatch=false) {
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
}