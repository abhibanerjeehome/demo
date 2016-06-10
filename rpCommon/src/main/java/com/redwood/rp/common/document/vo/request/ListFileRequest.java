/**
 * ListFileRequest.java DocumentService
 * 
 * Copyright (c) 2013, Auction.com All rights reserved.
 */
package com.redwood.rp.common.document.vo.request;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;

@SuppressWarnings("deprecation")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonWriteNullProperties(false)
public class ListFileRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("property")
	@XmlElement(name = "property")
	private String property;
	@JsonProperty("sections")
	@XmlElement(name = "sections")
	private List<String> listSection;
	@JsonProperty("sold")
	@XmlElement(name = "sold")
	private boolean sold;
	private String accessCode;
	@JsonProperty("event_number")
	@XmlElement(name = "event_number")
	private String eventNumber;

	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}
	/**
	 * @param property
	 *            the property to set
	 */
	public void setProperty(String property) {
		this.property = property;
	}
	/**
	 * @return the listSection
	 */
	public List<String> getListSection() {
		return listSection;
	}
	/**
	 * @param listSection
	 *            the listSection to set
	 */
	public void setListSection(List<String> listSection) {
		this.listSection = listSection;
	}
	/**
	 * @return the sold
	 */
	public boolean isSold() {
		return sold;
	}
	/**
	 * @param sold
	 *            the sold to set
	 */
	public void setSold(boolean sold) {
		this.sold = sold;
	}
	/**
	 * @return the accessCode
	 */
	public String getAccessCode() {
		return accessCode;
	}
	/**
	 * @param accessCode
	 *            the accessCode to set
	 */
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	public String getEventNumber() {
		return eventNumber;
	}
	public void setEventNumber(String eventNumber) {
		this.eventNumber = eventNumber.toLowerCase();
	}
}