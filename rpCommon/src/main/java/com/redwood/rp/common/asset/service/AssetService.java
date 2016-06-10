package com.redwood.rp.common.asset.service;

import com.redwood.rp.common.exception.PropertyInfoNotFoundException;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.solr.bo.PropertyInfoBO;

public interface AssetService {

	/**
	 * Method to fetch information of a property along with optional arguments.
	 * @param globalPropertyID
	 * @param additionalInformation
	 * @return {@link PropertyInfoBO}
	 * @throws ServiceException
	 * @throws PropertyInfoNotFoundException 
	 */
	public PropertyInfoBO getPropertyInformation(String globalPropertyID, String... additionalInformation) throws ServiceException, PropertyInfoNotFoundException;

	/**
	 * Method to fetch information of a property along with optional arguments.
	 * @param globalPropertyID
	 * @param eventNumber
	 * @param additionalInformation
	 * @return {@link PropertyInfoBO}
	 * @throws ServiceException
	 * @throws PropertyInfoNotFoundException 
	 */
	public PropertyInfoBO getPropertyInformation(String globalPropertyID,String eventNumber, String... additionalInformation) throws ServiceException, PropertyInfoNotFoundException;
	/* Method to get the property details from the DB
	 * @param globalPropertyID
	 * @return {@link PropertyInfoBO}
	 * @throws ServiceException
	 */
	public PropertyInfoBO getDetailsForProperty(String globalPropertyID) throws ServiceException;

}
