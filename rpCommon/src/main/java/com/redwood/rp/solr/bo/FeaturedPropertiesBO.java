package com.redwood.rp.solr.bo;

import java.io.Serializable;

public class FeaturedPropertiesBO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	String thumbImage = null;
	String isLuxuryEvent = null;
	String propertyId = null;
	String productType=null;
	String auctionID = null;
	String propertyAddress=null;
	String propertyCity=null;
	String propertyState=null;
	String startingBid=null;
	String auctionType=null;
	String globalPropID=null;
	String propertyZip=null;
	
	public String getThumbImage() {
		return thumbImage;
	}
	public void setThumbImage(String thumbImage) {
		this.thumbImage = thumbImage;
	}
	public String getIsLuxuryEvent() {
		return isLuxuryEvent;
	}
	public void setIsLuxuryEvent(String isLuxuryEvent) {
		this.isLuxuryEvent = isLuxuryEvent;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getAuctionID() {
		return auctionID;
	}
	public void setAuctionID(String auctionID) {
		this.auctionID = auctionID;
	}
	public String getPropertyAddress() {
		return propertyAddress;
	}
	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}
	public String getPropertyCity() {
		return propertyCity;
	}
	public void setPropertyCity(String propertyCity) {
		this.propertyCity = propertyCity;
	}
	public String getPropertyState() {
		return propertyState;
	}
	public void setPropertyState(String propertyState) {
		this.propertyState = propertyState;
	}
	public String getStartingBid() {
		return startingBid;
	}
	public void setStartingBid(String startingBid) {
		this.startingBid = startingBid;
	}
	public String getAuctionType() {
		return auctionType;
	}
	public void setAuctionType(String auctionType) {
		this.auctionType = auctionType;
	}
	public String getGlobalPropID() {
		return globalPropID;
	}
	public void setGlobalPropID(String globalPropID) {
		this.globalPropID = globalPropID;
	}
	public String getPropertyZip() {
		return propertyZip;
	}
	public void setPropertyZip(String propertyZip) {
		this.propertyZip = propertyZip;
	}
	
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		/**
		 * 	String thumbImage = null;
	String isLuxuryEvent = null;
	String propertyId = null;
	String productType=null;
	String auctionID = null;
	String propertyAddress=null;
	String propertyCity=null;
	String propertyState=null;
	String startingBid=null;
	String auctionType=null;
	String globalPropID=null;
	String propertyZip=null;
		 */
		stringBuilder.append(" thumbImage "+this.thumbImage);
		stringBuilder.append(" isLuxuryEvent "+this.isLuxuryEvent);
		stringBuilder.append(" propertyId "+this.propertyId);
		stringBuilder.append(" productType "+this.productType);
		stringBuilder.append(" auctionID "+this.auctionID);
		stringBuilder.append(" propertyAddress "+this.propertyAddress);
		stringBuilder.append(" propertyCity "+this.propertyCity);
		stringBuilder.append(" propertyState "+this.propertyState);
		stringBuilder.append(" startingBid "+this.startingBid);
		stringBuilder.append(" auctionType "+this.auctionType);
		stringBuilder.append(" globalPropID "+this.globalPropID);
		stringBuilder.append(" propertyZip "+this.propertyZip);
		
		return stringBuilder.toString();
	}
}
