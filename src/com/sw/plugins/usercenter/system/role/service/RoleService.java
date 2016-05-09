package com.sw.plugins.usercenter.system.role.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sw.core.data.entity.Authorization;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.service.CommonService;
import com.sw.plugins.usercenter.system.organization.entity.Organization;
import com.sw.plugins.usercenter.system.organization.service.OrganiztionService;
import com.sw.plugins.usercenter.system.role.entity.Role;
import com.sw.plugins.usercenter.system.role.entity.RoleAuthMapper;
import com.sw.plugins.usercenter.system.role.entity.RoleOrgMapper;
import com.sw.plugins.usercenter.system.user.entity.User;
import com.sw.plugins.usercenter.system.user.entity.UserRoleMapper;
import com.sw.plugins.usercenter.system.user.service.UserRoleMapperService;

/**
 * Service实现类 - Service实现类基类
 */

/**
 *  类简要说明
 *  @author Administrator
 *  @version 1.0
 *  </pre>
 *  Created on :下午02:30:26
 *  LastModified:
 *  History:
 *  </pre>
 */
@Service
public class RoleService extends CommonService<Role> {
	
	private static Logger logger = Logger.getLogger(RoleService.class);
	
	@Resource
    private RoleAuthMapperService roleAuthMapperService;
    @Resource
    private RoleOrgMapperService roleOrgMapperService;
    @Resource
    private UserRoleMapperService userRoleMapperService;
    @Resource
    private OrganiztionService organiztionService;
    
