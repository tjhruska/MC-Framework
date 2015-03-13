package com.tjhruska.mc.util.tagReplacement

import java.util.Map

class TemplateImpl implements Template {
  String beanName
  String template
  Boolean keepUnusedTags
  Integer maxIterations

  TemplateImpl() {
    this.keepUnusedTags = false
    this.maxIterations = 10
  }

  TemplateImpl(String template) {
    this.template = template
    this.keepUnusedTags = false
    this.maxIterations = 10
  }

  TemplateImpl(String template, Boolean keepUnusedTags, Integer maxIterations) {
    this.template = template
    this.keepUnusedTags = keepUnusedTags
    this.maxIterations = maxIterations
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.tjhruska.mc.util.tagReplacement.Template#setTemplate(java.lang.String)
   */
  @Override
  void setTemplate(String template) {
    if (this.template != null)
      throw new RuntimeException("Illegal attempt to alter template")
    this.template = template
  }

  /*
   * (non-Javadoc)
   *
   * @see com.tjhruska.mc.util.tagReplacement.Template#applyTags(java.util.Map)
   */
  @Override
  StringBuilder applyTags(Map<String, String> tags) {
    return TemplateReplacer.replaceTemplates(template.toCharArray(), tags, keepUnusedTags, maxIterations)
  }
}