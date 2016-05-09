package com.sw.plugins.clientcenter.requirement.investment.entity;


import com.sw.core.data.entity.BaseEntity;

/**
 * 投资需求信息
 */
public class Investment extends BaseEntity {

	private static final long serialVersionUID = 7457767709514990886L;
	private Long memberId;
	/**产品类型 */
	private Long productId;
	/**投资规模 */
	private String investScale;
	/**投资期限 */
	private Long investDeadline;
	/**预期收益率 */
	private Long yield;
	/**预计投资时间 */
	private Long predictTime;
	/**投资时间 */
	private String demandTime;
	/**投资描述 */
	private String description;
	/**删除状态 */
	private Integer state;
	
	/**产品类型 */
	private String productName;
	/**投资期限 */
	private String invesDeadlineName;
	/**预期收益率 */
	private String yieldName;
	/**预计投资时间 */
	private String predictTimeName;
	private String createTimeView;
	private Double minInvestScale;
	private Double maxInvestScale;
	
	private String name;
	private String gender;
	private String mobilePhone;
	
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
	public String getInvestScale() {
		return investScale;
	}
	public void setInvestScale(String investScale) {
		this.investScale = investScale;
	}
	public Long getInvestDeadline() {
		return investDeadline;
	}
	public void setInvestDeadline(Long investDeadline) {
		this.investDeadline = investDeadline;
	}
	public Long getYield() {
		return yield;
	}
	public void setYield(Long yield) {
		this.yield = yield;
	}
	public Long getPredictTime() {
		return predictTime;
	}
	public void setPredictTime(Long predictTime) {
		this.predictTime = predictTime;
	}
	public String getDemandTime() {
		return demandTime;
	}
	public void setDemandTime(String demandTime) {
		this.demandTime = demandTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getInvesDeadlineName() {
		return invesDeadlineName;
	}
	public void setInvesDeadlineName(String invesDeadlineName) {
		this.invesDeadlineName = invesDeadlineName;
	}
	public String getYieldName() {
		return yieldName;
	}
	public void setYieldName(String yieldName) {
		this.yieldName = yieldName;
	}
	public String getPredictTimeName() {
		return predictTimeName;
	}
	public void setPredictTimeName(String predictTimeName) {
		this.predictTimeName = predictTimeName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getCreateTimeView() {
		return createTimeView;
	}
	public void setCreateTimeView(String createTimeView) {
		this.createTimeView = createTimeView;
	}
	public Double getMinInvestScale() {
		return minInvestScale;
	}
	public void setMinInvestScale(Double minInvestScale) {
		this.minInvestScale = minInvestScale;
	}
	public Double getMaxInvestScale() {
		return maxInvestScale;
	}
	public void setMaxInvestScale(Double maxInvestScale) {
		this.maxInvestScale = maxInvestScale;
	}
	
}
