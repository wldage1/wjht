package com.sw.core.data.entity;


/**
 * 数据字典总体分类
 * @author Administrator
 *
 */
public class DictionarySort extends BaseEntity{
	
    private static final long serialVersionUID = 1L;  
    
    //字典名称
    private String name;
    //标识字典类型的固定值
    private String value;  

    public DictionarySort(){  
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
