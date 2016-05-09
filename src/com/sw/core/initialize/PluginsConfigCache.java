package com.sw.core.initialize;

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
import com.sw.core.data.entity.Authorization;
import com.sw.core.data.entity.ProductSort;

public class PluginsConfigCache {
	
	private static final String PLUGIN_CACHE_AUTH_NAME = "plugin_auth_config";
	private static final String PLUGIN_CACHE_PRODUCT_SORT_NAME = "plugin_product_sort_config";
	
	private static Cache cache = CacheManager.getInstance().getCache(PLUGIN_CACHE_AUTH_NAME);
	private static Cache productSortCache = CacheManager.getInstance().getCache(PLUGIN_CACHE_PRODUCT_SORT_NAME);

	public static synchronized void putCache(Authorization authorization) {
		if (authorization != null && !StringUtil.isEmpty(authorization.getCode())){
			Element element = new Element(authorization.getCode(), authorization);
			cache.put(element);
		}
	}

	public static synchronized Authorization getCache(String code) {
		Element element = null;
		try {
			element = cache.get(code);
		} catch (CacheException cacheException) {
			throw new DataRetrievalFailureException("ResourceCache failure: "
					+ cacheException.getMessage(), cacheException);
		}
		if (element == null)
			return null;

		return ((Authorization) element.getValue());
	}

	public static synchronized void removeCache(String code) {
		cache.remove(code);
	}

	public static synchronized void removeAllCache() {
		cache.removeAll();
		cache.clearStatistics();
		cache.flush();
	}

	public static synchronized Collection<Authorization> getAllCache() {
		Collection<String> resources;
		List<Authorization> resclist = new ArrayList<Authorization>();
		try {
			resources = cache.getKeys();
		} catch (IllegalStateException e) {
			throw new IllegalStateException(e.getMessage(), e);
		} catch (CacheException e) {
			throw new UnsupportedOperationException(e.getMessage(), e);
		}
		for (Iterator<String> localIterator = resources.iterator(); localIterator.hasNext();) {
			String code = localIterator.next();
			Authorization authorization = getCache(code);
			resclist.add(authorization);
		}
		return resclist;
	}
	
	
	public static synchronized void putProductSortCache(ProductSort productSort) {
		if (productSort != null && !StringUtil.isEmpty(productSort.getValue())){
			Element element = new Element(productSort.getValue(), productSort);
			productSortCache.put(element);
		}
	}

	public static synchronized ProductSort getProductSortCache(String value) {
		Element element = null;
		try {
			element = productSortCache.get(value);
		} catch (CacheException cacheException) {
			throw new DataRetrievalFailureException("ResourceCache failure: "
					+ cacheException.getMessage(), cacheException);
		}
		if (element == null)
			return null;

		return ((ProductSort) element.getValue());
	}

	public static synchronized void removeProductSortCache(String value) {
		productSortCache.remove(value);
	}

	public static synchronized void removeProductSortAllCache() {
		productSortCache.removeAll();
		productSortCache.clearStatistics();
		productSortCache.flush();
	}

	public static synchronized Collection<ProductSort> getAllProductSortCache() {
		Collection<String> resources;
		List<ProductSort> resclist = new ArrayList<ProductSort>();
		try {
			resources = productSortCache.getKeys();
		} catch (IllegalStateException e) {
			throw new IllegalStateException(e.getMessage(), e);
		} catch (CacheException e) {
			throw new UnsupportedOperationException(e.getMessage(), e);
		}
		for (Iterator<String> localIterator = resources.iterator(); localIterator.hasNext();) {
			String value = localIterator.next();
			ProductSort productSort = getProductSortCache(value);
			resclist.add(productSort);
		}
		return resclist;
	}	
}