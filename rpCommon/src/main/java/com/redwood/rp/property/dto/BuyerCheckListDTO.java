package com.redwood.rp.property.dto;

import java.sql.Timestamp;

public class BuyerCheckListDTO {
	private String auctionID;
	private String auctionNumber;
	private String propertyID;
	private String statusReg;
	private Timestamp propBidStartDT;
	private Timestamp propBidEndDT;
	private Timestamp biddingStartTime;
	private Timestamp biddingEndTime;
	private Timestamp eventBiddingStartTime;
	private Timestamp eventBiddingEndTime;
	private String timeSelection;
	private String registrationFlow;
	private String preAuction;
	private Boolean allowProxyBidding;
	private String userId;
	private Boolean isAssetLvlBidding;
	
	public boolean getIsAssetLvlBidding() {
		
		return isAssetLvlBidding;
	}

	public void setIsAssetLvlBidding(Boolean isAssetLvlBidding) {
		this.isAssetLvlBidding = isAssetLvlBidding;
	}

	public Boolean getAllowProxyBidding() {
		return allowProxyBidding;
	}

	public void setAllowProxyBidding(Boolean allowProxyBidding) {
		this.allowProxyBidding = allowProxyBidding;
	}
	

	
	public String getPreAuction() {
		return preAuction;
	}

	public void setPreAuction(String preAuction) {
		this.preAuction = preAuction;
	}

	public String getRegistrationFlow() {
		return registrationFlow;
	}

	public void setRegistrationFlow(String registrationFlow) {
		this.registrationFlow = registrationFlow;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTimeSelection() {
		return timeSelection;
	}

	public void setTimeSelection(String timeSelection) {
		this.timeSelection = timeSelection;
	}

	public String getStatusReg() {
		return statusReg;
	}

	public void setStatusReg(String statusReg) {
		this.statusReg = statusReg;
	}

	public Timestamp getPropBidStartDT() {
		return propBidStartDT;
	}

	public void setPropBidStartDT(Timestamp propBidStartDT) {
		this.propBidStartDT = propBidStartDT;
	}

	public Timestamp getPropBidEndDT() {
		return propBidEndDT;
	}

	public void setPropBidEndDT(Timestamp propBidEndDT) {
		this.propBidEndDT = propBidEndDT;
	}

	public Timestamp getBiddingStartTime() {
		return biddingStartTime;
	}

	public void setBiddingStartTime(Timestamp biddingStartTime) {
		this.biddingStartTime = biddingStartTime;
	}

	public Timestamp getBiddingEndTime() {
		return biddingEndTime;
	}

	public void setBiddingEndTime(Timestamp biddingEndTime) {
		this.biddingEndTime = biddingEndTime;
	}

	public Timestamp getEventBiddingStartTime() {
		return eventBiddingStartTime;
	}

	public void setEventBiddingStartTime(Timestamp eventBiddingStartTime) {
		this.eventBiddingStartTime = eventBiddingStartTime;
	}

	public Timestamp getEventBiddingEndTime() {
		return eventBiddingEndTime;
	}

	public void setEventBiddingEndTime(Timestamp eventBiddingEndTime) {
		this.eventBiddingEndTime = eventBiddingEndTime;
	}

	public String getAuctionID() {
		return auctionID;
	}

	public void setAuctionID(String auctionID) {
		this.auctionID = auctionID;
	}

	public String getAuctionNumber() {
		return auctionNumber;
	}

	public void setAuctionNumber(String auctionNumber) {
		this.auctionNumber = auctionNumber;
	}

	public String getPropertyID() {
		return propertyID;
	}

	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}
	
}
