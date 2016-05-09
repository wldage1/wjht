/**
 * CrmMember.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sw.thirdparty.crm.webservice;

public class CrmMember  extends com.sw.thirdparty.crm.webservice.BaseEntity  implements java.io.Serializable {
    private java.lang.String authenticationCode;

    private java.lang.String belief;

    private java.util.Calendar birthday;

    private java.lang.Integer birthdayDate;

    private java.lang.Integer birthdayMonth;

    private java.lang.Integer birthdayYear;

    private int branchID;

    private java.lang.String branchName;

    private java.lang.String cardType;

    private java.lang.String city;

    private java.lang.String[] clientIDList;

    private java.lang.String company;

    private java.lang.String companyIndustry;

    private java.lang.String contactAddress;

    private java.lang.Long crmID;

    private java.lang.String crmSource;

    private java.lang.String education;

    private java.lang.String email;

    private java.lang.String fpMobilePhone;

    private java.lang.String hobby;

    private java.lang.String homeAddress;

    private java.lang.String idCard;

    private java.lang.String idCardType;

    private java.lang.String importance;

    private java.lang.Float income;

    private java.lang.String incomeRangeId;

    private java.util.Calendar lastUsingTime;

    private java.lang.Integer married;

    private java.lang.String memberName;

    private java.lang.String memberPosition;

    private java.lang.String memberType;

    private java.lang.String mobilePhone;

    private java.lang.String[] mobilePhoneList;

    private java.lang.String mobilePhoneTwo;

    private java.util.Calendar modifyTime;

    private java.lang.String nationality;

    private java.lang.String occupation;

    private java.lang.String pad1;

    private java.lang.String pad10;

    private java.lang.String pad2;

    private java.lang.String pad3;

    private java.lang.String pad4;

    private java.lang.String pad5;

    private java.lang.String pad6;

    private java.lang.String pad7;

    private java.lang.String pad8;

    private java.lang.String pad9;

    private int parentBranchID;

    private java.lang.String phone;

    private java.lang.String postCode;

    private java.lang.String province;

    private com.sw.thirdparty.crm.webservice.ReturnData returnData;

    private java.lang.String sex;

    private java.lang.String sourceIndustry;

    private java.lang.Integer totalUsingCount;

    private java.lang.String tradeEndTime;

    private java.lang.String tradeStartTime;

    private java.lang.String userName;

    private java.lang.String ventureTrend;

    private java.lang.String workStatus;

    private java.lang.String xltPhone;

    public CrmMember() {
    }

    public CrmMember(
           java.lang.Long generatedKey,
           java.lang.Long id,
           java.lang.String[] ids,
           int limitRows,
           int page,
           int rows,
           java.lang.String authenticationCode,
           java.lang.String belief,
           java.util.Calendar birthday,
           java.lang.Integer birthdayDate,
           java.lang.Integer birthdayMonth,
           java.lang.Integer birthdayYear,
           int branchID,
           java.lang.String branchName,
           java.lang.String cardType,
           java.lang.String city,
           java.lang.String[] clientIDList,
           java.lang.String company,
           java.lang.String companyIndustry,
           java.lang.String contactAddress,
           java.lang.Long crmID,
           java.lang.String crmSource,
           java.lang.String education,
           java.lang.String email,
           java.lang.String fpMobilePhone,
           java.lang.String hobby,
           java.lang.String homeAddress,
           java.lang.String idCard,
           java.lang.String idCardType,
           java.lang.String importance,
           java.lang.Float income,
           java.lang.String incomeRangeId,
           java.util.Calendar lastUsingTime,
           java.lang.Integer married,
           java.lang.String memberName,
           java.lang.String memberPosition,
           java.lang.String memberType,
           java.lang.String mobilePhone,
           java.lang.String[] mobilePhoneList,
           java.lang.String mobilePhoneTwo,
           java.util.Calendar modifyTime,
           java.lang.String nationality,
           java.lang.String occupation,
           java.lang.String pad1,
           java.lang.String pad10,
           java.lang.String pad2,
           java.lang.String pad3,
           java.lang.String pad4,
           java.lang.String pad5,
           java.lang.String pad6,
           java.lang.String pad7,
           java.lang.String pad8,
           java.lang.String pad9,
           int parentBranchID,
           java.lang.String phone,
           java.lang.String postCode,
           java.lang.String province,
           com.sw.thirdparty.crm.webservice.ReturnData returnData,
           java.lang.String sex,
           java.lang.String sourceIndustry,
           java.lang.Integer totalUsingCount,
           java.lang.String tradeEndTime,
           java.lang.String tradeStartTime,
           java.lang.String userName,
           java.lang.String ventureTrend,
           java.lang.String workStatus,
           java.lang.String xltPhone) {
        super(
            generatedKey,
            id,
            ids,
            limitRows,
            page,
            rows);
        this.authenticationCode = authenticationCode;
        this.belief = belief;
        this.birthday = birthday;
        this.birthdayDate = birthdayDate;
        this.birthdayMonth = birthdayMonth;
        this.birthdayYear = birthdayYear;
        this.branchID = branchID;
        this.branchName = branchName;
        this.cardType = cardType;
        this.city = city;
        this.clientIDList = clientIDList;
        this.company = company;
        this.companyIndustry = companyIndustry;
        this.contactAddress = contactAddress;
        this.crmID = crmID;
        this.crmSource = crmSource;
        this.education = education;
        this.email = email;
        this.fpMobilePhone = fpMobilePhone;
        this.hobby = hobby;
        this.homeAddress = homeAddress;
        this.idCard = idCard;
        this.idCardType = idCardType;
        this.importance = importance;
        this.income = income;
        this.incomeRangeId = incomeRangeId;
        this.lastUsingTime = lastUsingTime;
        this.married = married;
        this.memberName = memberName;
        this.memberPosition = memberPosition;
        this.memberType = memberType;
        this.mobilePhone = mobilePhone;
        this.mobilePhoneList = mobilePhoneList;
        this.mobilePhoneTwo = mobilePhoneTwo;
        this.modifyTime = modifyTime;
        this.nationality = nationality;
        this.occupation = occupation;
        this.pad1 = pad1;
        this.pad10 = pad10;
        this.pad2 = pad2;
        this.pad3 = pad3;
        this.pad4 = pad4;
        this.pad5 = pad5;
        this.pad6 = pad6;
        this.pad7 = pad7;
        this.pad8 = pad8;
        this.pad9 = pad9;
        this.parentBranchID = parentBranchID;
        this.phone = phone;
        this.postCode = postCode;
        this.province = province;
        this.returnData = returnData;
        this.sex = sex;
        this.sourceIndustry = sourceIndustry;
        this.totalUsingCount = totalUsingCount;
        this.tradeEndTime = tradeEndTime;
        this.tradeStartTime = tradeStartTime;
        this.userName = userName;
        this.ventureTrend = ventureTrend;
        this.workStatus = workStatus;
        this.xltPhone = xltPhone;
    }


    /**
     * Gets the authenticationCode value for this CrmMember.
     * 
     * @return authenticationCode
     */
    public java.lang.String getAuthenticationCode() {
        return authenticationCode;
    }


    /**
     * Sets the authenticationCode value for this CrmMember.
     * 
     * @param authenticationCode
     */
    public void setAuthenticationCode(java.lang.String authenticationCode) {
        this.authenticationCode = authenticationCode;
    }


    /**
     * Gets the belief value for this CrmMember.
     * 
     * @return belief
     */
    public java.lang.String getBelief() {
        return belief;
    }


    /**
     * Sets the belief value for this CrmMember.
     * 
     * @param belief
     */
    public void setBelief(java.lang.String belief) {
        this.belief = belief;
    }


    /**
     * Gets the birthday value for this CrmMember.
     * 
     * @return birthday
     */
    public java.util.Calendar getBirthday() {
        return birthday;
    }


    /**
     * Sets the birthday value for this CrmMember.
     * 
     * @param birthday
     */
    public void setBirthday(java.util.Calendar birthday) {
        this.birthday = birthday;
    }


    /**
     * Gets the birthdayDate value for this CrmMember.
     * 
     * @return birthdayDate
     */
    public java.lang.Integer getBirthdayDate() {
        return birthdayDate;
    }


    /**
     * Sets the birthdayDate value for this CrmMember.
     * 
     * @param birthdayDate
     */
    public void setBirthdayDate(java.lang.Integer birthdayDate) {
        this.birthdayDate = birthdayDate;
    }


    /**
     * Gets the birthdayMonth value for this CrmMember.
     * 
     * @return birthdayMonth
     */
    public java.lang.Integer getBirthdayMonth() {
        return birthdayMonth;
    }


    /**
     * Sets the birthdayMonth value for this CrmMember.
     * 
     * @param birthdayMonth
     */
    public void setBirthdayMonth(java.lang.Integer birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
    }


    /**
     * Gets the birthdayYear value for this CrmMember.
     * 
     * @return birthdayYear
     */
    public java.lang.Integer getBirthdayYear() {
        return birthdayYear;
    }


    /**
     * Sets the birthdayYear value for this CrmMember.
     * 
     * @param birthdayYear
     */
    public void setBirthdayYear(java.lang.Integer birthdayYear) {
        this.birthdayYear = birthdayYear;
    }


    /**
     * Gets the branchID value for this CrmMember.
     * 
     * @return branchID
     */
    public int getBranchID() {
        return branchID;
    }


    /**
     * Sets the branchID value for this CrmMember.
     * 
     * @param branchID
     */
    public void setBranchID(int branchID) {
        this.branchID = branchID;
    }


    /**
     * Gets the branchName value for this CrmMember.
     * 
     * @return branchName
     */
    public java.lang.String getBranchName() {
        return branchName;
    }


    /**
     * Sets the branchName value for this CrmMember.
     * 
     * @param branchName
     */
    public void setBranchName(java.lang.String branchName) {
        this.branchName = branchName;
    }


    /**
     * Gets the cardType value for this CrmMember.
     * 
     * @return cardType
     */
    public java.lang.String getCardType() {
        return cardType;
    }


    /**
     * Sets the cardType value for this CrmMember.
     * 
     * @param cardType
     */
    public void setCardType(java.lang.String cardType) {
        this.cardType = cardType;
    }


    /**
     * Gets the city value for this CrmMember.
     * 
     * @return city
     */
    public java.lang.String getCity() {
        return city;
    }


    /**
     * Sets the city value for this CrmMember.
     * 
     * @param city
     */
    public void setCity(java.lang.String city) {
        this.city = city;
    }


    /**
     * Gets the clientIDList value for this CrmMember.
     * 
     * @return clientIDList
     */
    public java.lang.String[] getClientIDList() {
        return clientIDList;
    }


    /**
     * Sets the clientIDList value for this CrmMember.
     * 
     * @param clientIDList
     */
    public void setClientIDList(java.lang.String[] clientIDList) {
        this.clientIDList = clientIDList;
    }

    public java.lang.String getClientIDList(int i) {
        return this.clientIDList[i];
    }

    public void setClientIDList(int i, java.lang.String _value) {
        this.clientIDList[i] = _value;
    }


    /**
     * Gets the company value for this CrmMember.
     * 
     * @return company
     */
    public java.lang.String getCompany() {
        return company;
    }


    /**
     * Sets the company value for this CrmMember.
     * 
     * @param company
     */
    public void setCompany(java.lang.String company) {
        this.company = company;
    }


    /**
     * Gets the companyIndustry value for this CrmMember.
     * 
     * @return companyIndustry
     */
    public java.lang.String getCompanyIndustry() {
        return companyIndustry;
    }


    /**
     * Sets the companyIndustry value for this CrmMember.
     * 
     * @param companyIndustry
     */
    public void setCompanyIndustry(java.lang.String companyIndustry) {
        this.companyIndustry = companyIndustry;
    }


    /**
     * Gets the contactAddress value for this CrmMember.
     * 
     * @return contactAddress
     */
    public java.lang.String getContactAddress() {
        return contactAddress;
    }


    /**
     * Sets the contactAddress value for this CrmMember.
     * 
     * @param contactAddress
     */
    public void setContactAddress(java.lang.String contactAddress) {
        this.contactAddress = contactAddress;
    }


    /**
     * Gets the crmID value for this CrmMember.
     * 
     * @return crmID
     */
    public java.lang.Long getCrmID() {
        return crmID;
    }


    /**
     * Sets the crmID value for this CrmMember.
     * 
     * @param crmID
     */
    public void setCrmID(java.lang.Long crmID) {
        this.crmID = crmID;
    }


    /**
     * Gets the crmSource value for this CrmMember.
     * 
     * @return crmSource
     */
    public java.lang.String getCrmSource() {
        return crmSource;
    }


    /**
     * Sets the crmSource value for this CrmMember.
     * 
     * @param crmSource
     */
    public void setCrmSource(java.lang.String crmSource) {
        this.crmSource = crmSource;
    }


    /**
     * Gets the education value for this CrmMember.
     * 
     * @return education
     */
    public java.lang.String getEducation() {
        return education;
    }


    /**
     * Sets the education value for this CrmMember.
     * 
     * @param education
     */
    public void setEducation(java.lang.String education) {
        this.education = education;
    }


    /**
     * Gets the email value for this CrmMember.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this CrmMember.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the fpMobilePhone value for this CrmMember.
     * 
     * @return fpMobilePhone
     */
    public java.lang.String getFpMobilePhone() {
        return fpMobilePhone;
    }


    /**
     * Sets the fpMobilePhone value for this CrmMember.
     * 
     * @param fpMobilePhone
     */
    public void setFpMobilePhone(java.lang.String fpMobilePhone) {
        this.fpMobilePhone = fpMobilePhone;
    }


    /**
     * Gets the hobby value for this CrmMember.
     * 
     * @return hobby
     */
    public java.lang.String getHobby() {
        return hobby;
    }


    /**
     * Sets the hobby value for this CrmMember.
     * 
     * @param hobby
     */
    public void setHobby(java.lang.String hobby) {
        this.hobby = hobby;
    }


    /**
     * Gets the homeAddress value for this CrmMember.
     * 
     * @return homeAddress
     */
    public java.lang.String getHomeAddress() {
        return homeAddress;
    }


    /**
     * Sets the homeAddress value for this CrmMember.
     * 
     * @param homeAddress
     */
    public void setHomeAddress(java.lang.String homeAddress) {
        this.homeAddress = homeAddress;
    }


    /**
     * Gets the idCard value for this CrmMember.
     * 
     * @return idCard
     */
    public java.lang.String getIdCard() {
        return idCard;
    }


    /**
     * Sets the idCard value for this CrmMember.
     * 
     * @param idCard
     */
    public void setIdCard(java.lang.String idCard) {
        this.idCard = idCard;
    }


    /**
     * Gets the idCardType value for this CrmMember.
     * 
     * @return idCardType
     */
    public java.lang.String getIdCardType() {
        return idCardType;
    }


    /**
     * Sets the idCardType value for this CrmMember.
     * 
     * @param idCardType
     */
    public void setIdCardType(java.lang.String idCardType) {
        this.idCardType = idCardType;
    }


    /**
     * Gets the importance value for this CrmMember.
     * 
     * @return importance
     */
    public java.lang.String getImportance() {
        return importance;
    }


    /**
     * Sets the importance value for this CrmMember.
     * 
     * @param importance
     */
    public void setImportance(java.lang.String importance) {
        this.importance = importance;
    }


    /**
     * Gets the income value for this CrmMember.
     * 
     * @return income
     */
    public java.lang.Float getIncome() {
        return income;
    }


    /**
     * Sets the income value for this CrmMember.
     * 
     * @param income
     */
    public void setIncome(java.lang.Float income) {
        this.income = income;
    }


    /**
     * Gets the incomeRangeId value for this CrmMember.
     * 
     * @return incomeRangeId
     */
    public java.lang.String getIncomeRangeId() {
        return incomeRangeId;
    }


    /**
     * Sets the incomeRangeId value for this CrmMember.
     * 
     * @param incomeRangeId
     */
    public void setIncomeRangeId(java.lang.String incomeRangeId) {
        this.incomeRangeId = incomeRangeId;
    }


    /**
     * Gets the lastUsingTime value for this CrmMember.
     * 
     * @return lastUsingTime
     */
    public java.util.Calendar getLastUsingTime() {
        return lastUsingTime;
    }


    /**
     * Sets the lastUsingTime value for this CrmMember.
     * 
     * @param lastUsingTime
     */
    public void setLastUsingTime(java.util.Calendar lastUsingTime) {
        this.lastUsingTime = lastUsingTime;
    }


    /**
     * Gets the married value for this CrmMember.
     * 
     * @return married
     */
    public java.lang.Integer getMarried() {
        return married;
    }


    /**
     * Sets the married value for this CrmMember.
     * 
     * @param married
     */
    public void setMarried(java.lang.Integer married) {
        this.married = married;
    }


    /**
     * Gets the memberName value for this CrmMember.
     * 
     * @return memberName
     */
    public java.lang.String getMemberName() {
        return memberName;
    }


    /**
     * Sets the memberName value for this CrmMember.
     * 
     * @param memberName
     */
    public void setMemberName(java.lang.String memberName) {
        this.memberName = memberName;
    }


    /**
     * Gets the memberPosition value for this CrmMember.
     * 
     * @return memberPosition
     */
    public java.lang.String getMemberPosition() {
        return memberPosition;
    }


    /**
     * Sets the memberPosition value for this CrmMember.
     * 
     * @param memberPosition
     */
    public void setMemberPosition(java.lang.String memberPosition) {
        this.memberPosition = memberPosition;
    }


    /**
     * Gets the memberType value for this CrmMember.
     * 
     * @return memberType
     */
    public java.lang.String getMemberType() {
        return memberType;
    }


    /**
     * Sets the memberType value for this CrmMember.
     * 
     * @param memberType
     */
    public void setMemberType(java.lang.String memberType) {
        this.memberType = memberType;
    }


    /**
     * Gets the mobilePhone value for this CrmMember.
     * 
     * @return mobilePhone
     */
    public java.lang.String getMobilePhone() {
        return mobilePhone;
    }


    /**
     * Sets the mobilePhone value for this CrmMember.
     * 
     * @param mobilePhone
     */
    public void setMobilePhone(java.lang.String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }


    /**
     * Gets the mobilePhoneList value for this CrmMember.
     * 
     * @return mobilePhoneList
     */
    public java.lang.String[] getMobilePhoneList() {
        return mobilePhoneList;
    }


    /**
     * Sets the mobilePhoneList value for this CrmMember.
     * 
     * @param mobilePhoneList
     */
    public void setMobilePhoneList(java.lang.String[] mobilePhoneList) {
        this.mobilePhoneList = mobilePhoneList;
    }

    public java.lang.String getMobilePhoneList(int i) {
        return this.mobilePhoneList[i];
    }

    public void setMobilePhoneList(int i, java.lang.String _value) {
        this.mobilePhoneList[i] = _value;
    }


    /**
     * Gets the mobilePhoneTwo value for this CrmMember.
     * 
     * @return mobilePhoneTwo
     */
    public java.lang.String getMobilePhoneTwo() {
        return mobilePhoneTwo;
    }


    /**
     * Sets the mobilePhoneTwo value for this CrmMember.
     * 
     * @param mobilePhoneTwo
     */
    public void setMobilePhoneTwo(java.lang.String mobilePhoneTwo) {
        this.mobilePhoneTwo = mobilePhoneTwo;
    }


    /**
     * Gets the modifyTime value for this CrmMember.
     * 
     * @return modifyTime
     */
    public java.util.Calendar getModifyTime() {
        return modifyTime;
    }


    /**
     * Sets the modifyTime value for this CrmMember.
     * 
     * @param modifyTime
     */
    public void setModifyTime(java.util.Calendar modifyTime) {
        this.modifyTime = modifyTime;
    }


    /**
     * Gets the nationality value for this CrmMember.
     * 
     * @return nationality
     */
    public java.lang.String getNationality() {
        return nationality;
    }


    /**
     * Sets the nationality value for this CrmMember.
     * 
     * @param nationality
     */
    public void setNationality(java.lang.String nationality) {
        this.nationality = nationality;
    }


    /**
     * Gets the occupation value for this CrmMember.
     * 
     * @return occupation
     */
    public java.lang.String getOccupation() {
        return occupation;
    }


    /**
     * Sets the occupation value for this CrmMember.
     * 
     * @param occupation
     */
    public void setOccupation(java.lang.String occupation) {
        this.occupation = occupation;
    }


    /**
     * Gets the pad1 value for this CrmMember.
     * 
     * @return pad1
     */
    public java.lang.String getPad1() {
        return pad1;
    }


    /**
     * Sets the pad1 value for this CrmMember.
     * 
     * @param pad1
     */
    public void setPad1(java.lang.String pad1) {
        this.pad1 = pad1;
    }


    /**
     * Gets the pad10 value for this CrmMember.
     * 
     * @return pad10
     */
    public java.lang.String getPad10() {
        return pad10;
    }


    /**
     * Sets the pad10 value for this CrmMember.
     * 
     * @param pad10
     */
    public void setPad10(java.lang.String pad10) {
        this.pad10 = pad10;
    }


    /**
     * Gets the pad2 value for this CrmMember.
     * 
     * @return pad2
     */
    public java.lang.String getPad2() {
        return pad2;
    }


    /**
     * Sets the pad2 value for this CrmMember.
     * 
     * @param pad2
     */
    public void setPad2(java.lang.String pad2) {
        this.pad2 = pad2;
    }


    /**
     * Gets the pad3 value for this CrmMember.
     * 
     * @return pad3
     */
    public java.lang.String getPad3() {
        return pad3;
    }


    /**
     * Sets the pad3 value for this CrmMember.
     * 
     * @param pad3
     */
    public void setPad3(java.lang.String pad3) {
        this.pad3 = pad3;
    }


    /**
     * Gets the pad4 value for this CrmMember.
     * 
     * @return pad4
     */
    public java.lang.String getPad4() {
        return pad4;
    }


    /**
     * Sets the pad4 value for this CrmMember.
     * 
     * @param pad4
     */
    public void setPad4(java.lang.String pad4) {
        this.pad4 = pad4;
    }


    /**
     * Gets the pad5 value for this CrmMember.
     * 
     * @return pad5
     */
    public java.lang.String getPad5() {
        return pad5;
    }


    /**
     * Sets the pad5 value for this CrmMember.
     * 
     * @param pad5
     */
    public void setPad5(java.lang.String pad5) {
        this.pad5 = pad5;
    }


    /**
     * Gets the pad6 value for this CrmMember.
     * 
     * @return pad6
     */
    public java.lang.String getPad6() {
        return pad6;
    }


    /**
     * Sets the pad6 value for this CrmMember.
     * 
     * @param pad6
     */
    public void setPad6(java.lang.String pad6) {
        this.pad6 = pad6;
    }


    /**
     * Gets the pad7 value for this CrmMember.
     * 
     * @return pad7
     */
    public java.lang.String getPad7() {
        return pad7;
    }


    /**
     * Sets the pad7 value for this CrmMember.
     * 
     * @param pad7
     */
    public void setPad7(java.lang.String pad7) {
        this.pad7 = pad7;
    }


    /**
     * Gets the pad8 value for this CrmMember.
     * 
     * @return pad8
     */
    public java.lang.String getPad8() {
        return pad8;
    }


    /**
     * Sets the pad8 value for this CrmMember.
     * 
     * @param pad8
     */
    public void setPad8(java.lang.String pad8) {
        this.pad8 = pad8;
    }


    /**
     * Gets the pad9 value for this CrmMember.
     * 
     * @return pad9
     */
    public java.lang.String getPad9() {
        return pad9;
    }


    /**
     * Sets the pad9 value for this CrmMember.
     * 
     * @param pad9
     */
    public void setPad9(java.lang.String pad9) {
        this.pad9 = pad9;
    }


    /**
     * Gets the parentBranchID value for this CrmMember.
     * 
     * @return parentBranchID
     */
    public int getParentBranchID() {
        return parentBranchID;
    }


    /**
     * Sets the parentBranchID value for this CrmMember.
     * 
     * @param parentBranchID
     */
    public void setParentBranchID(int parentBranchID) {
        this.parentBranchID = parentBranchID;
    }


    /**
     * Gets the phone value for this CrmMember.
     * 
     * @return phone
     */
    public java.lang.String getPhone() {
        return phone;
    }


    /**
     * Sets the phone value for this CrmMember.
     * 
     * @param phone
     */
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }


    /**
     * Gets the postCode value for this CrmMember.
     * 
     * @return postCode
     */
    public java.lang.String getPostCode() {
        return postCode;
    }


    /**
     * Sets the postCode value for this CrmMember.
     * 
     * @param postCode
     */
    public void setPostCode(java.lang.String postCode) {
        this.postCode = postCode;
    }


    /**
     * Gets the province value for this CrmMember.
     * 
     * @return province
     */
    public java.lang.String getProvince() {
        return province;
    }


    /**
     * Sets the province value for this CrmMember.
     * 
     * @param province
     */
    public void setProvince(java.lang.String province) {
        this.province = province;
    }


    /**
     * Gets the returnData value for this CrmMember.
     * 
     * @return returnData
     */
    public com.sw.thirdparty.crm.webservice.ReturnData getReturnData() {
        return returnData;
    }


    /**
     * Sets the returnData value for this CrmMember.
     * 
     * @param returnData
     */
    public void setReturnData(com.sw.thirdparty.crm.webservice.ReturnData returnData) {
        this.returnData = returnData;
    }


    /**
     * Gets the sex value for this CrmMember.
     * 
     * @return sex
     */
    public java.lang.String getSex() {
        return sex;
    }


    /**
     * Sets the sex value for this CrmMember.
     * 
     * @param sex
     */
    public void setSex(java.lang.String sex) {
        this.sex = sex;
    }


    /**
     * Gets the sourceIndustry value for this CrmMember.
     * 
     * @return sourceIndustry
     */
    public java.lang.String getSourceIndustry() {
        return sourceIndustry;
    }


    /**
     * Sets the sourceIndustry value for this CrmMember.
     * 
     * @param sourceIndustry
     */
    public void setSourceIndustry(java.lang.String sourceIndustry) {
        this.sourceIndustry = sourceIndustry;
    }


    /**
     * Gets the totalUsingCount value for this CrmMember.
     * 
     * @return totalUsingCount
     */
    public java.lang.Integer getTotalUsingCount() {
        return totalUsingCount;
    }


    /**
     * Sets the totalUsingCount value for this CrmMember.
     * 
     * @param totalUsingCount
     */
    public void setTotalUsingCount(java.lang.Integer totalUsingCount) {
        this.totalUsingCount = totalUsingCount;
    }


    /**
     * Gets the tradeEndTime value for this CrmMember.
     * 
     * @return tradeEndTime
     */
    public java.lang.String getTradeEndTime() {
        return tradeEndTime;
    }


    /**
     * Sets the tradeEndTime value for this CrmMember.
     * 
     * @param tradeEndTime
     */
    public void setTradeEndTime(java.lang.String tradeEndTime) {
        this.tradeEndTime = tradeEndTime;
    }


    /**
     * Gets the tradeStartTime value for this CrmMember.
     * 
     * @return tradeStartTime
     */
    public java.lang.String getTradeStartTime() {
        return tradeStartTime;
    }


    /**
     * Sets the tradeStartTime value for this CrmMember.
     * 
     * @param tradeStartTime
     */
    public void setTradeStartTime(java.lang.String tradeStartTime) {
        this.tradeStartTime = tradeStartTime;
    }


    /**
     * Gets the userName value for this CrmMember.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this CrmMember.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Gets the ventureTrend value for this CrmMember.
     * 
     * @return ventureTrend
     */
    public java.lang.String getVentureTrend() {
        return ventureTrend;
    }


    /**
     * Sets the ventureTrend value for this CrmMember.
     * 
     * @param ventureTrend
     */
    public void setVentureTrend(java.lang.String ventureTrend) {
        this.ventureTrend = ventureTrend;
    }


    /**
     * Gets the workStatus value for this CrmMember.
     * 
     * @return workStatus
     */
    public java.lang.String getWorkStatus() {
        return workStatus;
    }


    /**
     * Sets the workStatus value for this CrmMember.
     * 
     * @param workStatus
     */
    public void setWorkStatus(java.lang.String workStatus) {
        this.workStatus = workStatus;
    }


    /**
     * Gets the xltPhone value for this CrmMember.
     * 
     * @return xltPhone
     */
    public java.lang.String getXltPhone() {
        return xltPhone;
    }


    /**
     * Sets the xltPhone value for this CrmMember.
     * 
     * @param xltPhone
     */
    public void setXltPhone(java.lang.String xltPhone) {
        this.xltPhone = xltPhone;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CrmMember)) return false;
        CrmMember other = (CrmMember) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.authenticationCode==null && other.getAuthenticationCode()==null) || 
             (this.authenticationCode!=null &&
              this.authenticationCode.equals(other.getAuthenticationCode()))) &&
            ((this.belief==null && other.getBelief()==null) || 
             (this.belief!=null &&
              this.belief.equals(other.getBelief()))) &&
            ((this.birthday==null && other.getBirthday()==null) || 
             (this.birthday!=null &&
              this.birthday.equals(other.getBirthday()))) &&
            ((this.birthdayDate==null && other.getBirthdayDate()==null) || 
             (this.birthdayDate!=null &&
              this.birthdayDate.equals(other.getBirthdayDate()))) &&
            ((this.birthdayMonth==null && other.getBirthdayMonth()==null) || 
             (this.birthdayMonth!=null &&
              this.birthdayMonth.equals(other.getBirthdayMonth()))) &&
            ((this.birthdayYear==null && other.getBirthdayYear()==null) || 
             (this.birthdayYear!=null &&
              this.birthdayYear.equals(other.getBirthdayYear()))) &&
            this.branchID == other.getBranchID() &&
            ((this.branchName==null && other.getBranchName()==null) || 
             (this.branchName!=null &&
              this.branchName.equals(other.getBranchName()))) &&
            ((this.cardType==null && other.getCardType()==null) || 
             (this.cardType!=null &&
              this.cardType.equals(other.getCardType()))) &&
            ((this.city==null && other.getCity()==null) || 
             (this.city!=null &&
              this.city.equals(other.getCity()))) &&
            ((this.clientIDList==null && other.getClientIDList()==null) || 
             (this.clientIDList!=null &&
              java.util.Arrays.equals(this.clientIDList, other.getClientIDList()))) &&
            ((this.company==null && other.getCompany()==null) || 
             (this.company!=null &&
              this.company.equals(other.getCompany()))) &&
            ((this.companyIndustry==null && other.getCompanyIndustry()==null) || 
             (this.companyIndustry!=null &&
              this.companyIndustry.equals(other.getCompanyIndustry()))) &&
            ((this.contactAddress==null && other.getContactAddress()==null) || 
             (this.contactAddress!=null &&
              this.contactAddress.equals(other.getContactAddress()))) &&
            ((this.crmID==null && other.getCrmID()==null) || 
             (this.crmID!=null &&
              this.crmID.equals(other.getCrmID()))) &&
            ((this.crmSource==null && other.getCrmSource()==null) || 
             (this.crmSource!=null &&
              this.crmSource.equals(other.getCrmSource()))) &&
            ((this.education==null && other.getEducation()==null) || 
             (this.education!=null &&
              this.education.equals(other.getEducation()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.fpMobilePhone==null && other.getFpMobilePhone()==null) || 
             (this.fpMobilePhone!=null &&
              this.fpMobilePhone.equals(other.getFpMobilePhone()))) &&
            ((this.hobby==null && other.getHobby()==null) || 
             (this.hobby!=null &&
              this.hobby.equals(other.getHobby()))) &&
            ((this.homeAddress==null && other.getHomeAddress()==null) || 
             (this.homeAddress!=null &&
              this.homeAddress.equals(other.getHomeAddress()))) &&
            ((this.idCard==null && other.getIdCard()==null) || 
             (this.idCard!=null &&
              this.idCard.equals(other.getIdCard()))) &&
            ((this.idCardType==null && other.getIdCardType()==null) || 
             (this.idCardType!=null &&
              this.idCardType.equals(other.getIdCardType()))) &&
            ((this.importance==null && other.getImportance()==null) || 
             (this.importance!=null &&
              this.importance.equals(other.getImportance()))) &&
            ((this.income==null && other.getIncome()==null) || 
             (this.income!=null &&
              this.income.equals(other.getIncome()))) &&
            ((this.incomeRangeId==null && other.getIncomeRangeId()==null) || 
             (this.incomeRangeId!=null &&
              this.incomeRangeId.equals(other.getIncomeRangeId()))) &&
            ((this.lastUsingTime==null && other.getLastUsingTime()==null) || 
             (this.lastUsingTime!=null &&
              this.lastUsingTime.equals(other.getLastUsingTime()))) &&
            ((this.married==null && other.getMarried()==null) || 
             (this.married!=null &&
              this.married.equals(other.getMarried()))) &&
            ((this.memberName==null && other.getMemberName()==null) || 
             (this.memberName!=null &&
              this.memberName.equals(other.getMemberName()))) &&
            ((this.memberPosition==null && other.getMemberPosition()==null) || 
             (this.memberPosition!=null &&
              this.memberPosition.equals(other.getMemberPosition()))) &&
            ((this.memberType==null && other.getMemberType()==null) || 
             (this.memberType!=null &&
              this.memberType.equals(other.getMemberType()))) &&
            ((this.mobilePhone==null && other.getMobilePhone()==null) || 
             (this.mobilePhone!=null &&
              this.mobilePhone.equals(other.getMobilePhone()))) &&
            ((this.mobilePhoneList==null && other.getMobilePhoneList()==null) || 
             (this.mobilePhoneList!=null &&
              java.util.Arrays.equals(this.mobilePhoneList, other.getMobilePhoneList()))) &&
            ((this.mobilePhoneTwo==null && other.getMobilePhoneTwo()==null) || 
             (this.mobilePhoneTwo!=null &&
              this.mobilePhoneTwo.equals(other.getMobilePhoneTwo()))) &&
            ((this.modifyTime==null && other.getModifyTime()==null) || 
             (this.modifyTime!=null &&
              this.modifyTime.equals(other.getModifyTime()))) &&
            ((this.nationality==null && other.getNationality()==null) || 
             (this.nationality!=null &&
              this.nationality.equals(other.getNationality()))) &&
            ((this.occupation==null && other.getOccupation()==null) || 
             (this.occupation!=null &&
              this.occupation.equals(other.getOccupation()))) &&
            ((this.pad1==null && other.getPad1()==null) || 
             (this.pad1!=null &&
              this.pad1.equals(other.getPad1()))) &&
            ((this.pad10==null && other.getPad10()==null) || 
             (this.pad10!=null &&
              this.pad10.equals(other.getPad10()))) &&
            ((this.pad2==null && other.getPad2()==null) || 
             (this.pad2!=null &&
              this.pad2.equals(other.getPad2()))) &&
            ((this.pad3==null && other.getPad3()==null) || 
             (this.pad3!=null &&
              this.pad3.equals(other.getPad3()))) &&
            ((this.pad4==null && other.getPad4()==null) || 
             (this.pad4!=null &&
              this.pad4.equals(other.getPad4()))) &&
            ((this.pad5==null && other.getPad5()==null) || 
             (this.pad5!=null &&
              this.pad5.equals(other.getPad5()))) &&
            ((this.pad6==null && other.getPad6()==null) || 
             (this.pad6!=null &&
              this.pad6.equals(other.getPad6()))) &&
            ((this.pad7==null && other.getPad7()==null) || 
             (this.pad7!=null &&
              this.pad7.equals(other.getPad7()))) &&
            ((this.pad8==null && other.getPad8()==null) || 
             (this.pad8!=null &&
              this.pad8.equals(other.getPad8()))) &&
            ((this.pad9==null && other.getPad9()==null) || 
             (this.pad9!=null &&
              this.pad9.equals(other.getPad9()))) &&
            this.parentBranchID == other.getParentBranchID() &&
            ((this.phone==null && other.getPhone()==null) || 
             (this.phone!=null &&
              this.phone.equals(other.getPhone()))) &&
            ((this.postCode==null && other.getPostCode()==null) || 
             (this.postCode!=null &&
              this.postCode.equals(other.getPostCode()))) &&
            ((this.province==null && other.getProvince()==null) || 
             (this.province!=null &&
              this.province.equals(other.getProvince()))) &&
            ((this.returnData==null && other.getReturnData()==null) || 
             (this.returnData!=null &&
              this.returnData.equals(other.getReturnData()))) &&
            ((this.sex==null && other.getSex()==null) || 
             (this.sex!=null &&
              this.sex.equals(other.getSex()))) &&
            ((this.sourceIndustry==null && other.getSourceIndustry()==null) || 
             (this.sourceIndustry!=null &&
              this.sourceIndustry.equals(other.getSourceIndustry()))) &&
            ((this.totalUsingCount==null && other.getTotalUsingCount()==null) || 
             (this.totalUsingCount!=null &&
              this.totalUsingCount.equals(other.getTotalUsingCount()))) &&
            ((this.tradeEndTime==null && other.getTradeEndTime()==null) || 
             (this.tradeEndTime!=null &&
              this.tradeEndTime.equals(other.getTradeEndTime()))) &&
            ((this.tradeStartTime==null && other.getTradeStartTime()==null) || 
             (this.tradeStartTime!=null &&
              this.tradeStartTime.equals(other.getTradeStartTime()))) &&
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              this.userName.equals(other.getUserName()))) &&
            ((this.ventureTrend==null && other.getVentureTrend()==null) || 
             (this.ventureTrend!=null &&
              this.ventureTrend.equals(other.getVentureTrend()))) &&
            ((this.workStatus==null && other.getWorkStatus()==null) || 
             (this.workStatus!=null &&
              this.workStatus.equals(other.getWorkStatus()))) &&
            ((this.xltPhone==null && other.getXltPhone()==null) || 
             (this.xltPhone!=null &&
              this.xltPhone.equals(other.getXltPhone())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getAuthenticationCode() != null) {
            _hashCode += getAuthenticationCode().hashCode();
        }
        if (getBelief() != null) {
            _hashCode += getBelief().hashCode();
        }
        if (getBirthday() != null) {
            _hashCode += getBirthday().hashCode();
        }
        if (getBirthdayDate() != null) {
            _hashCode += getBirthdayDate().hashCode();
        }
        if (getBirthdayMonth() != null) {
            _hashCode += getBirthdayMonth().hashCode();
        }
        if (getBirthdayYear() != null) {
            _hashCode += getBirthdayYear().hashCode();
        }
        _hashCode += getBranchID();
        if (getBranchName() != null) {
            _hashCode += getBranchName().hashCode();
        }
        if (getCardType() != null) {
            _hashCode += getCardType().hashCode();
        }
        if (getCity() != null) {
            _hashCode += getCity().hashCode();
        }
        if (getClientIDList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getClientIDList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getClientIDList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCompany() != null) {
            _hashCode += getCompany().hashCode();
        }
        if (getCompanyIndustry() != null) {
            _hashCode += getCompanyIndustry().hashCode();
        }
        if (getContactAddress() != null) {
            _hashCode += getContactAddress().hashCode();
        }
        if (getCrmID() != null) {
            _hashCode += getCrmID().hashCode();
        }
        if (getCrmSource() != null) {
            _hashCode += getCrmSource().hashCode();
        }
        if (getEducation() != null) {
            _hashCode += getEducation().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getFpMobilePhone() != null) {
            _hashCode += getFpMobilePhone().hashCode();
        }
        if (getHobby() != null) {
            _hashCode += getHobby().hashCode();
        }
        if (getHomeAddress() != null) {
            _hashCode += getHomeAddress().hashCode();
        }
        if (getIdCard() != null) {
            _hashCode += getIdCard().hashCode();
        }
        if (getIdCardType() != null) {
            _hashCode += getIdCardType().hashCode();
        }
        if (getImportance() != null) {
            _hashCode += getImportance().hashCode();
        }
        if (getIncome() != null) {
            _hashCode += getIncome().hashCode();
        }
        if (getIncomeRangeId() != null) {
            _hashCode += getIncomeRangeId().hashCode();
        }
        if (getLastUsingTime() != null) {
            _hashCode += getLastUsingTime().hashCode();
        }
        if (getMarried() != null) {
            _hashCode += getMarried().hashCode();
        }
        if (getMemberName() != null) {
            _hashCode += getMemberName().hashCode();
        }
        if (getMemberPosition() != null) {
            _hashCode += getMemberPosition().hashCode();
        }
        if (getMemberType() != null) {
            _hashCode += getMemberType().hashCode();
        }
        if (getMobilePhone() != null) {
            _hashCode += getMobilePhone().hashCode();
        }
        if (getMobilePhoneList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMobilePhoneList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMobilePhoneList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMobilePhoneTwo() != null) {
            _hashCode += getMobilePhoneTwo().hashCode();
        }
        if (getModifyTime() != null) {
            _hashCode += getModifyTime().hashCode();
        }
        if (getNationality() != null) {
            _hashCode += getNationality().hashCode();
        }
        if (getOccupation() != null) {
            _hashCode += getOccupation().hashCode();
        }
        if (getPad1() != null) {
            _hashCode += getPad1().hashCode();
        }
        if (getPad10() != null) {
            _hashCode += getPad10().hashCode();
        }
        if (getPad2() != null) {
            _hashCode += getPad2().hashCode();
        }
        if (getPad3() != null) {
            _hashCode += getPad3().hashCode();
        }
        if (getPad4() != null) {
            _hashCode += getPad4().hashCode();
        }
        if (getPad5() != null) {
            _hashCode += getPad5().hashCode();
        }
        if (getPad6() != null) {
            _hashCode += getPad6().hashCode();
        }
        if (getPad7() != null) {
            _hashCode += getPad7().hashCode();
        }
        if (getPad8() != null) {
            _hashCode += getPad8().hashCode();
        }
        if (getPad9() != null) {
            _hashCode += getPad9().hashCode();
        }
        _hashCode += getParentBranchID();
        if (getPhone() != null) {
            _hashCode += getPhone().hashCode();
        }
        if (getPostCode() != null) {
            _hashCode += getPostCode().hashCode();
        }
        if (getProvince() != null) {
            _hashCode += getProvince().hashCode();
        }
        if (getReturnData() != null) {
            _hashCode += getReturnData().hashCode();
        }
        if (getSex() != null) {
            _hashCode += getSex().hashCode();
        }
        if (getSourceIndustry() != null) {
            _hashCode += getSourceIndustry().hashCode();
        }
        if (getTotalUsingCount() != null) {
            _hashCode += getTotalUsingCount().hashCode();
        }
        if (getTradeEndTime() != null) {
            _hashCode += getTradeEndTime().hashCode();
        }
        if (getTradeStartTime() != null) {
            _hashCode += getTradeStartTime().hashCode();
        }
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        if (getVentureTrend() != null) {
            _hashCode += getVentureTrend().hashCode();
        }
        if (getWorkStatus() != null) {
            _hashCode += getWorkStatus().hashCode();
        }
        if (getXltPhone() != null) {
            _hashCode += getXltPhone().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CrmMember.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("webservice.crm.thirdparty.sw.com", "crmMember"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authenticationCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "authenticationCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("belief");
        elemField.setXmlName(new javax.xml.namespace.QName("", "belief"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthday");
        elemField.setXmlName(new javax.xml.namespace.QName("", "birthday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthdayDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "birthdayDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthdayMonth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "birthdayMonth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthdayYear");
        elemField.setXmlName(new javax.xml.namespace.QName("", "birthdayYear"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("branchID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "branchID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("branchName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "branchName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("city");
        elemField.setXmlName(new javax.xml.namespace.QName("", "city"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientIDList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clientIDList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("company");
        elemField.setXmlName(new javax.xml.namespace.QName("", "company"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companyIndustry");
        elemField.setXmlName(new javax.xml.namespace.QName("", "companyIndustry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contactAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("crmID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "crmID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("crmSource");
        elemField.setXmlName(new javax.xml.namespace.QName("", "crmSource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("education");
        elemField.setXmlName(new javax.xml.namespace.QName("", "education"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fpMobilePhone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fpMobilePhone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hobby");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hobby"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("homeAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "homeAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCard");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idCard"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCardType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idCardType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importance");
        elemField.setXmlName(new javax.xml.namespace.QName("", "importance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("income");
        elemField.setXmlName(new javax.xml.namespace.QName("", "income"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("incomeRangeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "incomeRangeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastUsingTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lastUsingTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("married");
        elemField.setXmlName(new javax.xml.namespace.QName("", "married"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "memberName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberPosition");
        elemField.setXmlName(new javax.xml.namespace.QName("", "memberPosition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "memberType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mobilePhone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mobilePhone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mobilePhoneList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mobilePhoneList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mobilePhoneTwo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mobilePhoneTwo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifyTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "modifyTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nationality");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nationality"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("occupation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "occupation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pad1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pad1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pad10");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pad10"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pad2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pad2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pad3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pad3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pad4");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pad4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pad5");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pad5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pad6");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pad6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pad7");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pad7"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pad8");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pad8"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pad9");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pad9"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentBranchID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parentBranchID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "phone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "postCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("province");
        elemField.setXmlName(new javax.xml.namespace.QName("", "province"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "returnData"));
        elemField.setXmlType(new javax.xml.namespace.QName("webservice.crm.thirdparty.sw.com", "returnData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sex");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceIndustry");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceIndustry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalUsingCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "totalUsingCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeEndTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradeEndTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeStartTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradeStartTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ventureTrend");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ventureTrend"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "workStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("xltPhone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "xltPhone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
