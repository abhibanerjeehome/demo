package com.redwood.rp.caching.service.impl;

import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redwood.rp.caching.service.CachingService;
import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.vo.json.ErrorVO;

public class CachingServiceImpl implements CachingService {

	private static Logger logger = LoggerFactory.getLogger(CachingServiceImpl.class);
	
	private MemcachedClient memcachedClient;

	public Object getMemcacheObject(String key) throws ServiceException {
		ErrorVO errorVO = null;
		if ( key != null && key.length() > 0 ) {
			try {
				return memcachedClient.get(key);
			} catch (TimeoutException exception) {
				errorVO = new ErrorVO(ErrorDescription.ERR_CD_MEMCACHE, "Timeout occurred when setting object into cache with key " + key, exception.getMessage());				
				exception.printStackTrace();
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
			} catch (InterruptedException exception) {
				errorVO = new ErrorVO(ErrorDescription.ERR_CD_MEMCACHE, "Interrupt exception occurred when setting object into cache with key " + key, exception.getMessage());				
				exception.printStackTrace();
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
			} catch (MemcachedException exception) {
				errorVO = new ErrorVO(ErrorDescription.ERR_CD_MEMCACHE, "Memcached exception occurred when setting object into cache with key " + key, exception.getMessage());
				exception.printStackTrace();
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
			}
		} else {
			return null;
		}
	}


	public void setMemcacheObject(String key, int timeout, Object obj) throws ServiceException {
		ErrorVO errorVO = null;
		if ( key != null && key.length() > 0  && obj != null ) {
			try {
				memcachedClient.set(key, timeout, obj);
			} catch (TimeoutException exception) {
				errorVO = new ErrorVO(ErrorDescription.ERR_CD_MEMCACHE, "Timeout occurred when setting object into cache with key " + key, exception.getMessage());		
				exception.printStackTrace();
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
			} catch (InterruptedException exception) {
				errorVO = new ErrorVO(ErrorDescription.ERR_CD_MEMCACHE, "Interrupt exception occurred when setting object into cache with key " + key, exception.getMessage());		
				exception.printStackTrace();
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
			} catch (MemcachedException exception) {
				errorVO = new ErrorVO(ErrorDescription.ERR_CD_MEMCACHE, "Memcached exception occurred when setting object into cache with key " + key, exception.getMessage());		
				exception.printStackTrace();
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.redwood.rp.caching.service.CachingService#addMemcacheObject(java.lang.String, int, java.lang.Object)
	 */
	@Override
	public boolean addMemcacheObject(String key, int timeout, Object obj) throws ServiceException {
		ErrorVO errorVO = null;
		boolean status  =false;
		if ( key != null && key.length() > 0  && obj != null ) {
			try {
				status = memcachedClient.add(key, timeout, obj);
			} catch (TimeoutException exception) {
				String message = "Timeout occurred when adding object into cache with key " + key;
				logger.error(message+" " +exception);
				errorVO = new ErrorVO(ErrorDescription.ERR_CD_MEMCACHE, message, exception.getMessage());		
				exception.printStackTrace();
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, null);
			} catch (InterruptedException exception) {
				String message = "Interrupt exception occurred when adding object into cache with key " + key;
				exception.printStackTrace();
				logger.error(message+" " +exception);				
				errorVO = new ErrorVO(ErrorDescription.ERR_CD_MEMCACHE, message, exception.getMessage());				
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
			} catch (MemcachedException exception) {
				String message = "Memcached exception occurred when adding object into cache with key " + key;
				exception.printStackTrace();
				logger.error(message+" " +exception);
				errorVO = new ErrorVO(ErrorDescription.ERR_CD_MEMCACHE, message, exception.getMessage());				
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
			}
		}
		return status;
	}	

	public boolean deletetMemacacheObject(String key) throws ServiceException {
		ErrorVO errorVO = null;
		if ( key != null && key.length() > 0 ) {
			try {
				memcachedClient.delete(key);
			} catch (TimeoutException exception) {
				errorVO = new ErrorVO(ErrorDescription.ERR_CD_MEMCACHE, "Timeout occurred when setting object into cache with key " + key, exception.getMessage());				
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
			} catch (InterruptedException exception) {
				errorVO = new ErrorVO(ErrorDescription.ERR_CD_MEMCACHE, "Interrupt exception occurred when setting object into cache with key " + key, exception.getMessage());				
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
			} catch (MemcachedException exception) {
				errorVO = new ErrorVO(ErrorDescription.ERR_CD_MEMCACHE, "Memcached exception occurred when setting object into cache with key " + key, exception.getMessage());				
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
			}
			return true;
		} else {
			return false;
		}
	}

	// Spring wire --- Setters and getters
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}
	public MemcachedClient getMemcachedClient() {
		return this.memcachedClient;
	}

}
