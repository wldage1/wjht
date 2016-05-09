package com.sw.plugins.informationcenter.message.maintain.entity;

import org.hibernate.validator.constraints.NotEmpty;

import com.sw.core.data.entity.BaseEntity;

/**
 *  消息发送记录实体类
 *  @author haoyuanfu
 *  @version 1.0
 *  </pre>
 *  Created on :上午09:19:41
 *  LastModified:
 *  History:
 *  </pre>
 */
public class SendRecord extends BaseEntity{
	
	private static final long serialVersionUID = -4388668477614168933L;
	/**标识ID */	
	private Long sysId;
	/**消息ID */
	private  Long msgId;
	/**用户ID */
	private Long userId;
	/**发送日期 */
	private String sendDate;
	/**操作人 */
	private Long Oper;
	
	
	
	/**消息标题 */
	private String msgTitle;
	/**推送时间 */
	private String msgPushDate;
	/**消息来源  0=系统填入，1=理财沙龙，2=研究报告，3=行业资讯，4=调查问卷 */
	private int msgFrom;
	/**发布日期 */
	private String  published;
	/**发布日期开始时间 */
	private String startTime;
	/**发布日期结束时间 */
	private String endTime;

	
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
	
	
	public Long getSysId() {
		return sysId;
	}
	public void setSysId(Long sysId) {
		this.sysId = sysId;
	}
	public Long getMsgId() {
		return msgId;
	}
	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public Long getOper() {
		return Oper;
	}
	public void setOper(Long oper) {
		Oper = oper;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	public String getMsgPushDate() {
		return msgPushDate;
	}
	public void setMsgPushDate(String msgPushDate) {
		this.msgPushDate = msgPushDate;
	}
	public int getMsgFrom() {
		return msgFrom;
	}
	public void setMsgFrom(int msgFrom) {
		this.msgFrom = msgFrom;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPublished() {
		return published;
	}
	public void setPublished(String published) {
		this.published = published;
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