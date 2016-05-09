/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.plugins.clientcenter.member.maintain.entity
 * FileName: TerminalCode.java
 * @version 1.0
 * @author haoyuanfu
 * @created on 2012-6-29
 * @last Modified 
 * @history
 */
package com.sw.plugins.clientcenter.member.maintain.entity;

import com.sw.core.data.entity.BaseEntity;

public class TerminalCode extends BaseEntity {
	/**会员ID*/
	private Long memberId;
	/**机器码*/
	private String code;
	/**授权码*/
	private String iAuthCode;
	
	/**用户IDS*/
	private String idsString;
	
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getiAuthCode() {
		return iAuthCode;
	}
	public void setiAuthCode(String iAuthCode) {
		this.iAuthCode = iAuthCode;
	}
	public String getIdsString() {
		return idsString;
	}
	public void setIdsString(String idsString) {
		this.idsString = idsString;
	}

}