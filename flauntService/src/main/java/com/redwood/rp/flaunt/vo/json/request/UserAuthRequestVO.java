package com.redwood.rp.flaunt.vo.json.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserAuthRequestVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public UserAuthRequestVO() {
		// TODO Auto-generated constructor stub
	}
	public UserAuthRequestVO(String userId, String approved,
			String auctionId, String clientIp, String propertyId,
			int status, String id) {

		this.approved=approved;
		this.id=id;
		this.auctionId=auctionId;
		this.clientIp=clientIp;
		this.propertyId=propertyId;
		this.status=status;
		this.userId=userId;
	}
	private String id;
	private String userId;
	private String clientIp;
	private String auctionId;
	private int status;
	private String propertyId;
	private String approved;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JsonProperty("user_id")
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
	@JsonProperty("auction_id")
	public String getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}
	@JsonProperty("status")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@JsonProperty("property_id")
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

	

}
