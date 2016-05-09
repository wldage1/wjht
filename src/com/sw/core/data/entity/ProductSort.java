package com.sw.core.data.entity;


/**
 * 产品分类
 * @author Administrator
 *
 */
public class ProductSort extends BaseEntity{
	
	private static final long serialVersionUID = 2855396925942170219L;
	//产品分类名称
    private String name;
    //产品分类值
    private String value;  

    public ProductSort(){  
    }  

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {  
    	return value;  

    }  

    public void setValue(String value) {  
    	this.value = value;  
    }  
}
