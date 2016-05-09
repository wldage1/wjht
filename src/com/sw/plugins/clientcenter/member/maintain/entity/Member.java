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
package com.sw.plugins.clientcenter.member.maintain.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sw.core.data.entity.BaseEntity;

public class Member extends BaseEntity {

	private static final long serialVersionUID = -6313626702033162433L;
	private Long crmId;
	private String memberLevel;
	@Size(max = 25)
	@Pattern(regexp = "^$|^[a-zA-Z\\u4e00-\\u9fa5]+$")
	private String memberName;
	@NotNull
	private Long sex;
	private String birthday;
	private int birthdayYear;
	private int birthdayMonth;
	private int birthdayDate;
	private Long idCardType;
	private String idCard;
	private String belief;
	private String nationality;
	@Pattern(regexp = "^((13[0-9])|(15[^4,\\D])|(18[0,0-9]))\\d{8}$")
	private String mobilePhone;
	private String cardType;
	private String xltPhone;
	private String phone;
	private String province;
	private String city;
	private String crmSource;
	private String sourceIndustry;
	private String memberType;
	private int married;
	private Long occupation;
	private String education;
	private String memberPosition;
	private String workStatus;
	private float income;
	private String incomeRangeId;
	private String hobby;
	private String email;
	private String homeAddress;
	private String contactAddress;
	private String postCode;
	private Long ventureTrend;
	private String company;
	private String companyIndustry;
	private int totalUsingCount;
	private String lastUsingTime;
	private String crmDateModifyTime;
	private String financialPlanner;
	private String introductionCustomers;
	private String crmDateCreateTime;
	private String serialNumber;
	private String fpOrganization;
	private String lastFollowTime;
	private String minQuota;
	private String maxQuota;
	private String intentionProduct;
	private String investmentSituation;
	private String crmCreateTime;
	private String crmModifyTime;
	private Long crmCreator;
	private Long extendTableId;
	@Pattern(regexp = "^[0-9a-zA-Z_]{1,50}$")
	private String userName;
	@Pattern(regexp = "^$|^[\\s\\S]{6}")
	private String passWord;
	@Pattern(regexp = "^$|^[\\s\\S]{6}")
	private String confirmPassWord;
	private String pwQuestion;
	@Size(max = 25)
	private String pwResult;
	@NotNull
	private Long level;
	@NotNull
	private Long status;
	private Integer delStatus;
	private Long source;
	private String registrationTime;
	private Long terminal;
	private String registrationIP;
	private String lastLoginTime;
	private String authenticationCodes;
	private String registPhone;
	private String sqlCondition;
	private Integer isNewClient;// 是否是CRM会员1：是0：不是
	private String branchOrg;

	// 页面用到的变量
	private String modifyPassWordFlag;
	private String complexSearchFlag;
	private String selectValues;
	private String startTime;// 新用户统计，注册开始时间
	private String endTime;// 新用户统计，注册截止时间
	private String resource;// 新用户统计，数据来源
	/* 消息ID */
	private Long msgId;
	/* 按条件检索用户IDS */
	private String idsString;

	public Long getCrmId() {
		return crmId;
	}

	public void setCrmId(Long crmId) {
		this.crmId = crmId;
	}

	public String getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getBirthdayYear() {
		return birthdayYear;
	}

	public void setBirthdayYear(int birthdayYear) {
		this.birthdayYear = birthdayYear;
	}

	public int getBirthdayMonth() {
		return birthdayMonth;
	}

	public void setBirthdayMonth(int birthdayMonth) {
		this.birthdayMonth = birthdayMonth;
	}

	public int getBirthdayDate() {
		return birthdayDate;
	}

	public void setBirthdayDate(int birthdayDate) {
		this.birthdayDate = birthdayDate;
	}

	public Long getIdCardType() {
		return idCardType;
	}

