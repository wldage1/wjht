package com.sw.core.security;

import java.util.Iterator;
import java.util.List;

import com.sw.core.data.dao.CommonDao;
import com.sw.core.data.entity.Authorization;

public class SecurityCacheInit{
	
	private CommonDao commonDao;
	
	public SecurityCacheInit() {
	}

	public void init() {
		SecurityResourceCache.removeAllCache();
		List<?> res = commonDao.selectList("authorization.select");
		for (Iterator<?> localIterator = res.iterator(); localIterator.hasNext();) {
			Authorization authorization = (Authorization) localIterator.next();
			SecurityResourceCache.putCache(authorization);
		}
	}

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	
}