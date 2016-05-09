package com.sw.core.data.entity;

import java.util.Date;

import com.sw.core.data.entity.BaseEntity;

public class BaseLog extends BaseEntity {
	private static final long serialVersionUID = 3528409663709225035L;
	/**用户id*/
    private Long userId;	
    /**用户账号*/
    private String userAccount;
    /**用户名称*/
    private String userName;
    /**操作时间*/
    private String optTime;
    /**操作内容*/
    private String content;
    /**访问ip*/
    private String accessIp;


    public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOptTime() {
		return optTime;
	}
	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAccessIp() {
		return accessIp;
	}
	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

    
    
}