package com.redwood.rp.caching.service;

import com.redwood.rp.core.exception.ServiceException;

public interface CachingService {

	public Object getMemcacheObject(String key) throws ServiceException;

	public void setMemcacheObject(String key, int timeout, Object ob) throws ServiceException;

	public boolean deletetMemacacheObject(String key) throws ServiceException;

	/**
	 * 	 * Add key-value item to memcached, success only when the key is not exists
	 * @param key
	 * @param timeout
	 * @param obj
	 * @return
	 * @throws ServiceException
	 */
	public boolean addMemcacheObject(String key, int timeout, Object obj) throws ServiceException;
}
