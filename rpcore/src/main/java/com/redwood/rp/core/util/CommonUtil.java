/**
 * CommonUtil.java ACPCore
 * 
 * Copyright (c) 2014, Auction.com All rights reserved.
 */

package com.redwood.rp.core.util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redwood.rp.core.constant.RestConst;
import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.exception.ValidationException;
import com.redwood.rp.security.service.OauthService;

/**
 * @author kjain
 */
@Named
@SuppressWarnings("unchecked")
public class CommonUtil {

	private static final String FILENAME = CommonUtil.class.getName();
	private static Logger logger = LoggerFactory.getLogger(FILENAME);

	/**
	 * This method is used to convert the queryParam to Java Object
	 * 
	 * @param calendarRequestVO
	 * @return
	 * @throws DaoException
	 * @throws ServiceException
	 * @throws ValidationException
	 * @throws IOException
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public <T> T retrieveRequestObjectFromQueryParam(HttpServletRequest httpServletRequest, Class<T> responseType) throws ServiceException, ValidationException,
			JsonParseException, JsonMappingException, JsonGenerationException, IOException {
		Object returnObject = null;
		try {
			JSONObject jsonRequestObject = requestParamsToJSON(httpServletRequest);
			ObjectMapper jacksonObjectMapper = new ObjectMapper();
			jacksonObjectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
			jacksonObjectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			returnObject = jacksonObjectMapper.readValue(jsonRequestObject.toString(), responseType);
		} catch (Exception exception) {
			String errorDescription = "error converting query param to Object";
			logger.error(errorDescription, exception);
		}
		return (T) returnObject;
	}

	/**
	 * This method is used to convert the request parameters in to JSON Object
	 * 
	 * @param request
	 * @return
	 * @throws JSONException
	 */
	private JSONObject requestParamsToJSON(ServletRequest request) throws JSONException {
		JSONObject jsonRequestObject = new JSONObject();
		Map<String, String[]> params = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			String valueArray[] = entry.getValue();
			Object object = (valueArray.length == 1) ? valueArray[0] : valueArray;
			jsonRequestObject.put(entry.getKey(), object);
		}
		return jsonRequestObject;
	}

	/**
	 * Generate the cache key from input parameters
	 * 
	 * @param httpServletRequest
	 * @return
	 * @throws DaoException
	 * @throws ServiceException
	 * @throws ValidationException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 * @throws IOException
	 */
	public String retrieveRequestStringCacheKey(HttpServletRequest httpServletRequest) throws ServiceException, ValidationException, JsonParseException, JsonMappingException,
			JsonGenerationException, IOException {
		JSONObject jsonRequestObject = null;
		StringBuilder params = null;
		try {
			Enumeration<String> attributeNames = httpServletRequest.getParameterNames();
			params = new StringBuilder();
			jsonRequestObject = new JSONObject(httpServletRequest.getParameterMap());
			while (attributeNames.hasMoreElements()) {
				String paramName = (String) attributeNames.nextElement();
				String paramValue = httpServletRequest.getParameter(paramName);
				params.append(paramValue);
			}
			logger.debug("incoming request : " + jsonRequestObject.toString());
		} catch (Exception exception) {
			String errorDescription = "error retrieving cacheKey";
			logger.error(errorDescription, exception);
		}
		return params.toString();
	}

	/**
	 * @param requestObject
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 * @throws IOException
	 */
	public String retrieveRequestStringAsString(Object requestObject) {
		String requestObjectAsString = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			requestObjectAsString = objectMapper.writeValueAsString(requestObject);

		} catch (Exception exception) {
			String errorDescription = "error retrieving object as string";
			logger.error(errorDescription, exception);
		}
		return requestObjectAsString;
	}

	/**
	 * This method is used to generate the headers for the service call
	 * 
	 * @param contentType
	 * @param acceptType
	 * @param token
	 * @return
	 */
	public Map<String, String> retrieveAuthHeaders(String contentType, String acceptType, String token) {
		Map<String, String> authHeaders = new HashMap<String, String>();
		String contenttype = contentType;
		String accepttype = acceptType;
		String authorizationToken = token;

		// if the send values are null. default it to json and passed token
		if (contentType == null) {
			contenttype = MediaType.APPLICATION_JSON;
		}
		if (acceptType == null) {
			accepttype = MediaType.APPLICATION_JSON;
		}
		if (token == null) {
			authorizationToken = OauthService.getUserRequest().getTokenValue();
		}
		authHeaders.put(RestConst.REQ_PARAM_CONTENT_TYPE, contenttype);
		authHeaders.put(RestConst.REQ_PARAM_ACCEPT, accepttype);
		authHeaders.put(RestConst.REQ_PARAM_AUTHORIZATION, "Bearer " + authorizationToken);
		return authHeaders;
	}
}