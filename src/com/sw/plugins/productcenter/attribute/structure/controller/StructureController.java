/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.plugins.productcenter.attribute.structure.controller
 * FileName: StructureController.java
 * @version 1.0
 * @author baokai
 * @created on 2012-5-14
 * @last Modified 
 * @history
 */
package com.sw.plugins.productcenter.attribute.structure.controller;

import java.net.URLDecoder;
import java.util.List;
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
import com.sw.core.data.entity.ProductSort;
import com.sw.core.initialize.PluginsConfigCache;
import com.sw.plugins.productcenter.attribute.structure.entity.Structure;
import com.sw.plugins.productcenter.attribute.structure.service.StructureService;
import com.sw.plugins.productcenter.product.maintain.entity.Product;
import com.sw.plugins.productcenter.product.maintain.service.ProductService;
import com.sw.plugins.usercenter.config.dynamic.entity.Dictionary;
import com.sw.plugins.usercenter.config.dynamic.service.DictionaryService;

/**
 *  类简要说明
 *  @author Administrator
 *  @version 1.0
 *  </pre>
 *  Created on :下午8:41:50
 *  LastModified:
 *  History:
 *  </pre>
 */
@Controller 
public class StructureController extends BaseController {
	
	private static Logger logger = Logger.getLogger(StructureController.class);
	
	@Resource
    private DictionaryService dictionaryService;
	@Resource
    private StructureService structureService;
	@Resource
    private ProductService productService;
	
