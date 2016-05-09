package com.sw.plugins.usercenter.system.role.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;


import com.sw.core.data.entity.BaseEntity;
import com.sw.core.service.CommonService;
import com.sw.plugins.usercenter.system.role.entity.RoleOrgMapper;

@Service
public class RoleOrgMapperService extends CommonService<RoleOrgMapper> {
	

	/**
	 *保存角色与机构关系
	 */
	@Override
	public void save(RoleOrgMapper entity) throws Exception {
		super.getCommonDao().insert("roleOrgMapper.insert", entity);
	}

	/**
	 *更新角色与机构关系
	 */
	@Override
	public void update(RoleOrgMapper entity) throws Exception {
		super.getCommonDao().update("roleOrgMapper.update", entity);
		
	}

	/**
	 *角色与机构关系所有记录条数
	 */
	@Override
	public Long getAllRecordCount() throws Exception {
		return (Long) super.getCommonDao().selectObject("roleOrgMapper.count");
	}

	/**
	 *条件查询角色与机构关系记录条数
	 */
	@Override
	public Long getRecordCount(RoleOrgMapper entity) throws Exception {
		return (Long) super.getCommonDao().selectObject("roleOrgMapper.count", entity);
	}	
	
	/**
	 *获取角色与机构关系列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleOrgMapper> getAll() throws Exception {
		return super.getCommonDao().selectList("roleOrgMapper.select");
	}

	/**
	 *条件获取角色与机构关系列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleOrgMapper> getList(RoleOrgMapper entity) throws Exception {
		return super.getCommonDao().selectList("roleOrgMapper.select",entity);
	}
	
	/**
	 *删除角色与机构关系
	 */
	@Override
	public void delete(RoleOrgMapper entity) throws Exception {
		super.getCommonDao().delete("roleOrgMapper.delete", entity);
	}

	/**
	 *获取单个角色与机构关系
	 */
	@Override
	public RoleOrgMapper getOneById(RoleOrgMapper entity) throws Exception {
		return (RoleOrgMapper) super.getCommonDao().selectObject("roleOrgMapper.select", entity);
	}

	
	@Override
	public Map<String, Object> getGrid(RoleOrgMapper entity) {
		return null;
	}

	/**
	 *批量删除角色与机构关系
	 */
	@Override
	public void deleteAll() throws Exception {
		super.getCommonDao().delete("roleOrgMapper.delete");
	}

	@Override
	public void deleteByIn(RoleOrgMapper entity) throws Exception {
		super.getCommonDao().delete("roleOrgMapper.deleteByIn");
	}

	@Override
	public void deleteByArr(RoleOrgMapper entity) throws Exception {
		RoleOrgMapper paramRoleOrgMapper = new RoleOrgMapper();
		if (entity != null && entity.getIds() != null){
			for (String id : entity.getIds()){
				paramRoleOrgMapper.setId(Long.parseLong(id));
				super.getCommonDao().delete("roleOrgMapper.delete",paramRoleOrgMapper);
			}
		}
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}


}
