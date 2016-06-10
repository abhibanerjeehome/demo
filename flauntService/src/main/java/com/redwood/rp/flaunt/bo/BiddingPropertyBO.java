package com.redwood.rp.flaunt.bo;

import org.codehaus.jackson.annotate.JsonProperty;

public class BiddingPropertyBO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private Long propertyId;

	private String biddingType;

	@JsonProperty("property_id")
	public Long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

	@JsonProperty("bidding_type")
	public String getBiddingType() {
		return biddingType;
	}

	public void setBiddingType(String biddingType) {
		this.biddingType = biddingType;
	}
	 

}
