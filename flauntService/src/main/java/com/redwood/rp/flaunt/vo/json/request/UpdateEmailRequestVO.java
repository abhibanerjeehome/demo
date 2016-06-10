package com.redwood.rp.flaunt.vo.json.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateEmailRequestVO implements Serializable {
	
	private static final long serialVersionUID = 2L;
	
	public UpdateEmailRequestVO() {		
	}

	private String email;
	private String uid;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}

}
