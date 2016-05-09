package com.sw.plugins.usercenter.socialization.shareplugin.entity;

import org.hibernate.validator.constraints.NotEmpty;

import com.sw.core.data.entity.BaseEntity;

public class Shareplugin extends BaseEntity {

	private static final long serialVersionUID = -5645914212917376676L;

	@NotEmpty
	private String name; // 平台名称
	@NotEmpty
	private String authoriKey; // 平台KEY
	private Integer[] statuses;
	private Integer status; // 启用状态 0:不启用1：启用
	private Integer sortNumber; // 排序
	
	
	private Integer[] types;// 终端类型 1:iphone 2:ipad 3:android

	public Shareplugin() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthoriKey() {
		return authoriKey;
	}

	public void setAuthoriKey(String authoriKey) {
		this.authoriKey = authoriKey;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}

	public Integer[] getTypes() {
		return types;
	}

	public void setTypes(Integer[] types) {
		this.types = types;
	}

	public Integer[] getStatuses() {
		return statuses;
	}

	public void setStatuses(Integer[] statuses) {
		this.statuses = statuses;
	}

}
