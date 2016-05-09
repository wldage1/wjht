package com.sw.plugins.clientcenter.member.generateaccount.service;

import java.net.URL;
import java.util.Date;

import org.apache.log4j.Logger;

import com.sw.core.common.SystemProperty;
import com.sw.core.data.dao.CommonDao;
import com.sw.core.data.dbholder.DatasourceTypeContextHolder;
import com.sw.core.exception.DetailException;
import com.sw.core.quartz.IQuartz;
import com.sw.core.util.DateUtil;
import com.sw.core.util.Encrypt;
import com.sw.plugins.clientcenter.member.generateaccount.entity.GenerateAccount;
import com.sw.plugins.clientcenter.member.generateaccount.entity.SmsSender;
import com.sw.plugins.clientcenter.member.maintain.entity.Member;
import com.sw.plugins.clientcenter.member.maintain.service.MemberService;
import com.sw.thirdparty.crm.webservice.CrmMember;
import com.sw.thirdparty.crm.webservice.impl.CRMMemberWebServiceServiceLocator;
import com.sw.thirdparty.webservice.WebserviceConfig;
import com.sw.thirdparty.webservice.WebserviceConfigCache;

/**
 *  类简要说明
 *  @author Administrator
 *  @version 1.0
 *  </pre>
 *  Created on :下午1:33:00
 *  LastModified:
 *  History:
 *  </pre>
 */
public class GenerateAccountQuartz implements IQuartz {

	private static Logger logger = Logger.getLogger(GenerateAccountQuartz.class);
	private CommonDao commonDao;

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public void execute() {
		this.generate();
	}
	
	
	/**
	 *  定时生成账号
	 *  @author baokai
	 *  Created on :2013-11-21 上午11:17:46
	 */
	private void generate() {
		try {
			logger.debug("generate account , please waiting...");
			Date date = new Date();
			Date d = new Date(date.getTime()-1000*60*60*24*8);
			System.out.println(DateUtil.dateToString(d));
			String now = DateUtil.dateToString(date);
			String week = DateUtil.dateToString(d);
			WebserviceConfig wsc = WebserviceConfigCache.getCache("crmMemberWebService");
			URL url = new URL(wsc.getValue());
			CRMMemberWebServiceServiceLocator cwssl = new CRMMemberWebServiceServiceLocator();
			CrmMember[] crmMembers = cwssl.getCRMMemberWebServicePort(url).findCRMTradeClientInfoByTime(week,now);
			if(crmMembers != null && crmMembers.length > 0){
				CrmMember cm = null;
				GenerateAccount ga = null;
				for (int i = 0; i < crmMembers.length; i++) {
					cm = crmMembers[i];
					//判断是否已存在账号
					Member m = new Member();
					m.setMobilePhone(cm.getMobilePhone());
					long count = getMemberRecordCount(m);
					if(count > 0){
						continue;
					}
					//判断是否已接受过短信
					ga = new GenerateAccount();
					ga.setCrmId(cm.getCrmID());
					ga.setMobilePhone(cm.getMobilePhone());
					count = getRecordCount(ga);
					if(count > 0){
						continue;
					}
					String code = DateUtil.dateToString(new Date(), DateUtil.TIME_PATTERN);
					ga.setPassWord(Encrypt.getMD5(code));
					ga.setUserName(cm.getMobilePhone());
					save(ga);
					ga.setPassWord(code);
					smsSend(ga);
				}
			}	
			logger.debug("generate account , please finish");	
		} catch (Exception e) {
			logger.debug("generate account error");
			e.printStackTrace();
		}
	}

	
	private Long getRecordCount(GenerateAccount entity) throws Exception {
		Object obj = getCommonDao().selectObject("generateAccount.selectCount",entity);
		if (obj instanceof Integer){
			return (Long)obj;
		}
		else if (obj instanceof Long){
			return (Long)obj;
		}
		return 0l;
	}
	
	private Long getMemberRecordCount(Member entity) throws Exception {
		Object obj = commonDao.selectObject("member.selectCount",entity);
		if (obj instanceof Integer){
			return (Long)obj;
		}
		else if (obj instanceof Long){
			return (Long)obj;
		}
		return 0l;
	}
	
	private void save(GenerateAccount entity) throws Exception {
		try {
			commonDao.save("generateAccount.insert", entity);
		} catch (Exception e) {
			DetailException.expDetail(e, MemberService.class);
			throw e;
		}
		
	}
	
	private void smsSend(GenerateAccount entity){
		try {
			SmsSender ss = new SmsSender();
			String time = DateUtil.dateToString(new Date(), "yyyy-MM-dd");
			ss.setServiceID(SystemProperty.getInstance("parameterConfig").getProperty("serviceId"));
			ss.setRecompleteTimeBegin(time);
			ss.setRecompleteTimeEnd(time);
			ss.setRecompleteHourBegin("0");
			ss.setRecompleteHourEnd("1439");
			ss.setPriority("1");
			ss.setRodeBy("1");
			ss.setSendTarget(entity.getMobilePhone());
			String template = SystemProperty.getInstance("parameterConfig").getProperty("sms_template");
			template = template.replace("userName", entity.getUserName()).replace("passWord", entity.getPassWord());
			ss.setSmContent(template);
			// 设置数据源
			DatasourceTypeContextHolder.setDataSourceType("sqlserver_dataSource3");
			commonDao.insert("sms.insert", ss);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DatasourceTypeContextHolder.clear();
		}
	}

}
