package com.redwood.rp.common.buyer.service.impl;

import static com.redwood.rp.rule.constant.BuyerCheckListConstant.BUYER_CHECKLIST_ALREADY_REGISTERED_FOR_AUCTION;
import static com.redwood.rp.rule.constant.BuyerCheckListConstant.BUYER_CHECKLIST_CASHIERCHECK_PAYMENT;
import static com.redwood.rp.rule.constant.BuyerCheckListConstant.BUYER_CHECKLIST_COMPLETE_BID_FORM;
import static com.redwood.rp.rule.constant.BuyerCheckListConstant.BUYER_CHECKLIST_COMPLETE_REGISTRATION;
import static com.redwood.rp.rule.constant.BuyerCheckListConstant.BUYER_CHECKLIST_CREDITCARD_PAYMENT;
import static com.redwood.rp.rule.constant.BuyerCheckListConstant.BUYER_CHECKLIST_PENDING_REGISTRATION_FOR_AUCTION;
import static com.redwood.rp.rule.constant.BuyerCheckListConstant.BUYER_CHECKLIST_REGISTER_FOR_AUCTION;
import static com.redwood.rp.rule.constant.BuyerCheckListConstant.BUYER_CHECKLIST_REGISTRATION_NOT_APPROVED;
import static com.redwood.rp.rule.constant.BuyerCheckListConstant.BUYER_CHECKLIST_VENUE_NOT_AVAILABLE;
import static com.redwood.rp.rule.constant.BuyerCheckListConstant.BUYER_CHECKLIST_WIRETRANSFER_PAYMENT;
import static com.redwood.rp.rule.constant.BuyerCheckListConstant.GLOBALASSET_AUCTIONTYPE_COMMERCIAL;
import static com.redwood.rp.rule.constant.BuyerCheckListConstant.GLOBALASSET_AUCTIONTYPE_NOTES;
import static com.redwood.rp.rule.constant.BuyerCheckListConstant.GLOBALASSET_AUCTIONTYPE_RESI;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redwood.rp.core.vo.generic.ACPRequest;
import com.redwood.rp.core.vo.generic.ACPResponse;
import com.redwood.rp.genericquery.vo.generic.GenericQueryRequest;
import com.redwood.rp.buyer.bo.BuyerCheckListBO;
import com.redwood.rp.buyer.vo.BuyerCheckListVO;
import com.redwood.rp.common.buyer.service.BuyerCheckListService;
import com.redwood.rp.common.generic.service.GenericService;
import com.redwood.rp.common.helper.ACPCommonHelper;
import com.redwood.rp.core.constant.RequestType;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.util.DateUtil;
import com.redwood.rp.core.util.StringUtil;
import com.redwood.rp.property.constant.ServiceNameMapping;
import com.redwood.rp.property.dto.BuyerCheckListDTO;
import com.redwood.rp.rule.constant.BuyerCheckListConstant;

@Named
public class BuyerCheckListServiceImpl implements BuyerCheckListService {

private static Logger logger = LoggerFactory.getLogger(BuyerCheckListServiceImpl.class.getName());
	
	private ACPCommonHelper acpCommonHelper = new ACPCommonHelper();
	
	@Inject
	private GenericService genericService;
	

	
	
