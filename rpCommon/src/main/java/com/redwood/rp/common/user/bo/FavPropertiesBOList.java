package com.redwood.rp.common.user.bo;

import java.util.List;

public class FavPropertiesBOList {

	private List<FavPropertiesBO> favProperties;//list of favorite properties
	private int totalCount;// the actual total count 
	public FavPropertiesBOList(){}
	public FavPropertiesBOList(List<FavPropertiesBO> favProperties,
			int totalCount) {
		super();
		this.favProperties = favProperties;
		this.totalCount = totalCount;
	}
	public List<FavPropertiesBO> getFavProperties() {
		return favProperties;
	}
	public void setFavProperties(List<FavPropertiesBO> favProperties) {
		this.favProperties = favProperties;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
}
