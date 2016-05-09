package com.sw.plugins.clientcenter.salon.message.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sw.core.common.Constant;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.service.CommonService;
import com.sw.plugins.clientcenter.salon.message.entity.SalonMessage;
import com.sw.plugins.informationcenter.message.maintain.entity.Message;
import com.sw.plugins.informationcenter.message.maintain.service.MessageService;
import com.sw.plugins.usercenter.config.dynamic.entity.Dictionary;
import com.sw.plugins.usercenter.config.dynamic.service.DictionaryService;

@Service
public class SalonMessageService extends CommonService<SalonMessage> {

	private static Logger logger = Logger.getLogger(SalonMessageService.class);
	
	@Resource
	private MessageService messageService;
	
	@Resource
	private DictionaryService dictionaryService;
	
	@Override
	public void save(SalonMessage entity) throws Exception {
		super.getCommonDao().save("SalonMessage.insert",entity);
	}

	@Override
	public void update(SalonMessage entity) throws Exception {
		super.getCommonDao().update("SalonMessage.update", entity);
	}

	@Override
	public Long getRecordCount(SalonMessage entity) throws Exception {
		return (Long)super.getCommonDao().selectObject("SalonMessage.selectCount", entity);
	}

	@Override
	public Long getAllRecordCount() throws Exception {
		return (Long)super.getCommonDao().selectObject("SalonMessage.selectCount");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<? extends BaseEntity> getList(SalonMessage entity) throws Exception {
		return super.getCommonDao().selectList("SalonMessage.selectCount");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<? extends BaseEntity> getAll() throws Exception {
		return super.getCommonDao().selectList("SalonMessage.select");
	}

	@Override
	public void delete(SalonMessage entity) throws Exception {
		super.getCommonDao().delete("SalonMessage.delete",entity);
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByIn(SalonMessage entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByArr(SalonMessage entity) throws Exception {
		if (entity != null && entity.getIds() != null){
			for (String id : entity.getIds()){
				entity.setId(Long.parseLong(id));
				super.getCommonDao().delete("SalonMessage.delete",entity);
			}
		}
	}

	@Override
	public SalonMessage getOneById(SalonMessage entity) throws Exception {
		if(entity == null)
			return null;
		SalonMessage sm = (SalonMessage)super.getCommonDao().selectObject("SalonMessage.select", entity);
		sm.setStatusViewName(getDictNameByMessageStatus(sm.getStatus()));
		sm.setMeetingTime(sm.getMeetingTime().substring(0, 10));
		return sm;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getGrid(SalonMessage entity) {
		List<Object> list = new ArrayList<Object>();
		List<SalonMessage> resultList = null;
		resultList = getCommonDao().selectList("SalonMessage.selectPaginated", entity); 
		for (SalonMessage salonMessage : resultList) {
			Map<String, Object> maprole = new Hashtable<String, Object>();
			List<Object> celllist = new ArrayList<Object>();
			celllist.add(salonMessage.getTitle());
			celllist.add(salonMessage.getMeetingTime());
			celllist.add(salonMessage.getMeetingPlace());
			celllist.add(getDictNameByMessageStatus(salonMessage.getStatus()));
			celllist.add(salonMessage.getStatus());
			maprole.put("id", salonMessage.getId());
			maprole.put("cell", celllist);
			list.add(maprole);
		}
		
		// 记录数
		long record = 0;
		try {
			record = getRecordCount(entity);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		// 页数
		int pageCount = (int) Math.ceil(record / (double) entity.getRows());
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("rows", list);
		map.put("page", entity.getPage());
		map.put("total", pageCount);
		map.put("records", record);
		return map;
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		return null;
	}
	
	/**
     * 返回消息状态列表
     * @return List<Dictionary>
     */
	public List<Dictionary> getMessageStatusList(){
		Dictionary dictionary = new Dictionary();
		dictionary.setDictSortValue(Constant.SALON_MESSAGE_STATES);
		List<Dictionary> messageStatusList = null;
		try {
			messageStatusList = dictionaryService.getList(dictionary);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return messageStatusList;
	}

	/**
     * 根据dictSortValue查找字典Name
     * @return String
     */
	public String getDictNameByMessageStatus(Long dicValue){
		Dictionary dictionary = new Dictionary();
		dictionary.setDictSortValue(Constant.SALON_MESSAGE_STATES);
		Map<Long,Dictionary> statusMap = dictionaryService.getDictMap(dictionary);
		if(statusMap.get(dicValue)!=null){
			return statusMap.get(dicValue).getName();
		}else{
			return "";
		}
	}
	
	/**
     * 根据dictSortValue查找字典value
     * @return String
     */
	public String getDictValueByMessageStatus(Long dicValue){
		Dictionary dictionary = new Dictionary();
		dictionary.setDictSortValue(Constant.SALON_MESSAGE_STATES);
		Map<Long,Dictionary> statusMap = dictionaryService.getDictMap(dictionary);
		if(statusMap.get(dicValue)!=null){
			return statusMap.get(dicValue).getValue();
		}else{
			return "";
		}
	}
	
	/**
     * 把理财沙龙消息推送出去
     */
	public void pushMessage(SalonMessage entity) throws Exception {
		Message message = new Message();
		message.setSourceId(entity.getId());
		message.setSourceType(2);
		message.setMsgTitle(entity.getTitle());
		message.setMsgContent(entity.getMeetingContent());
		message.setMsgFrom(11);
		messageService.save(message);
	}
}
