package com.redwood.rp.flaunt.das.dto;

import java.io.Serializable;
import java.util.Date;

public class UserRegistrationDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// user registration level 
	private long userId;
	private String email;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private int countryId;
	private String countryName;
	private String phone;
	private String title;
	private String companyName;	
	private String mobile;
	private Date dateOfBirth;
	private String address1;
	private int status;
	private String username;
	private int sellerBidder;
	private int county;
	private String password;
	private int suspend;
	private int vipStatus;
	private Date postedDate;
	private String preApproved;
	private String preApprovalAmount;
	private String bidderNumber;
	private int autoSellerGroupId;
	private String heardAboutUs;
	private String dreNumnber;
	private String officeNumber;
	private String brokerFirmName;
	private String brokerEmail;
	private String areaOfSpecialty;
	private Date joinDate;
	private String bio;
	private String documentSubscription;
	private String emailNewsletterPrefer;
	private String regularMailPrefer;
	private String telephonePrefer;
	private String mobilePrefer;
	private String smsPrefer;
	private String institutionVip;
	private String rememberMe;
	private String signupSource;
	private String amlStatus;
	private String prefix;
	private String entityType;
	

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String emailAddress) {
		this.email = emailAddress;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String streetAddress) {
		this.address = streetAddress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String primaryPhone) {
		this.phone = primaryPhone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getSellerBidder() {
		return sellerBidder;
	}
	public void setSellerBidder(Integer sellerBidder) {
		this.sellerBidder = sellerBidder;
	}
	public int getCounty() {
		return county;
	}
	public void setCounty(int county) {
		this.county = county;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSuspend() {
		return suspend;
	}
	public void setSuspend(int suspend) {
		this.suspend = suspend;
	}
	public int getVipStatus() {
		return vipStatus;
	}
	public void setVipStatus(int vipStatus) {
		this.vipStatus = vipStatus;
	}
	public Date getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
	public String getPreApproved() {
		return preApproved;
	}
	public void setPreApproved(String preApproved) {
		this.preApproved = preApproved;
	}
	public String getPreApprovalAmount() {
		return preApprovalAmount;
	}
	public void setPreApprovalAmount(String preApprovalAmount) {
		this.preApprovalAmount = preApprovalAmount;
	}
	public String getBidderNumber() {
		return bidderNumber;
	}
	public void setBidderNumber(String bidderNumber) {
		this.bidderNumber = bidderNumber;
	}
	public int getAutoSellerGroupId() {
		return autoSellerGroupId;
	}
	public void setAutoSellerGroupId(int autoSellerGroupId) {
		this.autoSellerGroupId = autoSellerGroupId;
	}
	public String getHeardAboutUs() {
		return heardAboutUs;
	}
	public void setHeardAboutUs(String heardAboutUs) {
		this.heardAboutUs = heardAboutUs;
	}
	public String getDreNumnber() {
		return dreNumnber;
	}
	public void setDreNumnber(String dreNumnber) {
		this.dreNumnber = dreNumnber;
	}
	public String getOfficeNumber() {
		return officeNumber;
	}
	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}
	public String getBrokerFirmName() {
		return brokerFirmName;
	}
	public void setBrokerFirmName(String brokerFirmName) {
		this.brokerFirmName = brokerFirmName;
	}
	public String getBrokerEmail() {
		return brokerEmail;
	}
	public void setBrokerEmail(String brokerEmail) {
		this.brokerEmail = brokerEmail;
	}
	public String getAreaOfSpecialty() {
		return areaOfSpecialty;
	}
	public void setAreaOfSpecialty(String areaOfSpecialty) {
		this.areaOfSpecialty = areaOfSpecialty;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getDocumentSubscription() {
		return documentSubscription;
	}
	public void setDocumentSubscription(String documentSubscription) {
		this.documentSubscription = documentSubscription;
	}
	public String getEmailNewsletterPrefer() {
		return emailNewsletterPrefer;
	}
	public void setEmailNewsletterPrefer(String emailNewsletterPrefer) {
		this.emailNewsletterPrefer = emailNewsletterPrefer;
	}
	public String getRegularMailPrefer() {
		return regularMailPrefer;
	}
	public void setRegularMailPrefer(String regularMailPrefer) {
		this.regularMailPrefer = regularMailPrefer;
	}
	public String getTelephonePrefer() {
		return telephonePrefer;
	}
	public void setTelephonePrefer(String telephonePrefer) {
		this.telephonePrefer = telephonePrefer;
	}
	public String getMobilePrefer() {
		return mobilePrefer;
	}
	public void setMobilePrefer(String mobilePrefer) {
		this.mobilePrefer = mobilePrefer;
	}
	public String getSmsPrefer() {
		return smsPrefer;
	}
	public void setSmsPrefer(String smsPrefer) {
		this.smsPrefer = smsPrefer;
	}
	public String getInstitutionVip() {
		return institutionVip;
	}
	public void setInstitutionVip(String institutionVip) {
		this.institutionVip = institutionVip;
	}
	public String getRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}
	public String getSignupSource() {
		return signupSource;
	}
	public void setSignupSource(String signupSource) {
		this.signupSource = signupSource;
	}
	public String getAmlStatus() {
		return amlStatus;
	}
	public void setAmlStatus(String amlStatus) {
		this.amlStatus = amlStatus;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
/*	public UserRegistrationDetailVO copyToUserRegistrationVO() {
		return new UserRegistrationDetailVO(this.userId,this.email,this.firstName, this.lastName, this.address,	this.city, this.state, 
				this.zipCode,this.countryId, this.phone, this.title,this.companyName, this.mobile, this.dateOfBirth, this.address1, 
				BooleanUtil.toBoolean(this.status), this.username, BooleanUtil.toBoolean(this.sellerBidder), 
				this.autoSellerGroupId, this.county, BooleanUtil.toBoolean(this.suspend), 
				BooleanUtil.toBoolean(this.vipStatus),this.postedDate,this.preApprovalAmount, BooleanUtil.toBoolean(this.preApproved),
				this.bidderNumber, this.heardAboutUs, this.dreNumnber, this.officeNumber, this.brokerFirmName, this.brokerEmail, 
				this.areaOfSpecialty, this.joinDate, this.bio, BooleanUtil.toBoolean(this.documentSubscription),
				BooleanUtil.toBoolean(this.emailNewsletterPrefer), BooleanUtil.toBoolean(this.regularMailPrefer), 
				BooleanUtil.toBoolean(this.mobilePrefer), BooleanUtil.toBoolean(this.smsPrefer), 
				BooleanUtil.toBoolean(this.telephonePrefer), BooleanUtil.toBoolean(this.rememberMe),
				this.entityType, this.prefix, BooleanUtil.toBoolean(this.amlStatus), this.signupSource, 
				BooleanUtil.toBoolean(this.institutionVip));
	}*/

}
