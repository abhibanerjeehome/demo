package com.redwood.rp.flaunt.bo;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
public class ContractInfo implements java.io.Serializable{
	
	private static final long serialVersionUID = 1892280002982982430L;

	private Long globalPropId;
	
	private Long propertyId;
	
	private Long auctionId;
	
	private String auctionNumber;
	
	private String propertyName;
	
	private String propertyAddress;
	
	private String propertyCity;
	
	private String propertyState;
	
	private String propertyZip;
	
	private String fullAddress;
	
	private String auctionDayStatus;
	
	private Double winningBid;
	
	private String venueCode;
	
	private String itemName;
	
	private Date purchasedOnDate;
	
	private String purchasedOn;
	
	private String contractFileName;
	
	private String contractDocLink;
	
	private Date contractReceivedDate;
	
	private String contractReceived;
	
	private Integer contractReceivedStatus;
	
	private Integer contractStatus;

	

	public String getContractDocLink() {
		return contractDocLink;
	}

	public void setContractDocLink(String contractDocLink) {
		this.contractDocLink = contractDocLink;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public Long getGlobalPropId() {
		return globalPropId;
	}

	public void setGlobalPropId(Long globalPropId) {
		this.globalPropId = globalPropId;
	}

	public Long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

	public Long getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(Long auctionId) {
		this.auctionId = auctionId;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	@JsonIgnore
	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	@JsonIgnore
	public String getPropertyCity() {
		return propertyCity;
	}

	public void setPropertyCity(String propertyCity) {
		this.propertyCity = propertyCity;
	}

	@JsonIgnore
	public String getPropertyState() {
		return propertyState;
	}

	public void setPropertyState(String propertyState) {
		this.propertyState = propertyState;
	}

	@JsonIgnore
	public String getPropertyZip() {
		return propertyZip;
	}

	public void setPropertyZip(String propertyZip) {
		this.propertyZip = propertyZip;
	}

	@JsonIgnore
	public String getAuctionDayStatus() {
		return auctionDayStatus;
	}

	public void setAuctionDayStatus(String auctionDayStatus) {
		this.auctionDayStatus = auctionDayStatus;
	}

	public Double getWinningBid() {
		return winningBid;
	}

	public void setWinningBid(Double winningBid) {
		this.winningBid = winningBid;
	}

	@JsonIgnore
	public String getVenueCode() {
		return venueCode;
	}

	public void setVenueCode(String venueCode) {
		this.venueCode = venueCode;
	}

	@JsonIgnore
	public String getContractFileName() {
		return contractFileName;
	}

	public void setContractFileName(String contractFileName) {
		this.contractFileName = contractFileName;
	}

	@JsonIgnore
	public Date getPurchasedOnDate() {
		return purchasedOnDate;
	}

	public void setPurchasedOnDate(Date purchasedOnDate) {
		this.purchasedOnDate = purchasedOnDate;
	}

	public String getPurchasedOn() {
		return purchasedOn;
	}

	public void setPurchasedOn(String purchasedOn) {
		this.purchasedOn = purchasedOn;
	}

	public String getContractReceived() {
		return contractReceived;
	}

	@JsonIgnore
	public Date getContractReceivedDate() {
		return contractReceivedDate;
	}
	
	public void setContractReceived(String contractReceived) {
		this.contractReceived = contractReceived;
	}

	public void setContractReceivedDate(Date contractReceivedDate) {
		this.contractReceivedDate = contractReceivedDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getContractReceivedStatus() {
		return contractReceivedStatus;
	}

	public void setContractReceivedStatus(Integer contractReceivedStatus) {
		this.contractReceivedStatus = contractReceivedStatus;
	}

	public Integer getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(Integer contractStatus) {
		this.contractStatus = contractStatus;
	}

	@JsonIgnore
	public String getAuctionNumber() {
		return auctionNumber;
	}

	public void setAuctionNumber(String auctionNumber) {
		this.auctionNumber = auctionNumber;
	}
	
}
