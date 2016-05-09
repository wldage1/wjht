package com.sw.plugins.clientcenter.salon.feedback.entity;

import com.sw.core.data.entity.BaseEntity;

/**
 * 理财沙龙反馈信息
 */
public class SalonFeedback extends BaseEntity {

	private static final long serialVersionUID = -8059890268571037739L;
	private Long salonMessageId;
	private Long memberId;
	//是否参加		0：参加	1：不参加
	private Integer isJoin;
	private Integer peopleNumber;
	private String peopleMobilePhone;
	//删除状态		0：正常	1：假删除
	private Integer status;
	
	//页面显示属性
	private String isJoinView;
	private String title;
	private String meetingContent;
	private String meetingTime;
	private String meetingPlace;
	
	public Long getSalonMessageId() {
		return salonMessageId;
	}
	public void setSalonMessageId(Long salonMessageId) {
		this.salonMessageId = salonMessageId;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Integer getIsJoin() {
		return isJoin;
	}
	public void setIsJoin(Integer isJoin) {
		this.isJoin = isJoin;
	}
	public Integer getPeopleNumber() {
		return peopleNumber;
	}
	public void setPeopleNumber(Integer peopleNumber) {
		this.peopleNumber = peopleNumber;
	}
	public String getPeopleMobilePhone() {
		return peopleMobilePhone;
	}
	public void setPeopleMobilePhone(String peopleMobilePhone) {
		this.peopleMobilePhone = peopleMobilePhone;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMeetingContent() {
		return meetingContent;
	}
	public void setMeetingContent(String meetingContent) {
		this.meetingContent = meetingContent;
	}
	public String getMeetingTime() {
		return meetingTime;
	}
	public void setMeetingTime(String meetingTime) {
		this.meetingTime = meetingTime;
	}
	public String getMeetingPlace() {
		return meetingPlace;
	}
	public void setMeetingPlace(String meetingPlace) {
		this.meetingPlace = meetingPlace;
	}
	public String getIsJoinView() {
		return isJoinView;
	}
	public void setIsJoinView(String isJoinView) {
		this.isJoinView = isJoinView;
	}
	
}
