package com.tjhruska.mc.util.tagReplacement

import java.util.Map
import org.springframework.beans.factory.BeanNameAware


interface Template extends BeanNameAware {
  String getTemplate()
  void setTemplate(String template)
  Boolean getKeepUnusedTags()
  void setKeepUnusedTags(Boolean keepUnusedTags)
  Integer getMaxIterations()
  void setMaxIterations(Integer maxIterations)
  StringBuilder applyTags(Map<String, String> tags)
}