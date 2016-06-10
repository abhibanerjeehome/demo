package com.redwood.rp.common.bidderreg.cre.domain;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long auctionId;
	
	private String auctionNumber;
	
	private String auctionType;
	
	private Date biddingStartTime;
	
	private Date biddingEndTime;
	
	private Boolean isTestAuction;
	
	private Boolean isDemoAuction;   
	
	
	public Boolean getIsTestAuction() {
		return isTestAuction;
	}

	public void setIsTestAuction(Boolean isTestAuction) {
		this.isTestAuction = isTestAuction;
	}

	public Boolean getIsDemoAuction() {
		return isDemoAuction;
	}

	public void setIsDemoAuction(Boolean isDemoAuction) {
		this.isDemoAuction = isDemoAuction;
	}

	public Long getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(Long auctionId) {
		this.auctionId = auctionId;
	}

	public String getAuctionNumber() {
		return auctionNumber;
	}

	public void setAuctionNumber(String auctionNumber) {
		this.auctionNumber = auctionNumber;
	}

	public String getAuctionType() {
		return auctionType;
	}

	public void setAuctionType(String auctionType) {
		this.auctionType = auctionType;
	}

	public Date getBiddingStartTime() {
		return biddingStartTime;
	}

	public void setBiddingStartTime(Date biddingStartTime) {
		this.biddingStartTime = biddingStartTime;
	}

	public Date getBiddingEndTime() {
		return biddingEndTime;
	}

	public void setBiddingEndTime(Date biddingEndTime) {
		this.biddingEndTime = biddingEndTime;
	}
	
}
