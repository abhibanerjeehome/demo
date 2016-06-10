package com.redwood.rp.common.event.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import org.codehaus.jackson.annotate.JsonProperty;

public class SearchResultsVO {

	@XmlElement(name="assets")
	@JsonProperty("assets")	
	private List<PropertyInfoVO> propertyInfoVOList;
	
	@XmlElement(name="count")
	@JsonProperty("count")	
	private long totalAssetCount;
	
	@XmlElement(name="filtered_columns_with_count")
	@JsonProperty("filtered_columns_with_count")
	private List<FacetFieldVO> filteredColumedForCount;
	
	public SearchResultsVO() {
	    // TODO Auto-generated constructor stub
    }
	

	
	public List<PropertyInfoVO> getPropertyInfoVOList() {
		return propertyInfoVOList;
	}


	public void setPropertyInfoVOList(List<PropertyInfoVO> propertyInfoVOList) {
		this.propertyInfoVOList = propertyInfoVOList;
	}


	public long getTotalAssetCount() {
		return totalAssetCount;
	}


	public void setTotalAssetCount(long totalAssetCount) {
		this.totalAssetCount = totalAssetCount;
	}


	
	public List<FacetFieldVO> getFilteredColumedForCount() {
		return filteredColumedForCount;
	}

	@XmlElement(name="filtered_columns_with_count")
	@JsonProperty("filtered_columns_with_count")	
	public void setFilteredColumedForCount(
			List<FacetFieldVO> filteredColumedForCount) {
		this.filteredColumedForCount = filteredColumedForCount;
	}
	
	
	
	
}
