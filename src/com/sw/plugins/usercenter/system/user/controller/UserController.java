package com.sw.plugins.usercenter.system.user.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sw.core.common.Constant;
import com.sw.core.controller.BaseController;
import com.sw.core.controller.CommonModelAndView;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.util.CommonUtil;
import com.sw.core.util.Encrypt;
import com.sw.plugins.usercenter.system.organization.entity.Organization;
import com.sw.plugins.usercenter.system.organization.service.OrganiztionService;
import com.sw.plugins.usercenter.system.role.entity.Role;
import com.sw.plugins.usercenter.system.role.service.RoleService;
import com.sw.plugins.usercenter.system.user.entity.User;
import com.sw.plugins.usercenter.system.user.service.UserService;

/**
 * 用户控制器，进行用户信息的添加，修改，删除，查询等拦截控制
 * @author Administrator
 *
 */
@Controller  
public class UserController extends BaseController{  
	
	private static Logger logger = Logger.getLogger(UserController.class);
	
    @Resource
    private UserService userService;
    
    @Resource
    private RoleService roleService;
    
    
    @Resource
    private OrganiztionService organiztionService;
    
    /**
     * 用户列表方法
     * @param code
     * @param role
     * @param request
     * @return
     */
	@RequestMapping("/usercenter/system/user/list")
    public CommonModelAndView list(User user,HttpServletRequest request,Map<String,Object> model){ 
		Object obj = new CommonModelAndView().getCurrentStatus(user, request);
		if (obj != null){
			if (obj instanceof User){
				user = (User)obj;
			}
		}
		if(user.getName()!=null)
			user.setName(user.getName().trim());
		if(user.getAccount()!=null)
			user.setAccount(user.getAccount().trim());
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,user);
		commonModelAndView.addObject("code", user.getC());
		model.put("user", user);
        return commonModelAndView;
    }  
	
	/**
	 * 用户创建操作
	 * @param role
	 * @param result
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/usercenter/system/user/save",method = RequestMethod.POST)
	public CommonModelAndView save(@Valid User user,BindingResult result,Map<String,Object> model,HttpServletRequest request) {
		boolean resultErrorFlag = result.hasErrors();
		//boolean pwNullErrorFlag = user.getNewPwd()==null && user.getConfirmPwd()==null;
		boolean pwErrorFlag = true;
		if(user.getNewPwd()!=null && user.getConfirmPwd()!=null){
			pwErrorFlag = !("").equals(user.getNewPwd()) && !("").equals(user.getConfirmPwd()) && user.getNewPwd().equals(user.getConfirmPwd());
		}
		if (resultErrorFlag || !pwErrorFlag) {
			//新密码不能为空
			if (user.getNewPwd() == null || user.getNewPwd().equals("")){
				result.rejectValue("newPwd", "NotEmpty.user.newPwd");
			}
			//确认密码不能为空
			if (user.getConfirmPwd() == null || user.getConfirmPwd().equals("")){
				result.rejectValue("confirmPwd", "NotEmpty.user.confirmPwd");
			}
			//判断新密码和确认密码是否一致
			if (result.getFieldErrors("confirmPwd").size()==0 && !user.getNewPwd().equals(user.getConfirmPwd())){
				result.rejectValue("confirmPwd", "user.confirmPwd.disaccord");
			}
			CommonModelAndView mav = new CommonModelAndView(user);
			List<Organization> orgList = new ArrayList<Organization>();
			List<Role> roleList = new ArrayList<Role>();
			try {
				Organization organization = new Organization();
				organization.setId(user.getOrgID());
				organization = organiztionService.getOneById(organization);
				mav.addObject("orgName", organization.getName());
				
				roleList = roleService.getList(new Role());
				List list = new ArrayList();
				for (Role r : roleList) {
					if(!list.contains(r.getOrgId())){
						list.add(r.getOrgId());
					}
				}
				String[] ids = (String[])list.toArray(new String[list.size()]);
				organization.setIds(ids);
				orgList = organiztionService.getOrgListByRole(organization);
				mav.addObject("orgList", orgList);
				mav.addObject("roleList", roleList);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			return mav;
		}
		//视图名
		String viewName = null;
		try {
			userService.saveOrUpdate(user);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage());
			user.setErrorMsg(e.getMessage());
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,user,messageSource);
		return commonModelAndView;		
	}
	

	/**
	 * 跳转到用户创建页面
	 * @param code
	 * @param role
	 * @param request
	 * @return
	 * @author 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/usercenter/system/user/create")
    public CommonModelAndView create(User user,HttpServletRequest request){
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,user);
		Organization organization = new Organization();
		List<Organization> orgList = new ArrayList<Organization>();
		List<Role> roleList = new ArrayList<Role>();
		try {
			roleList = roleService.getList(new Role());
			List list = new ArrayList();
			for (Role r : roleList) {
				if(!list.contains(r.getOrgId())){
					list.add(r.getOrgId());
				}
			}
			String[] ids = (String[])list.toArray(new String[list.size()]);
			organization.setIds(ids);
			orgList = organiztionService.getOrgListByRole(organization);
			
			commonModelAndView.addObject("orgList", orgList);
			commonModelAndView.addObject("roleList", roleList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
        return commonModelAndView; 
    }  
	
	/**
	 * 跳转到用户修改页面
	 * @param code
	 * @param role
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/usercenter/system/user/modify")
    public CommonModelAndView modify(User user,HttpServletRequest request){
		String code = user.getC();
		Organization organization = new Organization();
		List<Organization> orgList = new ArrayList<Organization>();
		List<Role> roleList = new ArrayList<Role>();
		if (user.getId() != null){
			try {
				user = userService.getOneById(user);
				//获取机构名称
				organization.setId(user.getOrgID());
				organization = organiztionService.getOneById(organization);
				
				roleList = roleService.getList(new Role());
				List list = new ArrayList();
				for (Role r : roleList) {
					if(!list.contains(r.getOrgId())){
						list.add(r.getOrgId());
					}
				}
				String[] ids = (String[])list.toArray(new String[list.size()]);
				organization.setIds(ids);
				orgList = organiztionService.getOrgListByRole(organization);
				
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		user.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,user);
		commonModelAndView.addObject("user", user);
		commonModelAndView.addObject("orgList", orgList);
		commonModelAndView.addObject("roleList", roleList);
	
		if(organization != null){
			commonModelAndView.addObject("orgName", organization.getName());
		}
        return commonModelAndView; 
    } 	
	
	/**
	 * 强制修改密码跳转
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/system/user/cmpwd")
    public CommonModelAndView cmpwd(User user,HttpServletRequest request){
		String code = user.getC();
		if (user.getId() != null){
			try {
				user = userService.getOneById(user);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		user.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,user);
		commonModelAndView.addObject("user", user);
        return commonModelAndView; 
    } 
	
	/**
	 * 强制修改密码保存
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/system/user/cmpwdSave")
    public CommonModelAndView cmpwdSave(@Valid User user,BindingResult result,Map<String,Object> model) {
		String newPwd = user.getNewPwd();
		String confirmPwd = user.getConfirmPwd();
		//新密码不能为空
		if (newPwd == null || newPwd.equals("")){
			result.rejectValue("newPwd", "NotEmpty.user.newPwd");
			return new CommonModelAndView(user);				
		}
		//确认密码不能为空
		if (confirmPwd == null || confirmPwd.equals("")){
			result.rejectValue("confirmPwd", "NotEmpty.user.confirmPwd");
			return new CommonModelAndView(user);				
		}
		//判断新密码和确认密码是否一致
		if (!newPwd.equals(confirmPwd)){
			result.rejectValue("confirmPwd", "user.confirmPwd.disaccord");
			return new CommonModelAndView(user);					
		}
		//视图名
		String viewName = null;		
		try {
			User tempUser = new User();
			tempUser = userService.getOneById(user);
			if(tempUser != null && tempUser.getName() != null){
				user.setName(tempUser.getName());
			}
			userService.saveOrUpdate(user);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}		
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,user,messageSource);
		return commonModelAndView;	
    }	
	
	/**
	 * 自助修改密码
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/system/user/shmpwd")
    public CommonModelAndView shmpwd(User user,HttpServletRequest request){
		String code = user.getC();
		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (object instanceof User){
			user = (User)object;
		}
		user.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,user);
		commonModelAndView.addObject("user", user);	
        return commonModelAndView; 
    } 	
	/**
	 * 自助修改密码保存
	 * @param user
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping("/usercenter/system/user/shmpwdSave")
    public CommonModelAndView shmpwdSave(@Valid User user,BindingResult result,Map<String,Object> model) {
		//当前登录用户密码
		String currPwd = null;
		//用户输入的原始密码
		String oldPwd = user.getPassword();
		String newPwd = user.getNewPwd();
		String confirmPwd = user.getConfirmPwd();		
		//获取登录用户信息
		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (object instanceof User){
			user.setId(((User)object).getId());
			user.setType(((User) object).getType());
			try {
				User u = new User();
				u.setId(((User)object).getId());
				u.setType(null);
				currPwd = userService.getOneById(u).getPassword();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		//原始密码不能为空
		if (oldPwd == null || oldPwd.equals("")){
			result.rejectValue("password", "NotEmpty.user.password");
			return new CommonModelAndView(user);			
		}	
		//原始密码输入不正确
		if (!currPwd.equals(Encrypt.getMD5(oldPwd))){
			result.rejectValue("password", "user.old.password");
			return new CommonModelAndView(user);					
		}			
		//新密码不能为空
		if (newPwd == null || newPwd.equals("")){
			result.rejectValue("newPwd", "NotEmpty.user.newPwd");
			return new CommonModelAndView(user);					
		}
		//确认密码不能为空
		if (confirmPwd == null || confirmPwd.equals("")){
			result.rejectValue("confirmPwd", "NotEmpty.user.confirmPwd");
			return new CommonModelAndView(user);					
		}
		//判断新密码和确认密码是否一致
		if (!newPwd.equals(confirmPwd)){
			result.rejectValue("confirmPwd", "user.confirmPwd.disaccord");
			return new CommonModelAndView(user);				
		}
		//视图名
		String viewName = null;		
		try {
			User tempUser = new User();
			tempUser = userService.getOneById(user);
			if(tempUser != null && tempUser.getName() != null){
				user.setName(tempUser.getName());
			}
			userService.saveOrUpdate(user);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}		
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,user,messageSource);
		return commonModelAndView;	
    }	
	/**
	 * 用户删除功能，json格式
	 * @param role
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/system/user/delete")
    public CommonModelAndView delete(User user,HttpServletRequest request){
		//视图名
		String viewName = null;		
		try {
			String name = URLDecoder.decode(user.getName(), Constant.ENCODING);
			user.setName(name);
			if(user!=null && user.getId()!=null){
				userService.delete(user);
			}else if(user!=null && user.getIds()!=null){
				userService.deleteByArr(user);
			}
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,user,messageSource);
		return commonModelAndView;			
    }  
	
	/**
	 * 保存用户角色信息
	 * @param user
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/usercenter/system/user/saveBind",method = RequestMethod.POST)
	public CommonModelAndView saveAuth(@Valid User user,BindingResult result,Map<String,Object> model) {
		//视图名
		String viewName = null;		
		try {
			User tempUser = new User();
			tempUser = userService.getOneById(user);
			if(tempUser != null && tempUser.getName() != null){
				user.setName(tempUser.getName());
			}
			userService.saveBind(user);
			viewName = this.SUCCESS;
			
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,user,messageSource);
		return commonModelAndView;			
	}	
	
	/**
	 * 用户角色跳转
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/system/user/bind")
    public CommonModelAndView auth(User user,HttpServletRequest request){
		String code = user.getC();
		if (user.getId() != null){
			try {
				user = userService.getOneById(user);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		user.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,user);
		commonModelAndView.addObject("user", user);
		commonModelAndView.addObject("json", userService.getUserRole(user));
        return commonModelAndView; 
    }	
	
	/**
	 * 查询用户信息 返回json格式
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/system/user/grid")
	public CommonModelAndView json(User user,HttpServletRequest request){
		user.setName(CommonUtil.convertSearchSign(user.getName()));
		Map<String, Object> map = userService.getGrid(user); 
		return new CommonModelAndView("jsonView",map,user,request);
	}

	/**
	 *  跳转到选择管理机构页面
	 *  @param user
	 *  @param request
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-6-13 下午2:54:12
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/usercenter/system/user/source")
	public CommonModelAndView sourceAuth(User user,HttpServletRequest request){
		String code = user.getC();
		Organization organization = new Organization();
		if (user.getId() != null){
			try {
				user = userService.getOneById(user);
				//获取机构名称
				organization.setId(user.getOrgID());
				organization = organiztionService.getOneById(organization);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		user.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,user);
		commonModelAndView.addObject("user", user);
		if(organization != null){
			commonModelAndView.addObject("orgName", organization.getName());
		}
		commonModelAndView.addObject("json", userService.getUserOrganization(user.getId()));
        return commonModelAndView; 
	}
	
	/**
	 *  保存管理的机构
	 *  @param user
	 *  @param request
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-6-13 下午2:56:13
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/usercenter/system/user/savesourceauth")
	public CommonModelAndView saveSourceAuth(User user,HttpServletRequest request){
		String viewName = null;
		try {
			//获取页面tree中选中的节点,数据以逗号分隔
			String orgs = request.getParameter("org");
			//保存用户与机构的关系
			userService.saveUserOrganization(user.getId(),orgs);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}		
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,user,messageSource);
		return commonModelAndView;	
	}
	
	@Override
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}
