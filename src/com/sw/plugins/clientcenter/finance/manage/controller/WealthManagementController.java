package com.sw.plugins.clientcenter.finance.manage.controller;

import java.net.URLDecoder;
import java.util.Hashtable;
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
import com.sw.core.common.SystemProperty;
import com.sw.core.controller.BaseController;
import com.sw.core.controller.CommonModelAndView;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.util.CommonUtil;
import com.sw.plugins.clientcenter.finance.manage.entity.WealthManagement;
import com.sw.plugins.clientcenter.finance.manage.service.WealthManagementService;

/**
 * 理财中心管理控制器，负责理财中心删除，查询等功能
 * @author Administrator
 *
 */
@Controller
public class WealthManagementController extends BaseController {
	
	private static Logger logger = Logger.getLogger(WealthManagementController.class);
	
	@Resource
	private WealthManagementService wealthManagementService;
	
	/**
     * 理财中心信息列表方法
     * @param code
     * @param request
     * @return
     */
	@RequestMapping("/clientcenter/finance/manage/list")
    public CommonModelAndView list(WealthManagement wealthManagement,HttpServletRequest request,Map<String,Object> model){ 
		Object obj = new CommonModelAndView().getCurrentStatus(wealthManagement, request);
		if (obj != null){
			if (obj instanceof WealthManagement){
				wealthManagement = (WealthManagement)obj;
			}
		} 
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, wealthManagement);
		commonModelAndView.addObject("code", wealthManagement.getC());
		commonModelAndView.addObject("wealthManagement", wealthManagement);
        return commonModelAndView;
    }
	
	/**
	 * 跳转到理财中心创建页面
	 * @param code
	 * @param request
	 * @return
	 * @author 
	 */
	@RequestMapping("/clientcenter/finance/manage/create")
    public CommonModelAndView create(WealthManagement wealthManagement,HttpServletRequest request){
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, wealthManagement);
		commonModelAndView.addObject("wealthManagement", wealthManagement);
		String imagePath = SystemProperty.getInstance("parameterConfig").getProperty("wealthManagement");
		commonModelAndView.addObject("imagePath", imagePath);
        return commonModelAndView; 
    } 
	
	/**
	 * 理财中心创建操作
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/clientcenter/finance/manage/save",method = RequestMethod.POST)
	public CommonModelAndView save(@Valid WealthManagement wealthManagement,BindingResult result,Map<String,Object> model) {
		CommonModelAndView commonModelAndView = null;
		if (result.hasErrors()) {
			commonModelAndView = new CommonModelAndView(wealthManagement);
			String imagePath = SystemProperty.getInstance("parameterConfig").getProperty("wealthManagement");
			commonModelAndView.addObject("imagePath", imagePath);
			return commonModelAndView;
		}
		//视图名
		String viewName = null;
		try {
			if(wealthManagement != null && wealthManagement.getId() != null){
				wealthManagementService.update(wealthManagement);
			}else{
				wealthManagementService.save(wealthManagement);
			}
			viewName = this.SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}
		commonModelAndView = new CommonModelAndView(viewName,wealthManagement,messageSource);
		return commonModelAndView;		
	}
	
	/**
	 * 跳转到理财沙龙消息修改页面
	 * @param code
	 * @param salonMessage
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/finance/manage/modify")
    public CommonModelAndView modify(WealthManagement wealthManagement,HttpServletRequest request,Map<String,Object> model){
		String code = wealthManagement.getC();
		if (wealthManagement.getId() != null) {
			try {
				WealthManagement wealth = new WealthManagement();
				wealth.setId(wealthManagement.getId());
				wealthManagement = wealthManagementService.getOneById(wealth);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		wealthManagement.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,wealthManagement);
		commonModelAndView.addObject("wealthManagement", wealthManagement);
		model.put("wealthManagement", wealthManagement);
		String imagePath = SystemProperty.getInstance("parameterConfig").getProperty("wealthManagement");
		commonModelAndView.addObject("imagePath", imagePath);
        return commonModelAndView; 
    }
	
	/**
	 * 跳转理财中心信息详细页面
	 * @param code
	 * @param financeFeedback
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/finance/manage/detail")
    public CommonModelAndView detail(WealthManagement wealthManagement,HttpServletRequest request){
		String code = wealthManagement.getC();
		try {
			wealthManagement = wealthManagementService.getOneById(wealthManagement);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		wealthManagement.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, wealthManagement);
		commonModelAndView.addObject("wealthManagement", wealthManagement);
		String imagePath = SystemProperty.getInstance("parameterConfig").getProperty("wealthManagement");
		commonModelAndView.addObject("imagePath", imagePath);
        return commonModelAndView; 
    }
	
	/**
	 * 理财中心信息删除功能，json格式
	 * @param consult
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/finance/manage/delete")
    public CommonModelAndView delete(WealthManagement wealthManagement,HttpServletRequest request){
		//视图名
		String viewName = null;		
		try {
			String name = URLDecoder.decode(wealthManagement.getName(), Constant.ENCODING);
			wealthManagement.setName(name);
			wealthManagementService.deleteByArr(wealthManagement);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, wealthManagement, messageSource);
		return commonModelAndView;
    }
	
	/**
	 *  查询理财中心信息 返回json格式
	 *  @param consult
	 *  @param request
	 */
	@RequestMapping("/clientcenter/finance/manage/grid")
	public CommonModelAndView json(WealthManagement wealthManagement,HttpServletRequest request){
		wealthManagement.setPhone(CommonUtil.convertSearchSign(wealthManagement.getPhone()));
		wealthManagement.setName(CommonUtil.convertSearchSign(wealthManagement.getName()));
		Map<String, Object> map = wealthManagementService.getGrid(wealthManagement);
		return new CommonModelAndView("jsonView",map); 
	}
	
	@Override
	@RequestMapping("/clientcenter/finance/manage/uploadfile")
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		return wealthManagementService.upload(baseEntity, request);
	}
	
	@RequestMapping("/clientcenter/finance/manage/deleteImgFile")
	public CommonModelAndView deleteImgFile(WealthManagement wealthManagement,String nodeid,HttpServletRequest request){
		if(wealthManagement!=null && wealthManagement.getImage()!=null){
			wealthManagementService.deleteImgFile(wealthManagement, request);
		}
		Map<String, Object> map =  new Hashtable<String, Object>();
		return new CommonModelAndView("jsonView",map);
	}

}
