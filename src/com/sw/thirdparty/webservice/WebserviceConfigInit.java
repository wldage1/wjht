package com.sw.thirdparty.webservice;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.sw.core.exception.DetailException;
import com.sw.core.initialize.PluginsConfigInit;


public class WebserviceConfigInit{
	
	private static Logger logger = Logger.getLogger(WebserviceConfigInit.class);
	
	public Map<String,Object> webserviceConfig;
	
	
	/**
	 *  webservice url 配置初始化
	 *  @throws ServletException
	 *  @author Administrator
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-23 上午09:24:05
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public void init() throws Exception
	{
		try
		{
			logger.debug("webservice config info initializing , please waiting...");
			if (webserviceConfig != null){
				Iterator<String> iterator = webserviceConfig.keySet().iterator();
				while (iterator.hasNext()){
					String key = null, value = null;
					key = iterator.next();
					if (key != null){
						value = webserviceConfig.get(key) == null ? "" : webserviceConfig.get(key).toString();
					}
					if (key != null && value != null){
						WebserviceConfig webserviceConfig = new WebserviceConfig();  
						webserviceConfig.setKey(key);
						webserviceConfig.setValue(value);
						WebserviceConfigCache.putCache(webserviceConfig);
					}
				}
    		}
    		logger.debug("webservice config info initialize finish.");
		}
		catch (Exception e)
		{
			logger.debug("webservice config info initialize fail！");
			String debug = DetailException.expDetail(e, PluginsConfigInit.class);
			logger.debug(debug);
			/**异常退出系统*/
			System.exit(0);
		}
	}


	public Map<String, Object> getWebserviceConfig() {
		return webserviceConfig;
	}


	public void setWebserviceConfig(Map<String, Object> webserviceConfig) {
		this.webserviceConfig = webserviceConfig;
	}
	
	
}