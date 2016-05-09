/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.plugins.survey.questionnaire.maintain.entity
 * FileName: Questionnaire.java
 * @version 1.0
 * @author zhaofeng
 * @created on 2012-5-16
 * @last Modified 
 * @history
 */
package com.sw.plugins.survey.questionnaire.maintain.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.sw.core.data.entity.BaseEntity;

/**
 *  调查问卷实体
 *  @author zhaofeng
 *  @version 1.0
 *  </pre>
 *  Created on :下午12:06:13
 *  LastModified:
 *  History:
 *  </pre>
 */

public class Questionnaire extends BaseEntity{
	
	private static final long serialVersionUID = 333362415727985268L;
	/**
	 * 问卷标识名称
	 */
	@Size(min = 1, max = 100)
	private String queName;
	/**
	 * 问卷标题
	 */
	@Size(min = 1, max = 100)
	private String queTitle;
	/**
	 * 问卷副标题
	 */
	@Size(min = 0, max = 100)
	private String queSubTitle;
	/**
	 * 问卷描述
	 */
	@Size(min = 0, max = 2000)
	private String queDescribe;
	/**
	 * 问卷开始时间
	 */
	private String queStartTime;
	/**
	 * 问卷结束时间
	 */
	private String queEndTime;
	/**
	 * 问卷发布状态,0未发布,1发布,发布的问卷不可以修改
	 */
	private Long openFlag;
	
	/**
	 * 问卷消息链接
	 */
	private String MsgURL;
	/**
	 * 问卷回复数
	 */
	private Long responseNum;
	
	/**
	 * 自定义SQL
	 */
	private String customSQL;
	
	
	
	/**
	 * 会员Id
	 */
	private Long userID;
	/**
	 * 会员姓名
	 */
	private String memberName;
	/**
	 * 会员性别
	 */
	private Long sex;
	/**
	 * 移动电话
	 */
	private String mobile;
    /**
     * 城市
     */
    private String city;
    /**
     * 电子邮箱
     */
    private String email;
    
    /**
     * 问卷调查图片（ipad）
     */
    private String queImgIpad;
    
    /**
     * 问卷调查图片（iphone）
     */
    private String queImgIphone;
    
    /**
     * 问卷调查图片的类型
     */
    private String queImgType;
	
	
	public String getQueImgType() {
		return queImgType;
	}
	public void setQueImgType(String queImgType) {
		this.queImgType = queImgType;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public Long getSex() {
		return sex;
	}
	public void setSex(Long sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQueName() {
		return queName;
	}
	public void setQueName(String queName) {
		this.queName = queName;
	}
	public String getQueTitle() {
		return queTitle;
	}
	public void setQueTitle(String queTitle) {
		this.queTitle = queTitle;
	}
	public String getQueSubTitle() {
		return queSubTitle;
	}
	public void setQueSubTitle(String queSubTitle) {
		this.queSubTitle = queSubTitle;
	}
	public String getQueDescribe() {
		return queDescribe;
	}
	public void setQueDescribe(String queDescribe) {
		this.queDescribe = queDescribe;
	}
	public String getQueStartTime() {
		return queStartTime;
	}
	public void setQueStartTime(String queStartTime) {
		this.queStartTime = queStartTime;
	}
	public String getQueEndTime() {
		return queEndTime;
	}
	public void setQueEndTime(String queEndTime) {
		this.queEndTime = queEndTime;
	}
	public Long getOpenFlag() {
		return openFlag;
	}
	public void setOpenFlag(Long openFlag) {
		this.openFlag = openFlag;
	}
	public String getMsgURL() {
		return MsgURL;
	}
	public void setMsgURL(String msgURL) {
		MsgURL = msgURL;
	}
	public String getCustomSQL() {
		return customSQL;
	}
	public void setCustomSQL(String customSQL) {
		this.customSQL = customSQL;
	}
	public Long getResponseNum() {
		return responseNum;
	}
	public void setResponseNum(Long responseNum) {
		this.responseNum = responseNum;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public String getQueImgIpad() {
		return queImgIpad;
	}
	public void setQueImgIpad(String queImgIpad) {
		this.queImgIpad = queImgIpad;
	}
	public String getQueImgIphone() {
		return queImgIphone;
	}
	public void setQueImgIphone(String queImgIphone) {
		this.queImgIphone = queImgIphone;
	}
	
	

}
