package com.redwood.rp.flaunt.vo.json.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

//@JsonPropertyOrder({ "user_id","user_email_id"})
@JsonIgnoreProperties(ignoreUnknown=true)
//@JsonWriteNullProperties(false)
//@JsonSerialize(include=Inclusion.NON_EMPTY)
public class UserDetailsRequestVO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public UserDetailsRequestVO() {
		// TODO Auto-generated constructor stub
	}
	
	private String userId;
	private String userEmailId;
	
	@JsonProperty("user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@JsonProperty("email_address")
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}



}
