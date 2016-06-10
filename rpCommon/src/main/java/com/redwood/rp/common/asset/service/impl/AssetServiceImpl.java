package com.redwood.rp.common.asset.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redwood.rp.caching.service.CachingService;
import com.redwood.rp.core.vo.generic.ACPRequest;
import com.redwood.rp.core.vo.generic.ACPResponse;
import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.genericquery.constant.QueryType;
import com.redwood.rp.genericquery.vo.generic.GenericQueryRequest;
import com.redwood.rp.security.service.OauthService;
import com.redwood.rp.security.vo.UserRequestVO;
import com.redwood.rp.common.asset.service.AssetService;
import com.redwood.rp.common.auth.service.PropertyAuthService;
import com.redwood.rp.common.exception.PropertyInfoNotFoundException;
import com.redwood.rp.common.generic.service.GenericService;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.constant.RequestType;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.util.PropertyUtil;
import com.redwood.rp.property.constant.PropertyServiceConstant;
import com.redwood.rp.property.constant.ServiceNameMapping;
import com.redwood.rp.solr.bo.PropertyInfoBO;
import com.redwood.rp.solr.service.external.ExternalSolrSearch;

@Named
public class AssetServiceImpl implements AssetService {

	private static Logger logger = LoggerFactory.getLogger(AssetServiceImpl.class.getName());

	@Inject
	private CachingService cachingService;

	@Inject
	private GenericService genericService;

	@Inject
	private PropertyAuthService propertyAuthService;

	@Inject
	private ExternalSolrSearch externalSolrSearch;

