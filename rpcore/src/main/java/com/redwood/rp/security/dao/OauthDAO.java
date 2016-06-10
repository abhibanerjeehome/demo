package com.redwood.rp.security.dao;

import java.util.List;

import org.springframework.security.oauth2.common.OAuth2AccessToken;

import com.redwood.rp.core.base.das.jdbc.AbstractJdbcDao;
import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.security.vo.OauthClientServiceVO;

/**
 *
 */
public interface OauthDAO extends AbstractJdbcDao{
	
	/**
	 * @param serviceName
	 * @return
	 * @throws DaoException
	 */
	public List<OauthClientServiceVO> getClientServiceByName(String serviceName) throws DaoException;
	
	/**
	 * @param oauthId
	 * @param serviceName
	 * @return
	 * @throws DaoException
	 */
	public List<OauthClientServiceVO> getClientServiceByClientIdAndServiceName(String oauthId, String serviceName) throws DaoException;

	/**
	 * @param clientServiceId
	 * @return
	 * @throws DaoException
	 */
	public OauthClientServiceVO getClientServiceById(int clientServiceId) throws DaoException;
	
	/**
	 * @return
	 * @throws DaoException
	 */
	public List<OauthClientServiceVO> getAllClientService() throws DaoException;
	
	/**
	 * @return
	 * @throws DaoException
	 */
	public List<String> getAllServiceNames() throws DaoException;
	
	/**
	 * @param accessTokenStr
	 * @return
	 * @throws DaoException
	 */
	public OAuth2AccessToken getAccessToken(String accessTokenStr) throws DaoException;


}
