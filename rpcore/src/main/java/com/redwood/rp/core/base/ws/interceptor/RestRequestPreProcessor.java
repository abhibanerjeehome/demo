package com.redwood.rp.core.base.ws.interceptor;
/**
 * ===================================================================== Restful
 * Request PreProcessor
 * 
 * 
 * --------------------------------------------------------------------- Update
 * date Contents
 * =====================================================================
 * 12/10/2012 create
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang.BooleanUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.MessageBodyReaderContext;
import org.jboss.resteasy.spi.interception.MessageBodyReaderInterceptor;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.redwood.rp.core.base.netacuity.GeoLocator;
import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.constant.RestConst;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.exception.UtilityException;
import com.redwood.rp.core.util.ExceptionUtil;
import com.redwood.rp.core.util.RestRequestStore;
import com.redwood.rp.core.vo.json.BaseErrorInfoResponseVO;
import com.redwood.rp.core.vo.json.BaseRestRequestVO;
import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.core.vo.json.StatusVO;
import com.redwood.rp.security.service.OauthService;
import com.redwood.rp.security.vo.EdgeLocationVO;
import com.redwood.rp.security.vo.UserRequestVO;

@Named
@Provider
@ServerInterceptor
public class RestRequestPreProcessor implements PreProcessInterceptor, MessageBodyReaderInterceptor {
	Logger logger = LoggerFactory.getLogger(RestRequestPreProcessor.class);

	@Context
	HttpServletRequest servletRequest;

	@Value("${security.oauth.openflag}")
	private String enableAuthentication;

	@Value("${core.geoLocation.enableflag}")
	private String geoLocationEnable;

	@Resource
	private OauthService oauthService = null;

	@Inject
	private GeoLocator geoLocator = null;

	public ServerResponse preProcess(HttpRequest request, ResourceMethod resourceMethod) throws Failure, WebApplicationException {

		/*
		 * SECURITY CHECK will has to be implemented
		 */
		RestRequestStore.setValue(RestConst.BASE_REST_REQ, updateRequestProperties(request));

		String authorizationStr = servletRequest.getHeader(RestConst.REQ_PARAM_AUTHORIZATION);

		if (BooleanUtils.toBoolean(enableAuthentication)) {
			try {
				// Validate the token information
				oauthService.validateToken(authorizationStr);
			} catch (ServiceException e) {
				StatusVO status = ExceptionUtil.genStatusVOFromErrorVO(e);
				BaseErrorInfoResponseVO response = new BaseErrorInfoResponseVO(status);
				logger.error("Wrong token info: " + authorizationStr + " , Error: " + status.getMessage(), e);
				logger.error("URL: " + request.getUri().getRequestUri().getPath());
				return new ServerResponse(response, Integer.valueOf(status.getCode()), new Headers<Object>());
			}
			UserRequestVO userRequest = OauthService.getUserRequest();

			String clientIP = servletRequest.getHeader(RestConst.TOKEN_REQUEST_CLIENT_IP);
			// to get IP Address from ipad app
			String requestIP = servletRequest.getRemoteAddr();
			userRequest.setRequestIP(requestIP);
			String sessionID = servletRequest.getHeader(RestConst.TOKEN_REQUEST_SESSION_ID);

			userRequest.setClientIP(clientIP);

			userRequest.setSessionId(sessionID);
			if (BooleanUtils.toBoolean(geoLocationEnable)) {
				EdgeLocationVO edgeLocationVO = geoLocator.getGeoLocation(clientIP);
				userRequest.copyGeoLocation(edgeLocationVO);
			}

			try {
				// Validate the service end point
				oauthService.authorizeService(request.getUri().getRequestUri().getPath(), request.getUri().getPath(), userRequest.isAnonymousToken());
			} catch (ServiceException e) {
				logger.error("Unregistered or blocked service error for:  " + request.getUri().getRequestUri().getPath(), e);
				logger.error("Token Info:  " + authorizationStr, e);
				logger.error("Client Id:  " + userRequest.getClientId(), e);
				logger.error("Client IP:  " + userRequest.getClientIP(), e);
				StatusVO status = ExceptionUtil.genStatusVOFromErrorVO(e);
				BaseErrorInfoResponseVO response = new BaseErrorInfoResponseVO(status);
				return new ServerResponse(response, Integer.valueOf(status.getCode()), new Headers<Object>());
			}
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public Object read(MessageBodyReaderContext context) throws IOException, WebApplicationException {
		if (MediaType.APPLICATION_JSON.equalsIgnoreCase(context.getMediaType().toString())) {
			String data = null;
			Object object = null;
			BufferedReader reader = null;
			ObjectMapper mapper = new ObjectMapper();
			StringBuilder sBuilder = new StringBuilder();
			InputStream inputStream = context.getInputStream();

			if (inputStream != null) {
				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((data = reader.readLine()) != null) {
					sBuilder.append(data);
				}
			}

			logger.info("reading data : " + sBuilder.toString());

			try {
				if (sBuilder.length() > 0) {
					object = mapper.readValue(sBuilder.toString(), context.getType());
				}
			} catch (Exception e) {
				ErrorVO errorVO = new ErrorVO(ErrorDescription.ERR_CD_UT_JSON_CONVERSION, ErrorDescription.ERR_MSG_UT_JSON_CONVERSION, e.getMessage());
				StatusVO status = ExceptionUtil.genStatusVOFromErrorVO(new UtilityException(ExceptionType.EXCEPTION_UTILITY, errorVO));
				BaseErrorInfoResponseVO response = new BaseErrorInfoResponseVO(status);
				throw new WebApplicationException(new ServerResponse(response, Integer.valueOf(status.getCode()), new Headers<Object>()));
			}
			return object;
		} else
			return context.proceed();
	}

	private BaseRestRequestVO updateRequestProperties(HttpRequest request) {

		BaseRestRequestVO baseReq = new BaseRestRequestVO();
//		baseReq.setFormParameters(request.getFormParameters());
		baseReq.setFormat(RestConst.REQ_DATA_FORMAT);
		baseReq.setHttpMethod(request.getHttpMethod());
		baseReq.setUri(request.getUri().getBaseUri().getRawQuery());
		baseReq.setRequestId(UUID.randomUUID());
		baseReq.setStartTime(new Date());
		return baseReq;
	}

}
