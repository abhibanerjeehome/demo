/*
 * RestEasy methods to be used. 
 */
package com.redwood.rp.rest;

import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.redwood.rp.core.exception.ServiceException;

/**
 * The Interface RestEasyService.
 * 
 * @author rpandya.cns
 */
public interface RestEasyService {

	// =================================================
	// APIs
	// =================================================

	/**
	 * Post.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param serviceEndPointUrl
	 *            the service end point url
	 * @param requestBean
	 *            the request bean
	 * @param responseType
	 *            the response type
	 * @return the t
	 * @throws ServiceException
	 *             the service exception
	 */
	public <T> T post(String serviceEndPointUrl, Object requestBean, Class<T> responseType) throws ServiceException;

	/**
	 * Post.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param serviceEndPointUrl
	 *            the service end point url
	 * @param requestBean
	 *            the request bean
	 * @param responseType
	 *            the response type
	 * @return the t
	 * @throws ServiceException
	 *             the service exception
	 */
	public <T> T post(String serviceEndPointUrl, Object requestBean, Class<T> responseType, Integer socketTimeout, Integer connectionTimeoutMillis) throws ServiceException;

	/**
	 * Post.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param serviceEndPointUrl
	 *            the service end point url
	 * @param pathVariables
	 *            the path variables
	 * @param responseType
	 *            the response type
	 * @return the t
	 * @throws ServiceException
	 *             the service exception
	 */
	public <T> T post(String serviceEndPointUrl, Object[] pathVariables, Class<T> responseType, Integer socketTimeout, Integer connectionTimeoutMillis) throws ServiceException;

	/**
	 * Post.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param serviceEndPointUrl
	 *            the service end point url
	 * @param iRequestBean
	 *            the i request bean
	 * @param pathVariables
	 *            the path variables
	 * @param responseType
	 *            the response type
	 * @return the t
	 * @throws ServiceException
	 *             the service exception
	 */
	public <T> T post(String serviceEndPointUrl, Object iRequestBean, Object[] pathVariables, Class<T> responseType, Integer socketTimeout, Integer connectionTimeoutMillis)
			throws ServiceException;

	/**
	 * Post.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param serviceEndPointUrl
	 *            the service end point url
	 * @param iRequestBean
	 *            the i request bean
	 * @param pathVariables
	 *            the path variables
	 * @param responseType
	 *            the response type
	 * @param contentType
	 *            the content type
	 * @return the t
	 * @throws ServiceException
	 *             the service exception
	 */
	public <T> T post(String serviceEndPointUrl, Object iRequestBean, Object[] pathVariables, MediaType contentType, Class<T> responseType, Integer socketTimeout,
			Integer connectionTimeoutMillis) throws ServiceException;

	/**
	 * Post.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param serviceEndPointUrl
	 *            the service end point url
	 * @param requestBean
	 *            the i request bean
	 * @param pathVariables
	 *            the path variables
	 * @param responseType
	 *            the response type
	 * @param contentType
	 *            the content type
	 * @param accept
	 *            the accept
	 * @return the t
	 * @throws ServiceException
	 *             the service exception
	 */
	public <T> T post(String serviceEndPointUrl, Object requestBean, Object[] pathVariables, MediaType contentType, MediaType accept, Class<T> responseType, Integer socketTimeout,
			Integer connectionTimeoutMillis) throws ServiceException;

	/**
	 * @param serviceEndPointUrl
	 * @param requestBean
	 * @param pathVariables
	 * @param authHeaders
	 * @param responseType
	 * @param socketTimeout
	 * @param connectionTimeoutMillis
	 * @return the t
	 * @throws ServiceException
	 */
	public <T> T post(String serviceEndPointUrl, Object requestBean, Object[] pathVariables, Map<String, String> authHeaders, Class<T> responseType, Integer socketTimeout,
			Integer connectionTimeoutMillis) throws ServiceException;
	
	/**
	 * @param serviceEndPointUrl
	 * @param requestBean
	 * @param pathVariables
	 * @param authHeaders
	 * @param responseType
	 * @param socketTimeout
	 * @param connectionTimeoutMillis
	 * @return
	 * @throws ServiceException
	 */
	public <T> T newPost(String serviceEndPointUrl, Object requestBean, Object[] pathVariables, Map<String, String> authHeaders, Class<T> responseType, Integer socketTimeout,
			Integer connectionTimeoutMillis) throws ServiceException;	

	/**
	 * Post.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param serviceEndPointUrl
	 *            the service end point url
	 * @param requestBean
	 *            the i request bean
	 * @param pathVariables
	 * @param authHeaders
	 * @param contentType
	 * @param accept
	 * @param responseType
	 * @param socketTimeout
	 * @param connectionTimeout
	 * @return the t
	 * @throws ServiceException
	 *             the service exception
	 */
	public <T> T post(String serviceEndPointUrl, Object requestBean, Object[] pathVariables, Map<String, String> authHeaders, MediaType contentType, MediaType accept,
			Class<T> responseType, Integer socketTimeout, Integer connectionTimeoutMillis) throws ServiceException;

	/**
	 * Post.
	 * 
	 * @param serviceEndPointUrl
	 *            the service end point url
	 * @param requestBean
	 *            the request bean
	 * @param pathVariables
	 *            the path variables
	 * @param authHeaders
	 *            the auth headers
	 * @param contentType
	 *            the content type
	 * @param accept
	 *            the accept
	 * @param responseType
	 *            the response type
	 * @param socketTimeout
	 * @param connectionTimeoutMillis
	 * @return the t
	 * @throws ServiceException
	 *             the service exception
	 */
	public <T> T postXML(String serviceEndPointUrl, Object requestBean, Object[] pathVariables, Map<String, String> authHeaders, MediaType contentType, MediaType accept,
			Class<T> responseType, Integer socketTimeout, Integer connectionTimeoutMillis) throws ServiceException;

