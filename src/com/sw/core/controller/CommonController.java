package com.sw.core.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sw.core.common.Constant;
import com.sw.core.data.entity.Authorization;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.data.entity.BaseUser;
import com.sw.core.exception.DetailException;
import com.sw.plugins.clientcenter.member.maintain.entity.Member;
import com.sw.plugins.clientcenter.member.maintain.service.MemberService;
import com.sw.plugins.clientcenter.reservation.info.entity.Reservation;
import com.sw.plugins.clientcenter.reservation.info.service.ReservationService;
import com.sw.plugins.informationcenter.message.maintain.entity.Message;
import com.sw.plugins.informationcenter.message.maintain.service.MessageService;
import com.sw.plugins.productcenter.product.maintain.entity.Product;
import com.sw.plugins.productcenter.product.maintain.service.ProductService;
/**
 * 后台Action类 - 管理中心基类
 */

@Controller  
public class CommonController extends BaseController{
	/**操作成功返回,非从定向url*/
	public String SUCCESS = "success";
    /**错误页面,非从定向url*/
	public String ERROR = "error";
	
	@Resource
    public MemberService memberService;
	@Resource
    public ReservationService reservationService;	
	@Resource
    public MessageService messageService;	
	@Resource
    public ProductService productService;	

	
	@RequestMapping("/")
    public CommonModelAndView defaultpage(){
		CommonModelAndView commonModelAndView = new CommonModelAndView();
		commonModelAndView.setViewName("redirect:/" + this.INDEX);
		return commonModelAndView;
	}
	
    /**
     * 进入首页
     * @return
     */
    @RequestMapping("/index")
    public CommonModelAndView index(BaseEntity baseEntity,HttpServletRequest request){ 
    	HttpSession session = request.getSession();		
        session.setAttribute("base",request.getContextPath());
        session.setAttribute("common",Constant.COMMON);
        session.setAttribute("style",Constant.STYLE);      	
        return new CommonModelAndView(this.INDEX);  
    }  
 
