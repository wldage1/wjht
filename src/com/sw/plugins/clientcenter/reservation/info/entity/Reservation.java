package com.sw.plugins.clientcenter.reservation.info.entity;


import com.sw.core.data.entity.BaseEntity;

/**
 * 预约信息表
 */
public class Reservation extends BaseEntity {
 
	private static final long serialVersionUID = 6926391964421824213L;
	/**用户id */
	private Long memberId;
	/**产品id */
	private Long productId;
	/**CRM产品ID */
	private Long CRMID;
	/**预计购买金额 */
	private Integer buySum;
	/**产品募集结束时间 */
	private String productEndTime;
	/**删除状态 */
	private Integer delState;
	/**处理状态 */
	private Integer disposeState;
	/**处理信息 */
	private String disposeContent;
	/**投资指南编号 */
	private String guideNumber;
	/**预约取消状态 */
	private Long cancelState;
	
	/**预约开始时间 */
	private String starTime;
	/**预约结束时间 */
	private String endTime;

	/**以下属性用于页面显示*/
	
	/**用户账户*/
	private String account;
	/**用户姓名*/
	private String name;
	/**用户手机号*/
	private String mobilePhone;
	/**客户等级 */
	private String clientGrade;
	private String sexName;
	private String productName;
	private String disposeStateName;
	
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getCRMID() {
		return CRMID;
	}
	public void setCRMID(Long cRMID) {
		CRMID = cRMID;
	}
	public String getClientGrade() {
		return clientGrade;
	}
	public void setClientGrade(String clientGrade) {
		this.clientGrade = clientGrade;
	}
	public Integer getBuySum() {
		return buySum;
	}
	public void setBuySum(Integer buySum) {
		this.buySum = buySum;
	}
	public String getProductEndTime() {
		return productEndTime;
	}
	public void setProductEndTime(String productEndTime) {
		this.productEndTime = productEndTime;
	}
	public Integer getDelState() {
		return delState;
	}
	public void setDelState(Integer delState) {
		this.delState = delState;
	}
	public Integer getDisposeState() {
		return disposeState;
	}
	public void setDisposeState(Integer disposeState) {
		this.disposeState = disposeState;
	}
	public String getDisposeContent() {
		return disposeContent;
	}
	public void setDisposeContent(String disposeContent) {
		this.disposeContent = disposeContent;
	}
	public String getGuideNumber() {
		return guideNumber;
	}
	public void setGuideNumber(String guideNumber) {
		this.guideNumber = guideNumber;
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
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getDisposeStateName() {
		return disposeStateName;
	}
	public void setDisposeStateName(String disposeStateName) {
		this.disposeStateName = disposeStateName;
	}
	public String getSexName() {
		return sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getCancelState() {
		return cancelState;
	}
	public void setCancelState(Long cancelState) {
		this.cancelState = cancelState;
	}
	
}
