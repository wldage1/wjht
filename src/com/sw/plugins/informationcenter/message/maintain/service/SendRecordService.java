/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.plugins.informationcenter.message.maintain.service
 * FileName: MessageSendrecordService.java
 * @version 1.0
 * @author 消息发送记录
 * @created on 2012-5-8
 * @last Modified 
 * @history
 */
package com.sw.plugins.informationcenter.message.maintain.service;

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
import com.sw.core.exception.DetailException;
import com.sw.core.service.CommonService;
import com.sw.plugins.clientcenter.member.maintain.entity.Member;
import com.sw.plugins.clientcenter.member.maintain.service.MemberService;
import com.sw.plugins.informationcenter.message.maintain.entity.Message;
import com.sw.plugins.informationcenter.message.maintain.entity.SendRecord;
import com.sw.plugins.usercenter.config.dynamic.entity.Dictionary;
import com.sw.plugins.usercenter.config.dynamic.service.DictionaryService;
import com.sw.plugins.usercenter.system.user.entity.User;

/**
 *  消息发送记录类
 *  @author zhaofeng
 *  @version 1.0
 *  </pre>
 *  Created on :下午3:12:11
 *  LastModified:
 *  History:
 *  </pre>
 */
@Service
public class SendRecordService extends CommonService<SendRecord>{
	private static Logger logger = Logger.getLogger(SendRecordService.class);
	
	@Resource
    private DictionaryService dictionaryService;

	@Override
	public void save(SendRecord entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(SendRecord entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getRecordCount(SendRecord entity) throws Exception {
		Object obj = getCommonDao().selectObject("sendrecord.selectCount",entity);
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
	public List<? extends BaseEntity> getList(SendRecord entity)
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
	public void delete(SendRecord entity) throws Exception {
		SendRecord paramEntity = new SendRecord();
		if (entity != null && entity.getIds() != null) {
			for (String id : entity.getIds()) {
				paramEntity.setSysId(Long.parseLong(id));
				super.getCommonDao().delete("sendrecord.delete", paramEntity);
			}
		}
		// 单条删除
		if (entity != null && entity.getId() != null) {
			paramEntity.setSysId(entity.getId());
			super.getCommonDao().delete("sendrecord.delete", paramEntity);
		}
	}


	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByIn(SendRecord entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByArr(SendRecord entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SendRecord getOneById(SendRecord entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getGrid(SendRecord entity) {
		List<Object> list = new ArrayList<Object>();
		Map<String, Object> map = new Hashtable<String, Object>();
		//查询条件开始时间和结束时间为同一日期处理
		if( !entity.getStartTime().equals("") && !entity.getEndTime().equals("") && entity.getStartTime().equals(entity.getEndTime())){
			entity.setPublished(entity.getStartTime());
			entity.setStartTime("");
			entity.setEndTime("");
		}
		try {
			List<SendRecord> resultList = getCommonDao().selectList("sendrecord.sendRecordList", entity);
			Dictionary dictionary = new Dictionary();
			dictionary.setDictSortValue(Constant.MSG_TYPE_MSGSOURCE);  //消息来源类型
			Map<Long,Dictionary> msgSourceMap = dictionaryService.getDictMap(dictionary);			
			for (SendRecord sendRecord : resultList) {
				Map<String, Object> mapSendRecord = new Hashtable<String, Object>();
				List<Object> celllist = new ArrayList<Object>();
				celllist.add(sendRecord.getMsgTitle());
				celllist.add(sendRecord.getPublished().substring(0, 10));
				if(msgSourceMap.get((long)sendRecord.getMsgFrom())!=null){
					celllist.add(msgSourceMap.get((long)sendRecord.getMsgFrom()).getName());
				}else{
					celllist.add(sendRecord.getMsgFrom());
				}
				mapSendRecord.put("id", sendRecord.getMsgId());
				mapSendRecord.put("cell", celllist);
				list.add(mapSendRecord);
			}
			// 记录数
			long record = 0;
			record = getRecordCount(entity);
			// 页数
			int pageCount = (int) Math.ceil(record
					/ (double) entity.getRows());
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
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 *  获取发送用户列表数据
	 *  @param entity
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-9 下午6:26:06
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public Map<String, Object> getSendUsersGrid(SendRecord entity) {
		List<Object> list = new ArrayList<Object>();
		Map<String, Object> map = new Hashtable<String, Object>();
		try {
			List<SendRecord> resultList = getCommonDao().selectList("sendrecord.sendRecordUsers", entity);
			Dictionary dictionary = new Dictionary();
			dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
			Map<Long,Dictionary> genderMap = dictionaryService.getDictMap(dictionary);   //性别
			for (SendRecord sendRecord : resultList) {
				Map<String, Object> mapSendRecord = new Hashtable<String, Object>();
				List<Object> celllist = new ArrayList<Object>();
				celllist.add(sendRecord.getMemberName());
				if(sendRecord.getSex()!=null && genderMap.get(sendRecord.getSex())!=null){
					celllist.add(genderMap.get(sendRecord.getSex()).getName());
				}else{
					celllist.add("");
				}
				celllist.add(sendRecord.getCity());
				celllist.add(sendRecord.getMobile());
				celllist.add(sendRecord.getEmail());
				celllist.add(sendRecord.getSendDate());
				mapSendRecord.put("id", sendRecord.getSysId());
				mapSendRecord.put("cell", celllist);
				list.add(mapSendRecord);
			}
			// 记录数
			long record = 0;
			record = getSendUsersCount(entity);
			// 页数
			int pageCount = (int) Math.ceil(record
					/ (double) entity.getRows());
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
	 *  查询发送用户的总数
	 *  @param entity
	 *  @return
	 *  @throws Exception
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-9 下午6:29:18
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public Long getSendUsersCount(SendRecord entity) throws Exception {
		Object obj = getCommonDao().selectObject("sendrecord.selectSenedUsersCount",entity);
		if (obj instanceof Integer){
			return (Long)obj;
		}
		else if (obj instanceof Long){
			return (Long)obj;
		}
		return 0l;
	}
	
	
	
	

}
