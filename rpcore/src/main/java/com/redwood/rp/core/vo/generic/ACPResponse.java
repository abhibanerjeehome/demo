package com.redwood.rp.core.vo.generic;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.redwood.rp.core.constant.RequestType;
import com.redwood.rp.security.vo.UserRequestVO;

@JsonIgnoreProperties
public class ACPResponse implements Serializable {

	private static final long serialVersionUID = -4402823049091767296L;

	// Service Input Info
	private String      serviceName;
	private RequestType requestType;
	private UserRequestVO userRequest;

	// Service Output Info
	private boolean     success;
	private String      errorCode;
	private String      errMsg;
	private String      errorDesceription;
	private int         affectedRows;
	private Number		generatedKey;
	private Object      payload;

	/**
	 * @param acpRequest
	 */
	public ACPResponse(ACPRequest acpRequest) {
		if (acpRequest != null) {
			this.serviceName = acpRequest.getServiceName();
			this.requestType = acpRequest.getRequestType();
			this.userRequest = acpRequest.getUserRequest();
		}
	}

	public ACPResponse(){
	}

	@JsonIgnore
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@JsonIgnore
	public RequestType getRequestType() {
		return requestType;
	}
	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public UserRequestVO getUserRequest() {
		return userRequest;
	}

	public void setUserRequest(UserRequestVO userRequest) {
		this.userRequest = userRequest;
	}

	@JsonIgnore
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

	@JsonIgnore
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	@JsonIgnore
	public String getErrorDesceription() {
		return errorDesceription;
	}
	public void setErrorDesceription(String errorDesceription) {
		this.errorDesceription = errorDesceription;
	}

	@JsonIgnore
	public int getAffectedRows() {
		return affectedRows;
	}
	public void setAffectedRows(int affectedRows) {
		this.affectedRows = affectedRows;
	}

	@JsonIgnore
	public Number getGeneratedKey() {
		return generatedKey;
	}
	public void setGeneratedKey(Number generatedKey) {
		this.generatedKey = generatedKey;
	}

	@JsonIgnore
	public Object getPayload() {
		return payload;
	}
	public void setPayload(Object payload) {
		this.payload = payload;
	}

	@JsonIgnore
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
