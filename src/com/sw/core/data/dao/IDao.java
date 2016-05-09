package com.sw.core.data.dao;

import java.util.List;

import javax.sql.DataSource;

import com.sw.core.data.entity.BaseEntity;

/**
 * 数据库操作dao
 * @author zgq
 *
 * @param <T>
 */

public interface IDao{

	/**
	 * 根据statementid进行数据删除（无参数）
	 * @param statementid 数据库操作sql语句id
	 * @return      返回整数
	 */
	public int delete(String statementid);
	
	/**
	 * 根据statementid，实体bean参数对象进行删除操作
	 * @param statementid   数据库操作sql语句id
	 * @param entity  实体bean对象
	 * @return        返回整数
	 */
	public int delete(String statementid,BaseEntity entity);
    
	/**
	 * 根据statementid进行数据插入操作（无参数）
	 * @param statementid 数据库操作sql语句id
	 * @return      返回整数
	 */
	public int insert(String statementid);
	
	/**
	 * 根据statementid，实体bean参数对象进行插入操作
	 * @param statementid   数据库操作sql语句id
	 * @param entity  实体bean参数对象
	 * @return        返回整数
	 */
	public int insert(String statementid,BaseEntity entity);	
	
	
	/**
	 * 根据statementid进行数据修改操作（无参数）
	 * @param statementid  数据库操作sql语句id
	 * @return       返回整数
	 */
	public int update(String statementid);
	
	/**
	 * 根据statementid，实体bean参数对象进行修改操作
	 * @param statementid   数据库操作sql语句id
	 * @param entity  实体bean参数对象
	 * @return        返回整数
	 */
	public int update(String statementid,BaseEntity entity);	
	
	/**
	 * 根据statementid，查询列表数据
	 * @param statementid 数据库操作sql语句id
	 * @return      返回整数
	 */
	public List<?> selectList(String statementid);
	
	/**
	 * 根据statementid，实体bean参数对象查询列表数据
	 * @param statementid  数据库操作sql语句id
	 * @param entity 实体bean参数对象
	 * @return       返回实体bean列表
	 */
	public List<?> selectList(String statementid,BaseEntity entity);	
	
	/**
	 * 根据statementid，实体bean参数对象，忽略记录数，最大记录数，查询分页列表数据
	 * @param statementid         数据库操作sql语句id
	 * @param entity        实体bean参数对象
	 * @param skipResults   忽略记录数
	 * @param maxResults    最大记录数
	 * @return              返回实体bean列表 
	 */
	public List<?> selectPaginatedList(String statementid,BaseEntity entity,int skipResults,int maxResults);		

	/**
	 * 根据statementid查询实体bean参数对象
	 * @param statementid  数据库操作sql语句id
	 * @return       返回实体bean对象
	 */
	public Object selectObject(String statementid);
	
	/**
	 * 根据statementid，实体bean参数对象，查询实体bean对象
	 * @param statementid  数据库操作sql语句id
	 * @param entity 实体bean对象
	 * @return       返回实体bean对象
	 */
	public Object selectObject(String statementid,BaseEntity entity);
	
	/**
	 * 获取数据源对象
	 * @return   返回数据源对象
	 */
	public DataSource getDateSource();
	
}