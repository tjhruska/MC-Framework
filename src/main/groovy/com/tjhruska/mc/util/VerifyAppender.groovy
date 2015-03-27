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