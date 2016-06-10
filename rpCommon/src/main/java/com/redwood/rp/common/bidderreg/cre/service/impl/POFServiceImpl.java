package com.redwood.rp.common.bidderreg.cre.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.redwood.rp.security.service.OauthService;
import com.redwood.rp.security.vo.UserRequestVO;
import com.redwood.rp.common.bidderreg.cre.das.POFDAO;
import com.redwood.rp.common.bidderreg.cre.domain.Asset;
import com.redwood.rp.common.bidderreg.cre.domain.POF;
import com.redwood.rp.common.bidderreg.cre.service.POFService;
import com.redwood.rp.common.bidderreg.cre.vo.pof.POFDocVO;
import com.redwood.rp.common.bidderreg.cre.vo.pof.POFVO;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.util.StringUtil;

@Transactional(value = "transactionManager_jdbc_userInformation", rollbackFor = Exception.class)
public class POFServiceImpl implements POFService {

	private static final String FILENAME = POFServiceImpl.class.getName();
	private static Logger mLogger = LoggerFactory.getLogger(FILENAME);
	private final String NEW_FORMAT = "yyyy-MM-dd";

	POFDAO pofDAO;

	@Override
	public POFVO validatePOF(List<Asset> assetList) throws ServiceException {
		// TODO Auto-generated method stub
		POFVO pofVO = new POFVO();
		List<POF> pofList = new ArrayList<POF>();
		List<POF> approvedPOFList = new ArrayList<POF>();
		List<POF> pendingPOFList = new ArrayList<POF>();
		List<POFDocVO> pendingDocs = new ArrayList<POFDocVO>();
		float maxPOFAllowed = 0;
		boolean fundsAvailable = false;
		boolean earlyExpiry = false;

		float approvedPOFSum = 0;
		try {
			UserRequestVO userRequestVO = OauthService.getUserRequest();

			String user = userRequestVO.getUserId();
			Long userIdLng = Long.parseLong(user);

			List<Map<String, Object>> resultList = pofDAO
			        .getPOFDetails(userIdLng);

			for (Map<String, Object> map : resultList) {

				Object documentId = map.get("documentId");
				Object userId = map.get("userId");
				Object documentType = map.get("documentType");
				Object documentName = map.get("documentName");
				Object documentDisplayName = map.get("documentDisplayName");
				Object expirationDate = map.get("expirationDate");
				Object registrationDocStatus = map.get("registrationDocStatus");
				Object pofAmount = map.get("pofAmount");
				Object docStatusUpdatedNotes = map.get("docStatusUpdatedNotes");

				POF pof = new POF();
				pof.setDocumentId(documentId == null ? null : Long.parseLong(documentId.toString()));
				pof.setUserId(userId == null ? null : Long.parseLong(userId.toString()));
				pof.setDocumentType(documentType == null ? null : Integer.parseInt(documentType.toString()));
				pof.setDocumentName(documentName == null ? null : documentName.toString());
				pof.setDocumentDisplayName(documentDisplayName == null ? null: documentDisplayName.toString());
				pof.setExpirationDate(expirationDate == null ? null: expirationDate.toString());
				pof.setRegistrationDocStatus(registrationDocStatus == null ? null: registrationDocStatus.toString());
				pof.setPofAmount(pofAmount == null ? null : Float.parseFloat(pofAmount.toString()));
				pof.setDocStatusUpdatedNotes(docStatusUpdatedNotes == null ? null: docStatusUpdatedNotes.toString());

				if ("APPROVED".equalsIgnoreCase(pof.getRegistrationDocStatus())) {
					approvedPOFSum = approvedPOFSum + pof.getPofAmount();
					approvedPOFList.add(pof);
				}
				if ("PENDING_APPROVAL".equalsIgnoreCase(pof.getRegistrationDocStatus())) {
					pendingPOFList.add(pof);
					
					POFDocVO pofDocVO=new POFDocVO();
					pofDocVO.setDisplayDocName(pof.getDocumentDisplayName());
					pofDocVO.setOriginalDocName(pof.getDocumentName());
					pendingDocs.add(pofDocVO);
				}

				pofList.add(pof);
			}

		} catch (DaoException e) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,
			        e.getErrorVO());
		}

		try {

			Map<String, Object> maxBidMinDateMap = getMaxStartingBidAndMinDate(assetList);
			
			if(maxBidMinDateMap !=null){
			
					Float maxBid = (Float) maxBidMinDateMap.get("MAX_BID");
		
					if (maxBid < approvedPOFSum) {
						fundsAvailable = true;
					} else {
						fundsAvailable = false;
					}
				
				if (fundsAvailable) {
					for (Asset asset : assetList) {
	
						if (earlyExpiry)
							break;
	
						Date auctionEndTime = asset.getBiddingEndTime();
						SimpleDateFormat sdf = new SimpleDateFormat(NEW_FORMAT);
	
						String date = sdf.format(auctionEndTime);
	
						Map<String, Object> validPOFMap = getPOFListForValidDates(approvedPOFList, date);
						Float pofSum = (Float) validPOFMap.get("VALID_POF_AMOUNT");
						int validPOFDate = pofSum.compareTo((float) (asset.getStartingBid() != null ? asset.getStartingBid().floatValue() : 0));
						if (validPOFDate < 0) {
							earlyExpiry = true;
	
						} else {
							if (maxPOFAllowed < pofSum)
								maxPOFAllowed = pofSum;
						}
	
					}
	
				} else {
					pofVO.setStatus("INSUFFICIENT_FUNDS");
					maxPOFAllowed = approvedPOFSum;
				}
			}else{
				pofVO.setStatus("UNAVAILABLE");
				maxPOFAllowed=approvedPOFSum;
			}
			if (earlyExpiry) {
				pofVO.setStatus("EXPIRED");
				maxPOFAllowed = approvedPOFSum;
			}

			if (StringUtil.isBlank(pofVO.getStatus())) {

				pofVO.setStatus("APPROVED");
			}

			if (pendingDocs != null && pendingDocs.size() > 0) {
				pofVO.setPendingDocs(pendingDocs);
			}
			pofVO.setCurrentPOFAmount(maxPOFAllowed);
		} catch (Exception ex) {
			mLogger.debug("Error while validating pof");
			mLogger.error(ex.getMessage());
			pofVO.setCurrentPOFAmount(maxPOFAllowed);
			pofVO.setStatus("UNAVAILABLE");
		}

		return pofVO;
	}

	Map<String, Object> getMaxStartingBidAndMinDate(List<Asset> assetList) {

		Map<String, Object> returnMap = new HashMap<String, Object>();
		float startingBidMax = 0;
		Date bidEndDate = null;
		try{
			for (Asset asset : assetList) {
				if (startingBidMax < asset.getStartingBid())
					startingBidMax = (float) (asset.getStartingBid() != null ? asset
					        .getStartingBid().floatValue() : 0);
				if (bidEndDate == null)
					bidEndDate = asset.getBiddingEndTime();
				if (bidEndDate != null
				        && bidEndDate.compareTo(asset.getBiddingEndTime()) > 0) {
					bidEndDate = asset.getBiddingEndTime();
				}
	
			}
		}catch(Exception ex){
			mLogger.debug("Error while calculating the Max start Bid and Min Date");
			return null;
		}
		returnMap.put("MAX_BID", startingBidMax);
		returnMap.put("MIN_DATE", bidEndDate);

		return returnMap;
	}

	Map<String, Object> getPOFListForValidDates(List<POF> pofList,String assetBidEndDate) {
		List<POF> vaildPOFList = new ArrayList<POF>();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		float pofAmount = 0;
		for (POF pof : pofList) {
			int validPOF = assetBidEndDate.compareTo(pof.getExpirationDate());

			if (validPOF < 0) {
				pofAmount = pofAmount + pof.getPofAmount();
				vaildPOFList.add(pof);

			}
		}
		returnMap.put("VALID_POF_LIST", vaildPOFList);
		returnMap.put("VALID_POF_AMOUNT", pofAmount);

		return returnMap;
	}

	public POFDAO getPofDAO() {
		return pofDAO;
	}

	public void setPofDAO(POFDAO pofDAO) {
		this.pofDAO = pofDAO;
	}

}
