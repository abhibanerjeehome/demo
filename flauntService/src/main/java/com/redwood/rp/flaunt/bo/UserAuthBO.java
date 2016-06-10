package com.redwood.rp.flaunt.bo;

import java.io.Serializable;


public class UserAuthBO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public UserAuthBO() {
		// TODO Auto-generated constructor stub
	}
	
	private String id;
	private String userId;
	private String clientIp;
	private String auctionId;
	private int status;
	private String propertyId;
	private String approved;
	private boolean ndaAccepted;


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getApproved() {
		return approved;
	}
	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isNdaAccepted() {
		return ndaAccepted;
	}
	public void setNdaAccepted(boolean ndaAccepted) {
		this.ndaAccepted = ndaAccepted;
	}
	

}
