package com.redwood.rp.core.vo.generic;

/**
 *=====================================================================
 * ACP Request Value Object 
 *
 * Generic Request Value Object for carrying on request information
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 01/16/2013   create
 *
 */

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.constant.RequestType;
import com.redwood.rp.core.exception.ValidationException;
import com.redwood.rp.core.util.MessageFormattor;
import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.security.service.OauthService;
import com.redwood.rp.security.vo.UserRequestVO;

public class ACPRequest implements Serializable {
	private static final long serialVersionUID = -5898574012254020583L;
	private static Logger logger = LoggerFactory.getLogger(ACPRequest.class.getName());

	// Service Input Info
	private String serviceName;
	private RequestType requestType;
	private Object payload;
	private UserRequestVO userRequest;

	/**
	 * Constructor
	 */
	public ACPRequest() {
		init();
	}

	/**
	 * Constructor
	 * @param userSessionVO
	 */
	public ACPRequest(String serviceName, RequestType requestType, Object payload) {
		// Reset 
		init();
		// set on service input
		this.serviceName = serviceName;
		this.requestType = requestType;
		this.payload = payload;
	}

	/**
	 * initialize this object 
	 */
	private void init() {
		// Reset service call Info
		this.serviceName = "";
		this.requestType = null;
		this.payload = null;
		this.userRequest = OauthService.getUserRequest();
	}

	public static boolean validateACPRequest(ACPRequest request) throws ValidationException {
		String errorDescription = "";
		if (request == null) {
			errorDescription = "The input ACPRequest object cannot be null.";
			logger.error(MessageFormattor.errorFormat(ACPRequest.class.getName(),
					"validateACPRequest", ExceptionType.EXCEPTION_VALIDATION, errorDescription));
			throw new ValidationException(ExceptionType.EXCEPTION_VALIDATION,
					new ErrorVO(ErrorDescription.ERR_CD_VALIDATION_NULLPOINTER,ErrorDescription.ERR_MSG_VALIDATION_NULLPOINTER, errorDescription));
		}
		if (request.getPayload() == null) {
			errorDescription = "The input payload object cannot be null.";
			logger.error(MessageFormattor.errorFormat(ACPRequest.class.getName(),
					"validateACPRequest", ExceptionType.EXCEPTION_VALIDATION, errorDescription));
			throw new ValidationException(ExceptionType.EXCEPTION_VALIDATION,
					new ErrorVO(ErrorDescription.ERR_CD_VALIDATION_NULLPOINTER,ErrorDescription.ERR_MSG_VALIDATION_NULLPOINTER, errorDescription));
		}
		if (request.getRequestType() == null) {
			errorDescription = "The input request type cannot be null.";
			logger.error(MessageFormattor.errorFormat(ACPRequest.class.getName(),
					"validateACPRequest", ExceptionType.EXCEPTION_VALIDATION, errorDescription));
			throw new ValidationException(ExceptionType.EXCEPTION_VALIDATION,
					new ErrorVO(ErrorDescription.ERR_CD_VALIDATION_NULLPOINTER,ErrorDescription.ERR_MSG_VALIDATION_NULLPOINTER, errorDescription));
		}
		return true;
	}

	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public RequestType getRequestType() {
		return requestType;
	}
	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public Object getPayload() {
		return payload;
	}
	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public UserRequestVO getUserRequest() {
		return userRequest;
	}

	public void setUserRequest(UserRequestVO userRequest) {
		this.userRequest = userRequest;
	}
}
