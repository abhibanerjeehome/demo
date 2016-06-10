package com.redwood.rp.flaunt.das.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonPropertyOrder({ "states"})
@JsonIgnoreProperties
@JsonSerialize(include=Inclusion.NON_EMPTY)
@JsonWriteNullProperties(false)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "States")
public class StateVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int stateId;
	private String stateName;
	private String stateCode;
	private int status;
	private int percent;
	private int maxPercent;
	private int region;
	private int countryId;
	private int displayOrder;
	
	@XmlElement(name="id")
	@JsonProperty("id")
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	@XmlElement(name="name")
	@JsonProperty("name")
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	@XmlElement(name="abbreviation")
	@JsonProperty("abbreviation")
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	@XmlElement(name="status")
	@JsonProperty("status")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@XmlElement(name="percent")
	@JsonProperty("percent")
	public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	@XmlElement(name="max_percent")
	@JsonProperty("max_percent")
	public int getMaxPercent() {
		return maxPercent;
	}
	public void setMaxPercent(int maxPercent) {
		this.maxPercent = maxPercent;
	}
	@XmlElement(name="region")
	@JsonProperty("region")
	public int getRegion() {
		return region;
	}
	public void setRegion(int region) {
		this.region = region;
	}
	@XmlElement(name="country_id")
	@JsonProperty("country_id")
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	@XmlElement(name="display_order")
	@JsonProperty("display_order")
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	
	

}