    @RequestMapping("/success")
    public CommonModelAndView success(String promptMessage,String statusParam,HttpServletRequest request){ 
    	//编码转换
    	try {
			promptMessage = URLDecoder.decode(promptMessage, Constant.ENCODING);
		} catch (UnsupportedEncodingException e) {
			DetailException.expDetail(e, CommonController.class);
		}
    	CommonModelAndView commonModelAndView = new CommonModelAndView(this.SUCCESS);
		//设置提示停息
    	if (promptMessage != null){
    		commonModelAndView.addObject(CommonModelAndView.PROMPT_MESSAGE,promptMessage);
    	}
    	if (statusParam != null){
    		commonModelAndView.addObject(CommonModelAndView.STATUS_PARAM,statusParam);
    	}
        return commonModelAndView;  
    }
    
    
    @RequestMapping("/error")
    public CommonModelAndView error(String promptMessage,String statusParam,HttpServletRequest request){ 
    	HttpSession session = request.getSession();		
        session.setAttribute("base",request.getContextPath());
        session.setAttribute("common",Constant.COMMON);
        session.setAttribute("style",Constant.STYLE);      	
    	CommonModelAndView commonModelAndView = new CommonModelAndView(this.ERROR);
		//设置提示停息
    	if (promptMessage != null){
        	//编码转换
        	try {
    			promptMessage = URLDecoder.decode(promptMessage, Constant.ENCODING);
    		} catch (UnsupportedEncodingException e) {
    			DetailException.expDetail(e, CommonController.class);
    		}    		
    		commonModelAndView.addObject(CommonModelAndView.PROMPT_MESSAGE,promptMessage);
    	}
    	if (statusParam != null){
    		commonModelAndView.addObject(CommonModelAndView.STATUS_PARAM,statusParam);
    	}
        return commonModelAndView;  
    }
    
    
    /**
     * 进入欢迎页面
     * @return
     */
    @RequestMapping("/welcome")
    public CommonModelAndView welcome(BaseEntity baseEntity,HttpServletRequest request){ 
    	CommonModelAndView commonModelAndView = new CommonModelAndView(this.WELCOME);
    	//读取一级权限信息
    	List<?> authlist1 = authorizationService.getAuthorizationByLevel("1");
    	commonModelAndView.addObject("authlist1", authlist1);
    	//读取二级权限信息
    	List<?> authlist2 = authorizationService.getAuthorizationByLevel("2");
    	commonModelAndView.addObject("authlist2", authlist2);
    	//读取三级权限信息
    	List<?> authlist3 = authorizationService.getAuthorizationByLevel("3");
    	commonModelAndView.addObject("authlist3", authlist3);
    	//浏览器版本
    	String bversion = request.getHeader("User-Agent");
    	commonModelAndView.addObject("bversion", bversion);
        ServletContext context = request.getSession().getServletContext();
        
        //servlet版本信息
        String serverInfo = context.getServerInfo();
        commonModelAndView.addObject("serverInfo", serverInfo);
        //servlet版本
        int majorVersion  = context.getMajorVersion();
        int minorVersion  = context.getMinorVersion();
        String servletVersion = new StringBuffer().append(majorVersion).append('.').append(minorVersion).toString();
        commonModelAndView.addObject("servletVersion", servletVersion);
        //虚拟机名称
        String vmName          = "";
        //虚拟机提供商
        String vmVendor        = "";
        //虚拟机版本
        String vmVersion       = "";
        //虚拟机版本
        String runtimeName     = "";
        //虚拟机版本
        String runtimeVersion  = "";
        //操作系统名称
        String osName          = "";
        //操作系统版本
        String osVersion       = "";
     	//cup
        String cpu             = "";
        try {
            vmName          = System.getProperty("java.vm.name", "");
            vmVendor        = System.getProperty("java.vm.vendor", "");
            vmVersion       = System.getProperty("java.vm.version", "");
            runtimeName     = System.getProperty("java.runtime.name", "");
            runtimeVersion  = System.getProperty("java.runtime.version", "");
            osName          = System.getProperty("os.name", "");
            osVersion       = System.getProperty("os.version", "");
            cpu             = System.getProperty("sun.cpu.isalist", "");
        } catch (Exception ex) {
        }
        commonModelAndView.addObject("vmName", vmName);
        commonModelAndView.addObject("vmVendor", vmVendor);
        commonModelAndView.addObject("vmVersion", vmVersion);
        commonModelAndView.addObject("osName", osName);
        commonModelAndView.addObject("runtimeName", runtimeName);
        commonModelAndView.addObject("runtimeVersion", runtimeVersion);
        commonModelAndView.addObject("osVersion", osVersion);
        commonModelAndView.addObject("cpu", cpu); 
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory  = runtime.freeMemory();
        long totalKB = totalMemory/1024;
        long freeKB  = freeMemory/1024;
        commonModelAndView.addObject("totalkb", totalKB);
        commonModelAndView.addObject("freekb", freeKB);
        /*try {
        	//会员数量
        	commonModelAndView.addObject("memberCount", memberService.getRecordCount(new Member()));
        	//预约数量
        	commonModelAndView.addObject("reservationCount", reservationService.getRecordCount(new Reservation()));
        	Reservation reservation = new Reservation();
        	//已处理预约数量
        	reservation.setDisposeState(Constant.RESERVATION_DISPOSE_STATE_NO);
        	commonModelAndView.addObject("noDisposeReservationCount", reservationService.getRecordCount(reservation));
        	//未处理预约数量
        	reservation.setDisposeState(Constant.RESERVATION_DISPOSE_STATE_YES);
        	commonModelAndView.addObject("yesDisposeReservationCount", reservationService.getRecordCount(reservation));
        	//消息数量
        	commonModelAndView.addObject("messageCount", messageService.getRecordCount(new Message()));
        	//待审核消息数量
        	commonModelAndView.addObject("auditingCount", messageService.getAuditingRecordCount(new Message()));
        	//已审核消息数量
        	commonModelAndView.addObject("auditedCount", messageService.getAuditedRecordCount(new Message()));
        	//产品数量
        	commonModelAndView.addObject("productCount", productService.getRecordCount(new Product()));
		} catch (Exception e) {
			DetailException.expDetail(e, CommonController.class);
		}*/
        return commonModelAndView;    
    }   
    
