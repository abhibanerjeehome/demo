package com.redwood.rp.flaunt.vo.json.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_EMPTY)
public class CreateUserRegistrationRequestVO implements Serializable {			

		private static final long serialVersionUID = 1L;
		
		public CreateUserRegistrationRequestVO() {
			
		}

		private String emailAddress;
		private String firstName;
		private String lastName;
		private String streetAddress;
		private String city;
		private String state;
		private String zipCode;
		private int countryId;
		private String primaryPhone;
		private String title;
		private String companyName;
		private String password;
		private int uniqueId;
		private String uid;
		private String mobile;
		private boolean emailOptIn;
		private int createdBy;
		private int updatedBy;
		
		@JsonProperty("email_newsletter_consent")
		public boolean isEmailOptIn() {
			return emailOptIn;
		}
		public void setEmailOptIn(boolean emailOptIn) {
			this.emailOptIn = emailOptIn;
		}
		@JsonProperty("cell")
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		@JsonProperty("uid")
		public String getUid() {
			return uid;
		}
		public void setUid(String uid) {
			this.uid = uid;
		}
		
		public int getUniqueId() {
			return uniqueId;
		}
		public void setUniqueId(int uniqueId) {
			this.uniqueId = uniqueId;
		}
		@JsonProperty("password")
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
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
		public int getCountryId() {
			return countryId;
		}
		public void setCountryId(int countryId) {
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
		public int getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(int createdBy) {
			this.createdBy = createdBy;
		}
		public int getUpdatedBy() {
			return updatedBy;
		}
		public void setUpdatedBy(int updatedBy) {
			this.updatedBy = updatedBy;
		}

	}