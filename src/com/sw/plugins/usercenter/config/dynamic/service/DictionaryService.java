package com.sw.plugins.usercenter.config.dynamic.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sw.core.data.entity.BaseEntity;
import com.sw.core.data.entity.DictionarySort;
import com.sw.core.exception.DetailException;
import com.sw.core.initialize.PluginsConfigInit;
import com.sw.core.service.CommonService;
import com.sw.core.service.dictionarySort.DictionarySortService;
import com.sw.plugins.usercenter.config.dynamic.entity.Dictionary;

@Service
public class DictionaryService extends CommonService<Dictionary> {

	private static Logger logger = Logger.getLogger(DictionaryService.class);
	public Map<String,Object> dictionarySort;
	@Resource
	private DictionarySortService dictionarySortService;
	
	
	public void init() throws ServletException
	{
		try
		{
			logger.debug("data dictionary sort info initializing , please waiting...");
			if (dictionarySort != null){
				Iterator<String> iterator = dictionarySort.keySet().iterator();
				while (iterator.hasNext()){
					try{
						String key = null, value = null;
						key = iterator.next();
						if (key != null){
							value = dictionarySort.get(key) == null ? "" : dictionarySort.get(key).toString();
						}
						if (key != null && value != null){
							DictionarySort dictionarySort = new DictionarySort();  
							dictionarySort.setName(value);
							dictionarySort.setValue(key);
							Object obj = super.getCommonDao().selectObject("dictionarySort.select", dictionarySort);
                    		if (obj != null){
                    			super.getCommonDao().insert("dictionarySort.update", dictionarySort);
                    		}
                    		else{
                    			super.getCommonDao().insert("dictionarySort.insert", dictionarySort);
                    		}
						}
					}
					catch (Exception e){
						logger.debug(e.getMessage());
					}
				}
    		}
    		else{
    			throw new Exception("get commonDao error");
    		}
    		logger.debug("data dictionary sort info initialize finish.");
		}
		catch (Exception e)
		{
			logger.debug("data dictionary sort info initialize fail！");
			String debug = DetailException.expDetail(e, PluginsConfigInit.class);
			logger.debug(debug);
			/**异常退出系统*/
			System.exit(0);
		}
	}
	
	
	public Map<String, Object> getGrid(String nodeid) throws Exception {
		List<Object> list = new ArrayList<Object>();
		List<Dictionary> resultList = null;
		Dictionary dictionary = new Dictionary();
		if(nodeid == null || "".equals(nodeid)){
			List<DictionarySort> dictionarySortList = new ArrayList<DictionarySort>();
			//取所有字典类型
			dictionarySortList = dictionarySortService.getAll();
			for(DictionarySort ds : dictionarySortList){
				Map<String, Object> maprole = new Hashtable<String, Object>();
				List<Object> celllist = new ArrayList<Object>();
				celllist.add('1');
				celllist.add(ds.getName());
				celllist.add(null);
				celllist.add(null);
				celllist.add(1);
				celllist.add(0);
				dictionary.setDictSortValue(ds.getValue());
				resultList = getList(dictionary);
				if(resultList.size() > 0){
					celllist.add(false);
				}else{
					celllist.add(true);
				}
				maprole.put("id", ds.getId()+"a");
				maprole.put("cell", celllist);
				list.add(maprole);
			}
		}else{
			DictionarySort dictionarySort = new DictionarySort();
			dictionarySort.setId(Long.parseLong(nodeid.replace("a", "")));
			dictionarySort = dictionarySortService.getOneById(dictionarySort);
			dictionary.setDictSortValue(dictionarySort.getValue());
			resultList = getList(dictionary);
			for(Dictionary dict : resultList){
				Map<String, Object> maprole = new Hashtable<String, Object>();
				List<Object> celllist = new ArrayList<Object>();
				celllist.add('0');
				celllist.add(dict.getName());
				celllist.add(dict.getValue());
				celllist.add(null);
				celllist.add(2);
				celllist.add(nodeid);
				celllist.add(true);
				maprole.put("id", dict.getId());
				maprole.put("cell", celllist);
				list.add(maprole);
			}
		}
			Map<String, Object> map = new Hashtable<String, Object>(); 
			map.put("rows", list);
			return map;
	}
	
