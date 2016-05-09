package com.sw.plugins.clientcenter.requirement.investment.controller;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sw.core.common.Constant;
import com.sw.core.controller.BaseController;
import com.sw.core.controller.CommonModelAndView;
import com.sw.core.data.entity.BaseEntity;
import com.sw.plugins.clientcenter.requirement.investment.entity.Investment;
import com.sw.plugins.clientcenter.requirement.investment.service.InvestmentService;
import com.sw.plugins.clientcenter.requirement.productsort.entity.RequirementProductSort;
import com.sw.plugins.clientcenter.requirement.productsort.service.RequirementProductSortService;

/**
 * 投资需求控制器，负责投资需求的添加，修改，删除，查询等功能
 * @author Administrator
 *
 */
@Controller
public class InvestmentController extends BaseController {
	
	private static Logger logger = Logger.getLogger(InvestmentController.class);
	
	@Resource
	private InvestmentService investmentService;
	@Resource
    private RequirementProductSortService requirementProductSortService;
	
	/**
     * 投资需求列表方法
     * @param code
     * @param request
     * @return
     */
	@RequestMapping("/clientcenter/requirement/investment/list")
    public CommonModelAndView list(Investment investment,HttpServletRequest request){ 
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, investment);
		commonModelAndView.addObject("code", investment.getC());
		commonModelAndView.addObject("investment" ,investment);
		List<RequirementProductSort> productTypeList;
		try {
			//取投资需求产品类型列表
			productTypeList = requirementProductSortService.getAll();
			commonModelAndView.addObject("productTypeList", productTypeList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
        return commonModelAndView;
    }
	
	/**
     * 投资需求回收站页面
     * @param code
     * @param request
     * @return
     */
	@RequestMapping("/clientcenter/requirement/investment/recycle")
    public CommonModelAndView recycle(Investment investment,HttpServletRequest request){ 
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, investment);
		commonModelAndView.addObject("code", investment.getC());
		commonModelAndView.addObject("investment" ,investment);
		List<RequirementProductSort> productTypeList;
		try {
			//取投资需求产品类型列表
			productTypeList = requirementProductSortService.getAll();
			commonModelAndView.addObject("productTypeList", productTypeList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
        return commonModelAndView;
    }
	
	/**
	 * 跳转到投资需求详细页面
	 * @param code
	 * @param investment
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/requirement/investment/detail")
    public CommonModelAndView detail(Investment investment,HttpServletRequest request){
		String code = investment.getC();
		try {
			investment = investmentService.getOneById(investment);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		investment.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, investment); 
		commonModelAndView.addObject("investment", investment);
        return commonModelAndView; 
    }
	
	/**
	 * 投资需求删除功能，json格式
	 * @param investment
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/requirement/investment/*delete")
    public CommonModelAndView delete(Investment investment,HttpServletRequest request){
		//视图名
		String viewName = null;		
		try {
			String name = URLDecoder.decode(investment.getName(), Constant.ENCODING);
			investment.setName(name);
			investmentService.deleteByArr(investment);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, investment, messageSource);
		return commonModelAndView;			
    }
	
	/**
	 * 查询投资需求,返回json格式
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/requirement/investment/grid")
	public CommonModelAndView json(Investment investment,HttpServletRequest request){
		Map<String, Object> map = investmentService.getGrid(investment); 
		return new CommonModelAndView("jsonView",map); 
	}

	@Override
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		return null;
	}

}
