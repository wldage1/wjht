package com.sw.plugins.clientcenter.reservation.info.controller;

import java.net.URLDecoder;
import java.util.Hashtable;
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
import com.sw.core.util.CommonUtil;
import com.sw.plugins.clientcenter.reservation.consult.controller.ConsultController;
import com.sw.plugins.clientcenter.reservation.info.entity.Reservation;
import com.sw.plugins.clientcenter.reservation.info.service.ReservationService;

/**
 * 预约信息控制器，负责预约的添加，修改，删除，查询等功能
 * @author Administrator
 *
 */
@Controller  
public class ReservationController extends BaseController {

	private static Logger logger = Logger.getLogger(ConsultController.class);
	
	@Resource
	private ReservationService reservationService;
	
	/**
     * 预约列表方法
     * @param code
     * @param reservation
     * @param request
     * @return
     */
	@RequestMapping("/clientcenter/reservation/info/list")
    public CommonModelAndView list(Reservation reservation,HttpServletRequest request){ 
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, reservation); 
		commonModelAndView.addObject("code", reservation.getC());
		commonModelAndView.addObject("reservation", reservation);
        return commonModelAndView;
    }
	
	/**
     * 预约回收站列表方法
     * @param code
     * @param reservation
     * @param request
     * @return
     */
	@RequestMapping("/clientcenter/reservation/info/recycle")
    public CommonModelAndView recycle(Reservation reservation,HttpServletRequest request){
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, reservation); 
		commonModelAndView.addObject("code", reservation.getC());
        return commonModelAndView;
    }
	
	
	/**
	 * 跳转到预约详细页面
	 * @param code
	 * @param reservation
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/reservation/info/detail")
    public CommonModelAndView detail(Reservation reservation,HttpServletRequest request){
		String code = reservation.getC();
		try {
			reservation = reservationService.getOneById(reservation);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		reservation.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, reservation); 
		commonModelAndView.addObject("reservation", reservation);
        return commonModelAndView; 
    }
	
	/**
	 * 预约删除功能，json格式
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/reservation/info/*delete")
    public CommonModelAndView delete(Reservation reservation,HttpServletRequest request){
		//视图名
		String viewName = null;		
		try {
			String name = URLDecoder.decode(reservation.getName(), Constant.ENCODING);
			reservationService.deleteByArr(reservation);
			reservation.setName(name);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.SUCCESS;
			logger.error(e.getMessage());
		}
		Map<String, Object> map = new Hashtable<String, Object>(); 
		map.put(Constant.STATUS, viewName);		
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, reservation, messageSource);
		return commonModelAndView;
    }  
	
	/**
	 * 查询预约信息 返回json格式
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/reservation/info/grid")
	public CommonModelAndView json(Reservation reservation,HttpServletRequest request){
		reservation.setName(CommonUtil.convertSearchSign(reservation.getName()));
		Map<String, Object> map = reservationService.getGrid(reservation); 
		return new CommonModelAndView("jsonView",map); 
	}

	@Override
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		return null;
	}
}
