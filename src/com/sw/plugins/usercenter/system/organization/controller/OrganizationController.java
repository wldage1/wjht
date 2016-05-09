package com.sw.plugins.usercenter.system.organization.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sw.core.common.Constant;
import com.sw.core.controller.BaseController;
import com.sw.core.controller.CommonModelAndView;
import com.sw.core.data.entity.BaseEntity;
import com.sw.plugins.usercenter.system.organization.entity.Organization;
import com.sw.plugins.usercenter.system.organization.service.OrganiztionService;
import com.sw.plugins.usercenter.system.role.entity.Role;
import com.sw.plugins.usercenter.system.user.entity.UserOrgMapper;

/**
 * 机构管理控制器，负责机构的添加，修改，删除，查询等功能拦截处理
 * @author Administrator
 *
 */
@Controller  
public class OrganizationController extends BaseController{
	
	private static Logger logger = Logger.getLogger(OrganizationController.class);
	
    @Resource
    private OrganiztionService organiztionService;
    /**
     * 跳转到机构列表页面
     * @param code
     * @param organization
     * @param request
     * @return
     */
	@RequestMapping("/usercenter/system/organization/list")
    public CommonModelAndView list(Organization organization,HttpServletRequest request){ 
		Object obj = new CommonModelAndView().getCurrentStatus(organization, request);
		if (obj != null){
			if (obj instanceof Role){
				organization = (Organization)obj;
			}
		} 
    	CommonModelAndView commonModelAndView = new CommonModelAndView(request,organization);
    	commonModelAndView.addObject("code", organization.getC());
        return commonModelAndView;
    }  
	
	/**
	 * 机构创建操作
	 * @param organization
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/usercenter/system/organization/save",method = RequestMethod.POST)
	public CommonModelAndView save(@Valid Organization organization,BindingResult result,Map<String,Object> model) {
		if (result.hasErrors()) {
			return new CommonModelAndView(organization);
		}
		//修改时验证，父机构不能为自己
		if (organization.getId() != null){
			long id = organization.getId().longValue();
			long pid = organization.getParentId().longValue();
			if (id == pid){
				result.rejectValue("parentName", "organization.noself");
				return new CommonModelAndView(organization);	
			}
		}
		//视图名
		String viewName = null;
		try {
			organiztionService.saveOrUpdate(organization);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage());
			organization.setErrorMsg(e.getMessage());
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,organization,messageSource);
		return commonModelAndView;
	}
	

	/**
	 * 跳转到机构创建页面
	 * @param code
	 * @param organization
	 * @param request
	 * @return
	 * @author 
	 */
	@RequestMapping("/usercenter/system/organization/create")
    public CommonModelAndView create(Organization organization,HttpServletRequest request){
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,organization);
		commonModelAndView.addObject("organization", organization);
        return commonModelAndView; 
    }  
	
	/**
	 * 跳转到机构修改页面
	 * @param code
	 * @param organization
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/system/organization/modify")
    public CommonModelAndView modify(Organization organization,HttpServletRequest request){
		String code = organization.getC();
		if (organization.getId() != null){
			try {
				organization = organiztionService.getOneById(organization);
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}
		organization.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,organization);
		commonModelAndView.addObject("organization", organization);
        return commonModelAndView; 
    } 	
	
	/**
	 * 机构删除功能，json格式
	 * @param organization
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/system/organization/delete")
    public CommonModelAndView delete(Organization organization,HttpServletRequest request){
		//视图名
		String viewName = null;		
		try {
			String name = URLDecoder.decode(organization.getName(), Constant.ENCODING);
			organization.setName(name);
			if(organiztionService.getUserCount(organization) > 0){
				Map map = new HashMap();
				map.put(Constant.STATUS, "ERROR");
				return new CommonModelAndView("jsonView",map);
			}else{
				organiztionService.delete(organization);
				viewName = this.SUCCESS;
			}
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,organization,messageSource);
		return commonModelAndView;				
    }  
	
	/**
	 * 查询结构信息 返回json格式
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/system/organization/tree")
	public CommonModelAndView json(String nodeid,HttpServletRequest request){
		Map<String, Object> map = organiztionService.getTree(nodeid);
		return new CommonModelAndView("jsonView",map); 
	}
	
	/**
	 * 获取下拉列表树信息
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/system/organization/stree")
	public CommonModelAndView stree(String id,HttpServletRequest request){
		Map<String, Object> map = organiztionService.getSelectTree(id); 
		return new CommonModelAndView("jsonView",map); 		
	}

	@Override
	@RequestMapping("/usercenter/system/organization/uploadfile")
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		return organiztionService.upload(baseEntity, request);
	}
	
}
