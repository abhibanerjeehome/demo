package com.redwood.rp.flaunt.vo.json.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateUserRequestVO implements Serializable {
	
	private static final long serialVersionUID = 2L;
	
	public UpdateUserRequestVO() {		
	}
	
	
	private String userId;
	private String emailAddress;
	private String firstName;
	private String lastName;
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	private String countryId;
	private String primaryPhone;
	private String title;
	private String companyName;
	private String mobilePhone;
	private String userName;
	private boolean emailNewsletterPrefer;
	private boolean mailPref;
	private boolean phonePrefer;
	private boolean mobilePrefer;
	private boolean smsPrefer;

	@JsonProperty("mobile_phone")
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	@JsonProperty("user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@JsonProperty("email_newsletter_consent")
	public boolean isEmailNewsletterPrefer() {
		return emailNewsletterPrefer;
	}
	public void setEmailNewsletterPrefer(boolean emailNewsletterPrefer) {
		this.emailNewsletterPrefer = emailNewsletterPrefer;
	}
	
	@JsonProperty("regular_mail_consent")
	public boolean isMailPref() {
		return mailPref;
	}
	public void setMailPref(boolean mailPref) {
		this.mailPref = mailPref;
	}
	
	@JsonProperty("phone_consent")
	public boolean isPhonePrefer() {
		return phonePrefer;
	}
	public void setPhonePrefer(boolean phonePrefer) {
		this.phonePrefer = phonePrefer;
	}
	@JsonProperty("mobile_consent")
	public boolean isMobilePrefer() {
		return mobilePrefer;
	}
	public void setMobilePrefer(boolean mobilePrefer) {
		this.mobilePrefer = mobilePrefer;
	}
	
	@JsonProperty("sms_consent")
	public boolean isSmsPrefer() {
		return smsPrefer;
	}
	public void setSmsPrefer(boolean smsPrefer) {
		this.smsPrefer = smsPrefer;
	}
	@JsonProperty("user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@JsonProperty("email")
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	@JsonProperty("first_name")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@JsonProperty("last_name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@JsonProperty("address")
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	@JsonProperty("city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@JsonProperty("state")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@JsonProperty("zip")
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	@JsonProperty("country")
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	@JsonProperty("phone")
	public String getPrimaryPhone() {
		return primaryPhone;
	}
	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}
	@JsonProperty("title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@JsonProperty("company")
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
