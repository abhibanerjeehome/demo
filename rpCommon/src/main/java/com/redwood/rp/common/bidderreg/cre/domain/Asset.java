package com.redwood.rp.common.bidderreg.cre.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Basic asset information.
 *
 */
public class Asset implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long assetId;
	
	private Event event;
	
	private Long globalAssetId;
	
	private String venueCode;
	
	private Long venueId;
	
	private Double depositAmount;
	
	private Date biddingStartTime;
	
	private Date biddingEndTime;
	
	private Double startingBid;

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Long getGlobalAssetId() {
		return globalAssetId;
	}

	public void setGlobalAssetId(Long globalAssetId) {
		this.globalAssetId = globalAssetId;
	}

	public String getVenueCode() {
		return venueCode;
	}

	public void setVenueCode(String venueCode) {
		this.venueCode = venueCode;
	}

	public Long getVenueId() {
		return venueId;
	}

	public void setVenueId(Long venueId) {
		this.venueId = venueId;
	}

	public Double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
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

	public Double getStartingBid() {
		return startingBid;
	}

	public void setStartingBid(Double startingBid) {
		this.startingBid = startingBid;
	}
	
}
