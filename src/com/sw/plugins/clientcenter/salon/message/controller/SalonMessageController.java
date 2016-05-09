package com.sw.plugins.clientcenter.salon.message.controller;

import java.net.URLDecoder;
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
import com.sw.plugins.clientcenter.salon.message.entity.SalonMessage;
import com.sw.plugins.clientcenter.salon.message.service.SalonMessageService;

/**
 * 理财沙龙消息控制器，负责理财沙龙消息的添加，修改，删除，查询等功能
 * @author Administrator
 *
 */
@Controller
public class SalonMessageController extends BaseController {

	private static Logger logger = Logger.getLogger(SalonMessageController.class);
	
	@Resource
	private SalonMessageService salonMessageService;
	
	/**
     * 理财沙龙消息列表方法
     * @param code
     * @param request
     * @return
     */
	@RequestMapping("/clientcenter/salon/message/list")
    public CommonModelAndView list(SalonMessage salonMessage,HttpServletRequest request,Map<String,Object> model){ 
		Object obj = new CommonModelAndView().getCurrentStatus(salonMessage, request);
		if (obj != null){
			if (obj instanceof SalonMessage){
				salonMessage = (SalonMessage)obj;
			}
		} 
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, salonMessage);
		commonModelAndView.addObject("code", salonMessage.getC());
		//从字典里获取理财沙龙消息状态列表
		commonModelAndView.addObject("salonMessage", salonMessage);
	    commonModelAndView.addObject("messageStatusList", salonMessageService.getMessageStatusList());
        return commonModelAndView;
    }
	
	/**
	 * 跳转到理财沙龙消息创建页面
	 * @param code
	 * @param request
	 * @return
	 * @author 
	 */
	@RequestMapping("/clientcenter/salon/message/create")
    public CommonModelAndView create(SalonMessage salonMessage,HttpServletRequest request){
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, salonMessage);
		commonModelAndView.addObject("salonMessage", salonMessage);
		commonModelAndView.addObject("messageStatusList", salonMessageService.getMessageStatusList());
        return commonModelAndView; 
    } 
	
	/**
	 * 理财沙龙消息创建操作
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/clientcenter/salon/message/save",method = RequestMethod.POST)
	public CommonModelAndView save(@Valid SalonMessage salonMessage,BindingResult result,Map<String,Object> model) {
		CommonModelAndView commonModelAndView = null;
		if (result.hasErrors()) {
			commonModelAndView = new CommonModelAndView(salonMessage);
			commonModelAndView.addObject("messageStatusList", salonMessageService.getMessageStatusList());
			return commonModelAndView;
		}
		//视图名
		String viewName = null;
		try {
			if(salonMessage != null && salonMessage.getId() != null){
				salonMessageService.update(salonMessage);
			}else{
				salonMessageService.save(salonMessage);
			}
			//如果消息为启用状态，就把消息推送出去
			if(salonMessage.getStatus() == 1){
				salonMessage.setId(salonMessage.getGeneratedKey());
				salonMessageService.pushMessage(salonMessage);
			}
			viewName = this.SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}
		commonModelAndView = new CommonModelAndView(viewName,salonMessage,messageSource);
		return commonModelAndView;		
	}
	
	/**
	 * 跳转到理财沙龙消息修改页面
	 * @param code
	 * @param salonMessage
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/salon/message/modify")
    public CommonModelAndView modify(SalonMessage salonMessage,HttpServletRequest request,Map<String,Object> model){
		String code = salonMessage.getC();
		if (salonMessage.getId() != null) {
			try {
				SalonMessage message = new SalonMessage();
				message.setId(salonMessage.getId());
				salonMessage = salonMessageService.getOneById(message);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		salonMessage.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,salonMessage);
		commonModelAndView.addObject("salonMessage", salonMessage);
		model.put("salonMessage", salonMessage);
		commonModelAndView.addObject("messageStatusList", salonMessageService.getMessageStatusList());
        return commonModelAndView; 
    }
	
	/**
	 * 跳转理财沙龙消息详细页面
	 * @param code
	 * @param salonMessage
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/salon/message/detail")
    public CommonModelAndView detail(SalonMessage salonMessage,HttpServletRequest request,Map<String,Object> model){
		String code = salonMessage.getC();
		try {
			salonMessage = salonMessageService.getOneById(salonMessage);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		salonMessage.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, salonMessage); 
		commonModelAndView.addObject("salonMessage", salonMessage);
		model.put("salonMessage", salonMessage);
        return commonModelAndView; 
    }
	

	/**
	 * 消息启用，json格式
	 * @param code
	 * @param salonMessage
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/salon/message/start")
    public CommonModelAndView start(SalonMessage salonMessage,HttpServletRequest request){
		//视图名
		String viewName = null;
		try {
			String code = salonMessage.getC();
			String title = URLDecoder.decode(salonMessage.getTitle(), Constant.ENCODING);
			SalonMessage message = salonMessageService.getOneById(salonMessage);
			//推送消息
			salonMessageService.pushMessage(message);
			//把消息更新到开启状态
			String startStatus = salonMessageService.getDictValueByMessageStatus(Long.parseLong(Constant.SALON_MESSAGE_START));
			message.setStatus(Long.parseLong(startStatus));
			salonMessageService.update(message);
			salonMessage.setC(code);
			salonMessage.setTitle(title);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, salonMessage, messageSource);
        return commonModelAndView; 
    }
	
	/**
	 * 理财沙龙消息删除功能，json格式
	 * @param consult
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/salon/message/delete")
    public CommonModelAndView delete(SalonMessage salonMessage,HttpServletRequest request){
		//视图名
		String viewName = null;
		try {
			String title = URLDecoder.decode(salonMessage.getTitle(), Constant.ENCODING);
			salonMessage.setTitle(title);
			salonMessageService.deleteByArr(salonMessage);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, salonMessage, messageSource);
		return commonModelAndView;
    }
	
	/**
	 *  查询理财沙龙消息信息 返回json格式
	 *  @param consult
	 *  @param request
	 */
	@RequestMapping("/clientcenter/salon/message/grid")
	public CommonModelAndView json(SalonMessage salonMessage,HttpServletRequest request){
		salonMessage.setTitle(CommonUtil.convertSearchSign(salonMessage.getTitle()));
		Map<String, Object> map = salonMessageService.getGrid(salonMessage); 
		return new CommonModelAndView("jsonView",map); 
	}
	
	@Override
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		return null;
	}

}
