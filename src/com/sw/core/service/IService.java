package com.sw.core.service;

import javax.servlet.http.HttpServletRequest;

import com.sw.core.data.entity.BaseEntity;


/**
 * Service接口 - Service接口基类
 */

public interface IService<T> {
	public abstract String upload(BaseEntity baseEntity,HttpServletRequest request);
}