    /**
     * 进入上传页面
     * @param baseEntity
     * @param request
     * @return
     */
    @RequestMapping("/upload")
    public CommonModelAndView upload(String iframeId,String controllerName,String fileTypeDesc,String fileTypeExts,String queueSizeLimit,String uploadLimit,String inputIds,String imgIds,HttpServletRequest request){ 
    	CommonModelAndView commonModelAndView = new CommonModelAndView(this.UPLOAD);  
    	commonModelAndView.addObject("controllerName", controllerName);
    	commonModelAndView.addObject("iframeId", iframeId);
    	commonModelAndView.addObject("fileTypeDesc", fileTypeDesc);
    	commonModelAndView.addObject("fileTypeExts", fileTypeExts);
    	commonModelAndView.addObject("queueSizeLimit", queueSizeLimit);
    	commonModelAndView.addObject("uploadLimit", uploadLimit);
    	commonModelAndView.addObject("inputIds", inputIds);
    	commonModelAndView.addObject("imgIds", imgIds);
        return commonModelAndView;    
    }       
        
    
	/**
	 * 通过业务代码获得该权限所对应的控制器，并跳转到该控制器
	 * @param code 业务代码
	 * @param request
	 * @return
	 * @author
	 */
    @RequestMapping("/content")
    public CommonModelAndView content(String c,HttpServletRequest request){ 
    	Authorization cauthorization = authorizationService.getAuthorizationByCode(c);
    	String controllerStr = cauthorization.getController();
    	String viewName = "redirect:";
    	int indx = controllerStr.indexOf("?");
    	if (indx > -1){
    		viewName += controllerStr + "&c="+c;
    	}
    	else{
    		viewName += controllerStr + "?c="+c;
    	}
    	CommonModelAndView CommonModelAndView = new CommonModelAndView(viewName); 
        return CommonModelAndView;  
    } 
    
    /**
     * 进入主页面
     * @return
     */
    @RequestMapping("/main")
    public CommonModelAndView main(Authorization authorization,HttpServletRequest request){ 
    	//设置level为1，代表系统级别
    	authorization.setLevel("1");
    	CommonModelAndView CommonModelAndView = new CommonModelAndView(Constant.MAIN); 
    	//查询权限信息（系统）
    	List<?> pmslist = authorizationService.getAuthorizationByLevel(authorization.getLevel());
    	CommonModelAndView.addObject("pmslist", pmslist);
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (obj != null){
	        HttpSession session = request.getSession();		
	        BaseUser baseUser = (BaseUser)obj;
	        String style = baseUser.getStyle();
	        String accessIp = request.getRemoteAddr();
	        baseUser.setAccessIp(accessIp);
			if (style == null || style.equals("")){
		        session.setAttribute("base",request.getContextPath());
		        session.setAttribute("common",Constant.COMMON);
		        session.setAttribute("style",Constant.STYLE);  
			}
			else{
				session.setAttribute("base",request.getContextPath());
				session.setAttribute("common",Constant.COMMON);
				session.setAttribute("style",style);  
			}
		}
        return CommonModelAndView;  
    }  
    
    @RequestMapping("/main2")
    public CommonModelAndView main2(Authorization authorization,HttpServletRequest request){ 
    	//设置level为0，代表系统级别
    	authorization.setLevel("0");
    	//查询权限信息（系统）
    	List<?> pmslist = authorizationService.getAuthorizationByLevel(authorization.getLevel());
    	//加登录验证
        return new CommonModelAndView("main2","pmslist",pmslist);  
    }  
    
    /**
     * 左侧菜单
     * @return
     */    
    @RequestMapping("/left")
    public CommonModelAndView left(String c,Authorization authorization,HttpServletRequest request){ 
    	CommonModelAndView CommonModelAndView = new CommonModelAndView(Constant.LEFT); 
    	authorization.setLevel("2");
    	//查询权限信息
    	List<?> pmsLevelList1 = authorizationService.getAuthorizationByLevel(authorization.getLevel());
    	authorization.setLevel("3");
    	//查询权限信息
    	List<?> pmsLevelList2 = authorizationService.getAuthorizationByLevel(authorization.getLevel());
    	CommonModelAndView.addObject("pmsLevelList1", pmsLevelList1);
    	CommonModelAndView.addObject("pmsLevelList2", pmsLevelList2);
    	CommonModelAndView.addObject("code", c);
    	//加登录验证
        return CommonModelAndView;  
    }
    
    /**
     * 登录验证码校验
     * @return
     */
    @RequestMapping("/loginVerify")
    public CommonModelAndView loginVerify(String error){
    	CommonModelAndView commonModelAndView = new CommonModelAndView();
    	if (StringUtils.endsWithIgnoreCase(error, "captcha")) {
    		commonModelAndView.setViewName(super.ERROR);
    		commonModelAndView.addPromptMessage(messageSource, "user.captcha.error", null);
    		return commonModelAndView;
    	}
    	else{
    		commonModelAndView.setViewName("redirect:/" + this.MAIN);
    	}
    	return commonModelAndView;
	}
    /**
     * 用户注销
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(){
    	return "redirect:/" + Constant.EXIT;
	}
    
    /**
     * 系统退出拦截
     * @param request
     * @return
     */
    @RequestMapping("/exit")
    public String exit(){
    	return Constant.EXIT; 
	}


	@Override
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}