	public BuyerCheckListVO getBuyerCheckListDetails(BuyerCheckListDTO buyerCheckListDTO, String auctionType)  throws ServiceException{
		
		BuyerCheckListBO buyerCheckListBO=new BuyerCheckListBO();
		BuyerCheckListVO buyerCheckListVO=new BuyerCheckListVO();
		String userId =buyerCheckListDTO.getUserId();
		if(userId ==null)
			{ 
			 buyerCheckListVO.setLoginRequired("true");
			// return buyerCheckListVO;
			
 			}
		
		
		
		logger.debug("current UserId "+ userId );
		Map<String,String> resultMap=new HashMap<String,String>();

		String isAssetLvlBidding="0";
		
		/*if(globalSettingsRow!=null&&globalSettingsRow.get("assetLevel_bidding")!=null)
			isAssetLvlBidding=globalSettingsRow.get("assetLevel_bidding").toString();*/
		
		logger.debug("isAssetLvlBidding "+isAssetLvlBidding);
	
		
	
		
		
		Map<String,String> inputParamMap=new HashMap<String,String>();
		inputParamMap.put("AUCTION_ID", buyerCheckListDTO.getAuctionID().toString());
		inputParamMap.put("PROPERTY_ID", buyerCheckListDTO.getPropertyID().toString());
		inputParamMap.put("AUCTION_NUMBER", buyerCheckListDTO.getAuctionNumber().toString());
	
		inputParamMap.put("USER_ID", userId);
		
		buyerCheckListBO.setRegistration_flow(buyerCheckListDTO.getRegistrationFlow());
		buyerCheckListBO.setAllowProxyBidding(buyerCheckListDTO.getAllowProxyBidding());
		buyerCheckListBO.setPreAuction(buyerCheckListDTO.getPreAuction());
		buyerCheckListBO.setIsAssetLvlBidding(buyerCheckListDTO.getIsAssetLvlBidding());
		buyerCheckListBO.setUserId(userId);
		
		
		
		
		
		Map<String,Object> authorizationMap=new HashMap<String,Object>();
		if(GLOBALASSET_AUCTIONTYPE_COMMERCIAL.equalsIgnoreCase(auctionType) )
		{	
			if(userId!=null){
				authorizationMap=getAuthorizationDetails(inputParamMap,BuyerCheckListConstant.CHECK_FOR_COMMERCIAL_AUTHORISATION);
				buyerCheckListBO.setOfferType(GLOBALASSET_AUCTIONTYPE_COMMERCIAL);
				try{
						if("0".equals(buyerCheckListDTO.getStatusReg()))
						BeanUtils.copyProperty(buyerCheckListBO, "venueStatusReg", "1");
				}catch(Exception ex){
					//TODO: handle error for beanutils
					logger.error(ex.getMessage(), ex);
				}
			   }
		if("Venue".equals(buyerCheckListDTO.getTimeSelection())){
			buyerCheckListBO.setBidStartTime(buyerCheckListDTO.getPropBidStartDT());
			buyerCheckListBO.setBidEndTime(buyerCheckListDTO.getPropBidEndDT());
		}else {
			buyerCheckListBO.setBidStartTime(buyerCheckListDTO.getBiddingStartTime());
			buyerCheckListBO.setBidEndTime(buyerCheckListDTO.getBiddingEndTime());
				}
	
		
		}else if(GLOBALASSET_AUCTIONTYPE_NOTES.equalsIgnoreCase(auctionType))
		{	
			if(userId!=null){
			authorizationMap=getAuthorizationDetails(inputParamMap,BuyerCheckListConstant.CHECK_FOR_NOTES_AUTHORISATION);
			buyerCheckListBO.setOfferType(GLOBALASSET_AUCTIONTYPE_NOTES);
			}
			//bidAuthorizationMap=getBidAuthorization(paramMap,BuyerCheckListConstant.CHECK_FOR_NOTES_AUTHORISATION);
		
			if("Event".equals(buyerCheckListDTO.getTimeSelection())){
				buyerCheckListBO.setBidStartTime(buyerCheckListDTO.getBiddingStartTime());
				buyerCheckListBO.setBidEndTime(buyerCheckListDTO.getBiddingEndTime());
			}else {
				buyerCheckListBO.setBidStartTime(buyerCheckListDTO.getEventBiddingStartTime());
				buyerCheckListBO.setBidEndTime(buyerCheckListDTO.getEventBiddingEndTime());
					}
			
		
				
		}else if(GLOBALASSET_AUCTIONTYPE_RESI.equalsIgnoreCase(auctionType) && userId!=null)
		{	
			
			if(userId!=null){
		    authorizationMap=getAuthorizationDetails(inputParamMap,BuyerCheckListConstant.CHECK_FOR_RESI_AUTHORISATION);
			buyerCheckListBO.setOfferType(GLOBALASSET_AUCTIONTYPE_RESI);
			}
			
		    	if("Venue".equals(buyerCheckListDTO.getTimeSelection())){
				if(buyerCheckListDTO.getEventBiddingStartTime()!=null&&buyerCheckListDTO.getEventBiddingStartTime()!=null){
					buyerCheckListBO.setBidStartTime(buyerCheckListDTO.getEventBiddingStartTime());
					buyerCheckListBO.setBidEndTime(buyerCheckListDTO.getEventBiddingEndTime());
				}else {
					buyerCheckListBO.setBidStartTime(buyerCheckListDTO.getBiddingStartTime());
					buyerCheckListBO.setBidEndTime(buyerCheckListDTO.getBiddingEndTime());
						}
				}
		    else{
		    	
		    	Map<String,Object> venueDateMap= getVenueDates(buyerCheckListDTO.getAuctionNumber(),buyerCheckListDTO.getBiddingStartTime(),buyerCheckListDTO.getBiddingEndTime());
		    	buyerCheckListBO.setBidStartTime((java.sql.Timestamp)venueDateMap.get("biddingStartTime"));
		    	buyerCheckListBO.setBidEndTime((java.sql.Timestamp)venueDateMap.get("biddingEndTime"));
		    }	
	    
		    
		}
			
		if(authorizationMap!=null&&userId!=null){
			buyerCheckListBO.setIsRegisteredForAsset(authorizationMap.get("isRegisteredForAsset").toString());
			buyerCheckListBO.setIsAssetFundValidated(authorizationMap.get("isAssetFundValidated").toString());   
		    if(authorizationMap.get("paymentType")!=null)
			buyerCheckListBO.setPaymentType(authorizationMap.get("paymentType").toString());
		    if(authorizationMap.get("bidderID")!=null)
				buyerCheckListBO.setBidderID(authorizationMap.get("bidderID").toString());
		    if(authorizationMap.get("approveOnlineBidding")!=null)
				buyerCheckListBO.setApproveOnlineBidding(authorizationMap.get("approveOnlineBidding").toString());
			if(authorizationMap.get("paymentStatus")!=null)
			buyerCheckListBO.setPaymentStatus(authorizationMap.get("paymentStatus").toString());
			if(authorizationMap.get("bidAmount")!=null)
			buyerCheckListBO.setBidAmount(authorizationMap.get("bidAmount").toString());
			buyerCheckListBO.setBidDateTime(authorizationMap.get("bidDateTime"));
			buyerCheckListVO=getBidderStatus(buyerCheckListBO);
			buyerCheckListVO.setBidStartTime(buyerCheckListBO.getBidStartTime());
			buyerCheckListVO.setBidEndTime(buyerCheckListBO.getBidEndTime());	
			buyerCheckListVO.setLoginRequired("false");
		}	

		buyerCheckListVO.setBidStartTime(buyerCheckListBO.getBidStartTime());
		buyerCheckListVO.setBidEndTime(buyerCheckListBO.getBidEndTime());	
		return buyerCheckListVO;
	}
	
	
	public Map<String,Object> getAuthorizationDetails(Map<String,String> propertyDetailMap,String requestType)throws ServiceException{
		
		Map<String,Object> paramMap=new HashMap<String,Object>();
		String auctionId=propertyDetailMap.get("AUCTION_ID");
		String propertyId=propertyDetailMap.get("PROPERTY_ID");
		String auctionNumber=propertyDetailMap.get("AUCTION_NUMBER");
		String userId= propertyDetailMap.get("USER_ID");
		String paymentType=null;
		String bidderID=null;
		String approveOnlineBidding=null;
		
		
		
		
		String isRegisteredForAsset="false";
		String isAssetFundValidated="false";
		
			
		if(StringUtil.isNotBlank(userId))
		paramMap.put("USER_ID", userId);
		else
			return null;
		
		paramMap.put("AUCTION_ID",auctionId);
		paramMap.put("AUCTION_NUMBER",auctionNumber);
		paramMap.put("PROPERTY_ID",propertyId);
		
		
		
		
		//String requestType=BuyerCheckListConstant.CHECK_FOR_RESI_AUTHORISATION;
		
		
		GenericQueryRequest genericQueryRequest = acpCommonHelper.getGenericQueryRequest(requestType,paramMap);
		ACPRequest acpRequest=new ACPRequest(
				ServiceNameMapping.GET_GENERIC_SERVICE,RequestType.JSON,genericQueryRequest);
		
		ACPResponse acpResponse = genericService.getGenericService(acpRequest);
		List<Map<String, Object>>  authorisationList = (List<Map<String, Object>>) acpResponse.getPayload();
		if(authorisationList!=null&&authorisationList.size()>0)
			{		
			  Map<String,Object> authorisationRow=authorisationList.get(0);		
			 if(authorisationRow!=null){
				 if(authorisationRow.get("paymentStatus")!=null&&"1".equalsIgnoreCase(authorisationRow.get("paymentStatus").toString()))
				 isRegisteredForAsset="true";
				 if(authorisationRow.get("validateFunds")!=null&&"Approved".equalsIgnoreCase(authorisationRow.get("validateFunds").toString()))
					isAssetFundValidated="true";
				 paymentType=authorisationRow.get("paymentType")!=null?authorisationRow.get("paymentType").toString():null;
				 bidderID=authorisationRow.get("bidderID")!=null?authorisationRow.get("bidderID").toString():null;
				 approveOnlineBidding=authorisationRow.get("approveOnlineBidding")!=null?authorisationRow.get("approveOnlineBidding").toString():null; 
				 
			 }
			}
		
		Map<String,Object> returnMap=new HashMap<String,Object>();
		returnMap.put("isRegisteredForAsset",isRegisteredForAsset);
		returnMap.put("isAssetFundValidated",isAssetFundValidated);
		if(StringUtil.isNotBlank(paymentType))
		returnMap.put("paymentType",paymentType);
		if(StringUtil.isNotBlank(bidderID))
		returnMap.put("bidderID", bidderID);
		if(StringUtil.isNotBlank(approveOnlineBidding))
			returnMap.put("approveOnlineBidding", approveOnlineBidding);
		
		return returnMap;
	}

	
	
