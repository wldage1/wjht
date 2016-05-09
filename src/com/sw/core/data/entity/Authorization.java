package com.sw.core.data.entity;

import java.util.List;


public class Authorization extends BaseEntity{
	
	private static final long serialVersionUID = -8667400765027701089L;
	private String code;
	private String index;
	private String relation;
	private String parentCode;
	private String level;
	private String name;
	private String controller;
	private String dataSource;
	/**是否记录日志*/
	private String isLog;
	/**是否设置导航*/
	private String isNav;
	/**是否设置子权限列表*/
	private String isSubAuth;
	/**是否记录当前状态*/
	private String isStatus;
	//查询用到的变量
	private List<String> codes;
	public String getIsStatus() {
		return isStatus;
	}

	public void setIsStatus(String isStatus) {
		this.isStatus = isStatus;
	}

	/**前置图标*/
	private String icon;

	public Authorization() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getIsLog() {
		return isLog;
	}

	public void setIsLog(String log) {
		this.isLog = log;
	}

	public String getIsNav() {
		return isNav;
	}

	public void setIsNav(String isNav) {
		this.isNav = isNav;
	}

	public String getIsSubAuth() {
		return isSubAuth;
	}

	public void setIsSubAuth(String isSubAuth) {
		this.isSubAuth = isSubAuth;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public List<String> getCodes() {
		return codes;
	}

	public void setCodes(List<String> codes) {
		this.codes = codes;
	}
	
	
}
