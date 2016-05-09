package com.sw.plugins.usercenter.system.log.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.sw.core.data.entity.BaseEntity;
import com.sw.core.exception.DetailException;
import com.sw.core.service.CommonService;
import com.sw.plugins.usercenter.system.log.entity.Log;

/**
 *  类简要说明
 *  @author Administrator
 *  @version 1.0
 *  </pre>
 *  Created on :下午02:21:17
 *  LastModified:
 *  History:
 *  </pre>
 */
@Service
public class LogService extends CommonService<Log>{

	/**
	 *获取日志MAP集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getGrid(Log log) {
		//从request对象中获取页面信息
		int skip = log.getRows() * log.getPage() - log.getRows();
		List<Object> list = new ArrayList<Object>();
		List<Log> resultList = null;
		resultList = getCommonDao().selectPaginatedList("log.select", log, skip, log.getRows());
		for (Log tempLog : resultList){
			Map<String, Object> maporg = new Hashtable<String, Object>();
			List<Object> celllist = new ArrayList<Object>();
			celllist.add(tempLog.getUserName());
			celllist.add(tempLog.getAccessIp());
			celllist.add(tempLog.getOptTime().substring(0, tempLog.getOptTime().length()-2));
			celllist.add(tempLog.getContent());
			maporg.put("id", tempLog.getId());
			maporg.put("cell", celllist);
			list.add(maporg);
		}
		//记录数
		long record = 0;
		try {
			record = getCommonDao().selectList("log.select", log).size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//页数
		int pageCount = (int)Math.ceil(record/(double) log.getRows());
		Map<String, Object> map = new Hashtable<String, Object>(); 
		map.put("rows", list);	
		map.put("page", log.getPage());
		map.put("total", pageCount);
		map.put("records", record);
		return map;
	}
	
	
	/**
	 *删除日志
	 */
	@Override
	public void delete(Log log) throws Exception{
		try{
			super.getCommonDao().delete("log.delete", log);	
		}
		catch(Exception e){
			DetailException.expDetail(e, LogService.class);
			throw e;
		}
	}

	
	/**
	 *保存日志
	 */
	@Override
	public void save(Log entity) throws Exception {
		super.getCommonDao().insert("log.insert", entity);
		
	}

	
	/**
	 *更新日志
	 */
	@Override
	public void update(Log entity) throws Exception {
		super.getCommonDao().update("log.update", entity);
	}


	/**
	 *统计所有日志记录
	 */
	@Override
	public Long getAllRecordCount() throws Exception {
		return (Long) super.getCommonDao().selectObject("log.count");
	}

	/**
	 *条件统计所有日志记录
	 */
	@Override
	public Long getRecordCount(Log entity) throws Exception {
		return (Long) super.getCommonDao().selectObject("log.count",entity);
	}	
	
	/**
	 *查询所有日志列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Log> getAll() throws Exception {
		return super.getCommonDao().selectList("log.select");
	}
	
	
	/**
	 *条件日志列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Log> getList(Log entity) throws Exception {
		return super.getCommonDao().selectList("log.select",entity);
	}
	
	
	/**
	 *获取单个日志
	 */
	@Override
	public Log getOneById(Log entity) throws Exception {
		return null;
	}

	/**
	 *删除所有日志
	 */
	@Override
	public void deleteAll() throws Exception {
		super.getCommonDao().delete("log.delete");
	}

	
	/**
	 *批量删除日志
	 */
	@Override
	public void deleteByIn(Log entity) throws Exception {
		super.getCommonDao().delete("log.deleteByIn");
	}
	
	@Override
	public void deleteByArr(Log entity) throws Exception {
		Log paramLog = new Log();
		if (entity != null && entity.getIds() != null){
			for (String id : entity.getIds()){
				paramLog.setId(Long.parseLong(id));
				super.getCommonDao().delete("log.delete",paramLog);
			}
		}
	}


	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}