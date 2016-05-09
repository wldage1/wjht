package com.sw.thirdparty.webservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.dao.DataRetrievalFailureException;

import com.opensymphony.oscache.util.StringUtil;

public class WebserviceConfigCache {
	
	private static final String PLUGIN_CACHE_WEBSERVICE_NAME = "plugin_webservice_config";
	
	private static Cache cache = CacheManager.getInstance().getCache(PLUGIN_CACHE_WEBSERVICE_NAME);

	public static synchronized void putCache(WebserviceConfig webserviceConfig) {
		if (webserviceConfig != null && !StringUtil.isEmpty(webserviceConfig.getKey())){
			Element element = new Element(webserviceConfig.getKey(), webserviceConfig);
			cache.put(element);
		}
	}

	public static synchronized WebserviceConfig getCache(String key) {
		Element element = null;
		try {
			element = cache.get(key);
		} catch (CacheException cacheException) {
			throw new DataRetrievalFailureException("ResourceCache failure: "
					+ cacheException.getMessage(), cacheException);
		}
		if (element == null)
			return null;

		return ((WebserviceConfig) element.getObjectValue());
	}

	public static synchronized void removeCache(String key) {
		cache.remove(key);
	}

	public static synchronized void removeAllCache() {
		cache.removeAll();
		cache.clearStatistics();
		cache.flush();
	}

	@SuppressWarnings("unchecked")
	public static synchronized Collection<WebserviceConfig> getAllCache() {
		Collection<String> resources;
		List<WebserviceConfig> resclist = new ArrayList<WebserviceConfig>();
		try {
			resources = cache.getKeys();
		} catch (IllegalStateException e) {
			throw new IllegalStateException(e.getMessage(), e);
		} catch (CacheException e) {
			throw new UnsupportedOperationException(e.getMessage(), e);
		}
		for (Iterator<String> localIterator = resources.iterator(); localIterator.hasNext();) {
			String key = localIterator.next();
			WebserviceConfig webserviceConfig = getCache(key);
			resclist.add(webserviceConfig);
		}
		return resclist;
	}
}