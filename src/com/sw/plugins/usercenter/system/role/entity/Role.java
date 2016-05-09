package com.sw.plugins.usercenter.system.role.entity;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.sw.core.data.entity.Authorization;
import com.sw.core.data.entity.BaseEntity;
import com.sw.plugins.usercenter.system.organization.entity.Organization;

public class Role extends BaseEntity{
	private static final long serialVersionUID = -8552065215066165229L;
	/**角色名称*/
	@Size(min = 1, max = 20)
	@NotEmpty
	@Pattern(regexp="^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$")
    private String name;
	/**角色类型 0：超级管理员角色 1：普通角色*/
	private Integer type = 1;
    /**角色描述*/
    private String description;
    /**权限字符串*/
    private String auth;
    /**机构字符串**/
    private String org;
    /**角色绑定的权限信息*/
    private List<Authorization> authorizations;
    /**角色资源的机构信息*/
    private List<Organization> organizations;
    private String  orgId;
    private String  orgName;
    
    public Role(){}
    
	
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

	public List<Authorization> getAuthorizations() {
		return authorizations;
	}
	public void setAuthorizations(List<Authorization> authorizations) {
		this.authorizations = authorizations;
	}
	
	public List<Organization> getOrganizations() {
		return organizations;
	}
	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
}