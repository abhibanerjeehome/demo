package com.redwood.rp.solr.bo;

import java.io.Serializable;

public class PropertyInfoBO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	String globalPropertyId = null;
	String propertyId = null;
	String auctionType = null;
	String auctionID = null;
	String auctionNumber=null;
	String trusteeSale=null;
	String productType=null;
	String propertyType=null;
	String propertyState=null;
	String propertyOccupancyStatus=null;
	String poolNumber=null;
	String venueType=null;
	String venueId=null;
	String collectionName=null;
	boolean dueDiligenceEnabled;
	boolean isTestEvent;

	public String getGlobalPropertyId() {
		return globalPropertyId;
	}
	public void setGlobalPropertyId(String globalPropertyId) {
		this.globalPropertyId = globalPropertyId;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public String getAuctionType() {
		return auctionType;
	}
	public void setAuctionType(String auctionType) {
		this.auctionType = auctionType;
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
	public String getTrusteeSale() {
		return trusteeSale;
	}
	public void setTrusteeSale(String trusteeSale) {
		this.trusteeSale = trusteeSale;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public String getPropertyState() {
		return propertyState;
	}
	public void setPropertyState(String propertyState) {
		this.propertyState = propertyState;
	}
	public String getPropertyOccupancyStatus() {
		return propertyOccupancyStatus;
	}
	public void setPropertyOccupancyStatus(String propertyOccupancyStatus) {
		this.propertyOccupancyStatus = propertyOccupancyStatus;
	}
	public String getPoolNumber() {
		return poolNumber;
	}
	public void setPoolNumber(String poolNumber) {
		this.poolNumber = poolNumber;
	}
	public String getVenueType() {
		return venueType;
	}
	public void setVenueType(String venueType) {
		this.venueType = venueType;
	}
	public String getVenueId() {
		return venueId;
	}
	public void setVenueId(String venueId) {
		this.venueId = venueId;
	}
	public String getCollectionName() {
		return collectionName;
	}
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
	public boolean isDueDiligenceEnabled() {
		return dueDiligenceEnabled;
	}
	public void setDueDiligenceEnabled(boolean dueDiligence) {
		this.dueDiligenceEnabled = dueDiligence;
	}
	public boolean isTestEvent() {
		return isTestEvent;
	}
	public void setTestEvent(boolean isTestEvent) {
		this.isTestEvent = isTestEvent;
	}
}
