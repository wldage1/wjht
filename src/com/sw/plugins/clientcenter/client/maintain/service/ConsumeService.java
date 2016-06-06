package com.sw.plugins.clientcenter.client.maintain.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.sw.core.data.entity.BaseEntity;
import com.sw.core.service.CommonService;
import com.sw.plugins.clientcenter.client.maintain.entity.Client;
import com.sw.plugins.clientcenter.client.maintain.entity.Consume;

@Service
public class ConsumeService extends CommonService<Consume> {

	@Override
	public void save(Consume entity) throws Exception {
		super.getCommonDao().insert("consume.insert",entity);
	}

	@Override
	public void update(Consume entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getRecordCount(Consume entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getAllRecordCount() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends BaseEntity> getList(Consume entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends BaseEntity> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Consume entity) throws Exception {
		super.getCommonDao().delete("consume.delete",entity);
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByIn(Consume entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByArr(Consume entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Consume getOneById(Consume entity) throws Exception {
		if (entity == null || entity.getId() == null)
			return null;
		Object obj = getCommonDao().selectObject("consume.selectOne", entity);
		if (obj instanceof Consume){
			return (Consume)obj;
		}
		return null;
	}

	@Override
	public Map<String, Object> getGrid(Consume entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public Consume getMoneyTotal(Consume consume) {
		if (consume == null || consume.getClientId() == null)
			return null;
		Object obj = getCommonDao().selectObject("consume.selectMoneySum", consume);
		if (obj instanceof Consume){
			return (Consume)obj;
		}
		return null;
	}

}