	/**
	 * Gets the.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param serviceEndPointUrl
	 *            the service end point url
	 * @param responseType
	 *            the response type
	 * @return the t
	 * @throws ServiceException
	 *             the service exception
	 */
	public <T> T get(String serviceEndPointUrl, Class<T> responseType) throws ServiceException;

	/**
	 * Gets the.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param serviceEndPointUrl
	 *            the service end point url
	 * @param responseType
	 *            the response type
	 * @return the t
	 * @throws ServiceException
	 *             the service exception
	 */
	public <T> T get(String serviceEndPointUrl, Class<T> responseType, Integer socketTimeout, Integer connectionTimeoutMillis) throws ServiceException;

	/**
	 * Gets the.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param serviceEndPointUrl
	 *            the service end point url
	 * @param pathVariables
	 *            the path variables
	 * @param responseType
	 *            the response type
	 * @return the t
	 * @throws ServiceException
	 *             the service exception
	 */
	public <T> T get(String serviceEndPointUrl, Object[] pathVariables, Class<T> responseType, Integer socketTimeout, Integer connectionTimeoutMillis) throws ServiceException;

	/**
	 * Gets the.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param serviceEndPointUrl
	 *            the service end point url
	 * @param pathVariables
	 *            the path variables
	 * @param responseType
	 *            the response type
	 * @param contentType
	 *            the content type
	 * @return the t
	 * @throws ServiceException
	 *             the service exception
	 */
	public <T> T get(String serviceEndPointUrl, Object[] pathVariables, Class<T> responseType, MediaType contentType, Integer socketTimeout, Integer connectionTimeoutMillis)
			throws ServiceException;

	/**
	 * Gets the.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param serviceEndPointUrl
	 *            the service end point url
	 * @param pathVariables
	 *            the path variables
	 * @param responseType
	 *            the response type
	 * @param contentType
	 *            the content type
	 * @param accept
	 *            the accept
	 * @return the t
	 * @throws ServiceException
	 *             the service exception
	 */
	public <T> T get(String serviceEndPointUrl, Object[] pathVariables, Class<T> responseType, MediaType contentType, MediaType accept, Integer socketTimeout,
			Integer connectionTimeoutMillis) throws ServiceException;

	/**
	 * Gets the.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param serviceEndPointUrl
	 *            the service end point url
	 * @param pathVariables
	 *            the path variables
	 * @param responseType
	 *            the response type
	 * @param contentType
	 *            the content type
	 * @param accept
	 *            the accept
	 * @param authHeaders
	 *            the auth headers
	 * @return the t
	 * @throws ServiceException
	 *             the service exception
	 */
	public <T> T get(String serviceEndPointUrl, Object[] pathVariables, Class<T> responseType, MediaType contentType, MediaType accept, Map<String, String> authHeaders,
			Integer socketTimeout, Integer connectionTimeoutMillis) throws ServiceException;

	/**
	 * @param serviceEndPointUrl
	 * @param pathVariables
	 * @param responseType
	 * @param authHeaders
	 * @param socketTimeout
	 * @param connectionTimeoutMillis
	 * @return
	 * @throws ServiceException
	 */
	public <T> T get(String serviceEndPointUrl, Object[] pathVariables, Class<T> responseType, Map<String, String> authHeaders, Integer socketTimeout,
			Integer connectionTimeoutMillis) throws ServiceException;

	/**
	 * @param serviceEndPointUrl
	 * @param pathVariables
	 * @param requestBean
	 * @param responseType
	 * @param authHeaders
	 * @param socketTimeout
	 * @param connectionTimeoutMillis
	 * @return
	 * @throws ServiceException
	 */
	public <T> T get(String serviceEndPointUrl, Object[] pathVariables, Object requestBean, Class<T> responseType, Map<String, String> authHeaders, Integer socketTimeout,
			Integer connectionTimeoutMillis) throws ServiceException;
	
	/**
	 * @param serviceEndPointUrl
	 * @param pathVariables
	 * @param queryParams
	 * @param requestBean
	 * @param responseType
	 * @param authHeaders
	 * @param socketTimeout
	 * @param connectionTimeoutMillis
	 * @return
	 * @throws ServiceException
	 */
	public <T> T get(String serviceEndPointUrl,MultivaluedMap<String, String> queryParams,Class<T> responseType, Map<String, String> authHeaders) throws ServiceException;	

	/**
	 * @param serviceEndPointUrl
	 * @param pathVariables
	 * @param responseType
	 * @param authHeaders
	 * @param socketTimeout
	 * @param connectionTimeoutMillis
	 * @return
	 * @throws ServiceException
	 */
	public <T> T delete(String serviceEndPointUrl, Object[] pathVariables, Class<T> responseType, Map<String, String> authHeaders, Integer socketTimeout,
			Integer connectionTimeoutMillis) throws ServiceException;
	
	/**
	 * @param serviceEndPointUrl
	 * @param requestBean
	 * @param pathVariables
	 * @param authHeaders
	 * @param responseType
	 * @param socketTimeout
	 * @param connectionTimeoutMillis
	 * @return
	 * @throws ServiceException
	 */
	public <T> T put(String serviceEndPointUrl, Object requestBean, Object[] pathVariables, Map<String, String> authHeaders, Class<T> responseType, Integer socketTimeout,
			Integer connectionTimeoutMillis) throws ServiceException;
}