	/*
	 * (non-Javadoc)
	 * @see com.redwood.rp.solr.service.external.ExternalSolrSearch#getPropertyInformation(java.lang.String, java.lang.String[])
	 */
	@Override
	public PropertyInfoBO getPropertyInformation(String globalPropertyID, String... additionalInformation) throws ServiceException, PropertyInfoNotFoundException {
		PropertyInfoBO propertyAssetInfoBO = null;
		List<String> parameters = null;
		String postCode = null;
		String userID = null;
		if (additionalInformation != null) {
			parameters = Arrays.asList(additionalInformation);
		}
		if (parameters != null && !parameters.isEmpty()) {
			postCode = parameters.get(0);
		}
		if (StringUtils.isNotBlank(postCode)) {
			UserRequestVO userRequest = OauthService.getUserRequest();
			if (userRequest != null && !userRequest.isAnonymousToken() &&  StringUtils.isNotBlank(userRequest.getUserId())) {
				userID = userRequest.getUserId();
				if (propertyAuthService.allowAccessToSoldProperty(postCode, userID)) {
					logger.info("post code " + postCode + " associated with global property id " + globalPropertyID + " and user id " + userID + " is being accessed ");
					// retrieve from the DB since SOLR collection will not have it.
					propertyAssetInfoBO = getDetailsForProperty(globalPropertyID);
				} else {
					String exceptionMsg = "post code " + postCode + " for property " + globalPropertyID + " is not registered for user " + userID;
					logger.error(exceptionMsg);
					throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, new ErrorVO(null, exceptionMsg, null));
				}
			} else {
				String exceptionMsg = "post code " + postCode + " has no user token provided in the request to fetch details for property " + globalPropertyID;
				logger.error(exceptionMsg);
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, new ErrorVO(null, exceptionMsg, null));
			}
		} else {
			logger.info("retrieving details for property " + globalPropertyID + " from SOLR");
			propertyAssetInfoBO = externalSolrSearch.getGlobalPropertyDetails(globalPropertyID);
		}
		return propertyAssetInfoBO;
	}
	@Override
	public PropertyInfoBO getPropertyInformation(String globalPropertyID,String eventNumber, String... additionalInformation) throws ServiceException, PropertyInfoNotFoundException {
		PropertyInfoBO propertyAssetInfoBO = null;
		List<String> parameters = null;
		String postCode = null;
		String userID = null;
		if (additionalInformation != null) {
			parameters = Arrays.asList(additionalInformation);
		}
		if (parameters != null && !parameters.isEmpty()) {
			postCode = parameters.get(0);
		}
		if (StringUtils.isNotBlank(postCode)) {
			UserRequestVO userRequest = OauthService.getUserRequest();
			if (userRequest != null && !userRequest.isAnonymousToken() &&  StringUtils.isNotBlank(userRequest.getUserId())) {
				userID = userRequest.getUserId();
				if (propertyAuthService.allowAccessToSoldProperty(postCode, userID)) {
					logger.info("post code " + postCode + " associated with global property id " + globalPropertyID + " and user id " + userID + " is being accessed ");
					propertyAssetInfoBO = externalSolrSearch.getGlobalPropertyDetails(globalPropertyID,eventNumber);
				} else {
					String exceptionMsg = "post code " + postCode + " for property " + globalPropertyID + " is not registered for user " + userID;
					logger.error(exceptionMsg);
					throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, new ErrorVO(null, exceptionMsg, null));
				}
			} else {
				String exceptionMsg = "post code " + postCode + " has no user token provided in the request to fetch details for property " + globalPropertyID;
				logger.error(exceptionMsg);
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, new ErrorVO(null, exceptionMsg, null));
			}
		} else {
			logger.info("retrieving details for property " + globalPropertyID + " from SOLR");
			propertyAssetInfoBO = externalSolrSearch.getGlobalPropertyDetails(globalPropertyID,eventNumber);
		}
		return propertyAssetInfoBO;
	}


	/**
	 * Method to return details of a property without any filters on the statuses.
	 * @param globalPropertyID
	 * @return {@link PropertyInfoBO}
	 * @throws ServiceException
	 */
	public PropertyInfoBO getDetailsForProperty(String globalPropertyID) throws ServiceException {
		PropertyInfoBO property = null;
		try {
			property = (PropertyInfoBO) cachingService.getMemcacheObject("solr_unavailable_" + globalPropertyID);
		} catch (Exception exception) {
			logger.error(" an error occurred when fetching property details for " + globalPropertyID + " from cache for key " + "solr_unavailable_" + globalPropertyID, exception);
		}

		logger.info("retrieving details for property " + globalPropertyID + " from DB");
		// setting parameters to query
		Map<String, Object> parametersMap = new HashMap<String, Object>();
		parametersMap.put("GLOBAL_PROPERTY_ID", globalPropertyID);
		GenericQueryRequest genericQueryRequest = new GenericQueryRequest();
		genericQueryRequest.setQueryName(PropertyServiceConstant.GET_PROPERTY_INFORMATION);
		genericQueryRequest.setQueryType(QueryType.SELECT);
		genericQueryRequest.setSP(false);
		genericQueryRequest.setParameterMap(parametersMap);

		// create service and invoke generic service call.
		ACPRequest acpRequest = new ACPRequest(ServiceNameMapping.GET_GENERIC_SERVICE, RequestType.JSON, genericQueryRequest);
		ACPResponse acpResponse = genericService.getGenericService(acpRequest);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>>  propertiesInformation = (List<Map<String, Object>>) acpResponse.getPayload();
		if (propertiesInformation != null && !propertiesInformation.isEmpty()) {
			Map<String, Object> propertyInformation = propertiesInformation.get(0);
			property = new PropertyInfoBO();
			property.setAuctionID((String)propertyInformation.get("auctionID"));
			property.setAuctionType((String)propertyInformation.get("auctionType"));
			property.setAuctionNumber((String)propertyInformation.get("auctionNumber"));
			property.setProductType((String)propertyInformation.get("product_type"));
			property.setPropertyType((String)propertyInformation.get("propertyType"));
			property.setPropertyId((String)propertyInformation.get("propertyId"));
			property.setPropertyState((String)propertyInformation.get("propertyState"));
			property.setPropertyOccupancyStatus((String)propertyInformation.get("propertyOccupancyStatus"));
			property.setPoolNumber((String)propertyInformation.get("poolNumber"));
			property.setVenueType((String)propertyInformation.get("venueType"));				
			property.setGlobalPropertyId((String)propertyInformation.get("globalPropId").toString());
			property.setTrusteeSale((String)propertyInformation.get("trusteeSale"));
			property.setVenueId((String)propertyInformation.get("venueId"));

		}

		if (property != null) {
			try {
				cachingService.setMemcacheObject("solr_unavailable_" + globalPropertyID, Integer.parseInt(PropertyUtil.get("acpcommon.solr.globalpropertyid.cache.timeout")), property);
			} catch (Exception exception) {
				logger.error(" an error occurred when fetching property details for " + globalPropertyID + " from cache for key " + "solr_unavailable_" + globalPropertyID, exception);
			}
		} else {
			String exceptionMsg = "no property information available in DB for property " + globalPropertyID;
			logger.error(exceptionMsg);
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, new ErrorVO(null, exceptionMsg, null));
		}
		return property;
	}

}
