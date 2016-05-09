package com.sw.plugins.clientcenter.reservation.consult.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.sw.core.common.Constant;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.service.CommonService;
import com.sw.plugins.clientcenter.reservation.consult.entity.Consult;
import com.sw.plugins.usercenter.config.dynamic.entity.Dictionary;
import com.sw.plugins.usercenter.config.dynamic.service.DictionaryService;

@Service
public class ConsultService extends CommonService<Consult> {
	
	@Resource
	private DictionaryService dictionaryService;

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getGrid(Consult entity) {
		List<Object> list = new ArrayList<Object>();
		List<Consult> resultList = null;
		resultList = getCommonDao().selectList("consult.selectPaginated", entity); 
		for (Consult consult : resultList) {
			Map<String, Object> maprole = new Hashtable<String, Object>();
			List<Object> celllist = new ArrayList<Object>();
			celllist.add(consult.getName());
			//获取数据字典对应值
			Dictionary dictionary = new Dictionary();
			dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
			Map<Long,Dictionary> genderMap = dictionaryService.getDictMap(dictionary);
			if(consult.getGender()!=null && genderMap.get(consult.getGender())!=null){
				celllist.add(genderMap.get(consult.getGender()).getName());
			}else{
				celllist.add("");
			}
			celllist.add(consult.getMobilePhone());
			celllist.add(com.sw.core.util.DateUtil.dateToStringWithTime(consult.getCreateTime()));
			maprole.put("id", consult.getId());
			maprole.put("cell", celllist);
			list.add(maprole);
		}
		
		// 记录数
		long record = 0;
		try {
			record = getRecordCount(entity);
		} catch (Exception e) {
			e.printStackTrace();
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
	public void save(Consult entity) throws Exception {
		super.getCommonDao().save("consult.insert",entity);
	}

	@Override
	public void update(Consult entity) throws Exception {
		super.getCommonDao().update("consult.update", entity);
	}

	@Override
	public Long getRecordCount(Consult entity) throws Exception {
		return (Long)super.getCommonDao().selectObject("consult.selectCount", entity);
	}

	
	@Override
	public Long getAllRecordCount() throws Exception {
		return (Long)super.getCommonDao().selectObject("consult.selectCount");
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Consult> getList(Consult entity) throws Exception {
		return super.getCommonDao().selectList("consult.selectCount");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Consult> getAll() throws Exception {
		return super.getCommonDao().selectList("consult.select");
	}	
	
	@Override
	public void deleteAll() throws Exception {
		super.getCommonDao().delete("consult.delete");
	}

	@Override
	public void deleteByIn(Consult entity) throws Exception {
		super.getCommonDao().delete("consult.deleteByIn");
	}

	@Override
	public void deleteByArr(Consult entity) throws Exception {
		//假删除与还原
		if (entity != null && entity.getIds() != null && entity.getState() != null){
			for (String id : entity.getIds()){
				entity.setId(Long.parseLong(id));
				super.getCommonDao().delete("consult.updateStatus",entity);
			}
		}else{
			for (String id : entity.getIds()){
				entity.setId(Long.parseLong(id));
				delete(entity);
			}
		}
	}
	
	/**
	 *根据Id查询需要修改的数据
	 */
	@Override
	public Consult getOneById(Consult entity) throws Exception {
		if(entity == null)
			return null;
		Consult consult = (Consult)super.getCommonDao().selectObject("consult.select", entity);
		//获取数据字典对应值
		Dictionary dictionary = new Dictionary();
		dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
		Map<Long,Dictionary> genderMap = dictionaryService.getDictMap(dictionary);
		if(consult.getGender() !=null && genderMap.get(consult.getGender())!=null){
			consult.setGenderName(genderMap.get(consult.getGender()).getName());
		}else{
			consult.setGenderName("");
		}
		return consult;
	}

	@Override
	public void delete(Consult entity) throws Exception {
		super.getCommonDao().delete("consult.delete",entity);
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		return null;
	}

}
