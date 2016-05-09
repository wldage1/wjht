package com.sw.plugins.usercenter.socialization.shareplugin.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sw.core.data.entity.BaseEntity;
import com.sw.core.service.CommonService;
import com.sw.plugins.usercenter.socialization.shareplugin.entity.Plugintype;
import com.sw.plugins.usercenter.socialization.shareplugin.entity.Shareplugin;

@Service
public class SharepluginService extends CommonService<Shareplugin> {

	private static Logger logger = Logger.getLogger(SharepluginService.class);
	
	@Resource
	private PlugintypeService plugintypeService;

	public Map<String, Object> shareplugin;

	public void init() throws ServletException {
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getGrid(Shareplugin shareplugin) {
		List<Object> list = new ArrayList<Object>();
		List<Shareplugin> resultList = null;
		resultList = getCommonDao().selectList("shareplugin.select", new Shareplugin());
		for (Shareplugin soci : resultList) {
			Map<String, Object> map = new Hashtable<String, Object>();
			List<Object> celllist = new ArrayList<Object>();
			celllist.add(soci.getName());
			celllist.add(soci.getAuthoriKey());
			map.put("id", soci.getId());
			map.put("cell", celllist);
			list.add(map);
		}
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("rows", list);
		return map;
	}

	/**
	 * 保存分享配置信息
	 * 
	 * @param shareplugin
	 * @throws Exception
	 */
	public void saveOrUpdate(Shareplugin shareplugin) throws Exception {
		if (shareplugin.getId() == null) {
			this.save(shareplugin);
			shareplugin.setId(shareplugin.getGeneratedKey());
		} else {
			this.update(shareplugin);
		}
		
		// 清空插件类型
		Plugintype pt = new Plugintype();
		pt.setPluginId(shareplugin.getId());
		plugintypeService.deleteByPluginId(pt);
		// 保存插件类型表
		if(shareplugin.getTypes() != null){
			for(Integer type : shareplugin.getTypes()){
				pt = new Plugintype();
				pt.setPluginId(shareplugin.getId());
				pt.setType(type);
				plugintypeService.save(pt);
			}
		}

	}

	public Long checkDuplication(Shareplugin shareplugin) throws Exception {
		return (Long) super.getCommonDao().selectObject("shareplugin.count_for_duplication", shareplugin);
	}

	/**
	 * 保存分享配置
	 * 
	 * @param entity
	 */
	public void saveConfig(Shareplugin entity) {
		// 重置所有排序及状态
		super.getCommonDao().update("shareplugin.reset_all_plugin", entity);
		Shareplugin sp = new Shareplugin();
		for(int i = 0; i < entity.getIds().length; i++){
			sp.setId(Long.parseLong(entity.getIds()[i]));
			sp.setStatus(entity.getStatuses()[i]);
			sp.setSortNumber(i);
			super.getCommonDao().update("shareplugin.update", sp);
		}
		
	}

	/**
	 * 保存分享配置
	 */
	@Override
	public void save(Shareplugin entity) throws Exception {
		super.getCommonDao().insert("shareplugin.insert", entity);
	}

	/**
	 * 理新分享配置
	 */
	@Override
	public void update(Shareplugin entity) throws Exception {
		super.getCommonDao().update("shareplugin.update", entity);
	}

	/**
	 * 所有分享配置记录条数
	 */
	@Override
	public Long getRecordCount(Shareplugin entity) throws Exception {
		return (Long) super.getCommonDao().selectObject("shareplugin.count", entity);
	}

	/**
	 * 条件查询分享配置条数
	 */
	@Override
	public Long getAllRecordCount() throws Exception {
		return (Long) super.getCommonDao().selectObject("shareplugin.count");
	}

	/**
	 * 获取所有分享配置列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Shareplugin> getAll() throws Exception {
		return super.getCommonDao().selectList("shareplugin.select");
	}

	/**
	 * 条件获取分享配置列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Shareplugin> getList(Shareplugin entity) throws Exception {
		return super.getCommonDao().selectList("shareplugin.select", entity);
	}

	/**
	 * 删除分享配置
	 */
	@Override
	public void delete(Shareplugin entity) throws Exception {
		super.getCommonDao().delete("shareplugin.delete", entity);
	}

	/**
	 * 获取单个分享配置
	 */
	@Override
	public Shareplugin getOneById(Shareplugin entity) throws Exception {
		return (Shareplugin) super.getCommonDao().selectObject("shareplugin.select", entity);
	}

	/**
	 * 删除所有分享配置
	 */
	@Override
	public void deleteAll() throws Exception {
		super.getCommonDao().delete("shareplugin.delete");
	}

	/**
	 * 批量删除分享配置
	 */
	@Override
	public void deleteByIn(Shareplugin entity) throws Exception {
		super.getCommonDao().delete("shareplugin.deleteByIn");
	}

	@Override
	public void deleteByArr(Shareplugin entity) throws Exception {
		Shareplugin paramShareplugin = new Shareplugin();
		if (entity != null && entity.getIds() != null) {
			for (String id : entity.getIds()) {
				paramShareplugin.setId(Long.parseLong(id));
				super.getCommonDao().delete("shareplugin.delete", paramShareplugin);
			}
		}
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
