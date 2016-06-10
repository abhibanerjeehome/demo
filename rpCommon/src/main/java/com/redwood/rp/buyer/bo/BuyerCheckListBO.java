package com.redwood.rp.buyer.bo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.redwood.rp.core.util.BooleanUtil;
import com.redwood.rp.core.util.DateUtil;

public class BuyerCheckListBO  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//bidderID bidderID,ValidateFunds validateFundsBidderReg,signFirstName signFirstName,approve_online_bidding approveOnlineBidding
	
	private Integer bidCount;
	private String venueStatusReg;
	private String isRegisteredForAsset;
	private boolean isAssetLvlBidding;
	private String isAssetFundValidated;
	private String bidAmount;
	private Timestamp bidDateTime;
	private String paymentType;
	private String paymentStatus;
	private String preAuctionBidAmtResi;
	private String lastAlteredResi;
	private String statusResi;
	private String proxyBidAmountResi;
	private String validateFundsBidderReg;
	private String signFirstName;
	private String approveOnlineBidding;
	private String bidderID;
	private Timestamp bidStartTime;
	private Timestamp bidEndTime;
	private String offerType;
	private String userId;
	private String registration_flow;
	private String preAuction;
	private Boolean allowProxyBidding;

	
	
	
	
	

	public Boolean getAllowProxyBidding() {
		return allowProxyBidding;
	}

	public void setAllowProxyBidding(Boolean allowProxyBidding) {
		this.allowProxyBidding = allowProxyBidding;
	}
	

	
	public String getPreAuction() {
		return preAuction;
	}

	public void setPreAuction(String preAuction) {
		this.preAuction = preAuction;
	}

	public String getRegistration_flow() {
		return registration_flow;
	}

	public void setRegistration_flow(String registration_flow) {
		this.registration_flow = registration_flow;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public Timestamp getBidStartTime() {
		return bidStartTime;
	}

	public void setBidStartTime(Timestamp bidStartTime) {
		this.bidStartTime = bidStartTime;
	}

	public Timestamp getBidEndTime() {
		return bidEndTime;
	}

	public void setBidEndTime(Timestamp bidEndTime) {
		this.bidEndTime = bidEndTime;
	}

	public String getValidateFundsBidderReg() {
		return validateFundsBidderReg;
	}

	public void setValidateFundsBidderReg(String validateFundsBidderReg) {
		this.validateFundsBidderReg = validateFundsBidderReg;
	}

	public String getSignFirstName() {
		return signFirstName;
	}

	public void setSignFirstName(String signFirstName) {
		this.signFirstName = signFirstName;
	}

	public String getApproveOnlineBidding() {
		return approveOnlineBidding;
	}

	public void setApproveOnlineBidding(String approveOnlineBidding) {
		this.approveOnlineBidding = approveOnlineBidding;
	}

	public String getBidderID() {
		return bidderID;
	}

	public void setBidderID(String bidderID) {
		this.bidderID = bidderID;
	}

	public String getPreAuctionBidAmtResi() {
		return preAuctionBidAmtResi;
	}

	public void setPreAuctionBidAmtResi(String preAuctionBidAmtResi) {
		this.preAuctionBidAmtResi = preAuctionBidAmtResi;
	}

	public String getLastAlteredResi() {
		return lastAlteredResi;
	}

	public void setLastAlteredResi(String lastAlteredResi) {
		this.lastAlteredResi = lastAlteredResi;
	}

	public String getStatusResi() {
		return statusResi;
	}

	public void setStatusResi(String statusResi) {
		this.statusResi = statusResi;
	}

	public String getProxyBidAmountResi() {
		return proxyBidAmountResi;
	}

	public void setProxyBidAmountResi(String proxyBidAmountResi) {
		this.proxyBidAmountResi = proxyBidAmountResi;
	}

	public String getVenueStatusReg() {
		return venueStatusReg;
	}

	public void setVenueStatusReg(String venueStatusReg) {
		this.venueStatusReg = venueStatusReg;
	}

	public boolean getIsRegisteredForAsset() {
		return BooleanUtil.toBoolean(isRegisteredForAsset);
	}

	public void setIsRegisteredForAsset(String isRegisteredForAsset) {
		this.isRegisteredForAsset = isRegisteredForAsset;
	}

	public boolean getIsAssetLvlBidding() {
		
		return isAssetLvlBidding;
	}

	public void setIsAssetLvlBidding(boolean isAssetLvlBidding) {
		this.isAssetLvlBidding = isAssetLvlBidding;
	}

	public boolean getIsAssetFundValidated() {
		return BooleanUtil.toBoolean(isAssetFundValidated);
	}

	public void setIsAssetFundValidated(String isAssetFundValidated) {
		this.isAssetFundValidated = isAssetFundValidated;
	}

	public String getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(String bidAmount) {
		this.bidAmount= bidAmount;
	}

	public Timestamp getBidDateTime() {
		return bidDateTime;
	}

	public void setBidDateTime(Object bidDateTime)  {
		this.bidDateTime=DateUtil.objectToTimestampConverter(bidDateTime);	
		
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Integer getBidCount() {
		return bidCount;
	}

	public void setBidCount(Integer bidCount) {
		this.bidCount = bidCount;
	}
	
	public void setBidCount(Object bidCount) {
		if(bidCount != null)
			this.bidCount = new Integer (bidCount.toString());
	}
}
