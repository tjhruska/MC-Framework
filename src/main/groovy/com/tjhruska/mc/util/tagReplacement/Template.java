package com.tjhruska.mc.util.tagReplacement;

import java.util.Map;

import org.springframework.beans.factory.BeanNameAware;


public class Template implements BeanNameAware{
	private String name;
	private String template;
	private Boolean keepUnusedTags;
	private Integer maxIterations;
	
	public Template(){
		this.keepUnusedTags = false;
		this.maxIterations = 10;
	}
	
	public Template(String template){
		this.template = template;
		this.keepUnusedTags = false;
		this.maxIterations = 10;
	}
	
	public Template(String template, Boolean keepUnusedTags, Integer maxIterations){
		this.template = template;
		this.keepUnusedTags = keepUnusedTags;
		this.maxIterations = maxIterations;
	}
	
	public String getTemplate(){
		return template;
	}

	public void setTemplate(String template){
		if (this.template != null)
			throw new RuntimeException("Illegal attempt to alter template");
		this.template = template;
	}
	
	public Boolean getKeepUnusedTags() {
		return keepUnusedTags;
	}

	public void setKeepUnusedTags(Boolean keepUnusedTags) {
		this.keepUnusedTags = keepUnusedTags;
	}

	public Integer getMaxIterations() {
		return maxIterations;
	}

	public void setMaxIterations(Integer maxIterations) {
		this.maxIterations = maxIterations;
	}

	public StringBuilder applyTags(Map<String,String> tags){
		return TemplateReplacer.replaceTemplates(template.toCharArray(), tags, keepUnusedTags, maxIterations);
	}

	public void setBeanName(String name) {
		this.name = name;
	}
	
	public String getBeanName(){
		return this.name;
	}
}
