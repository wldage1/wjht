package com.sw.thirdparty.webservice;

import org.springframework.stereotype.Service;


@Service
public class WebserviceConfig{
	/**自定义webservice url key值*/
	private String key;
	/**自定义webservice url值*/
	private String value;
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}