package com.sw.plugins.clientcenter.client.maintain.entity;

import com.sw.core.data.entity.BaseEntity;

public class Consume extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	public String clientId;
	public String money;
	public String description;
	public String permitTime;
	public String status;
	public String userName;
	public String total;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPermitTime() {
		return permitTime;
	}
	public void setPermitTime(String permitTime) {
		this.permitTime = permitTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
}