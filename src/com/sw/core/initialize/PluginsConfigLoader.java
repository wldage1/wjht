package com.sw.core.initialize;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.Resource;

import com.sw.core.data.entity.Authorization;
import com.sw.core.util.FileUtil;

public class PluginsConfigLoader{
	
	private static Logger logger = Logger.getLogger(PluginsConfigLoader.class);
	
	/** 插件文件名称 */
	private static String PLUGIN_FILE_NAME = "plugin-config.xml";
	/** ibatis3 sqlmap 配置文件模板文件名 */
	private static String SQL_MAP_FILE_NAME = "sqlMapConfig.tmp";
	/** ibatis3 sqlmap 配置文件名 */
	private static String SQL_MAP_REAL_FILE_NAME = "sqlMapConfig.xml";	
	/** 中文语言配置文件模板文件名 */
	private static String PROPERTY_CN = "pluginsMessages_zh_CN.tmp";
	/** 中文语言配置文件名 */
	private static String PROPERTY_CN_REAL = "pluginsMessages_zh_CN.properties";	
	/** 英文语言配置文件模板文件名 */
	private static String PROPERTY_US = "pluginsMessages_en_US.tmp";
	/** 英文语言配置文件名 */
	private static String PROPERTY_US_REAL = "pluginsMessages_en_US.properties";	
	/**ibatis 3 sqlmap 配置文件根目录*/
	private static String CLS_PATH = "classes";
	/**ibatis 3 插件配置文件存放变量*/
	private StringBuffer sqlMapperssb = new StringBuffer();
	/**中文语言配置文件存放变量*/
	private StringBuffer propertiecn = new StringBuffer();
	/**英文语言配置文件存放变量*/
	private StringBuffer propertieus = new StringBuffer();
	/**系统权限存放列表对象*/
	private Resource configLocation;

	public void init() {
		//获取系统绝对路劲，针对tomcat和weblogic获取路径不同
		File pluginsFile = null;
		String parentPath = null;
		String classesPath = null;
		try {
			pluginsFile = configLocation.getFile();
			parentPath = configLocation.getFile().getParent();
			if (parentPath != null){
				int cindex = parentPath.indexOf(CLS_PATH);
				if (cindex > -1){
					classesPath = parentPath.substring(0, cindex+CLS_PATH.length());
				}
			}
		} catch (IOException e1) {
			try {
				throw new Exception("file path not find!");
			} catch (Exception e) {
				logger.debug(e.getMessage());
				System.exit(0);
			}
		}
		//ibatis3 sqlmap 配置文件验证
		String sqlMapConfig = parentPath + File.separator
		+ "core" + File.separator + "template" + File.separator + SQL_MAP_FILE_NAME;
		File sqlMapFile = new File(sqlMapConfig);
		//如果文件不存在，抛出异常，并系统退出
		if (!sqlMapFile.exists()){
			try {
				throw new Exception("Ibatis3 sqlMapConfig template file '"+SQL_MAP_FILE_NAME+"' is required");
			} catch (Exception e) {
				logger.debug(e.getMessage());
				System.exit(0);
			}
		}
		//struts2 资源文件中文语言包 配置文件验证
		String procn = parentPath + File.separator
		+ "core" +  File.separator + "template" + File.separator + PROPERTY_CN;
		File procnFile = new File(procn);
		//如果文件不存在，抛出异常，并系统退出
		if (!procnFile.exists()){
			try {
				throw new Exception("spring language(cn) propertie template file '"+PROPERTY_CN+"' is required");
			} catch (Exception e) {
				logger.debug(e.getMessage());
				System.exit(0);
			}
		}	
		//struts2 资源文件英文语言包 配置文件验证
		String prous = parentPath + File.separator 
		+ "core" +  File.separator + "template" + File.separator + PROPERTY_US;
		File prousFile = new File(prous);
		//如果文件不存在，抛出异常，并系统退出
		if (!prousFile.exists()){
			try {
				throw new Exception("spring language(us) propertie template file '"+PROPERTY_US+"' is required");
			} catch (Exception e) {
				logger.debug(e.getMessage());
				System.exit(0);
			}
		}		
		//系统插件配置文件目录
		if (pluginsFile != null && pluginsFile.exists()){
			try {
				getPluginFileRecursion(pluginsFile);
			} catch (Exception e) {
				logger.debug(e.getMessage());
				System.exit(0);
			}
		}
		//读取本地sqlmap配置文件模板,并将插件里面的sqlmap内容替换到配置文件中
		String smc = FileUtil.readFileByLines(sqlMapConfig);
		if (smc.indexOf("<!--[replaceContent]-->") > -1){
			smc =  smc.replaceAll("<!--\\[replaceContent\\]-->",sqlMapperssb.toString());
		}
		try {
			//sqlmap真实文件处理
			File realSqlMapConfig = new File(classesPath + File.separator + SQL_MAP_REAL_FILE_NAME);
			//每次执行都先删除原来的在创建一个新的
			if (realSqlMapConfig.exists()){
				realSqlMapConfig.delete();
			}
			realSqlMapConfig.createNewFile();
			FileUtil.strToFile(smc, realSqlMapConfig);
		} catch (IOException e) {
			logger.debug(e.getMessage());
		}
		
		//读取本地propertie配置文件模板,并将插件里面资源文件的内容替换到中文配置文件中
		try {
			//中文pro真实文件处理
			File realProcn = new File(classesPath + File.separator + PROPERTY_CN_REAL);
			if (realProcn.exists()){
				realProcn.delete();
			}
			realProcn.createNewFile();			
			FileUtil.strToFile(propertiecn.toString(), realProcn);
		} catch (IOException e) {
			logger.debug(e.getMessage());
		}		
		//读取本地propertie配置文件模板,并将插件里面资源文件的内容替换到英文配置文件中
		try {
			//中文pro真实文件处理
			File realProus = new File(classesPath + File.separator + PROPERTY_US_REAL);
			if (realProus.exists()){
				realProus.delete();
			}
			realProus.createNewFile();					
			FileUtil.strToFile(propertieus.toString(), realProus);
		} catch (IOException e) {
			logger.debug(e.getMessage());
		}
	}
	
