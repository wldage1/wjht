/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.plugins.productcenter.attribute.structure.entity
 * FileName: Structure.java
 * @version 1.0
 * @author baokai
 * @created on 2012-5-15
 * @last Modified 
 * @history
 */
package com.sw.plugins.productcenter.attribute.structure.entity;

import java.util.List;

import com.sw.core.data.entity.BaseEntity;

/**
 *  类简要说明
 *  @author baokai
 *  @version 1.0
 *  </pre>
 *  Created on :上午9:02:57
 *  LastModified:
 *  History:
 *  </pre>
 */
public class Structure extends BaseEntity{

	private static final long serialVersionUID = -7579348308958089946L;
	private Long productTypeId;
	private String chineseName;
	private String englishName;
	private int isRequired;
	private int isEnabled;
	private Long showType;
	private String value;
	private Long verify;
	private String verifyValue;
	private int isSreach;
	private int isShowMyProduct;
	private int isShowProductCounter;
	private int isShowOnList;
	private int isShowOnContent;
	private Integer isOrder;
	private String description;
	//页面用到的属性
	private String structureString;
	private String productTypeName;
	public Long getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public int getIsRequired() {
		return isRequired;
	}
	public void setIsRequired(int isRequired) {
		this.isRequired = isRequired;
	}
	public int getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(int isEnabled) {
		this.isEnabled = isEnabled;
	}
	public Long getShowType() {
		return showType;
	}
	public void setShowType(Long showType) {
		this.showType = showType;
	}
	public Long getVerify() {
		return verify;
	}
	public void setVerify(Long verify) {
		this.verify = verify;
	}
	public int getIsSreach() {
		return isSreach;
	}
	public void setIsSreach(int isSreach) {
		this.isSreach = isSreach;
	}
	public int getIsShowMyProduct() {
		return isShowMyProduct;
	}
	public void setIsShowMyProduct(int isShowMyProduct) {
		this.isShowMyProduct = isShowMyProduct;
	}
	public int getIsShowProductCounter() {
		return isShowProductCounter;
	}
	public void setIsShowProductCounter(int isShowProductCounter) {
		this.isShowProductCounter = isShowProductCounter;
	}
	public int getIsShowOnList() {
		return isShowOnList;
	}
	public void setIsShowOnList(int isShowOnList) {
		this.isShowOnList = isShowOnList;
	}
	public int getIsShowOnContent() {
		return isShowOnContent;
	}
	public void setIsShowOnContent(int isShowOnContent) {
		this.isShowOnContent = isShowOnContent;
	}
	public Integer getIsOrder() {
		return isOrder;
	}
	public void setIsOrder(Integer isOrder) {
		this.isOrder = isOrder;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getVerifyValue() {
		return verifyValue;
	}
	public void setVerifyValue(String verifyValue) {
		this.verifyValue = verifyValue;
	}
	public String getStructureString() {
		return structureString;
	}
	public void setStructureString(String structureString) {
		this.structureString = structureString;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	
}
