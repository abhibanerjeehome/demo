package com.redwood.rp.common.auth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redwood.rp.core.vo.generic.ACPRequest;
import com.redwood.rp.core.vo.generic.ACPResponse;
import com.redwood.rp.genericquery.constant.QueryType;
import com.redwood.rp.genericquery.vo.generic.GenericQueryRequest;
import com.redwood.rp.common.auth.service.PropertyAuthService;
import com.redwood.rp.common.generic.service.GenericService;
import com.redwood.rp.core.constant.RequestType;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.property.constant.PropertyServiceConstant;
import com.redwood.rp.property.constant.ServiceNameMapping;

@Named
public class PropertyAuthServiceImpl implements PropertyAuthService {

	private static Logger logger = LoggerFactory.getLogger(PropertyAuthServiceImpl.class.getName());

	@Inject
	private GenericService genericService;


	/*
	 * (non-Javadoc)
	 * @see com.redwood.rp.common.auth.service.PropertyAuthService#allowAccessToSoldProperty(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean allowAccessToSoldProperty(String postCode, String userID) throws ServiceException {
		boolean allowAccessToProperty = false;

		if (StringUtils.isNotBlank(postCode) && StringUtils.isNotBlank(userID)) {

			logger.info("checking access for property with post code " + postCode + " and user " + userID);

			// setting parameters to query
			Map<String, Object> parametersMap = new HashMap<String, Object>();
			parametersMap.put("POST_CODE", postCode);
			parametersMap.put("USER_ID", userID);
			GenericQueryRequest genericQueryRequest = new GenericQueryRequest();
			genericQueryRequest.setQueryName(PropertyServiceConstant.GET_SOLD_PROPERTY_ACCESS_DETAILS_FOR_CODE);
			genericQueryRequest.setQueryType(QueryType.SELECT);
			genericQueryRequest.setSP(false);
			genericQueryRequest.setParameterMap(parametersMap);

			// create service and invoke generic service call.
			ACPRequest acpRequest = new ACPRequest(ServiceNameMapping.GET_GENERIC_SERVICE, RequestType.JSON, genericQueryRequest);
			ACPResponse acpResponse = genericService.getGenericService(acpRequest);
			@SuppressWarnings("unchecked")
			List<Map<String, Object>>  soldPropertyDetails = (List<Map<String, Object>>) acpResponse.getPayload();

			if (soldPropertyDetails != null && !soldPropertyDetails.isEmpty()) {
				logger.info(" user " + userID + " has access to property with post code " + postCode);
				allowAccessToProperty = true;
			}

		}
		return allowAccessToProperty;
	}

}

