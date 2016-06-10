package com.redwood.rp.flaunt.bo;

import org.codehaus.jackson.annotate.JsonIgnore;

public class SavedSearchValue {

	private Long savedSearchId;

	private String filterName;

	private String filterValue;
	
	private Long createdBy;
	
	private Long updatedBy;
	
	
	@JsonIgnore
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@JsonIgnore
	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getSavedSearchId() {
		return savedSearchId;
	}

	public void setSavedSearchId(Long savedSearchId) {
		this.savedSearchId = savedSearchId;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

}
