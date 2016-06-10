package com.redwood.rp.security.vo;

public class OauthClientServiceVO implements java.io.Serializable {

	private static final long serialVersionUID = -4897574397263215226L;

	private Integer clientServiceId;
	
	private Integer version;
	
	private String serviceName;
	
	private String serviceURL;
	
	private boolean userTokenRequired;
	
	private boolean urlFilterFlag;
	
	private String serviceDescription;
	
	//Use to pre-select checkbox when select services for a role
	private boolean checkBoxDefault;

	public boolean isCheckBoxDefault() {
		return checkBoxDefault;
	}

	public void setCheckBoxDefault(boolean checkBoxDefault) {
		this.checkBoxDefault = checkBoxDefault;
	}

	public Integer getClientServiceId() {
		return clientServiceId;
	}

	public void setClientServiceId(Integer clientServiceId) {
		this.clientServiceId = clientServiceId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceURL() {
		return serviceURL;
	}

	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}

	public boolean isUserTokenRequired() {
		return userTokenRequired;
	}

	public void setUserTokenRequired(boolean userTokenRequired) {
		this.userTokenRequired = userTokenRequired;
	}

	public boolean isUrlFilterFlag() {
		return urlFilterFlag;
	}

	public void setUrlFilterFlag(boolean urlFilterFlag) {
		this.urlFilterFlag = urlFilterFlag;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
}
