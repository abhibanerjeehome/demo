/**
 * 
 */
package com.redwood.rp.common.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import com.redwood.rp.caching.service.CachingService;
import com.redwood.rp.core.annotation.Loggable;
import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.common.user.bo.FavPropertiesBO;
import com.redwood.rp.common.user.bo.FavPropertiesBOList;
import com.redwood.rp.common.user.das.UserInformationDaoService;
import com.redwood.rp.common.user.service.UserInformationService;
import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.util.PropertyUtil;

/**
 * @author asengamalai
 * 
 */
@Named
public class UserInformationServiceImpl implements UserInformationService {
	Logger logger = LoggerFactory.getLogger(UserInformationServiceImpl.class);

	@Inject
	UserInformationDaoService userDao;
	@Inject
	private CachingService cachingService;

	private static final String SAVED_PROP_CACHE_PRE_KEY = "USER_SAVED_PROPERTY_";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.redwood.rp.common.user.service.UserInformationService#getSavedProperties
	 * (java.lang.String, int, int)
	 */
	@Loggable
	@Transactional("jdbc_mlh")
	@Override
	public FavPropertiesBOList getSavedProperties(String userId, int offset,
			int limit) throws ServiceException {
		FavPropertiesBOList favProperties= new FavPropertiesBOList(new ArrayList<FavPropertiesBO>(),0);
		Map<String, FavPropertiesBO> favoritePropertiesMap = getPropertiesForUser(userId);
		if (favoritePropertiesMap != null) {
			int noOfProperties = favoritePropertiesMap.size();
			favProperties.setTotalCount(noOfProperties);
			if (offset < noOfProperties) {
				limit = offset + limit;
				// if the limit is 0 we will return all the remainig from offset
				// - a feature to getall
				limit = (limit > 0 && limit<noOfProperties) ? limit
						: (noOfProperties);
				favProperties.setFavProperties((new ArrayList<FavPropertiesBO>(
						favoritePropertiesMap.values())).subList(offset, limit));				
			}
		}
		return favProperties;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redwood.rp.common.user.service.UserInformationService#
	 * getAllSavedProperties(java.lang.String)
	 */
	@Override
	public FavPropertiesBOList getAllSavedProperties(String userId)
			throws ServiceException {
		if (userId == null || userId.isEmpty()) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,
					new ErrorVO(ErrorDescription.ERR_CD_SERVICE_VALIDATION,
							ErrorDescription.ERR_MSG_SERVICE_VALIDATION,
							"User Id cannot be null for getAllSavedProperties"));
		}

		return getSavedProperties(userId, 0, 0);
	}

	/*
	 * we have marked this function async so that users of this function, do'nt
	 * need to wait (this object has to be injected thought spring for this
	 * functionality to work) (non-Javadoc)
	 * 
	 * @see com.redwood.rp.common.user.service.UserInformationService#
	 * reloadCachedPropertiesForUser(java.lang.String)
	 */
	@Override
	@Async
	public void reloadCachedPropertiesForUser(String userId)
			throws ServiceException {
		logger.debug("Reloading saved property cache for user" + userId);
		if (userId == null || userId.isEmpty()) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,
					new ErrorVO(ErrorDescription.ERR_CD_SERVICE_VALIDATION,
							ErrorDescription.ERR_MSG_SERVICE_VALIDATION,
							"User Id cannot be null for getAllSavedProperties"));
		}
		try {
			cachingService.deletetMemacacheObject(SAVED_PROP_CACHE_PRE_KEY
					+ userId);
		} catch (Exception e) {
			logger.error("Error with evicting user property from Memcache", e);
		}
