
package com.sw.plugins.clientcenter.member.generateaccount.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sw.core.controller.BaseController;
import com.sw.core.controller.CommonModelAndView;
import com.sw.core.data.entity.BaseEntity;
import com.sw.plugins.clientcenter.member.generateaccount.entity.GenerateAccount;
import com.sw.plugins.clientcenter.member.generateaccount.service.GenerateAccountService;

/**
 *  CRM成单生成账号
 *  @author baokai
 *  @version 1.0
 *  </pre>
 *  Created on :下午1:14:11
 *  LastModified:
 *  History:
 *  </pre>
 */
@Controller  
public class GenerateAccountController extends BaseController{  
	
	private static Logger logger = Logger.getLogger(GenerateAccountController.class);
	
	@Resource
	private GenerateAccountService generateAccountService;

	@Override
	public @ResponseBody
	String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 @RequestMapping("/clientcenter/member/generateaccount/list")
	 public CommonModelAndView list(GenerateAccount generateAccount,HttpServletRequest request,Map<String,Object> model){ 
        Object obj = new CommonModelAndView().getCurrentStatus(generateAccount, request);
		if (obj != null){
			if (obj instanceof GenerateAccount){
				generateAccount = (GenerateAccount)obj;
			}
		} 
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,generateAccount);
		commonModelAndView.addObject("code", generateAccount.getC());
		model.put("generateAccount", generateAccount);
        return commonModelAndView;
	 }
	
	 @RequestMapping("/clientcenter/member/generateaccount/grid")
	 public CommonModelAndView json(GenerateAccount generateAccount,HttpServletRequest request){
		Map<String, Object> map = generateAccountService.getGrid(generateAccount); 
		return new CommonModelAndView("jsonView",map,generateAccount,request); 
	 }
    
	 @RequestMapping("/clientcenter/member/generateaccount/generate")
	 public CommonModelAndView generate(GenerateAccount generateAccount,HttpServletRequest request){
		String viewName = null;		
		try {
			generateAccountService.generate(generateAccount);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,generateAccount,messageSource);
		return commonModelAndView;					
	 }
	
}

