package com.redwood.rp.genericquery.vo.json;

import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonPropertyOrder({ "name", "value"})
public class ParameterItem {
	
	private String name;
	
	private String value;
	
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

}