	@RequestMapping("/productcenter/attribute/structure/list")
    public CommonModelAndView list(Structure structure,HttpServletRequest request,Map<String,Object> model){ 
        Object obj = new CommonModelAndView().getCurrentStatus(structure, request);
		if (obj != null){
			if (obj instanceof Structure){
				structure = (Structure)obj;
			}
		} 
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,structure);
		commonModelAndView.addObject("code", structure.getC());
        return commonModelAndView;
    }
	
	@RequestMapping("/productcenter/attribute/structure/create")
    public CommonModelAndView create(Structure structure,HttpServletRequest request){
        CommonModelAndView commonModelAndView = new CommonModelAndView(request,structure);
		commonModelAndView.addObject("Structure", structure);
		try {
			List<ProductSort> productSortList = (List<ProductSort>) PluginsConfigCache.getAllProductSortCache();
			//过滤已经存在属性的类型
			productSortList = structureService.filterProductType(productSortList);
			commonModelAndView.addObject("productSortList", productSortList);
			Dictionary dictionary = new Dictionary();
			dictionary.setDictSortValue(Constant.DICT_TYPE_PRODUCTSTRUCTUREVERIFY);
			List<Dictionary> productStructureVerifyList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("productStructureVerifyList", productStructureVerifyList);
			commonModelAndView.addObject("showTypeText", Constant.PRD_SHOWTYPE_TEXT);
			commonModelAndView.addObject("showTypeSelect", Constant.PRD_SHOWTYPE_SELECT);
			commonModelAndView.addObject("showTypeRadio", Constant.PRD_SHOWTYPE_RADIO);
			commonModelAndView.addObject("showTypeCheckbox", Constant.PRD_SHOWTYPE_CHECKBOX);
			commonModelAndView.addObject("showTypeTextArea", Constant.PRD_SHOWTYPE_TEXTAREA);
			commonModelAndView.addObject("showTypeDate", Constant.PRD_SHOWTYPE_DATE);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
        return commonModelAndView; 
    }  
	
	
	@RequestMapping("/productcenter/attribute/structure/modify")
    public CommonModelAndView modify(Structure structure,HttpServletRequest request,Map<String,Object> model){
		//导航只判断id是否为空，为保证导航连接可用，所以将产品类型ID设为ID
		//ID为空则说明是从list页面进来的
		if(structure.getId()==null)
			structure.setId(structure.getProductTypeId());
		//产品类型ID为空则说名是从导航进来的，则把ID设置回产品类型ID
		if(structure.getProductTypeId()==null)
			structure.setProductTypeId(structure.getId());
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,structure);
		commonModelAndView.addObject("code", structure.getC());
		model.put("structure", structure);
		try {
			structure.setId(null);
			List<Structure> structureList = structureService.getList(structure);
			commonModelAndView.addObject("structureList", structureList);
			List<ProductSort> productSortList = (List<ProductSort>) PluginsConfigCache.getAllProductSortCache();
			commonModelAndView.addObject("productSortList", productSortList);
			Dictionary dictionary = new Dictionary();
			dictionary.setDictSortValue(Constant.DICT_TYPE_PRODUCTSTRUCTUREVERIFY);
			List<Dictionary> productStructureVerifyList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("productStructureVerifyList", productStructureVerifyList);
			commonModelAndView.addObject("showTypeText", Constant.PRD_SHOWTYPE_TEXT);
			commonModelAndView.addObject("showTypeSelect", Constant.PRD_SHOWTYPE_SELECT);
			commonModelAndView.addObject("showTypeRadio", Constant.PRD_SHOWTYPE_RADIO);
			commonModelAndView.addObject("showTypeCheckbox", Constant.PRD_SHOWTYPE_CHECKBOX);
			commonModelAndView.addObject("showTypeTextArea", Constant.PRD_SHOWTYPE_TEXTAREA);
			commonModelAndView.addObject("showTypeDate", Constant.PRD_SHOWTYPE_DATE);
			//判断此产品类型结构是否被引用
			Product product = new Product();
			product.setPrdType(structure.getProductTypeId());
			if(productService.isUseProductType(product)){
				commonModelAndView.addObject("isUse",true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
        return commonModelAndView;
    }  
	
	@RequestMapping(value="/productcenter/attribute/structure/save",method = RequestMethod.POST)
	public CommonModelAndView save(@Valid Structure structure,BindingResult result,Map<String,Object> model) {
		String viewName = null;
		try {
			if(structure.getProductTypeId() != null){
				structureService.saveOrUpdate(structure);
				viewName = this.SUCCESS;
			}else{
				viewName = this.ERROR;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,structure,messageSource);
		return commonModelAndView;
	}
	
	@RequestMapping("/productcenter/attribute/structure/grid")
	public CommonModelAndView json(Structure structure,HttpServletRequest request){
		Map<String, Object> map = structureService.getGrid(structure); 
		return new CommonModelAndView("jsonView",map,structure,request); 
	}
	
	@RequestMapping("/productcenter/attribute/structure/delete")
    public CommonModelAndView delete(Structure structure,HttpServletRequest request){
		String viewName = null;		
		try {
			Product product = new Product();
			product.setPrdType(structure.getProductTypeId());
			if(productService.isUseProductType(product)){
				viewName = this.ERROR;
			}else{
				String productTypeName = URLDecoder.decode(structure.getProductTypeName(), Constant.ENCODING);
				structure.setProductTypeName(productTypeName);
				if(structure!=null && structure.getProductTypeId()!=null){
					structureService.delete(structure);
				}
				viewName = this.SUCCESS;
			}
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,structure,messageSource);
		return commonModelAndView;					
    }  
	
	@RequestMapping("/productcenter/attribute/structure/detail")
    public CommonModelAndView detail(Structure structure,HttpServletRequest request,Map<String,Object> model){
		//导航只判断id是否为空，为保证导航连接可用，所以将产品类型ID设为ID
		//ID为空则说明是从list页面进来的
		if(structure.getId()==null)
			structure.setId(structure.getProductTypeId());
		//产品类型ID为空则说名是从导航进来的，则把ID设置回产品类型ID
		if(structure.getProductTypeId()==null)
			structure.setProductTypeId(structure.getId());
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,structure);
		commonModelAndView.addObject("code", structure.getC());
		model.put("structure", structure);
		try {
			//查询只通过产品类型ID查询，把id设为null
			structure.setId(null);
			List<Structure> structureList = structureService.getListByOrder(structure);
			commonModelAndView.addObject("structureList", structureList);
			Dictionary dictionary = new Dictionary();
			dictionary.setDictSortValue(Constant.DICT_TYPE_PRODUCTSTRUCTUREVERIFY);
			List<Dictionary> productStructureVerifyList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("productStructureVerifyList", productStructureVerifyList);
			commonModelAndView.addObject("showTypeText", Constant.PRD_SHOWTYPE_TEXT);
			commonModelAndView.addObject("showTypeSelect", Constant.PRD_SHOWTYPE_SELECT);
			commonModelAndView.addObject("showTypeRadio", Constant.PRD_SHOWTYPE_RADIO);
			commonModelAndView.addObject("showTypeCheckbox", Constant.PRD_SHOWTYPE_CHECKBOX);
			commonModelAndView.addObject("showTypeTextArea", Constant.PRD_SHOWTYPE_TEXTAREA);
			commonModelAndView.addObject("showTypeDate", Constant.PRD_SHOWTYPE_DATE);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
        return commonModelAndView;
    }  

	@Override
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
