package com.redwood.rp.core.base.ws.service;
/**
 *=====================================================================
 * Abstract Base Restful Service Implementation
 *
 * Provide a base RESTful services. 
 * All business RESTful implementation should extend this class.
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redwood.rp.core.constant.RestConst;
import com.redwood.rp.core.exception.BaseException;
import com.redwood.rp.core.util.ExceptionUtil;
import com.redwood.rp.core.util.RestRequestStore;
import com.redwood.rp.core.util.StringUtil;
import com.redwood.rp.core.vo.json.BaseErrorInfoResponseVO;
import com.redwood.rp.core.vo.json.BaseRestRequestVO;
import com.redwood.rp.core.vo.json.BaseRestResponseVO;
import com.redwood.rp.core.vo.json.ResponseDebugVO;
import com.redwood.rp.core.vo.json.StatusVO;


public abstract class BaseRestService {
	protected static final Logger logger = LoggerFactory.getLogger(BaseRestService.class);
	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";

	@Context
	protected HttpServletRequest request;

	/**
	 * get Base Rest Request.
	 * 
	 * @return base rest request
	 */
	public final BaseRestRequestVO getBaseRestRequest() {
		/** Value initialized in RestRequestPreprocessor */
		return (BaseRestRequestVO) RestRequestStore.getValue(RestConst.BASE_REST_REQ);
	}

	/**
	 * get input parameter value based on parameter key.
	 * 
	 * @param parameter
	 *            key name
	 * @return input parameter key value
	 */
	protected String getFormParameterValue(String parameter) {
		MultivaluedMap<String, String> params = getBaseRestRequest().getFormParameters();
		if (params.get(parameter) == null || StringUtil.isBlank(params.get(parameter).get(0)))	{
			return null;
		} else {
			logger.debug("getFormParameterValue - "+ parameter +" : " + params.get(parameter).get(0));
			return params.get(parameter).get(0);
		}
	}	

	/**
	 * set the Response Data.
	 * 
	 * @param response base response
	 * 
	 * @return base rest response
	 */	
	protected Response setResponseData(BaseRestResponseVO response)	{		
		StatusVO status = response.getResponseStatus();
		String code = String.valueOf(200);
		if (status == null) {	
			status = new StatusVO();
			status.setCode(code);
			status.setMessage(SUCCESS);
		} else if (NumberUtils.isDigits(status.getCode())) {
			code = status.getCode();
		}
		if (getBaseRestRequest()!=null) {
			ResponseDebugVO debug = new ResponseDebugVO();
			debug.setTimings(getBaseRestRequest().getStartTime());
			debug.setDescription(getBaseRestRequest().getUri());
			status.setDebug(debug);
		}
		response.setResponseStatus(status);
		RestRequestStore.clear();
		return Response.status(Integer.valueOf(code)).entity(response).build();
	}

	/**
	 * set the Error Info Response Data.
	 * 
	 * @param serviceException service exception
	 *  
	 * @return base rest response
	 */	
	protected Response setErrorInfoResponse(BaseException exception)	{
		//		ErrorVO errorVO = serviceException.getErrorVO();
		//		// retrieve code from exception type.
		//		String code = String.valueOf(ExceptionUtil.getErrorCode(serviceException.getExceptionType()));
		//		StatusVO status = null;
		//		if (status == null) {	
		//			status = new StatusVO();
		//			status.setCode(code);
		//			status.setMessage(FAILURE);
		//			if (errorVO != null) {
		//				status.setErrors(serviceException.getErrorVO().getErrors());
		//			}
		//		}
		StatusVO status = ExceptionUtil.genStatusVOFromErrorVO(exception);
		BaseErrorInfoResponseVO response = new BaseErrorInfoResponseVO(status);
		RestRequestStore.clear();
		return Response.status(Integer.valueOf(status.getCode())).entity(response).build();
	}


	/**
	 * set the Error Info Response Data.
	 * 
	 * @param message error message
	 *  
	 * @return base rest response
	 *//*	
	protected Response setErrorInfoResponse(String message)	{					
		BaseErrorInfoResponseVO errorInfoResponse = new BaseErrorInfoResponseVO(new ErrorVO(ErrorDescription.ERR_CD_SERVICE_VALIDATION, 
				                                              message, ErrorDescription.ERR_MSG_SERVICE_VALIDATION));
		errorInfoResponse.setCode(404);
		errorInfoResponse.setTimings(getBaseRestRequest().getStartTime());
		RestRequestStore.clear();
		return null; //Response.serverError().status(errorInfoResponse.getCode()).entity(errorInfoResponse).build();		
	}*/
}