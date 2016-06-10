package com.redwood.rp.common.auth.service;

import com.redwood.rp.core.exception.ServiceException;

public interface PropertyAuthService {

	/**
	 * Method to check if property is accessible
	 * @param postCode
	 * @param userID
	 * @return boolean
	 * @throws ServiceException
	 */
	public boolean allowAccessToSoldProperty(String postCode, String userID) throws ServiceException;

}
