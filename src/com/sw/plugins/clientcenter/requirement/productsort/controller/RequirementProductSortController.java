package com.sw.plugins.clientcenter.requirement.productsort.controller;

import java.net.URLDecoder;
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
import com.sw.core.util.CommonUtil;
import com.sw.plugins.clientcenter.requirement.investment.entity.Investment;
import com.sw.plugins.clientcenter.requirement.productsort.entity.RequirementProductSort;
import com.sw.plugins.clientcenter.requirement.productsort.service.RequirementProductSortService;

/**
 * 产品类型控制器，负责产品类型的添加，修改，删除，查询等功能
 * @author Administrator
 *
 */
@Controller 
public class RequirementProductSortController extends BaseController {

	private static Logger logger = Logger.getLogger(RequirementProductSortController.class);
	
	@Resource
	private RequirementProductSortService requirementProductSortService;
	
	/**
	 * 投资需求产品类型列表
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping("/clientcenter/requirement/productsort/list")
    public CommonModelAndView list(RequirementProductSort requirementProductSort,HttpServletRequest request){ 
		Object obj = new CommonModelAndView().getCurrentStatus(requirementProductSort, request);
		if (obj != null){
			if (obj instanceof RequirementProductSort){
				requirementProductSort = (RequirementProductSort)obj;
			}
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, requirementProductSort);
		commonModelAndView.addObject("code", requirementProductSort.getC());
		commonModelAndView.addObject("requirementProductSort", requirementProductSort);
        return commonModelAndView;
    }  
	
	/**
	 * 投资需求产品类型创建页面
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping("/clientcenter/requirement/productsort/create")
    public CommonModelAndView create(RequirementProductSort requirementProductSort,HttpServletRequest request){
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, requirementProductSort);
        return commonModelAndView; 
    } 
	
	/**
	 * 投资需求产品类型创建操作
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/clientcenter/requirement/productsort/save",method = RequestMethod.POST)
	public CommonModelAndView save(@Valid RequirementProductSort requirementProductSort,BindingResult result,Map<String,Object> model,HttpServletRequest request) {
		CommonModelAndView commonModelAndView = null;
		if (result.hasErrors()) {
			commonModelAndView = new CommonModelAndView(requirementProductSort);
			return commonModelAndView;
		}
		//视图名
		String viewName = null;
		try {
			if(requirementProductSort != null && requirementProductSort.getId() != null){
				requirementProductSortService.update(requirementProductSort);
			}else{
				requirementProductSortService.save(requirementProductSort);
			}
			viewName = this.SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}
		commonModelAndView = new CommonModelAndView(viewName,requirementProductSort,messageSource);
		return commonModelAndView;		
	}
	
	/**
	 * 跳转到投资需求产品类型修改页面
	 * @param code
	 * @param consult
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/requirement/productsort/modify")
    public CommonModelAndView modify(RequirementProductSort requirementProductSort,HttpServletRequest request,Map<String,Object> model){
		String code = requirementProductSort.getC();
		if (requirementProductSort.getId() != null){
			try {
				requirementProductSort = requirementProductSortService.getOneById(requirementProductSort);
				
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		requirementProductSort.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,requirementProductSort);
		commonModelAndView.addObject("requirementProductSort", requirementProductSort);
        return commonModelAndView; 
    }
	
	/**
	 * 投资需求产品用户列表，json格式
	 * @param consult
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/requirement/productsort/userList")
    public CommonModelAndView userList(RequirementProductSort requirementProductSort,HttpServletRequest request){
		//视图名
		String viewName = null;		
		try {
			Investment investment = new Investment();
			investment.setProductId(requirementProductSort.getId());
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, requirementProductSort, messageSource);
		return commonModelAndView;			
    }
	
	/**
	 * 投资需求产品类型删除功能，json格式
	 * @param consult
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/requirement/productsort/delete")
    public CommonModelAndView delete(RequirementProductSort requirementProductSort,HttpServletRequest request){
		//视图名
		String viewName = null;		
		try {
			String name = URLDecoder.decode(requirementProductSort.getName(), Constant.ENCODING);
			requirementProductSortService.deleteByArr(requirementProductSort);
			requirementProductSort.setName(name);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, requirementProductSort, messageSource);
		return commonModelAndView;			
    }
	
	/**
	 *  查询投资需求产品类型信息 返回json格式
	 *  @param consult
	 *  @param request
	 *  @return
	 *  @author Administrator
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-4-27 下午2:49:45
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/clientcenter/requirement/productsort/grid")
	public CommonModelAndView json(RequirementProductSort requirementProductSort,HttpServletRequest request){
		Map<String, Object> map = null;
		requirementProductSort.setName(CommonUtil.convertSearchSign(requirementProductSort.getName()));
		try {
			map = requirementProductSortService.getGrid(requirementProductSort); 
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}		
		return new CommonModelAndView("jsonView",map); 
	}

	@Override
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		return null;
	}
}
