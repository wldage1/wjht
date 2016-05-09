/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.plugins.clientcenter.member.maintain.entity
 * FileName: Member.java
 * @version 1.0
 * @author baokai
 * @created on 2012-4-27
 * @last Modified 
 * @history
 */
package com.sw.plugins.clientcenter.member.generateaccount.entity;

import com.sw.core.data.entity.BaseEntity;

public class GenerateAccount extends BaseEntity {

	/**
	 * CRM成单生成账号
	 * @author baokai
	 * Created on :2013-11-20 上午9:56:57
	 */
	private static final long serialVersionUID = 2502067380325501723L;
	
	private Long crmId;
	
	private String mobilePhone;
	
	private String userName;
	
	private String passWord;
	
	private String generateTime;
	
	private String startTime;
	
	private String endTime;

	public Long getCrmId() {
		return crmId;
	}

	public void setCrmId(Long crmId) {
		this.crmId = crmId;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getGenerateTime() {
		return generateTime;
	}

	public void setGenerateTime(String generateTime) {
		this.generateTime = generateTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	

}