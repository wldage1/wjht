package com.sw.plugins.usercenter.socialization.appversion.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sw.core.data.entity.BaseEntity;
import com.sw.core.exception.DetailException;
import com.sw.core.initialize.InitialData;
import com.sw.core.initialize.PluginsConfigInit;
import com.sw.core.service.CommonService;
import com.sw.plugins.usercenter.socialization.appversion.entity.AppVersion;

@Service
public class AppVersionService extends CommonService<AppVersion> {

	private static Logger logger = Logger.getLogger(AppVersionService.class);

	public InitialData initialData;
	

	public InitialData getInitialData() {
		return initialData;
	}

	public void setInitialData(InitialData initialData) {
		this.initialData = initialData;
	}

	public void init() throws ServletException {
		try {
			logger.debug("appversion info initializing , please waiting...");

			if (initialData != null) {
				Map<String,Object> deviceTypeMap = initialData.getDeviceType();
				Iterator<String> iterator = deviceTypeMap.keySet().iterator();
				while (iterator.hasNext()) {
					String deviceType = null;
					deviceType = iterator.next();
					if (deviceType != null) {
						AppVersion appversion = new AppVersion();
						appversion.setDeviceType(Integer.valueOf(deviceType));
						Long cv = (Long) super.getCommonDao().selectObject("appversion.count_by_devicetype", appversion);
						if (cv == 0) {
							super.getCommonDao().insert("appversion.insert", appversion);
						}
					}
				}
			}
			logger.debug("appversion info initialize finished.");
		} catch (Exception e) {
			logger.debug("appversion info initialize fail！");
			String debug = DetailException.expDetail(e, PluginsConfigInit.class);
			logger.debug(debug);
			/** 异常退出系统 */
			System.exit(0);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getGrid(AppVersion appversion) {
		List<Object> list = new ArrayList<Object>();
		List<AppVersion> resultList = null;
		resultList = getCommonDao().selectList("appversion.select", new AppVersion());
		for (AppVersion version : resultList) {
			Map<String, Object> map = new Hashtable<String, Object>();
			List<Object> celllist = new ArrayList<Object>();
			celllist.add(version.getDeviceType());
			celllist.add(version.getVersion());
			celllist.add(version.getDownloadURL());
			celllist.add(version.getNoticeRate());
			map.put("id", version.getId());
			map.put("cell", celllist);
			list.add(map);
		}
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("rows", list);
		return map;
	}

	/**
	 * 保存
	 */
	@Override
	public void save(AppVersion entity) throws Exception {
		super.getCommonDao().insert("appversion.insert", entity);
	}

	/**
	 * 理新
	 */
	@Override
	public void update(AppVersion entity) throws Exception {
		super.getCommonDao().update("appversion.update", entity);

	}

	/**
	 * 所有记录条数
	 */
	@Override
	public Long getRecordCount(AppVersion entity) throws Exception {
		return (Long) super.getCommonDao().selectObject("appversion.count", entity);
	}

	/**
	 * 条件查询条数
	 */
	@Override
	public Long getAllRecordCount() throws Exception {
		return (Long) super.getCommonDao().selectObject("appversion.count");
	}

	/**
	 * 获取所有列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AppVersion> getAll() throws Exception {
		return super.getCommonDao().selectList("appversion.select");
	}

	/**
	 * 条件获取列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AppVersion> getList(AppVersion entity) throws Exception {
		return super.getCommonDao().selectList("appversion.select", entity);
	}

	/**
	 * 删除
	 */
	@Override
	public void delete(AppVersion entity) throws Exception {
		super.getCommonDao().delete("appversion.delete", entity);
	}

	/**
	 * 获取单个
	 */
	@Override
	public AppVersion getOneById(AppVersion entity) throws Exception {
		return (AppVersion) super.getCommonDao().selectObject("appversion.select", entity);
	}

	/**
	 * 删除所有
	 */
	@Override
	public void deleteAll() throws Exception {
		super.getCommonDao().delete("appversion.delete");
	}

	/**
	 * 批量删除
	 */
	@Override
	public void deleteByIn(AppVersion entity) throws Exception {
		super.getCommonDao().delete("appversion.deleteByIn");
	}

	@Override
	public void deleteByArr(AppVersion entity) throws Exception {
		AppVersion paramAppVersion = new AppVersion();
		if (entity != null && entity.getIds() != null) {
			for (String id : entity.getIds()) {
				paramAppVersion.setId(Long.parseLong(id));
				super.getCommonDao().delete("appversion.delete", paramAppVersion);
			}
		}
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}