	public Map<String,Object> getVenueDates(String auctionNumber, Timestamp bidStartTime, Timestamp bidEndtime) throws ServiceException{
		
		Map<String,Object> resultMap=new HashMap<String,Object>();
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("AUCTION_NUMBER", auctionNumber);
		String requestType=BuyerCheckListConstant.GET_LIVE_VENUE_DATES;
		
		GenericQueryRequest genericQueryRequest = acpCommonHelper.getGenericQueryRequest(requestType,paramMap);
		ACPRequest acpRequest=new ACPRequest(
				ServiceNameMapping.GET_GENERIC_SERVICE,RequestType.JSON,genericQueryRequest);
		ACPResponse acpResponse = genericService.getGenericService(acpRequest);
		List<Map<String, Object>>  venueDates = (List<Map<String, Object>>) acpResponse.getPayload();
		Map<String,Object> venueDatesRow=null;
		if(venueDates!=null &&venueDates.size()>0)
			venueDatesRow=venueDates.get(0);
		java.sql.Timestamp liveVenueTimestamp=null;
		if(venueDatesRow!=null&& venueDatesRow.get("auctionDate")!=null &&venueDatesRow.get("auctionTime")!=null)
		{
			String venueDate=venueDatesRow.get("auctionDate")+" "+venueDatesRow.get("auctionTime");
				
			liveVenueTimestamp = DateUtil.objectToTimestampConverter(venueDate);
			
			if((liveVenueTimestamp.getTime()-bidStartTime.getTime())<0){
				
				resultMap.put("biddingStartTime",liveVenueTimestamp);
				
			}
		}
		
		
		requestType=BuyerCheckListConstant.GET_ONLINE_VENUE_DATES;
		
		genericQueryRequest = acpCommonHelper.getGenericQueryRequest(requestType,paramMap);
		acpRequest=new ACPRequest(
				ServiceNameMapping.GET_GENERIC_SERVICE,RequestType.JSON,genericQueryRequest);
		acpResponse = genericService.getGenericService(acpRequest);
		venueDates = (List<Map<String, Object>>) acpResponse.getPayload();
		venueDatesRow=null;
		Timestamp onlineVenueTimestamp=null;
		if(venueDates!=null &&venueDates.size()>0)
			venueDatesRow=venueDates.get(0);
		if(venueDatesRow!=null&&venueDatesRow.get("biddingStartTime")!=null &&venueDatesRow.get("biddingEndTime")!=null){
			String onlineVenueDate=venueDatesRow.get("biddingStartTime").toString();
		
			onlineVenueTimestamp = DateUtil.objectToTimestampConverter(onlineVenueDate);
		
			if((onlineVenueTimestamp.getTime()-bidStartTime.getTime())<0){
				
				resultMap.put("biddingStartTime",onlineVenueTimestamp);
				
			}else{
				if(liveVenueTimestamp!=null)
					resultMap.put("biddingStartTime",liveVenueTimestamp);
				else
				resultMap.put("biddingStartTime",bidStartTime);
				
			}
			
			
			String onlineVenueEndDate=venueDatesRow.get("biddingEndTime").toString();
			Timestamp onlineVenueEndTimestamp=null;
			
				
			onlineVenueEndTimestamp = DateUtil.objectToTimestampConverter(onlineVenueEndDate);
			
			if((onlineVenueEndTimestamp.getTime()-bidEndtime.getTime())>0){
				
				resultMap.put("biddingEndTime",onlineVenueEndTimestamp);
				
			}else{
				if(liveVenueTimestamp!=null)
					resultMap.put("biddingEndTime",liveVenueTimestamp);
				else
				resultMap.put("biddingEndTime",bidEndtime);
				
			}
			
		}else{
			
			if(liveVenueTimestamp!=null)
				resultMap.put("biddingStartTime",liveVenueTimestamp);
			else
			resultMap.put("biddingStartTime",bidStartTime);
			resultMap.put("biddingEndTime",bidEndtime);
		}
		
		return resultMap;
	}
	
