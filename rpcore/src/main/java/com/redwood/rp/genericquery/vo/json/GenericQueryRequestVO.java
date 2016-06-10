package com.redwood.rp.genericquery.vo.json;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.redwood.rp.genericquery.constant.QueryType;



public class GenericQueryRequestVO {
	
	private String queryName;
	
	private QueryType queryType;
	
	/** True if it is stored procedure */
	private boolean isSP = false;
	
	private List<ParameterItem> parameterList;

	
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public QueryType getQueryType() {
		return queryType;
	}

	public void setQueryType(QueryType queryType) {
		this.queryType = queryType;
	}

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public boolean isSP() {
		return isSP;
	}

	public void setSP(boolean isSP) {
		this.isSP = isSP;
	}

	public List<ParameterItem> getParameterList() {
		return parameterList;
	}

	public void setParameterList(List<ParameterItem> parameterList) {
		this.parameterList = parameterList;
	}
	
	

}
