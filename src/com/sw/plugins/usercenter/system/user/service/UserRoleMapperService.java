package com.sw.plugins.usercenter.system.user.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.sw.core.data.entity.BaseEntity;
import com.sw.core.service.CommonService;
import com.sw.plugins.usercenter.system.user.entity.UserRoleMapper;

@Service
public class UserRoleMapperService extends CommonService<UserRoleMapper> {
	

	/**
	 *保存用户与角色关系
	 */
	@Override
	public void save(UserRoleMapper entity) throws Exception {
		super.getCommonDao().insert("userRoleMapper.insert", entity);
	}

	/**
	 *理新用户与角色关系
	 */
	@Override
	public void update(UserRoleMapper entity) throws Exception {
		super.getCommonDao().update("userRoleMapper.update", entity);
		
	}

	/**
	 *所有用户与角色关系记录条数
	 */
	@Override
	public Long getRecordCount(UserRoleMapper entity) throws Exception {
		return (Long) super.getCommonDao().selectObject("userRoleMapper.count", entity);
	}

	/**
	 *条件查询用户与角色关系条数
	 */
	@Override
	public Long getAllRecordCount() throws Exception {
		return (Long) super.getCommonDao().selectObject("userRoleMapper.count");
	}	
	
	/**
	 *获取所有用户与角色关系列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleMapper> getAll() throws Exception {
		return super.getCommonDao().selectList("userRoleMapper.select");
	}

	/**
	 *条件获取用户与角色关系列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleMapper> getList(UserRoleMapper entity) throws Exception {
		return super.getCommonDao().selectList("userRoleMapper.select", entity);
	}	
	
	/**
	 *删除用户与角色关系
	 */
	@Override
	public void delete(UserRoleMapper entity) throws Exception {
		super.getCommonDao().delete("userRoleMapper.delete", entity);
	}

	/**
	 *获取单个用户与角色关系
	 */
	@Override
	public UserRoleMapper getOneById(UserRoleMapper entity) throws Exception {
		return (UserRoleMapper) super.getCommonDao().selectObject("userRoleMapper.select", entity);
	}

	@Override
	public Map<String, Object> getGrid(UserRoleMapper entity) {
		return null;
	}

	/**
	 *删除所有用户与角色关系
	 */
	@Override
	public void deleteAll() throws Exception {
		super.getCommonDao().delete("userRoleMapper.delete");
	}

	/**
	 *批量删除用户与角色关系
	 */
	@Override
	public void deleteByIn(UserRoleMapper entity) throws Exception {
		super.getCommonDao().delete("userRoleMapper.deleteByIn");
	}

	@Override
	public void deleteByArr(UserRoleMapper entity) throws Exception {
		UserRoleMapper paramUserRoleMapper = new UserRoleMapper();
		if (entity != null && entity.getIds() != null){
			for (String id : entity.getIds()){
				paramUserRoleMapper.setId(Long.parseLong(id));
				super.getCommonDao().delete("userRoleMapper.delete",paramUserRoleMapper);
			}
		}
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}
