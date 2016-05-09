package com.sw.core.service.dictionarySort;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.sw.core.data.entity.BaseEntity;
import com.sw.core.data.entity.DictionarySort;
import com.sw.core.service.CommonService;

/**
 * Service实现类 -
 */

@Service
public class DictionarySortService extends CommonService<DictionarySort>{
	
	public DictionarySortService() {
	}
	
	@Override
	public Map<String, Object> getGrid(DictionarySort entity) {
		return null;
	}
	
	@Override
	public void save(DictionarySort entity) throws Exception {
		super.getCommonDao().insert("dictionarySort.insert", entity);
	}
	
	@Override
	public void update(DictionarySort entity) throws Exception {
		super.getCommonDao().update("dictionarySort.update", entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DictionarySort> getAll() throws Exception {
		return super.getCommonDao().selectList("dictionarySort.select");
	}
	
	@Override
	public void delete(DictionarySort entity) {
		super.getCommonDao().delete("dictionarySort.delete", entity);
	}
	
	@Override
	public DictionarySort getOneById(DictionarySort entity) throws Exception {
		if (entity == null || entity.getId() == null)
			return null;
		Object obj = getCommonDao().selectObject("dictionarySort.select", entity);
		if (obj instanceof DictionarySort){
			return (DictionarySort)obj;
		}
		return null;
	}
	
	@Override
	public Long getRecordCount(DictionarySort entity) throws Exception {
		Object obj = getCommonDao().selectObject("dictionarySort.selectCount",entity);
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
		Object obj = getCommonDao().selectObject("dictionarySort.selectCount");
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
	public List<DictionarySort> getList(DictionarySort entity) throws Exception {
		return super.getCommonDao().selectList("dictionarySort.select",entity);
	}

	@Override
	public void deleteAll() throws Exception {
		super.getCommonDao().delete("dictionarySort.delete");
	}

	@Override
	public void deleteByIn(DictionarySort entity) throws Exception {
		super.getCommonDao().delete("dictionarySort.deleteByIn");
	}

	@Override
	public void deleteByArr(DictionarySort entity) throws Exception {
		DictionarySort paramDictionarySort = new DictionarySort();
		if (entity != null && entity.getIds() != null){
			for (String id : entity.getIds()){
				paramDictionarySort.setId(Long.parseLong(id));
				super.getCommonDao().delete("dictionarySort.delete",paramDictionarySort);
			}
		}
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}