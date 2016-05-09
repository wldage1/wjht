package com.sw.plugins.usercenter.system.role.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.sw.core.data.entity.BaseEntity;
import com.sw.core.service.CommonService;
import com.sw.plugins.usercenter.system.role.entity.RoleAuthMapper;

@Service
public class RoleAuthMapperService extends CommonService<RoleAuthMapper> {
	

	
	/**
	 *保存角色权限关系
	 */
	@Override
	public void save(RoleAuthMapper entity) throws Exception {
		super.getCommonDao().insert("roleAuthMapper.insert", entity);
	}

	/**
	 *更新角色权限关系
	 */
	@Override
	public void update(RoleAuthMapper entity) throws Exception {
		super.getCommonDao().update("roleAuthMapper.update", entity);
		
	}

	/**
	 *获取角色权限关系记录条数
	 */
	@Override
	public Long getAllRecordCount() throws Exception {
		return (Long) super.getCommonDao().selectObject("roleAuthMapper.count");
	}

	/**
	 *条件获取角色权限关系记录条数
	 */
	@Override
	public Long getRecordCount(RoleAuthMapper entity) throws Exception {
		return (Long) super.getCommonDao().selectObject("roleAuthMapper.count", entity);
	}	
	
	/**
	 *所有角色权限关系列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleAuthMapper> getAll() throws Exception {
		return super.getCommonDao().selectList("roleAuthMapper.select");
	}

	
	/**
	 *条件查询所有角色权限关系列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleAuthMapper> getList(RoleAuthMapper entity) throws Exception {
		return super.getCommonDao().selectList("roleAuthMapper.select",entity);
	}	
	
	
	/**
	 *删除单个角色权限关系
	 */
	@Override
	public void delete(RoleAuthMapper entity) throws Exception {
		super.getCommonDao().delete("roleAuthMapper.delete", entity);
	}

	/**
	 *获取单个角色权限关系
	 */
	@Override
	public RoleAuthMapper getOneById(RoleAuthMapper entity) throws Exception {
		return (RoleAuthMapper) super.getCommonDao().selectObject("roleAuthMapper.select", entity);
	}

	@Override
	public Map<String, Object> getGrid(RoleAuthMapper entity) {
		return null;
	}
	
	/**
	 *批量删除角色权限关系
	 */
	@Override
	public void deleteAll() throws Exception {
		super.getCommonDao().delete("roleAuthMapper.delete");
	}

	@Override
	public void deleteByIn(RoleAuthMapper entity) throws Exception {
		super.getCommonDao().delete("roleAuthMapper.deleteByIn");
	}

	@Override
	public void deleteByArr(RoleAuthMapper entity) throws Exception {
		RoleAuthMapper paramRoleAuthMapper = new RoleAuthMapper();
		if (entity != null && entity.getIds() != null){
			for (String id : entity.getIds()){
				paramRoleAuthMapper.setId(Long.parseLong(id));
				super.getCommonDao().delete("roleAuthMapper.delete",paramRoleAuthMapper);
			}
		}
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}


}