/*		TODO:  we want to reload but for now we will remove from cache
 		
 		Map<String, FavPropertiesBO> favoritePropertiesMap = null;
		try {
			favoritePropertiesMap = userDao.getAllSavedProperties(userId);
		} catch (DaoException e) {
			logger.error(
					"Error trying to load async saved properties for user "
							+ userId, e);
		}
		if (favoritePropertiesMap != null && !favoritePropertiesMap.isEmpty()) {
			cachingService
					.setMemcacheObject(
							SAVED_PROP_CACHE_PRE_KEY + userId,
							Integer.parseInt(PropertyUtil
									.get("acpcommon.user.savedproperty.cache.timeout")),
							favoritePropertiesMap);
		}*/
	}

	@Override
	public List<String> checkForSavedPropeties(String userId,
			List<String> globalPropertyIds) throws ServiceException {
		if (globalPropertyIds == null || globalPropertyIds.isEmpty()) {
			throw new ServiceException(
					ExceptionType.EXCEPTION_SERVICE,
					new ErrorVO(ErrorDescription.ERR_CD_SERVICE_VALIDATION,
							ErrorDescription.ERR_MSG_SERVICE_VALIDATION,
							"globalPropertyId list cannot be null for getAllSavedProperties"));
		}
		Map<String, FavPropertiesBO> favoritePropertiesMap = getPropertiesForUser(userId);
		List<String> filteredList = new ArrayList<String>();
		if (favoritePropertiesMap != null) {
			for (String globalPropertyId : globalPropertyIds) {
				if (favoritePropertiesMap.containsKey(globalPropertyId)) {
					filteredList.add(globalPropertyId);
				}

			}
		}
		return filteredList;
	}

	@Override
	public boolean isPropertyInSavedList(String userId, String globalPropertyIds)
			throws ServiceException {
		if (userId == null || userId.isEmpty()) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,
					new ErrorVO(ErrorDescription.ERR_CD_SERVICE_VALIDATION,
							ErrorDescription.ERR_MSG_SERVICE_VALIDATION,
							"User Id cannot be null for getAllSavedProperties"));
		}
		Map<String, FavPropertiesBO> favoritePropertiesMap = getPropertiesForUser(userId);
		if (favoritePropertiesMap != null) {
			return favoritePropertiesMap.containsKey(globalPropertyIds);
		}
		return false;
	}

	/**
	 * this functions retrieves the list of property for the user from cache, if
	 * not found in cache from db
	 * 
	 * @param userId
	 * @return
	 */
	private Map<String, FavPropertiesBO> getPropertiesForUser(String userId)
			throws ServiceException {
		if (userId == null || userId.isEmpty()) {
			throw new ServiceException(
					ExceptionType.EXCEPTION_SERVICE,
					new ErrorVO(ErrorDescription.ERR_CD_SERVICE_VALIDATION,
							ErrorDescription.ERR_MSG_SERVICE_VALIDATION,
							"User Id cannot be null for getting saved properties"));
		}
		Map<String, FavPropertiesBO> favoritePropertiesMap = null;
		try {
			// we use try catch for all the memcache so that we dont use the
			// cache when there is an issue and goto db
			try {
				favoritePropertiesMap = (Map<String, FavPropertiesBO>) cachingService
						.getMemcacheObject(SAVED_PROP_CACHE_PRE_KEY + userId);
			} catch (Exception e) {
				logger.error("Error with getting user property Memcache", e);
			}
			if (favoritePropertiesMap == null) {
				logger.debug("Loading fav properties from db");
				favoritePropertiesMap = userDao.getAllSavedProperties(userId);
				try {
					cachingService
							.setMemcacheObject(
									SAVED_PROP_CACHE_PRE_KEY + userId,
									Integer.parseInt(PropertyUtil
											.get("acpcommon.user.savedproperty.cache.timeout")),
									favoritePropertiesMap);
				} catch (Exception e) {
					logger.error("Error with storing user property Memcache", e);
				}
			}
		} catch (DaoException e) {
			ServiceException serve = new ServiceException(e.getMessage(),
					e.getErrorVO());
			serve.setStackTrace(e.getStackTrace());
			throw serve;
		}
		return favoritePropertiesMap;
	}

}
