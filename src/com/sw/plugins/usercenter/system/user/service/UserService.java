package com.sw.plugins.usercenter.system.user.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sw.core.common.Constant;
import com.sw.core.data.entity.Authorization;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.exception.DetailException;
import com.sw.core.initialize.PluginsConfigCache;
import com.sw.core.service.CommonService;
import com.sw.core.util.Encrypt;
import com.sw.plugins.usercenter.system.organization.entity.Organization;
import com.sw.plugins.usercenter.system.role.entity.Role;
import com.sw.plugins.usercenter.system.user.entity.User;
import com.sw.plugins.usercenter.system.user.entity.UserOrgMapper;
import com.sw.plugins.usercenter.system.user.entity.UserRoleMapper;

@Service
public class UserService extends CommonService<User> implements UserDetailsService{

	private static Logger logger = Logger.getLogger(UserService.class);
	
	//管理员账户（Spring 注入）
	private String account;
	//管理员密码（Spring 注入）
	private String password;
	
	@Resource
    private UserRoleMapperService userRoleMapperService;
	
	/**
	 * 默认初始化集合，并创建超级管理员信息
	 */
	public void init(){
		try
		{
			logger.debug("user info initializing");
			if (super.getCommonDao() != null){
				User user = new User();
				user.setType(0);
				user = getOneById(user);
				if (user == null){
					user = new User();
	    			user.setAccount(account);
	    			user.setPassword(Encrypt.getMD5(password));
	    			user.setType(0);
	    			user.setUsername(account);
	    			user.setName(account);
	    			save(user);					
				}
				else{
	    			user.setAccount(account);
	    			user.setUsername(account);
	    			user.setName(account);	
	    			user.setType(0);
					update(user);
				}
			}
    		else{
    			throw new Exception("get commonDao error");
    		}
			logger.debug("user info initialize finished");
		}
		catch (Exception e)
		{
			String debug = DetailException.expDetail(e, UserService.class);
			logger.debug("user info initialize fail");
			logger.debug(debug);
		}
	}	
	
