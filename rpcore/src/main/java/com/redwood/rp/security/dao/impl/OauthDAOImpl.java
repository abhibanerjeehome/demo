package com.redwood.rp.security.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;

import com.redwood.rp.core.base.das.jdbc.impl.AbstractJdbcDaoImpl;
import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.constant.RestConst;
import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.core.util.MessageFormattor;
import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.security.dao.OauthDAO;
import com.redwood.rp.security.vo.OauthClientServiceVO;

/**
 * OAUTH Security related database operations.
 *
 */
public class OauthDAOImpl  extends AbstractJdbcDaoImpl implements OauthDAO{
	
	private static Logger logger = LoggerFactory.getLogger(OauthDAOImpl.class.getName());
	
	 
	/* (non-Javadoc)
	 * @see com.redwood.rp.security.dao.OauthDAO#getClientServiceByName(java.lang.String)
	 */
	public List<OauthClientServiceVO> getClientServiceByName(String serviceName) throws DaoException{
		String queryStr = "SELECT * FROM oauth_clientservice WHERE service_name=:SERVICE_NAME";
		
		List<Map<String, Object>> resultMapList = null;
		try {
			Map<String,Object> valueMap = new HashMap<String,Object>();
			valueMap.put("SERVICE_NAME", serviceName);
			resultMapList = executeJdbcQuery(queryStr,valueMap);
		} catch (DaoException e) {
			throw new DaoException(ExceptionType.EXCEPTION_SERVICE,e.getErrorVO()); 
		}
		if(resultMapList==null || resultMapList.size()==0){
			return null;
		} else{
			List<OauthClientServiceVO> clientServiceList = new ArrayList<OauthClientServiceVO>();
			
			for(Map<String, Object> resultMap : resultMapList ){
				clientServiceList.add(createOauthClientServiceVO(resultMap));
			}
			return clientServiceList;
		}
	}
	
