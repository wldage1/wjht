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

import com.sw.core.data.entity.BaseEntity;

/**
 *  产品属性值
 *  @author haoyuanfu
 *  @version 1.0
 *  </pre>
 *  Created on :上午09:22:20
 *  LastModified:
 *  History:
 *  </pre>
 */
public class ProductValue extends BaseEntity {
	
	/**唯一性标识ID*/
	private Long sysId;
	/**产品ID*/
	private Long prdId;
	/**产品属性ID*/
	private Long prdAttribute;
	/**产品属性对应值*/
	private String prdValue;
	
	/**用于查询的PRDIDS*/
	private String selectPrdIds;
	
	public Long getSysId() {
		return sysId;
	}
	public void setSysId(Long sysId) {
		this.sysId = sysId;
	}
	public Long getPrdId() {
		return prdId;
	}
	public void setPrdId(Long prdId) {
		this.prdId = prdId;
	}
	public Long getPrdAttribute() {
		return prdAttribute;
	}
	public void setPrdAttribute(Long prdAttribute) {
		this.prdAttribute = prdAttribute;
	}
	public String getPrdValue() {
		return prdValue;
	}
	public void setPrdValue(String prdValue) {
		this.prdValue = prdValue;
	}
	public String getSelectPrdIds() {
		return selectPrdIds;
	}
	public void setSelectPrdIds(String selectPrdIds) {
		this.selectPrdIds = selectPrdIds;
	}

}
