/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.plugins.survey.question.maintain.entity
 * FileName: QuestionRadio.java
 * @version 1.0
 * @author zhaofeng
 * @created on 2012-5-23
 * @last Modified 
 * @history
 */
package com.sw.plugins.survey.question.maintain.entity;

import com.sw.core.data.entity.BaseEntity;

/**
 *  问题选项实体
 *  @author zhaofeng
 *  @version 1.0
 *  </pre>
 *  Created on :下午1:16:52
 *  LastModified:
 *  History:
 *  </pre>
 */
public class QuestionOption extends BaseEntity{
	private static final long serialVersionUID = -498923743465916987L;
	/**
     * 调查问卷ID
     */
    private Long queID;
    /**
     * 问题ID
     */
    private Long questionID;
    /**
     * 选项ID（页面中生成）
     */
    private Long optionID;
    /**
     * 选项内容
     */
    private String optionName; 
    
    /**
     * 问题回复数
     */
    private Long responseNum;
    
    
    
    public Long getQueID() {
		return queID;
	}
	public void setQueID(Long queID) {
		this.queID = queID;
	}
	public Long getQuestionID() {
		return questionID;
	}
	public void setQuestionID(Long questionID) {
		this.questionID = questionID;
	}
	public Long getOptionID() {
		return optionID;
	}
	public void setOptionID(Long optionID) {
		this.optionID = optionID;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public Long getResponseNum() {
		return responseNum;
	}
	public void setResponseNum(Long responseNum) {
		this.responseNum = responseNum;
	}

}
