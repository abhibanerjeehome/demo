package com.redwood.rp.rest.impl;

import static com.redwood.rp.core.constant.ErrorDescription.ERR_MSG_EXTERNAL_SERVICE;
import static com.redwood.rp.core.constant.ErrorDescription.EXTERNAL_SERVICE_EXCEPTION;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParamBean;
import org.apache.http.params.HttpParams;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;

import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.core.vo.json.RestResponseVO;
import com.redwood.rp.rest.RestEasyService;
import com.redwood.rp.rest.client.ACPRestClient;
import com.sun.research.ws.wadl.HTTPMethods;

/**
 * The Class RestEasyServiceImpl.
 * 
 * @author rpandya.cns
 */
@Named
public class RestEasyServiceImpl implements RestEasyService {

	// =================================================
	// Class Variables
	// =================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redwood.rp.restEasy.RestEasyService#post(java.lang.String,
	 * java.lang.Object, java.lang.Class)
	 */
	@Override
	public <T> T post(String serviceEndPointUrl, Object requestBean, Class<T> responseType) throws ServiceException {
		return post(serviceEndPointUrl, requestBean, null, null, null, null, responseType, null, null);
	}

	@Override
	public <T> T post(String serviceEndPointUrl, Object requestBean, Class<T> responseType, Integer socketTimeout, Integer connectionTimeoutMillis) throws ServiceException {
		// Auto-generated method stub
		return post(serviceEndPointUrl, requestBean, null, null, null, null, responseType, socketTimeout, connectionTimeoutMillis);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redwood.rp.restEasy.RestEasyService#post(java.lang.String,
	 * java.lang.Object[], java.lang.Class)
	 */
	@Override
	public <T> T post(String serviceEndPointUrl, Object[] pathVariables, Class<T> responseType, Integer socketTimeout, Integer connectionTimeoutMillis) throws ServiceException {
		return post(serviceEndPointUrl, null, pathVariables, null, null, null, responseType, socketTimeout, connectionTimeoutMillis);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redwood.rp.restEasy.RestEasyService#post(java.lang.String,
	 * java.lang.Object, java.lang.Object[], java.lang.Class)
	 */
	@Override
	public <T> T post(String serviceEndPointUrl, Object requestBean, Object[] pathVariables, Class<T> responseType, Integer socketTimeout, Integer connectionTimeoutMillis)
			throws ServiceException {
		return post(serviceEndPointUrl, requestBean, pathVariables, null, null, null, responseType, socketTimeout, connectionTimeoutMillis);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redwood.rp.restEasy.RestEasyService#post(java.lang.String,
	 * java.lang.Object, java.lang.Object[], java.lang.Class,
	 * javax.ws.rs.core.MediaType)
	 */
	@Override
	public <T> T post(String serviceEndPointUrl, Object requestBean, Object[] pathVariables, MediaType contentType, Class<T> responseType, Integer socketTimeout,
			Integer connectionTimeoutMillis) throws ServiceException {
		return post(serviceEndPointUrl, requestBean, pathVariables, null, contentType, null, responseType, socketTimeout, connectionTimeoutMillis);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redwood.rp.restEasy.RestEasyService#post(java.lang.String,
	 * java.lang.Object, java.lang.Object[], java.lang.Class,
	 * javax.ws.rs.core.MediaType, javax.ws.rs.core.MediaType)
	 */
	@Override
	public <T> T post(String serviceEndPointUrl, Object requestBean, Object[] pathVariables, MediaType contentType, MediaType accept, Class<T> responseType, Integer socketTimeout,
			Integer connectionTimeoutMillis) throws ServiceException {
		return post(serviceEndPointUrl, requestBean, pathVariables, null, contentType, accept, responseType, socketTimeout, connectionTimeoutMillis);
	}

	// =================================================
	// Instance Methods
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
	 * @param pathVariables
	 *            the path variables
	 * @param authHeaders
	 *            the auth headers
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
	@SuppressWarnings("unchecked")
	public <T> T post(String serviceEndPointUrl, Object requestBean, Object[] pathVariables, Map<String, String> authHeaders, MediaType contentType, MediaType accept,
			Class<T> responseType, Integer socketTimeout, Integer connectionTimeoutMillis) throws ServiceException {

		RestResponseVO responseValue = null;
		BufferedReader br = null;
		ObjectMapper jacksonObjectMapper = new ObjectMapper();
		Object returnObject = null;
		ClientResponse<String> responseEntity = null;
		// System.out.println("serviceEndPointUrl "+serviceEndPointUrl+" requestBean "+requestBean);
		if (contentType == null) {
			// contentType = DEFAULT_CONTENT_TYPE;
			contentType = MediaType.APPLICATION_JSON_TYPE;
		}
		if (accept == null) {
			// accept = DEFAULT_ACCEPT;
			accept = MediaType.APPLICATION_JSON_TYPE;// "application/json";
		}
		try {
			if (socketTimeout == null) {
				socketTimeout = new Integer("50000");
			}
			if (connectionTimeoutMillis == null) {
				connectionTimeoutMillis = new Integer("50000");
			}
			ACPRestClient clientRequest = new ACPRestClient();
			clientRequest = getClientRequest(clientRequest, serviceEndPointUrl, requestBean, authHeaders, contentType, accept, socketTimeout, connectionTimeoutMillis);
			clientRequest.setHttpMethod("Post");

			if (pathVariables == null) {
				// responseEntity = super.postForEntity(serviceEndPointUrl,
				// aRequestEntity, responseType);.
				responseEntity = (ClientResponse<String>) clientRequest.post(String.class);
			} else {

				clientRequest.pathParameters(pathVariables);
				responseEntity = (ClientResponse<String>) clientRequest.post(String.class);
			}
			if (responseEntity != null && responseEntity.getStatus() == 200) {

				// responseValue =
				// responseMapper.getObjectFromJSON(ACPResponse.class,responseEntity.getEntity());
				// responseValue =
				// jacksonObjectMapper.readValue(responseEntity.getEntity(),ACPResponse.class);

				try {
					// responseJsonVO =
					// jacksonObjectMapper.readValue(jacksonObjectMapper.writeValueAsString(responseEntity.getEntity(responseType.getClass())),
					// typeRef);
					// responseValue =
					// jacksonObjectMapper.readValue(jacksonObjectMapper.writeValueAsString(responseEntity.getEntity()),
					// ACPResponse.class);
					jacksonObjectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					responseValue = jacksonObjectMapper.readValue(responseEntity.getEntity(), RestResponseVO.class);

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {

				br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(responseEntity.getEntity().getBytes())));

				String output;
				// System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}

				throw new RuntimeException("Failed : HTTP error code : " + responseEntity.getStatus());

			}
		} catch (ServiceException serviceException) {

		} catch (Exception exception) {
			exception.printStackTrace();

		}
		if (responseValue != null) {
			Object oResult = responseValue.getResult();
			if (oResult != null && oResult instanceof java.lang.String)
				returnObject = oResult;
			else if (oResult != null) {
				LinkedHashMap<String, Object> resultHashMap = (LinkedHashMap<String, Object>) oResult;

				try {
					try {
						returnObject = Class.forName(responseType.getName()).newInstance();
					} catch (ClassNotFoundException e) {
						// Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// Auto-generated catch block
						e.printStackTrace();
					}
					jacksonObjectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					returnObject = jacksonObjectMapper.readValue(jacksonObjectMapper.writeValueAsString(resultHashMap), responseType);
					// BeanUtils.copyProperties(returnObject, resultHashMap);
				} catch (IllegalAccessException | IOException e) {
					// Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return (T) returnObject;
	}

	/**
	 * Post.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param serviceEndPointUrl
	 *            the service end point url
	 * @param requestBean
	 *            the request bean
	 * @param pathVariables
	 *            the path variables
	 * @param authHeaders
	 *            the auth headers
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
	@SuppressWarnings("unchecked")
	public <T> T post(String serviceEndPointUrl, Object requestBean, Object[] pathVariables, Map<String, String> authHeaders, Class<T> responseType, Integer socketTimeout,
			Integer connectionTimeoutMillis) throws ServiceException {

		ClientResponse<?> responseEntity = null;
		try {
			if (socketTimeout == null) {
				socketTimeout = new Integer("50000");
			}
			if (connectionTimeoutMillis == null) {
				connectionTimeoutMillis = new Integer("50000");
			}
			ACPRestClient clientRequest = new ACPRestClient();
			clientRequest = getClientRequest(clientRequest, serviceEndPointUrl, requestBean, authHeaders, null, null, socketTimeout, connectionTimeoutMillis);
			clientRequest.setHttpMethod("Post");

			if (pathVariables == null) {
				// responseEntity = super.postForEntity(serviceEndPointUrl,
				// aRequestEntity, responseType);.
				responseEntity = (ClientResponse<?>) clientRequest.post(responseType);
			} else {

				clientRequest.pathParameters(pathVariables);
				responseEntity = (ClientResponse<?>) clientRequest.post(responseType);
			}
			
		} catch (ServiceException serviceException) {

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return (T) responseEntity;
	}
	
	@Override
	public <T> T newPost(String serviceEndPointUrl, Object requestBean, Object[] pathVariables, Map<String, String> authHeaders, Class<T> responseType, Integer socketTimeout,
			Integer connectionTimeoutMillis) throws ServiceException {

		ClientResponse<?> responseEntity = null;
		try {
			if (socketTimeout == null) {
				socketTimeout = new Integer("50000");
			}
			if (connectionTimeoutMillis == null) {
				connectionTimeoutMillis = new Integer("50000");
			}
			ACPRestClient clientRequest = new ACPRestClient();
			clientRequest = getClientRequest(clientRequest, serviceEndPointUrl, requestBean, authHeaders, null, null, socketTimeout, connectionTimeoutMillis);
			clientRequest.setHttpMethod("Post");

			if (pathVariables == null) {
				// responseEntity = super.postForEntity(serviceEndPointUrl,
				// aRequestEntity, responseType);.
				responseEntity = (ClientResponse<?>) clientRequest.post(responseType);
			} else {

				clientRequest.pathParameters(pathVariables);
				responseEntity = (ClientResponse<?>) clientRequest.post(responseType);
			}
//			
//			if(responseEntity.getStatus() != 201) {
//				System.out.println(responseEntity.getEntity(String.class));
//			}

		} catch (ServiceException serviceException) {

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return (T) responseEntity.getEntity(responseType);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redwood.rp.rest.RestEasyService#put(java.lang.String,
	 * java.lang.Object, java.lang.Object[], java.util.Map, java.lang.Class,
	 * java.lang.Integer, java.lang.Integer)
	 */
	public <T> T put(String serviceEndPointUrl, Object requestBean, Object[] pathVariables, Map<String, String> authHeaders, Class<T> responseType, Integer socketTimeout,
			Integer connectionTimeoutMillis) throws ServiceException {

		ClientResponse<?> responseEntity = null;
		try {
			if (socketTimeout == null) {
				socketTimeout = new Integer("50000");
			}
			if (connectionTimeoutMillis == null) {
				connectionTimeoutMillis = new Integer("50000");
			}
			ACPRestClient clientRequest = new ACPRestClient();
			clientRequest = getClientRequest(clientRequest, serviceEndPointUrl, requestBean, authHeaders, null, null, socketTimeout, connectionTimeoutMillis);
			clientRequest.setHttpMethod("Put");

			if (pathVariables == null) {
				// responseEntity = super.postForEntity(serviceEndPointUrl,
				// aRequestEntity, responseType);.
				responseEntity = (ClientResponse<?>) clientRequest.put(responseType);
			} else {

				clientRequest.pathParameters(pathVariables);
				responseEntity = (ClientResponse<?>) clientRequest.post(responseType);
			}
		} catch (ServiceException serviceException) {

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return (T) responseEntity.getEntity(responseType);
	}

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
	@SuppressWarnings("unchecked")
	public <T> T postXML(String serviceEndPointUrl, Object requestBean, Object[] pathVariables, Map<String, String> authHeaders, MediaType contentType, MediaType accept,
			Class<T> responseType, Integer socketTimeout, Integer connectionTimeoutMillis) throws ServiceException {
		BufferedReader br = null;
		Object returnObject = null;
		ClientResponse<String> responseEntity = null;
		// System.out.println("serviceEndPointUrl "+serviceEndPointUrl+" requestBean "+requestBean);
		if (contentType == null) {
			// contentType = DEFAULT_CONTENT_TYPE;
			contentType = MediaType.APPLICATION_XML_TYPE;
		}
		if (accept == null) {
			// accept = DEFAULT_ACCEPT;
			accept = MediaType.APPLICATION_XML_TYPE;// "application/json";
		}
		try {
			if (socketTimeout == null) {
				socketTimeout = new Integer("50000");
			}
			if (connectionTimeoutMillis == null) {
				connectionTimeoutMillis = new Integer("50000");
			}
			ACPRestClient clientRequest = new ACPRestClient();
			/*
			 * clientRequest = getClientRequest(clientRequest,
			 * serviceEndPointUrl, requestBean, authHeaders , contentType,
			 * accept, socketTimeout,connectionTimeoutMillis);
			 */

			StringWriter writer = new StringWriter();
			JAXBContext jaxbContext = JAXBContext.newInstance(requestBean.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// jaxbMarshaller.setProperty("com.sun.xml.bind.xmlHeaders",
			// "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			// jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT,
			// Boolean.TRUE);
			jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
			jaxbMarshaller.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			jaxbMarshaller.marshal(requestBean, writer);

			clientRequest.setHttpMethod("Post");

			ClientRequest request = new ClientRequest(serviceEndPointUrl);

			System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<!DOCTYPE REQUEST_GROUP SYSTEM \"C2DRequestv2.0.dtd\">" + writer.getBuffer().toString());

			request.body(contentType, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<!DOCTYPE REQUEST_GROUP SYSTEM \"C2DRequestv2.0.dtd\">" + writer.getBuffer().toString());

			if (pathVariables == null) {
				// responseEntity = super.postForEntity(serviceEndPointUrl,
				// aRequestEntity, responseType);.
				responseEntity = (ClientResponse<String>) request.post(String.class);
			} else {

				clientRequest.pathParameters(pathVariables);
				responseEntity = (ClientResponse<String>) clientRequest.post(String.class);
			}
			if (responseEntity != null && responseEntity.getStatus() == 200 && MediaType.APPLICATION_XML.equalsIgnoreCase(accept.toString())) {

				System.out.println(responseEntity.getEntity());

				JAXBContext jaxbContext1 = JAXBContext.newInstance(Class.forName(responseType.getName()));
				Unmarshaller unmarshaller = jaxbContext1.createUnmarshaller();

				StringReader reader = new StringReader(responseEntity.getEntity());
				returnObject = Class.forName(responseType.getName()).newInstance();
				returnObject = unmarshaller.unmarshal(reader);

				System.out.println(returnObject.toString());
			} else {

				br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(responseEntity.getEntity().getBytes())));

				String output;
				// System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}

				throw new RuntimeException("Failed : HTTP error code : " + responseEntity.getStatus());

			}
		} catch (ServiceException serviceException) {

		} catch (Exception exception) {
			exception.printStackTrace();

		}

		return (T) returnObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redwood.rp.restEasy.RestEasyService#get(java.lang.String,
	 * java.lang.Class)
	 */
	@Override
	public <T> T get(String serviceEndPointUrl, Class<T> responseType) throws ServiceException {
		return get(serviceEndPointUrl, null, responseType, null, null, null, null, null);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redwood.rp.restEasy.RestEasyService#get(java.lang.String,
	 * java.lang.Class)
	 */
	@Override
	public <T> T get(String serviceEndPointUrl, Class<T> responseType, Integer socketTimeout, Integer connectionTimeoutMillis) throws ServiceException {
		return get(serviceEndPointUrl, null, responseType, null, null, null, socketTimeout, connectionTimeoutMillis);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redwood.rp.restEasy.RestEasyService#get(java.lang.String,
	 * java.lang.Object[], java.lang.Class)
	 */
	@Override
	public <T> T get(String serviceEndPointUrl, Object[] pathVariables, Class<T> responseType, Integer socketTimeout, Integer connectionTimeoutMillis) throws ServiceException {
		return get(serviceEndPointUrl, null, responseType, null, null, null, socketTimeout, connectionTimeoutMillis);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redwood.rp.restEasy.RestEasyService#get(java.lang.String,
	 * java.lang.Object[], java.lang.Class, javax.ws.rs.core.MediaType)
	 */
	@Override
	public <T> T get(String serviceEndPointUrl, Object[] pathVariables, Class<T> responseType, MediaType contentType, Integer socketTimeout, Integer connectionTimeoutMillis)
			throws ServiceException {
		return get(serviceEndPointUrl, pathVariables, responseType, contentType, null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redwood.rp.restEasy.RestEasyService#get(java.lang.String,
	 * java.lang.Object[], java.lang.Class, javax.ws.rs.core.MediaType,
	 * javax.ws.rs.core.MediaType)
	 */
	public <T> T get(String serviceEndPointUrl, Object[] pathVariables, Class<T> responseType, MediaType contentType, MediaType accept, Integer socketTimeout,
			Integer connectionTimeoutMillis) throws ServiceException {

		return get(serviceEndPointUrl, pathVariables, responseType, contentType, accept, null, null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redwood.rp.restEasy.RestEasyService#get(java.lang.String,
	 * java.lang.Object[], java.lang.Class, javax.ws.rs.core.MediaType,
	 * javax.ws.rs.core.MediaType, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String serviceEndPointUrl, Object[] pathVariables, Class<T> responseType, Map<String, String> authHeaders, Integer socketTimeout,
			Integer connectionTimeoutMillis) throws ServiceException {

		ClientResponse<T> responseEntity = null;

		try {
			if (socketTimeout == null) {
				socketTimeout = new Integer("5000");
			}
			if (connectionTimeoutMillis == null) {
				connectionTimeoutMillis = new Integer("5000");
			}
			ACPRestClient clientRequest = new ACPRestClient();
			clientRequest = getClientRequest(clientRequest, serviceEndPointUrl, null, authHeaders, null, null, socketTimeout, connectionTimeoutMillis);
			clientRequest.setHttpMethod("Get");

			if (pathVariables == null) {
				// responseEntity = super.postForEntity(serviceEndPointUrl,
				// aRequestEntity, responseType);.
				responseEntity = (ClientResponse<T>) clientRequest.get(responseType);

			} else {

				clientRequest.pathParameters(pathVariables);
				responseEntity = (ClientResponse<T>) clientRequest.get(responseType);
			}
		} catch (ServiceException serviceException) {

		} catch (Exception exception) {
			exception.printStackTrace();

		}
		return (T) responseEntity;

	}

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
	@Override
	public <T> T get(String serviceEndPointUrl, Object[] pathVariables, Object requestBean, Class<T> responseType, Map<String, String> authHeaders, Integer socketTimeout,
			Integer connectionTimeoutMillis) throws ServiceException {

		ClientResponse<T> responseEntity = null;

		try {
			if (socketTimeout == null) {
				socketTimeout = new Integer("5000");
			}
			if (connectionTimeoutMillis == null) {
				connectionTimeoutMillis = new Integer("5000");
			}
			ACPRestClient clientRequest = new ACPRestClient();
			clientRequest = getClientRequest(clientRequest, serviceEndPointUrl, null, authHeaders, null, null, socketTimeout, connectionTimeoutMillis);
			clientRequest.setHttpMethod("Get");

			if (pathVariables == null) {
				// responseEntity = super.postForEntity(serviceEndPointUrl,
				// aRequestEntity, responseType);
				clientRequest.followRedirects(true);
				responseEntity = (ClientResponse<T>) clientRequest.get(responseType);
			} else {
				clientRequest.pathParameters(pathVariables);
				responseEntity = (ClientResponse<T>) clientRequest.get(responseType);
			}
		} catch (ServiceException serviceException) {

		} catch (Exception exception) {
			exception.printStackTrace();

		}
		return (T) responseEntity.getEntity(responseType);

	}

	@Override
	public <T> T get(String serviceEndPointUrl, MultivaluedMap<String, String> queryParams, Class<T> responseType, Map<String, String> authHeaders) throws ServiceException {
		ClientResponse<T> responseEntity = null;

		try {
			ACPRestClient clientRequest = new ACPRestClient();
			clientRequest = getClientRequest(clientRequest, serviceEndPointUrl, null, authHeaders, null, null, null, null);
			clientRequest.setHttpMethod(HTTPMethods.GET.name());
			clientRequest.getQueryParameters().putAll(queryParams);
			responseEntity = (ClientResponse<T>) clientRequest.get(responseType);
		} catch (ServiceException serviceException) {

		} catch (Exception exception) {
			exception.printStackTrace();

		}
		return (T) responseEntity.getEntity(responseType);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redwood.rp.rest.RestEasyService#delete(java.lang.String,
	 * java.lang.Object[], java.lang.Class, java.util.Map, java.lang.Integer,
	 * java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T delete(String serviceEndPointUrl, Object[] pathVariables, Class<T> responseType, Map<String, String> authHeaders, Integer socketTimeout,
			Integer connectionTimeoutMillis) throws ServiceException {

		ClientResponse<T> responseEntity = null;

		try {
			if (socketTimeout == null) {
				socketTimeout = new Integer("5000");
			}
			if (connectionTimeoutMillis == null) {
				connectionTimeoutMillis = new Integer("5000");
			}
			ACPRestClient clientRequest = new ACPRestClient();
			clientRequest = getClientRequest(clientRequest, serviceEndPointUrl, null, authHeaders, null, null, socketTimeout, connectionTimeoutMillis);
			clientRequest.setHttpMethod("DELETE");

			if (pathVariables == null) {
				// responseEntity = super.postForEntity(serviceEndPointUrl,
				// aRequestEntity, responseType);.
				responseEntity = (ClientResponse<T>) clientRequest.delete();

			} else {
				clientRequest.pathParameters(pathVariables);
				responseEntity = (ClientResponse<T>) clientRequest.delete();
			}
		} catch (ServiceException serviceException) {

		} catch (Exception exception) {
			exception.printStackTrace();

		}
		return (T) responseEntity.getEntity(responseType);

	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public <T> T get(String serviceEndPointUrl, Object[] pathVariables, Class<T> responseType, MediaType contentType, MediaType accept, Map<String, String> authHeaders,
			Integer socketTimeout, Integer connectionTimeoutMillis) throws ServiceException {

		RestResponseVO responseValue = null;
		ObjectMapper jacksonObjectMapper = new ObjectMapper();
		Object returnObject = null;

		if (contentType == null) {
			// contentType = DEFAULT_CONTENT_TYPE;
			contentType = MediaType.APPLICATION_JSON_TYPE;
		}
		if (accept == null) {
			// accept = DEFAULT_ACCEPT;
			accept = MediaType.APPLICATION_JSON_TYPE;// "application/json";
		}
		try {
			if (socketTimeout == null) {
				socketTimeout = new Integer("50000");
			}
			if (connectionTimeoutMillis == null) {
				connectionTimeoutMillis = new Integer("50000");
			}
			ACPRestClient clientRequest = new ACPRestClient();
			clientRequest = getClientRequest(clientRequest, serviceEndPointUrl, null, authHeaders, contentType, accept, socketTimeout, connectionTimeoutMillis);
			clientRequest.setHttpMethod("Get");
			ClientResponse<String> responseEntity = null;

			if (pathVariables == null) {
				// responseEntity = super.postForEntity(serviceEndPointUrl,
				// aRequestEntity, responseType);.
				responseEntity = (ClientResponse<String>) clientRequest.get(String.class);
			} else {
				clientRequest.pathParameters(pathVariables);
				responseEntity = (ClientResponse<String>) clientRequest.get(String.class);
			}
			if (responseEntity != null && responseEntity.getStatus() == 200) {
				try {
					jacksonObjectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					responseValue = jacksonObjectMapper.readValue(responseEntity.getEntity(), RestResponseVO.class);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(responseEntity.getEntity().getBytes())));

				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}

				throw new RuntimeException("Failed : HTTP error code : " + responseEntity.getStatus());
			}
		} catch (ServiceException serviceException) {

		} catch (Exception exception) {
			exception.printStackTrace();

		}
		if (responseValue != null) {
			Object oResult = responseValue.getResult();
			String responseClassName = responseType.getName();
			if (oResult != null && responseClassName.contains("String"))
				returnObject = oResult;
			else if (oResult != null && oResult instanceof java.util.LinkedHashMap) {
				LinkedHashMap<String, Object> resultHashMap = (LinkedHashMap<String, Object>) oResult;
				try {
					try {
						returnObject = Class.forName(responseType.getName()).newInstance();
					} catch (ClassNotFoundException e) {
						// Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// Auto-generated catch block
						e.printStackTrace();
					}
					if (returnObject != null && resultHashMap != null)
						// BeanUtils.copyProperties(returnObject,
						// resultHashMap);
						jacksonObjectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					returnObject = jacksonObjectMapper.readValue(jacksonObjectMapper.writeValueAsString(resultHashMap), responseType);
				} catch (IllegalAccessException e) {
					// Auto-generated catch block
					e.printStackTrace();
				} catch (JsonParseException e) {
					// Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// Auto-generated catch block
					e.printStackTrace();
				} catch (JsonGenerationException e) {
					// Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// Auto-generated catch block
					e.printStackTrace();
				}
			} else if (oResult != null && oResult instanceof java.util.ArrayList) {
				List resultHashMap = (ArrayList) oResult;

				try {
					try {
						returnObject = Class.forName(responseType.getName()).newInstance();
					} catch (ClassNotFoundException e) {
						// Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// Auto-generated catch block
						e.printStackTrace();
					}
					if (returnObject != null && resultHashMap != null)
						// BeanUtils.copyProperties(returnObject,
						// resultHashMap);
						jacksonObjectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					returnObject = jacksonObjectMapper.readValue(jacksonObjectMapper.writeValueAsString(resultHashMap), responseType);
				} catch (IllegalAccessException e) {
					// Auto-generated catch block
					e.printStackTrace();
				} catch (JsonParseException e) {
					// Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// Auto-generated catch block
					e.printStackTrace();
				} catch (JsonGenerationException e) {
					// Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return (T) returnObject;
	}

	// =================================================
	// Private Methods
	// =================================================

	/**
	 * Assign header values.
	 * 
	 * @param clientRequest
	 *            the client request
	 * @param authHeaders
	 *            the auth headers
	 * @return the client request
	 */
	private ACPRestClient assignHeaderValues(ACPRestClient clientRequest, Map<String, String> authHeaders) {
		if (authHeaders != null && authHeaders.size() != 0) {
			for (String key : authHeaders.keySet()) {
				clientRequest.header(key, authHeaders.get(key));
			}
		}

		return clientRequest;
	}

	private ACPRestClient getClientRequest(ACPRestClient clientRequest, String serviceEndPointUrl, Object requestBean, Map<String, String> authHeaders, MediaType contentType,
			MediaType accept, Integer connectionTimeoutMillis, Integer socketTimeoutMillis) throws ServiceException {

		if (StringUtils.isNotEmpty(serviceEndPointUrl)) {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpParams params = httpClient.getParams();
			HttpConnectionParamBean httpConnectionParams = new HttpConnectionParamBean(params);
			if (connectionTimeoutMillis != null) {
				httpConnectionParams.setConnectionTimeout(connectionTimeoutMillis.intValue());
			}
			if (socketTimeoutMillis != null) {
				httpConnectionParams.setSoTimeout(socketTimeoutMillis);
			}

			if (authHeaders == null || authHeaders.size() == 0)
				authHeaders = new HashMap<String, String>();

			ClientExecutor executor = new ApacheHttpClient4Executor(httpClient);
			try {
				clientRequest.assignValues(serviceEndPointUrl, executor);
				clientRequest = assignHeaderValues(clientRequest, authHeaders);

				if (contentType == null && authHeaders.get("Content-Type") == null) {
					// contentType = DEFAULT_CONTENT_TYPE;
					contentType = MediaType.APPLICATION_JSON_TYPE;
					authHeaders.put("Content-Type", contentType.toString());
				}
				if (accept == null && authHeaders.get("Accept") == null) {
					// accept = DEFAULT_ACCEPT;
					accept = MediaType.APPLICATION_JSON_TYPE;// "application/json";
					clientRequest.accept(accept);
				}

				if (requestBean != null)
					if (null != authHeaders.get("Content-Type"))
						clientRequest.body(authHeaders.get("Content-Type"), requestBean);
					else
						clientRequest.body(contentType, requestBean);

			} catch (Exception exception) {
				exception.printStackTrace();
				ErrorVO errorVO = new ErrorVO(EXTERNAL_SERVICE_EXCEPTION, ERR_MSG_EXTERNAL_SERVICE, exception.getMessage());
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
			}
		} else {
			ErrorVO errorVO = new ErrorVO(EXTERNAL_SERVICE_EXCEPTION, ERR_MSG_EXTERNAL_SERVICE, "No url defined");
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
		}
		return clientRequest;
	}

}