	/**
	 *  更具字典类型常量值，获取该字典类型下的字典信息，json格式
	 *  @param entity
	 *  @return
	 *  @author Administrator
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-2 下午2:40:26
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@SuppressWarnings("unchecked")
	public String getDictJson(Dictionary entity) {
		List<Object> list = new ArrayList<Object>();
		List<Dictionary> resultList = getCommonDao().selectList("dictionary.select", entity);
		Map<String, Object> map = new Hashtable<String, Object>();
		for (Dictionary dictionary : resultList){
			map.put("name", dictionary.getName());
			map.put("dictSortValue", dictionary.getDictSortValue());
			map.put("id", dictionary.getId());
			list.add(map);
		}
		return JSONArray.fromObject(list).toString();
	}

	@SuppressWarnings("unchecked")
	public Map<Long, Dictionary> getDictMap(Dictionary entity) {
		List<Dictionary> resultList = getCommonDao().selectList("dictionary.select", entity);
		Map<Long, Dictionary> map = new Hashtable<Long, Dictionary>();
		for (Dictionary dictionary : resultList){
			map.put(Long.parseLong(dictionary.getValue()), dictionary);
		}
		return map;
	}
	
	
	@Override
	public void save(Dictionary entity) throws Exception {
		getCommonDao().insert("dictionary.insert",entity);
	}

	@Override
	public void update(Dictionary entity) throws Exception {
		getCommonDao().update("dictionary.update", entity);	
	}

	@Override
	public Long getAllRecordCount() throws Exception {
		Object obj = getCommonDao().selectObject("dictionary.selectCount");
		if (obj instanceof Integer){
			return (Long)obj;
		}
		else if (obj instanceof Long){
			return (Long)obj;
		}
		return 0l;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dictionary> getAll() throws Exception {
		return getCommonDao().selectList("dictionary.select");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dictionary> getList(Dictionary entity) throws Exception {
		return getCommonDao().selectList("dictionary.select", entity);
	}
	
	@Override
	public void delete(Dictionary entity) {
		getCommonDao().delete("dictionary.deleteDictionary", entity);
	}

	@Override
	public Dictionary getOneById(Dictionary entity) throws Exception {
		if (entity == null || entity.getId() == null)
			return null;
		Object obj = getCommonDao().selectObject("dictionary.select", entity);
		if (obj instanceof Dictionary){
			return (Dictionary)obj;
		}
		return null;
	}

	@Override
	public Long getRecordCount(Dictionary entity) throws Exception {
		Object obj = getCommonDao().selectObject("dictionary.selectCount",entity);
		if (obj instanceof Integer){
			return (Long)obj;
		}
		else if (obj instanceof Long){
			return (Long)obj;
		}
		return 0l;
	}

	@Override
	public void deleteAll() throws Exception {
		super.getCommonDao().delete("dictionary.delete");
	}

	@Override
	public void deleteByIn(Dictionary entity) throws Exception {
		super.getCommonDao().delete("dictionary.deleteByIn");
	}

	@Override
	public void deleteByArr(Dictionary entity) throws Exception {
		Dictionary paramDictionary = new Dictionary();
		if (entity != null && entity.getIds() != null){
			for (String id : entity.getIds()){
				paramDictionary.setId(Long.parseLong(id));
				super.getCommonDao().delete("dictionary.deleteDictionary",paramDictionary);
			}
		}
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		return null;
	}

	public Map<String, Object> getDictionarySort() {
		return dictionarySort;
	}

	public void setDictionarySort(Map<String, Object> dictionarySort) {
		this.dictionarySort = dictionarySort;
	}
	
	public Long selectValue(Dictionary entity){
		Object obj = getCommonDao().selectObject("dictionary.selectValue",entity);
		if (obj instanceof Integer){
			return (Long)obj;
		}
		else if (obj instanceof Long){
			return (Long)obj;
		}
		return 0l;
	}
	
	public Long selectName(Dictionary entity){
		Object obj = getCommonDao().selectObject("dictionary.selectName",entity);
		if (obj instanceof Integer){
			return (Long)obj;
		}
		else if (obj instanceof Long){
			return (Long)obj;
		}
		return 0l;
	}


	@Override
	public Map<String, Object> getGrid(Dictionary entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
