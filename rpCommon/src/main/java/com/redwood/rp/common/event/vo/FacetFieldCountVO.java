package com.redwood.rp.common.event.vo;

import javax.xml.bind.annotation.XmlElement;

import org.codehaus.jackson.annotate.JsonProperty;

public class FacetFieldCountVO {

	@XmlElement(name="field_value")
	@JsonProperty("field_value")	
	private String fieldValue;
	
	@XmlElement(name="count")
	@JsonProperty("count")		
	private long count;
	
	
	public String getFieldValue() {
		return fieldValue;
	}
	
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	public long getCount() {
		return count;
	}
	
	public void setCount(long count) {
		this.count = count;
	}
}
