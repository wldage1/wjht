/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.plugins.usercenter.system.user.entity
 * FileName: UserOrgMapper.java
 * @version 1.0
 * @author 用户机构关系表实体
 * @created on 2012-6-12
 * @last Modified 
 * @history
 */
package com.sw.plugins.usercenter.system.user.entity;

import com.sw.core.data.entity.BaseEntity;

/**
 *  用户机构关系实体
 *  @author zhaofeng
 *  @version 1.0
 *  </pre>
 *  Created on :下午3:53:21
 *  LastModified:
 *  History:
 *  </pre>
 */
public class UserOrgMapper extends BaseEntity{
	
	/**
	 * 用户ID
	 */
	private Long userID;
	/**
	 * 组织ID
	 */
	private Long orgID;
	
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Long getOrgID() {
		return orgID;
	}
	public void setOrgID(Long orgID) {
		this.orgID = orgID;
	}


}
