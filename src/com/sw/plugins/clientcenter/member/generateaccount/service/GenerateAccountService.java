package com.sw.plugins.clientcenter.member.generateaccount.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.sw.core.common.SystemProperty;
import com.sw.core.data.dbholder.DatasourceTypeContextHolder;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.exception.DetailException;
import com.sw.core.service.CommonService;
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
 * Service实现类 - Service实现类基类
 */

@Service
public class GenerateAccountService extends CommonService<GenerateAccount>{

	private static Logger logger = Logger.getLogger(GenerateAccountService.class);
	
	@Resource
    private MemberService memberService;
	
	@Override
	public Map<String, Object> getGrid(GenerateAccount entity) {
		Map<String, Object> map = new Hashtable<String, Object>();
		try {
			List<Object> list = new ArrayList<Object>();
			List<GenerateAccount> resultList = null;
			resultList = getCommonDao().selectList("generateAccount.selectPaginated", entity); 
			for (GenerateAccount generateAccount : resultList) {
				Map<String, Object> mapGenerateAccount = new Hashtable<String, Object>();
				List<Object> cellList = new ArrayList<Object>();
				cellList.add(generateAccount.getCrmId());
				cellList.add(generateAccount.getMobilePhone());
				cellList.add(generateAccount.getUserName());
				cellList.add(generateAccount.getGenerateTime().substring(0, generateAccount.getGenerateTime().length()-2));
				mapGenerateAccount.put("id", generateAccount.getId());
				mapGenerateAccount.put("cell", cellList);
				list.add(mapGenerateAccount);
			}
			// 记录数
			long record = 0;
			record = getRecordCount(entity);
			// 页数
			int pageCount = (int) Math.ceil(record / (double) entity.getRows());
			map.put("rows", list);
			map.put("page", entity.getPage());
			map.put("total", pageCount);
			map.put("records", record);
		} catch (Exception e) {
			DetailException.expDetail(e, GenerateAccountService.class);
		}
		return map;
	}


	@Override
	public void save(GenerateAccount entity) throws Exception {
		try {
			getCommonDao().save("generateAccount.insert", entity);
		} catch (Exception e) {
			DetailException.expDetail(e, MemberService.class);
			throw e;
		}
		
	}


	@Override
	public void update(GenerateAccount entity) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Long getRecordCount(GenerateAccount entity) throws Exception {
		Object obj = getCommonDao().selectObject("generateAccount.selectCount",entity);
		if (obj instanceof Integer){
			return (Long)obj;
		}
		else if (obj instanceof Long){
			return (Long)obj;
		}
		return 0l;
	}


	@Override
	public Long getAllRecordCount() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<? extends BaseEntity> getList(GenerateAccount entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<? extends BaseEntity> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(GenerateAccount entity) throws Exception {
		super.getCommonDao().delete("generateAccount.delete",entity);
	}


	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteByIn(GenerateAccount entity) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteByArr(GenerateAccount entity) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public GenerateAccount getOneById(GenerateAccount entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void generate(GenerateAccount entity){
		try {
			if(entity != null){
				WebserviceConfig wsc = WebserviceConfigCache.getCache("crmMemberWebService");
				URL url = new URL(wsc.getValue());
				CRMMemberWebServiceServiceLocator cwssl = new CRMMemberWebServiceServiceLocator();
				CrmMember[] crmMembers = cwssl.getCRMMemberWebServicePort(url).findCRMTradeClientInfoByTime(entity.getStartTime(), entity.getEndTime());
				if(crmMembers != null && crmMembers.length > 0){
					CrmMember cm = null;
					GenerateAccount ga = null;
					for (int i = 0; i < crmMembers.length; i++) {
						cm = crmMembers[i];
						//判断是否已存在账号
						Member m = new Member();
						m.setMobilePhone(cm.getMobilePhone());
						long count = memberService.getRecordCount(m);
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
						System.out.println("############################password is :" + code);
						ga.setPassWord(code);
						smsSend(ga);
					}
				}	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void smsSend(GenerateAccount entity){
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
			getCommonDao().insert("sms.insert", ss);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DatasourceTypeContextHolder.clear();
		}
	}
	
	
}