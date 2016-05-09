package com.sw.core.data.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.orm.ibatis3.support.SqlSessionDaoSupport;

import com.sw.core.data.dbholder.DatabaseOperateContextHolder;
import com.sw.core.data.entity.BaseEntity;

/**
 * Dao实现类 - Dao实现类基类
 */

public class BaseDao<T extends BaseEntity> extends SqlSessionDaoSupport implements IDao {

	public BaseDao() {
	}

	@Override
	public int delete(String statementid) {
		DatabaseOperateContextHolder.setOperateContext("true");
		return getSqlSessionTemplate().delete(statementid);
	}

	@Override
	public int delete(String statementid, BaseEntity entity) {
		DatabaseOperateContextHolder.setOperateContext("true");
		return getSqlSessionTemplate().delete(statementid, entity);
	}

	@Override
	public int insert(String statementid) {
		DatabaseOperateContextHolder.setOperateContext("true");
		return getSqlSessionTemplate().insert(statementid);
	}

	@Override
	public int insert(String statementid, BaseEntity entity) {
		DatabaseOperateContextHolder.setOperateContext("true");
		return getSqlSessionTemplate().insert(statementid,entity);
	}

	@Override
	public int update(String statementid) {
		DatabaseOperateContextHolder.setOperateContext("true");
		return getSqlSessionTemplate().update(statementid);
	}

	@Override
	public int update(String statementid, BaseEntity entity) {
		DatabaseOperateContextHolder.setOperateContext("true");
		return getSqlSessionTemplate().update(statementid, entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> selectList(String statementid) {
		return getSqlSessionTemplate().selectList(statementid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> selectList(String statementid, BaseEntity entity) {
		return getSqlSessionTemplate().selectList(statementid, entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> selectPaginatedList(String statementid, BaseEntity entity, int skipResults,
			int maxResults) {
		return getSqlSessionTemplate().selectList(statementid, entity, new RowBounds(skipResults,maxResults));
	}

	@Override
	public Object selectObject(String statementid) {
		return getSqlSessionTemplate().selectOne(statementid);
	}

	@Override
	public Object selectObject(String statementid, BaseEntity entity) {
		return getSqlSessionTemplate().selectOne(statementid,entity);
	}

	@Override
	public DataSource getDateSource() {
		return getSqlSessionTemplate().getDataSource();
	}
}