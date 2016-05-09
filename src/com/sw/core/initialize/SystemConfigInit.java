package com.sw.core.initialize;

import java.util.Properties;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;

import com.sw.core.common.Constant;
import com.sw.core.exception.DetailException;

public class SystemConfigInit{
	
	private static Logger logger = Logger.getLogger(SystemConfigInit.class);

	private Resource configLocation;
	private String dataSourceType;

	public void init() throws ServletException
	{
		try
		{
			Properties props = new Properties();
	        props.load(configLocation.getInputStream());			
			logger.debug("system config initializing , please waiting...");
			//读取系统自定义控制器是否输出的状态
			String isOutPut = props.getProperty("controller.info.output") == null ?"":props.getProperty("controller.info.output");
			if (isOutPut.equals("true")){
				Constant.IS_OUTPUT = true;
			}
			else{
				Constant.IS_OUTPUT = false;
			}
			//根据数据源类型设置数据库类型，默认为mysql
			if (dataSourceType != null && !dataSourceType.equals("")){
				if (dataSourceType.indexOf(Constant.DATASOURCE_MYSQL) > -1){
					Constant.DEFAULT_DATABASE_TYPE = Constant.DATASOURCE_MYSQL;
				}
				else if (dataSourceType.indexOf(Constant.DATASOURCE_ORACLE) > -1){
					Constant.DEFAULT_DATABASE_TYPE = Constant.DATASOURCE_ORACLE;
				}
				else if (dataSourceType.indexOf(Constant.DATASOURCE_SQLSERVER) > -1){
					Constant.DEFAULT_DATABASE_TYPE = Constant.DATASOURCE_SQLSERVER;
				}	
				else if (dataSourceType.indexOf(Constant.DATASOURCE_INFORMIX) > -1){
					Constant.DEFAULT_DATABASE_TYPE = Constant.DATASOURCE_INFORMIX;
				}	
				else if (dataSourceType.indexOf(Constant.DATASOURCE_SYBASE) > -1){
					Constant.DEFAULT_DATABASE_TYPE = Constant.DATASOURCE_SYBASE;
				}		
				else if (dataSourceType.indexOf(Constant.DATASOURCE_POSTGRES) > -1){
					Constant.DEFAULT_DATABASE_TYPE = Constant.DATASOURCE_POSTGRES;
				}
				else if (dataSourceType.indexOf(Constant.DATASOURCE_DB2) > -1){
					Constant.DEFAULT_DATABASE_TYPE = Constant.DATASOURCE_DB2;
				}
				else{
					Constant.DEFAULT_DATABASE_TYPE = Constant.DATASOURCE_MYSQL;
				}
			}
    		logger.debug("system config initialize finish.");
		}
		catch (Exception e)
		{
			logger.debug("system config initialize fail！");
			String debug = DetailException.expDetail(e, PluginsConfigInit.class);
			logger.debug(debug);
			/**异常退出系统*/
			System.exit(0);
		}
	}
    
	public Resource getConfigLocation() {
		return configLocation;
	}

	public void setConfigLocation(Resource configLocation) {
		this.configLocation = configLocation;
	}

	public String getDataSourceType() {
		return dataSourceType;
	}

	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}
	
}
