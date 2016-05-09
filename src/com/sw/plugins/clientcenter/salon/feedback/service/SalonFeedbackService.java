package com.sw.plugins.clientcenter.salon.feedback.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sw.core.data.entity.BaseEntity;
import com.sw.core.service.CommonService;
import com.sw.plugins.clientcenter.salon.feedback.entity.SalonFeedback;

@Service
public class SalonFeedbackService extends CommonService<SalonFeedback> {

	private static Logger logger = Logger.getLogger(SalonFeedbackService.class);
	
	@Override
	public void save(SalonFeedback entity) throws Exception {
		super.getCommonDao().save("messageFeedBack.insert",entity);
	}

	@Override
	public void update(SalonFeedback entity) throws Exception {
		
	}

	@Override
	public Long getRecordCount(SalonFeedback entity) throws Exception {
		return (Long)super.getCommonDao().selectObject("messageFeedBack.selectCount", entity);
	}

	@Override
	public Long getAllRecordCount() throws Exception {
		return (Long)super.getCommonDao().selectObject("messageFeedBack.selectCount");
	}

	@Override
	public List<? extends BaseEntity> getList(SalonFeedback entity)
			throws Exception {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<? extends BaseEntity> getAll() throws Exception {
		return super.getCommonDao().selectList("messageFeedBack.select");
	}

	@Override
	public void delete(SalonFeedback entity) throws Exception {
		super.getCommonDao().delete("messageFeedBack.delete",entity);
	}

	@Override
	public void deleteAll() throws Exception {
		
	}

	@Override
	public void deleteByIn(SalonFeedback entity) throws Exception {
		
	}

	@Override
	public void deleteByArr(SalonFeedback entity) throws Exception {
		if (entity != null && entity.getIds() != null && entity.getStatus() != null){
			for (String id : entity.getIds()){
				entity.setId(Long.parseLong(id));
				//修改删除状态	0:正常	1：假删除
				super.getCommonDao().delete("messageFeedBack.update",entity);
			}
		}else{
			for (String id : entity.getIds()){
				entity.setId(Long.parseLong(id));
				//真删除
				delete(entity);
			}
		}
	}

	@Override
	public SalonFeedback getOneById(SalonFeedback entity) throws Exception {
		SalonFeedback salonFeedback = (SalonFeedback) super.getCommonDao().selectObject("messageFeedBack.select", entity);
		//是否参加
		//String isJoinView = salonFeedback.getIsJoin()==1? Constant.SALON_MESSAGE_FEEDBACK_YESJOIN : Constant.SALON_MESSAGE_FEEDBACK_NOJOIN;
		salonFeedback.setIsJoin(salonFeedback.getIsJoin());
		return salonFeedback;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getGrid(SalonFeedback entity) {
		List<Object> list = new ArrayList<Object>();
		List<SalonFeedback> resultList = null;
		resultList = getCommonDao().selectList("messageFeedBack.selectPaginated", entity); 
		for (SalonFeedback salonFeedback : resultList) {
			Map<String, Object> maprole = new Hashtable<String, Object>();
			List<Object> celllist = new ArrayList<Object>();
			celllist.add(salonFeedback.getTitle());
			celllist.add(salonFeedback.getPeopleMobilePhone());
			//判断是否参加会议
			//String isJoin = salonFeedback.getIsJoin()==1? Constant.SALON_MESSAGE_FEEDBACK_YESJOIN : Constant.SALON_MESSAGE_FEEDBACK_NOJOIN;
			celllist.add(salonFeedback.getIsJoin());
			celllist.add(salonFeedback.getPeopleNumber());
			maprole.put("id", salonFeedback.getId());
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
	
	@SuppressWarnings("unchecked")
	public List<SalonFeedback> export(SalonFeedback entity) throws Exception {
		List<SalonFeedback> resultList = null;
		resultList = getCommonDao().selectList("messageFeedBack.exportSelect", entity);
		return resultList;
	}
	
	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		return null;
	}


}
