/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.plugins.survey.question.maintain.controller
 * FileName: QuestionController.java
 * @version 1.0
 * @author zhaofeng
 * @created on 2012-5-17
 * @last Modified 
 * @history
 */
package com.sw.plugins.survey.question.maintain.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sw.core.common.Constant;
import com.sw.core.controller.BaseController;
import com.sw.core.controller.CommonModelAndView;
import com.sw.core.data.entity.BaseEntity;
import com.sw.plugins.survey.question.maintain.entity.Question;
import com.sw.plugins.survey.question.maintain.entity.QuestionOption;
import com.sw.plugins.survey.question.maintain.service.QuestionOptionService;
import com.sw.plugins.survey.question.maintain.service.QuestionService;
import com.sw.plugins.survey.questionnaire.maintain.entity.Questionnaire;
import com.sw.plugins.survey.questionnaire.maintain.service.QuestionnaireService;

/**
 *  问题页面跳转控制类
 *  @author zhaofeng
 *  @version 1.0
 *  </pre>
 *  Created on :下午2:11:59
 *  LastModified:
 *  History:
 *  </pre>
 */
@Controller  
public class QuestionController extends  BaseController {
	
   private static Logger logger = Logger.getLogger(QuestionController.class);
	
   //问题service
	@Resource 
	private QuestionService questionService;
	
	//问题选项service
    @Resource 
    private QuestionOptionService questionOptionService;	
    
    //问卷service
    @Resource 
    private QuestionnaireService questionnaireService;


