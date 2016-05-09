package com.sw.core.interceptor;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sw.core.common.Constant;
import com.sw.core.controller.CommonModelAndView;
import com.sw.core.data.dao.CommonDao;
import com.sw.core.data.dbholder.CreatorContextHolder;
import com.sw.core.data.dbholder.DatabaseOperateContextHolder;
import com.sw.core.data.dbholder.DatabaseTypeContextHolder;
import com.sw.core.data.dbholder.DatasourceTypeContextHolder;
import com.sw.core.data.entity.Authorization;
import com.sw.core.data.entity.BaseLog;
import com.sw.core.initialize.PluginsConfigCache;
import com.sw.core.util.DateUtil;
import com.sw.plugins.usercenter.system.user.entity.User;

public class CommonInterceptor implements HandlerInterceptor {

	private static Logger logger = Logger.getLogger(CommonInterceptor.class);

	@Resource
	private CommonDao commonDao;


	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj, ModelAndView modelAndView) throws Exception {
		//操作代码
		String c = request.getParameter("c");
		if (c != null && !c.equals("")){
			Authorization authorization = PluginsConfigCache.getCache(c);
			//验证是否为数据库操作
			if (c != null && DatabaseOperateContextHolder.getOperateContext() != null && DatabaseOperateContextHolder.getOperateContext() == "true") {
				//获取系统所有权限
				//验证是否需要记录日志
				if (authorization.getIsLog() != null && ("true".equals(authorization.getIsLog()) || "1".equals(authorization.getIsLog()))) {
					BaseLog baseLog = new BaseLog();
					Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					//验证用户是否登录
					if (currentUser != null) {
						if (currentUser instanceof User) {
							User user = (User) currentUser;
							baseLog.setUserId(user.getId());
							baseLog.setAccessIp(user.getAccessIp());
							baseLog.setUserAccount(user.getAccount());
							baseLog.setUserName(user.getName());
//							baseLog.setOptTime(DateUtil.getCurrentDateTime());
							baseLog.setContent(CommonModelAndView.getPromptMessage());
							try {
								//写入日志
								commonDao.save("log.insert", baseLog);
							} catch (Exception e) {
								logger.debug(e.getMessage());
							}
						}
					}
				}
			}
		}
		//清楚上下文中存储的数据源，数据库，操作，创建者信息
		DatabaseOperateContextHolder.clear();
		DatasourceTypeContextHolder.clear();
		DatabaseTypeContextHolder.clear();
		CreatorContextHolder.clear();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		//控制器访问信息输出，config.properties配置文件中控制是否输出
		if (Constant.IS_OUTPUT){
			logger.debug("==>RequestURI:"+request.getRequestURI());
			Enumeration enumer = request.getParameterNames();
			while(enumer.hasMoreElements()){
				Object pname = enumer.nextElement();
				if (pname != null){
					String pvalue = request.getParameter(pname.toString());
			        logger.debug("==>Prameter:" + pname.toString() + "=" + pvalue);
				}
			}
		}		
		String c = request.getParameter("c");
		if (c != null && !c.equals("")){
			Authorization authorization = PluginsConfigCache.getCache(c);
			//获取数据源名称，并根据数据源名称动态设置数据源和数据类型
			String dataSource = "";
			if(authorization != null && authorization.getDataSource() != null){
				dataSource = authorization.getDataSource();
				DatasourceTypeContextHolder.setDataSourceType(dataSource);
			}
			if (dataSource != null && !dataSource.equals("")){
				
				//设置数据库类型
				if (dataSource.indexOf(Constant.DATASOURCE_MYSQL) > -1){
					dataSource = Constant.DATASOURCE_MYSQL;
				}
				else if (dataSource.indexOf(Constant.DATASOURCE_ORACLE) > -1){
					dataSource = Constant.DATASOURCE_ORACLE;
				}
				else if (dataSource.indexOf(Constant.DATASOURCE_ORACLE) > -1){
					dataSource = Constant.DATASOURCE_SQLSERVER;
				}	
				else if (dataSource.indexOf(Constant.DATASOURCE_SQLSERVER) > -1){
					dataSource = Constant.DATASOURCE_DB2;
				}	
				else if (dataSource.indexOf(Constant.DATASOURCE_INFORMIX) > -1){
					dataSource = Constant.DATASOURCE_INFORMIX;
				}	
				else if (dataSource.indexOf(Constant.DATASOURCE_SYBASE) > -1){
					dataSource = Constant.DATASOURCE_SYBASE;
				}		
				else if (dataSource.indexOf(Constant.DATASOURCE_POSTGRES) > -1){
					dataSource = Constant.DATASOURCE_POSTGRES;
				}	
			}
			else{
				//设置默认数据源
				dataSource = Constant.DEFAULT_DATABASE_TYPE;
			}
			//设置数据库类型
			DatabaseTypeContextHolder.setDatabaseType(dataSource);
			//设置创建用户
			Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (currentUser instanceof User) {
				User user = (User) currentUser;
				CreatorContextHolder.setCreatorContext(String.valueOf(user.getId()));
			}
		}
		return true;
	}
	public CommonDao getCommonDao() {
		return commonDao;
	}
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
}
