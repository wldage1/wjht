package com.sw.core.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.DelegatingMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.sw.core.common.Constant;
import com.sw.core.data.entity.Authorization;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.exception.DetailException;
import com.sw.core.initialize.PluginsConfigCache;

public class CommonModelAndView extends ModelAndView{
	
	/**提示信息常量*/
	public static String PROMPT_MESSAGE = "promptMessage";
	/**导航信息变量*/
	public static String NAVIGATION = "navigation"; 
	/**子全新列表变量*/
	public static String SUB_PERMISSION_LIST = "subPermissionList"; 
	/**系统状态参数存储变量*/
	public static String STATUS_PARAM = "statusParam"; 	
	/**提示信息*/
	private static String promptMessage;
	/**提示状态*/
	private String status;
	
	public CommonModelAndView()
	{
		super();
	}
	 
	public CommonModelAndView(String viewName)
	{
		super(viewName);
	}
	 
	public CommonModelAndView(View view)
	{
		super(view);
	}
	
	public CommonModelAndView(String viewName, Map<String, ?> model)
	{
		super(viewName,model);
	}
	 //异步请求，记录当前状态
	public CommonModelAndView(String viewName, Map<String, ?> model, BaseEntity entity, HttpServletRequest request)
	{
		super(viewName,model);
		//记录当前操作状态，列表默认都记录
		Authorization authorization =  PluginsConfigCache.getCache(entity.getC());
		//是否记录当前状态
		if(authorization.getIsStatus()!= null && "true".equals(authorization.getIsStatus())){
			this.addCurrentStatus(entity, request);
		}
	}
	
	 
	public CommonModelAndView(View view, Map<String, ?> model)
	{
		super(view,model);
	}
	 
	public CommonModelAndView(String viewName, String modelName, Object modelObject)
	{
		super(viewName,modelName,modelObject);
	}
	
	/**
	 * 此构造封装方法：
	 * 1、配置视图(获取 "*plugin-config.xml" 里的 "controller" 配置)；
	 * 2、是否加载导航条(根据"*plugin.config.xml" 里 配置的"isNav")；
	 * 3、是否添加子权限信息和子权限信息列表到上下文中；
	 * 4、设置上下文控制器url，在目标页面中可以获取到，并将当时请求中的参数记录到session中，状态回退时能模拟当前状态；
	 * @param request
	 * @param entity
	 */
	public CommonModelAndView(HttpServletRequest request,BaseEntity entity){
		//视图
		super(PluginsConfigCache.getCache(entity.getC()).getController());
		Authorization authorization =  PluginsConfigCache.getCache(entity.getC());
		//是否加载导航
		if(authorization.getIsNav()!= null && "true".equals(authorization.getIsNav())){
			this.addNavigation(entity, request);
		}
		//是否设置子权限目录
		if(authorization.getIsSubAuth()!= null && "true".equals(authorization.getIsSubAuth())){
			this.addSubPermissionCode(entity.getC());
		}
		//是否记录当前状态
		if(authorization.getIsStatus()!= null && "true".equals(authorization.getIsStatus())){
			this.addCurrentStatus(entity, request);
		}
		this.addController(entity,request);
		this.addObject(CommonModelAndView.STATUS_PARAM, entity.getC());
	}
	
