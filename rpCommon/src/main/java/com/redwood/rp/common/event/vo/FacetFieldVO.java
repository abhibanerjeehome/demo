package com.redwood.rp.common.event.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import org.codehaus.jackson.annotate.JsonProperty;

public class FacetFieldVO {


	@XmlElement(name="filtered_count_fields")
	@JsonProperty("filtered_count_fields")
	private List<FacetFieldCountVO> facetFieldCountVOs;
	
	@XmlElement(name="filtered_field_name")
	@JsonProperty("filtered_field_name")	
	private String facetFieldName;
	
	public FacetFieldVO() {
	    // TODO Auto-generated constructor stub
    }

	public List<FacetFieldCountVO> getFacetFieldCountVOs() {
		return facetFieldCountVOs;
	}

	public void setFacetFieldCountVOs(List<FacetFieldCountVO> facetFieldCountVOs) {
		this.facetFieldCountVOs = facetFieldCountVOs;
	}

	
	public String getFacetFieldName() {
		return facetFieldName;
	}

	public void setFacetFieldName(String facetFieldName) {
		this.facetFieldName = facetFieldName;
	}
}
