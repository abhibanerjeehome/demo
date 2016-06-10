package com.redwood.rp.flaunt.bo;

import java.io.Serializable;

public class SavedSearchBO implements Serializable{

	private static final long serialVersionUID = 1L;

	public SavedSearchBO() {
		// TODO Auto-generated constructor stub
	}

	private String emailSettingId;
	private String auctionType;
	private String userId; 
	private String actionType;
	private String country;
	private String state;
	private String county;
	private String city; 
	private String zipcode;
	private String resiPrevValuedTo;
	private String resiSqft;
	private String geoRegion;
	private String collateralType;
	private String cnt;
	private String resiPrevValuedFrom;
	private String maxbed;
	private String maxbath;		

	private String settingName;
	private String settingType;
	private String notes;
	private String notificationOff;
	private String assetTypes;
	private String propertyType;
	private String resiAddress;
	private String miles;
	private String resiBed;
	private String resiBath;
	private String resiYear;
	private String defaultEmail;
	private String additionalEmail;
	private String emailFormat;
	private String loanStatus;
	private String resiSqftFrom;
	private String resiSqftTo;
	private String keywords;
	private String yearBuiltMin;
	private String yearBuiltMax;
	private String auctionStartFrom;
	private String auctionStartTo;
	private String auctionEndFrom;
	private String auctionEndTo;	
	private String userAggregateType;
	private String propertyAggregateType;


	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getYearBuiltMin() {
		return yearBuiltMin;
	}
	public void setYearBuiltMin(String yearBuiltMin) {
		this.yearBuiltMin = yearBuiltMin;
	}
	public String getYearBuiltMax() {
		return yearBuiltMax;
	}
	public void setYearBuiltMax(String yearBuiltMax) {
		this.yearBuiltMax = yearBuiltMax;
	}
	public String getAuctionStartFrom() {
		return auctionStartFrom;
	}
	public void setAuctionStartFrom(String auctionStartFrom) {
		this.auctionStartFrom = auctionStartFrom;
	}
	public String getAuctionStartTo() {
		return auctionStartTo;
	}
	public void setAuctionStartTo(String auctionStartTo) {
		this.auctionStartTo = auctionStartTo;
	}
	public String getAuctionEndFrom() {
		return auctionEndFrom;
	}
	public void setAuctionEndFrom(String auctionEndFrom) {
		this.auctionEndFrom = auctionEndFrom;
	}
	public String getAuctionEndTo() {
		return auctionEndTo;
	}
	public void setAuctionEndTo(String auctionEndTo) {
		this.auctionEndTo = auctionEndTo;
	}
	public String getEmailSettingId() {
		return emailSettingId;
	}
	public void setEmailSettingId(String emailSetting_id) {
		this.emailSettingId = emailSetting_id;
	}

	public String getAuctionType() {
		return auctionType;
	}

	public void setAuctionType(String auctionType) {
		this.auctionType = auctionType;
	}

	public String getSettingName() {
		return settingName;
	}
	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}

	public String getSettingType() {
		return settingType;
	}
	public void setSettingType(String settingType) {
		this.settingType = settingType;
	}

	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotificationOff() {
		return notificationOff;
	}
	public void setNotificationOff(String notificationOff) {
		this.notificationOff = notificationOff;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAssetTypes() {
		return assetTypes;
	}
	public void setAssetTypes(String assetTypes) {
		this.assetTypes = assetTypes;
	}

	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getResiAddress() {
		return resiAddress;
	}
	public void setResiAddress(String resiAddress) {
		this.resiAddress = resiAddress;
	}

	public String getMiles() {
		return miles;
	}
	public void setMiles(String miles) {
		this.miles = miles;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getResiPrevValuedTo() {
		return resiPrevValuedTo;
	}
	public void setResiPrevValuedTo(String resiPrevValuedTo) {
		this.resiPrevValuedTo = resiPrevValuedTo;
	}

	public String getResiSqft() {
		return resiSqft;
	}
	public void setResiSqft(String resiSqft) {
		this.resiSqft = resiSqft;
	}

	public String getResiBed() {
		return resiBed;
	}
	public void setResiBed(String resiBed) {
		this.resiBed = resiBed;
	}

	public String getResiBath() {
		return resiBath;
	}
	public void setResiBath(String resiBath) {
		this.resiBath = resiBath;
	}

	public String getResiYear() {
		return resiYear;
	}
	public void setResiYear(String resiYear) {
		this.resiYear = resiYear;
	}

	public String getDefaultEmail() {
		return defaultEmail;
	}
	public void setDefaultEmail(String defaultEmail) {
		this.defaultEmail = defaultEmail;
	}

	public String getAdditionalEmail() {
		return additionalEmail;
	}
	public void setAdditionalEmail(String additionalEmail) {
		this.additionalEmail = additionalEmail;
	}

	public String getEmailFormat() {
		return emailFormat;
	}
	public void setEmailFormat(String emailFormat) {
		this.emailFormat = emailFormat;
	}

	public String getGeoRegion() {
		return geoRegion;
	}
	public void setGeoRegion(String geoRegion) {
		this.geoRegion = geoRegion;
	}

	public String getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public String getCollateralType() {
		return collateralType;
	}
	public void setCollateralType(String collateralType) {
		this.collateralType = collateralType;
	}

	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public String getResiSqftFrom() {
		return resiSqftFrom;
	}
	public void setResiSqftFrom(String resiSqftFrom) {
		this.resiSqftFrom = resiSqftFrom;
	}

	public String getResiSqftTo() {
		return resiSqftTo;
	}
	public void setResiSqftTo(String resiSqftTo) {
		this.resiSqftTo = resiSqftTo;
	}

	public String getResiPrevValuedFrom() {
		return resiPrevValuedFrom;
	}
	public void setResiPrevValuedFrom(String resiPrevValuedFrom) {
		this.resiPrevValuedFrom = resiPrevValuedFrom;
	}

	public String getMaxbed() {
		return maxbed;
	}
	public void setMaxbed(String maxbed) {
		this.maxbed = maxbed;
	}

	public String getMaxbath() {
		return maxbath;
	}
	public void setMaxbath(String maxbath) {
		this.maxbath = maxbath;
	}

	public String getUserAggregateType() {
		return userAggregateType;
	}
	public void setUserAggregateType(String userAggregateType) {
		this.userAggregateType = userAggregateType;
	}
	
	public String getPropertyAggregateType() {
		return propertyAggregateType;
	}
	public void setPropertyAggregateType(String propertyAggregateType) {
		this.propertyAggregateType = propertyAggregateType;
	}


}
