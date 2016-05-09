package com.sw.plugins.informationcenter.message.maintain.controller;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sw.core.common.Constant;
import com.sw.core.controller.BaseController;
import com.sw.core.controller.CommonModelAndView;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.util.CommonUtil;
import com.sw.plugins.clientcenter.member.maintain.entity.Member;
import com.sw.plugins.clientcenter.member.maintain.service.MemberService;
import com.sw.plugins.informationcenter.message.maintain.entity.Message;
import com.sw.plugins.informationcenter.message.maintain.entity.SendRecord;
import com.sw.plugins.informationcenter.message.maintain.service.MessageService;
import com.sw.plugins.informationcenter.message.maintain.service.SendRecordService;
import com.sw.plugins.usercenter.config.dynamic.entity.Dictionary;
import com.sw.plugins.usercenter.config.dynamic.service.DictionaryService;

/**
 *  消息管理
 *  @author Haoyuanfu
 *  @version 1.0
 *  </pre>
 *  Created on :2012-4-27 10:45:26
 *  LastModified:
 *  History:
 *  </pre>
 */
@Controller  
public class MessageController extends BaseController{
	
	private static Logger logger = Logger.getLogger(MessageController.class);
	
   @Resource
   private MessageService messageService;
   @Resource
   private DictionaryService dictionaryService;
   @Resource
   private MemberService memberService;
   @Resource
   private SendRecordService sendRecordService;
   
/**
 *  消息列表页面
 *  @param message
 *  @param request
 *  @return CommonModelAndView
 *  @author haoyuanfu
 *  @version 1.0
 *  </pre>
 *  Created on :2012-4-27 上午11:21:43
 *  LastModified:
 *  History:
 *  </pre>
 */


@RequestMapping("/informationcenter/message/maintain/list")
   public CommonModelAndView list(Message message,HttpServletRequest request){ 
		Object obj = new CommonModelAndView().getCurrentStatus(message, request);
		if (obj != null){
			if (obj instanceof Message){
				message = (Message)obj;
			}
		} 
	   	CommonModelAndView commonModelAndView = new CommonModelAndView(request,message);
	   	commonModelAndView.addObject("code", message.getC());
	   	try {
			Dictionary dictionary = new Dictionary();
			//下拉框显示内容
    		dictionary.setDictSortValue(Constant.MSG_TYPE_MSGSOURCE); //消息来源
    		List<Dictionary> msgSourceList = dictionaryService.getList(dictionary);
    		commonModelAndView.addObject("msgSourceList", msgSourceList);
    		dictionary = new Dictionary();
    		dictionary.setDictSortValue(Constant.MSG_TYPE_AUDITINGSTATE); //审核状态
    		List<Dictionary> auditingStateList = dictionaryService.getList(dictionary);
    		commonModelAndView.addObject("auditingStateList", auditingStateList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	   	commonModelAndView.addObject("message", message);
       return commonModelAndView;
   }  
   	

/**
 *  跳转到消息创建页面
 *  @param message
 *  @param request
 *  @return
 *  @author haoyuanfu
 *  @version 1.0
 *  </pre>
 *  Created on :2012-4-27 下午02:34:56
 *  LastModified:
 *  History:
 *  </pre>
 */
   @RequestMapping("/informationcenter/message/maintain/create")
   public CommonModelAndView create(Message message,HttpServletRequest request){
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,message);
       return commonModelAndView; 
   }  

	/**
	 *  跳转到消息修改页面
	 *  @param messsage
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-4-28 上午10:55:08
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/informationcenter/message/maintain/modify")
   public CommonModelAndView modify(Message message,HttpServletRequest request,Map<String,Object> model){
		String code = message.getC();
		if(message.getId()!=null)
			message.setMsgId(message.getId());
		if (message.getMsgId() != null){
			try {
				Message m = new Message();
				m.setMsgId(message.getMsgId());
				message = messageService.getOneById(m);
				message.setContent(message.getMsgContent());
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		message.setC(code);
		message.setId(message.getMsgId());
		Dictionary dictionary = new Dictionary();
		dictionary.setDictSortValue(Constant.MSG_TYPE_MSGSOURCE);  //消息来源类型
		Map<Long,Dictionary> msgSourceMap = dictionaryService.getDictMap(dictionary);
		message.setMsgFromChn(msgSourceMap.get((long)message.getMsgFrom()).getName());
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,message);
		model.put("message", message);
		commonModelAndView.addObject("message",message);
		
       return commonModelAndView;
   } 	
   
	/**
	 * 用户删除功能，json格式
	 * @param role
	 * @param request
	 * @return
	 */
	@RequestMapping("/informationcenter/message/maintain/delete")
    public CommonModelAndView delete(Message message,HttpServletRequest request){
		//视图名
		String viewName = "";		
		try {
			if(message.getMsgTitle()!=null &&!equals(message.getMsgTitle())){
				String msgTitle = URLDecoder.decode(message.getMsgTitle(), Constant.ENCODING);
				message.setMsgTitle(msgTitle);
			}
			//按照IDS批量置删除标记为1
			messageService.deleteByArr(message);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, message, messageSource);
		return commonModelAndView;		
    }  
 /**
 *  保存消息
 *  @param message
 *  @param result
 *  @param model
 *  @return
 *  @author Administrator
 *  @version 1.0
 *  </pre>
 *  Created on :2012-4-27 下午01:53:49
 *  LastModified:
 *  History:
 *  </pre>
 */
   @RequestMapping(value="/informationcenter/message/maintain/save",method = RequestMethod.POST)
	public CommonModelAndView save(@Valid Message message,BindingResult result,Map<String,Object> model) {
		if (result.hasErrors()) {
			return new CommonModelAndView(message);
		}
		//视图名
		String viewName = null;
		try {
			if(message != null && message.getMsgId() != null){
				if(!message.getContent().equals("default")){
					message.setMsgContent(message.getContent());
				}
				messageService.update(message);
			}else{				
				message.setMsgContent(message.getContent());
				messageService.save(message);
			}
			viewName = this.SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,message,messageSource);
		return commonModelAndView;		
	}
   
   @RequestMapping("/informationcenter/message/maintain/*details")
   public CommonModelAndView details(Message message,HttpServletRequest request,Map<String,Object> model){
		String code = message.getC();
		Object obj = new CommonModelAndView().getCurrentStatus(message, request);
		if (obj != null){
			if (obj instanceof Message){
				message = (Message)obj;
			}
		}else{
			if (message.getId()!= null){
				try {
					message.setMsgId(message.getId());
					message = messageService.getOneById(message);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		}
			
		message.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,message);
		commonModelAndView.setViewName("/informationcenter/message/maintain/details");
		commonModelAndView.addObject("message", message);
		model.put("message", message);
		Dictionary dictionary = new Dictionary();
		dictionary.setDictSortValue(Constant.MSG_TYPE_MSGSOURCE);  //消息来源类型
		Map<Long,Dictionary> msgSourceMap = dictionaryService.getDictMap(dictionary);
		commonModelAndView.addObject("msgFromChn",msgSourceMap.get((long)message.getMsgFrom()).getName());
       return commonModelAndView; 
   } 	

	/**
	 *   消息列表展示方法
	 *  @param message
	 *  @param nodeid
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-4-27 下午01:22:37
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/informationcenter/message/maintain/grid")
	public CommonModelAndView json(Message message,String nodeid,HttpServletRequest request){
		message.setMsgTitle(CommonUtil.convertSearchSign(message.getMsgTitle()));
		Map<String, Object> map = messageService.getGrid(message); 
		return new CommonModelAndView("jsonView",map);
	}
	
	/**
	 *  发送列表显示json
	 *  @param member
	 *  @param request
	 *  @return
	 *  @author Administrator
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-7-2 上午10:03:59
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/informationcenter/message/maintain/sendgrid")
	public CommonModelAndView sendjson(Member member,HttpServletRequest request){
		//会员状态为开启
		member.setStatus(Long.parseLong(Constant.MEMBER_STATUS_START));
		//转换查询时会员姓名中的%
		member.setMemberName(CommonUtil.convertSearchSign(member.getMemberName()));
		Map<String, Object> map = memberService.getGridForSendMessage(member); 
		return new CommonModelAndView("jsonView",map); 
	}
	
	/**
	 *  待审核消息列表
	 *  @param message
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-3 下午08:13:22
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/informationcenter/message/maintain/auditinglist")
	   public CommonModelAndView auditinglist(Message message,HttpServletRequest request){ 
			Object obj = new CommonModelAndView().getCurrentStatus(message, request);
			if (obj != null){
				if (obj instanceof Message){
					message = (Message)obj;
				}
			} 
	   	CommonModelAndView commonModelAndView = new CommonModelAndView(request,message);
	   	commonModelAndView.addObject("code", message.getC());
	   	try {
			Dictionary dictionary = new Dictionary();
			//下拉框显示内容
    		dictionary.setDictSortValue(Constant.MSG_TYPE_MSGSOURCE); //消息来源
    		List<Dictionary> msgSourceList = dictionaryService.getList(dictionary);
    		commonModelAndView.addObject("msgSourceList", msgSourceList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	   	commonModelAndView.addObject("message", message);
	       return commonModelAndView;
	   }  

	/**
	 *  已审核消息列表
	 *  @param message
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-3 下午08:12:58
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/informationcenter/message/maintain/auditedlist")
	public CommonModelAndView auditedlist(Message message,
			HttpServletRequest request) {
		Object obj = new CommonModelAndView()
				.getCurrentStatus(message, request);
		if (obj != null) {
			if (obj instanceof Message) {
				message = (Message) obj;
			}
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,
				message);
		commonModelAndView.addObject("code", message.getC());
		try {
			Dictionary dictionary = new Dictionary();
			//下拉框显示内容
    		dictionary.setDictSortValue(Constant.MSG_TYPE_MSGSOURCE); //消息来源
    		List<Dictionary> msgSourceList = dictionaryService.getList(dictionary);
    		commonModelAndView.addObject("msgSourceList", msgSourceList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	   	commonModelAndView.addObject("message", message);
		return commonModelAndView;
	}  
	
	/**
	 *  消息发送列表
	 *  @param message
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-3 下午08:02:08
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/informationcenter/message/maintain/sendlist")
	public CommonModelAndView sendlist(Member member,Message message,HttpServletRequest request,Map<String,Object> model) {	
		Object obj1 = new CommonModelAndView().getCurrentStatus(message, request);
		if (obj1 != null){
			if (obj1 instanceof Message){
				message = (Message)obj1;
			}
		}else{
			if (message.getId()!= null){
				try {
					message.setMsgId(message.getId());
					message = messageService.getOneById(message);
					member.setLevel((long)100);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		}
		//根据页面传回的MSGID查询当前所发送的消息
		if(member.getMsgId()!=null){
			try {
				message.setId(member.getMsgId());
				message.setMsgId(member.getMsgId());
				message = messageService.getOneById(message);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		if(member!=null&&member.getIdsString()!=null){
			try{
			//将用户的IDS转换成为String类型返回到界面上
			String memberIds = messageService.getMemberIdsToStr(member);
			member.setIdsString(memberIds);
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
		Object obj = new CommonModelAndView().getCurrentStatus(member, request);
		if (obj != null){
			if (obj instanceof Member){
				member = (Member)obj;
			}
		} 
		member.setDelStatus(Constant.MEMBER_DELSTATUS_NORMAL);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,member);
		try {
			Dictionary dictionary = new Dictionary();
    		dictionary.setDictSortValue(Constant.DICT_TYPE_LEVEL);
    		List<Dictionary> levelList = dictionaryService.getList(dictionary);
    		commonModelAndView.addObject("levelList", levelList);
    		dictionary = new Dictionary();
    		dictionary.setDictSortValue(Constant.DICT_TYPE_MEMBERSTATUS);
    		List<Dictionary> statusList = dictionaryService.getList(dictionary);
    		commonModelAndView.addObject("statusList", statusList);
    		commonModelAndView.addObject("statusStart", Constant.MEMBER_STATUS_START);
    		commonModelAndView.addObject("statusStop", Constant.MEMBER_STATUS_STOP);
    		dictionary = new Dictionary();
    		dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
    		List<Dictionary> sexList = dictionaryService.getList(dictionary);
    		commonModelAndView.addObject("sexList", sexList);
    		dictionary = new Dictionary();
    		dictionary.setDictSortValue(Constant.DICT_TYPE_DATASOURCE);
    		List<Dictionary> sourceList = dictionaryService.getList(dictionary);
    		commonModelAndView.addObject("sourceList", sourceList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		commonModelAndView.addObject("code", member.getC());
//		member.setSelectValues(request.getParameter("selectValues"));
//		commonModelAndView.addObject("selectValues",request.getParameter("selectValues"));
		model.put("member", member);
		commonModelAndView.addObject("message", message);
        return commonModelAndView;
	}
	
	/**
	 *  消息审核
	 *  @param message
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-4 上午11:15:40
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/informationcenter/message/maintain/audit")
	public CommonModelAndView audit(Message message,HttpServletRequest request,Map<String,Object> model){
		String code = message.getC();
		if(message.getId()!=null)
			message.setMsgId(message.getId());
		if (message.getMsgId() != null){
			try {
				Message m = new Message();
				m.setMsgId(message.getMsgId());
				message = messageService.getOneById(m);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		message.setC(code);
		message.setId(message.getMsgId());
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,message);
		//审核时可查看消息内容
		//commonModelAndView.addObject("message", message);
		model.put("message", message);
		Dictionary dictionary = new Dictionary();
		dictionary.setDictSortValue(Constant.MSG_TYPE_MSGSOURCE);  //消息来源类型
		Map<Long,Dictionary> msgSourceMap = dictionaryService.getDictMap(dictionary);
		commonModelAndView.addObject("msgFromChn",msgSourceMap.get((long)message.getMsgFrom()).getName());
		return commonModelAndView;
	}
	
	/**
	 *  消息回收列表
	 *  @param message
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-7 下午01:24:42
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/informationcenter/message/maintain/recycledlist")
	public CommonModelAndView recycledlist(Message message,HttpServletRequest request) {
		Object obj = new CommonModelAndView().getCurrentStatus(message, request);
		if (obj != null) {
			if (obj instanceof Message) {
				message = (Message) obj;
			}
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,message);
		commonModelAndView.addObject("code", message.getC());
		try {
			Dictionary dictionary = new Dictionary();
			//下拉框显示内容
    		dictionary.setDictSortValue(Constant.MSG_TYPE_MSGSOURCE); //消息来源
    		List<Dictionary> msgSourceList = dictionaryService.getList(dictionary);
    		commonModelAndView.addObject("msgSourceList", msgSourceList);
    		dictionary = new Dictionary();
    		dictionary.setDictSortValue(Constant.MSG_TYPE_AUDITINGSTATE); //审核状态
    		List<Dictionary> auditingStateList = dictionaryService.getList(dictionary);
    		commonModelAndView.addObject("auditingStateList", auditingStateList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	   	commonModelAndView.addObject("message", message);
		return commonModelAndView;
	}
	
	/**
	 *  回收站——删除功能
	 *  @param message
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-7 下午01:29:21
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/informationcenter/message/maintain/tdelete")
    public CommonModelAndView tdelete(Message message,HttpServletRequest request){
		//视图名
		String viewName = "";		
		try {
			if(message.getMsgTitle()!=null &&!equals(message.getMsgTitle())){
				String msgTitle = URLDecoder.decode(message.getMsgTitle(), Constant.ENCODING);
				message.setMsgTitle(msgTitle);
			}
			//按IDS删除消息(真删除)
			messageService.deleteByArrTrue(message);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, message, messageSource);
		return commonModelAndView;		
    }  
	
	/**
	 *  消息回收——消息还原
	 *  @param message
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-7 下午01:36:27
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/informationcenter/message/maintain/restore")
    public CommonModelAndView restore(Message message,HttpServletRequest request){
		//视图名
		String viewName = "";		
		try {
			if(message.getMsgTitle()!=null &&!equals(message.getMsgTitle())){
				String msgTitle = URLDecoder.decode(message.getMsgTitle(), Constant.ENCODING);
				message.setMsgTitle(msgTitle);
			}
			//按IDS还原消息
			messageService.restoreByArr(message);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, message, messageSource);
		return commonModelAndView;		
    }  
	
	
	/**
	 *  消息发送-消息发送
	 *  @param message
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-8 下午01:47:31
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/informationcenter/message/maintain/sending")
    public CommonModelAndView sending(Message message,Member member,HttpServletRequest request){
		//视图名
		String viewName = "";	
		String status =null;
		Long msgId = Long.parseLong(request.getParameter("msgId"));
		try {
			message.setMsgId(msgId);
			if(message.getSendApp()!=null&&message.getSendApp().equals("1")){
				status = messageService.pushData(message, member, request);
			}	
			//按IDS发送消息信息
			messageService.sendByArr(message, member);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		if(status != null){
			viewName = "redirect:/fail";
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, message, messageSource);
		return commonModelAndView;		
    }

	/**
	 *  消息发送_---发送所有用户
	 *  @param message
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-8 下午01:47:31
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/informationcenter/message/maintain/sendall")
    public CommonModelAndView sendall(Message message,Member member,HttpServletRequest request){
		//视图名
		String viewName = "";	
		Long msgId = Long.parseLong(request.getParameter("msgId"));
		try {
			//按IDS发送消息信息
			message.setMsgId(msgId);
			String[] memberIds = messageService.getMemberIds(member);
			member.setIds(memberIds);
			if(message.getSendApp().equals("1")){
				messageService.pushData(message, member, request);
			}
			messageService.sendByArr(message, member);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, message, messageSource);
		return commonModelAndView;		
    }
	
	
	/**
	 *  消息发送——按检索条件发送
	 *  @param message
	 *  @param member
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-9 下午07:31:04
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/informationcenter/message/maintain/sendby")
    public CommonModelAndView sendby(Message message,Member member,HttpServletRequest request){
		//视图名
		String viewName = "";	
		try {
			//按IDS发送消息信息
			message.setMsgId(member.getMsgId());
			if(!member.getIdsString().equals("") && member.getIdsString() != null ){
				String[] memberIds = messageService.getMemberIdsToArray(member);
				member.setIds(memberIds);
				if(message.getSendApp()!=null&&message.getSendApp().equals("1")){
					messageService.pushData(message, member, request);
				}
				messageService.sendByArr(message, member);
			}
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, member, messageSource);
		return commonModelAndView;		
    }

	
	/**
	 *   文件上传
	 *  @param message
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-8 下午01:47:31
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */

	@RequestMapping("/informationcenter/message/maintain/import")
	public CommonModelAndView uploadfile(Member member,Message message, HttpServletRequest request) {
		
		//视图名
		String viewName = "";		
		String memberIdsString = "";
		Long msgId = Long.parseLong(request.getParameter("msgId"));
		try {
			//按IDS发送消息信息
			message.setMsgId(msgId);
			String[] memberIds = messageService.getMemberIds(member);
			member.setIds(memberIds);
			memberIdsString = messageService.sendByExcel(message, member);
		/*	member.setIdsString(memberIdsString);
			String[] memberIdsArray=memberIdsString.split(",");
			member.setIds(memberIdsArray);*/
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		viewName = "/informationcenter/message/maintain/sendlist";
		//request.setAttribute("ids", memberIdsString);
		//CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, member, messageSource);
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName);
		commonModelAndView.addObject("member",member);
		return commonModelAndView;
		
	}
	
	/**
	 *  跳转到发送记录列表页面
	 *  @param sendRecord
	 *  @param request
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-8 下午3:55:46
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/informationcenter/message/maintain/sendrecordlist")
	public CommonModelAndView sendRecordList(SendRecord sendRecord,HttpServletRequest request){
		Object obj = new CommonModelAndView().getCurrentStatus(sendRecord, request);
		if (obj != null){
			if (obj instanceof SendRecord){
				sendRecord = (SendRecord)obj;
			}
		} 
		
	   	CommonModelAndView commonModelAndView = new CommonModelAndView(request,sendRecord);
	   	try{
	   	Dictionary dictionary = new Dictionary();
		//下拉框显示内容
		dictionary.setDictSortValue(Constant.MSG_TYPE_MSGSOURCE); //消息来源
		List<Dictionary> msgSourceList = dictionaryService.getList(dictionary);
		commonModelAndView.addObject("msgSourceList", msgSourceList);
	   	}catch(Exception e){
	   		logger.error(e.getMessage());
	   	}
	   	commonModelAndView.addObject("sendRecord", sendRecord);
	   	
        return commonModelAndView;
	}
	/**
	 *  发送记录列表数据读取方法
	 *  @param sendRecord
	 *  @param request
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-9 下午4:49:57
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/informationcenter/message/maintain/sendrecordgrid")
	public CommonModelAndView sendRecordGrid(SendRecord sendRecord,HttpServletRequest request){
		sendRecord.setMsgTitle(CommonUtil.convertSearchSign(sendRecord.getMsgTitle()));
		Map<String, Object> map = sendRecordService.getGrid(sendRecord);
		return new CommonModelAndView("jsonView",map);
	}
	/**
	 *  跳转到消息已发送用户列表
	 *  @param sendRecord
	 *  @param request
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-9 下午4:51:40
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/informationcenter/message/maintain/sendusers")
	public CommonModelAndView sendUsersList(SendRecord sendRecord,HttpServletRequest request){
		Object obj = new CommonModelAndView().getCurrentStatus(sendRecord, request);
		if (obj != null){
			if (obj instanceof SendRecord){
				sendRecord = (SendRecord)obj;
			}
		} 
	   	CommonModelAndView commonModelAndView = new CommonModelAndView(request,sendRecord);
	   	commonModelAndView.addObject("sendRecord", sendRecord);
        return commonModelAndView;
	}
	/**
	 *  消息已发送用户列表数据读取
	 *  @param sendRecord
	 *  @param request
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-9 下午4:52:43
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/informationcenter/message/maintain/sendusersgrid")
	public CommonModelAndView sendUsersGrid(SendRecord sendRecord,HttpServletRequest request){
		sendRecord.setMemberName(CommonUtil.convertSearchSign(sendRecord.getMemberName()));
		Map<String, Object> map = sendRecordService.getSendUsersGrid(sendRecord);
		return new CommonModelAndView("jsonView",map);
	}
	
	
	/**
	 *  记录删除
	 *  @param sendRecord
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-21 下午12:41:51
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/informationcenter/message/maintain/recorddel")
    public CommonModelAndView recorddel(SendRecord sendRecord,HttpServletRequest request){
		//视图名
		String viewName = "";		
		try {
			
			//按IDS删除消息(真删除)
			sendRecordService.delete(sendRecord);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, sendRecord, messageSource);
		return commonModelAndView;		
    }  


	@Override
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	

    /**
     *  开打上传页面
     *  @param iframeId
     *  @param controllerName
     *  @param fileTypeDesc
     *  @param fileTypeExts
     *  @param queueSizeLimit
     *  @param uploadLimit
     *  @param inputIds
     *  @param imgIds
     *  @param request
     *  @return
     *  @author haoyuanfu
     *  @version 1.0
     *  </pre>
     *  Created on :2012-6-7 下午01:54:08
     *  LastModified:
     *  History:
     *  </pre>
     */
/*    @RequestMapping("/uploadExcel")
    public CommonModelAndView uploadExcel(String iframeId,String controllerName,String fileTypeDesc,String fileTypeExts,String queueSizeLimit,String uploadLimit,String inputIds,String imgIds,HttpServletRequest request){ 
    	CommonModelAndView commonModelAndView = new CommonModelAndView(this.UPLOAD);  
    	commonModelAndView.addObject("controllerName", controllerName);
    	commonModelAndView.addObject("iframeId", iframeId);
    	commonModelAndView.addObject("fileTypeDesc", fileTypeDesc);
    	commonModelAndView.addObject("fileTypeExts", fileTypeExts);
    	commonModelAndView.addObject("queueSizeLimit", queueSizeLimit);
    	commonModelAndView.addObject("uploadLimit", uploadLimit);
    	commonModelAndView.addObject("inputIds", inputIds);
    	commonModelAndView.addObject("imgIds", imgIds);
        return commonModelAndView;    
    }*/
}
