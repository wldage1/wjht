package com.sw.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.sw.core.common.Constant;
import com.sw.core.data.entity.Authorization;
import com.sw.core.initialize.PluginsConfigCache;

public class MySecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	
	private static Logger logger = Logger.getLogger(MySecurityMetadataSource.class);

	/**url和全面代码映射map*/
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public MySecurityMetadataSource() {

		loadResourceDefine();
	}
	
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

	// 加载所有资源与权限的关系
	private void loadResourceDefine() {
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			Collection<Authorization> authorizations = PluginsConfigCache.getAllCache();
			for (Authorization authorization : authorizations) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				// 以权限名封装为Spring的security Object
				ConfigAttribute configAttribute = new SecurityConfig(authorization.getCode());
				configAttributes.add(configAttribute);
				resourceMap.put(authorization.getController(), configAttributes);
			}		
			//设置主页面为所有用户可以访问
			Collection<ConfigAttribute> configAttributesMain = new ArrayList<ConfigAttribute>();
			// 以权限名封装为Spring的security Object
			ConfigAttribute configAttributeMain = new SecurityConfig(Constant.ALL_USER_MAIN);
			configAttributesMain.add(configAttributeMain);		
			resourceMap.put("/"+Constant.MAIN, configAttributesMain);	
			
			//设置用户有退出页面权限
//			Collection<ConfigAttribute> configAttributesExit = new ArrayList<ConfigAttribute>();
//			// 以权限名封装为Spring的security Object
//			ConfigAttribute configAttributeExit = new SecurityConfig(Constant.ALL_USER_EXIT);
//			configAttributesExit.add(configAttributeExit);		
//			resourceMap.put(Constant.EXIT, configAttributesExit);	
			
			//设置用户有退出页面权限
			Collection<ConfigAttribute> configAttributesLogout = new ArrayList<ConfigAttribute>();
			// 以权限名封装为Spring的security Object
			ConfigAttribute configAttributeLogout = new SecurityConfig(Constant.ALL_USER_LOGOUT);
			configAttributesLogout.add(configAttributeLogout);		
			resourceMap.put("/"+Constant.LOGOUT, configAttributesLogout);				
			
			//设置左侧菜单页面为所有用户可以访问
			Collection<ConfigAttribute> configAttributesLeft = new ArrayList<ConfigAttribute>();
			// 以权限名封装为Spring的security Object
			ConfigAttribute configAttributeLeft = new SecurityConfig(Constant.ALL_USER_LEFT);
			configAttributesLeft.add(configAttributeLeft);		
			resourceMap.put("/"+Constant.LEFT, configAttributesLeft);	
			
			//设置左侧菜单页面为所有用户可以访问
			Collection<ConfigAttribute> configAttributesContent = new ArrayList<ConfigAttribute>();
			// 以权限名封装为Spring的security Object
			ConfigAttribute configAttributeContent = new SecurityConfig(Constant.ALL_USER_CONTENT);
			configAttributesContent.add(configAttributeContent);		
			resourceMap.put(Constant.CONYENT, configAttributesContent);	
			
			
			//设置欢迎页面为所有用户可以访问
			Collection<ConfigAttribute> configAttributesWelCome = new ArrayList<ConfigAttribute>();
			// 以权限名封装为Spring的security Object
			ConfigAttribute configAttributeWelCome = new SecurityConfig(Constant.ALL_USER_WELCOME);
			configAttributesWelCome.add(configAttributeWelCome);		
			resourceMap.put("/"+Constant.WELCOME, configAttributesWelCome);	
		}		
	}

	// 返回所请求资源所需要的权限
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		//logger.debug("MySecurityMetadataSource-91:requestUrl is " + requestUrl);
		if (resourceMap == null) {
			loadResourceDefine();
		}
		return resourceMap.get(requestUrl);
	}
}