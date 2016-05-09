package com.sw.plugins.usercenter.config.dynamic.entity;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sw.core.data.entity.BaseEntity;

/**
 * 字典表
 */
public class Dictionary extends BaseEntity {
	private static final long serialVersionUID = -5645914212917376676L;
	@Pattern(regexp="^[\u4e00-\u9fa5]{1,20}")
	private String name;
	@Pattern(regexp="^\\d{1,3}$")
	private String value;
	private String dictSortValue;
	@Size(max=100)
	private String description;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDictSortValue() {
		return dictSortValue;
	}
	public void setDictSortValue(String dictSortValue) {
		this.dictSortValue = dictSortValue;
	}
}
