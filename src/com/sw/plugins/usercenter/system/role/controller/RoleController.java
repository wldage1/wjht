package com.sw.plugins.usercenter.system.role.controller;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sw.core.common.Constant;
import com.sw.core.controller.BaseController;
import com.sw.core.controller.CommonModelAndView;
import com.sw.core.data.entity.BaseEntity;
import com.sw.plugins.usercenter.system.organization.entity.Organization;
import com.sw.plugins.usercenter.system.role.entity.Role;
import com.sw.plugins.usercenter.system.role.service.RoleService;

/**
 * 角色管理控制器，负责角色的添加，修改，删除，查询等功能拦截处理
 * @author Administrator
 *
 */
@Controller  
public class RoleController extends BaseController{ 
	
	private static Logger logger = Logger.getLogger(RoleController.class);
	
    @Resource
    private RoleService roleService;
    
    /**
     * 角色列表方法
     * @param code
     * @param role
     * @param request
     * @return
     */
	@RequestMapping("/usercenter/system/role/list")
    public CommonModelAndView list(Role role,HttpServletRequest request,Map<String,Object> model){ 
		Object obj = new CommonModelAndView().getCurrentStatus(role, request);
		if (obj != null){
			if (obj instanceof Role){
				role = (Role)obj;
			}
		}	
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,role);
    	commonModelAndView.addObject("code", role.getC());
    	model.put("role", role);
        return commonModelAndView;
    }  
	
	/**
	 * 角色创建操作
	 * @param role
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/usercenter/system/role/save",method = RequestMethod.POST)
	public CommonModelAndView save(@Valid Role role,BindingResult result,Map<String,Object> model) {
		if (result.hasErrors()) {
			return new CommonModelAndView(role);
		}
		//视图名
		String viewName = null;
		try {
			roleService.saveOrUpdate(role);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			role.setErrorMsg(e.getMessage());
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,role,messageSource);
		return commonModelAndView;
	}
	
	/**
	 * 角色权限保存
	 * @param role
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/usercenter/system/role/saveAuth",method = RequestMethod.POST)
	public CommonModelAndView saveAuth(@Valid Role role,BindingResult result,Map<String,Object> model) {
		//视图名
		String viewName = null;		
		try {
			Role tempRole = new Role();
			tempRole = roleService.getOneById(role);
			if(tempRole!= null && tempRole.getName() != null){
				role.setName(tempRole.getName());
			}
			roleService.saveAuth(role);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,role,messageSource);
		return commonModelAndView;			
	}	
	
	
	/**
	 * 角色资源权限保存
	 * @param role
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/usercenter/system/role/saveSourceAuth",method = RequestMethod.POST)
	public CommonModelAndView saveSourceAuth(@Valid Role role,BindingResult result,Map<String,Object> model) {
		//视图名
		String viewName = null;		
		try {
			Role tempRole = new Role();
			tempRole = roleService.getOneById(role);
			if(tempRole!= null && tempRole.getName() != null){
				role.setName(tempRole.getName());
			}
			roleService.saveOrgAuth(role);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,role,messageSource);
		return commonModelAndView;			
	}	

	/**
	 * 跳转到角色创建页面
	 * @param code
	 * @param role
	 * @param request
	 * @return
	 * @author 
	 */
	@RequestMapping("/usercenter/system/role/create")
    public CommonModelAndView create(Role role,HttpServletRequest request){
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,role);
		commonModelAndView.addObject("role", role);
		try {
			List<Organization> orgList = roleService.getOrgList();
			commonModelAndView.addObject("orgList", orgList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
        return commonModelAndView; 
    }  
	
	/**
	 * 跳转到角色修改页面
	 * @param code
	 * @param role
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/system/role/modify")
    public CommonModelAndView modify(Role role,HttpServletRequest request){
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,role);
		String code = role.getC();
		if (role.getId() != null){
			try {
				role = roleService.getOneById(role);
				List<Organization> orgList = roleService.getOrgList();
				commonModelAndView.addObject("orgList", orgList);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		role.setC(code);
		commonModelAndView.addObject("role", role);
        return commonModelAndView; 
    } 	
	
	/**
	 * 跳转到角色授权页面
	 * @param role
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/system/role/auth")
    public CommonModelAndView auth(Role role,HttpServletRequest request){
		String code = role.getC();
		try {
			//如果有状态记录，则获取记录状态为当前值
			//否者为空
			Object obj = new CommonModelAndView().getCurrentStatus(role, request);
			if (obj != null){
				if (obj instanceof Role){
					role = (Role)obj;
				}
			}
			role = roleService.getOneById(role);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		role.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,role);
		commonModelAndView.addObject("role", role);
		commonModelAndView.addObject("json", roleService.getRoleAuthorization(role));
        return commonModelAndView; 
    }	
	
	
	/**
	 * 跳转到资源授权页面
	 * @param role
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/system/role/source")
	public CommonModelAndView source(Role role,HttpServletRequest request){
		String code = role.getC();
		try {
			//如果有状态记录，则获取记录状态为当前值
			//否者为空
			Object obj = new CommonModelAndView().getCurrentStatus(role, request);
			if (obj != null){
				if (obj instanceof Role){
					role = (Role)obj;
				}
			}
			role = roleService.getOneById(role);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		role.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,role);
		commonModelAndView.addObject("role", role);
		commonModelAndView.addObject("json", roleService.getOrgAuthorization(role));
		return commonModelAndView; 
	}	
	
	/**
	 * 角色删除功能，json格式
	 * @param role
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/system/role/delete")
    public CommonModelAndView delete(Role role,HttpServletRequest request){
		//视图名
		String viewName = null;	
		try {
			String name = URLDecoder.decode(role.getName(), Constant.ENCODING);
			role.setName(name);
			if(role!=null && role.getId()!=null){
				roleService.delete(role);
			}else if(role!=null && role.getIds()!=null){
				roleService.deleteByArr(role);
			}
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,role,messageSource);
		return commonModelAndView;						
    }  
	
	/**
	 * 查询角色信息 返回json格式
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/system/role/grid")
	public CommonModelAndView json(Role role,HttpServletRequest request){
		Map<String, Object> map = roleService.getGrid(role);
		return new CommonModelAndView("jsonView",map,role,request); 
	}

	@Override
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}
