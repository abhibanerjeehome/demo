package com.redwood.rp.property.constant;


public class PropertyServiceConstant {
	
	
	public final static String GLOBALASSET_AUCTIONTYPE_RESI = "RESIDENTIAL";

	public final static String GLOBALASSET_AUCTIONTYPE_COMMERCIAL = "COMMERCIAL";
	
	public final static String GLOBALASSET_AUCTIONTYPE_NOTES = "NOTES";
	
	public final static String POST_FORECLOSURE = "Post Foreclosure";
	
	public final static String AUCTION = "AUCTION";
	
	public final static String EMPTY = "";
	
	public final static String 	REO = "REO";
	
	public final static String 	OCCUPIED = "Occupied";
	
	public final static String 	REDEMPTION = "Redemption";
	
	public final static String 	SCALE = "scale";
	
	public final static String SELLER_WCT = "WCT";
	
	public final static String SELLER_AHD = "AHD";
	
	public final static String SELLER_BAO = "BAO";
	
	public final static String SELLER_NSO = "NSO";

	public final static String Y_STR = "Y";
	
	public final static String YES = "YES";
	
	public final static String CC =	"CC";
	
	public final static String DL =	"DL";
	
	public final static String INFO = "INFO";
	
	public final static String ONE_PERCENT =  "1%";
	
	public final static String GREATER_OF_1_PER_OR_$_400 = "The greater of 1% or $400";;

	public final static String  ASTERIC = "*";
	
	public final static String  ZERO_STR_NUM = 	"0";
	
	public final static String  NOT_OFFERED = "Not Offered";
	
	public final static String  USD = "USD";
	
	public final static String  EVENT = "Event"; 
	
	public final static String  TRUE_STR = "TRUE";
			
	public final static String  DATE_FORMAT_WITH_TIME ="YYYY-MM-DD HH:mm:ss.S";
	
	public final static String  DATE_FORMAT_WITH_NO_TIME ="yyyy-MM-dd";
	
	
	// Exception description
	
	public final static String TIMESTAMP_PARSE_EXCEPTION = "Timestamp parse exception";
	
	
	// QUERIES
	public final static String GET_AUCTION_DETAILS="GET_AUCTION_DETAILS";
	
	public final static String GET_PROPERTY_DETAILS="GET_PROPERTY_DETAILS";
	
	public final static String GET_PROPERTY_COMM_DETAILS="GET_PROPERTY_COMM_DETAILS";
	
	public final static String GET_PROPERTY_NOTES_DETAILS="GET_PROPERTY_NOTES_DETAILS";
	
	public final static String GET_MORTGAGE_COMPANY_DETAILS="GET_MORTGAGE_COMPANY_DETAILS";
	
	public final static String GET_TOTAL_BID="GET_TOTAL_BID";
	
	public final static String GET_PURCHASE_DOC_TYPE="GET_PURCHASE_DOC_TYPE";
	
	public final static String GET_DISCLOSURE_FOR_TC="GET_DISCLOSURE_FOR_TC";

	public final static String GET_DISCLOSURE_FOR_OCCUPIED_TC="GET_DISCLOSURE_FOR_TC";
	
	public final static String GET_DISCLOSURE_BY_CMS="GET_DISCLOSURE_BY_CMS";
	
	public final static String GET_COUNTYAUCTIONDT_BY_AID_GPID="GET_COUNTYAUCTIONDT_BY_AID_GPID";
	
	public final static String GET_AUCTION_TYPE="GET_AUCTION_TYPE";
	
	//Query params
	
	public final static String PROPERTY_ID="PROPERTY_ID";
	
	public final static String GLOBAL_PROPERTY_ID="GLOBAL_PROPERTY_ID";
	
	public final static String AUCTION_ID="AUCTION_ID";
	
	public final static String POOL_NUMBER_PART="POOL_NUMBER_PART";
	
	public final static String AUCTION_TYPE="AUCTION_TYPE";
	
	public final static String PRODUCT_TYPE="PRODUCT_TYPE";
	
	public final static String STATE="STATE";
	
	public final static String POOL_NUMBER="POOL_NUMBER";
	
	public final static String PROPERTY_OCCUPANCY_STATUS="PROPERTY_OCCUPANCY_STATUS";
	
	public final static String PRODUCT_ID="PRODUCT_ID";
	
	public final static String AUCTION_NUMBER="AUCTION_NUMBER";
	
	public final static String AUCTION_DT="AUCTION_DT";
	
	public final static String 	CMS_ID  = 	"CMS_ID";
	
	
	// msgs 
	public final static String 	CC_MSG  = "You need to have your credit card authorized before you can view these confidential documents.";
	
	public final static String 	DL_MSG  = 	"We need your driver s license number to verify your identity";
	
	public final static String 	INFO_DATA_VAULT_MSG  = 		"We need the following information to view the Secure Data Vault";
	
	// from .properties
	
	public final static String SHOULD_SHOW_LABELS = "${propertyService.shouldShowLabels}";
	
	public final static String AUCTION_MAIL_ID = "${propertyService.auction.emailId}";
	
	// get minimal property details
	
	public final static String GET_COMMERICIAL_PROPERTY_DETAILS = "GET_COMMERCIAL_PROPERTY_DETAILS";
	public final static String GET_RESIDENTIAL_PROPERTY_DETAILS = "GET_RESIDENTIAL_PROPERTY_DETAILS";
	public final static String GET_NOTES_PROPERTY_DETAILS = "GET_NOTES_PROPERTY_DETAILS";
	
	// check sold property access (tbl_post_code)
	public final static String GET_SOLD_PROPERTY_ACCESS_DETAILS = "GET_SOLD_PROPERTY_ACCESS_DETAILS";
	public final static String GET_SOLD_PROPERTY_ACCESS_DETAILS_FOR_CODE = "GET_SOLD_PROPERTY_ACCESS_DETAILS_FOR_CODE";
	public final static String GET_PROPERTY_INFORMATION = "GET_PROPERTY_INFORMATION";
}