	/**
	 * 递归方式读取插件配置文件，并处理ibatis3 sqlmap和propertie 配置文件
	 * @param parentFile
	 * @param sqlMapperssb
	 * @param propertiesb
	 * @throws Exception 
	 */
	public void getPluginFileRecursion(File parentFile) throws Exception{
		if (parentFile == null)
			return;
		File file [] = parentFile.listFiles();
		for (int i=0; i<file.length; i++){
			if (file[i] != null){
				//插件配置文件
				if (file[i].isFile() && file[i].getName().indexOf(PLUGIN_FILE_NAME) > -1){
					this.parserXml(file[i]);
				}
			}
		}
	}
	
	/**
	 * dom4j 解析xml 文件
	 * @param fileName
	 * @throws Exception 
	 */
    public void parserXml(File pfile) throws Exception  
    {    
        try {    
        	
        	String fpath = pfile.getParent();
        	SAXReader saxReader = new SAXReader();
        	//把文件读入到文档 
            Document document = saxReader.read(pfile);   
          //获取文档根节点 
            Element ele=document.getRootElement();   
            this.recursionParser(ele,fpath); 
        } catch (DocumentException e) {  
        	throw new Exception(e.getMessage());
        }    
    }
    
    /**
     * 递归方式解析
     * @param ele
     */
    private void recursionParser(Element ele,String proPath) 
    { 
        for(Iterator iterator = ele.elementIterator();iterator.hasNext();) 
        { 
            Element node = (Element)iterator.next(); 
            String nodeName = node.getName();
            //读取到资源文件
            if (nodeName != null && nodeName.equals("language")){
            	for(Iterator language = node.elementIterator();language.hasNext();) {
            		Element languageNode = (Element)language.next();
					//资源文件名称
					String pcn = languageNode.getText();
					try{
						if (pcn != null){
							//插件内资源文件路径
							String rfile = proPath + pcn;
							//读取资源文件内容
							String pcon = FileUtil.readFileByLines(rfile);
						    //追加到全局变量里,CN 中文 US 英文
							if (pcn.indexOf("CN") > -1){
								propertiecn.append(pcon);
							}
							else{
								propertiecn.append(pcon);
							}
						}
					}
					catch(Exception e){
						logger.debug(e.getMessage());
					}            		
            	}
            }
            else{
	            if(node.attributes()!=null && node.attributes().size()>0) 
	            { 
	            	//系统权限信息
	            	Authorization authorization = new Authorization();
	                for(Iterator subiterator = node.attributeIterator();subiterator.hasNext();) 
	                { 
	                    Attribute item = (Attribute)subiterator.next(); 
	                    String attrName = item.getName();
	                    String attrValue = item.getValue();
	                    if (attrName != null && attrName.equals("index")){
	                    	authorization.setIndex(attrValue);
	                    }
	                    if (attrName != null && attrName.equals("code")){
	                    	int len = attrValue.length();
	                    	String pcode = "0";
	                    	if (len > 2){
	                    		pcode = attrValue.substring(0,len-2);
	                    	}
	                    	String codeRelation = ",";
	                    	for (int i=1; i<=len;i++){
	                    		if (i%2 == 1){
	                    			String temp = attrValue.substring(0,i);
	                    			codeRelation += temp + ",";
	                    		}
	                    	}
	                    	authorization.setRelation(codeRelation);
	                    	authorization.setParentCode(pcode);
	                    	authorization.setCode(attrValue);
	                    }
	                    if (attrName != null && attrName.equals("name")){
	                    	authorization.setName(attrValue);
	                    }
	                    if (attrName != null && attrName.equals("level")){
	                    	authorization.setLevel(attrValue);
	                    }
	                    if (attrName != null && attrName.equals("controller")){
	                    	authorization.setController(attrValue);
	                    } 
	                    if (attrName != null && attrName.equals("isLog")){
	                    	authorization.setIsLog(attrValue);
	                    } 
	                    if (attrName != null && attrName.equals("isNav")){
	                    	authorization.setIsNav(attrValue);
	                    } 
	                    if (attrName != null && attrName.equals("isSubAuth")){
	                    	authorization.setIsSubAuth(attrValue);
	                    } 
	                    if (attrName != null && attrName.equals("isStatus")){
	                    	authorization.setIsStatus(attrValue);
	                    } 
	                    if (attrName != null && attrName.equals("icon")){
	                    	authorization.setIcon(attrValue);
	                    } 
	                   // System.out.print("属性名："+item.getName()+"\t属性值："+item.getValue()+"\n"); 
	                } 
	              //放到缓存当中
	                PluginsConfigCache.putCache(authorization);
	            } 
            }
            if(node.elementIterator().hasNext()) 
            { 
                this.recursionParser(node,proPath); 
            }
        } 
    }     
    
	public Resource getConfigLocation() {
		return configLocation;
	}

	public void setConfigLocation(Resource configLocation) {
		this.configLocation = configLocation;
	}
	
	
}
