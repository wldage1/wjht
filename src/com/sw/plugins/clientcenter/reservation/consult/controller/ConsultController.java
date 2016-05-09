package com.sw.plugins.clientcenter.reservation.consult.controller;

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
import com.sw.core.util.CommonUtil;
import com.sw.plugins.clientcenter.reservation.consult.entity.Consult;
import com.sw.plugins.clientcenter.reservation.consult.service.ConsultService;
import com.sw.plugins.usercenter.config.dynamic.entity.Dictionary;
import com.sw.plugins.usercenter.config.dynamic.service.DictionaryService;

/**
 * 预约咨询控制器，负责预约咨询的添加，修改，删除，查询等功能
 * @author Administrator
 *
 */
@Controller
public class ConsultController extends BaseController {
	
	private static Logger logger = Logger.getLogger(ConsultController.class);
	
	@Resource
	private ConsultService consultService;
    @Resource
    private DictionaryService dictionaryService;
	
	/**
     * 预约咨询列表方法
     * @param code
     * @param request
     * @return
     */
	@RequestMapping("/clientcenter/reservation/consult/list")
    public CommonModelAndView list(Consult consult,HttpServletRequest request){ 
		Object obj = new CommonModelAndView().getCurrentStatus(consult, request);
		if (obj != null){
			if (obj instanceof Consult){
				consult = (Consult)obj;
			}
		} 
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, consult);
		commonModelAndView.addObject("code", consult.getC());
		commonModelAndView.addObject("consult" ,consult);
        return commonModelAndView;
    }
	
	/**
     * 预约咨询回收站页面
     * @param code
     * @param request
     * @return
     */
	@RequestMapping("/clientcenter/reservation/consult/recycle")
    public CommonModelAndView recycle(Consult consult,HttpServletRequest request){ 
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, consult);
		commonModelAndView.addObject("code", consult.getC());
		commonModelAndView.addObject("consult" ,consult);
        return commonModelAndView;
    }
	
	/**
	 * 跳转到预约咨询创建页面
	 * @param code
	 * @param request
	 * @return
	 * @author 
	 */
	@RequestMapping("/clientcenter/reservation/consult/create")
    public CommonModelAndView create(Consult consult,HttpServletRequest request){
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, consult);
		commonModelAndView.addObject("consult", consult);
		try {
			Dictionary dictionary = new Dictionary();
			dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
			List<Dictionary> genderList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("genderList", genderList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
        return commonModelAndView; 
    } 
	
	/**
	 * 预约咨询创建操作
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/clientcenter/reservation/consult/save",method = RequestMethod.POST)
	public CommonModelAndView save(@Valid Consult consult,BindingResult result,Map<String,Object> model) {
		CommonModelAndView commonModelAndView = null;
		if (result.hasErrors()) {
			commonModelAndView = new CommonModelAndView(consult);
			Dictionary dictionary = new Dictionary();
			dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
			List<Dictionary> genderList;
			try {
				genderList = dictionaryService.getList(dictionary);
				commonModelAndView.addObject("genderList", genderList);
			} catch (Exception e) {
				logger.error(e.getMessage());

			}
			return commonModelAndView;
		}
		//视图名
		String viewName = null;
		try {
			if(consult != null && consult.getId() != null){
				consultService.update(consult);
			}else{
				consultService.save(consult);
			}
			viewName = this.SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}
		commonModelAndView = new CommonModelAndView(viewName,consult,messageSource);
		return commonModelAndView;		
	}
	
	/**
	 * 跳转到咨询修改页面
	 * @param code
	 * @param consult
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/reservation/consult/modify")
    public CommonModelAndView modify(Consult consult,HttpServletRequest request,Map<String,Object> model){
		String code = consult.getC();
		if (consult.getId() != null) {
			try {
				Consult c = new Consult();
				c.setId(consult.getId());
				consult = consultService.getOneById(c);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		consult.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,consult);
		model.put("consult", consult);
		Dictionary dictionary = new Dictionary();
		dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
		List<Dictionary> genderList;
		try {
			genderList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("genderList", genderList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
        return commonModelAndView; 
    }
	
	/**
	 * 跳转到咨询详细页面
	 * @param code
	 * @param consult
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/reservation/consult/detail")
    public CommonModelAndView detail(Consult consult,HttpServletRequest request,Map<String,Object> model){
		String code = consult.getC();
		try {
			consult = consultService.getOneById(consult);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		consult.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, consult); 
		commonModelAndView.addObject("consult", consult);
		model.put("consult", consult);
        return commonModelAndView; 
    }
	
	/**
	 * 咨询删除功能，json格式
	 * @param consult
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/reservation/consult/*delete")
    public CommonModelAndView delete(Consult consult,HttpServletRequest request){
		//视图名
		String viewName = null;		
		try {
			String name = URLDecoder.decode(consult.getName(), Constant.ENCODING);
			consultService.deleteByArr(consult);
			consult.setName(name);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, consult, messageSource);
		return commonModelAndView;			
    }
	
	/**
	 *  查询预约咨询信息 返回json格式
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
	@RequestMapping("/clientcenter/reservation/consult/grid")
	public CommonModelAndView json(Consult consult,HttpServletRequest request){
		consult.setName(CommonUtil.convertSearchSign(consult.getName()));
		consult.setMobilePhone(CommonUtil.convertSearchSign(consult.getMobilePhone()));
		Map<String, Object> map = consultService.getGrid(consult); 
		return new CommonModelAndView("jsonView",map); 
	}

	@Override
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		return null;
	}


}
