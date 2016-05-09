/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.plugins.survey.question.maintain.service
 * FileName: QuestionService.java
 * @version 1.0
 * @author 问题业务操作类
 * @created on 2012-5-17
 * @last Modified 
 * @history
 */
package com.sw.plugins.survey.question.maintain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.sw.core.common.Constant;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.service.CommonService;
import com.sw.plugins.survey.question.maintain.entity.Question;
import com.sw.plugins.survey.question.maintain.entity.QuestionOption;
import com.sw.plugins.survey.questionnaire.maintain.entity.Questionnaire;

/**
 *  问题业务操作类
 *  @author zhaofeng
 *  @version 1.0
 *  </pre>
 *  Created on :下午7:44:45
 *  LastModified:
 *  History:
 *  </pre>
 */
@Service
public class QuestionService extends CommonService<Question>{
	
	@Resource
    private QuestionOptionService questionOptionService;
	
	@Override
	public void save(Question entity) throws Exception {
		getCommonDao().insert("question.insert" , entity);
	}
	
	@Override
	public void update(Question entity) throws Exception {
		getCommonDao().update("question.update" , entity);
	}
	public void updateOrderId(Question entity) throws Exception {
		getCommonDao().update("question.updateOrderId" , entity);
	}

	@Override
	public Long getRecordCount(Question entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getAllRecordCount() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends BaseEntity> getList(Question entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends BaseEntity> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Question entity) throws Exception {
		getCommonDao().delete("question.delete",entity);
		//删除问题对应的选项
		questionOptionService.deleteOptionByQuestion(entity);
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByIn(Question entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByArr(Question entity) throws Exception {
		if (entity != null && entity.getIds() != null){
			for (String id : entity.getIds()){
				entity.setId(Long.parseLong(id));
				super.getCommonDao().delete("question.delete",entity);
			}
		}
	}

	@Override
	public Question getOneById(Question entity) throws Exception {
		if (entity.getId() == null)
			return null;
		return (Question)getCommonDao().selectObject("question.selectByOne", entity);
	}

	@Override
	public Map<String, Object> getGrid(Question entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
    /**
     *  查询调查问卷对应的问题集合
     *  @param questionnaire
     *  @return
     *  @author zhaofeng
     *  @version 1.0
     *  </pre>
     *  Created on :2012-5-17 下午7:54:20
     *  LastModified:
     *  History:
     *  </pre>
     */
    public List selectQuestions(Questionnaire questionnaire){
    	List list = new ArrayList();
    	//获取问题
    	List<Question> questionList = super.getCommonDao().selectList("question.selectQuestions", questionnaire);
    	
    	for(Question question : questionList){
    		//获取问题对应的选项
    		if(question.getQuestionType() == Constant.QUE_QUESTION_TYPE_SINGLE_CHOICE || 
    				question.getQuestionType() == Constant.QUE_QUESTION_TYPE_MULTIPLE_CHOICE){//判断是否为选择题
    			//查询选项集合
    			List optionList = questionOptionService.getOptionsByQuestion(question);
        		question.setOptionList(optionList);
    		}
    		
    		list.add(question);
    	}
    	return list;
    }
    
    public Question selectNext(Question entity){
    	if (entity.getId() == null)
			return null;
		return (Question)getCommonDao().selectObject("question.selectNext", entity);
    }
    
    //返回orderId
    public Long selectMaxOrderId(Question entity){
    	Long orderId = (Long)getCommonDao().selectObject("question.selectMaxOrderId", entity);
    	orderId = orderId == null?(long) 1:orderId+1;
		return orderId;
    }
    
    /**
     *  查询选项的统计结果
     *  @param questionnaire
     *  @return
     *  @author zhaofeng
     *  @version 1.0
     *  </pre>
     *  Created on :2012-5-31 上午9:16:35
     *  LastModified:
     *  History:
     *  </pre>
     */
    public List selectQuestionResults(Questionnaire questionnaire){
    	List list = new ArrayList();
    	//获取问题
    	List<Question> questionList = super.getCommonDao().selectList("question.selectQuestions", questionnaire);
    	for(Question question : questionList){
    		//查询问题回复数
    		question.setResponseNum((Long)getCommonDao().selectObject("question.countQuestionResponseNum", question));
    		//获取问题对应的选项
    		if(question.getQuestionType() == Constant.QUE_QUESTION_TYPE_SINGLE_CHOICE || 
    				question.getQuestionType() == Constant.QUE_QUESTION_TYPE_MULTIPLE_CHOICE){//判断是否为选择题
    			//查询选项集合
    			List<QuestionOption> optionList = questionOptionService.getOptionsByQuestion(question);
    			 //oracle/sqlserver 数据库不支持FIND_IN_SET函数，修改为在程序里面循环
    			 if(questionnaire.getDatabaseType().equals(Constant.DATASOURCE_ORACLE) || questionnaire.getDatabaseType().equals(Constant.DATASOURCE_SQLSERVER)){
    				 List<Map> optionAnswerList = getCommonDao().selectList("questionOption.selectQuestionOptionResponse", question);
    				 for( QuestionOption questionOption :  optionList){
    					 int rnum = 0 ;
			    		 for(Map map:optionAnswerList){
			    			 if(map != null){
			    			 String answerStr  = map.get("OPTIONANSWER")+"";
			    			 if(answerStr != null && !"".equals(answerStr)){
			    				 String[] array = answerStr.split(",");
				    			 for(String s:array){
				    				 if(s.equals(questionOption.getId()+"")){
				    					 rnum ++ ;
				    					 continue;
				    				 }
				    			 }
			    			 }
			    		   }
			    		 }
			    		 questionOption.setResponseNum(Long.valueOf(rnum));
    				 }
    			 }else{//mysql 数据库
    				    for( QuestionOption questionOption :  optionList){
       			    	 //查询选项回复数
       			    	 questionOption.setResponseNum((Long)getCommonDao().selectObject("questionOption.countQuestionOptionResponseNum", questionOption));
       			     }
    			 }
    			
    			 
    			
        		question.setOptionList(optionList);
    		}
    		
    		list.add(question);
    	}
    	return list;
    }
    
    /**
     *  查询填空题答案
     *  @param question
     *  @return
     *  @author zhaofeng
     *  @version 1.0
     *  </pre>
     *  Created on :2012-5-31 下午8:12:58
     *  LastModified:
     *  History:
     *  </pre>
     */
    public List selectBlankResult(Question question){
    	List<Map> list = super.getCommonDao().selectList("question.selectBlankResult", question);
    	return list;
    }
    /**
     *  查询用户答题信息
     *  @param questionnaire
     *  @return
     *  @author zhaofeng
     *  @version 1.0
     *  </pre>
     *  Created on :2012-7-16 下午3:16:42
     *  LastModified:
     *  History:
     *  </pre>
     */
    public Map selectUserResponseInfo(Questionnaire questionnaire){
    	Map map = (Map)super.getCommonDao().selectObject("question.selectUserResponseInfo", questionnaire);
    	return map;
    }
}
