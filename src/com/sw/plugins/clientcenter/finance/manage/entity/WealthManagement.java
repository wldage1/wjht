package com.sw.plugins.clientcenter.finance.manage.entity;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sw.core.data.entity.BaseEntity;

public class WealthManagement extends BaseEntity { 
	private static final long serialVersionUID = -3727408874554768583L;
	@Size(min=2, max=50)
	private String name;
	@Size(min=2, max=50)
	private String address;
	//@Pattern(regexp="^(\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}$")
	private String phone;
	//@Pattern(regexp="^(\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}$")
	private String fax;
	//@Pattern(regexp= "^([\\d]{1,3}\\.[\\d]{1,2})$|(\\s*)")
	private String accuracy;
	//@Pattern(regexp= "^([\\d]{1,2}\\.[\\d]{1,2})$|(\\s*)")
	private String latitude;
	private String image;
	@Size(min=0, max=150)
	private String description;
	
	public WealthManagement(){}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
