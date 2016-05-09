/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.core.tags
 * FileName: QuestionnaireResultTag.java
 * @version 1.0
 * @author zhaofeng
 * @created on 2012-5-31
 * @last Modified 
 * @history
 */
package com.sw.core.tags;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.sw.core.common.Constant;
import com.sw.plugins.survey.question.maintain.entity.Question;
import com.sw.plugins.survey.question.maintain.entity.QuestionOption;
import com.sw.plugins.survey.questionnaire.maintain.entity.Questionnaire;

/**
 *  问卷调查结果查看
 *  @author zhaofeng
 *  @version 1.0
 *  </pre>
 *  Created on :上午10:33:27
 *  LastModified:
 *  History:
 *  </pre>
 */
public class QuestionnaireResultTag extends TagSupport{
	/**
	 * 调查问卷实体
	 */
	private Questionnaire questionnaire;
	private List<Question> questionList;
	//查看填空结果的url
	private String viewUrl;

	public int doStartTag() throws JspException {
		String common = (String) pageContext.getSession().getAttribute("common");
		String style = (String)pageContext.getSession().getAttribute("style");
		String base = (String)pageContext.getSession().getAttribute("base");
		JspWriter writer = pageContext.getOut();
		try {
			writer.append("<div class=\"queResult\" >");
			
			//问卷标题
			writer.append("<div class=\"\"><div class=\"title\">")
			.append(questionnaire.getQueTitle()).append("</div>");
			writer.append("<span style=\"font-size:16px;\">频数频率分析</span>");
			writer.append("<span style=\"color:red\">* </span><span>频数是指选项在回复中出现的次数，频率则指相比样本回复数(N)而言，频数所占的比例</span>");
			writer.append("<hr/>");
			writer.append("</div>");
			
			//问卷问题
			
			if(questionList != null && questionList.size() > 0 ){
			writer.append("<div class=\" content\">");
			for (Question question : questionList ){
				writer.append("<table class=\"question_table\" id=\"question_").append(question.getId()+"").append("\" >");
					
				    writer.append("<tr>");
					
					writer.append("<td class=\"question\" style=\"text-align: left;\" colspan=\"6\" >");
					//是否为必答题
					if(question.getRequiredFlag() != null && question.getRequiredFlag() == 1){
						writer.append("<span style=\"color:red\">*</span>");
					}
					//问题内容
					writer.append("<span  id=\"questionName_").append(question.getId()+"").append("\" >").append(question.getQuestionName()).append("</span>");
					
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
					writer.append("</span>").append("</td></tr>");  
					
					if (question.getQuestionType() == Constant.QUE_QUESTION_TYPE_BLANK || 
							question.getQuestionType() == Constant.QUE_QUESTION_TYPE_SHORTANSWER){//填空题,简答题
					   
						//打印表头
					   writer.append("<tr class=\"table_head\"><th>回复</th><th>回复百分比</th><th> 百分比图示</th><th>缺失</th><th>缺失百分比</th><th>百分比图示</th></tr>");
                       writer.append("<tr class=\"data_table\">");
                       //回复数
                       writer.append("<td>").append(question.getResponseNum()+"").append("</td>");
                       //回复百分比
                       writer.append("<td>").append(percent(question.getResponseNum(),questionnaire.getResponseNum())).append("</td>");
                       //回复百分比图示
                       writer.append("<td style=\"text-align:left;\" width=\"170px;\"><img style=\"height:10px;\" src=\"").append(base).append("/")
                       .append(common).append("/").append(style).append("/images/icon/bar.gif").append("\" width=\"").
					   append(percent(question.getResponseNum(),questionnaire.getResponseNum())).append("\" /></td>");
                       //缺失
                       writer.append("<td>").append(questionnaire.getResponseNum()-question.getResponseNum()+"").append("</td>");
                       //缺失百分比
                       writer.append("<td>").append(percent(questionnaire.getResponseNum()-question.getResponseNum(),questionnaire.getResponseNum()))
                       .append("</td>");
                       //缺失百分比图示
                       writer.append("<td style=\"text-align:left;\" width=\"170px;\"><img style=\"height:10px;\" src=\"").append(base).append("/")
                       .append(common).append("/").append(style).append("/images/icon/bar.gif").append("\" width=\"").
					   append(percent(questionnaire.getResponseNum()-question.getResponseNum(),questionnaire.getResponseNum())).append("\" /></td>");
                       writer.append("</tr>");
                       //输出查询填空题答案按钮
                       writer.append("<tr><td colspan=\"6\">");
                       writer.append("<a class=\"viewBlankResult\" href=\"").append(getViewUrl()).append("&queID=").append(this.questionnaire.getId()+"");
                       writer.append("&id=").append(question.getId()+"")
                       //回复数，留给查看问题回复页面的返回按钮使用
                       .append("&queResponseNum=").append(questionnaire.getResponseNum()+"")
                       
                       .append("\" >查看答案").append("</a>");
                       writer.append("</td></tr>");
                       
                       
					
					}else{//选择题
						//样本数 
						writer.append("<tr class=\"select_info\"><td style=\"text-align:left;\">");
						writer.append("样本数：</td><td >").append(questionnaire.getResponseNum()+"");
						writer.append("</td><td colspan=\"4\"></td></tr>");
						
						//回复数
						writer.append("<tr class=\"select_info\">");
						writer.append("<td>有效回复数：</td><td>").append(question.getResponseNum()+"").append("</td><td>回复百分比：</td><td>");
						writer.append(percent(question.getResponseNum(),questionnaire.getResponseNum())).append("</td>");
						writer.append("<td style=\"text-align:left;\" width=\"170px;\"><img style=\"height:10px;\" src=\"").append(base).append("/")
						.append(common).append("/").append(style).append("/images/icon/bar.gif").append("\" width=\"").
						append(percent(question.getResponseNum(),questionnaire.getResponseNum())).append("\" /></td>")
						.append("<td></td>");
						writer.append("</tr>");
						
						//打印统计的表头
						writer.append("<tr class=\"table_head\"><th>选项名称</th><th>频数</th><th> 百分比</th><th>百分比图示</th><th>有效百分比</th><th>百分比图示</th></tr>");
						List<QuestionOption> optionList = question.getOptionList();
						if(optionList != null ){
							for(QuestionOption questionOption : optionList){
							  //选项名称
							  writer.append("<tr class=\"data_table\"><td>").append(questionOption.getOptionName()).append("</td>");
							  //频数
							  writer.append("<td>").append(questionOption.getResponseNum()+"").append("</td>");
							  //百分比(选项回复数与样本数的百分比)
							  writer.append("<td>").append(percent(questionOption.getResponseNum(),questionnaire.getResponseNum())).append("</td>");
							  //百分比图示
							  writer.append("<td style=\"text-align:left;\" width=\"170px;\"><img style=\"height:10px;\" src=\"").append(base).append("/").append(common).append("/").append(style).
								append("/images/icon/bar.gif").append("\" width=\"").
								append(percent(questionOption.getResponseNum(),questionnaire.getResponseNum())).append("\" /></td>");
							  //有效百分比(选项回复数与问题回复数的百分比)
							  writer.append("<td>").append(percent(questionOption.getResponseNum(),question.getResponseNum())).append("</td>");
							  writer.append("<td style=\"text-align:left;\" width=\"170px;\"><img style=\"height:10px;\" src=\"").append(base).append("/").append(common).append("/").append(style).
								append("/images/icon/bar.gif").append("\" width=\"").
								append(percent(questionOption.getResponseNum(),question.getResponseNum())).append("\" /></td>");
							  writer.append("</tr>");
							}
						}
					}
					
					writer.append("</table>");
			}
			       writer.append("</div>");
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	/**
	 *  生成百分数
	 *  @param num
	 *  @param total
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-31 下午4:52:20
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	private String percent (Long num,Long total){
		Double numInt = Double.parseDouble(num.toString());
		Double totalInt = Double.parseDouble(total.toString());
		if(numInt/totalInt > 0 && total != 0){
			DecimalFormat df = new DecimalFormat("#.00");
			String dfNum = df.format((numInt / totalInt) * 100);
			if (dfNum.indexOf(".") > 0) {
				dfNum = dfNum.replaceAll("0+?$", "");// 去掉多余的0
				dfNum = dfNum.replaceAll("[.]$", "");// 如最后一位是.则去掉
			}
			return dfNum + "%";
		}
		    return "0%";
	}
	
	@Override
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().append("</div>");
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

	public String getViewUrl() {
		return viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}
	

}
