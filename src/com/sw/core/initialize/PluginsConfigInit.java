package com.sw.core.initialize;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.sw.core.data.dao.CommonDao;
import com.sw.core.data.entity.Authorization;
import com.sw.core.exception.DetailException;

public class PluginsConfigInit{
	
	private static Logger logger = Logger.getLogger(PluginsConfigInit.class);

	private CommonDao commonDao;

	public void init() throws ServletException
	{
		try
		{
    		logger.debug("authorization info initializing , please waiting...");
    		if (commonDao != null){
	    		//删除所有权限信息
    			//commonDao.delete("authorization.deleteAll");  
    			List codes = new ArrayList();
	    		//系统所有插件配置的权限信息
	    		Collection<Authorization> parrayList = PluginsConfigCache.getAllCache();
	    		if(parrayList != null){
	    			for (Iterator<Authorization> localIterator = parrayList.iterator(); localIterator.hasNext();) {
	    				Authorization handlerAuthorization = localIterator.next();
	    				try{
		    				if (handlerAuthorization != null){
		    					//权限bean的拷贝
		    					String controller = handlerAuthorization.getController();
		    					if (controller != null && !controller.startsWith("/")){
		    						handlerAuthorization.setController("/"+ controller);
		    					}
		    					if (handlerAuthorization.getCode() == null && handlerAuthorization.getCode().equals("")){
		    						throw new Exception("permission code is null");
		    					}
		    					if (handlerAuthorization.getParentCode() == null && handlerAuthorization.getParentCode().equals("")){
		    						throw new Exception("permission parentcode is null");
		    					}
		    					/***如果数据库中存在，则只进行修改操作****/
		    					List list = commonDao.selectList("authorization.selectByCode", handlerAuthorization);
		    					if(list != null && list.size() == 0) {
		    						commonDao.insert("authorization.insert", handlerAuthorization);
		    					}else {
		    						commonDao.update("authorization.update", handlerAuthorization);
		    					}
		    					codes.add(handlerAuthorization.getCode());
		    				}
	    				}
	    				catch (Exception e){
	    					logger.debug(e.getMessage() + " cause '" + handlerAuthorization.getName()+ "' authorization info initialize fail , please check it.");
	    					throw new Exception(e);
	    				}
	    			}
	    		}
	    		Authorization a = new Authorization();
	    		a.setCodes(codes);
	    		List<Authorization> authList = commonDao.selectList("authorization.selectByNotCodes",a );
	    		for (int i = 0; i < authList.size(); i++) {
	    			if((Long)commonDao.selectObject("authorization.roleAuthCount", authList.get(i)) == 0){
	    				commonDao.delete("authorization.deleteByCode", authList.get(i));
	    			}
				}
    		}
    		else{
    			throw new Exception("get commonDao error");
    		}
    		logger.debug("authorization info initialize finish.");
		}
		catch (Exception e)
		{
			logger.debug("authorization info initialize fail！");
			String debug = DetailException.expDetail(e, PluginsConfigInit.class);
			logger.debug(debug);
			/**异常退出系统*/
			System.exit(0);
		}
	}

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	
}
