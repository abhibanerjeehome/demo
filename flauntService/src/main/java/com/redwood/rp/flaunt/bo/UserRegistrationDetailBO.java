package com.redwood.rp.flaunt.bo;

import java.io.Serializable;
import java.util.Date;

public class UserRegistrationDetailBO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String role;
	private int roleId;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
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
