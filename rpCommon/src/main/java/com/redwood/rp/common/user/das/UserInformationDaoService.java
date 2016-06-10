package com.redwood.rp.common.user.das;

import java.util.Map;

import com.redwood.rp.common.user.bo.FavPropertiesBO;
import com.redwood.rp.core.exception.DaoException;
/**
 * Dao service class to fetch details related to user and user profile information
 * 
 * @author asengamalai
 *
 */
public interface UserInformationDaoService {

	/**
	 * Gets all the saved properties from DB for the given userid
	 * @param userid
	 * @return
	 */
	public Map<String,FavPropertiesBO> getAllSavedProperties(String userid) throws DaoException;
}
