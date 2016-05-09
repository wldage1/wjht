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
 *  产品附属PDF文件
 *  @author haoyuanfu
 *  @version 1.0
 *  </pre>
 *  Created on :下午08:19:01
 *  LastModified:
 *  History:
 *  </pre>
 */
public class ProductPdf extends BaseEntity {
	
	/**产品ID*/
	private Long prdId;
	/**产品PDF附件IPadURL*/
	private String iPadUrl;
	/**上传文件名称IPad（中文）*/
	private String iPadFileName;
	/**产品PDF附件IPhoneURL*/
	private String iPhoneUrl;
	/**上传文件名称IPhone（中文）*/
	private String iPhoneFileName;
	
	
	public Long getPrdId() {
		return prdId;
	}
	public void setPrdId(Long prdId) {
		this.prdId = prdId;
	}
	public String getiPadUrl() {
		return iPadUrl;
	}
	public void setiPadUrl(String iPadUrl) {
		this.iPadUrl = iPadUrl;
	}
	public String getiPadFileName() {
		return iPadFileName;
	}
	public void setiPadFileName(String iPadFileName) {
		this.iPadFileName = iPadFileName;
	}
	public String getiPhoneUrl() {
		return iPhoneUrl;
	}
	public void setiPhoneUrl(String iPhoneUrl) {
		this.iPhoneUrl = iPhoneUrl;
	}
	public String getiPhoneFileName() {
		return iPhoneFileName;
	}
	public void setiPhoneFileName(String iPhoneFileName) {
		this.iPhoneFileName = iPhoneFileName;
	}

}
