package com.sw.plugins.usercenter.socialization.appversion.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sw.core.controller.BaseController;
import com.sw.core.controller.CommonModelAndView;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.exception.DetailException;
import com.sw.plugins.usercenter.socialization.appversion.entity.AppVersion;
import com.sw.plugins.usercenter.socialization.appversion.service.AppVersionService;

@Controller
public class AppVersionController extends BaseController {

	private static Logger logger = Logger.getLogger(AppVersionController.class);

	@Resource
	private AppVersionService appVersionService;

	/**
	 * 列表方法
	 * 
	 * @param appversion
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/socialization/appversion/list")
	public CommonModelAndView list(AppVersion appVersion, HttpServletRequest request) {
		Object obj = new CommonModelAndView().getCurrentStatus(appVersion, request);
		if (obj != null) {
			if (obj instanceof AppVersion) {
				appVersion = (AppVersion) obj;
			}
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, appVersion);
		commonModelAndView.addObject("code", appVersion.getC());
		commonModelAndView.addObject("appversion", appVersion);
		commonModelAndView.addObject("devicetype", initialData.getDeviceType());
		commonModelAndView.addObject("noticerate", initialData.getNoticeRate());
		return commonModelAndView;
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @param code
	 * @param consult
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/socialization/appversion/edit")
	public CommonModelAndView edit(AppVersion appVersion, HttpServletRequest request, Map<String, Object> model) {
		String code = appVersion.getC();
		if (appVersion.getId() != null) {
			try {
				appVersion = appVersionService.getOneById(appVersion);
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}
		appVersion.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, appVersion);
		commonModelAndView.addObject("appversion", appVersion);
		commonModelAndView.addObject("devicetype", initialData.getDeviceType());
		commonModelAndView.addObject("noticerate", initialData.getNoticeRate());
		return commonModelAndView;
	}

	/**
	 * 保存
	 * 
	 * @param appversion
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/usercenter/socialization/appversion/save", method = RequestMethod.POST)
	public CommonModelAndView save(@Valid AppVersion appVersion, BindingResult result, Map<String, Object> model) {
		if (result.hasErrors()) {
			return new CommonModelAndView(appVersion);
		}
		// 视图名
		String viewName = null;
		try {
			appVersionService.update(appVersion);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			logger.error(DetailException.expDetail(e, getClass()));
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, appVersion, messageSource);
		return commonModelAndView;
	}

	/**
	 * 查询信息 返回json格式
	 * 
	 * @param consult
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/socialization/appversion/grid")
	public CommonModelAndView json(AppVersion appVersion, HttpServletRequest request) {
		Map<String, Object> map = null;
		try {
			map = appVersionService.getGrid(appVersion);

		} catch (Exception e) {
			logger.debug(DetailException.expDetail(e, AppVersionController.class));
		}
		return new CommonModelAndView("jsonView", map);
	}

	@Override
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		return null;
	}
}
