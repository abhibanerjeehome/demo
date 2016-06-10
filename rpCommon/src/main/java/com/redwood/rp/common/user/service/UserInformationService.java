package com.redwood.rp.common.user.service;

import java.util.List;

import com.redwood.rp.common.user.bo.FavPropertiesBOList;
import com.redwood.rp.core.exception.ServiceException;
/**
 * This service fethches information relates user and user profile information
 * @author asengamalai
 *
 */
public interface UserInformationService {	
	/**
	 * Gets a list of saved properties for the user, the result can be limited by offset and limit
	 * @param userId
	 * @param offset
	 * @param limit
	 * @return
	 * @throws ServiceException
	 */
	public FavPropertiesBOList getSavedProperties(String userId, int offset, int limit)throws ServiceException;
	/**
	 * Gets all the saved properties for a given user 
	 * @param userId
	 * @return 
	 * @throws ServiceException
	 */
	public FavPropertiesBOList getAllSavedProperties(String userId)throws ServiceException;
	
	/**
	 * This function takes userid and a list of global proprety ids and checks against
	 * users saved property list and filters the input property ids and returns them
	 * @param globalPropertyIds
	 * @return  list of globalpropertyIds filtered against savedproperty list
	 * @throws ServiceException
	 */
	public List<String> checkForSavedPropeties(String userId,List<String> globalPropertyIds)throws ServiceException;
	/**
	 * checks whether the input globalproperty id is present in the users list of savedpropertyid
	 * @param userId
	 * @param globalPropertyIds
	 * @return
	 * @throws ServiceException
	 */
	public boolean isPropertyInSavedList(String userId,String globalPropertyIds)throws ServiceException;
	
	/**
	 * This function will be used by downstream api's to reload the savedcachedproperty.  This will be
	 * helpful during add/delete of saved properties for a user
	 * @param userId
	 * @throws ServiceException
	 */
	public void reloadCachedPropertiesForUser(String userId)throws ServiceException;
}
