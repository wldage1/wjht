package com.sw.plugins.usercenter.socialization.shareword.controller;

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
import com.sw.plugins.usercenter.socialization.shareword.entity.Shareword;
import com.sw.plugins.usercenter.socialization.shareword.service.SharewordService;

@Controller
public class SharewordController extends BaseController {

	private static Logger logger = Logger.getLogger(SharewordController.class);

	@Resource
	private SharewordService sharewordService;

	/**
	 * 分享语列表方法
	 * 
	 * @param shareword
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/socialization/shareword/list")
	public CommonModelAndView list(Shareword shareword, HttpServletRequest request) {
		Object obj = new CommonModelAndView().getCurrentStatus(shareword, request);
		if (obj != null) {
			if (obj instanceof Shareword) {
				shareword = (Shareword) obj;
			}
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, shareword);
		commonModelAndView.addObject("code", shareword.getC());
		commonModelAndView.addObject("shareword", shareword);
		return commonModelAndView;
	}

	/**
	 * 跳转到分享语修改页面
	 * 
	 * @param code
	 * @param consult
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/socialization/shareword/edit")
	public CommonModelAndView edit(Shareword shareword, HttpServletRequest request, Map<String, Object> model) {
		String code = shareword.getC();
		if (shareword.getId() != null) {
			try {
				shareword = sharewordService.getOneById(shareword);
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}
		shareword.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, shareword);
		model.put("shareword", shareword);
		return commonModelAndView;
	}

	/**
	 * 分享语保存
	 * 
	 * @param shareword
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/usercenter/socialization/shareword/save", method = RequestMethod.POST)
	public CommonModelAndView save(@Valid Shareword shareword, BindingResult result, Map<String, Object> model) {
		if (result.hasErrors()) {
			return new CommonModelAndView(shareword);
		}
		// 视图名
		String viewName = null;
		try {
			sharewordService.update(shareword);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			logger.error(DetailException.expDetail(e, getClass()));
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, shareword, messageSource);
		return commonModelAndView;
	}

	/**
	 * 查询分享语信息 返回json格式
	 * 
	 * @param consult
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/socialization/shareword/grid")
	public CommonModelAndView json(Shareword shareword, HttpServletRequest request) {
		Map<String, Object> map = null;
		try {
			map = sharewordService.getGrid(shareword);

		} catch (Exception e) {
			logger.debug(DetailException.expDetail(e, SharewordController.class));
		}
		return new CommonModelAndView("jsonView", map);
	}

	@Override
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		return null;
	}
}
