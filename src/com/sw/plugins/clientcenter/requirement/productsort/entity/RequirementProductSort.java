package com.sw.plugins.clientcenter.requirement.productsort.entity;

import javax.validation.constraints.Size;

import com.sw.core.data.entity.BaseEntity;

/**
 * 投资需求产品类型表
 */
public class RequirementProductSort extends BaseEntity {

	private static final long serialVersionUID = -7591513946993919498L;
	
	@Size(min=2,max=20)
	private String name;
	@Size(max=30)
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