	@Override
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 *  跳转到创建问题页面
	 *  @param questionnaire
	 *  @param request
	 *  @return
	 *  @author zhoafeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-21 下午4:24:45
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/question/maintain/create")
	   public CommonModelAndView create(Question question,HttpServletRequest request){
			CommonModelAndView commonModelAndView = new CommonModelAndView(request,question);
			commonModelAndView.addObject("question", question);
	       return commonModelAndView; 
	   } 
	
	
	 /**
	 *  根据新建 问题类型，跳转对应的新建问题页面
	 *  @param question
	 *  @param request
	 *  @return
	 *  @author Administrator
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-22 下午8:03:27
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/question/maintain/addQuestion*")
	 public CommonModelAndView addQuestion(Question question,HttpServletRequest request){
		    //视图名
			String viewName = null;
	        if(question.getQuestionType() == Constant.QUE_QUESTION_TYPE_SINGLE_CHOICE){//单选题
				viewName = "/survey/question/maintain/addsinglechoice";
	        }else if (question.getQuestionType() == Constant.QUE_QUESTION_TYPE_MULTIPLE_CHOICE){//多选题
	        	viewName = "/survey/question/maintain/addmultiplechoice";
	        }else if(question.getQuestionType() == Constant.QUE_QUESTION_TYPE_BLANK){
	        	viewName = "/survey/question/maintain/add/fillblank";
	        }else if(question.getQuestionType() == Constant.QUE_QUESTION_TYPE_SHORTANSWER){
	        	viewName = "/survey/question/maintain/add/shortAnswer";
	        }
	        CommonModelAndView commonModelAndView = new CommonModelAndView(viewName);
			return commonModelAndView; 
	   } 
	/**
	 *  保存问题
	 *  @param question
	 *  @param result
	 *  @param model
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-21 下午4:26:33
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping(value="/survey/question/maintain/save",method = RequestMethod.POST)
	public CommonModelAndView save(@Valid Question question,BindingResult result,Map<String,Object> model,HttpServletRequest request) {
		String viewName = null;
		if (result.hasErrors()) {
			//修改实体Bean验证
			if(question != null && question.getId() != null){
				if(question.getQuestionType() == Constant.QUE_QUESTION_TYPE_BLANK){
					viewName = "/survey/question/maintain/editfillblank";
				}else{
					viewName = "/survey/question/maintain/editshortAnswer";
				}
			//创建实体Bean验证	
			}else{
				if(question.getQuestionType() == Constant.QUE_QUESTION_TYPE_BLANK){
					viewName = "/survey/question/maintain/add/fillblank";
				}else{
					viewName = "/survey/question/maintain/add/shortAnswer";
				}
			}
			return new CommonModelAndView(viewName,question,messageSource);
		}
		//视图名
		try {
			//更新
			if(question != null && question.getId() != null){
				questionService.update(question);
			}else{
				//返回orderId
				question.setOrderId(questionService.selectMaxOrderId(question));
				questionService.save(question);
			}
			//跳转到自定义的成功提示
			viewName = "/survey/question/maintain/saveSuccess";
		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,question,messageSource);
		commonModelAndView.addObject("question", question);
		return commonModelAndView;		
	}
	
	 /**
	 *  跳转到编辑问题页面,根据问题类型不同跳转到不同的页面
	 *  @param question
	 *  @param request
	 *  @param model
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-21 下午4:32:27
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/question/maintain/modify")
	public CommonModelAndView modify(Question question,HttpServletRequest request,Map<String,Object> model){
	        //视图名
			String viewName = null;
			if(question.getQuestionType() == Constant.QUE_QUESTION_TYPE_SINGLE_CHOICE){//编辑单选题
				List<QuestionOption>  optionList = null;
				if (question.getId()!= null){
					try {
						//获取问题
						String code = question.getC();
						question = questionService.getOneById(question);
						question.setC(code);
						//获取问题选项集合
						optionList  = questionOptionService.getOptionsByQuestion(question);
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
				viewName = "/survey/question/maintain/editsinglechoice";
				CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,question,messageSource);
				commonModelAndView.addObject("questionOptionList",optionList);
				model.put("question", question);
				return commonModelAndView;
	        }else if (question.getQuestionType() == Constant.QUE_QUESTION_TYPE_MULTIPLE_CHOICE){
	        	List<QuestionOption>  optionList = null;
				if (question.getId()!= null){
					try {
						//获取问题
						String code = question.getC();
						question = questionService.getOneById(question);
						question.setC(code);
						//获取问题选项集合
						optionList  = questionOptionService.getOptionsByQuestion(question);
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
				viewName = "/survey/question/maintain/editmultiplechoice";
				CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,question,messageSource);
				commonModelAndView.addObject("questionOptionList",optionList);
				model.put("question", question);
				return commonModelAndView;
	        }else{
	        	String code = question.getC();
	        	if(question.getId() != null){
	        		try{
	        			question = questionService.getOneById(question);
	        			question.setC(code);
	        		}catch(Exception e){
	        			logger.error(e.getMessage());
	        		}
	        	}
	        	if(question.getQuestionType() == Constant.QUE_QUESTION_TYPE_BLANK){
	        		viewName = "/survey/question/maintain/editfillblank";
	        	}else{
	        		viewName = "/survey/question/maintain/editshortAnswer";
	        	}
				CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,question,messageSource);
				model.put("question", question);
				return commonModelAndView;
	        }
	 }
	/**
	 *  更新问题
	 *  @param question
	 *  @param result
	 *  @param model
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-21 下午4:37:10
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping(value="/survey/question/maintain/update",method = RequestMethod.POST)
	public CommonModelAndView update(@Valid Question question,BindingResult result,Map<String,Object> model) {
		if (result.hasErrors()) {
			return new CommonModelAndView(question);
		}
		//视图名
		String viewName = null;
		try {
			
			questionService.update(question);
			//跳转到自定义的成功提示
			viewName = "saveSuccess";
		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,question,messageSource);
		return commonModelAndView;		
	}
	/**
	 *  删除问题
	 *  @param question
	 *  @param request
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-21 下午4:39:59
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/question/maintain/delete")
    public CommonModelAndView delete(Question question,HttpServletRequest request,Map<String,Object> model){
		Questionnaire questionnaire = null;
		try {
			if (question != null && question.getId() != null) {
				questionService.delete(question);
				// 删除问题对应选项
				questionOptionService.deleteOptionByQuestion(question);
			} else if (question != null && question.getIds() != null) {
				questionService.deleteByArr(question);
			}
			questionnaire = new Questionnaire();
			questionnaire.setId(question.getQueID());
			questionnaire = questionnaireService.getOneById(questionnaire);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		// 查询问卷调查对应的问题集合
		List questionList = questionService.selectQuestions(questionnaire);
		// 转换为json格式，在页面初始化是插入到列表控件中
		String gridJsonData = JSONArray.fromObject(questionList).toString();
		questionnaire.setC("5010105");
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,questionnaire);
		commonModelAndView.setViewName("/survey/questionnaire/maintain/design");
		model.put("questionnaire", questionnaire);
		commonModelAndView.addObject("gridJsonData", gridJsonData);
		return commonModelAndView;

    } 
	/**
	 *  保存单选题
	 *  @param question
	 *  @param result
	 *  @param model
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-22 下午8:08:41
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping(value="/survey/question/maintain/saveSingleChoice",method = RequestMethod.POST)
	public CommonModelAndView saveSingleChoice(@Valid Question question,BindingResult result,HttpServletRequest request) {
		if (result.hasErrors()) {
			//获取页面的选项数，回显选项
			 Long questionCountNum =  Long.valueOf(request.getParameter("questionCountNum"));
			 List<QuestionOption> optionList = new ArrayList();
			 if(questionCountNum > 0 ){
				 for(int i = 0; i < questionCountNum; i++){
					 QuestionOption qr = new QuestionOption();
	                 qr.setQueID(question.getQueID());
	                 qr.setOptionID(Long.valueOf(i+1));
	                 //从页面获取选项的的内容
	                 qr.setOptionName((String)request.getParameter("optionID["+ (i+1) +"]"));
	                 optionList.add(qr);
				 }
			 }
			String viewName = "/survey/question/maintain/addsinglechoice";
			CommonModelAndView errorback = new CommonModelAndView(viewName,question,messageSource);
			errorback.addObject("questionOptionList", optionList);
			return errorback;
		}
		//视图名
		String viewName = null;
		try {
			//返回orderId
			question.setOrderId(questionService.selectMaxOrderId(question));
		     questionService.save(question);
		     //获取页面的选项数，选项在页面的命名规则为 optionID[1],optionID[2],optionID[3] ...
			 Long questionCountNum =  Long.valueOf(request.getParameter("questionCountNum"));
			 List<QuestionOption> optionList = new ArrayList();
			 if(questionCountNum > 0 ){
				 for(int i = 0; i < questionCountNum; i++){
					 QuestionOption qr = new QuestionOption();
	                 qr.setQueID(question.getQueID());
	                 qr.setQuestionID(question.getGeneratedKey());
	                 qr.setOptionID(Long.valueOf(i+1));
	                 //从页面获取选项的的内容
	                 qr.setOptionName((String)request.getParameter("optionID["+ (i+1) +"]"));
	                 optionList.add(qr);
				 }
			 }
			 //保存选项
			 questionOptionService.insertBatch(optionList);
			//跳转到自定义的成功提示
			viewName = "/survey/question/maintain/saveSuccess";
		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,question,messageSource);
		return commonModelAndView;		
	}
	
	/**
	 *  更新单选题
	 *  @param question
	 *  @param result
	 *  @param request
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-24 下午2:41:26
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping(value="/survey/question/maintain/updateSingleChoice",method = RequestMethod.POST)
	public CommonModelAndView updateSingleChoice(@Valid Question question,BindingResult result,HttpServletRequest request) {
		 //获取页面的选项数，选项在页面的命名规则为 optionID[1],optionID[2],optionID[3] ...
		 Long questionCountNum =  Long.valueOf(request.getParameter("questionCountNum"));
		 List<QuestionOption> optionList = new ArrayList();
		 if(questionCountNum > 0 ){
			 for(int i = 0; i < questionCountNum; i++){
				 QuestionOption qr = new QuestionOption();
                 qr.setQueID(question.getQueID());
                 qr.setQuestionID(question.getId());
                 qr.setOptionID(Long.valueOf(i+1));
                 //从页面获取选项的的内容
                 qr.setOptionName((String)request.getParameter("optionID["+ (i+1) +"]"));
                 optionList.add(qr);
			 }
		 }
		if (result.hasErrors()) {
			String viewName = "/survey/question/maintain/editsinglechoice";
			CommonModelAndView back = new CommonModelAndView(viewName,question,messageSource);
			//校验不通过，回显问题选项
			back.addObject("questionOptionList", optionList);
			return back;
		}
		//视图名
		String viewName = null;
		try {
			 String code = question.getC();
		     questionService.update(question);
		     question.setC(code);
		     //删除问题下所有选项
		     questionOptionService.deleteOptionByQuestion(question);
			 //保存选项
			 questionOptionService.insertBatch(optionList);
			//跳转到自定义的成功提示
			viewName = "/survey/question/maintain/saveSuccess";
		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,question,messageSource);
		commonModelAndView.addObject("question",question);
		return commonModelAndView;		
	}

	/**
	 *  保存多选题
	 *  @param question
	 *  @param result
	 *  @param request
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-25 上午9:27:30
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping(value="/survey/question/maintain/saveMultipleChoice",method = RequestMethod.POST)
	public CommonModelAndView saveMultipleChoice(@Valid Question question,BindingResult result,HttpServletRequest request) {
		if (result.hasErrors()) {
			 //获取页面的选项数，选项在页面的命名规则为 optionID[1],optionID[2],optionID[3] ...
			 Long questionCountNum =  Long.valueOf(request.getParameter("questionCountNum"));
			 List<QuestionOption> optionList = new ArrayList();
			 if(questionCountNum > 0 ){
				 for(int i = 0; i < questionCountNum; i++){
					 QuestionOption qr = new QuestionOption();
	                  qr.setQueID(question.getQueID());
	                  qr.setOptionID(Long.valueOf(i+1));
	                  //从页面获取选项的的内容
	                  qr.setOptionName((String)request.getParameter("optionID["+ (i+1) +"]"));
	                  optionList.add(qr);
				 }
			 }
			String viewName = "/survey/question/maintain/addmultiplechoice";
			CommonModelAndView errorback = new CommonModelAndView(viewName,question,messageSource);
			errorback.addObject("questionOptionList", optionList);
			return errorback;
		}
		//视图名
		String viewName = null;
		try {
			//返回orderId
			question.setOrderId(questionService.selectMaxOrderId(question));
		     questionService.save(question);
		     //获取页面的选项数，选项在页面的命名规则为 optionID[1],optionID[2],optionID[3] ...
			 Long questionCountNum =  Long.valueOf(request.getParameter("questionCountNum"));
			 List<QuestionOption> optionList = new ArrayList();
			 if(questionCountNum > 0 ){
				 for(int i = 0; i < questionCountNum; i++){
					 QuestionOption qr = new QuestionOption();
	                  qr.setQueID(question.getQueID());
	                  qr.setQuestionID(question.getGeneratedKey());
	                  qr.setOptionID(Long.valueOf(i+1));
	                  //从页面获取选项的的内容
	                  qr.setOptionName((String)request.getParameter("optionID["+ (i+1) +"]"));
	                  optionList.add(qr);
				 }
			 }
			 //保存选项
			 questionOptionService.insertBatch(optionList);
			//跳转到自定义的成功提示
			viewName = "/survey/question/maintain/saveSuccess";
		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,question,messageSource);
		return commonModelAndView;		
	}
	/**
	 *  更新多选题
	 *  @param question
	 *  @param result
	 *  @param request
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-25 上午9:36:35
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping(value="/survey/question/maintain/updateMultipleChoice",method = RequestMethod.POST)
	public CommonModelAndView updateMultipleChoice(@Valid Question question,BindingResult result,HttpServletRequest request) {
		 //获取页面的选项数，选项在页面的命名规则为 optionID[1],optionID[2],optionID[3] ...
		 Long questionCountNum =  Long.valueOf(request.getParameter("questionCountNum"));
		 List<QuestionOption> optionList = new ArrayList();
		 if(questionCountNum > 0 ){
			 for(int i = 0; i < questionCountNum; i++){
				 QuestionOption qr = new QuestionOption();
                 qr.setQueID(question.getQueID());
                 qr.setQuestionID(question.getId());
                 qr.setOptionID(Long.valueOf(i+1));
                 //从页面获取选项的的内容
                 qr.setOptionName((String)request.getParameter("optionID["+ (i+1) +"]"));
                 optionList.add(qr);
			 }
		 }
		if (result.hasErrors()) {
			String viewName = "/survey/question/maintain/editmultiplechoice";
			CommonModelAndView back = new CommonModelAndView(viewName,question,messageSource);
			//校验不通过，回显问题选项
			back.addObject("questionOptionList", optionList);
			return back;
		}
		//视图名
		String viewName = null;
		try {
			 String code = question.getC();
		     questionService.update(question);
		     question.setC(code);
		     //删除问题下所有选项
		     questionOptionService.deleteOptionByQuestion(question);
			 //保存选项
			 questionOptionService.insertBatch(optionList);
			//跳转到自定义的成功提示
			viewName = "/survey/question/maintain/saveSuccess";
		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,question,messageSource);
		commonModelAndView.addObject("question",question);
		return commonModelAndView;		
	}
	
	/**
	 *  预览调查问卷
	 *  @param questionnaire
	 *  @param request
	 *  @param model
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-25 下午2:38:15
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/question/maintain/preview*")
	public CommonModelAndView preview(Questionnaire questionnaire,HttpServletRequest request,Map<String,Object> model){
		
		String code = questionnaire.getC();
		if (questionnaire.getId() != null) {
			try {
				questionnaire.setId(questionnaire.getId());
				questionnaire = questionnaireService.getOneById(questionnaire);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		// 查询问卷调查对应的问题集合
		List questionList = questionService.selectQuestions(questionnaire);
		questionnaire.setC(code);
		//设置问卷参与时间
		Date now  = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        request.getSession().setAttribute("que_joinTime", df.format(now));
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,
				questionnaire);
		String viewName = "/survey/question/maintain/preview";
		commonModelAndView.setViewName(viewName);
		model.put("questionnaire", questionnaire);
		commonModelAndView.addObject("questionList", questionList);
		commonModelAndView.addObject("JoinTime", df.format(now));
		return commonModelAndView;
		
	}
	
	//排序
	@RequestMapping("/survey/question/maintain/order")
    public CommonModelAndView order(Question question,HttpServletRequest request,Map<String,Object> model){
		Questionnaire questionnaire = null;
		try {
			if( question!=null &&  question.getId()!=null){
				Long sawpId = (long) 0;
				Question sawpQue = new Question();
				Question orderQuestion_1 = new Question();
				//向上排序
				if(request.getParameter("swapId") != null && !"undefined".equals(request.getParameter("swapId"))){
					sawpId = Long.parseLong(request.getParameter("swapId"));
					Question sawpQuestion = new Question();
					sawpQuestion.setId(sawpId);
					//根据ID查询需要排序的数据
					sawpQue = questionService.getOneById(sawpQuestion);
					//根据ID更新orderId(上一条记录)
					orderQuestion_1 = new Question();
					orderQuestion_1.setId(sawpId);
					orderQuestion_1.setOrderId(question.getOrderId());
				}
				//向下排序
				else{
					Question sawpQuestion = new Question();
					sawpQuestion.setId(question.getId());
					sawpQuestion.setQueID(question.getQueID());
					sawpQuestion.setOrderId(question.getOrderId());
					sawpQue = questionService.selectNext(sawpQuestion);
					//根据ID更新orderId(下一条记录)
					orderQuestion_1 = new Question();
					orderQuestion_1.setId(sawpQue.getId());
					orderQuestion_1.setOrderId(question.getOrderId());
				}
				//根据ID更新orderId(本条记录)
				Question orderQuestion_2 = new Question();
				orderQuestion_2.setOrderId(sawpQue.getOrderId());
				orderQuestion_2.setId(question.getId());
				if(question.getOrderId() != sawpQue.getOrderId()){
					questionService.updateOrderId(orderQuestion_1);
					questionService.updateOrderId(orderQuestion_2);
				}
			}
			questionnaire = new Questionnaire();
			questionnaire.setId(question.getQueID());
			questionnaire = questionnaireService.getOneById(questionnaire);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		//查询问卷调查对应的问题集合
		List questionList = questionService.selectQuestions(questionnaire);
		// 转换为json格式，在页面初始化是插入到列表控件中
		String gridJsonData = JSONArray.fromObject(questionList).toString();
		questionnaire.setC(question.getC());
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,questionnaire);
		commonModelAndView.setViewName("/survey/questionnaire/maintain/design");
		model.put("questionnaire", questionnaire);
		commonModelAndView.addObject("gridJsonData",gridJsonData);
		return commonModelAndView;	
    } 
	/**
	 *  提交问卷
	 *  @param questionnaire
	 *  @param request
	 *  @param model
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-28 下午3:47:36
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/question/maintain/questionnairesubmit")
	public CommonModelAndView questionnaireSubmit(Questionnaire questionnaire,HttpServletRequest request,Map<String,Object> model){
		
		//System.out.println(getIp(request));
		//获取表单提交上来的所有参数
		Map map = request.getParameterMap();
		Map commonColumnMap = new HashMap();
		//IP地址
		commonColumnMap.put("IpAddress", getIp(request));
		//参与时间
		//ios端提交数据，没有session，直接从map里面取值
		if(map.get("JoinTime") != null){
			commonColumnMap.put("JoinTime",request.getParameter("JoinTime"));
		}else{
			String joinTime = (String) request.getSession().getAttribute("que_joinTime");
			//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			commonColumnMap.put("JoinTime", joinTime);
		}
		
		
		//获取问卷开始时间戳
		questionnaireService.saveQuestionnaireAnswer(map,questionnaire,commonColumnMap);
	
		CommonModelAndView commonModelAndView = new CommonModelAndView("/survey/question/maintain/submitTips");
		model.put("questionnaire", questionnaire);
			return commonModelAndView;
		
	}
	/**
	 *  获取客户端IP地址
	 *  @param request
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-29 下午7:57:27
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public  String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	/**
	 *  查看填空或者简答题结果
	 *  @param question
	 *  @param request
	 *  @param model
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-31 下午7:51:46
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/question/maintain/viewblankresult")
	public CommonModelAndView viewBlankResult(Question question,HttpServletRequest request,Map<String,Object> model){
	        //视图名
			List list = null;
			String queResponseNum = (String)request.getParameter("queResponseNum");
			String code = question.getC();
				List<QuestionOption>  optionList = null;
					try {
						 list = questionService.selectBlankResult(question);
						 question = questionService.getOneById(question);
						 question.setC(code);
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
					//返回按钮使用 begin
					question.setId(question.getQueID());
					//返回按钮使用 end 
					
				CommonModelAndView commonModelAndView = new CommonModelAndView(request,question);
				commonModelAndView.addObject("answerList",list);
				commonModelAndView.addObject("queResponseNum",queResponseNum);
				model.put("question", question);
				return commonModelAndView;
	 }
	/**
	 *  跳转到文件展示页面，这个链接是公开的且没有权限的限制
	 *  @param questionnaire
	 *  @param request
	 *  @param model
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-6-27 下午2:00:23
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/question/maintain/join")
	public ModelAndView join(Questionnaire questionnaire,HttpServletRequest request,Map<String,Object> model){
		if (questionnaire.getId() != null) {
			try {
				questionnaire.setId(questionnaire.getId());
				questionnaire = questionnaireService.getOneById(questionnaire);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		// 查询问卷调查对应的问题集合
		List questionList = questionService.selectQuestions(questionnaire);
		//设置问卷参与时间
		Date now  = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        request.getSession().setAttribute("que_joinTime", df.format(now));
		String viewName = "/survey/question/maintain/iospaper";
		ModelAndView md = new ModelAndView(viewName);
		model.put("questionnaire", questionnaire);
		md.addObject("questionList", questionList);
		md.addObject("JoinTime",df.format(now) );
		return md;
	}
	
	/**
	 *  用户答题详情
	 *  @param questionnaire
	 *  @param request
	 *  @param model
	 *  @return
	 *  @author zhanofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-7-16 上午10:22:59
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/question/maintain/userresultinfo")
	public ModelAndView userresultinfo(Questionnaire questionnaire,HttpServletRequest request,Map<String,Object> model){
		
		String code = questionnaire.getC();
		Long userID  = questionnaire.getUserID();
		if (questionnaire.getId() != null) {
			try {
				questionnaire.setId(questionnaire.getId());
				questionnaire = questionnaireService.getOneById(questionnaire);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		// 查询问卷调查对应的问题集合
		List questionList = questionService.selectQuestions(questionnaire);
		//查询用户答题信息
		questionnaire.setUserID(userID);
		Map userAnswerMap = questionService.selectUserResponseInfo(questionnaire);
		questionnaire.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,
				questionnaire);
		model.put("questionnaire", questionnaire);
		commonModelAndView.addObject("questionList", questionList);
		commonModelAndView.addObject("userAnswerMap", userAnswerMap);
		return commonModelAndView;
	}
}
