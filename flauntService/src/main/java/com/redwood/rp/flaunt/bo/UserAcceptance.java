package com.redwood.rp.flaunt.bo;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserAcceptance {

	private Long id;
	private Long userId;
	private String clientIp;
	private Long auctionId;
	private String auctionNumber;
	private Long globalPropertyId;
	private Long propertyId;
	private boolean isAccepted; 
	
	//"NDA", "DISCLAIMER"
	private String docType;
	private Long createdBy;
	private Long updatedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonProperty("user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@JsonProperty("client_ip")
	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	@JsonProperty("auction_id")
	public Long getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(Long auctionId) {
		this.auctionId = auctionId;
	}

	@JsonProperty("auction_number")
	public String getAuctionNumber() {
		return auctionNumber;
	}

	public void setAuctionNumber(String auctionNumber) {
		this.auctionNumber = auctionNumber;
	}

	@JsonProperty("property_id")
	public Long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

	@JsonProperty("global_property_id")
	public Long getGlobalPropertyId() {
		return globalPropertyId;
	}

	public void setGlobalPropertyId(Long globalPropertyId) {
		this.globalPropertyId = globalPropertyId;
	}

	@JsonProperty("is_accepted")
	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	@JsonProperty("doc_type")
	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}
	
}
