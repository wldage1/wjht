/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.plugins.productcenter.attribute.structure.service
 * FileName: StructureService.java
 * @version 1.0
 * @author baokai
 * @created on 2012-5-16
 * @last Modified 
 * @history
 */
package com.sw.plugins.productcenter.attribute.structure.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.sw.core.common.Constant;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.data.entity.ProductSort;
import com.sw.core.exception.DetailException;
import com.sw.core.initialize.PluginsConfigCache;
import com.sw.core.service.CommonService;
import com.sw.plugins.clientcenter.member.maintain.service.MemberService;
import com.sw.plugins.productcenter.attribute.structure.entity.Structure;
import com.sw.plugins.usercenter.config.dynamic.entity.Dictionary;
import com.sw.plugins.usercenter.config.dynamic.service.DictionaryService;

/**
 *  类简要说明
 *  @author Administrator
 *  @version 1.0
 *  </pre>
 *  Created on :下午3:00:31
 *  LastModified:
 *  History:
 *  </pre>
 */
@Service
public class StructureService extends CommonService<Structure>{
	
	@Resource
    private DictionaryService dictionaryService;

	@Override
	public void save(Structure entity) throws Exception {
		getCommonDao().save("structure.insert", entity);
	}

	@Override
	public void update(Structure entity) throws Exception {
		getCommonDao().update("structure.update", entity);
	}
	
	public void saveOrUpdate(Structure entity) throws Exception {
		try {
			String structureString = entity.getStructureString();
			Structure s = new Structure();
			s.setProductTypeId(entity.getProductTypeId());
			List<Structure> Structures = getList(s);
			List delIds = new ArrayList();
			for (int i = 0; i < Structures.size(); i++) {
				Structure temp = Structures.get(i);
				delIds.add(temp.getId());
			}
			String[] structureStr = structureString.split("@");
			for(int i = 0; i < structureStr.length; i++ ){
				String[] attributeStr = structureStr[i].split("#");
				Structure structure = new Structure();
				structure.setProductTypeId(entity.getProductTypeId());
				structure.setChineseName(attributeStr[0]);
				structure.setEnglishName(attributeStr[1]);
				structure.setIsRequired(Integer.parseInt(attributeStr[2]));
				structure.setIsEnabled(Integer.parseInt(attributeStr[3]));
				structure.setShowType(Long.parseLong(attributeStr[4]));
				structure.setValue(attributeStr[5]);
				structure.setVerify(Long.parseLong(attributeStr[6]));
				structure.setVerifyValue(attributeStr[7]);
				structure.setIsSreach(Integer.parseInt(attributeStr[8]));
				structure.setIsShowMyProduct(Integer.parseInt(attributeStr[9]));
				structure.setIsShowProductCounter(Integer.parseInt(attributeStr[10]));
				structure.setIsShowOnList(Integer.parseInt(attributeStr[11]));
				structure.setIsShowOnContent(Integer.parseInt(attributeStr[12]));
				structure.setIsOrder(Integer.parseInt(attributeStr[13]));
				if(attributeStr.length > 14 && attributeStr[14]!=null && !attributeStr[14].equals("") && !attributeStr[14].equals("0")){
					structure.setId(Long.parseLong(attributeStr[14]));
					update(structure);
					delIds.remove(Long.parseLong(attributeStr[14]));
				}else{
					save(structure);
				}
			}
			if(delIds.size() > 0){
				for (int i = 0; i < delIds.size(); i++) {
					Long id = (Long) delIds.get(i);
					Structure temp = new Structure();
					temp.setId(id);
					delete(temp);
				}
			}
		} catch (Exception e) {
			DetailException.expDetail(e, StructureService.class);
			throw e;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Structure> getProductAtrribute(Structure structure) throws Exception{
		return (List<Structure>)super.getCommonDao().selectList("structure.selectAttribute",structure);
	}

	@Override
	public Long getRecordCount(Structure entity) throws Exception {
		Object obj = getCommonDao().selectObject("structure.selectCount",entity);
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Structure> getList(Structure entity)throws Exception {
		return getCommonDao().selectList("structure.select", entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<Structure> getListByOrder(Structure entity)throws Exception {
		return getCommonDao().selectList("structure.selectOrder", entity);
	}

	@Override
	public List<? extends BaseEntity> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Structure entity) throws Exception {
		super.getCommonDao().delete("structure.delete",entity);
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByIn(Structure entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByArr(Structure entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Structure getOneById(Structure entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getGrid(Structure entity) {
		Map<String, Object> map = new Hashtable<String, Object>();
		try {
			List<Object> list = new ArrayList<Object>();
			List<ProductSort> productSortList = (List<ProductSort>) PluginsConfigCache.getAllProductSortCache();
			Structure structure = new Structure();
			long record = 0;
			for (ProductSort productSort : productSortList) {
				structure.setProductTypeId(Long.parseLong(productSort.getValue()));
				record = getRecordCount(structure);
				if(record != 0){
					Map<String, Object> mapStructure = new Hashtable<String, Object>();
					List<Object> cellList = new ArrayList<Object>();
					cellList.add(productSort.getName());
					mapStructure.put("id", productSort.getValue());
					mapStructure.put("cell", cellList);
					list.add(mapStructure);
				}
			}
			map.put("rows", list);
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

	public List<ProductSort> filterProductType(List<ProductSort> productSortList) throws Exception {
		Structure entity = new Structure();
		long record = 0;
		for (int i = 0; i < productSortList.size(); i++) {
			ProductSort productSort = productSortList.get(i);
			entity.setProductTypeId(Long.parseLong(productSort.getValue()));
			record = getRecordCount(entity);
			if(record != 0){
				productSortList.remove(i);
				i--;
			}
		}
		return productSortList;
	}
	
	public boolean isUseProductType(Structure entity) throws Exception {
		long record = getRecordCount(entity);
		if(record == 0){
			return true;
		}else{
			return false;
		}
	}

}
