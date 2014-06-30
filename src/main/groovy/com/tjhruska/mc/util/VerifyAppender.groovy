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

interface VerifyAppender {
  abstract void demandLog(Object level, String message, Boolean substringMatch)
  abstract void demandLog(Object level, String message, Exception e, Boolean substringMatch)
  abstract void verify()
}