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