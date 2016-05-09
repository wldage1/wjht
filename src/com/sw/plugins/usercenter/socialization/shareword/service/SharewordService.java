package com.sw.plugins.usercenter.socialization.shareword.service;

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
import com.sw.core.initialize.PluginsConfigInit;
import com.sw.core.service.CommonService;
import com.sw.plugins.usercenter.socialization.shareword.entity.Shareword;

@Service
public class SharewordService extends CommonService<Shareword> {

	private static Logger logger = Logger.getLogger(SharewordService.class);

	public Map<String, Object> shareword;
	
	public Map<String, Object> getShareword() {
		return shareword;
	}

	public void setShareword(Map<String, Object> shareword) {
		this.shareword = shareword;
	}

	public void init() throws ServletException {
		try {
			logger.debug("shareword info initializing , please waiting...");

			if (shareword != null) {
				Iterator<String> iterator = shareword.keySet().iterator();
				while (iterator.hasNext()) {
					String code = null, title = null;
					code = iterator.next();
					if (code != null) {
						title = shareword.get(code) == null ? "" : shareword.get(code).toString();
					}
					if (code != null && title != null) {
						Shareword shareword = new Shareword();
						shareword.setTitle(title);
						shareword.setCode(code);
						Long soci = (Long) super.getCommonDao().selectObject("shareword.count_by_code", shareword);
						if (soci != 0) {
							super.getCommonDao().update("shareword.update_by_code", shareword);
						} else {
							super.getCommonDao().insert("shareword.insert", shareword);
						}
					}
				}
			}
			logger.debug("shareword info initialize finished.");
		} catch (Exception e) {
			logger.debug("shareword info initialize fail！");
			String debug = DetailException.expDetail(e, PluginsConfigInit.class);
			logger.debug(debug);
			/** 异常退出系统 */
			System.exit(0);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getGrid(Shareword shareword) {
		List<Object> list = new ArrayList<Object>();
		List<Shareword> resultList = null;
		resultList = getCommonDao().selectList("shareword.select", new Shareword());
		for (Shareword soci : resultList) {
			Map<String, Object> map = new Hashtable<String, Object>();
			List<Object> celllist = new ArrayList<Object>();
			celllist.add(soci.getTitle());
			celllist.add(soci.getCode());
			map.put("id", soci.getId());
			map.put("cell", celllist);
			list.add(map);
		}
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("rows", list);
		return map;
	}

	/**
	 * 保存分享语
	 */
	@Override
	public void save(Shareword entity) throws Exception {
		super.getCommonDao().insert("shareword.insert", entity);
	}

	/**
	 * 理新分享语
	 */
	@Override
	public void update(Shareword entity) throws Exception {
		super.getCommonDao().update("shareword.update", entity);

	}

	/**
	 * 所有分享语记录条数
	 */
	@Override
	public Long getRecordCount(Shareword entity) throws Exception {
		return (Long) super.getCommonDao().selectObject("shareword.count", entity);
	}

	/**
	 * 条件查询分享语条数
	 */
	@Override
	public Long getAllRecordCount() throws Exception {
		return (Long) super.getCommonDao().selectObject("shareword.count");
	}

	/**
	 * 获取所有分享语列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Shareword> getAll() throws Exception {
		return super.getCommonDao().selectList("shareword.select");
	}

	/**
	 * 条件获取分享语列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Shareword> getList(Shareword entity) throws Exception {
		return super.getCommonDao().selectList("shareword.select", entity);
	}

	/**
	 * 删除分享语
	 */
	@Override
	public void delete(Shareword entity) throws Exception {
		super.getCommonDao().delete("shareword.delete", entity);
	}

	/**
	 * 获取单个分享语
	 */
	@Override
	public Shareword getOneById(Shareword entity) throws Exception {
		return (Shareword) super.getCommonDao().selectObject("shareword.select", entity);
	}

	/**
	 * 删除所有分享语
	 */
	@Override
	public void deleteAll() throws Exception {
		super.getCommonDao().delete("shareword.delete");
	}

	/**
	 * 批量删除分享语
	 */
	@Override
	public void deleteByIn(Shareword entity) throws Exception {
		super.getCommonDao().delete("shareword.deleteByIn");
	}

	@Override
	public void deleteByArr(Shareword entity) throws Exception {
		Shareword paramShareword = new Shareword();
		if (entity != null && entity.getIds() != null) {
			for (String id : entity.getIds()) {
				paramShareword.setId(Long.parseLong(id));
				super.getCommonDao().delete("shareword.delete", paramShareword);
			}
		}
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}