	public CommonModelAndView(BaseEntity entity){
		super(PluginsConfigCache.getCache(entity.getC()).getController());
		this.addObject(CommonModelAndView.STATUS_PARAM, entity.getC());
	}
	
	
	/**
	 * 此构造封装方法：
	 * 1、配置视图(接受控制层指定的viewName)；
	 * 2、设置并替换提示信息(提交页面配置的"prompt"字段名来替换相应的提示信息)；
	 * 3、记录操作状态。
	 * @param request
	 * @param entity
	 */
	public CommonModelAndView(String viewName,BaseEntity entity,DelegatingMessageSource messageSource)
	{
		this.addObject(CommonModelAndView.STATUS_PARAM, entity.getC());
		this.setViewName(viewName);
		//提示信息替换内容
		Class<?> clazz = entity.getClass();
		String replaceMsgs []= null;
		Map<String, Object> map = new Hashtable<String, Object>();
		String tempViewName = "success";
		int indx = viewName.indexOf("redirect:/");
		if (indx >= 0){
			tempViewName = viewName.replace("redirect:/", "");
		}
		map.put(Constant.STATUS, tempViewName);
		this.addAllObjects(map);
		//设置并替换提示信息
		if(entity.getPrompt()!= null){
			String promptField[] = entity.getPrompt().split(",");
			replaceMsgs = new String[promptField.length];
			int i = 0;
			for(String pField : promptField){
				Field field;
				try {
					field = clazz.getDeclaredField(pField);
					field.setAccessible(true);  //设置私有属性范围
					replaceMsgs[i] = (String) field.get(entity);
					i++;
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			this.addPromptMessage(messageSource, entity, replaceMsgs);
		}
	}
	
	
	public CommonModelAndView(View view, String modelName, Object modelObject)
	{
		super(view,modelName,modelObject);
	}
	
    /**
     * 生成页面导航条
     * @param code
     * @param request
     */
    public void addNavigation(BaseEntity entity,HttpServletRequest request){
		//生成导航信息
    	Authorization currAuthorization = PluginsConfigCache.getCache(entity.getC());
		String path = "";
		if (currAuthorization != null){
			path = currAuthorization.getRelation();
		}
		String codeArr [] = path.split(",");
		String webroot = request.getContextPath();
		String navigation = "";
		for (int i=0; i<codeArr.length;i++){
			if (codeArr[i] != null && !codeArr[i].trim().equals("")){
				Authorization authorization = PluginsConfigCache.getCache(codeArr[i]);
				if (authorization != null){
					if (authorization.getLevel().equals("1") || authorization.getLevel().equals("2")){
						navigation += " &gt; " + authorization.getName();
					}
					else{
						int indx = authorization.getController().indexOf("?");
						if (indx > -1){
							if(entity.getId()!=null){
								navigation += " &gt; <a href='" + webroot + authorization.getController()+"&c="+authorization.getCode()+"&id="+entity.getId()+"'>" + authorization.getName() + "</a>";
							}else{
								navigation += " &gt; <a href='" + webroot + authorization.getController()+"&c="+authorization.getCode()+"'>" + authorization.getName() + "</a>";
							}
						}
						else{
							if(entity.getId()!=null){
								navigation += " &gt; <a href='" + webroot + authorization.getController()+"?c="+authorization.getCode()+"&id="+entity.getId()+"'>" + authorization.getName() + "</a>";
							}else{
								navigation += " &gt; <a href='" + webroot + authorization.getController()+"?c="+authorization.getCode()+"'>" + authorization.getName() + "</a>";
							}
						}
					}
				}
			}
		}	
		request.getSession().setAttribute(NAVIGATION, navigation);
	}	
    
    /**
     * 将提示信息添加到上下文中
     * @param messageSource
     * @param baseEntity
     * @param replaceMsg
     */
	public void addPromptMessage(DelegatingMessageSource messageSource,BaseEntity baseEntity,String replaceMsg []){
		String pmsg = this.getPromptMessage(messageSource, baseEntity, replaceMsg);
		CommonModelAndView.promptMessage = pmsg;
		//设置提示停息
		try {
			pmsg = URLEncoder.encode(pmsg, Constant.ENCODING);
		} catch (UnsupportedEncodingException e) {
			DetailException.expDetail(e, CommonModelAndView.class);
		}
		this.addObject(CommonModelAndView.PROMPT_MESSAGE,pmsg);
	}
	
	/**
	 * 根据属性文件key获取信息，并存放到上下文中
	 * @param messageSource
	 * @param msgKey
	 * @param replaceMsg
	 */
	public void addPromptMessage(DelegatingMessageSource messageSource,String msgKey,String replaceMsg []){
		String pmsg = this.getPromptMessage(messageSource, msgKey, replaceMsg);
		CommonModelAndView.promptMessage = pmsg;
		//设置提示停息
		try {
			pmsg = URLEncoder.encode(pmsg, Constant.ENCODING);
		} catch (UnsupportedEncodingException e) {
			DetailException.expDetail(e, CommonModelAndView.class);
		}		
		this.addObject(CommonModelAndView.PROMPT_MESSAGE,pmsg);
	}
	
	/**
	 * 获取资源文件信息
	 * @return
	 */
	public static String getPromptMessage(){
		return promptMessage;
	}	
	
	/**
	 * 获取提示信息
	 * @param status
	 * @param baseEntity
	 * @param messageSource
	 * @param replaceMsg
	 * @return
	 */
	public String getPromptMessage(DelegatingMessageSource messageSource,BaseEntity baseEntity,String replaceMsg []){
		String clsname = BaseEntity.class.getName();
		if (baseEntity != null){
			clsname = baseEntity.getClass().getName();
		}
		if (clsname != null){
			clsname = clsname.toLowerCase();
		}
		String viewName = this.getViewName();
		int indx = viewName.indexOf("redirect:/");
		if (indx >= 0){
			viewName = viewName.replace("redirect:/", "");
		}
		if (baseEntity.getErrorMsg() != null ){
			if (baseEntity.getErrorMsg().toLowerCase().indexOf("duplicate") > -1 
				|| baseEntity.getErrorMsg().toLowerCase().indexOf("unique key") > -1 
				|| baseEntity.getErrorMsg().toLowerCase().indexOf("unique constraint") > -1
				|| baseEntity.getErrorMsg().toLowerCase().indexOf("ora-00001") > -1){
				viewName = "multiple";
			}
		}
		return messageSource.getMessage(clsname+"."+baseEntity.getC()+"."+viewName, replaceMsg, Locale.CHINA);
	}

	/**
	 * 直接根据属性文件key值获取信息
	 * @param messageSource
	 * @param msgKey
	 * @param replaceMsg
	 * @return
	 */
	public String getPromptMessage(DelegatingMessageSource messageSource,String msgKey,String replaceMsg []){
		return messageSource.getMessage(msgKey, replaceMsg, Locale.CHINA);
	}	
	
	/**
	 * 添加子权限信息和子权限信息列表到上下文中
	 * @param code
	 */
	public void addSubPermissionCode(String code){
		//更具code查询子权限
		List<Authorization> list = new ArrayList<Authorization>();
		Collection<Authorization> authorizations = PluginsConfigCache.getAllCache();
		for (Authorization authorization:authorizations){
			String parentCode = authorization.getParentCode();
			if (parentCode != null && parentCode.equals(code)){
				list.add(authorization);
				//把每个子权限信息存放到上限文中
				super.addObject(authorization.getIndex(), authorization);
			}
		}
		//把子权限信息存放到上限文中
		super.addObject(CommonModelAndView.SUB_PERMISSION_LIST, list);
	}
	
	/**
	 * 记录当期code以及所有父code的控制器url
	 * 并默认写上back标志
	 * @param code
	 */
	public void addController(BaseEntity baseEntity,HttpServletRequest request){
		Authorization currAuthorization = PluginsConfigCache.getCache(baseEntity.getC());
		String codeRelation = currAuthorization.getRelation();
		if (codeRelation != null){
			String codeRelationArr [] = codeRelation.split(",");
			String parent = "controller";
			for (int i = codeRelationArr.length - 1;i>=0; i--){
				String tempCode = codeRelationArr[i];
				if (tempCode != null && !tempCode.trim().equals("")){
					Authorization authorization = PluginsConfigCache.getCache(tempCode);
					if (authorization != null){
						String controller = authorization.getController() + "?c="+authorization.getCode();
						if (baseEntity.getId() != null){
							controller += "&id=" + baseEntity.getId();
						}
						if (baseEntity.getPage() != 0){
							controller += "&page=" + baseEntity.getPage();
						}
						controller +=  "&back=1";
						request.getSession().setAttribute(parent+"_"+baseEntity.getC(),controller );
						parent = "parent_" + parent;
					}
				}
			}
		}		
	}
	/**
	 * 设置上下文控制器url，在目标页面中可以获取到，
	 * 并将当时请求中的参数记录到session中，状态回退时能模拟当前状态
	 * @param code
	 * @param baseEntity
	 */
	public void addCurrentStatus(BaseEntity baseEntity,HttpServletRequest request){
		String code = baseEntity.getC();
		Authorization currAuthorization = PluginsConfigCache.getCache(code);
		//只要返回状态为null或为0，进行当前请求信息的记录，否者获取当前的状态放到上下文中
		if (baseEntity.getBack() == null || baseEntity.getBack().equals("0")){
			request.getSession().setAttribute(currAuthorization.getController() + "?c=" + code, baseEntity);
		}
	}
	
	/**
	 * 获取记录的状态信息
	 * @param baseEntity
	 * @param request
	 * @return
	 */
	public Object getCurrentStatus(BaseEntity baseEntity,HttpServletRequest request){
		String code = baseEntity.getC();
		Authorization currAuthorization = PluginsConfigCache.getCache(code);
		//只要返回状态为null或为0，进行当前请求信息的记录，否者获取当前的状态放到上下文中
		if (baseEntity.getBack() != null && !baseEntity.getBack().equals("0")){
			return request.getSession().getAttribute(currAuthorization.getController() + "?c=" + code);
		}		
		else{
			request.getSession().removeAttribute(currAuthorization.getController() + "?c=" + code);
			return null;
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
