package com.redwood.rp.common.user.bo;

import java.io.Serializable;
import java.util.Date;

public class FavPropertiesBO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2207865808012633219L;

	public FavPropertiesBO() {
		// TODO Auto-generated constructor stub
	}
	
	private String id;
	private String userId;
	private String propertyId;
	private String globalPropertyId;
	private String status;
	private Date postedDate;
	private String auctionId;
	private String bidderRegStatus;
	private String productType;
	
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getBidderRegStatus() {
		return bidderRegStatus;
	}
	public void setBidderRegStatus(String bidderRegStatus) {
		this.bidderRegStatus = bidderRegStatus;
	}	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	
	public String getGlobalPropertyId() {
		return globalPropertyId;
	}
	public void setGlobalPropertyId(String globalPropertyId) {
		this.globalPropertyId = globalPropertyId;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
	
	public String getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}


}
