package com.sw.plugins.usercenter.system.role.entity;

import com.sw.core.data.entity.BaseEntity;

public class RoleAuthMapper extends BaseEntity{
	
	private static final long serialVersionUID = -4355429053598922587L;
	private Long roleId ;
    private String authCode;
    
    public RoleAuthMapper(){}
    
    public RoleAuthMapper(Long roleId,String authCode){
    	this.roleId = roleId;
    	this.authCode = authCode;
    }

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
}