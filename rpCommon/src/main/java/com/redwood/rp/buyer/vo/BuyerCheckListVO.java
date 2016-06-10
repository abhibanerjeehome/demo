/**
 * DirVO.java
 * DocumentService
 * 
 * Copyright (c) 2013, Auction.com
 * All rights reserved.
 */
package com.redwood.rp.buyer.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonWriteNullProperties;

@SuppressWarnings("deprecation")
@JsonWriteNullProperties(false)

public class BuyerCheckListVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String registerationLink;
	private String registrationRequired;
	private String registrationStatus;	
	private String loginRequired;
	private Timestamp bidStartTime;
	private Timestamp bidEndTime;
	
	
	
	
	
	
	public BuyerCheckListVO() {
	}

	public String getRegisterationLink() {
		return registerationLink;
	}

	public void setRegisterationLink(String registerationLink) {
		this.registerationLink = registerationLink;
	}

	public String getRegistrationRequired() {
		return registrationRequired;
	}

	public void setRegistrationRequired(String registrationRequired) {
		this.registrationRequired = registrationRequired;
	}

	public String getLoginRequired() {
		return loginRequired;
	}

	public void setLoginRequired(String loginRequired) {
		this.loginRequired = loginRequired;
	}

	public Timestamp getBidStartTime() {
		return bidStartTime;
	}

	public void setBidStartTime(Timestamp bidStartTime) {
		this.bidStartTime = bidStartTime;
	}

	public Timestamp getBidEndTime() {
		return bidEndTime;
	}

	public void setBidEndTime(Timestamp bidEndTime) {
		this.bidEndTime = bidEndTime;
	}

	public String getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}
	
	
	
	
	

	
}