	/**
	 *用户登录验证
	 */
	@SuppressWarnings({ "unchecked"})
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		try{
			User user = new User();
			user.setType(null);
			user.setAccount(username);
			//先验证用户是否存在
			user = getOneById(user);
			if (user != null){
				//若不是超级管理员，直接查询期具有的系统功能和数据权限
				List<Role> roleList = new ArrayList<Role>();
				if (user.getType() != null && user.getType() == 0){
					//所有机构信息（数据权限）
					List<Authorization> authorizationList = super.getCommonDao().selectList("authorization.select",new Authorization());
//					//所有权限信息（功能权限）
					List<Organization> organizationList = super.getCommonDao().selectList("organization.select");
					Role role = new Role();
					role.setType(0);
					role.setAuthorizations(authorizationList);
//					role.setOrganizations(organizationList);
					roleList.add(role);
					user.setOrganizations(organizationList);
					
				}else{
					List<Role> tempRoleList = super.getCommonDao().selectList("user.selectUserRole", user);
					for(Role role:tempRoleList){
						List<Authorization> authorizationList = super.getCommonDao().selectList("role.selectAuth",role);
//						List<Organization> organizationList = super.getCommonDao().selectList("role.selectOrg",role);
						role.setAuthorizations(authorizationList);
//						role.setOrganizations(organizationList);
						roleList.add(role);
					}
					UserOrgMapper tempUserOrgMapper = new UserOrgMapper();
					tempUserOrgMapper.setUserID(user.getId());
					List<Organization> organizationList = super.getCommonDao().selectList("organization.selectUserOrg",tempUserOrgMapper);
					if(organizationList.size()>0)
						user.setOrganizations(organizationList);
				}	
				user.setRoles(roleList);
			}
			else{
				throw new UsernameNotFoundException("user is not exist");
			}
			user.setAuthorities(getGrantedAuthorities(user));
			return user;	
		}
		catch (Exception e){
			logger.debug(e.getMessage());
		}
		return new User();
	}
	
	/**
	 * 获得权限数组
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Collection<GrantedAuthority> getGrantedAuthorities(User user) {
		Collection<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();
		//如果是超级管理员，设置其具有所有的系统权限
		if (user.getType() != null && user.getType() == 0){
			Collection<Authorization> authorizationList = PluginsConfigCache.getAllCache();
		    for(Authorization authorization : authorizationList) {   
		    	grantedAuthority.add(new GrantedAuthorityImpl(authorization.getCode()));   
		    }	
		}
		else{
			List<Role> roleList = super.getCommonDao().selectList("user.selectUserRole", user);
			if (roleList != null){
				for(Role role:roleList){
					List<Authorization> authorizationList = super.getCommonDao().selectList("role.selectAuth", role);
					if (authorizationList != null){
						for(Authorization authorization : authorizationList) {   
						    grantedAuthority.add(new GrantedAuthorityImpl(authorization.getCode()));   
					    }  
					}
				}
			}
		}
		//设置所用用户具有main页面访问权限
		grantedAuthority.add(new GrantedAuthorityImpl(Constant.ALL_USER_MAIN)); 
		//设置所用用户具有left页面访问权限
		grantedAuthority.add(new GrantedAuthorityImpl(Constant.ALL_USER_LEFT)); 
		//设置所用用户具有退出页面访问权限
		grantedAuthority.add(new GrantedAuthorityImpl(Constant.ALL_USER_LOGOUT)); 	
		//设置所用用户具有内容页面访问权限
		grantedAuthority.add(new GrantedAuthorityImpl(Constant.ALL_USER_CONTENT));
		//设置所用用户具有欢迎页面访问权限
		grantedAuthority.add(new GrantedAuthorityImpl(Constant.ALL_USER_WELCOME));
		return grantedAuthority;
	}
	
	/**
	 * 获取用户角色json串
	 * @param users
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getUserRole(User user){
		String json = null;
		try {
			List<Object> list = new ArrayList<Object>();
			List<Role> roleList = super.getCommonDao().selectList("role.select",new Role());					
			List<Role> bindRoleList =  super.getCommonDao().selectList("user.selectUserRole",user);;
			if (roleList != null){
				for (Role role:roleList) {
					Map<String, Object> maprole = new Hashtable<String, Object>();
					maprole.put("id", role.getId());
					maprole.put("pId", 0);
					maprole.put("name", role.getName());
					if (bindRoleList != null){
						for (Role tempRole:bindRoleList){
							Long id = tempRole.getId();
							if (id != null && id == role.getId()){
								maprole.put("checked", true);
								break;
							}
						}
					}
					list.add(maprole);
				}
			}
			json = JSONArray.fromObject(list).toString();
			logger.debug("UserService-196:"+json);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		return json;
	}	
	
	/**
	 * 保存用户角色信息
	 * @param role
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public void saveBind(User user) throws Exception{
		String bind = user.getBind();
		if (bind != null){
			UserRoleMapper userRoleMapper = new UserRoleMapper();
			List<Role> roleList = super.getCommonDao().selectList("role.select",new Role());
			try{
				userRoleMapper.setUserId(user.getId());
				super.getCommonDao().delete("userRoleMapper.delete", userRoleMapper);
				if (roleList != null){
					String bindArr[] = bind.split(",");
					for (String tempBind :bindArr){
						for (Role role:roleList){
							if (tempBind != null && tempBind.equals(String.valueOf(role.getId()))){
								userRoleMapper.setUserId(user.getId());
								userRoleMapper.setRoleId(role.getId());
								super.getCommonDao().insert("userRoleMapper.insert",userRoleMapper);
								break;
							}
						}
					}	
				}
			}catch(Exception e){logger.debug(e.getMessage());}
		}
	}
	
	/**
	 *获取用户信息集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getGrid(User entity) {
		List<Object> list = new ArrayList<Object>();
		List<User> resultList = null;
		// 记录数
		long record = 0;
		User u = new User();
		u.setId(Long.valueOf(entity.getCreator()));
		u = getRoleByUid(u);
		if(u != null && u.getRoleName().contains("代理")){
			User uc = getRoleByUid(entity);
			if(uc != null){
				Map<String, Object> maprole = new Hashtable<String, Object>();
				List<Object> celllist = new ArrayList<Object>();
				celllist.add(uc.getName());
				celllist.add(uc.getAccount());
				celllist.add(uc.getOrgName());
				celllist.add(uc.getRoleName());
				maprole.put("id", uc.getId());
				maprole.put("cell", celllist);
				list.add(maprole);
				record = 1;
			}
		}else{
			resultList = getCommonDao().selectList("user.selectPaginated", entity); 
			for (User user : resultList) {
				Map<String, Object> maprole = new Hashtable<String, Object>();
				List<Object> celllist = new ArrayList<Object>();
				celllist.add(user.getName());
				celllist.add(user.getAccount());
				celllist.add(user.getOrgName());
				celllist.add(user.getRoleName());
				maprole.put("id", user.getId());
				maprole.put("cell", celllist);
				list.add(maprole);
			}
			
			try {
				record = getRecordCount(entity);
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
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
	 *  保存或更新用户信息
	 */
	public void saveOrUpdate(User user) throws Exception{
		if(user != null && user.getId() != null){
			if(user.getNewPwd()!=null)
				user.setPassword(Encrypt.getMD5(user.getNewPwd()));
			update(user);
		}else{
			if(user.getNewPwd()!=null)
				user.setPassword(Encrypt.getMD5(user.getNewPwd()));
			save(user);
		}
	}

	
	/**
	 *  删除用户
	 */
	public void deleteUser(User user){
		super.getCommonDao().delete("user.delete", user);
	}
	

	/**
	 * 保存用户信息
	 */
	@Override
	public void save(User entity) throws Exception {
		super.getCommonDao().insert("user.insert", entity);
	}


	/**
	 * 更新用户信息
	 */
	@Override
	public void update(User entity) throws Exception {
		super.getCommonDao().update("user.update", entity);
	}

	/**
	 * 获取所有用户信息记录条数
	 */
	@Override
	public Long getAllRecordCount() throws Exception {
		return (Long) super.getCommonDao().selectObject("user.count");
	}
	
	/**
	 * 条件查询用户信息记录条数
	 */
	@Override
	public Long getRecordCount(User entity) throws Exception {
		return (Long) super.getCommonDao().selectObject("user.count", entity);
	}	

	/**
	 * 获取所有用户信息列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() throws Exception {
		return getCommonDao().selectList("user.select");
	}

    /**
     * 条件获取用户信息列表
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getList(User entity) throws Exception {
		return getCommonDao().selectList("user.select", entity);
	}	
	
	/**
	 * 删除用户
	 */
	@Override
	public void delete(User entity) throws Exception {
		if(userRoleMapperService.getRecordCount(new UserRoleMapper(null,entity.getId()))>0){
			userRoleMapperService.delete(new UserRoleMapper(null,entity.getId()));
		}
		super.getCommonDao().delete("user.deleteUser", entity);
	}

	/**
	 * 获取单个用户信息
	 */
	@Override
	public User getOneById(User entity) throws Exception {
		return (User) super.getCommonDao().selectObject("user.select", entity);
	}
	
	/**
	 * 删除所有用户信息
	 */
	@Override
	public void deleteAll() throws Exception {
		super.getCommonDao().delete("user.delete");
	}
	
	/**
	 * 批量删除用户信息
	 */
	@Override
	public void deleteByIn(User entity) throws Exception {
		super.getCommonDao().delete("user.deleteByIn");
	}

	@Override
	public void deleteByArr(User entity) throws Exception {
		User paramUser = new User();
		if (entity != null && entity.getIds() != null){
			for (String id : entity.getIds()){
				paramUser.setId(Long.parseLong(id));
				delete(paramUser);
			}
		}
	}
	

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
    /**
     *  保存用户与机构的关系
     *  @param userID
     *  @param orgs
     *  @author zhaofeng
     *  @version 1.0
     *  </pre>
     *  Created on :2012-6-12 下午4:04:50
     *  LastModified:
     *  History:
     *  </pre>
     */
    public void saveUserOrganization(Long userID,String orgs){
    	//删除原有关系
    	User user = new User();
    	user.setId(userID);
		super.getCommonDao().delete("user.deleteUserOrg", user);
		
		String[] orgsArray = orgs.split(",");
    	for(String s :orgsArray){
    		UserOrgMapper userOrgMapper = new UserOrgMapper();
    		userOrgMapper.setUserID(userID);
    		userOrgMapper.setOrgID(Long.valueOf(s));
    		//保存新关系
    		super.getCommonDao().save("user.saveUserOrgMapper", userOrgMapper);
    	}
    }
    /**
	 *  获取用户管理的组织信息
	 *  @param userID
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-6-13 上午9:24:33
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public String getUserOrganization(Long userID){
		        String json = null;
		        Integer orgNum = 0;
				List<Object> list = new ArrayList<Object>();
				Set<String> orgSet = new HashSet<String>();
				UserOrgMapper tempUserOrgMapper = new UserOrgMapper();
				tempUserOrgMapper.setUserID(userID);
				List<UserOrgMapper> userOrgList = super.getCommonDao().selectList("user.selectUserOrg",tempUserOrgMapper);
				
				//查询所有的机构
				List<Organization> organizationList = super.getCommonDao().selectList("organization.select",new Organization());
						for (Organization organization:organizationList) {
							orgSet.add(organization.getId().toString());
							if(orgSet.size() > orgNum){
								orgNum = orgSet.size();
								Map<String, Object> maprole = new Hashtable<String, Object>();
								//树形参数
								maprole.put("id", organization.getId());
								maprole.put("pId", organization.getParentId());
								maprole.put("name", organization.getName());
									//设置为选中状态
								/*	for (Organization tempOrganization:roleOrgList){
										String id = tempOrganization.getId().toString();
										if (id != null && id.equals(organization.getId().toString())){
											//设置为选中
											maprole.put("checked", true);
											break;
										}
									}*/
								
								for(UserOrgMapper userOrgMapper : userOrgList){
									if(userOrgMapper.getOrgID() == organization.getId()){
										maprole.put("checked", true);
										break;
									}
								}
								
								list.add(maprole);
							}
						}							
				json = JSONArray.fromObject(list).toString();
		        return json;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> selectUserList(User entity){
		return getCommonDao().selectList("user.selectUserList", entity);
	}

	public User getRoleByUid(User user) {
		return (User) getCommonDao().selectObject("user.selectRoleByUid", user);
		
	}
}