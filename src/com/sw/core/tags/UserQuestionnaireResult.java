/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.core.tags
 * FileName: UserQuestionnaireResult.java
 * @version 1.0
 * @author zhaofeng
 * @created on 2012-7-16
 * @last Modified 
 * @history
 */
package com.sw.core.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.sw.core.common.Constant;
import com.sw.plugins.survey.question.maintain.entity.Question;
import com.sw.plugins.survey.question.maintain.entity.QuestionOption;
import com.sw.plugins.survey.questionnaire.maintain.entity.Questionnaire;

/**
 *  用户答题详情
 *  @author zhaofeng
 *  @version 1.0
 *  </pre>
 *  Created on :下午2:11:56
 *  LastModified:
 *  History:
 *  </pre>
 */
public class UserQuestionnaireResult extends TagSupport{
	private Questionnaire questionnaire;
	private List<Question> questionList;
	private HashMap answerMap;
	private String type;
	
	/**
	 * 记录必填的选择题ID，以逗号分隔
	 */
	private String choiceRequireElments = null;
	
	public int doStartTag() throws JspException {
		  
		JspWriter writer = pageContext.getOut();
		try {
			writer.append("<div class=\"que\" >");
			
			//问卷标题
			writer.append("<div class=\"layout title\"><h4>").append(questionnaire.getQueTitle()).append("</h4>");
			//问卷副标题
			if(questionnaire.getQueSubTitle() != null && !"".equals(questionnaire.getQueSubTitle())){
				writer.append("<h2>").append(questionnaire.getQueSubTitle()).append("</h2>");
			}
			//问卷说明
			if(questionnaire.getQueDescribe() != null && !"".equals(questionnaire.getQueDescribe())){
				writer.append("<h5>").append(questionnaire.getQueDescribe()).append("</h5>");
			}
			writer.append("<hr/>");
			writer.append("</div>");
			
			
			//问卷问题 
			writer.append("<div class=\"layout content\">");
			if(questionList != null && questionList.size() > 0 ){
				for (Question question : questionList ){
					writer.append("<table class=\"question_table\" id=\"question_").append(question.getId()+"").append("\" >");
						
					    writer.append("<tr>");
						
						writer.append("<td class=\"question\" >");
						//是否为必答题
						if(question.getRequiredFlag() != null && question.getRequiredFlag() == 1){
							//组装必填选择题id
							if(question.getQuestionType() == Constant.QUE_QUESTION_TYPE_SINGLE_CHOICE || 
									question.getQuestionType() == Constant.QUE_QUESTION_TYPE_MULTIPLE_CHOICE){
								if(choiceRequireElments == null){
									choiceRequireElments = question.getId() + "" ;
								}else{
									choiceRequireElments += "," + question.getId() + "" ;
								}
							}
							writer.append("<span style=\"color:red\">*</span>");
						}
						//问题内容
						writer.append("<span id=\"questionName_").append(question.getId()+"").append("\" >").append(question.getQuestionName()).append("</span>");
						
						writer.append("<span class=\"questiontype\" >");
						if(question.getQuestionType() == Constant.QUE_QUESTION_TYPE_SINGLE_CHOICE){//单选题
							writer.append("[单选题]");
						}else if(question.getQuestionType() == Constant.QUE_QUESTION_TYPE_MULTIPLE_CHOICE){//多选题
							writer.append("[多选题]");
						}else if(question.getQuestionType() == Constant.QUE_QUESTION_TYPE_BLANK){//填空题
							writer.append("[填空题]");
						}else if(question.getQuestionType() == Constant.QUE_QUESTION_TYPE_SHORTANSWER){//简答题
							writer.append("[简答题]");
						}
						writer.append("</span>").append("</td>");  
						
						//问题提示
						if(question.getQuestionNotes() != null){
							writer.append("<tr><td class=\"layout note\">");
							writer.append("<span id=\"questionNotes_").append(question.getId() + "" ).append("\" >")
							.append(question.getQuestionNotes()).append("</span>")
							.append("</td></tr>");
						}
						
						//问题选项
						writer.append("<tr><td class=\"answer\" >");
						if (question.getQuestionType() == Constant.QUE_QUESTION_TYPE_BLANK){//填空题
							writer.append("<input type=\"text\" ");
							if(question.getMaxWordNumber() != null)
								writer.append(" maxlength=\"").append(question.getMaxWordNumber() + "").append("\" ");
							if(question.getMinWordNumber() != null)
								writer.append(" minlength=\"").append(question.getMinWordNumber() + "").append("\" ");
							if(question.getFieldWidth() != null)
								writer.append(" size=\"").append(question.getFieldWidth() + "").append("\" ");
							 //用户答案
							if(answerMap!= null && answerMap.get("OPTION_" + question.getId() + "") != null){
								writer.append(" value=\"").append(answerMap.get("OPTION_" + question.getId() + "") +"").append("\"");
							}
							writer.append(" />");
						}else if (question.getQuestionType() == Constant.QUE_QUESTION_TYPE_SHORTANSWER){//简答题
							writer.append("<textarea ");
							if(question.getFieldHeight() != null)
								writer.append(" cols=\"").append(question.getFieldWidth() + "").append("\" ");
							if(question.getFieldWidth() != null)
								writer.append(" rows=\"").append(question.getFieldHeight() + "").append("\" ");
							if(question.getMaxWordNumber() != null)
								writer.append(" maxlength=\"").append(question.getMaxWordNumber() + "").append("\" ");
							if(question.getMinWordNumber() != null)
								writer.append(" minlength=\"").append(question.getMinWordNumber() + "\" ");
							writer.append(" >");
                            //用户答案
							if(answerMap!= null && answerMap.get("OPTION_" + question.getId() + "") != null){
								writer.append(answerMap.get("OPTION_" + question.getId() + "") +"");
							}
							writer.append("</textarea>");
						}else{
							//用户答案
							String [] choiceAnswerArray = null;
							if(answerMap!= null && answerMap.get("OPTION_" + question.getId() + "") != null){
								String choiceAnswer = String.valueOf( answerMap.get("OPTION_" + question.getId() + ""));
								choiceAnswerArray = choiceAnswer.split(",");
							}
							List<QuestionOption> optionList = question.getOptionList();
							if(optionList != null ){
								for(QuestionOption questionOption : optionList){
									writer.append("<div>");
									//单选题打印radio
									if(question.getQuestionType() == Constant.QUE_QUESTION_TYPE_SINGLE_CHOICE){
										writer.append("<input type=\"radio\" name=\"option_").append(questionOption.getQuestionID() + "").append("\" ");
										writer.append(" id=\"option_").append(questionOption.getQuestionID() + "").append("\" ");
										//打印用户是否选择此项
										if(choiceAnswerArray != null && this.isInArray(questionOption.getId() + "", choiceAnswerArray)){
											writer.append(" checked ");
										}
										writer.append(" value=\"").append(questionOption.getId() + "").append("\" />");
										//选项文字
										writer.append("<span id=\"optionName_").append(questionnaire.getId() + "").append("_")
										.append(question.getId() + "" ).append("_").append(questionOption.getId()+"").append("\" >");
										writer.append(questionOption.getOptionName()).append("</span>");
									
									}else if (question.getQuestionType() == Constant.QUE_QUESTION_TYPE_MULTIPLE_CHOICE){//多选题
										writer.append("<input type=\"checkbox\" name=\"option_").append(questionOption.getQuestionID() + "").append("\" ");
										writer.append(" id=\"option_").append(questionOption.getQuestionID() + "").append("\" ");
										//打印用户是否选择此项
										if(choiceAnswerArray != null && this.isInArray(questionOption.getId() + "", choiceAnswerArray)){
											writer.append(" checked ");
										}
										writer.append(" value=\"").append(questionOption.getId() + "").append("\" />");
										//选项文字
										writer.append("<span id=\"optionName_").append(questionnaire.getId() + "").append("_")
										.append(question.getId() + "" ).append("_").append(questionOption.getId()+"").append("\" >");
										writer.append(questionOption.getOptionName()).append("</span>");
										
									}
									writer.append("</div>");
								}
							}
						}
						writer.append("</td></tr></table>");
						
				}
				     
			}else{
				writer.append("<div style=\"text-align:center;height:200px;padding-top:50px;\">此问卷没有添加问题</div>");
			}
			if(choiceRequireElments == null){
				choiceRequireElments = "";
			}
			writer.append("<span id=\"questionnaireChoiceRequiredEls\" els=\"").append(choiceRequireElments).append("\" style=\"display:none;\" ></span>");
			  writer.append("</div>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	private boolean isInArray(String testStr,String[] array){
		boolean flag = false;
		for(String s : array){
			if(s.equals(testStr)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	@Override
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().append("</div>");
			choiceRequireElments = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	
	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}


	public List<Question> getQuestionList() {
		return questionList;
	}


	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public HashMap getAnswerMap() {
		return answerMap;
	}

	public void setAnswerMap(HashMap answerMap) {
		this.answerMap = answerMap;
	}

}
