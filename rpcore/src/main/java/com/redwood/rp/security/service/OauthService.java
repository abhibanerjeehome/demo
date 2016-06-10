package com.redwood.rp.security.service;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.transaction.annotation.Transactional;

import com.redwood.rp.caching.service.CachingService;
import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.constant.RestConst;
import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.util.StringUtil;
import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.security.dao.OauthDAO;
import com.redwood.rp.security.vo.OauthClientServiceVO;
import com.redwood.rp.security.vo.UserRequestVO;

@Named
public class OauthService {

	private static final ThreadLocal<UserRequestVO> userRequestVO_local = new ThreadLocal<UserRequestVO>();

	@Resource
	private CachingService cachingService;

	@Resource
	private OauthDAO oauthDAO;
	
	private static Logger logger = LoggerFactory.getLogger(OauthService.class.getName());

	/**
	 * @param authorizationStr
	 * @return
	 * @throws ServiceException 
	 */
	public void validateToken(String authorizationStr) throws ServiceException { 
		OAuth2AccessToken token = null;
		if (StringUtils.isBlank(authorizationStr)) {
			String errorMSG = ErrorDescription.ERR_MSG_SECURITY_NO_TOKEN;
			ErrorVO errorVO = new ErrorVO(ErrorDescription.ERR_CD_SECURITY_NO_TOKEN, ErrorDescription.ERR_MSG_SECURITY_NO_TOKEN, errorMSG);
			throw new ServiceException(ExceptionType.EXCEPTION_AUTHENTICATION, errorVO);
		} else {
			String tokenValue = null;
			if (authorizationStr.toLowerCase().startsWith(RestConst.REQ_PARAM_AUTHORIZATION_BEARER)) {
				tokenValue = authorizationStr.substring(authorizationStr.indexOf(" ")).trim();
			} else {
				String errorMSG = ErrorDescription.ERR_MSG_SECURITY_INVALID_TOKEN;
				ErrorVO errorVO = new ErrorVO(ErrorDescription.ERR_CD_SECURITY_INVALID_TOKEN, ErrorDescription.ERR_MSG_SECURITY_INVALID_TOKEN, errorMSG);
				throw new ServiceException(ExceptionType.EXCEPTION_AUTHENTICATION, errorVO);
			}
			
			if(tokenValue==null){
				String errorMSG = ErrorDescription.ERR_MSG_SECURITY_INVALID_TOKEN;
				ErrorVO errorVO = new ErrorVO(ErrorDescription.ERR_CD_SECURITY_INVALID_TOKEN, ErrorDescription.ERR_MSG_SECURITY_INVALID_TOKEN, errorMSG);
				throw new ServiceException(ExceptionType.EXCEPTION_AUTHENTICATION, errorVO);
			}
			
			boolean isFromDBFlag = false;
			try {
				token = (OAuth2AccessToken)cachingService.getMemcacheObject(tokenValue);
			} catch (ServiceException e) {
				String errorMSG = ErrorDescription.ERR_MSG_SECURITY_INVALID_TOKEN+". Failed to get token from storage. Error detail: "+e.getErrorVO().getErrMsg()+" "+e.getErrorVO().getErrDescription();
				logger.error(errorMSG, e);
				//If the memecached has issue, we go to database to check the token.
				token = getAccessTokenFromDB(tokenValue);
				isFromDBFlag = true;
			}
			
			if (token == null) {
				//If the token is null and it is retrieved from memcached, we also go to database and double check if the token is valid. 
				//In case the memecached is not working properly for some reasons.
				if(!isFromDBFlag){
					token = getAccessTokenFromDB(tokenValue);
				}
				if (token == null){
					ErrorVO errorVO = new ErrorVO(ErrorDescription.ERR_CD_SECURITY_INVALID_TOKEN,ErrorDescription.ERR_MSG_SECURITY_INVALID_TOKEN, ErrorDescription.ERR_MSG_SECURITY_INVALID_TOKEN);
					throw new ServiceException(ExceptionType.EXCEPTION_AUTHENTICATION,errorVO);
				}else{
					try{
						cachingService.setMemcacheObject(token.getValue(), 3600, token);
					}catch(ServiceException e){
						logger.error("Failed to set token to memcached. Error: "+e.getErrorVO().getErrDescription(), e);
					}
				}
			} 
			
			if (token.isExpired()) {
				ErrorVO errorVO = new ErrorVO(ErrorDescription.ERR_CD_SECURITY_EXPIRED_TOKEN,ErrorDescription.ERR_MSG_SECURITY_EXPIRED_TOKEN, ErrorDescription.ERR_MSG_SECURITY_EXPIRED_TOKEN);
				throw new ServiceException(ExceptionType.EXCEPTION_AUTHENTICATION,errorVO);
			} else {
				UserRequestVO userRequest = new UserRequestVO(token);
				Object grantTypeObject = token.getAdditionalInformation().get(RestConst.SECURITY_NAME_GRANT_TYPE);
				Object userNameObject = token.getAdditionalInformation().get(RestConst.SECURITY_NAME_USERNAME);
				Object emailObject = token.getAdditionalInformation().get(RestConst.SECURITY_NAME_EMAIL);
				Object userIdObject = token.getAdditionalInformation().get(RestConst.SECURITY_NAME_USERID);
				Object firstNameObject = token.getAdditionalInformation().get(RestConst.SECURITY_NAME_FIRST_NAME);
				Object lastNameObject = token.getAdditionalInformation().get(RestConst.SECURITY_NAME_LAST_NAME);
				Object clientIdObject = token.getAdditionalInformation().get(RestConst.TOKEN_REQUEST_CLIENT_ID);
				Object resourceIdObject = token.getAdditionalInformation().get(RestConst.TOKEN_REQUEST_RESOURCE_ID);
				Object vipSellerAllowedObject = token.getAdditionalInformation().get(RestConst.SECURITY_NAME_VIP_SELLER_ALLOWED);
				Object dmsUserNameObject = token.getAdditionalInformation().get(RestConst.SECURITY_NAME_DMS_USER_NAME);
				Object oauthIdObject = token.getAdditionalInformation().get(RestConst.TOKEN_REQUEST_OAUTH_ID);
				
				userRequest.setGrantType(grantTypeObject==null?null:grantTypeObject.toString());
				userRequest.setUsername(userNameObject==null?null:userNameObject.toString());
				userRequest.setEmail(emailObject==null?null:emailObject.toString());
				userRequest.setUserId(userIdObject==null?null:userIdObject.toString());
				userRequest.setFirstName(firstNameObject==null?null:firstNameObject.toString());
				userRequest.setLastName(lastNameObject==null?null:lastNameObject.toString());
				userRequest.setClientId(clientIdObject==null?null:clientIdObject.toString());
				userRequest.setAuthResource(resourceIdObject==null?null:resourceIdObject.toString());
				userRequest.setVipSellerAllowed(vipSellerAllowedObject==null?null:vipSellerAllowedObject.toString());
				userRequest.setDmsUserName(dmsUserNameObject==null?"":dmsUserNameObject.toString());
				userRequest.setOauthId(oauthIdObject==null?"":oauthIdObject.toString());
				
				userRequestVO_local.set(userRequest);
			}
		}
	}

