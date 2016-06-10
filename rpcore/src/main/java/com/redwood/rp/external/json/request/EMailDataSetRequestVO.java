package com.redwood.rp.external.json.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;




/**
 * The Class EMailDataSetRequestVO.
 * @author rpandya
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class EMailDataSetRequestVO implements Serializable{

	/** The Constant serialVersionUID. */
	
	
	private static final long serialVersionUID = 1L;

	private String venueCode;
	private String propertyAddress;
	private String propertyCity;
	private String propertyCounty;
	private String propertyState;
	private String propertyZip;
	private String currentBrokerAgent;
	private String brokerPhone;
	private String brokerEmail;
	private String totalRoomCount;
	private String bedrooms;
	private String baths;
	private String homeSquareFootage;
	private String yearBuilt;
	private String smallImage;
	private String auctionNumber;
	private String propertiesImage;
	private String auctionID;
	private String propertyID;
	private String globalPropertyID;
	
	public EMailDataSetRequestVO(){}
	
	
	public EMailDataSetRequestVO(String venueCode, String propertyAddress,
			String propertyCity, String propertyCounty, String propertyState,
			String propertyZip, String currentBrokerAgent, String brokerPhone,
			String brokerEmail, String totalRoomCount, String bedrooms,
			String baths, String homeSquareFootage, String yearBuilt,
			String smallImage, String auctionNumber, String propertiesImage,
			String auctionID, String propertyID, String globalPropertyID) {
		super();
		this.venueCode = venueCode;
		this.propertyAddress = propertyAddress;
		this.propertyCity = propertyCity;
		this.propertyCounty = propertyCounty;
		this.propertyState = propertyState;
		this.propertyZip = propertyZip;
		this.currentBrokerAgent = currentBrokerAgent;
		this.brokerPhone = brokerPhone;
		this.brokerEmail = brokerEmail;
		this.totalRoomCount = totalRoomCount;
		this.bedrooms = bedrooms;
		this.baths = baths;
		this.homeSquareFootage = homeSquareFootage;
		this.yearBuilt = yearBuilt;
		this.smallImage = smallImage;
		this.auctionNumber = auctionNumber;
		this.propertiesImage = propertiesImage;
		this.auctionID = auctionID;
		this.propertyID = propertyID;
		this.globalPropertyID = globalPropertyID;
	}

	@JsonProperty("venue_code")
	public String getVenueCode() {
		return venueCode;
	}
	
	@JsonProperty("venue_code")
	public void setVenueCode(String venueCode) {
		this.venueCode = venueCode;
	}
	
	@JsonProperty("property_address")
	public String getPropertyAddress() {
		return propertyAddress;
	}
	
	@JsonProperty("property_address")
	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}
	
	@JsonProperty("property_city")
	public String getPropertyCity() {
		return propertyCity;
	}
	
	@JsonProperty("property_city")
	public void setPropertyCity(String propertyCity) {
		this.propertyCity = propertyCity;
	}
	
	@JsonProperty("property_county")
	public String getPropertyCounty() {
		return propertyCounty;
	}
	
	@JsonProperty("property_county")
	public void setPropertyCounty(String propertyCounty) {
		this.propertyCounty = propertyCounty;
	}
	
	@JsonProperty("property_state")
	public String getPropertyState() {
		return propertyState;
	}
	
	@JsonProperty("property_state")
	public void setPropertyState(String propertyState) {
		this.propertyState = propertyState;
	}
	
	@JsonProperty("property_zip")
	public String getPropertyZip() {
		return propertyZip;
	}
	
	@JsonProperty("property_zip")
	public void setPropertyZip(String propertyZip) {
		this.propertyZip = propertyZip;
	}
	
	@JsonProperty("current_broker_agent")
	public String getCurrentBrokerAgent() {
		return currentBrokerAgent;
	}
	
	@JsonProperty("current_broker_agent")
	public void setCurrentBrokerAgent(String currentBrokerAgent) {
		this.currentBrokerAgent = currentBrokerAgent;
	}
	
	@JsonProperty("broker_phone")
	public String getBrokerPhone() {
		return brokerPhone;
	}
	
	@JsonProperty("broker_phone")
	public void setBrokerPhone(String brokerPhone) {
		this.brokerPhone = brokerPhone;
	}
	
	@JsonProperty("broker_email")
	public String getBrokerEmail() {
		return brokerEmail;
	}
	
	@JsonProperty("broker_email")
	public void setBrokerEmail(String brokerEmail) {
		this.brokerEmail = brokerEmail;
	}
	
	@JsonProperty("total_room_count")
	public String getTotalRoomCount() {
		return totalRoomCount;
	}
	
	@JsonProperty("total_room_count")
	public void setTotalRoomCount(String totalRoomCount) {
		this.totalRoomCount = totalRoomCount;
	}
	
	@JsonProperty("bedrooms")
	public String getBedrooms() {
		return bedrooms;
	}
	
	@JsonProperty("bedrooms")
	public void setBedrooms(String bedrooms) {
		this.bedrooms = bedrooms;
	}
	
	@JsonProperty("baths")
	public String getBaths() {
		return baths;
	}
	
	@JsonProperty("baths")
	public void setBaths(String baths) {
		this.baths = baths;
	}
	
	@JsonProperty("home_square_footage")
	public String getHomeSquareFootage() {
		return homeSquareFootage;
	}
	
	@JsonProperty("home_square_footage")
	public void setHomeSquareFootage(String homeSquareFootage) {
		this.homeSquareFootage = homeSquareFootage;
	}
	
	@JsonProperty("year_built")
	public String getYearBuilt() {
		return yearBuilt;
	}
	
	@JsonProperty("year_built")
	public void setYearBuilt(String yearBuilt) {
		this.yearBuilt = yearBuilt;
	}
	
	@JsonProperty("small_image")
	public String getSmallImage() {
		return smallImage;
	}
	
	@JsonProperty("small_image")
	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}
	
	@JsonProperty("auction_number")
	public String getAuctionNumber() {
		return auctionNumber;
	}
	
	@JsonProperty("auction_number")
	public void setAuctionNumber(String auctionNumber) {
		this.auctionNumber = auctionNumber;
	}
	
	@JsonProperty("properties_image")
	public String getPropertiesImage() {
		return propertiesImage;
	}
	
	@JsonProperty("properties_image")
	public void setPropertiesImage(String propertiesImage) {
		this.propertiesImage = propertiesImage;
	}
	
	@JsonProperty("auction_id")
	public String getAuctionID() {
		return auctionID;
	}
	
	@JsonProperty("auction_id")
	public void setAuctionID(String auctionID) {
		this.auctionID = auctionID;
	}
	
	@JsonProperty("property_id")
	public String getPropertyID() {
		return propertyID;
	}
	
	@JsonProperty("property_id")
	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}
	
	@JsonProperty("global_property_id")
	public String getGlobalPropertyID() {
		return globalPropertyID;
	}
	
	@JsonProperty("global_property_id")
	public void setGlobalPropertyID(String globalPropertyID) {
		this.globalPropertyID = globalPropertyID;
	}
}