	public List<OauthClientServiceVO> getClientServiceByClientIdAndServiceName(String oauthId, String serviceName) throws DaoException {
		String queryStr = "select ocService.* from oauth_client oClient "
								+ "join oauth_client_role ocRole on oClient.oauth_id=ocRole.oauth_id "
								+ "join oauth_role oRole on ocRole.role_id=oRole.role_id "
								+ "join oauth_role_service orService on oRole.role_id = orService.role_id "
								+ "join oauth_clientservice ocService on orService.service_id=ocService.clientservice_id "
								+ "where oRole.active_flag=1 and oClient.oauth_id=:oauthId and service_name=:serviceName group by clientservice_id order by clientservice_id";

		List<Map<String, Object>> resultMapList = null;
		try {
			Map<String, Object> valueMap = new HashMap<String, Object>();
			valueMap.put("oauthId", oauthId);
			valueMap.put("serviceName", serviceName);
			resultMapList = executeJdbcQuery(queryStr, valueMap);
		} catch (DaoException e) {
			throw new DaoException(ExceptionType.EXCEPTION_SERVICE,
					e.getErrorVO());
		}
		if (resultMapList == null || resultMapList.size() == 0) {
			return null;
		} else {
			List<OauthClientServiceVO> clientServiceList = new ArrayList<OauthClientServiceVO>();

			for (Map<String, Object> resultMap : resultMapList) {
				clientServiceList.add(createOauthClientServiceVO(resultMap));
			}
			return clientServiceList;
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.redwood.rp.security.dao.OauthDAO#getAllService()
	 */
	public List<OauthClientServiceVO> getAllClientService() throws DaoException{
		String queryStr = "SELECT * FROM oauth_clientservice ORDER BY service_name";
		
		List<Map<String, Object>> resultMapList = null;
		try {
			resultMapList = executeJdbcQuery(queryStr,null);
		} catch (DaoException e) {
			throw new DaoException(ExceptionType.EXCEPTION_SERVICE,e.getErrorVO()); 
		}
		if(resultMapList==null || resultMapList.size()==0){
			return null;
		} else{
			List<OauthClientServiceVO> clientServiceList = new ArrayList<OauthClientServiceVO>();
			
			for(Map<String, Object> resultMap : resultMapList ){
				clientServiceList.add(createOauthClientServiceVO(resultMap));
			}
			return clientServiceList;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.redwood.rp.security.dao.OauthDAO#getClientServiceById(int)
	 */
	public OauthClientServiceVO getClientServiceById(int clientServiceId) throws DaoException{
		String queryStr = "SELECT * FROM oauth_clientservice WHERE clientservice_id=:clientservice_id";
		
		List<Map<String, Object>> resultMapList = null;
		try {
			Map<String,Object> valueMap = new HashMap<String,Object>();
			valueMap.put("clientservice_id", clientServiceId);
			resultMapList = executeJdbcQuery(queryStr,valueMap);
		} catch (DaoException e) {
			throw new DaoException(ExceptionType.EXCEPTION_SERVICE,e.getErrorVO()); 
		}
		if(resultMapList==null || resultMapList.size()==0){
			return null;
		} else{
			return createOauthClientServiceVO(resultMapList.get(0));
		}
	}
	
	/* (non-Javadoc)
	 * @see com.redwood.rp.security.dao.OauthDAO#getAllServiceNames()
	 */
	public List<String> getAllServiceNames() throws DaoException{
		String queryStr = "SELECT service_name FROM oauth_clientservice GROUP BY service_Name ORDER BY clientservice_ID";
		
		List<Map<String, Object>> resultMapList = null;
		try {
			resultMapList = executeJdbcQuery(queryStr,null);
		} catch (DaoException e) {
			throw new DaoException(ExceptionType.EXCEPTION_SERVICE,e.getErrorVO()); 
		}
		if(resultMapList==null || resultMapList.size()==0){
			return null;
		} else{
			List<String> serviceNameList = new ArrayList<String>();
			
			for(Map<String, Object> resultMap : resultMapList ){
				Object serviceNameObject = resultMap.get("service_name");
				serviceNameList.add(serviceNameObject==null?"":(String)serviceNameObject);
			}
			return serviceNameList;
		}
	}
	
	public OAuth2AccessToken getAccessToken(String accessTokenStr) throws DaoException{
		
		String queryStr = "SELECT a.token, c.oauth_id FROM appauthorize.oauth_access_token a,oauth_client c where a.client_id=c.oauth_client_id and a.token_id=:access_token and a.token_status=1";
		
		List<Map<String, Object>> resultMapList = null;
		try {
			Map<String,Object> valueMap = new HashMap<String,Object>();
			valueMap.put("access_token", accessTokenStr);
			resultMapList = executeJdbcQuery(queryStr,valueMap);
			if(resultMapList==null || resultMapList.size()==0){
				return null;
			} else{
				Map<String, Object> resultMap = resultMapList.get(0);
				Object tokenObj = resultMap.get("token");
				Object oauthIdObj = resultMap.get("oauth_id");
				
				if(tokenObj==null){
					return null;
				}
				byte[] tokenBytes = (byte[])tokenObj;
				OAuth2AccessToken accessToken = SerializationUtils.deserialize(tokenBytes);
				
				if(accessToken==null || oauthIdObj==null){
					return null;
				}
				accessToken.getAdditionalInformation().put(RestConst.TOKEN_REQUEST_OAUTH_ID, oauthIdObj.toString());
				return accessToken;
			}
		} catch (DaoException e) {
			throw new DaoException(ExceptionType.EXCEPTION_SERVICE,e.getErrorVO()); 
		} catch (Exception e) {
			String errorDescription = "Failed to get the access token from database. The error is : "+e.getMessage();
            logger.error(MessageFormattor.errorFormat(this.getClass().getName(),
                "executeNamedQuery", ExceptionType.EXCEPTION_DAO, errorDescription), e);
            throw new DaoException(ExceptionType.EXCEPTION_DAO,
					new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
		}
	}
	
	/**
	 * @param token
	 * @return
	 */
	protected OAuth2AccessToken deserializeAccessToken(byte[] token) {
		return SerializationUtils.deserialize(token);
	}
	
	/**
	 * @param resultMap
	 * @return
	 */
	private OauthClientServiceVO createOauthClientServiceVO(Map<String, Object> resultMap){
		
		OauthClientServiceVO o = new OauthClientServiceVO();
		Object clientServiceIdObject = resultMap.get("clientservice_id");
		Object versionObject = resultMap.get("version");
		Object serviceNameObject = resultMap.get("service_name");
		Object serviceURLObject = resultMap.get("service_url");
		Object tokenRequiredObject = resultMap.get("usertoken_required");
		Object urlFilterFlagObject = resultMap.get("url_filter_enable");
		Object descriptionObject = resultMap.get("service_description");
		
		o.setClientServiceId(clientServiceIdObject==null?0:((Long)clientServiceIdObject).intValue());
		o.setVersion(versionObject==null?0:(Integer)versionObject);
		o.setServiceName(serviceNameObject==null?"":(String)serviceNameObject);
		o.setServiceURL(serviceURLObject==null?"":(String)serviceURLObject);
		o.setUserTokenRequired(tokenRequiredObject==null?false:((Integer)tokenRequiredObject)==1);
		o.setUrlFilterFlag(urlFilterFlagObject==null?false:((Integer)urlFilterFlagObject)==1);
		o.setServiceDescription(descriptionObject==null?"":(String)descriptionObject);
		return o;
	}
	
}
