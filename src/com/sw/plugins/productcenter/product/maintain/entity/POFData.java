/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.plugins.productcenter.product.maintain.entity
 * FileName: TrustProduct.java
 * @version 1.0
 * @author baokai
 * @created on 2012-6-4
 * @last Modified 
 * @history
 */
package com.sw.plugins.productcenter.product.maintain.entity;

import com.sw.core.data.entity.BaseEntity;

/**
 *  类简要说明
 *  @author baokai
 *  @version 1.0
 *  </pre>
 *  Created on :下午3:24:47
 *  LastModified:
 *  History:
 *  </pre>
 */
public class POFData extends BaseEntity{
	
	private String trustCode;//基金代码
	private String navDate;//最新净值日期
	private Float nav;//最新单位净值
	private Float fqNav;//最新复权净值
	private String m1NavG;//一个月收益
	private String m3NavG;//三个月收益
	private String m6NavG;//六个月收益
	private String y1NavG;//一年收益
	private String y2NavG;//二年收益
	private String tyNavG;//今年收益
	private String createNavG;//成立以来收益
	public String getTrustCode() {
		return trustCode;
	}
	public void setTrustCode(String trustCode) {
		this.trustCode = trustCode;
	}
	public String getNavDate() {
		return navDate;
	}
	public void setNavDate(String navDate) {
		this.navDate = navDate;
	}
	public Float getNav() {
		return nav;
	}
	public void setNav(Float nav) {
		this.nav = nav;
	}
	public Float getFqNav() {
		return fqNav;
	}
	public void setFqNav(Float fqNav) {
		this.fqNav = fqNav;
	}
	public String getM1NavG() {
		return m1NavG;
	}
	public void setM1NavG(String m1NavG) {
		this.m1NavG = m1NavG;
	}
	public String getM3NavG() {
		return m3NavG;
	}
	public void setM3NavG(String m3NavG) {
		this.m3NavG = m3NavG;
	}
	public String getM6NavG() {
		return m6NavG;
	}
	public void setM6NavG(String m6NavG) {
		this.m6NavG = m6NavG;
	}
	public String getY1NavG() {
		return y1NavG;
	}
	public void setY1NavG(String y1NavG) {
		this.y1NavG = y1NavG;
	}
	public String getY2NavG() {
		return y2NavG;
	}
	public void setY2NavG(String y2NavG) {
		this.y2NavG = y2NavG;
	}
	public String getTyNavG() {
		return tyNavG;
	}
	public void setTyNavG(String tyNavG) {
		this.tyNavG = tyNavG;
	}
	public String getCreateNavG() {
		return createNavG;
	}
	public void setCreateNavG(String createNavG) {
		this.createNavG = createNavG;
	}
	
	
	
	
}
