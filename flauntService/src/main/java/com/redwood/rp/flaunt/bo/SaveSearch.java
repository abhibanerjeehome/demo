package com.redwood.rp.flaunt.bo;

import java.util.Date;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_EMPTY)
@JsonWriteNullProperties(false)
public class SaveSearch implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long saveSearchId;

	private Long userId;

	private String savedSearchName;

	private String note;

	private Boolean emailAlertSignup;

	private Integer emailAlertType;
	
	private Integer filterVersion;

	private Date createdDate;

	private Long createdBy;

	private Date updatedDate;

	private Long updatedBy;
	
	private Integer requestSourceId;
	
	private Map<String, String> filters;
	
	@JsonProperty("filter_version")
	public Integer getFilterVersion() {
		return filterVersion;
	}

	public void setFilterVersion(Integer filterVersion) {
		this.filterVersion = filterVersion;
	}

	@JsonProperty("saved_search_id")
	public Long getSaveSearchId() {
		return saveSearchId;
	}

	public void setSaveSearchId(Long saveSearchId) {
		this.saveSearchId = saveSearchId;
	}

	@JsonProperty("user_id")	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@JsonProperty("saved_search_name")	
	public String getSavedSearchName() {
		return savedSearchName;
	}

	public void setSavedSearchName(String savedSearchName) {
		this.savedSearchName = savedSearchName;
	}

	public Map<String, String> getFilters() {
		return filters;
	}

	public void setFilters(Map<String, String> filters) {
		this.filters = filters;
	}

	@JsonProperty("email_alert_signup")
	public Boolean isEmailAlertSignup() {
		return emailAlertSignup;
	}

	public void setEmailAlertSignup(boolean emailAlertSignup) {
		this.emailAlertSignup = emailAlertSignup;
	}

	@JsonProperty("email_alert_type")
	public Integer getEmailAlertType() {
		return emailAlertType;
	}

	public void setEmailAlertType(Integer emailAlertType) {
		this.emailAlertType = emailAlertType;
	}

	@JsonIgnore
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@JsonIgnore
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@JsonIgnore
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@JsonIgnore
	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getRequestSourceId() {
		return requestSourceId;
	}

	public void setRequestSourceId(Integer requestSourceId) {
		this.requestSourceId = requestSourceId;
	}

}
