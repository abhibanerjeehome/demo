package com.redwood.rp.common.asset.util;

import org.apache.commons.lang.StringUtils;

import com.redwood.rp.core.util.PropertyUtil;
/**
 * Utility Class for asset related common functions
 * 
 * @author asengamalai
 * 
 */
public class AssetUtil {
	final static String AUCTION_HOST = PropertyUtil.get("acpcommon.host");
	/**
	 * generates a pdp url, all the inputs are mandatory
	 * 
	 * @param globalPropID
	 * @param auctionID
	 * @param auctionType
	 * @param auctionNumber
	 * @param propertyAddress
	 * @param propertyCity
	 * @param propertyZip
	 * @param propertyStateName
	 * @param propertyStateAbbrevation
	 * @param dueDiligence
	 * @return
	 */
	public static String generatePdpUrl(Integer globalPropID, Integer auctionID, String auctionType, String auctionNumber, String propertyAddress, String propertyCity,
			String propertyZip, String propertyStateName, String propertyStateAbbrevation, boolean dueDiligence) {
		StringBuffer pdpUrl = new StringBuffer();
		pdpUrl.append(AUCTION_HOST);

		if (StringUtils.isNotBlank(propertyStateName)) {
			pdpUrl.append(propertyStateName);
		}

		if (StringUtils.isNotBlank(auctionType)) {
			pdpUrl.append("/" + auctionType + "-auction-asset");
		}

		if (globalPropID != null) {
			pdpUrl.append("/" + globalPropID);
		}

		if (auctionID != null) {
			pdpUrl.append("-" + auctionID);
		}

		if (dueDiligence) {
			pdpUrl.append("-See-Due-Diligence");
		} else if (StringUtils.isNotBlank(propertyAddress)) {
			pdpUrl.append("-" + propertyAddress.trim().replaceAll("[#?&/]", "").replaceAll("\\s+", " "));
		}

		if (StringUtils.isNotBlank(propertyCity)) {
			pdpUrl.append("-" + propertyCity);
		}
		pdpUrl.append("-" + propertyStateAbbrevation);

		if (StringUtils.isNotBlank(propertyZip)) {
			pdpUrl.append("-" + propertyZip);
		}

		if (StringUtils.isNotBlank(auctionNumber)) {
			pdpUrl.append("-" + auctionNumber.replaceAll("-", "_"));
		}
		return pdpUrl.toString().replaceAll(" ", "-").toLowerCase();
	}

}
