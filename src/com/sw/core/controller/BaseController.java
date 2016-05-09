package com.sw.core.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.DelegatingMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sw.core.data.entity.BaseEntity;
import com.sw.core.initialize.InitialData;
import com.sw.core.service.authorization.AuthorizationService;
import com.sw.core.service.dictionarySort.DictionarySortService;

/**
 * 控制器基类
 */
@Controller  
public abstract class BaseController{
	
	/**操作成功返回*/
	public String SUCCESS = "redirect:/success";
    /**错误页面*/
	public String ERROR = "redirect:/error";
    /**失败页面*/
	public String FAIL = "fail";	
	/**首页跳转*/
	public String INDEX = "index";  
    /**主操作页面跳转*/
	public String MAIN = "main";
    /**主操作页面左侧菜单*/
	public String LEFT = "left";    
    /**欢迎页面*/
	public String WELCOME = "welcome";   
	/**下载页面*/
	public String UPLOAD = "upload";   
    /**退出系统*/
	public String LOGOUT = "logout"; 
    /**退出系统*/
	public String EXIT = "exit"; 
	/**json返回状态*/
	public String STATUS = "status";

    @Resource
    public AuthorizationService authorizationService;	
    @Resource
    public DictionarySortService dictionarySortService;	
    @Resource
	public DelegatingMessageSource messageSource;
    @Resource
    public InitialData initialData;
    
    /**
     * 上传文件
     * @param baseEntity
     * @param request
     * @return
     */
    public abstract @ResponseBody String uploadfile(BaseEntity baseEntity,HttpServletRequest request);


}