	/**
	 *获取角色集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getGrid(Role entity) {
		List<Object> list = new ArrayList<Object>();
		List<Role> resultList = null;
		resultList = getCommonDao().selectList("role.selectPaginated", entity); 
		for (Role role : resultList) {
			Map<String, Object> maprole = new Hashtable<String, Object>();
			List<Object> celllist = new ArrayList<Object>();
			celllist.add(role.getOrgName());
			celllist.add(role.getName());
			celllist.add(role.getDescription());
			maprole.put("id", role.getId());
			maprole.put("cell", celllist);
			list.add(maprole);
		}
		// 记录数
		long record = 0;
		try {
			record = getRecordCount(entity);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		// 页数
		int pageCount = (int) Math.ceil(record / (double) entity.getRows());
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("rows", list);
		map.put("page", entity.getPage());
		map.put("total", pageCount);
		map.put("records", record);
		return map;
	}

	/**
	 * 获取角色权限字符串
	 * 
	 * @param role
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getRoleAuthorization(Role role) {
		String json = null;
		Object obj = SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (obj instanceof User) {
			try {
				User user = (User) obj;
				List<Object> list = new ArrayList<Object>();
				Set<String> authSet = new HashSet<String>();
				Integer authNum = 0;
				// 当前登录用户具有的角色信息
				List<Role> roleList = user.getRoles();
				// 此授权角色具有的权限信息，没有授权是，此值为空
				List<Authorization> roleAuthList = super.getCommonDao().selectList("role.selectAuth", role);
				if (roleList != null && roleList.size() > 0) {
					for (Role tempRole : roleList) {
						List<Authorization> authorizationList = tempRole.getAuthorizations();
						if (authorizationList != null) {
							for (Authorization authorization : authorizationList) {
								authSet.add(authorization.getCode());
								if(authSet.size() > authNum){
									authNum = authSet.size();
									Map<String, Object> maprole = new Hashtable<String, Object>();
									// 树形参数
									maprole.put("id", authorization.getCode());
									maprole.put("pId",
											authorization.getParentCode());
									maprole.put("name", authorization.getName());
									if (roleAuthList != null) {
										// 如果存在登录用户具有的权限信息和已经授权角色的权限信息一致
										// 设置此权限为选中状态
										for (Authorization tempAuthorization : roleAuthList) {
											String code = tempAuthorization
													.getCode();
											if (code != null
													&& code.equals(authorization
															.getCode())) {
												// 设置为选中
												maprole.put("checked", true);
												break;
											}
										}
									}
									list.add(maprole);
								}
							}
						}
					}
				}
				json = JSONArray.fromObject(list).toString();
				logger.debug("RoleService-94:" + json);
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}
		return json;
	}

	/**
	 * 获取角色资源字符串
	 * 
	 * @param role
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getOrgAuthorization(Role role){
		String json = null;
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (obj instanceof User){
			try {
				User user = (User)obj;
				List<Object> list = new ArrayList<Object>();
				Set<String> orgSet = new HashSet<String>();
				Integer orgNum = 0;
				//当前登录用户具有的角色信息
				List<Role> roleList = user.getRoles();
				//此授权角色具有的机构信息，没有授权是，此值为空
				List<Organization> roleOrgList = super.getCommonDao().selectList("role.selectOrg",role);
				if (roleList != null && roleList.size() > 0){
					for(Role tempRole:roleList){
						List<Organization> organizationList = super.getCommonDao().selectList("role.selectOrg",tempRole);
						if (organizationList.size() == 0 && tempRole.getType() == 0){
							organizationList = super.getCommonDao().selectList("organization.select",new Organization());
						}
						for (Organization organization:organizationList) {
							orgSet.add(organization.getId().toString());
							if(orgSet.size() > orgNum){
								orgNum = orgSet.size();
								Map<String, Object> maprole = new Hashtable<String, Object>();
								//树形参数
								maprole.put("id", organization.getId());
								maprole.put("pId", organization.getParentId());
								maprole.put("name", organization.getName());
								if (roleOrgList != null){
									//如果存在登录用户具有的权限信息和已经授权角色的权限信息一致
									//设置此权限为选中状态
									for (Organization tempOrganization:roleOrgList){
										String id = tempOrganization.getId().toString();
										if (id != null && id.equals(organization.getId().toString())){
											//设置为选中
											maprole.put("checked", true);
											break;
										}
									}
								}
								list.add(maprole);
							}
						}							
					}
				}
				json = JSONArray.fromObject(list).toString();
				logger.debug("RoleService-95:"+json);
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}		
		return json;
	}

	/**
	 * 保存角色权限信息
	 * 
	 * @param role
	 * @throws Exception
	 */
	public void saveAuth(Role role) throws Exception {
		String auth = role.getAuth();
		if (auth != null) {
			RoleAuthMapper roleAuthMapper = new RoleAuthMapper();
			Object obj = SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			List<Role> roleList = null;
			if (obj instanceof User) {
				User user = (User) obj;
				// 当前登录用户具有的权限信息
				roleList = user.getRoles();
				String authArr[] = auth.split(",");
				roleAuthMapper.setRoleId(role.getId());
				try {
					super.getCommonDao().delete("roleAuthMapper.delete", roleAuthMapper);
					for (String tempAuth : authArr) {
						if (roleList != null && roleList.size() > 0) {
							for (Role tempRole : roleList) {
								List<Authorization> currplist = tempRole
								.getAuthorizations();
								for (Authorization authorization : currplist) {
									if (tempAuth != null
											&& tempAuth.equals(authorization
													.getCode())) {
										roleAuthMapper.setRoleId(role.getId());
										roleAuthMapper.setAuthCode(authorization.getCode());
										super.getCommonDao().insert("roleAuthMapper.insert",roleAuthMapper);
										break;
									}
								}
							}
						}
					}
				}catch(Exception e){logger.debug(e.getMessage());}
			}
		}
	}
	
