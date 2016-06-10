package com.redwood.rp.flaunt.bo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class BiddingPropertyResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer totalCount;

	private List<BiddingPropertyBO> propertyList;

	@JsonProperty("total_count")
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	@JsonProperty("property_list")
	public List<BiddingPropertyBO> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<BiddingPropertyBO> propertyList) {
		this.propertyList = propertyList;
	}

}
