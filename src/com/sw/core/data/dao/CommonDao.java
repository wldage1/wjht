package com.sw.core.data.dao;


import com.sw.core.data.dbholder.DatabaseOperateContextHolder;
import com.sw.core.data.entity.BaseEntity;


/**
 * Dao实现类 - Dao实现类基类
 */

@SuppressWarnings("rawtypes")
public class CommonDao extends BaseDao{

	public CommonDao() {}
	
	public int save(String statementid, BaseEntity entity){
		DatabaseOperateContextHolder.setOperateContext("true");
		return super.insert(statementid, entity);
	}
	
	public int update(String statementid, BaseEntity entity) {
		DatabaseOperateContextHolder.setOperateContext("true");
		return super.update(statementid, entity);
	}
	
}