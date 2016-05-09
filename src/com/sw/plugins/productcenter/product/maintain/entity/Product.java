/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.plugins.productcenter.product.maintain.entity
 * FileName: Product.java
 * @version 1.0
 * @author haoyuanfu
 * @created on 2012-5-14
 * @last Modified 
 * @history
 */
package com.sw.plugins.productcenter.product.maintain.entity;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.sw.core.data.entity.BaseEntity;
import com.sw.plugins.productcenter.attribute.structure.entity.Structure;

/**
 *   产品管理主表
 *  @author haoyuanfu
 *  @version 1.0
 *  </pre>
 *  Created on :上午09:22:20
 *  LastModified:
 *  History:
 *  </pre>
 */
public class Product extends BaseEntity {
	
	/**产品ID（产品表主键）*/
	private Long prdId;
	/**产品类型*/
	private Long prdType;
	/**产品名称*/
	private String prdName;
	/**产品编号*/
	private String prdNo;
	/**产品描述*/
	private String prdDescribe;
	/**产品来源   1=本系统录入 2=CRM导入*/
	private Integer prdFrom;
	/**产品状态  0=停用 1=启用*/
	private Integer prdStatus;
	/**财汇数据库产品编号*/
	private String finchinaSymbol;
	/**嘉华Crm产品ID*/
	private Long crmPrdId;
	/**删除标记*/
	private Integer delFlag;
	/**嘉华Crm产品结束时间*/
	private String productFinishTime;
	/**产品分享状态 0=不分享 1=分享*/
	private Integer shared;

	// 用于界面加载
	
	/**产品属性列表*/
	private List<Structure>  structureList;
	/**扩展属性中的属性ID和值*/
	private String attributeAndValue;
	/**用于查询的PRDIDS*/
	private String selectPrdIds;
	/**用于CRM产品类型中文显示*/
	private String prdTypeChn;
	/**在本系统中CRM产品ID*/
	private Long sysCrmId;
	/**用于产品PDF相关属性值字符串获取（IPad）*/
	private String pdfFileIPad;
	/**用于产品PDF相关属性值字符串获取（IPhone）*/
	private String pdfFileIPhone;
	/**获取与PDF列表Ipad*/
	private List<ProductPdf> ipadFileList;
	/**获取与PDF列表Iphone*/
	private List<ProductPdf> iphoneFileList;
	/**资源服务器链接地址*/
	private String synURL;
	/**上传相对路径*/
	private String filePath;
	
	public String getSelectPrdIds() {
		return selectPrdIds;
	}

	public void setSelectPrdIds(String selectPrdIds) {
		this.selectPrdIds = selectPrdIds;
	}

	public Product() {
		super();
	}
	
	public Long getPrdId() {
		return prdId;
	}

	public void setPrdId(Long prdId) {
		this.prdId = prdId;
	}
	public Long getPrdType() {
		return prdType;
	}
	public void setPrdType(Long prdType) {
		this.prdType = prdType;
	}
	public String getPrdName() {
		return prdName;
	}
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}

	public String getPrdNo() {
		return prdNo;
	}

	public void setPrdNo(String prdNo) {
		this.prdNo = prdNo;
	}

	public String getPrdDescribe() {
		return prdDescribe;
	}
	public void setPrdDescribe(String prdDescribe) {
		this.prdDescribe = prdDescribe;
	}
	public Integer getPrdFrom() {
		return prdFrom;
	}
	public void setPrdFrom(Integer prdFrom) {
		this.prdFrom = prdFrom;
	}
	public Integer getPrdStatus() {
		return prdStatus;
	}
	public void setPrdStatus(Integer prdStatus) {
		this.prdStatus = prdStatus;
	}
	public String getFinchinaSymbol() {
		return finchinaSymbol;
	}
	public void setFinchinaSymbol(String finchinaSymbol) {
		this.finchinaSymbol = finchinaSymbol;
	}
	public Long getCrmPrdId() {
		return crmPrdId;
	}
	public void setCrmPrdId(Long crmPrdId) {
		this.crmPrdId = crmPrdId;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public List<Structure> getStructureList() {
		return structureList;
	}

	public void setStructureList(List<Structure> structureList) {
		this.structureList = structureList;
	}

	public String getAttributeAndValue() {
		return attributeAndValue;
	}

	public void setAttributeAndValue(String attributeAndValue) {
		this.attributeAndValue = attributeAndValue;
	}

	public String getPrdTypeChn() {
		return prdTypeChn;
	}

	public void setPrdTypeChn(String prdTypeChn) {
		this.prdTypeChn = prdTypeChn;
	}

	public Long getSysCrmId() {
		return sysCrmId;
	}

	public void setSysCrmId(Long sysCrmId) {
		this.sysCrmId = sysCrmId;
	}

	public String getPdfFileIPad() {
		return pdfFileIPad;
	}

	public void setPdfFileIPad(String pdfFileIPad) {
		this.pdfFileIPad = pdfFileIPad;
	}

	public String getPdfFileIPhone() {
		return pdfFileIPhone;
	}

	public void setPdfFileIPhone(String pdfFileIPhone) {
		this.pdfFileIPhone = pdfFileIPhone;
	}

	public List<ProductPdf> getIpadFileList() {
		return ipadFileList;
	}

	public void setIpadFileList(List<ProductPdf> ipadFileList) {
		this.ipadFileList = ipadFileList;
	}

	public List<ProductPdf> getIphoneFileList() {
		return iphoneFileList;
	}

	public void setIphoneFileList(List<ProductPdf> iphoneFileList) {
		this.iphoneFileList = iphoneFileList;
	}

	public String getProductFinishTime() {
		return productFinishTime;
	}

	public void setProductFinishTime(String productFinishTime) {
		this.productFinishTime = productFinishTime;
	}

	public String getSynURL() {
		return synURL;
	}

	public void setSynURL(String synURL) {
		this.synURL = synURL;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getShared() {
		return shared;
	}

	public void setShared(Integer share) {
		this.shared = share;
	}

}