	public void setIdCardType(Long idCardType) {
		this.idCardType = idCardType;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getBelief() {
		return belief;
	}

	public void setBelief(String belief) {
		this.belief = belief;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getXltPhone() {
		return xltPhone;
	}

	public void setXltPhone(String xltPhone) {
		this.xltPhone = xltPhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCrmSource() {
		return crmSource;
	}

	public void setCrmSource(String crmSource) {
		this.crmSource = crmSource;
	}

	public String getSourceIndustry() {
		return sourceIndustry;
	}

	public void setSourceIndustry(String sourceIndustry) {
		this.sourceIndustry = sourceIndustry;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public int getMarried() {
		return married;
	}

	public void setMarried(int married) {
		this.married = married;
	}

	public Long getOccupation() {
		return occupation;
	}

	public void setOccupation(Long occupation) {
		this.occupation = occupation;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getMemberPosition() {
		return memberPosition;
	}

	public void setMemberPosition(String memberPosition) {
		this.memberPosition = memberPosition;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public float getIncome() {
		return income;
	}

	public void setIncome(float income) {
		this.income = income;
	}

	public String getIncomeRangeId() {
		return incomeRangeId;
	}

	public void setIncomeRangeId(String incomeRangeId) {
		this.incomeRangeId = incomeRangeId;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Long getVentureTrend() {
		return ventureTrend;
	}

	public void setVentureTrend(Long ventureTrend) {
		this.ventureTrend = ventureTrend;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanyIndustry() {
		return companyIndustry;
	}

	public void setCompanyIndustry(String companyIndustry) {
		this.companyIndustry = companyIndustry;
	}

	public int getTotalUsingCount() {
		return totalUsingCount;
	}

	public void setTotalUsingCount(int totalUsingCount) {
		this.totalUsingCount = totalUsingCount;
	}

	public String getLastUsingTime() {
		return lastUsingTime;
	}

	public void setLastUsingTime(String lastUsingTime) {
		this.lastUsingTime = lastUsingTime;
	}

	public String getCrmDateModifyTime() {
		return crmDateModifyTime;
	}

	public void setCrmDateModifyTime(String crmDateModifyTime) {
		this.crmDateModifyTime = crmDateModifyTime;
	}

	public String getFinancialPlanner() {
		return financialPlanner;
	}

	public void setFinancialPlanner(String financialPlanner) {
		this.financialPlanner = financialPlanner;
	}

	public String getIntroductionCustomers() {
		return introductionCustomers;
	}

	public void setIntroductionCustomers(String introductionCustomers) {
		this.introductionCustomers = introductionCustomers;
	}

	public String getCrmDateCreateTime() {
		return crmDateCreateTime;
	}

	public void setCrmDateCreateTime(String crmDateCreateTime) {
		this.crmDateCreateTime = crmDateCreateTime;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getFpOrganization() {
		return fpOrganization;
	}

	public void setFpOrganization(String fpOrganization) {
		this.fpOrganization = fpOrganization;
	}

	public String getLastFollowTime() {
		return lastFollowTime;
	}

	public void setLastFollowTime(String lastFollowTime) {
		this.lastFollowTime = lastFollowTime;
	}

	public String getMinQuota() {
		return minQuota;
	}

	public void setMinQuota(String minQuota) {
		this.minQuota = minQuota;
	}

	public String getMaxQuota() {
		return maxQuota;
	}

	public void setMaxQuota(String maxQuota) {
		this.maxQuota = maxQuota;
	}

	public String getIntentionProduct() {
		return intentionProduct;
	}

	public void setIntentionProduct(String intentionProduct) {
		this.intentionProduct = intentionProduct;
	}

	public String getInvestmentSituation() {
		return investmentSituation;
	}

	public void setInvestmentSituation(String investmentSituation) {
		this.investmentSituation = investmentSituation;
	}

	public String getCrmCreateTime() {
		return crmCreateTime;
	}

	public void setCrmCreateTime(String crmCreateTime) {
		this.crmCreateTime = crmCreateTime;
	}

	public String getCrmModifyTime() {
		return crmModifyTime;
	}

	public void setCrmModifyTime(String crmModifyTime) {
		this.crmModifyTime = crmModifyTime;
	}

	public Long getCrmCreator() {
		return crmCreator;
	}

	public void setCrmCreator(Long crmCreator) {
		this.crmCreator = crmCreator;
	}

	public Long getExtendTableId() {
		return extendTableId;
	}

	public void setExtendTableId(Long extendTableId) {
		this.extendTableId = extendTableId;
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

	public String getPwQuestion() {
		return pwQuestion;
	}

	public void setPwQuestion(String pwQuestion) {
		this.pwQuestion = pwQuestion;
	}

	public String getPwResult() {
		return pwResult;
	}

	public void setPwResult(String pwResult) {
		this.pwResult = pwResult;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getSource() {
		return source;
	}

	public void setSource(Long source) {
		this.source = source;
	}

	public String getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(String registrationTime) {
		this.registrationTime = registrationTime;
	}

	public Long getTerminal() {
		return terminal;
	}

	public void setTerminal(Long terminal) {
		this.terminal = terminal;
	}

	public String getRegistrationIP() {
		return registrationIP;
	}

	public void setRegistrationIP(String registrationIP) {
		this.registrationIP = registrationIP;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getAuthenticationCodes() {
		return authenticationCodes;
	}

	public void setAuthenticationCodes(String authenticationCodes) {
		this.authenticationCodes = authenticationCodes;
	}

	public String getConfirmPassWord() {
		return confirmPassWord;
	}

	public void setConfirmPassWord(String confirmPassWord) {
		this.confirmPassWord = confirmPassWord;
	}

	public Integer getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}

	public String getModifyPassWordFlag() {
		return modifyPassWordFlag;
	}

	public void setModifyPassWordFlag(String modifyPassWordFlag) {
		this.modifyPassWordFlag = modifyPassWordFlag;
	}

	public String getSqlCondition() {
		return sqlCondition;
	}

	public void setSqlCondition(String sqlCondition) {
		this.sqlCondition = sqlCondition;
	}

	public String getComplexSearchFlag() {
		return complexSearchFlag;
	}

	public void setComplexSearchFlag(String complexSearchFlag) {
		this.complexSearchFlag = complexSearchFlag;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public String getIdsString() {
		return idsString;
	}

	public void setIdsString(String idsString) {
		this.idsString = idsString;
	}

	public String getSelectValues() {
		return selectValues;
	}

	public void setSelectValues(String selectValues) {
		this.selectValues = selectValues;
	}

	public String getRegistPhone() {
		return registPhone;
	}

	public void setRegistPhone(String registPhone) {
		this.registPhone = registPhone;
	}

	public Integer getIsNewClient() {
		return isNewClient;
	}

	public void setIsNewClient(Integer isNewClient) {
		this.isNewClient = isNewClient;
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

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getBranchOrg() {
		return branchOrg;
	}

	public void setBranchOrg(String branchOrg) {
		this.branchOrg = branchOrg;
	}

	//
}