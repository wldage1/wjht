package com.sw.core.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sw.core.common.Constant;
import com.sw.core.data.dao.CommonDao;
import com.sw.core.data.entity.Authorization;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.security.SecurityResourceCache;

/**
 * 逻辑实现层（具体实现），service
 * @param <T>
 * @param <PK>
 */
public abstract class CommonService<T extends BaseEntity> {
	
	static Logger log = Logger.getLogger(Constant.FRAMEWORK);
	
	
	@Resource
	private CommonDao commonDao;

    
	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	/**
	 * 保存记录
	 * @param entity
	 * @throws Exception
	 */
	public abstract void save(T entity) throws Exception;

	/**
	 * 修改记录
	 * @param entity
	 * @throws Exception
	 */
	public abstract void update(T entity) throws Exception;

	/**
	 * 根据条件查询记录数
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public abstract Long getRecordCount(T entity) throws Exception;
	
	/**
	 * 查询总记录数
	 * @return
	 * @throws Exception
	 */
	public abstract Long getAllRecordCount() throws Exception;

	/**
	 * 查询列表
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public abstract List<? extends BaseEntity> getList(T entity) throws Exception;
	
	/**
	 * 查询所有数据
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public abstract List<? extends BaseEntity> getAll() throws Exception;

	/**
	 * 删除记录
	 * @param entity
	 * @throws Exception
	 */
	public abstract void delete(T entity) throws Exception;
	
	/**
	 * 删除所有记录
	 * @throws Exception
	 */
	public abstract void deleteAll() throws Exception;
	
	
	/**
	 * 字符串 in 的方式删除
	 * @param param
	 * @throws Exception
	 */
	public abstract void deleteByIn(T entity) throws Exception;
	
	/**
	 * 单条记录循环删除
	 * @param param
	 * @throws Exception
	 */
	public abstract void deleteByArr(T entity) throws Exception;

	/**
	 * 根据id查询单条记录
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public abstract T getOneById(T entity) throws Exception;
	
	/**
	 * 列表（json表格）
	 * @param nodeid
	 * @return
	 */
	public abstract Map<String, Object> getGrid(T entity);
	
	/**
	 * 上传文件
	 * @param baseEntity
	 * @param request
	 * @return
	 */
	public abstract String upload(BaseEntity baseEntity,HttpServletRequest request);
	
	/**
	 * 获取权限信息
	 * @param code
	 * @return
	 */
	public Authorization getPermission(String code){
		Collection<Authorization> authorizations = SecurityResourceCache.getAllCache();
		Authorization rauthorization = null;
		for (Authorization authorization:authorizations){
			String tempCode = authorization.getCode();
			if (tempCode != null && code.equals(tempCode)){
				rauthorization  = authorization;
				break;
			}
		}
		return rauthorization;
	}
	
	/**
	 * 获取控制器url
	 * @param code
	 * @return
	 */
	public String getController(String code){
		Collection<Authorization> authorizations = SecurityResourceCache.getAllCache();
		String controller = "";
		for (Authorization authorization:authorizations){
			String tempCode = authorization.getCode();
			if (tempCode != null && code.equals(tempCode)){
				controller = authorization.getController();
				break;
			}
		}
		return controller;
	}
	
}