package com.sw.plugins.usercenter.config.dynamic.controller;

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
import com.sw.core.data.entity.DictionarySort;
import com.sw.core.exception.DetailException;
import com.sw.plugins.usercenter.config.dynamic.entity.Dictionary;
import com.sw.plugins.usercenter.config.dynamic.service.DictionaryService;

/**
 * 字典控制器，负责字典的添加，修改，删除，查询等功能
 * @author Administrator
 */
@Controller 
public class DictionaryController extends BaseController {
	
	private static Logger logger = Logger.getLogger(DictionaryController.class);
	
	@Resource
	private DictionaryService dictionaryService;
	
	
	/**
	 *   字典值列表方法
	 *  @param dictionary
	 *  @param request
	 *  @return
	 *  @author Administrator
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-2 上午11:44:34
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/usercenter/config/dynamic/list")
    public CommonModelAndView list(Dictionary dictionary,HttpServletRequest request){
		Object obj = new CommonModelAndView().getCurrentStatus(dictionary, request);
		if (obj != null){
			if (obj instanceof Dictionary){
				dictionary = (Dictionary)obj;
			}
		} 
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, dictionary);
		commonModelAndView.addObject("code", dictionary.getC());
		commonModelAndView.addObject("dictionary", dictionary);
        return commonModelAndView;
    }  
	
	 /**
	  *   跳转到字典值创建页面
	  *  @param dictionary
	  *  @param request
	  *  @return
	  *  @author Administrator
	  *  @version 1.0
	  *  </pre>
	  *  Created on :2012-5-2 上午11:44:34
	  *  LastModified:
	  *  History:
	  *  </pre>
	  */
	@RequestMapping("/usercenter/config/dynamic/create")
    public CommonModelAndView create(Dictionary dictionary,HttpServletRequest request){
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, dictionary);
		//获取字典类型列表
		List<DictionarySort> dslist = null;
		try {
			dslist = dictionarySortService.getAll();
			commonModelAndView.addObject("dictionarySortList",dslist);
			commonModelAndView.addObject("dictionary", dictionary);
		} catch (Exception e) {
			DetailException.expDetail(e, DictionaryController.class);
			logger.debug(e.getMessage());
		}
        return commonModelAndView; 
    } 
	
	/**
	 * 字典值创建操作
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/usercenter/config/dynamic/save",method = RequestMethod.POST)
	public CommonModelAndView save(@Valid Dictionary dictionary,BindingResult result,Map<String,Object> model,HttpServletRequest request) {

		CommonModelAndView commonModelAndView = null;
		if (result.hasErrors()) {
			commonModelAndView = new CommonModelAndView(dictionary);
			List<DictionarySort> dslist = null;
			try {
				dslist = dictionarySortService.getAll();
				commonModelAndView.addObject("dictionarySortList",dslist);
				commonModelAndView.addObject("dictionary", dictionary);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());

			}
			return commonModelAndView;
		}else{
			if(dictionaryService.selectValue(dictionary) != 0 && dictionary.getId() == null){
				result.rejectValue("value", "already.dictionary.value");
				commonModelAndView = new CommonModelAndView(dictionary);
				List<DictionarySort> dslist = null;
				try {
					dslist = dictionarySortService.getAll();
					commonModelAndView.addObject("dictionarySortList",dslist);
					commonModelAndView.addObject("dictionary", dictionary);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
				return commonModelAndView;
			}
			if(dictionaryService.selectName(dictionary) != 0){
				result.rejectValue("name", "already.dictionary.name");
				commonModelAndView = new CommonModelAndView(dictionary);
				List<DictionarySort> dslist = null;
				try {
					dslist = dictionarySortService.getAll();
					commonModelAndView.addObject("dictionarySortList",dslist);
					commonModelAndView.addObject("dictionary", dictionary);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
				return commonModelAndView;
			}
		}
		//视图名
		String viewName = null;
		try {
			Dictionary dict = new Dictionary();
			dict.setValue(dictionary.getValue());
			if(dictionary != null && dictionary.getId() != null){
				dictionaryService.update(dictionary);
			}else{
				dictionaryService.save(dictionary);
			}
			viewName = this.SUCCESS;
		} catch (Exception e) {
			logger.debug(e.getMessage());
			dictionary.setErrorMsg(e.getMessage());
			viewName = this.ERROR;
		}
		commonModelAndView = new CommonModelAndView(viewName,dictionary,messageSource);
		return commonModelAndView;		
	}
	
	/**
	 * 跳转到字典值修改页面
	 * @param code
	 * @param consult
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/config/dynamic/modify")
    public CommonModelAndView modify(Dictionary dictionary,HttpServletRequest request,Map<String,Object> model){
		String code = dictionary.getC();
		if (dictionary.getId() != null){
			try {
				dictionary = dictionaryService.getOneById(dictionary);
				
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}
		dictionary.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,dictionary);
		model.put("dictionary", dictionary);
		//取所有字典类型
		List<DictionarySort> dslist = null;
		try {
			dslist = dictionarySortService.getAll();
			commonModelAndView.addObject("dictionarySortList",dslist);
			
		} catch (Exception e) {
			DetailException.expDetail(e, DictionaryController.class);
			logger.debug(e.getMessage());
		}		
        return commonModelAndView; 
    }
	
	/**
	 * 字典值删除功能，json格式
	 * @param consult
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/config/dynamic/delete")
    public CommonModelAndView delete(Dictionary dictionary,HttpServletRequest request){
		//视图名
		String viewName = null;		
		try {
			String name = URLDecoder.decode(dictionary.getName(), Constant.ENCODING);
			dictionary.setName(null);
			dictionaryService.deleteByArr(dictionary);
			dictionary.setName(name);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			e.printStackTrace();
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, dictionary, messageSource);
		return commonModelAndView;			
    }
	
	/**
	 *  查询字典值信息 返回json格式
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
	@RequestMapping("/usercenter/config/dynamic/grid")
	public CommonModelAndView json(String nodeid,HttpServletRequest request){
		Map<String, Object> map = null;
		try {
			map = dictionaryService.getGrid(nodeid); 
			
		} catch (Exception e) {
			DetailException.expDetail(e, DictionaryController.class);
			logger.debug(e.getMessage());
		}		
		return new CommonModelAndView("jsonView",map); 
	}

	@Override
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		return null;
	}
}
