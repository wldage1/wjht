package com.sw.plugins.usercenter.system.organization.entity;

import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.sw.core.data.entity.BaseEntity;
public class Organization extends BaseEntity{
	private static final long serialVersionUID = 8439388546577637264L;
	/**级别（如果是树形结构使用此值）*/
	private Integer level;	
	/**树形结构描述关系（例如：,0,1,2,3,4,5,   5代表自身节点值，0-4代表其所有父级节点值）*/
	private String  path;
	/**是否是子节点 1:是子节点  0：不是子节点*/
	private Integer childNode;
	/**
	 * 
	 	@NotEmpty
		@Size(min = 1, max = 20)
		@NotNull
		@NumberFormat(style = Style.NUMBER)
		@Min(1)
		@Max(110)
		@NotEmpty(message = "Password must not be blank.")
		@Size(min = 1, max = 10, message = "Password must between 1 to 10 Characters.")
		@Pattern(regexp="(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{8}")
	 */
	/**父主键（如果是树形结构使用此值）*/
	private Long parentId;
	/**父主键（如果是树形结构使用此值）*/
	@NotEmpty
	private String parentName;	
	/**原父机构id*/
	private Long oldParentId;
	/**机构代码 */
	@Pattern(regexp="^\\d{1,30}$")
    private String code;
    /**机构名称 */
	@NotEmpty
	@Size(max = 50)
	@Pattern(regexp="^[a-zA-Z\\u4e00-\\u9fa5]+$")
    private String name;
    /**机构电话*/
	@Pattern(regexp="^$|(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)(\\d{8}|\\d{7})")
    private String phone;
    /**机构传真*/
	@Size(max = 15)
	@Pattern(regexp="^$|(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)(\\d{8}|\\d{7})")
    private String fax;
    /**邮政编码*/
	@Pattern(regexp="^$|^[\\s\\S]{6}")
    @NumberFormat(style = Style.NUMBER)
    private String postCode;
    /**机构地址*/
    @Size(max = 250)
    private String address;
    /**机构描述*/
    @Size(max = 250)
    private String description;
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getChildNode() {
		return childNode;
	}
	public void setChildNode(Integer childNode) {
		this.childNode = childNode;
	}
	public Long getOldParentId() {
		return oldParentId;
	}
	public void setOldParentId(Long oldParentId) {
		this.oldParentId = oldParentId;
	}	
	
	
}