	private BuyerCheckListVO getBidderStatus(BuyerCheckListBO buyerCheckListBO) {
		BuyerCheckListVO buyerCheckListVO = new BuyerCheckListVO();
		String paymentType = buyerCheckListBO.getPaymentType();
		String approvedOnlineBidding = buyerCheckListBO.getApproveOnlineBidding();
		String offerType = buyerCheckListBO.getOfferType();
		boolean validateFundsBidderReg = false;
		boolean isAssetLvlBidding = buyerCheckListBO.getIsAssetLvlBidding();
		boolean isRegisteredForAsset = buyerCheckListBO.getIsRegisteredForAsset();
		boolean isAssetFundValidated = buyerCheckListBO.getIsAssetFundValidated();
		boolean unRegisteredUser = buyerCheckListBO.getUserId() != null&& buyerCheckListBO.getBidderID() == null;
		boolean registeredUser = buyerCheckListBO.getUserId() != null&& buyerCheckListBO.getBidderID() != null;
		boolean registrationFlowOccupied = "Occupied".equalsIgnoreCase(buyerCheckListBO.getRegistration_flow());
		boolean isCreditCardPayment=BUYER_CHECKLIST_CREDITCARD_PAYMENT.equalsIgnoreCase(paymentType);
		boolean isCashierCheckPayment=BUYER_CHECKLIST_CASHIERCHECK_PAYMENT.equalsIgnoreCase(paymentType);
		boolean isWireTransfer=BUYER_CHECKLIST_WIRETRANSFER_PAYMENT.equalsIgnoreCase(paymentType);
		String registartionStatus="NOT REGISTERD";
		
		
		buyerCheckListVO.setRegistrationRequired("true");
		if (buyerCheckListBO.getBidderID() != null) {

			buyerCheckListVO.setRegistrationRequired("false");

			if ("Approved".equalsIgnoreCase(buyerCheckListBO
			        .getValidateFundsBidderReg())) {
				validateFundsBidderReg = true;
			}
			if (!validateFundsBidderReg
			        && isCreditCardPayment
			        && GLOBALASSET_AUCTIONTYPE_RESI.equalsIgnoreCase(offerType)) {
				validateFundsBidderReg = true;
			}
		}
		if ("2".equals(approvedOnlineBidding)) {
			buyerCheckListVO
			        .setRegisterationLink(BUYER_CHECKLIST_REGISTRATION_NOT_APPROVED);
			registartionStatus="NOT APPROVED";
		} else if ("1".equals(buyerCheckListBO.getVenueStatusReg())
		        && (((isAssetLvlBidding) && !(isRegisteredForAsset)) || ((isAssetLvlBidding) && !(isAssetFundValidated)))) {
			         buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_VENUE_NOT_AVAILABLE);
			         registartionStatus="VENUE NOT AVAILABLE FOR REGISTRATION";
		} else if (buyerCheckListBO.getBidStartTime()!=null&&DateUtil.differenceInMinutesForCurrent(buyerCheckListBO.getBidStartTime()) > 0) {
			if (unRegisteredUser) {
				if (registrationFlowOccupied) {
					buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_REGISTER_FOR_AUCTION);
					registartionStatus="NOT REGISTERED";
				} else {
					registartionStatus="NOT REGISTERED";
					// different url
					buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_REGISTER_FOR_AUCTION);
				}
			} else if (registeredUser) {

				if (isAssetLvlBidding) {

					if (isRegisteredForAsset && isAssetFundValidated
					        && "1".equals(approvedOnlineBidding)) {
						buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_ALREADY_REGISTERED_FOR_AUCTION);
						registartionStatus="REGISTERED";

					} else if ((isRegisteredForAsset && (!(isAssetFundValidated) || "0"
					        .equals(approvedOnlineBidding)))
					        || (paymentType != null && (isWireTransfer || isCashierCheckPayment))) {
						buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_PENDING_REGISTRATION_FOR_AUCTION);
						registartionStatus="PENDING";
					} else {
						if (GLOBALASSET_AUCTIONTYPE_NOTES.equalsIgnoreCase(offerType)) {
							buyerCheckListVO.setRegisterationLink(BuyerCheckListConstant.BUYER_CHECKLIST_COMPLETE_REGISTRATION);
							registartionStatus="PARTIALLY REGISTERED";
						}
						if (GLOBALASSET_AUCTIONTYPE_COMMERCIAL.equalsIgnoreCase(offerType)) {
							// send different url if FE requires one
							
							buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_COMPLETE_REGISTRATION);
							registartionStatus="PARTIALLY REGISTERED";
						} else {
							// send generic url
							buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_COMPLETE_REGISTRATION);
							registartionStatus="PARTIALLY REGISTERED";
						}
					}

				} else {
					if (validateFundsBidderReg
					        && "1".equals(approvedOnlineBidding)) {
						buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_ALREADY_REGISTERED_FOR_AUCTION);
						registartionStatus="REGISTERED";
					} else if (GLOBALASSET_AUCTIONTYPE_RESI.equalsIgnoreCase(offerType)&& (isWireTransfer || isCashierCheckPayment)) {

						buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_PENDING_REGISTRATION_FOR_AUCTION);
						registartionStatus="PENDING";
					} else if (GLOBALASSET_AUCTIONTYPE_NOTES.equalsIgnoreCase(offerType)) {
						buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_COMPLETE_REGISTRATION);
						registartionStatus="PARTIALLY REGISTERED";
					} else if (GLOBALASSET_AUCTIONTYPE_COMMERCIAL.equalsIgnoreCase(offerType)) {
						// send different url if FE requires one
						buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_COMPLETE_REGISTRATION);
						registartionStatus="PARTIALLY REGISTERED";
					} else {
						// send generic url
						buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_COMPLETE_REGISTRATION);
						registartionStatus="PARTIALLY REGISTERED";
					}
				}

			} else {
				if (registrationFlowOccupied) {
					buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_REGISTER_FOR_AUCTION);
					registartionStatus="NOT REGISTERED";
				} else {
					// different url
					buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_REGISTER_FOR_AUCTION);
					registartionStatus="NOT REGISTERED";
				}
			}
		} else if ("Yes".equalsIgnoreCase(buyerCheckListBO.getPreAuction())) {
			if (unRegisteredUser) {

				// different url
				buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_COMPLETE_BID_FORM);
				registartionStatus="BID FORM REQUIRED";

			} else if (registeredUser) {
				registartionStatus="REGISTERED FOR PREAUCTION";
			} else {
				// different url
				buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_COMPLETE_BID_FORM);
				registartionStatus="BID FORM REQUIRED";
			}

		} else if (buyerCheckListBO.getAllowProxyBidding() != null
		        && buyerCheckListBO.getAllowProxyBidding().booleanValue()) {
			if (unRegisteredUser) {
				if (registrationFlowOccupied) {
					buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_REGISTER_FOR_AUCTION);
				} else {
					// different url
					buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_REGISTER_FOR_AUCTION);
				}
				// different url

			} else if (registeredUser) {

				if (isAssetLvlBidding) {
					if (isRegisteredForAsset) {
						buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_ALREADY_REGISTERED_FOR_AUCTION);
						registartionStatus="REGISTERED";
					} else {
						if (registrationFlowOccupied) {
							buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_REGISTER_FOR_AUCTION);
							registartionStatus="NOT REGISTERED";
						} else {
							// different url
							buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_REGISTER_FOR_AUCTION);
							registartionStatus="NOT REGISTERED";
						}
					}
				} else {

					if (validateFundsBidderReg) {
						buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_ALREADY_REGISTERED_FOR_AUCTION);
						registartionStatus="REGISTERED";
					} else {

						if (registrationFlowOccupied) {
							buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_COMPLETE_REGISTRATION);
							registartionStatus="PARTIALLY REGISTERED";
						} else {
							// different url
							buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_COMPLETE_REGISTRATION);
							registartionStatus="PARTIALLY REGISTERED";
						}
					}

				}

			} else {
				if (registrationFlowOccupied) {
					buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_REGISTER_FOR_AUCTION);
					registartionStatus="NOT REGISTERED";
				} else {
					// different url
					buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_REGISTER_FOR_AUCTION);
					registartionStatus="NOT REGISTERED";
				}
			}
		} else {
			if (unRegisteredUser) {
				if (registrationFlowOccupied) {
					buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_REGISTER_FOR_AUCTION);
					registartionStatus="NOT REGISTERED";
				} else {
					// different url
					registartionStatus="NOT REGISTERED";
					buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_REGISTER_FOR_AUCTION);
				}
				// different url

			} else if (registeredUser) {
				if (isAssetLvlBidding) {
					if (isRegisteredForAsset && isAssetFundValidated
					        && "1".equals(approvedOnlineBidding)) {
						buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_ALREADY_REGISTERED_FOR_AUCTION);
						registartionStatus="REGISTERED";
					} else if ((isRegisteredForAsset && (!isAssetFundValidated || "0".equalsIgnoreCase(approvedOnlineBidding)))
							    || (paymentType != null && (isWireTransfer || isCashierCheckPayment))) {
						buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_PENDING_REGISTRATION_FOR_AUCTION);
						registartionStatus="REGISTERED";

					} else {
						if (GLOBALASSET_AUCTIONTYPE_NOTES.equalsIgnoreCase(offerType)) {
							buyerCheckListVO.setRegisterationLink(BuyerCheckListConstant.BUYER_CHECKLIST_COMPLETE_REGISTRATION);
							registartionStatus="PARTIALLY REGISTERED";
						}
						if (GLOBALASSET_AUCTIONTYPE_COMMERCIAL.equalsIgnoreCase(offerType)) {
							// send different url if FE requires one
							buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_COMPLETE_REGISTRATION);
							registartionStatus="PARTIALLY REGISTERED";
						} else {
							// send generic url
							buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_COMPLETE_REGISTRATION);
							registartionStatus="PARTIALLY REGISTERED";
						}
					}
				} else {

					if (validateFundsBidderReg && "1".equalsIgnoreCase(approvedOnlineBidding)) {
						buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_ALREADY_REGISTERED_FOR_AUCTION);
						registartionStatus="REGISTERED";
					} else if (GLOBALASSET_AUCTIONTYPE_RESI.equalsIgnoreCase(offerType)
					        && (isWireTransfer || isCashierCheckPayment)) {
						buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_PENDING_REGISTRATION_FOR_AUCTION);
						registartionStatus="REGISTERED";
					} else if (GLOBALASSET_AUCTIONTYPE_NOTES.equalsIgnoreCase(offerType)) {
						buyerCheckListVO.setRegisterationLink(BuyerCheckListConstant.BUYER_CHECKLIST_COMPLETE_REGISTRATION);
						registartionStatus="PARTIALLY REGISTERED";
					}
					if (GLOBALASSET_AUCTIONTYPE_COMMERCIAL.equalsIgnoreCase(offerType)) {
						// send different url if FE requires one
						buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_COMPLETE_REGISTRATION);
						registartionStatus="PARTIALLY REGISTERED";
					} else {

						/*if (registrationFlowOccupied) {
							buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_REGISTER_FOR_AUCTION);
							registartionStatus="NOT REGISTERED";
						} else {
							// different url
							buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_REGISTER_FOR_AUCTION);
							registartionStatus="NOT REGISTERED";
						}*/
					}

				}
			} else {
				if (registrationFlowOccupied) {
					buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_REGISTER_FOR_AUCTION);
					registartionStatus="NOT REGISTERED";
				} else {
					// different url
					buyerCheckListVO.setRegisterationLink(BUYER_CHECKLIST_REGISTER_FOR_AUCTION);
					registartionStatus="NOT REGISTERED";
				}
			}

		}
		buyerCheckListVO.setRegistrationStatus(registartionStatus);
		return buyerCheckListVO;
	}
	private Map<String,Object> getResidentialSpecificBidDetails(Map<String,Object> paramMap)  throws ServiceException{
		Map<String,Object> resultMap=new HashMap<String,Object>();
		
			String requestType=BuyerCheckListConstant.CHECK_PRE_AUCTION_RESI;
			
			GenericQueryRequest genericQueryRequest = acpCommonHelper.getGenericQueryRequest(requestType,paramMap);
			ACPRequest acpRequest=new ACPRequest(
					ServiceNameMapping.GET_GENERIC_SERVICE,RequestType.JSON,genericQueryRequest);
			ACPResponse acpResponse = genericService.getGenericService(acpRequest);
			List<Map<String, Object>>  preAuctionResi = (List<Map<String, Object>>) acpResponse.getPayload();
			Map<String,Object> preAuctionResiRow=null;
			if(preAuctionResi!=null &&preAuctionResi.size()>0)
				preAuctionResiRow=preAuctionResi.get(0);
			if(preAuctionResiRow!=null){
			Set<String> keys=preAuctionResiRow.keySet();
			
			for(String indexKey:keys){
				if(preAuctionResiRow.get(indexKey)!=null)
				resultMap.put(indexKey,preAuctionResiRow.get(indexKey));
			}
			
			}
			
			System.out.println("preAuctionResiRow "+ preAuctionResiRow);
			
			 requestType=BuyerCheckListConstant.CHECK_PROXY_BID_RESI_AUCTION;
			 genericQueryRequest = acpCommonHelper.getGenericQueryRequest(requestType,paramMap);
			 acpRequest=new ACPRequest(
						ServiceNameMapping.GET_GENERIC_SERVICE,RequestType.JSON,genericQueryRequest);
			 acpResponse = genericService.getGenericService(acpRequest);
			 Map<String,Object> proxyBidRow=null;
				List<Map<String, Object>>  proxyBid= (List<Map<String, Object>>) acpResponse.getPayload();
				if(proxyBid!=null &&proxyBid.size()>0)
					proxyBidRow=proxyBid.get(0);
				
				System.out.println("proxyBid"+ proxyBidRow);
				
				if(proxyBidRow!=null){
				Set<String> bidKeys=proxyBidRow.keySet();
				
				for(String indexKey:bidKeys){
					if(proxyBidRow.get(indexKey)!=null)
					resultMap.put(indexKey,proxyBidRow.get(indexKey));
				}
			}
		
		return resultMap;
	}



	
	/*private Map<String,Object> getBidAuthorization(Map<String,Object> paramMap,String requestType)  throws ServiceException{
		Map<String,Object> resultMap=new HashMap<String,Object>();
		
			
			
			GenericQueryRequest genericQueryRequest = acpCommonHelper.getGenericQueryRequest(requestType,paramMap);
			ACPRequest acpRequest=new ACPRequest(
					ServiceNameMapping.GET_GENERIC_SERVICE,RequestType.JSON,genericQueryRequest);
			ACPResponse acpResponse = businessDelegate.execute(acpRequest);
			List<Map<String, Object>>  commercialAuthorisation = (List<Map<String, Object>>) acpResponse.getPayload();
			Map<String,Object> commercialAuthorisationRow=null;
			Set<String> keys=null;
			if(commercialAuthorisation!=null &&commercialAuthorisation.size()>0)
			{	commercialAuthorisationRow=commercialAuthorisation.get(0);
				keys=commercialAuthorisationRow.keySet();
				for(String indexKey:keys){
					if(commercialAuthorisationRow.get(indexKey)!=null)
					resultMap.put(indexKey,commercialAuthorisationRow.get(indexKey));
				}
			}
			
			
			//check if it is demo auction
			
			System.out.println("commercialAuthorisationRow "+ commercialAuthorisationRow);
			
			 requestType=BuyerCheckListConstant.CHECK_BID_LIVE_COMMERCIAL;
			 genericQueryRequest = acpCommonHelper.getGenericQueryRequest(requestType,paramMap);
			 acpRequest=new ACPRequest(
						ServiceNameMapping.GET_GENERIC_SERVICE,RequestType.JSON,genericQueryRequest);
			 acpResponse = businessDelegate.execute(acpRequest);
			 Map<String,Object> bidLiveCommercialRow=null;
				List<Map<String, Object>>  bidLiveCommercial = (List<Map<String, Object>>) acpResponse.getPayload();
				if(bidLiveCommercial!=null &&bidLiveCommercial.size()>0)
				{bidLiveCommercialRow=bidLiveCommercial.get(0);
                 Set<String> bidKeys=bidLiveCommercialRow.keySet();
				
				 for(String indexKey:bidKeys){
					if(bidLiveCommercialRow.get(indexKey)!=null)
					resultMap.put(indexKey,bidLiveCommercialRow.get(indexKey));
				}
				}
				
				
				System.out.println("bidLiveCommercialRow"+ bidLiveCommercialRow);
		
		return resultMap;
	}*/
	
	

	
}
