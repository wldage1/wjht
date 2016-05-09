package com.sw.plugins.usercenter.system.log.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sw.core.controller.BaseController;
import com.sw.core.controller.CommonModelAndView;
import com.sw.core.data.entity.BaseEntity;
import com.sw.plugins.usercenter.system.log.entity.Log;
import com.sw.plugins.usercenter.system.log.service.LogService;

/**
 * 控制器，进行日志信息维护
 * @author Administrator
 *
 */
@Controller  
public class LogController extends BaseController{  
	
	private static Logger logger = Logger.getLogger(LogController.class);
	
    @Resource
    private LogService logService;
    /**
     * 日志列表方法
     * @param code
     * @param role
     * @param request
     * @return
     */
	@RequestMapping("/usercenter/system/log/list")
    public CommonModelAndView list(Log log,HttpServletRequest request){ 
		Object obj = new CommonModelAndView().getCurrentStatus(log, request);
		if (obj != null){
			if (obj instanceof Log){
				log = (Log)obj;
			}
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,log);
    	commonModelAndView.addObject("code", log.getC());
        return commonModelAndView;
    }  
	
	/**
	 * 日志删除功能，json格式
	 * @param role
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/system/log/delete")
    public CommonModelAndView delete(Log log,HttpServletRequest request){
		//视图名
		String viewName = null;		
		try {
			if(log!=null && log.getId()!=null){
				logService.delete(log);
			}else if(log!=null && log.getIds()!=null){
				logService.deleteByArr(log);
			}
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.SUCCESS;
			logger.error(e.getMessage());
		}	
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,log,messageSource);
		return commonModelAndView;		
    }  
	
	/**
	 * 查询日志信息 返回json格式
	 * @param request
	 * @return
	 */
	@RequestMapping("/usercenter/system/log/grid")
	public CommonModelAndView json(Log log,String nodeid,HttpServletRequest request){
		Map<String, Object> map = logService.getGrid(log); 
		return new CommonModelAndView("jsonView",map); 
	}

	@Override
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}
