/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.plugins.survey.questionnaire.maintain.controller
 * FileName: QuestionnaireController.java
 * @version 1.0
 * @author zhaofeng
 * @created on 2012-5-16
 * @last Modified 
 * @history
 */
package com.sw.plugins.survey.questionnaire.maintain.controller;

import java.util.Hashtable;
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

import com.sw.core.common.Constant;
import com.sw.core.common.SystemProperty;
import com.sw.core.controller.BaseController;
import com.sw.core.controller.CommonModelAndView;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.util.CommonUtil;
import com.sw.plugins.informationcenter.message.maintain.entity.Message;
import com.sw.plugins.informationcenter.message.maintain.service.MessageService;
import com.sw.plugins.productcenter.product.maintain.entity.ProductPdf;
import com.sw.plugins.survey.question.maintain.service.QuestionService;
import com.sw.plugins.survey.questionnaire.maintain.entity.Questionnaire;
import com.sw.plugins.survey.questionnaire.maintain.service.QuestionnaireService;

/**
 *  调查问卷跳转控制类
 *  @author zhaofeng
 *  @version 1.0
 *  </pre>
 *  Created on :下午12:03:17
 *  LastModified:
 *  History:
 *  </pre>
 */

@Controller  
public class QuestionnaireController extends BaseController{
    
     private static Logger logger = Logger.getLogger(QuestionnaireService.class);
	
	@Resource
    private QuestionnaireService questionnaireService;
	
	@Resource 
	private QuestionService questionService;
	
