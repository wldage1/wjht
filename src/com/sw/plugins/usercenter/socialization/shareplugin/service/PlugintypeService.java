package com.sw.plugins.usercenter.socialization.shareplugin.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sw.core.data.entity.BaseEntity;
import com.sw.core.service.CommonService;
import com.sw.plugins.usercenter.socialization.shareplugin.entity.Plugintype;

@Service
public class PlugintypeService extends CommonService<Plugintype> {

	private static Logger logger = Logger.getLogger(PlugintypeService.class);

	public Map<String, Object> plugintype;

	public void init() throws ServletException {
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getGrid(Plugintype plugintype) {
		return null;
	}

	/**
	 * 保存插件类型信息
	 * 
	 * @param plugintype
	 * @throws Exception
	 */
	public void saveOrUpdate(Plugintype plugintype) throws Exception {
		if (plugintype.getId() == null) {
			this.save(plugintype);
		} else {
			this.update(plugintype);
		}

	}

	public Long checkDuplication(Plugintype plugintype) throws Exception {
		return (Long) super.getCommonDao().selectObject("plugintype.count_for_duplication", plugintype);
	}


	/**
	 * 保存插件类型
	 */
	@Override
	public void save(Plugintype entity) throws Exception {
		super.getCommonDao().insert("plugintype.insert", entity);
	}

	/**
	 * 理新插件类型
	 */
	@Override
	public void update(Plugintype entity) throws Exception {
		super.getCommonDao().update("plugintype.update", entity);
	}

	/**
	 * 所有插件类型记录条数
	 */
	@Override
	public Long getRecordCount(Plugintype entity) throws Exception {
		return (Long) super.getCommonDao().selectObject("plugintype.count", entity);
	}

	/**
	 * 条件查询插件类型条数
	 */
	@Override
	public Long getAllRecordCount() throws Exception {
		return (Long) super.getCommonDao().selectObject("plugintype.count");
	}

	/**
	 * 获取所有插件类型列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Plugintype> getAll() throws Exception {
		return super.getCommonDao().selectList("plugintype.select");
	}

	/**
	 * 条件获取插件类型列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Plugintype> getList(Plugintype entity) throws Exception {
		return super.getCommonDao().selectList("plugintype.select", entity);
	}

	/**
	 * 删除插件类型
	 */
	@Override
	public void delete(Plugintype entity) throws Exception {
		super.getCommonDao().delete("plugintype.delete", entity);
	}
	
	public void deleteByPluginId(Plugintype entity) throws Exception {
		super.getCommonDao().delete("plugintype.delete_by_pluginid", entity);
	}

	/**
	 * 获取单个插件类型
	 */
	@Override
	public Plugintype getOneById(Plugintype entity) throws Exception {
		return (Plugintype) super.getCommonDao().selectObject("plugintype.select", entity);
	}

	/**
	 * 删除所有插件类型
	 */
	@Override
	public void deleteAll() throws Exception {
		super.getCommonDao().delete("plugintype.delete");
	}

	/**
	 * 批量删除插件类型
	 */
	@Override
	public void deleteByIn(Plugintype entity) throws Exception {
		super.getCommonDao().delete("plugintype.deleteByIn");
	}

	@Override
	public void deleteByArr(Plugintype entity) throws Exception {
		Plugintype paramPlugintype = new Plugintype();
		if (entity != null && entity.getIds() != null) {
			for (String id : entity.getIds()) {
				paramPlugintype.setId(Long.parseLong(id));
				super.getCommonDao().delete("plugintype.delete", paramPlugintype);
			}
		}
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