	/**
	 * @param serviceName
	 * @param org_accessUri
	 * @param isAnonymousToken
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(value="jdbc_appauthorize_ro")
	@SuppressWarnings("unchecked")
	public void authorizeService(String path, String accessUri, boolean isAnonymousToken) throws ServiceException {
		ErrorVO errorVO =  new ErrorVO(ErrorDescription.ERR_CD_SECURITY_UNKNOWN_SERVICE_ENDPOINT,ErrorDescription.ERR_MSG_SECURITY_UNKNOWN_SERVICE_ENDPOINT, 
				ErrorDescription.ERR_MSG_SECURITY_UNKNOWN_SERVICE_ENDPOINT);

		String serviceName = getServiceNameFromPath(path);
		if(StringUtils.isEmpty(serviceName)){
			throw new ServiceException(ExceptionType.EXCEPTION_AUTHENTICATION,errorVO);
		}
		
		UserRequestVO userRequest = userRequestVO_local.get();
		if(userRequest!=null){
			userRequest.setSeviceName(serviceName);
		}
		
		if(StringUtil.isBlank(accessUri)){
			throw new ServiceException(ExceptionType.EXCEPTION_AUTHENTICATION,errorVO);
		}else{
			accessUri = accessUri.trim();
		}
		
		List<OauthClientServiceVO> clientServiceList = null;
		
		Object cachedObject = null;
		String key = new StringBuffer(RestConst.PRE_SERVICE_ENDPOINT_FLITER).append(userRequest.getOauthId()).append("_").append(serviceName).toString();
		try{
			cachedObject = cachingService.getMemcacheObject(key);
		}catch(ServiceException e){
			logger.error("Failed to get service endpoint list from memcached. Error: "+e.getErrorVO().getErrDescription(), e);
		}
		
		if(cachedObject!=null){
			clientServiceList = (List<OauthClientServiceVO>)cachedObject;
		}else{
			try {
				clientServiceList = oauthDAO.getClientServiceByClientIdAndServiceName(userRequest.getOauthId(), serviceName);
			} catch (DaoException e) {
				throw new ServiceException(ExceptionType.EXCEPTION_AUTHENTICATION,errorVO);
			}
			if(clientServiceList==null || clientServiceList.size()==0){
				throw new ServiceException(ExceptionType.EXCEPTION_AUTHENTICATION,errorVO);
			}
			try{
				cachingService.setMemcacheObject(key, 7200, clientServiceList);
			}catch(ServiceException e){
				logger.error("Failed to set service endpoint list to memcached. Error: "+e.getErrorVO().getErrDescription(), e);
			}
		}
		
		for(OauthClientServiceVO o : clientServiceList){
			String serviceURL = o.getServiceURL()!=null?o.getServiceURL().trim():"";
			if(accessUri.equalsIgnoreCase(serviceURL)|| accessUri.startsWith(serviceURL+"/") ||
					accessUri.startsWith(serviceURL+"?")){
				if(!o.isUrlFilterFlag()){
					ErrorVO errorVO2 =  new ErrorVO(ErrorDescription.ERR_CD_SECURITY_DISABLED_SERVICE_ENDPOINT,ErrorDescription.ERR_MSG_SECURITY_DISABLED_SERVICE_ENDPOINT, 
							ErrorDescription.ERR_MSG_SECURITY_DISABLED_SERVICE_ENDPOINT);
					throw new ServiceException(ExceptionType.EXCEPTION_AUTHENTICATION,errorVO2);
				}else{
					if(isAnonymousToken && o.isUserTokenRequired()){
						ErrorVO errorVO2 =  new ErrorVO(ErrorDescription.ERR_CD_SECURITY_UNAUTHORIZED_TOKEN,ErrorDescription.ERR_MSG_SECURITY_UNAUTHORIZED_TOKEN, 
								ErrorDescription.ERR_MSG_SECURITY_UNAUTHORIZED_TOKEN);
						throw new ServiceException(ExceptionType.EXCEPTION_AUTHENTICATION,errorVO2);
					}else{
						return;
					}
				}
			}
		}
		throw new ServiceException(ExceptionType.EXCEPTION_AUTHENTICATION,errorVO);
	}
	
	private OAuth2AccessToken getAccessTokenFromDB(String tokenValue) throws ServiceException{
		OAuth2AccessToken token = null;
		try {
			token = oauthDAO.getAccessToken(tokenValue);
		} catch (DaoException e1) {
			String errorMSG = ErrorDescription.ERR_MSG_SECURITY_INVALID_TOKEN+". Failed to get token from database. Error detail: "+e1.getErrorVO().getErrMsg()+" "+e1.getErrorVO().getErrDescription();
			ErrorVO errorVO = new ErrorVO(ErrorDescription.ERR_CD_SECURITY_INTENAL,ErrorDescription.ERR_MSG_SECURITY_INTENAL, errorMSG);
			throw new ServiceException(ExceptionType.EXCEPTION_AUTHENTICATION,errorVO);
		}
		return token;
	}


	/**
	 * @param path
	 * @return
	 */
	private String getServiceNameFromPath(String path){
		if(StringUtils.isEmpty(path)){
			return null;
		}
		if(path.startsWith("/")){
			path = path.substring(1);
		}
		int index = path.indexOf("/");
		if(index==-1){
			return null;
		}else{
			String serviceName = path.substring(0, index);
			return serviceName;
		}
	}

	/**
	 * @return
	 */
	public static UserRequestVO getUserRequest(){
		return userRequestVO_local.get();
	}

	/**
	 * @param userRequestVO
	 */
	public static void setUserRequest(UserRequestVO userRequestVO){
		userRequestVO_local.set(userRequestVO);
	}

	public CachingService getCachingService() {
		return cachingService;
	}

	public void setCachingService(CachingService cachingService) {
		this.cachingService = cachingService;
	}

}