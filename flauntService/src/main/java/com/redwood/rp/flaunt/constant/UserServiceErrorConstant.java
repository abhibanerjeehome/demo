package com.redwood.rp.flaunt.constant;

public class UserServiceErrorConstant {

	//Error Codes

		public static final String US_INVALID_INPUT_EXCEPTION = "US101";
		public static final String ERR_MSG_US_INVALID_INPUT = "Invalid input sent in the request.";
		
		public static final String US_INVALID_USER_EXCEPTION = "US102";
		public static final String ERR_MSG_FOR_INVALID_USER = "User Id is not valid.";
		
		public static final String US_REQUIRED_FIELD_EXCEPTION = "US103";
		public static final String ERR_MSG_US_REQUIRED = "Missing mandatory field.";
		
		
		public static final String US_NO_RECORDS_EXCEPTION = "US104";
		public static final String ERR_MSG_FOR_NO_RECORDS = "No Records returned.";
		
		public static final String US_DB_EXCEPTION = "US105";
		public static final String ERR_MSG_DB_EXCEPTION = "Failed to execute DB query.";
		
		public static final String US_SAVE_PROPERTY_EXCEPTION = "US106";
		public static final String ERR_MSG_SAVE_PROPERTY = "Not able to Save Property.";
		
		public static final String US_SAVE_SEARCH_EXCEPTION = "US107";
		public static final String ERR_MSG_SAVE_SEARCH = "Not able to Save Search Criteria.";
		
		public static final String US_STATES_EXCEPTION = "US108";
		public static final String ERR_MSG_STATES = "Not able to find State info.";
		
		public static final String US_ANONYMOUS_EXCEPTION = "US109";
		public static final String ERR_MSG_ANONYMOUS = "User not logged in.";
		
		public static final String US_PASSWORD_EXCEPTION = "US110";
		public static final String ERR_INVALID_PASSWORD = "Password is Invalid.";
		
		public static final String US_CHANGE_PASSWORD_EXCEPTION = "US111";
		public static final String ERR_MSG_CHANGE_PASSWORD = "Not able to Change Password.";
		
		public static final String US_USER_EXISTS_EXCEPTION = "US112";
		public static final String ERR_MSG_USER_EXISTS = "Email Address is already taken by a registered User";
		
		public static final String US_USER_UNCONFIRMED_EXCEPTION = "US113";
		public static final String ERR_MSG_USER_UNCONFIRMED = "Account Confirmation pending for this email address";
		
		public static final String US_USER_REGISTER_EXCEPTION = "US114";
		public static final String ERR_MSG_USER_REGISTER = "Failed to update User Registration";		

		public static final String US_INVALID_USER = "US115";
		public static final String ERR_MSG_INVALID_USER = "Invalid User";
		
		public static final String US_FAIL_USER_CONFIRM = "US116";
		public static final String ERR_MSG_FAIL_USER_CONFIRM = "Failed to Confirm User";
		
		public static final String US_USER_SUSPEND = "US117";
		public static final String ERR_MSG_USER_SUSPEND = "User is Suspended";
		
		public static final String US_RESET_PASSWORD_EXPIRED = "US118";
		public static final String ERR_MSG_RESET_PASSWORD_EXPIRED = "Reset Link Expired";
		
		public static final String US_RESET_PASSWORD_INVALID_UID = "US119";
		public static final String ERR_MSG_RESET_PASSWORD_INVALID_UID = "Invalid UID";
		
		public static final String US_CHANGE_EMAIL_LINK_EXPIRED = "US120";
		public static final String ERR_MSG_CHANGE_EMAIL_LINK_EXPIRED = "Change email link expired";
		
		public static final String US_CHANGE_EMAIL_INVALID_UID = "US121";
		public static final String ERR_MSG_CHANGE_EMAIL_INVALID_UID = "Invalid UID";
	

}
