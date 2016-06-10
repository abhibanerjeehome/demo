package com.redwood.rp.flaunt.constant;


public class UserServiceConstant {
	
	
	
	public final static String EMPTY = "";
	
	public final static String COMMA_STR = ",";
	
	public final static String DASH_STR = "-";
	
	public final static String COLON_STR = ":";
	
	public final static String Y_STR = "Y";
	
	public final static String YES = "YES";	
	
	public final static String Yes = "Yes";
	
	public final static String NO = "No";	

	public final static String INFO = "INFO";	

	public final static String  ASTERIC = "*";
	
	public final static String  ZERO_STR_NUM = 	"0";	
	
	public final static String ONE_STR_NUM = "1";

	public final static String  TRUE_STR = "TRUE";
	
	public final static String  FALSE_STR = "FALSE";
			
	public final static String  DATE_FORMAT_WITH_TIME ="YYYY-MM-DD HH:mm:ss.S";
	
	public final static String  DATE_FORMAT_WITH_NO_TIME ="yyyy-MM-dd";
	
	public static final String RESI_STR = "residential";
	
	public static final String HTML_STR = "HTML";
	
	public static String AUTHENTICATION_TOKEN = "authentication_token";
	
	public static String AUTHENTICATION_TOKEN_TIMESTAMP = "authentication_token_timestamp";
	
	public static String AUTHORIZATION = "Authorization";	
	
	public static String CONTENT_TYPE = "Content-type";
	
	public static String ACCEPT = "Accept";
	
	public static final int RANDOM_NUMBER_MAX = 5000;
	
	public static final int ONE_INT_NUM = 1;
	
	public static final int ZERO_INT_NUM = 0;
	
	public static final String DEFAULT_SEARCH_SETTING_NAME = "All States - All Cities";
	
	public static final String DEFAULT_COUNTRY_ID_USA = "71";
	
	
	
	// Exception description
	
	public final static String TIMESTAMP_PARSE_EXCEPTION = "Timestamp parse exception";
	
	public final static String REQUEST_EXCEPTION = "Invalid Request Input exception";
	
	public final static String FETCH_CACHED_USER_EXCEPTION_NOTOKEN = "No User Id exception. TokenVO empty";
	
	public final static String FETCH_CLIENT_IP_EXCEPTION_NOTOKEN = "No Client IP exception. TokenVO empty";
	
	public static final String FETCH_CLIENT_ID_EXCEPTION_NOTOKEN = "No Client Id exception. TokenVO empty";
	
	public final static String FETCH_CACHED_USER_EXCEPTION_ANONYMOUS = "No User Id exception. Anonymous User";
	
	
	// QUERIES	
	
	public final static String GET_USER_DETAILS="GET_USER_DETAILS";
	
	
	//Query params
	
	public final static String USER_ID="USER_ID";
	
	public final static String GLOBAL_PROPERTY_ID="GLOBAL_PROPERTY_ID";
	
	public final static String STATE="STATE";
	
	public final static String COUNTRY_ID_USA="71";
	
	public final static String COUNTRY_NAME_USA="United States";
	
	public final static String USER_SCHEMA="USER_SCHEMA";
	
	public final static String FORECLOSURE_SCHEMA="FORECLOSURE_SCHEMA";
	
	public final static String OCCUPANCY_STR = "OCCUPANCY";
	

	// msgs 
	public final static String 	USER_EXISTS_MSG  = "This email address / user already exists.  If you forgot your password please click below";
	
	public final static String 	USER_UPDATE_SUCCESS_MSG  = 	"User data was updated Successfully";

	public static final String SPACE = " ";

	public static final String DECIMAL_POINT = ".";
	
	public static final String OCCUPANCY_DELIMITER = "OCCUPANCY-";

	public static final Object MAX_STR = "MAX";

	public static final String OFFSET = "OFFSET";
	
	public static final String LIMIT = "LIMIT";

	public static final String SUCCESS = "success";

	public static final String BIDDER_TAB_FILTER_ALL = "all";
	
	public static final String BIDDER_TAB_FILTER_WINNING = "winning";
	
	public static final String BIDDER_TAB_FILTER_OUTBID = "outbid";
	
	public static final String BIDDER_TAB_FILTER_WON = "won";
	
	public static final String BIDDER_TAB_FILTER_PROXY = "proxy";

	public static final String BIDDER_TAB_FILTER_PRESALE = "presale";
	
	public static final String BIDDER_TAB_FILTER_REGISTERED = "registered";
	
	public static final String BIDDER_TAB_FILTER_READY_TO_BID = "ready_to_bid";
	
	public static final String BIDDER_TAB_FILTER_SAVED = "saved";
	
	public static final String BIDDER_TAB_SORT_EVENT_TIME = "event_time";
	
	public static final String BIDDER_TAB_SORT_CATEGORY_AZ = "category_a_to_z";
	
	public static final String BIDDER_TAB_SORT_CATEGORY_ZA = "category_z_to_a";
	
	public static final String BIDDER_TAB_SORT_ITEM_NO = "item_no";
	
	public static final String BIDDER_TAB_SORT_ACTIVITY = "activity";

	public static final Integer TWO_INT_NUM = 2;
	
	//User acceptance doc type
	public static final String USER_ACCEPTANCE_DOC_TYPE_NDA = "NDA";
	
	public static final String USER_ACCEPTANCE_DOC_TYPE_DISCLAIMER = "Disclaimer";
	
	public static final String SAVED_SEARCH_PARAM_NAME_AUCTION_TYPE = "auction_type";
	
	public static final String SAVED_SEARCH_PARAM_VALUE_AUCTION_TYPE_ANY = "any";
	
	public static final String SAVED_SEARCH_PARAM_VALUE_AUCTION_TYPE_RESI = "residential";
	
	public static final String SAVED_SEARCH_PARAM_VALUE_AUCTION_TYPE_COMM = "commercial";
	
	public static final String SAVED_SEARCH_PARAM_VALUE_AUCTION_TYPE_NOTES = "notes";
	
	public static final String SAVED_SEARCH_PARAM_VALUE_AUCTION_TYPE_ANY_REQUEST = "ANY";
	
	public static final String SAVED_SEARCH_PARAM_VALUE_AUCTION_TYPE_RESI_REQUEST = "RESIDENTIAL";
	
	public static final String SAVED_SEARCH_PARAM_VALUE_AUCTION_TYPE_COMM_REQUEST = "COMMERCIAL";
	
	public static final String SAVED_SEARCH_PARAM_VALUE_AUCTION_TYPE_NOTES_REQUEST = "NOTES";
	
}