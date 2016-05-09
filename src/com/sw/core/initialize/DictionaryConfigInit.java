package com.sw.core.initialize;

import java.io.File;
import java.util.Iterator;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.Resource;

import com.sw.core.data.dao.CommonDao;
import com.sw.core.data.entity.DictionarySort;
import com.sw.core.exception.DetailException;

public class DictionaryConfigInit{
	
	private static Logger logger = Logger.getLogger(DictionaryConfigInit.class);

	private CommonDao commonDao;
	private Resource configLocation;

	public void init() throws ServletException
	{
		File dicFile = null;
		try
		{
			logger.debug("data dictionary info initializing , please waiting...");
			dicFile = configLocation.getFile();
    		if (commonDao != null){
            	SAXReader saxReader = new SAXReader();
            	//把文件读入到文档 
                Document document = saxReader.read(dicFile);   
              //获取文档根节点 
                Element ele = document.getRootElement();   
                for(Iterator iterator = ele.elementIterator();iterator.hasNext();) 
                { 
                    Element node = (Element)iterator.next(); 
                    String nodeName = node.getName();
                    if (nodeName != null && nodeName.equals("dictionary")){
                    	//数据字典对象
                    	DictionarySort dictionarySort = new DictionarySort();            	
                        for(Iterator subiterator = node.elementIterator();subiterator.hasNext();) 
                        { 
                            Element subNode = (Element)subiterator.next(); 
                            String subNodeName = subNode.getName();  
                            String subNodeValue = subNode.getText();
                            if (subNodeName != null && subNodeName.equals("name")){
                            	dictionarySort.setName(subNodeValue);
                            }
                            if (subNodeName != null && subNodeName.equals("value")){
                            	dictionarySort.setValue(subNodeValue);
                            }
                        }  
                    	Object obj = commonDao.selectObject("dictionarySort.select", dictionarySort);
                    	if (obj != null){
                    		commonDao.insert("dictionarySort.update", dictionarySort);
                    	}
                    	else{
                    		commonDao.insert("dictionarySort.insert", dictionarySort);
                    	}                        
                    }
                } 
    		}
    		else{
    			throw new Exception("get commonDao error");
    		}
    		logger.debug("data dictionary info initialize finish.");
		}
		catch (Exception e)
		{
			logger.debug("data dictionary info initialize fail！");
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

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	
}
