package com.sw.plugins.clientcenter.member.maintain.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.sw.core.common.Constant;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.exception.DetailException;
import com.sw.core.service.CommonService;
import com.sw.core.util.poi.excel.write.Excel2007WriteHandler;
import com.sw.plugins.clientcenter.member.generateaccount.entity.GenerateAccount;
import com.sw.plugins.clientcenter.member.generateaccount.service.GenerateAccountService;
import com.sw.plugins.clientcenter.member.maintain.entity.Member;
import com.sw.plugins.usercenter.config.dynamic.entity.Dictionary;
import com.sw.plugins.usercenter.config.dynamic.service.DictionaryService;
import com.sw.thirdparty.crm.webservice.CrmMember;
import com.sw.thirdparty.crm.webservice.impl.CRMMemberWebServiceServiceLocator;
import com.sw.thirdparty.webservice.WebserviceConfig;
import com.sw.thirdparty.webservice.WebserviceConfigCache;

/**
 * Service实现类 - Service实现类基类
 */

@Service
public class MemberService extends CommonService<Member>{

	private static Logger logger = Logger.getLogger(MemberService.class);
	
	@Resource
    private DictionaryService dictionaryService;
	@Resource
    private GenerateAccountService generateAccountService;
	
	@Override
	public Map<String, Object> getGrid(Member entity) {
		Map<String, Object> map = new Hashtable<String, Object>();
		try {
			List<Object> list = new ArrayList<Object>();
			List<Member> resultList = null;
			resultList = getCommonDao().selectList("member.selectPaginated", entity); 
			Dictionary dictionary = new Dictionary();
//			dictionary.setDictSortValue(Constant.DICT_TYPE_LEVEL);
//			Map<Long,Dictionary> levelMap = dictionaryService.getDictMap(dictionary);
			dictionary.setDictSortValue(Constant.DICT_TYPE_DATASOURCE);
			Map<Long,Dictionary> dataSourceMap = dictionaryService.getDictMap(dictionary);
//			dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
//			Map<Long,Dictionary> genderMap = dictionaryService.getDictMap(dictionary);
			for (Member member : resultList) {
				Map<String, Object> mapMember = new Hashtable<String, Object>();
				List<Object> cellList = new ArrayList<Object>();
				cellList.add(member.getUserName());
//				cellList.add(member.getMemberName());
				cellList.add(member.getCrmId());
//				if(member.getSex()!=null && genderMap.get(member.getSex())!=null){
//					cellList.add(genderMap.get(member.getSex()).getName());
//				}else{
//					cellList.add("");
//				}
				cellList.add(member.getFinancialPlanner());
//				cellList.add(castMobilePhone(member.getMobilePhone()));
				String branchOrg = member.getBranchOrg();
//				if(branchOrg != null && !branchOrg.equals("") && branchOrg.indexOf("-") > 0){
//					branchOrg = branchOrg.substring(branchOrg.indexOf("-")+1, branchOrg.length());
//					if(branchOrg.indexOf("-") < branchOrg.lastIndexOf("-")){
//						branchOrg = branchOrg.substring(0, branchOrg.lastIndexOf("-"));
//					}
//				}else{
//					branchOrg = "";
//				}
				cellList.add(branchOrg);
				cellList.add(castMobilePhone(member.getRegistPhone()));
//				if(member.getLevel()!=null && levelMap.get(member.getLevel())!=null){
//					cellList.add(levelMap.get(member.getLevel()).getName());
//				}else{
//					cellList.add("");
//				}
				cellList.add(member.getMemberLevel());
				if(member.getSource()!=null && dataSourceMap.get(member.getSource())!=null){
					cellList.add(dataSourceMap.get(member.getSource()).getName());
				}else{
					cellList.add("");
				}
				if(member.getRegistrationTime()!=null){
					cellList.add(member.getRegistrationTime().substring(0, member.getRegistrationTime().length()-2));
				}else{
					cellList.add("");
				}
				cellList.add(member.getStatus());
				mapMember.put("id", member.getId());
				mapMember.put("cell", cellList);
				list.add(mapMember);
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
			DetailException.expDetail(e, MemberService.class);
		}
		return map;
	}
	
	/**
	 * 电话号码干扰
	 * @param phone
	 * @return
	 */
	private String castMobilePhone(String phone){
		StringBuffer castPhone = new StringBuffer();
		if(phone != null && phone.length()>0){
//			castPhone.append(phone.substring(0,phone.length() - (phone.substring(3)).length()));
//			castPhone.append("****");
//			castPhone.append(phone.substring(7));
			
			castPhone.append(phone.substring(0,7));
			castPhone.append("****");
		}
		return castPhone.toString();
	}
	
	public Map<String, Object> getGridForSendMessage(Member entity) {
		Map<String, Object> map = new Hashtable<String, Object>();
		try {
			List<Object> list = new ArrayList<Object>();
			List<Member> resultList = null;
			resultList = getCommonDao().selectList("member.selectPaginatedForSendMessage", entity); 
			Dictionary dictionary = new Dictionary();
			dictionary.setDictSortValue(Constant.DICT_TYPE_LEVEL);
			Map<Long,Dictionary> levelMap = dictionaryService.getDictMap(dictionary);
			dictionary.setDictSortValue(Constant.DICT_TYPE_DATASOURCE);
			Map<Long,Dictionary> dataSourceMap = dictionaryService.getDictMap(dictionary);
			dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
			Map<Long,Dictionary> genderMap = dictionaryService.getDictMap(dictionary);
			for (Member member : resultList) {
				Map<String, Object> mapMember = new Hashtable<String, Object>();
				List<Object> cellList = new ArrayList<Object>();
				cellList.add(member.getMemberName());
				if(member.getSex()!=null && genderMap.get(member.getSex())!=null){
					cellList.add(genderMap.get(member.getSex()).getName());
				}else{
					cellList.add("");
				}
				cellList.add(member.getMobilePhone());
				if(member.getLevel()!=null && levelMap.get(member.getLevel())!=null){
					cellList.add(levelMap.get(member.getLevel()).getName());
				}else{
					cellList.add("");
				}
				if(member.getSource()!=null && dataSourceMap.get(member.getSource())!=null){
					cellList.add(dataSourceMap.get(member.getSource()).getName());
				}else{
					cellList.add("");
				}
				cellList.add(member.getStatus());
				mapMember.put("id", member.getId());
				mapMember.put("cell", cellList);
				list.add(mapMember);
			}
			// 记录数
			long record = 0;
			record = getRecordCountForSendMessage(entity);
			// 页数
			int pageCount = (int) Math.ceil(record / (double) entity.getRows());
			map.put("rows", list);
			map.put("page", entity.getPage());
			map.put("total", pageCount);
			map.put("records", record);
		} catch (Exception e) {
			DetailException.expDetail(e, MemberService.class);
		}
		return map;
	}
	
	@Override
	public void save(Member entity) throws Exception {
		try {
			entity.setMemberLevel("新会员");
			getCommonDao().save("member.insertMember", entity);
			entity.setSource(Long.parseLong(Constant.MEMBER_SOURCE_REGISTRATION));
			getCommonDao().save("member.insertMemberExtend", entity);
			//通过mobilePhone查找是否有生成的账号并删除
			GenerateAccount ga = new GenerateAccount();
			ga.setMobilePhone(entity.getMobilePhone());
			generateAccountService.delete(ga);
		} catch (Exception e) {
			DetailException.expDetail(e, MemberService.class);
			throw e;
		}
	}

	@Override
	public void update(Member entity) throws Exception {
		try {
			getCommonDao().update("member.updateMember",entity);
			getCommonDao().update("member.updateMemberExtend",entity);
			//通过mobilePhone查找是否有生成的账号并删除
			GenerateAccount ga = new GenerateAccount();
			ga.setMobilePhone(entity.getMobilePhone());
			generateAccountService.delete(ga);
		} catch (Exception e) {
			DetailException.expDetail(e, MemberService.class);
			throw e;
		}
		
	}

	@Override
	public Long getAllRecordCount() throws Exception {
		Object obj = getCommonDao().selectObject("member.selectCount");
		if (obj instanceof Integer){
			return (Long)obj;
		}
		else if (obj instanceof Long){
			return (Long)obj;
		}
		return 0l;
	}

	@Override
	public Long getRecordCount(Member entity) throws Exception {
		Object obj = getCommonDao().selectObject("member.selectCount",entity);
		if (obj instanceof Integer){
			return (Long)obj;
		}
		else if (obj instanceof Long){
			return (Long)obj;
		}
		return 0l;
	}
	
	public Long getRecordCountForSendMessage(Member entity) throws Exception {
		Object obj = getCommonDao().selectObject("member.selectCountForSendMessage",entity);
		if (obj instanceof Integer){
			return (Long)obj;
		}
		else if (obj instanceof Long){
			return (Long)obj;
		}
		return 0l;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getAll() throws Exception {
		return getCommonDao().selectList("member.select");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getList(Member entity) throws Exception {
		return getCommonDao().selectList("member.select", entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<Member> getListForSendMessage(Member entity) throws Exception {
		return getCommonDao().selectList("member.selectForSendMessage", entity);
	}

	@Override
	public Member getOneById(Member entity) throws Exception {
		if (entity == null || entity.getId() == null)
			return null;
		Object obj = getCommonDao().selectObject("member.select", entity);
		if (obj instanceof Member){
			return (Member)obj;
		}
		return null;
	}	
	
	@Override
	public void deleteAll() throws Exception {
//		super.getCommonDao().delete("member.delete");
	}

	@Override
	public void deleteByIn(Member entity) throws Exception {
//		super.getCommonDao().delete("member.deleteByIn");
	}

	@Override
	public void deleteByArr(Member entity) throws Exception {
		Member paramMember = new Member();
		if (entity != null && entity.getIds() != null){
			for (String id : entity.getIds()){
				paramMember.setId(Long.parseLong(id));
				this.delete(paramMember);
			}
		}
	}

	@Override
	public void delete(Member entity) throws Exception {
		super.getCommonDao().delete("member.deleteMember",entity);
	}
	
	public void pseudoDelete(Member entity)throws Exception{
		if(entity!=null && entity.getId()!=null){
			entity.setDelStatus(Constant.MEMBER_DELSTATUS_DEL);
			getCommonDao().update("member.updateMemberExtend", entity);
		}else if(entity!=null && entity.getIds()!=null){
			Member paramMember = new Member();
			if (entity != null && entity.getIds() != null){
				for (String id : entity.getIds()){
					paramMember.setId(Long.parseLong(id));
					paramMember.setDelStatus(Constant.MEMBER_DELSTATUS_DEL);
					getCommonDao().update("member.updateMemberExtend", paramMember);
				}
			}
		}
	}
	
	public void emptyAuthenticationCode(Member entity)throws Exception{
		if(entity!=null && entity.getId()!=null){
			getCommonDao().update("member.emptyCRMId", entity);
			getCommonDao().update("member.emptyAuthenticationCode", entity);
		}else if(entity!=null && entity.getIds()!=null){
			Member paramMember = new Member();
			if (entity != null && entity.getIds() != null){
				for (String id : entity.getIds()){
					paramMember.setId(Long.parseLong(id));
					getCommonDao().update("member.emptyCRMId", paramMember);
					getCommonDao().update("member.emptyAuthenticationCode", paramMember);
				}
			}
		}
	}
	
	public void restore(Member entity)throws Exception{
		if(entity!=null && entity.getId()!=null){
			entity.setDelStatus(Constant.MEMBER_DELSTATUS_NORMAL);
			getCommonDao().update("member.updateMemberExtend", entity);
		}else if(entity!=null && entity.getIds()!=null){
			Member paramMember = new Member();
			if (entity != null && entity.getIds() != null){
				for (String id : entity.getIds()){
					paramMember.setId(Long.parseLong(id));
					paramMember.setDelStatus(Constant.MEMBER_DELSTATUS_NORMAL);
					getCommonDao().update("member.updateMemberExtend", paramMember);
				}
			}
		}
	}
	
	public void stop(Member entity)throws Exception{
		if(entity!=null && entity.getId()!=null){
			entity.setStatus(Long.parseLong(Constant.MEMBER_STATUS_STOP));
			getCommonDao().update("member.updateMemberExtend", entity);
		}else if(entity!=null && entity.getIds()!=null){
			Member paramMember = new Member();
			if (entity != null && entity.getIds() != null){
				for (String id : entity.getIds()){
					paramMember.setId(Long.parseLong(id));
					paramMember.setStatus(Long.parseLong(Constant.MEMBER_STATUS_STOP));
					getCommonDao().update("member.updateMemberExtend", paramMember);
				}
			}
		}
	}
	
	public void start(Member entity)throws Exception{
		if(entity!=null && entity.getId()!=null){
			entity.setStatus(Long.parseLong(Constant.MEMBER_STATUS_START));
			getCommonDao().update("member.updateMemberExtend", entity);
		}else if(entity!=null && entity.getIds()!=null){
			Member paramMember = new Member();
			if (entity != null && entity.getIds() != null){
				for (String id : entity.getIds()){
					paramMember.setId(Long.parseLong(id));
					paramMember.setStatus(Long.parseLong(Constant.MEMBER_STATUS_START));
					getCommonDao().update("member.updateMemberExtend", paramMember);
				}
			}
		}
	}
	
	public Long getRecordCountForSave(Member entity) throws Exception {
		Object obj = getCommonDao().selectObject("member.selectCountForSave",entity);
		if (obj instanceof Integer){
			return (Long)obj;
		}
		else if (obj instanceof Long){
			return (Long)obj;
		}
		return 0l;
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public XSSFWorkbook exportMember(Member entity) {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet1 = wb.createSheet("APP客户信息");
		List<List> list1 = new ArrayList<List>();
		List titleList = new ArrayList();
		titleList.add("CRM客户ID号");
		titleList.add("客户级别");
		titleList.add("所属理财师");
		titleList.add("所属分中心");
		titleList.add("注册时间");
		list1.add(titleList);
		try {
			List<Member> resultList = getCommonDao().selectList("member.selectPaginatedForExport", entity);
			List list = null;
			for (Member member : resultList) {
				list = new ArrayList();
				if(member.getCrmId()!=null){
					list.add(member.getCrmId());
				}else{
					list.add("");
				}
				if(member.getMemberLevel()!=null){
					list.add(member.getMemberLevel());
				}else{
					list.add("");
				}
				if(member.getFinancialPlanner()!=null){
					list.add(member.getFinancialPlanner());
				}else{
					list.add("");
				}
				if(member.getBranchOrg()!=null){
					String branchOrg = member.getBranchOrg();
//					if(branchOrg != null && !branchOrg.equals("") && branchOrg.indexOf("-") > 0){
//						branchOrg = branchOrg.substring(branchOrg.indexOf("-")+1, branchOrg.length());
//						if(branchOrg.indexOf("-") < branchOrg.lastIndexOf("-")){
//							branchOrg = branchOrg.substring(0, branchOrg.lastIndexOf("-"));
//						}
//					}else{
//						branchOrg = "";
//					}
					list.add(branchOrg);
				}else{
					list.add("");
				}
				if(member.getRegistrationTime()!=null){
					list.add(member.getRegistrationTime().substring(0, member.getRegistrationTime().length()-2));
				}else{
					list.add("");
				}
				list1.add(list);
			}
			Excel2007WriteHandler excel2007WriteHandler = new Excel2007WriteHandler();
			excel2007WriteHandler.fillData(sheet1, list1);
		} catch (Exception e) {
			DetailException.expDetail(e, MemberService.class);
		}
		return wb;
	}
	
	
	public void certification(){
		try {
			List<String> mobilePhoneString = getCommonDao().selectList("member.selectForCertification");
			if(mobilePhoneString != null && mobilePhoneString.size() > 0){
				WebserviceConfig wsc = WebserviceConfigCache.getCache("crmMemberWebService");
				URL url = new URL(wsc.getValue());
				CRMMemberWebServiceServiceLocator cwssl = new CRMMemberWebServiceServiceLocator();
				CrmMember[] crmMembers = cwssl.getCRMMemberWebServicePort(url).getCRMMemberArrayByMobilephones((String[]) mobilePhoneString.toArray(new String[mobilePhoneString.size()]));
				if(crmMembers != null && crmMembers.length > 0){
					Member m = null;
					for (int i = 0; i < mobilePhoneString.size(); i++) {
						String mobilephone = mobilePhoneString.get(i);
						for (int j = 0; j < crmMembers.length; j++) {
							if(mobilephone.equals(crmMembers[j].getMobilePhone()) && crmMembers[j].getCrmID() != null){
								m = new Member();
								m.setMobilePhone(mobilephone);
								m.setCrmId(crmMembers[j].getCrmID());
								getCommonDao().update("member.updateMemberForMP",m);
							}
						}
					}
					
				}
			}
		} catch (Exception e) {
			DetailException.expDetail(e, MemberService.class);
		}
	}
	
	public void crmSynchronous(Member member){
		try {
			if(member.getIds().length > 0){
				WebserviceConfig wsc = WebserviceConfigCache.getCache("crmMemberWebService");
				URL url = new URL(wsc.getValue());
				CRMMemberWebServiceServiceLocator cwssl = new CRMMemberWebServiceServiceLocator();
				CrmMember[] crmMembers = cwssl.getCRMMemberWebServicePort(url).getCRMMemberInfoArrayByID(member.getIds());
				if(crmMembers != null){
					for (int i = 0; i < crmMembers.length; i++) {
						CrmMember cm = crmMembers[i];
						if(cm != null){
							Member m = new Member();
							m.setCrmId(cm.getCrmID());
							m.setFinancialPlanner(cm.getUserName());
							m.setMemberLevel(cm.getImportance());
							String branchOrg = cm.getBranchName();
							if(branchOrg != null && !branchOrg.equals("") && branchOrg.indexOf("-") > 0){
								branchOrg = branchOrg.substring(branchOrg.indexOf("-")+1, branchOrg.length());
								if(branchOrg.indexOf("-") < branchOrg.lastIndexOf("-")){
									branchOrg = branchOrg.substring(0, branchOrg.lastIndexOf("-"));
								}
							}else{
								branchOrg = "";
							}
							m.setBranchOrg(branchOrg);
							getCommonDao().update("member.updateMemberForSync",m);
						}
					}
				}
			}
		} catch (Exception e) {
			DetailException.expDetail(e, MemberService.class);
		}
		
	}
}