	@Resource 
	private MessageService messageService;

	
	/**
	 *  调查问卷列表页面
	 *  @param questionnaire
	 *  @param request
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-16 下午12:10:19
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/questionnaire/maintain/list")
	   public CommonModelAndView list(Questionnaire questionnaire,HttpServletRequest request){ 
			Object obj = new CommonModelAndView().getCurrentStatus(questionnaire, request);
			if (obj != null){
				if (obj instanceof Questionnaire){
					questionnaire = (Questionnaire)obj;
				}
			} 
		   	CommonModelAndView commonModelAndView = new CommonModelAndView(request,questionnaire);
		   	commonModelAndView.addObject("code", questionnaire.getC());
		   	commonModelAndView.addObject("questionnaire", questionnaire);
	        return commonModelAndView;
	   }  
	
	@RequestMapping("/survey/questionnaire/maintain/grid")
	public CommonModelAndView json(Questionnaire questionnaire,String nodeid,HttpServletRequest request){
		questionnaire.setQueName(CommonUtil.convertSearchSign(questionnaire.getQueName()));
		questionnaire.setQueTitle(CommonUtil.convertSearchSign(questionnaire.getQueTitle()));
		Map<String, Object> map = questionnaireService.getGrid(questionnaire); 
		return new CommonModelAndView("jsonView",map);
	}
	
	  /**
	 *  跳转到创建页面
	 *  @param questionnaire
	 *  @param request
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-16 下午1:21:42
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/questionnaire/maintain/create")
	   public CommonModelAndView create(Questionnaire questionnaire,HttpServletRequest request){
			CommonModelAndView commonModelAndView = new CommonModelAndView(request,questionnaire);
	       return commonModelAndView; 
	   } 
	
	   /**
	 *  保存问卷
	 *  @param questionnaire
	 *  @param result
	 *  @param model
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-16 下午1:24:40
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping(value="/survey/questionnaire/maintain/save",method = RequestMethod.POST)
		public CommonModelAndView save(@Valid Questionnaire questionnaire,BindingResult result,Map<String,Object> model) {
			if (result.hasErrors()) {
				return new CommonModelAndView(questionnaire);
			}
			//视图名
			String viewName = null;
			try {
				questionnaireService.save(questionnaire);
				viewName = this.SUCCESS;
			} catch (Exception e) {
				logger.error(e.getMessage());
				viewName = this.ERROR;
			}
			CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,questionnaire,messageSource);
			return commonModelAndView;		
		}
	
	/**
	 *  跳转到修改问卷页面
	 *  @param questionnaire
	 *  @param request
	 *  @param model
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-16 下午1:29:14
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/questionnaire/maintain/modify")
	   public CommonModelAndView modify(Questionnaire questionnaire,HttpServletRequest request,Map<String,Object> model){
			String code = questionnaire.getC();
			Object obj = new CommonModelAndView().getCurrentStatus(questionnaire, request);
			if (obj != null){
				if (obj instanceof Questionnaire){
					questionnaire = (Questionnaire)obj;
				}
			}else{
				if (questionnaire.getId()!= null){
					try {
						questionnaire.setId(questionnaire.getId());
						questionnaire = questionnaireService.getOneById(questionnaire);
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
			}
			questionnaire.setC(code);
			CommonModelAndView commonModelAndView = new CommonModelAndView(request,questionnaire);
			model.put("questionnaire", questionnaire);
			//问卷附件图片相关信息
			if(null != questionnaire.getQueImgIpad() && !"".equals(questionnaire.getQueImgIpad())) {
				String imgIpadFileId = questionnaire.getQueImgIpad().substring(0,questionnaire.getQueImgIpad().lastIndexOf("."));
				commonModelAndView.addObject("imgIpadFileId", imgIpadFileId);
				String imgPathIpad = SystemProperty.getInstance("parameterConfig").getProperty("queImgPathIpad") ;
				commonModelAndView.addObject("imgPathIpad", imgPathIpad);
			}
			if(null != questionnaire.getQueImgIphone() && !"".equals(questionnaire.getQueImgIphone())) {
				String imgIphoneFileId = questionnaire.getQueImgIphone().substring(0,questionnaire.getQueImgIphone().lastIndexOf("."));
				commonModelAndView.addObject("imgIphoneFileId", imgIphoneFileId);
				String imgPathIphone = SystemProperty.getInstance("parameterConfig").getProperty("queImgPathIphone") ;
				commonModelAndView.addObject("imgPathIphone", imgPathIphone);
			}
			String openStatus = SystemProperty.getInstance("parameterConfig").getProperty("openStatus") ;
			if(openStatus.equals("open")){
				String synUploadUrl = SystemProperty.getInstance("parameterConfig").getProperty("uploadUrl") ;
				//资源服务器url
				commonModelAndView.addObject("synUploadUrl", synUploadUrl);
			}
			commonModelAndView.addObject("openStatus", openStatus);
	        return commonModelAndView; 
	   } 	
	
	/**
	 *  更新调查问卷
	 *  @param questionnaire
	 *  @param result
	 *  @param model
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-16 下午1:32:59
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping(value="/survey/questionnaire/maintain/update",method = RequestMethod.POST)
	public CommonModelAndView update(@Valid Questionnaire questionnaire,BindingResult result,Map<String,Object> model) {
		if (result.hasErrors()) {
			return new CommonModelAndView(questionnaire);
		}
		//视图名
		String viewName = null;
		try {
			questionnaireService.update(questionnaire);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,questionnaire,messageSource);
		return commonModelAndView;		
	}
	
	/**
	 *  删除调查问卷
	 *  @param questionnaire
	 *  @param request
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-16 下午1:35:44
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/questionnaire/maintain/delete")
    public CommonModelAndView delete(Questionnaire questionnaire,HttpServletRequest request){
		String viewName = null;		
		try {
			if(questionnaire!=null && questionnaire.getId()!=null){
				questionnaireService.deletQue(questionnaire,request);
			}else if(questionnaire!=null && questionnaire.getIds()!=null){
				questionnaireService.deleteByArrAndImg(questionnaire,request);
			}
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,questionnaire,messageSource);
		return commonModelAndView;	
    }  
	
	/**
	 *  跳转到设计问卷页面
	 *  @param questionnaire
	 *  @param request
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-16 下午6:52:28
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/questionnaire/maintain/operate")
	public  CommonModelAndView operate(Questionnaire questionnaire,HttpServletRequest request,Map<String,Object> model){
		
		String code = questionnaire.getC();
		Object obj = new CommonModelAndView().getCurrentStatus(questionnaire, request);
		if (obj != null){
			if (obj instanceof Questionnaire){
				questionnaire = (Questionnaire)obj;
			}
		}else{
			if (questionnaire.getId()!= null){
				try {
					 List questionList = questionService.selectQuestions(questionnaire);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		}
		questionnaire.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,questionnaire);
		model.put("questionnaire", questionnaire);
        return commonModelAndView; 
	}
	
	
	/**
	 *  跳转到调查问卷设计页面
	 *  @param questionnaire
	 *  @param request
	 *  @param model
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-17 下午3:58:01
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/questionnaire/maintain/design")
	public  CommonModelAndView design(Questionnaire questionnaire,HttpServletRequest request,Map<String,Object> model){
		String code = questionnaire.getC();
			if (questionnaire.getId()!= null){
				try {
					questionnaire.setId(questionnaire.getId());
					questionnaire = questionnaireService.getOneById(questionnaire);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		//查询问卷调查对应的问题集合
		
		List questionList = questionService.selectQuestions(questionnaire);
        //转换为json格式，在页面初始化是插入到列表控件中
		String gridJsonData = JSONArray.fromObject(questionList).toString();
		questionnaire.setC(code);
		CommonModelAndView commonModelAndView =  new CommonModelAndView(request,questionnaire);
		model.put("questionnaire", questionnaire);
		commonModelAndView.addObject("gridJsonData",gridJsonData);
        return commonModelAndView; 
	}
	
	/**
	 *  发布问卷
	 *  @param questionnaire
	 *  @param request
	 *  @param model
	 *  @return
	 *  @author  zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-28 下午4:14:07
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/questionnaire/maintain/questionnairerelease")
	public  CommonModelAndView release(Questionnaire questionnaire,HttpServletRequest request,Map<String,Object> model){
		String viewName = null;	
		//发布问卷，建立答案表
		this.questionnaireService.release(questionnaire);
		questionnaire.setOpenFlag(Constant.QUE_QUESTIONNAIRE_OPEN_FLAG_TRUE);
		try {
			//修改问卷状态，把发布状态改为1
			this.questionnaireService.setQuestionnaireOpenFlag(questionnaire);
			viewName = this.SUCCESS;	
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,questionnaire,messageSource);
        return commonModelAndView; 
	}
	
	/**
	 *  查询问卷包含的问题数
	 *  @param questionnaire
	 *  @param nodeid
	 *  @param request
	 *  @return
	 *  @author 
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-29 下午4:00:02
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/questionnaire/maintain/countquequestion.json")
	public CommonModelAndView countQueQuestions(Questionnaire questionnaire,String nodeid,HttpServletRequest request){
		Long record = this.questionnaireService.countQueQuestions(questionnaire);
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("questionRecord", record);
		return new CommonModelAndView("jsonView",map);
	}
	
	/**
	 *  查看调查结果
	 *  @param questionnaire
	 *  @param nodeid
	 *  @param request
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-30 下午4:58:14
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/questionnaire/maintain/viewresult")
	public CommonModelAndView viewResult(Questionnaire questionnaire,String nodeid,HttpServletRequest request,Map<String,Object> model){
		String code = questionnaire.getC();
		//从list页面获取问卷回复数 modify by lizf 2012-06-29 改为从数据库查询
		//Long responseNum = questionnaire.getResponseNum(); 
		Long responseNum = this.questionnaireService.selectQuestionnaireResponseNum(questionnaire);
		if (questionnaire.getId() != null) {
			try {
				questionnaire.setId(questionnaire.getId());
				questionnaire = questionnaireService.getOneById(questionnaire);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		// 查询问卷调查对应的问题集合,集合中包含问题回复数，和选项回复数
		List questionList = questionService.selectQuestionResults(questionnaire);
		questionnaire.setC(code);
		//设置回复数
		questionnaire.setResponseNum(responseNum);

		CommonModelAndView commonModelAndView = new CommonModelAndView(request,
				questionnaire);
		model.put("questionnaire", questionnaire);
		commonModelAndView.addObject("questionList", questionList);
		return commonModelAndView;
	}
	
	/**
	 *  清空问卷对应的答案数据
	 *  @param questionnaire
	 *  @param request
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-6-1 上午10:15:21
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/questionnaire/maintain/deleteresult")
    public CommonModelAndView deleteResult(Questionnaire questionnaire,HttpServletRequest request){
		String viewName = null;		
		try {
			//删除答案表
			questionnaireService.deleteQuestionnaireAnswer(questionnaire);
			//删除用户参与的记录
			questionnaireService.deleteUserResult(questionnaire);
			//把问卷调查状态置为未发布
			questionnaire.setOpenFlag(Constant.QUE_QUESTIONNAIRE_OPEN_FLAG_FALSE);
			questionnaireService.setQuestionnaireOpenFlag(questionnaire);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,questionnaire,messageSource);
		return commonModelAndView;	
    }
	
	/**
	 *  跳转到参与问卷用户列表
	 *  @param questionnaire
	 *  @param nodeid
	 *  @param request
	 *  @param model
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-7-13 下午1:51:57
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/questionnaire/maintain/userresult")
	public CommonModelAndView userResult(Questionnaire questionnaire,String nodeid,HttpServletRequest request,Map<String,Object> model){
		Object obj = new CommonModelAndView().getCurrentStatus(questionnaire, request);
		if (obj != null){
			if (obj instanceof Questionnaire){
				questionnaire = (Questionnaire)obj;
			}
		} 
		questionnaire.setId(Long.valueOf( request.getParameter("id")));
	   	CommonModelAndView commonModelAndView = new CommonModelAndView(request,questionnaire);
	   	commonModelAndView.addObject("code", questionnaire.getC());
	   	commonModelAndView.addObject("questionnaire", questionnaire);
        return commonModelAndView;
	}
	@RequestMapping("/survey/questionnaire/maintain/userresultgrid")
	public CommonModelAndView getUserResultGrid(Questionnaire questionnaire,String nodeid,HttpServletRequest request){
		questionnaire.setMemberName(CommonUtil.convertSearchSign(questionnaire.getMemberName()));
		Map<String, Object> map = questionnaireService.getUserResult(questionnaire); 
		return new CommonModelAndView("jsonView",map);
	}
	/**
	 *  把问卷作为消息推送到消息模块
	 *  @param questionnaire
	 *  @param nodeid
	 *  @param request
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-7-20 上午9:23:04
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/questionnaire/maintain/sendmessage")
	public CommonModelAndView sendMessage(Questionnaire questionnaire,String nodeid,HttpServletRequest request){
		String code = questionnaire.getC();

		try {
			questionnaire.setId(questionnaire.getId());
			questionnaire = questionnaireService.getOneById(questionnaire);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		questionnaire.setC(code); 
		//保存为消息
		Message message = new Message();
		;
		//message.setMsgTitle(SystemProperty.getInstance("parameterConfig").getProperty("queMessageTitle").replace("{0}", questionnaire.getQueTitle()));
		//message.setMsgContent(SystemProperty.getInstance("parameterConfig").getProperty("queMessageContent").replace("{0}", questionnaire.getQueTitle()));
		message.setMsgTitle(questionnaire.getQueTitle());
		message.setSourceId(questionnaire.getId());
		message.setMsgFrom(12);
		message.setSourceType(3);
		
		try {
			messageService.save(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("sendFlag", "success");
		return new CommonModelAndView("jsonView", map);
	}




	/**
	 *上传调查的图片
	 */
	@RequestMapping("/survey/questionnaire/maintain/uploadfile")
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		return questionnaireService.upload(baseEntity, request);
	}
	/**
	 *  删除调查文件的图片
	 *  @param baseEntity
	 *  @param request
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-9-13 上午12:19:43
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/survey/questionnaire/maintain/deleteImgFile")
	public CommonModelAndView deleteImgFile(BaseEntity baseEntity,HttpServletRequest request){
		questionnaireService.deleteQeuImg(request);
		Map<String, Object> map =  new Hashtable<String, Object>();
		return new CommonModelAndView("jsonView",map);
	}
}
