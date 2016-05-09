package com.sw.plugins.usercenter.socialization.shareplugin.entity;

import com.sw.core.data.entity.BaseEntity;

public class Plugintype extends BaseEntity {

	private static final long serialVersionUID = -5645914212917376676L;

	private Long pluginId;// 插件ID
	private Integer type; // 终端类型 1:iphone 2:ipad 3:android

	public Long getPluginId() {
		return pluginId;
	}

	public void setPluginId(Long pluginId) {
		this.pluginId = pluginId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
