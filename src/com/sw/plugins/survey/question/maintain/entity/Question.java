/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.plugins.survey.question.maintain.controller
 * FileName: Question.java
 * @version 1.0
 * @author zhaofeng
 * @created on 2012-5-17
 * @last Modified 
 * @history
 */
package com.sw.plugins.survey.question.maintain.entity;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.sw.core.data.entity.BaseEntity;

/**
 *  问题实体
 *  @author zhaofeng
 *  @version 1.0
 *  </pre>
 *  Created on :下午2:11:59
 *  LastModified:
 *  History:
 *  </pre>
 */

public class Question extends BaseEntity {
	private static final long serialVersionUID = 333448505094682649L;
	/**
     * 调查问卷ID
     */
    private Long queID;
     /**
     * 问题内容
     */
    @Size(min = 1, max = 1000)
    private String questionName;
     /**
     * 问题类型
     */
    private Long questionType; 
     /**
     * 问题提示
     */
    @Size(min = 0, max = 1000)
    private String questionNotes;
    //显示设置
    /**
     * 选项是否横向排列 0 否 ， 1 是
     */
    private Long arrangeFlag;
    
    /**
     * 是否下拉列表显示 0 否 ， 1 是
     */
    private Long isDisplayAsSelect;
    /**
     * 选项是否随机 0 否 ， 1 是
     */
    private Long isRandOptions;
    /**
     * 每行放置的选项数（横向排列为真时才有效）
     */
    private Long perRowCol;
   
	//控制设置
    /**
     * 是否为必答题 0 否 ， 1 是
     */
    private Long requiredFlag;
    
    private Long minChoose;
    private Long maxChoose;
    @Min(1)@Max(100)
    private Long maxWordNumber;
    @Min(1)@Max(100)
    private Long minWordNumber;
    @Min(1)@Max(100)
    private Long fieldWidth;
    @Min(1)@Max(100)
    private Long fieldHeight;
    private Long orderId;
    private String isNextCreate;
    
    
    /**
     * 问题选项集合
     */
    private List optionList;
    
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
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public Long getQuestionType() {
		return questionType;
	}
	public void setQuestionType(Long questionType) {
		this.questionType = questionType;
	}
	public String getQuestionNotes() {
		return questionNotes;
	}
	public void setQuestionNotes(String questionNotes) {
		this.questionNotes = questionNotes;
	}
	public Long getArrangeFlag() {
		return arrangeFlag;
	}
	public void setArrangeFlag(Long arrangeFlag) {
		this.arrangeFlag = arrangeFlag;
	}
	public Long getMinChoose() {
		return minChoose;
	}
	public void setMinChoose(Long minChoose) {
		this.minChoose = minChoose;
	}
	public Long getMaxChoose() {
		return maxChoose;
	}
	public void setMaxChoose(Long maxChoose) {
		this.maxChoose = maxChoose;
	}
	public Long getRequiredFlag() {
		return requiredFlag;
	}
	public void setRequiredFlag(Long requiredFlag) {
		this.requiredFlag = requiredFlag;
	}
	public Long getFieldWidth() {
		return fieldWidth;
	}
	public void setFieldWidth(Long fieldWidth) {
		this.fieldWidth = fieldWidth;
	}
	public Long getFieldHeight() {
		return fieldHeight;
	}
	public void setFieldHeight(Long fieldHeight) {
		this.fieldHeight = fieldHeight;
	}
    public Long getIsDisplayAsSelect() {
		return isDisplayAsSelect;
	}
	public void setIsDisplayAsSelect(Long isDisplayAsSelect) {
		this.isDisplayAsSelect = isDisplayAsSelect;
	}
	public Long getIsRandOptions() {
		return isRandOptions;
	}
	public void setIsRandOptions(Long isRandOptions) {
		this.isRandOptions = isRandOptions;
	}
	public Long getPerRowCol() {
		return perRowCol;
	}
	public void setPerRowCol(Long perRowCol) {
		this.perRowCol = perRowCol;
	}
	public String getIsNextCreate() {
		return isNextCreate;
	}
	public void setIsNextCreate(String isNextCreate) {
		this.isNextCreate = isNextCreate;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public List getOptionList() {
		return optionList;
	}
	public void setOptionList(List optionList) {
		this.optionList = optionList;
	}
	public Long getMaxWordNumber() {
		return maxWordNumber;
	}
	public void setMaxWordNumber(Long maxWordNumber) {
		this.maxWordNumber = maxWordNumber;
	}
	public Long getMinWordNumber() {
		return minWordNumber;
	}
	public void setMinWordNumber(Long minWordNumber) {
		this.minWordNumber = minWordNumber;
	}
	public Long getResponseNum() {
		return responseNum;
	}
	public void setResponseNum(Long responseNum) {
		this.responseNum = responseNum;
	}
	
}
