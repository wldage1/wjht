package com.sw.plugins.clientcenter.client.maintain.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sw.core.common.Constant;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.exception.DetailException;
import com.sw.core.service.CommonService;
import com.sw.core.util.CommonUtil;
import com.sw.core.util.DateUtil;
import com.sw.plugins.clientcenter.client.maintain.entity.Client;
import com.sw.plugins.clientcenter.client.maintain.entity.Consume;
import com.sw.plugins.clientcenter.member.generateaccount.service.GenerateAccountService;
import com.sw.plugins.clientcenter.member.maintain.entity.Member;
import com.sw.plugins.usercenter.config.dynamic.entity.Dictionary;
import com.sw.plugins.usercenter.config.dynamic.service.DictionaryService;
import com.sw.plugins.usercenter.system.user.entity.User;
import com.sw.plugins.usercenter.system.user.service.UserService;

/**
 * Service实现类 - Service实现类基类
 */

@Service
public class ClientService extends CommonService<Client> {

	private static Logger logger = Logger.getLogger(ClientService.class);

	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private GenerateAccountService generateAccountService;
	@Resource
	private UserService userService;
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getGrid(Client entity) {
		Map<String, Object> map = new Hashtable<String, Object>();
		try {
			List<Object> list = new ArrayList<Object>();
			User u = new User();
			u.setId(Long.valueOf(entity.getCreator()));
			u = userService.getRoleByUid(u);
			if(u != null && !u.getRoleName().contains("代理")){
				entity.setCreator("0");
			}
			if("1".equals(entity.getCreator())){
				entity.setCreator("0");
			}
			if(CommonUtil.isNotEmpty(entity.getEndTime())){
				entity.setEndTime(entity.getEndTime() +" 23:59:59");
			}
			List<Client> resultList = getCommonDao().selectList("client.selectPaginated", entity);
			for (Client client : resultList) {
				Map<String, Object> mapMember = new Hashtable<String, Object>();
				List<Object> cellList = new ArrayList<Object>();
				cellList.add(client.getName());
				cellList.add(client.getPhone());
				cellList.add(client.getPermit());
				String cardNum = client.getCardNum();
				if(StringUtils.isNotEmpty(cardNum)){
					StringBuffer url = new StringBuffer();
					url.append("<a href='javascript:void(0);' style='color: #333333;' onclick=sendCard("+ client.getId() +","+ cardNum + "," + client.getCardType() + ")>");
					url.append(cardNum);
					url.append("</a>");
					cellList.add(url);
				}else{
					cellList.add("");
				}
				String cardStatus = client.getCardStatus();
				cellList.add(cardStatus); 
				cellList.add(client.getCardType());
				if("2".equals(client.getCardStatus())){
					cellList.add(client.getCredit());
				}else{
					cellList.add("");
				}
				cellList.add(client.getUserName());
				cellList.add(DateUtil.dateToString(client.getCreateTime() ,DateUtil.DATETIME_PATTERN));
				cellList.add(client.getCreateTime());
				mapMember.put("id", client.getId());
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
			DetailException.expDetail(e, ClientService.class);
		}
		return map;
	}

	@Override
	public Long getRecordCount(Client entity) throws Exception {
		Object obj = getCommonDao().selectObject("client.selectCount",entity);
		if (obj instanceof Integer){
			return (Long)obj;
		}
		else if (obj instanceof Long){
			return (Long)obj;
		}
		return 0l;
	}
	
	@Override
	public void save(Client entity) throws Exception {
		super.getCommonDao().update("client.insert", entity);

	}

	@Override
	public void update(Client entity) throws Exception {
		super.getCommonDao().update("client.update", entity);

	}

	@Override
	public Long getAllRecordCount() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends BaseEntity> getList(Client entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends BaseEntity> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Client entity) throws Exception {
		super.getCommonDao().delete("client.update",entity);
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByIn(Client entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByArr(Client entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Client getOneById(Client entity) throws Exception {
		if (entity == null || entity.getId() == null)
			return null;
		Object obj = getCommonDao().selectObject("client.selectOne", entity);
		if (obj instanceof Client){
			return (Client)obj;
		}
		return null;
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> consumeGrid(Consume consume) {
		Map<String, Object> map = new Hashtable<String, Object>();
		try {
			List<Object> list = new ArrayList<Object>();
		
			List<Consume> resultList = getCommonDao().selectList("consume.selectPaginated", consume);
			for (Consume c : resultList) {
				Map<String, Object> mapMember = new Hashtable<String, Object>();
				List<Object> cellList = new ArrayList<Object>();
				cellList.add(DateUtil.dateToString(c.getCreateTime() ,DateUtil.DATETIME_PATTERN));
				cellList.add(c.getMoney());
				cellList.add(c.getDescription());
				cellList.add(c.getUserName());
				mapMember.put("id", c.getId());
				mapMember.put("cell", cellList);
				list.add(mapMember);
			}
			// 记录数
			long record = 0;
			record = getCousmeRecordCount(consume);
			// 页数
			int pageCount = (int) Math.ceil(record / (double) consume.getRows());
			map.put("rows", list);
			map.put("page", consume.getPage());
			map.put("total", pageCount);
			map.put("records", record);
		} catch (Exception e) {
			DetailException.expDetail(e, ClientService.class);
		}
		return map;
	}

	private long getCousmeRecordCount(Consume consume) {
		Object obj = getCommonDao().selectObject("consume.selectCount",consume);
		if (obj instanceof Integer){
			return (Long)obj;
		}
		else if (obj instanceof Long){
			return (Long)obj;
		}
		return 0l;
	}

}