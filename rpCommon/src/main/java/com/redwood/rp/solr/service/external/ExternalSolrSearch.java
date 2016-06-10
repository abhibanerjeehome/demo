package com.redwood.rp.solr.service.external;

import java.util.List;

import com.redwood.rp.common.exception.PropertyInfoNotFoundException;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.solr.bo.FeaturedPropertiesBO;
import com.redwood.rp.solr.bo.PropertyInfoBO;

public interface ExternalSolrSearch {

	public PropertyInfoBO getGlobalPropertyDetails(String globalPropId) throws PropertyInfoNotFoundException;
	public PropertyInfoBO getGlobalPropertyDetailsForSoldAssets(String globalPropId) throws PropertyInfoNotFoundException;
	public PropertyInfoBO getGlobalPropertyDetails(String globalPropId,String auctionNumber) throws PropertyInfoNotFoundException;
	public PropertyInfoBO getGlobalPropertyDetailsForSoldAssets(String globalPropId,String auctionNumber) throws PropertyInfoNotFoundException;
	public List<FeaturedPropertiesBO> getFeaturePropertiesWithGeoLocations(String venueIDs, String propertyCity, String propertyState) throws PropertyInfoNotFoundException;
	public List<FeaturedPropertiesBO> getFeaturePropertiesWithPropertyIDs() throws PropertyInfoNotFoundException;
	public List<FeaturedPropertiesBO> getFeaturePropertiesAll(String venueIDs) throws PropertyInfoNotFoundException;

	/**
	 * Method to fetch property details based on other parameters
	 * @param collection
	 * @param mainQuery
	 * @param filterQuery
	 * @param rows
	 * @return List<{@link PropertyInfoBO}>
	 * @throws ServiceException
	 */
	// generic SOLR call
	public List<PropertyInfoBO> getProperties(String collection, String mainQuery, String filterQuery, Integer rows) throws ServiceException;

}
