/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.plugins.clientcenter.member.maintain.controller
 * FileName: MemberController.java
 * @version 1.0
 * @author baokai
 * @created on 2012-4-27
 * @last Modified 
 * @history
 */

package com.sw.plugins.clientcenter.member.maintain.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sw.core.common.Constant;
import com.sw.core.controller.BaseController;
import com.sw.core.controller.CommonModelAndView;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.util.CommonUtil;
import com.sw.core.util.Encrypt;
import com.sw.plugins.clientcenter.member.maintain.entity.Member;
import com.sw.plugins.clientcenter.member.maintain.service.MemberService;
import com.sw.plugins.usercenter.config.dynamic.entity.Dictionary;
import com.sw.plugins.usercenter.config.dynamic.service.DictionaryService;

/**
 *  类简要说明
 *  @author baokai
 *  @version 1.0
 *  </pre>
 *  Created on :下午1:14:11
 *  LastModified:
 *  History:
 *  </pre>
 */
@Controller  
public class MemberController extends BaseController{  
	
	private static Logger logger = Logger.getLogger(MemberController.class);
	
    @Resource
    private MemberService memberService;
    @Resource
    private DictionaryService dictionaryService;
	
	
	/**
	 *  跳转到列表页
	 *  @param member
	 *  @param request
	 *  @return
	 *  @author baokai
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-4-27 下午4:53:59
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
    @RequestMapping("/clientcenter/member/maintain/list")
    public CommonModelAndView list(Member member,HttpServletRequest request,Map<String,Object> model){ 
        Object obj = new CommonModelAndView().getCurrentStatus(member, request);
		if (obj != null){
			if (obj instanceof Member){
				member = (Member)obj;
			}
		} 
		member.setDelStatus(Constant.MEMBER_DELSTATUS_NORMAL);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,member);
		try {
			Dictionary dictionary = new Dictionary();
    		dictionary.setDictSortValue(Constant.DICT_TYPE_CRM_LEVEL);
    		List<Dictionary> levelList = dictionaryService.getList(dictionary);
    		commonModelAndView.addObject("levelList", levelList);
    		dictionary = new Dictionary();
    		dictionary.setDictSortValue(Constant.DICT_TYPE_MEMBERSTATUS);
    		List<Dictionary> statusList = dictionaryService.getList(dictionary);
    		commonModelAndView.addObject("statusList", statusList);
    		commonModelAndView.addObject("statusStart", Constant.MEMBER_STATUS_START);
    		commonModelAndView.addObject("statusStop", Constant.MEMBER_STATUS_STOP);
    		dictionary = new Dictionary();
    		dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
    		List<Dictionary> sexList = dictionaryService.getList(dictionary);
    		commonModelAndView.addObject("sexList", sexList);
    		dictionary = new Dictionary();
    		dictionary.setDictSortValue(Constant.DICT_TYPE_DATASOURCE);
    		List<Dictionary> sourceList = dictionaryService.getList(dictionary);
    		commonModelAndView.addObject("sourceList", sourceList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		commonModelAndView.addObject("code", member.getC());
//		member.setSelectValues(request.getParameter("selectValues"));
//		commonModelAndView.addObject("selectValues",request.getParameter("selectValues"));
		model.put("member", member);
        return commonModelAndView;
    }
    
    @RequestMapping("/clientcenter/member/maintain/recycler")
    public CommonModelAndView recycler(Member member,HttpServletRequest request){ 
        Object obj = new CommonModelAndView().getCurrentStatus(member, request);
		if (obj != null){
			if (obj instanceof Member){
				member = (Member)obj;
			}
		} 
		member.setDelStatus(Constant.MEMBER_DELSTATUS_DEL);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,member);
		try {
			Dictionary dictionary = new Dictionary();
    		dictionary.setDictSortValue(Constant.DICT_TYPE_CRM_LEVEL);
    		List<Dictionary> levelList = dictionaryService.getList(dictionary);
    		commonModelAndView.addObject("levelList", levelList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
    	commonModelAndView.addObject("code", member.getC());
        return commonModelAndView;
    }  
	
	/**
	 *  保存修改方法
	 *  @param member
	 *  @param result
	 *  @param model
	 *  @return
	 *  @author baokai
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-4-27 下午4:54:21
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping(value="/clientcenter/member/maintain/save",method = RequestMethod.POST)
	public CommonModelAndView save(@Valid Member member,BindingResult result,Map<String,Object> model) {
		//实体bean验证
		boolean resultErrorFlag = result.hasErrors();
		//两次密码是否一致验证
		boolean pwErrorFlag = (member.getPassWord()!=null && member.getConfirmPassWord()!=null && !member.getPassWord().equals(member.getConfirmPassWord()));
		//手机号是否重复验证
		boolean mobilePhoneErrorFlag = false;
		//帐号是否重复验证
		boolean userNameErrorFlag = false;
		//填写密码后，密码保护问题是否填写
		boolean pwQuestionErrorFlag = false;
		if(member.getId() == null){
			if(member.getPassWord() != null && !member.getPassWord().equals("") && (null == member.getPwQuestion() || (null != member.getPwQuestion() && "".equals(member.getPwQuestion()))))
				pwQuestionErrorFlag = true;
		}else{
			if("1".equals(member.getModifyPassWordFlag())){
				if(member.getPassWord() != null && !member.getPassWord().equals("") && (null == member.getPwQuestion() || (null != member.getPwQuestion() && "".equals(member.getPwQuestion()))))
					pwQuestionErrorFlag = true;
			}
		}
		
		//填写密码保护问题，答案时候填写
		boolean pwResultErrorFlag = false;
		if(member.getPwQuestion() != null && !member.getPwQuestion().equals("") && (null == member.getPwResult() || (null != member.getPwResult() && "".equals(member.getPwResult()))))
			pwResultErrorFlag = true;
		try {
			Member tempMember = new Member();
			tempMember.setMobilePhone(member.getMobilePhone());
			//判断是新增还是修改
			if(member.getId()!=null){
				tempMember.setId(member.getId());
			}
			if(memberService.getRecordCountForSave(tempMember)>0) mobilePhoneErrorFlag = true;
			tempMember = new Member();
			tempMember.setUserName(member.getUserName());
			//判断是新增还是修改
			if(member.getId()==null){
				if(memberService.getRecordCountForSave(tempMember)!=0) userNameErrorFlag = true;
			}else{
				if(memberService.getRecordCountForSave(tempMember)>1) userNameErrorFlag = true;
			}
			if (resultErrorFlag || pwErrorFlag || mobilePhoneErrorFlag || userNameErrorFlag || pwQuestionErrorFlag || pwResultErrorFlag) {
				CommonModelAndView commonModelAndView = new CommonModelAndView(member);
				try {
					Dictionary dictionary = new Dictionary();
					dictionary.setDictSortValue(Constant.DICT_TYPE_LEVEL);
					List<Dictionary> levelList = dictionaryService.getList(dictionary);
					commonModelAndView.addObject("levelList", levelList);
					dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
					List<Dictionary> genderList = dictionaryService.getList(dictionary);
					commonModelAndView.addObject("genderList", genderList);
					dictionary.setDictSortValue(Constant.DICT_TYPE_MEMBERSTATUS);
					List<Dictionary> memberStatusList = dictionaryService.getList(dictionary);
					commonModelAndView.addObject("memberStatusList", memberStatusList);
					dictionary.setDictSortValue(Constant.DICT_TYPE_PWQUESTION);
					List<Dictionary> memberPWQuestionList = dictionaryService.getList(dictionary);
					commonModelAndView.addObject("memberPWQuestionList", memberPWQuestionList);
					model.put("member", member);
					if(result.getFieldErrors("confirmPassWord").size()==0 && pwErrorFlag){
						result.rejectValue("confirmPassWord","unequal.member.passWord");
					}
					if(result.getFieldErrors("mobilePhone").size()==0 && mobilePhoneErrorFlag){
						result.rejectValue("mobilePhone","already.member.mobilePhone");
					}
					if(result.getFieldErrors("userName").size()==0 && userNameErrorFlag){
						result.rejectValue("userName","already.member.userName");
					}
					if(pwQuestionErrorFlag){
						result.rejectValue("pwQuestion","please.select.member.pwQuestion");
					}
					if(pwResultErrorFlag){
						result.rejectValue("pwResult","please.fill.member.pwResult");
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				return commonModelAndView;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		String viewName = null;
		try {
			member.setDelStatus(Constant.MEMBER_DELSTATUS_NORMAL);
			if(member.getId() == null){
				if(member.getPassWord()!=null)
					member.setPassWord(Encrypt.getMD5(member.getPassWord()));
				if(null == member.getPassWord() || (null != member.getPassWord() && "".equals(member.getPassWord()))){
					member.setPwQuestion(null);
					member.setPwResult(null);
				}else{
					if(member != null && member.getPwQuestion() != null)
						member.setPwResult(CommonUtil.escape(member.getPwResult()));
					if(member != null && member.getPwResult() != null)
						member.setPwQuestion(CommonUtil.escape(member.getPwQuestion()));
				}
				memberService.save(member);
			}else{
				if(member.getPassWord()!=null && "1".equals(member.getModifyPassWordFlag())){
					member.setPassWord(Encrypt.getMD5(member.getPassWord()));
				}else{
					member.setPassWord(null);
				}
				if((null == member.getPassWord() || (null != member.getPassWord() && "".equals(member.getPassWord()))) && "1".equals(member.getModifyPassWordFlag())){
					member.setPwQuestion(null);
					member.setPwResult(null);
				}else{
					if(member != null && member.getPwQuestion() != null)
						member.setPwQuestion(CommonUtil.escape(member.getPwQuestion()));
					if(member != null && member.getPwResult() != null)
						member.setPwResult(CommonUtil.escape(member.getPwResult()));
				}
				memberService.update(member);
			}
			viewName = this.SUCCESS;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,member,messageSource);
		return commonModelAndView;
	}

	
	/**
	 *  跳转到创建会员页面
	 *  @param member
	 *  @param request
	 *  @return
	 *  @author baokai
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-3 下午1:13:51
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/clientcenter/member/maintain/levelList")
    public CommonModelAndView create(Member member,HttpServletRequest request){
        CommonModelAndView commonModelAndView = new CommonModelAndView(request,member);
		commonModelAndView.addObject("member", member);
		try {
			Dictionary dictionary = new Dictionary();
			dictionary.setDictSortValue(Constant.DICT_TYPE_LEVEL);
			List<Dictionary> levelList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("levelList", levelList);
			dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
			List<Dictionary> genderList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("genderList", genderList);
			dictionary.setDictSortValue(Constant.DICT_TYPE_MEMBERSTATUS);
			List<Dictionary> memberStatusList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("memberStatusList", memberStatusList);
//			dictionary.setDictSortValue(Constant.DICT_TYPE_PWQUESTION);
//			List<Dictionary> memberPWQuestionList = dictionaryService.getList(dictionary);
//			commonModelAndView.addObject("memberPWQuestionList", memberPWQuestionList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
        return commonModelAndView; 
    }  
	
	
	/**
	 *  跳转到会员修改页面
	 *  @param member
	 *  @param request
	 *  @return
	 *  @author baokai
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-3 下午1:13:58
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/clientcenter/member/maintain/modify")
    public CommonModelAndView modify(Member member,HttpServletRequest request,Map<String,Object> model){
        String code = member.getC();
		if (member.getId() != null){
			try {
				Member m = new Member();
				m.setId(member.getId());
				member = memberService.getOneById(m);
				if(member != null && member.getPwQuestion() != null)
					member.setPwQuestion(CommonUtil.unescape(member.getPwQuestion()));
				if(member != null && member.getPwResult() != null)
					member.setPwResult(CommonUtil.unescape(member.getPwResult()));
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		member.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,member);
		try {
			Dictionary dictionary = new Dictionary();
			dictionary.setDictSortValue(Constant.DICT_TYPE_LEVEL);
			List<Dictionary> levelList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("levelList", levelList);
			dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
			List<Dictionary> genderList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("genderList", genderList);
			dictionary.setDictSortValue(Constant.DICT_TYPE_MEMBERSTATUS);
			List<Dictionary> memberStatusList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("memberStatusList", memberStatusList);
			dictionary.setDictSortValue(Constant.DICT_TYPE_PWQUESTION);
			List<Dictionary> memberPWQuestionList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("memberPWQuestionList", memberPWQuestionList);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		model.put("member", member);
		return commonModelAndView; 
    } 
	
	@RequestMapping("/clientcenter/member/maintain/pseudoDelete")
    public CommonModelAndView pseudoDelete(Member member,HttpServletRequest request){
		String viewName = null;		
		try {
			String memberName = URLDecoder.decode(member.getMemberName(), Constant.ENCODING);
			member.setMemberName(memberName);
			memberService.pseudoDelete(member);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,member,messageSource);
		return commonModelAndView;					
    }
	
	@RequestMapping("/clientcenter/member/maintain/emptyauthenticationcode")
    public CommonModelAndView emptyAuthenticationCode(Member member,HttpServletRequest request){
		String viewName = null;		
		try {
			String memberName = URLDecoder.decode(member.getMemberName(), Constant.ENCODING);
			member.setMemberName(memberName);
			memberService.emptyAuthenticationCode(member);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,member,messageSource);
		return commonModelAndView;					
    }
	
	@RequestMapping("/clientcenter/member/maintain/restore")
    public CommonModelAndView restore(Member member,HttpServletRequest request){
		String viewName = null;		
		try {
			String memberName = URLDecoder.decode(member.getMemberName(), Constant.ENCODING);
			member.setMemberName(memberName);
			memberService.restore(member);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,member,messageSource);
		return commonModelAndView;					
    }
	
	@RequestMapping("/clientcenter/member/maintain/stop")
    public CommonModelAndView stop(Member member,HttpServletRequest request){
		String viewName = null;		
		try {
			String memberName = URLDecoder.decode(member.getMemberName(), Constant.ENCODING);
			member.setMemberName(memberName);
			memberService.stop(member);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,member,messageSource);
		return commonModelAndView;					
    }
	
	@RequestMapping("/clientcenter/member/maintain/start")
    public CommonModelAndView start(Member member,HttpServletRequest request){
		String viewName = null;		
		try {
			String memberName = URLDecoder.decode(member.getMemberName(), Constant.ENCODING);
			member.setMemberName(memberName);
			memberService.start(member);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,member,messageSource);
		return commonModelAndView;					
    }
	
	@RequestMapping("/clientcenter/member/maintain/delete")
    public CommonModelAndView delete(Member member,HttpServletRequest request){
		String viewName = null;		
		try {
			String memberName = URLDecoder.decode(member.getMemberName(), Constant.ENCODING);
			member.setMemberName(memberName);
			if(member!=null && member.getId()!=null){
				memberService.delete(member);
			}else if(member!=null && member.getIds()!=null){
				memberService.deleteByArr(member);
			}
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,member,messageSource);
		return commonModelAndView;					
    }  
	
	
	/**
	 *  列表json
	 *  @param member
	 *  @param request
	 *  @return
	 *  @author baokai
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-3 下午1:14:14
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/clientcenter/member/maintain/grid")
	public CommonModelAndView json(Member member,HttpServletRequest request){
		member.setFinancialPlanner(CommonUtil.convertSearchSign(member.getFinancialPlanner()));
		member.setRegistPhone(CommonUtil.convertSearchSign(member.getRegistPhone()));
		Map<String, Object> map = memberService.getGrid(member); 
		if(member.getSelectValues()!=null && !(member.getSelectValues().equals(""))){
			member.setSelectValues(member.getSelectValues().replace("\"", "\\\""));
		}
		return new CommonModelAndView("jsonView",map,member,request); 
	}
	
	@RequestMapping("/clientcenter/member/maintain/statistic")
    public CommonModelAndView statistic(Member member,HttpServletRequest request,Map<String,Object> model){ 
        Object obj = new CommonModelAndView().getCurrentStatus(member, request);
		if (obj != null){
			if (obj instanceof Member){
				member = (Member)obj;
			}
		} 
		member.setDelStatus(Constant.MEMBER_DELSTATUS_NORMAL);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,member);
		try {
			Dictionary dictionary = new Dictionary();
    		dictionary.setDictSortValue(Constant.DICT_TYPE_DATASOURCE);
    		List<Dictionary> sourceList = dictionaryService.getList(dictionary);
    		commonModelAndView.addObject("dataSourceList", sourceList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		commonModelAndView.addObject("code", member.getC());
		model.put("member", member);
        return commonModelAndView;
    }
	
	@RequestMapping("/clientcenter/member/maintain/statisticGrid")
	public CommonModelAndView statisticGrid(Member member,HttpServletRequest request){
		Map<String, Object> map = null;
		try {
			if(member.getSqlCondition() == null || ("").equals(member.getSqlCondition())){
				member.setSqlCondition("ME.IsNewClient = 1");
			}
			map = memberService.getGrid(member);
			Member tm = new Member();
			tm.setSqlCondition("ME.IsNewClient = 1");
			Long count = memberService.getRecordCount(tm);
			Map<String,Object> cmap = new HashMap<String,Object>();
			cmap.put("newTotal", count);
			map.put("userdata", cmap);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new CommonModelAndView("jsonView",map,member,request); 
	}
	
	@RequestMapping("/clientcenter/member/maintain/detail")
    public CommonModelAndView detail(Member member,HttpServletRequest request,Map<String,Object> model){
        String code = member.getC();
		if (member.getId() != null){
			try {
				Member m = new Member();
				m.setId(member.getId());
				member = memberService.getOneById(m);
				if(member != null && member.getPwQuestion() != null)
					member.setPwQuestion(CommonUtil.unescape(member.getPwQuestion()));
				if(member != null && member.getPwResult() != null)
					member.setPwResult(CommonUtil.unescape(member.getPwResult()));
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		member.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,member);
		try {
			Dictionary dictionary = new Dictionary();
			dictionary.setDictSortValue(Constant.DICT_TYPE_LEVEL);
			List<Dictionary> levelList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("levelList", levelList);
			dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
			List<Dictionary> genderList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("genderList", genderList);
			dictionary.setDictSortValue(Constant.DICT_TYPE_MEMBERSTATUS);
			List<Dictionary> memberStatusList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("memberStatusList", memberStatusList);
			dictionary.setDictSortValue(Constant.DICT_TYPE_PWQUESTION);
			List<Dictionary> memberPWQuestionList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("memberPWQuestionList", memberPWQuestionList);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		model.put("member", member);
		return commonModelAndView; 
    } 

	@Override
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@RequestMapping("/clientcenter/member/maintain/export")
    public @ResponseBody Map<String, Object> exportMember(Member member,HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			member.setFinancialPlanner(CommonUtil.convertSearchSign(member.getFinancialPlanner()));
			member.setRegistPhone(CommonUtil.convertSearchSign(member.getRegistPhone()));
			XSSFWorkbook wb = memberService.exportMember(member);
			Date date = new Date(System.currentTimeMillis());
		    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			String fileName = "APPMember"+"("+df.format(date)+").xlsx";
			String path = request.getSession().getServletContext().getRealPath("/")+"excel/";
			File f = new File(path);
			if(f!=null&&!f.exists()){ 
				f.mkdirs();
			}
			FileOutputStream fileOut = new FileOutputStream(path+fileName);
			wb.write(fileOut);
			fileOut.close();
			map.put("status", 1);
			map.put("path",path);
			map.put("filename",fileName);
		} catch (Exception e){
			e.printStackTrace();
		}
		return map;
    } 
	
	@RequestMapping("/clientcenter/member/maintain/downloadExcel")
	public void downloadExcel(String n,String p,HttpServletRequest request, HttpServletResponse response) throws IOException{
		InputStream in = null ;
		OutputStream out = null ;
		byte[] data = null;
		try {
			n = java.net.URLDecoder.decode(n, "UTF-8");
			p = java.net.URLDecoder.decode(p, "UTF-8");
			File file = new File(p+n);
			in = new FileInputStream(file);
			data = new byte[in.available()];
			in.read(data);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(n, "UTF-8"));
			out = response.getOutputStream();
			out.write(data);
			out.flush();
		} catch (Exception e){
			e.printStackTrace();
		}finally{  
			if(in != null) in.close() ;
			if(out != null) out.close();
	    }  
	}
	
	@RequestMapping("/clientcenter/member/maintain/certification")
    public CommonModelAndView certification(Member member,HttpServletRequest request){
		String viewName = null;		
		try {
			memberService.certification();
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,member,messageSource);
		return commonModelAndView;					
    }
	
	@RequestMapping("/clientcenter/member/maintain/crmsynchronous")
    public CommonModelAndView crmsynchronous(Member member,HttpServletRequest request){
		String viewName = null;		
		try {
			memberService.crmSynchronous(member);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,member,messageSource);
		return commonModelAndView;					
    }
	
}

