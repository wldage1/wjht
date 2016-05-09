package com.sw.plugins.clientcenter.salon.message.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.sw.core.data.entity.BaseEntity;

/**
 * 理财沙龙消息
 */
public class SalonMessage extends BaseEntity {

	private static final long serialVersionUID = -5574199646090395462L;
	@Size(min=2, max=50)
	private String title;
	@Size(min=10, max=400)
	private String meetingContent;
	@NotEmpty
	private String meetingTime;
	@Size(min=2, max=50)
	private String meetingPlace;
	//消息状态		1：启用	2：停用
	@NotNull
	private Long status;
	
	//页面显示属性
	private String starTime;
	private String endTime;
	private String statusViewName;
	
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
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getStarTime() {
		return starTime;
	}
	public void setStarTime(String starTime) {
		this.starTime = starTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStatusViewName() {
		return statusViewName;
	}
	public void setStatusViewName(String statusViewName) {
		this.statusViewName = statusViewName;
	}
	
}
