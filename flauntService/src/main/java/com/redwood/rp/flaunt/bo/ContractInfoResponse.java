package com.redwood.rp.flaunt.bo;

import java.util.List;

public class ContractInfoResponse {

	private Integer totalCount;
	
	private List<ContractInfo> contractList;

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<ContractInfo> getContractList() {
		return contractList;
	}

	public void setContractList(List<ContractInfo> contractList) {
		this.contractList = contractList;
	}
	
	
}
