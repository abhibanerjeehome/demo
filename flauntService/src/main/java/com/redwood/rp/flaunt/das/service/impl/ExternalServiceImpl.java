/**
 * ExternalServiceCall.java
 * UserService
 * 
 * Copyright (c) 2013, Auction.com
 * All rights reserved.
 */
package com.redwood.rp.flaunt.das.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.redwood.rp.rest.RestEasyService;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.util.MessageFormattor;
import com.redwood.rp.flaunt.constant.UserServiceConstant;
import com.redwood.rp.flaunt.das.service.ExternalService;
import com.redwood.rp.flaunt.vo.json.response.TokenResponse;

@Named
public class ExternalServiceImpl implements ExternalService {
	//  =================================================
	//	 		Class Variables
	//	=================================================	
	private static final String FILENAME = ExternalServiceImpl.class.getName();
	private static Logger logger = LoggerFactory.getLogger(ExternalServiceImpl.class.getName());

	@Inject
	RestEasyService restEasy;

	@Inject
	ObjectMapper jacksonObjectMapper;

	//@Value("${user.service.property.data.external.url}")
	private String propertyServiceUrl;		

	//@Value("${acpPlatform.propertyService.socketTimeout}")
	private Integer socketTimeout;

	////@Value("${acpPlatform.propertyService.connectionTimeoutMillis}")
	private Integer connectionTimeoutMillis;

	//@Value("${authorization.token.expiry.interval}")
	private Long tokenExpiryInterval;

	//@Value("${oauth2.authorization.server}")
	private String securityServiceUrl;		

	//@Value("${security.oauth.openflag}")
	private Boolean shouldAuthenticate=false;



	/**
	 * The token map keeps track of whether a token exists or not.  The first time
	 * any token is created , it will be checked against this map and created
	 * if not already available.
	 */
	private Map<String, String> tokenForAuthorization = new LinkedHashMap<String, String>();	


	/**
	 * Method to getting token for authorization.
	 * @return String
	 * @throws ServiceException 
	 */
	public String getTokenForAuthorization(String username, String password) throws ServiceException {
		String token = null;
		logger.info(MessageFormattor.infoFormat(FILENAME, "getTokenForAuthorization","Invoking service for retrieving security token for invoking external service"));
		String tokenGeneratedTimestamp = tokenForAuthorization.get(UserServiceConstant.AUTHENTICATION_TOKEN_TIMESTAMP);
		token = tokenForAuthorization.get(UserServiceConstant.AUTHENTICATION_TOKEN);
		if (StringUtils.isBlank(token) || (StringUtils.isBlank(tokenGeneratedTimestamp) && (Calendar.getInstance().getTimeInMillis() - Long.valueOf(tokenGeneratedTimestamp)) > tokenExpiryInterval)) {
			// fetch from the security service.
			token = getToken(username, password);
			if (StringUtils.isNotBlank(token)) {
				tokenForAuthorization.put(UserServiceConstant.AUTHENTICATION_TOKEN, token);
				tokenForAuthorization.put(UserServiceConstant.AUTHENTICATION_TOKEN_TIMESTAMP, String.valueOf(Calendar.getInstance().getTimeInMillis()));
			}
		}
		return token;
	}


	/**
	 * Method to get token from the security service
	 * @return String
	 * @throws ServiceException 
	 */
	@SuppressWarnings("unchecked")
	private String getToken(String username, String password) throws ServiceException {
		String token = null;
		Map<String,String> authHeaders = new HashMap<String,String>();
		authHeaders.put(UserServiceConstant.CONTENT_TYPE, MediaType.APPLICATION_JSON);
		authHeaders.put(UserServiceConstant.ACCEPT, MediaType.APPLICATION_JSON);
		try{
			String serviceUrl = securityServiceUrl + "/oauth/token?grant_type=client_credentials&client_id=auctionEngine&client_secret=secret";
			
			if (!StringUtils.isEmpty(username) && (!StringUtils.isEmpty(password))) {
				serviceUrl = serviceUrl + "&username="+username+"&password="+password;
			} 
			
			logger.info(MessageFormattor.infoFormat(FILENAME, "getToken","Invoking service for retrieving security token for invoking service from userService"+serviceUrl));
			Response securityServiceObject = (Response) restEasy.get(serviceUrl, null,Object.class,authHeaders,socketTimeout, connectionTimeoutMillis);

			if(null != securityServiceObject && securityServiceObject.getStatus() == 200 ){
				logger.info(MessageFormattor.infoFormat(FILENAME, "getToken","Response recieved from Security Service"));
				LinkedHashMap<String, Object> resultHashMap = (LinkedHashMap<String, Object>) securityServiceObject.getEntity();				
				TokenResponse tokenResponse = new TokenResponse();

				BeanUtils.copyProperties(tokenResponse, resultHashMap);
				if (tokenResponse != null) {
					token = "Bearer" + " " + tokenResponse.getAccess_token();
				}
				logger.info(MessageFormattor.infoFormat(FILENAME, "getToken","Token Receivd "+token));
			}
		}catch(Exception e){			
			//logger.info(MessageFormattor.infoFormat(FILENAME, "getToken","Exception in receiving Token getToken "));
			e.printStackTrace();
		}
		return token;
	}	

}
