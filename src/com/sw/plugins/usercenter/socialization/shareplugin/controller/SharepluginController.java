package com.sw.plugins.usercenter.socialization.shareplugin.controller;

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
import com.sw.core.exception.DetailException;
import com.sw.core.initialize.InitialData;
import com.sw.plugins.usercenter.socialization.shareplugin.entity.Plugintype;
import com.sw.plugins.usercenter.socialization.shareplugin.entity.Shareplugin;
import com.sw.plugins.usercenter.socialization.shareplugin.service.PlugintypeService;
import com.sw.plugins.usercenter.socialization.shareplugin.service.SharepluginService;

@Controller
public class SharepluginController extends BaseController {

	private static Logger logger = Logger.getLogger(SharepluginController.class);

	@Resource
	private SharepluginService sharepluginService;
	@Resource
	private PlugintypeService plugintypeService;
	@Resource
	private InitialData initialData;
	

	/**
	 * 分享配置列表方法
	 * 
	 * @param shareplugin
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/socialization/shareplugin/list")
	public CommonModelAndView list(Shareplugin shareplugin, HttpServletRequest request) {
		Object obj = new CommonModelAndView().getCurrentStatus(shareplugin, request);
		if (obj != null) {
			if (obj instanceof Shareplugin) {
				shareplugin = (Shareplugin) obj;
			}
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, shareplugin);
		commonModelAndView.addObject("code", shareplugin.getC());
		commonModelAndView.addObject("shareplugin", shareplugin);
		return commonModelAndView;
	}

	/**
	 * 创建分享平台信息
	 * 
	 * @param shareplugin
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/socialization/shareplugin/create")
	public CommonModelAndView create(Shareplugin shareplugin, HttpServletRequest request) {
		Object obj = new CommonModelAndView().getCurrentStatus(shareplugin, request);
		if (obj != null) {
			if (obj instanceof Shareplugin) {
				shareplugin = (Shareplugin) obj;
			}
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, shareplugin);
		commonModelAndView.addObject("code", shareplugin.getC());
		commonModelAndView.addObject("shareplugin", shareplugin);
		commonModelAndView.addObject("devicetypes", initialData.getDeviceType());
		return commonModelAndView;
	}

	/**
	 * 跳转到分享配置修改页面
	 * 
	 * @param code
	 * @param consult
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/socialization/shareplugin/modify")
	public CommonModelAndView modify(Shareplugin shareplugin, HttpServletRequest request, Map<String, Object> model) {
		String code = shareplugin.getC();
		List<Plugintype> plugintypes = null;
		if (shareplugin.getId() != null) {
			try {
				shareplugin = sharepluginService.getOneById(shareplugin);
				// 加载插件适用终端类型
				Plugintype pt = new Plugintype();
				pt.setPluginId(shareplugin.getId());
				plugintypes = plugintypeService.getList(pt);
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}
		shareplugin.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, shareplugin);
		model.put("shareplugin", shareplugin);
		commonModelAndView.addObject("plugintypes", plugintypes);
		commonModelAndView.addObject("devicetypes", initialData.getDeviceType());
		return commonModelAndView;
	}

	/**
	 * 跳转到分享配置页面
	 * 
	 * @param code
	 * @param consult
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/socialization/shareplugin/config")
	public CommonModelAndView config(Shareplugin shareplugin, HttpServletRequest request, Map<String, Object> model) {
		String code = shareplugin.getC();
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, shareplugin);
		try {
			List<Shareplugin> sharepluginList = sharepluginService.getList(shareplugin);
			model.put("sharepluginList", sharepluginList);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		shareplugin.setC(code);
		model.put("shareplugin", shareplugin);
		return commonModelAndView;
	}

	/**
	 * 保存分享信息
	 * 
	 * @param shareword
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/usercenter/socialization/shareplugin/save", method = RequestMethod.POST)
	public CommonModelAndView save(@Valid Shareplugin shareplugin, BindingResult result, HttpServletRequest request, Map<String, Object> model) {

		// 视图名
		String viewName = null;
		try {
			Shareplugin sp = new Shareplugin();
			Long cSp = 0L;
			if (shareplugin.getName() != null && !"".equals(shareplugin.getName())) {
				sp.setId(shareplugin.getId());
				sp.setName(shareplugin.getName());
				cSp = sharepluginService.checkDuplication(sp);
				if (cSp != 0) {
					result.rejectValue("name", "Duplicate.shareplugin.name");
				}
			}
			cSp = 0L;
			if (shareplugin.getAuthoriKey() != null && !"".equals(shareplugin.getAuthoriKey())) {
				sp = new Shareplugin();
				sp.setId(shareplugin.getId());
				sp.setAuthoriKey(shareplugin.getAuthoriKey());
				cSp = sharepluginService.checkDuplication(sp);
				if (cSp != 0) {
					result.rejectValue("authoriKey", "Duplicate.shareplugin.authoriKey");
				}
			}
			if (result.hasErrors()) {
				CommonModelAndView commonModelAndView = new CommonModelAndView(request,shareplugin);
				commonModelAndView.addObject("devicetypes", initialData.getDeviceType());
				return commonModelAndView;
			}

			sharepluginService.saveOrUpdate(shareplugin);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			logger.error(DetailException.expDetail(e, getClass()));
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, shareplugin, messageSource);
		return commonModelAndView;
	}

	/**
	 * 保存分享配置
	 * 
	 * @param shareword
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/usercenter/socialization/shareplugin/save_config", method = RequestMethod.POST)
	public CommonModelAndView saveConfig(Shareplugin shareplugin, Map<String, Object> model) {
		// 视图名
		String viewName = null;
		try {
			sharepluginService.saveConfig(shareplugin);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			logger.error(DetailException.expDetail(e, getClass()));
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, shareplugin, messageSource);
		return commonModelAndView;
	}

	/**
	 * 删除分享配置
	 * 
	 * @param shareplugin
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/socialization/shareplugin/delete")
	public CommonModelAndView delete(Shareplugin shareplugin, HttpServletRequest request) {
		// 视图名
		String viewName = null;
		try {
			String name = URLDecoder.decode(shareplugin.getName(), Constant.ENCODING);
			shareplugin.setName(name);
			if (shareplugin != null && shareplugin.getId() != null) {
				sharepluginService.delete(shareplugin);
			} else if (shareplugin != null && shareplugin.getIds() != null) {
				sharepluginService.deleteByArr(shareplugin);
			}
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, shareplugin, messageSource);
		return commonModelAndView;
	}

	/**
	 * 查询分享配置信息 返回json格式
	 * 
	 * @param consult
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/socialization/shareplugin/grid")
	public CommonModelAndView json(Shareplugin shareplugin, HttpServletRequest request) {
		Map<String, Object> map = null;
		try {
			map = sharepluginService.getGrid(shareplugin);

		} catch (Exception e) {
			DetailException.expDetail(e, SharepluginController.class);
			logger.debug(e.getMessage());
		}
		return new CommonModelAndView("jsonView", map);
	}

	@Override
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		return null;
	}
}
