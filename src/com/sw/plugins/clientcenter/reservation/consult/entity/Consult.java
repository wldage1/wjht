package com.sw.plugins.clientcenter.reservation.consult.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sw.core.data.entity.BaseEntity;

/**
 * 预约咨询表
 */
public class Consult extends BaseEntity {
	
	private static final long serialVersionUID = -2430085147380238107L;
	@Pattern(regexp="^[a-zA-Z]|[[a-zA-Z](\\s){1}[a-zA-Z]]{1,20}|[\u4e00-\u9fa5]{2,4}")
	private String name;
	@NotNull
	private Long gender;
	@Pattern(regexp="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$")
	private String mobilePhone;
	private Integer state;
	@Size(max=100)
	private String content;
	
	//页面显示属性
	private String starTime;
	private String endTime;
	private String genderName;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getGender() {
		return gender;
	}
	public void setGender(Long gender) {
		this.gender = gender;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getGenderName() {
		return genderName;
	}
	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}
	
}
