package com.sw.plugins.usercenter.system.role.entity;



import com.sw.core.data.entity.BaseEntity;

public class RoleOrgMapper extends BaseEntity{
	private static final long serialVersionUID = -2746579102459151682L;
	private Long roleId;
    private Long orgId;
    
    public RoleOrgMapper(){}
    
    public RoleOrgMapper(Long roleId,Long orgId){
    	this.roleId = roleId;
    	this.orgId = orgId;
    }

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
    
}