package com.redwood.rp.common.email.request.vo;

import java.io.Serializable;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("from_email")
	private String fromEmail;
	// comma separated values.
	@JsonProperty("to_email")
	private String toEmail;
	@JsonProperty("subject")
	private String subject;
	@JsonProperty("content")
	private String content;
	@JsonProperty("email_type")
	private String emailType;
	@JsonProperty("source")
	private String source;
	@JsonProperty("block_email")
	private boolean blockEmail;
	@JsonProperty("first_name")
	private String toFirstName;
	@JsonProperty("last_name")
	private String toLastName;
	@JsonProperty("company")
	private String company;
	@JsonProperty("priority")
	private String priority;
	@JsonProperty("asset_type")
	private String assetType;
	@JsonProperty("job_title")
	private String jobTitle;
	@JsonProperty("phone")
	private String phone;
	@JsonProperty("state")
	private String state;
	@JsonProperty("zip_code")
	private String zipCode;
	@JsonProperty("country")
	private String country;
	@JsonProperty("saved_search_id")
	private Integer savedSearchId;
	@JsonProperty("match_asset_count")
	private Integer matchAssetCount;
	@JsonProperty("is_log_email")
	private boolean logEmail;
	@JsonProperty("data_set")
	private Map<String, String> fieldsForTemplate;
	@JsonProperty("cc_email")
	private String ccList;
	@JsonProperty("bcc_email")
	private String bccList;

	/**
	 * @return the fromEmail
	 */
	public String getFromEmail() {
		return fromEmail;
	}
	/**
	 * @param fromEmail
	 *            the fromEmail to set
	 */
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	/**
	 * @return the toEmail
	 */
	public String getToEmail() {
		return toEmail;
	}
	/**
	 * @param toEmail
	 *            the toEmail to set
	 */
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the emailType
	 */
	public String getEmailType() {
		return emailType;
	}
	/**
	 * @param emailType
	 *            the emailType to set
	 */
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the blockEmail
	 */
	public boolean isBlockEmail() {
		return blockEmail;
	}
	/**
	 * @param blockEmail
	 *            the blockEmail to set
	 */
	public void setBlockEmail(boolean blockEmail) {
		this.blockEmail = blockEmail;
	}
	/**
	 * @return the toFirstName
	 */
	public String getToFirstName() {
		return toFirstName;
	}
	/**
	 * @param toFirstName
	 *            the toFirstName to set
	 */
	public void setToFirstName(String toFirstName) {
		this.toFirstName = toFirstName;
	}
	/**
	 * @return the toLastName
	 */
	public String getToLastName() {
		return toLastName;
	}
	/**
	 * @param toLastName
	 *            the toLastName to set
	 */
	public void setToLastName(String toLastName) {
		this.toLastName = toLastName;
	}
	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}
	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	/**
	 * @return the assetType
	 */
	public String getAssetType() {
		return assetType;
	}
	/**
	 * @param assetType
	 *            the assetType to set
	 */
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	/**
	 * @param jobTitle
	 *            the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the saveSearchId
	 */
	public Integer getSavedSearchId() {
		return savedSearchId;
	}
	/**
	 * @param saveSearchId
	 *            the saveSearchId to set
	 */
	public void setSavedSearchId(Integer savedSearchId) {
		this.savedSearchId = savedSearchId;
	}
	/**
	 * @return the matchAssetCount
	 */
	public Integer getMatchAssetCount() {
		return matchAssetCount;
	}
	/**
	 * @param matchAssetCount
	 *            the matchAssetCount to set
	 */
	public void setMatchAssetCount(Integer matchAssetCount) {
		this.matchAssetCount = matchAssetCount;
	}

	/**
	 * @return the logEmail
	 */
	public boolean isLogEmail() {
		return logEmail;
	}
	/**
	 * @param logEmail
	 *            the logEmail to set
	 */
	public void setLogEmail(boolean logEmail) {
		this.logEmail = logEmail;
	}
	/**
	 * @return the fieldsForTemplate
	 */
	public Map<String, String> getFieldsForTemplate() {
		return fieldsForTemplate;
	}
	/**
	 * @param fieldsForTemplate
	 *            the fieldsForTemplate to set
	 */
	public void setFieldsForTemplate(Map<String, String> fieldsForTemplate) {
		this.fieldsForTemplate = fieldsForTemplate;
	}
	/**
	 * @return the ccList
	 */
	public String getCcList() {
		return ccList;
	}
	/**
	 * @param ccList
	 *            the ccList to set
	 */
	public void setCcList(String ccList) {
		this.ccList = ccList;
	}
	/**
	 * @return the bccList
	 */
	public String getBccList() {
		return bccList;
	}
	/**
	 * @param bccList
	 *            the bccList to set
	 */
	public void setBccList(String bccList) {
		this.bccList = bccList;
	}
}