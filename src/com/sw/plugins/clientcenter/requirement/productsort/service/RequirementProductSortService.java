package com.sw.plugins.clientcenter.requirement.productsort.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.sw.core.data.entity.BaseEntity;
import com.sw.core.service.CommonService;
import com.sw.plugins.clientcenter.requirement.productsort.entity.RequirementProductSort;

@Service
public class RequirementProductSortService extends CommonService<RequirementProductSort> {
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getGrid(RequirementProductSort entity) {
		List<Object> list = new ArrayList<Object>();
		List<RequirementProductSort> resultList = null;
		resultList = getCommonDao().selectList("requirementProductSort.selectPaginated", entity); 
		for (RequirementProductSort productSort : resultList) {
			Map<String, Object> maprole = new Hashtable<String, Object>();
			List<Object> celllist = new ArrayList<Object>();
			celllist.add(productSort.getName());
			celllist.add(productSort.getDescription());
			maprole.put("id", productSort.getId());
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
	public void save(RequirementProductSort entity) throws Exception {
		super.getCommonDao().save("requirementProductSort.insert",entity);
	}

	@Override
	public void update(RequirementProductSort entity) throws Exception {
		super.getCommonDao().update("requirementProductSort.update", entity);
	}

	@Override
	public Long getRecordCount(RequirementProductSort entity) throws Exception {
		return (Long)super.getCommonDao().selectObject("requirementProductSort.selectCount", entity);
	}

	
	@Override
	public Long getAllRecordCount() throws Exception {
		return (Long)super.getCommonDao().selectObject("requirementProductSort.selectCount");
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RequirementProductSort> getList(RequirementProductSort entity) throws Exception {
		return super.getCommonDao().selectList("requirementProductSort.selectCount");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RequirementProductSort> getAll() throws Exception {
		return super.getCommonDao().selectList("requirementProductSort.select");
	}	
	
	@Override
	public void deleteAll() throws Exception {
		super.getCommonDao().delete("requirementProductSort.delete");
	}

	@Override
	public void deleteByIn(RequirementProductSort entity) throws Exception {
		super.getCommonDao().delete("requirementProductSort.deleteByIn");
	}

	@Override
	public void deleteByArr(RequirementProductSort entity) throws Exception {
		if (entity != null && entity.getIds() != null){
			for (String id : entity.getIds()){
				entity.setId(Long.parseLong(id));
				super.getCommonDao().delete("requirementProductSort.delete",entity);
			}
		}
	}
	
	/**
	 *根据Id查询需要修改的数据
	 */
	@Override
	public RequirementProductSort getOneById(RequirementProductSort entity) throws Exception {
		if(entity == null)
			return null;
		RequirementProductSort requirementProductSort = (RequirementProductSort)super.getCommonDao().selectObject("requirementProductSort.select", entity);
		return requirementProductSort;
	}

	@Override
	public void delete(RequirementProductSort entity) throws Exception {
		super.getCommonDao().delete("requirementProductSort.delete",entity);
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		return null;
	}
}
