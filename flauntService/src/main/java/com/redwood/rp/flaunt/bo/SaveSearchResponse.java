package com.redwood.rp.flaunt.bo;

import java.util.List;

public class SaveSearchResponse implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer totalCount;
	
	private List<SaveSearch> saveSearchList;

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<SaveSearch> getSaveSearchList() {
		return saveSearchList;
	}

	public void setSaveSearchList(List<SaveSearch> saveSearchList) {
		this.saveSearchList = saveSearchList;
	}

	
}
