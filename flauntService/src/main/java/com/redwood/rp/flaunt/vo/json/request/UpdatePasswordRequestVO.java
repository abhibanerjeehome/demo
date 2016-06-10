package com.redwood.rp.flaunt.vo.json.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdatePasswordRequestVO implements Serializable {
	
	private static final long serialVersionUID = 2L;
	
	public UpdatePasswordRequestVO() {		
	}
	

	private String emailAddress;
	private String oldPassword;
	private String newPassword;
	private String uid;
	
	@JsonProperty("uid")
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	@JsonProperty("old_pwd")
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	@JsonProperty("new_pwd")
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@JsonProperty("email")
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


}
