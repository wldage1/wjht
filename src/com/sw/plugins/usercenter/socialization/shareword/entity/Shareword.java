package com.sw.plugins.usercenter.socialization.shareword.entity;

import javax.validation.constraints.Size;

import com.sw.core.data.entity.BaseEntity;

public class Shareword extends BaseEntity {

	private static final long serialVersionUID = -5645914212917376676L;

	private String code;
	private String title;
	@Size(max = 50)
	private String content;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