	/**
	 * 保存角色资源权限信息
	 * 
	 * @param role
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void saveOrgAuth(Role role) throws Exception {
		String org = role.getOrg();
		if (org != null) {
			RoleOrgMapper roleOrgMapper = new RoleOrgMapper();
			Object obj = SecurityContextHolder.getContext().getAuthentication()
			.getPrincipal();
			List<Role> roleList = null;
			if (obj instanceof User) {
				User user = (User) obj;
				// 当前登录用户具有的权限信息
				Integer type = user.getType();
				if(type == 0){
					Role tempRole = new Role();
					List<Role> tempList = new ArrayList<Role>();
					tempRole.setOrganizations(super.getCommonDao().selectList("organization.select",new Organization()));
					tempList.add(tempRole);
					roleList = tempList;
				}else{
					roleList = super.getCommonDao().selectList("role.selectRoleOrg",role);
				}
				String orgArr[] = org.split(",");
				try {
					roleOrgMapper.setRoleId(role.getId());
					super.getCommonDao().delete("roleOrgMapper.delete", roleOrgMapper);
					for (String tempOrg : orgArr) {
						if (roleList != null && roleList.size() > 0) {
							for (Role tempRole : roleList) {
								List<Organization> currplist = tempRole.getOrganizations();
								for (Organization organization : currplist) {
									if (tempOrg != null && tempOrg.equals(organization.getId().toString())) {
										roleOrgMapper.setRoleId(role.getId());
										roleOrgMapper.setOrgId(organization.getId());
										super.getCommonDao().insert("roleOrgMapper.insert",roleOrgMapper);
										break;
									}
								}
							}
						}
					}
				}catch(Exception e){logger.debug(e.getMessage());}
			}
		}
	}
	
	/**
	 *  保存或更改角色信息
	 *  @param role
	 *  @throws Exception
	 */
	public void saveOrUpdate(Role role) throws Exception{
		if(role != null && role.getId() != null){
			update(role);
		}else{
			save(role);
		}
	}
	

	
	/**
	 *保存角色
	 */
	@Override
	public void save(Role entity) throws Exception {
		super.getCommonDao().insert("role.insert",entity);
	}

	
	/**
	 *更新角色
	 */
	@Override
	public void update(Role entity) throws Exception {
		super.getCommonDao().update("role.update",entity);
	}

	/**
	 *所有角色记录条数
	 */
	@Override
	public Long getAllRecordCount() throws Exception {
		return (Long) super.getCommonDao().selectObject("role.count");
	}

	/**
	 *条件获取角色记录条数
	 */
	@Override
	public Long getRecordCount(Role entity) throws Exception {
		return (Long) super.getCommonDao().selectObject("role.count",entity);
	}	
	
	/**
	 *所有角色记录列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAll() throws Exception {
		return super.getCommonDao().selectList("role.select");
	}

	/**
	 *条件查询角色记录列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getList(Role entity) throws Exception {
		return super.getCommonDao().selectList("role.select",entity);
	}	
	
	/**
	 * 
	 *  删除角色
	 */
	@Override
	public void delete(Role entity) throws Exception {
		//删除角色关联权限
		if(roleAuthMapperService.getRecordCount(new RoleAuthMapper(entity.getId(),null))>0){
			roleAuthMapperService.delete(new RoleAuthMapper(entity.getId(),null));
		}
		//删除角色关联机构
//		if(roleOrgMapperService.getRecordCount(new RoleOrgMapper(entity.getId(),null))>0){
//			roleOrgMapperService.delete(new RoleOrgMapper(entity.getId(),null));
//		}
		//删除用户关联角色
		if(userRoleMapperService.getRecordCount(new UserRoleMapper(entity.getId(),null))>0){
			userRoleMapperService.delete(new UserRoleMapper(entity.getId(),null));
		}
		super.getCommonDao().delete("role.delete", entity);
		
	}

	
	/**
	 *获取单个角色
	 */
	@Override
	public Role getOneById(Role entity) throws Exception {
		return (Role) super.getCommonDao().selectObject("role.select", entity);
	}

	/**
	 *删除所有角色
	 */
	@Override
	public void deleteAll() throws Exception {
		super.getCommonDao().delete("role.delete");
	}

	/**
	 *批量删除角色
	 */
	@Override
	public void deleteByIn(Role entity) throws Exception {
		super.getCommonDao().delete("role.deleteByIn");
	}

	@Override
	public void deleteByArr(Role entity) throws Exception {
		Role paramRole = new Role();
		if (entity != null && entity.getIds() != null){
			for (String id : entity.getIds()){
				paramRole.setId(Long.parseLong(id));
				delete(paramRole);
			}
		}
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Organization> getOrgList() throws Exception {
		Organization org = new Organization();
		org.setLevel(2);
		List<Organization> list = organiztionService.selectOrglist(org);
		return list;
	}

}