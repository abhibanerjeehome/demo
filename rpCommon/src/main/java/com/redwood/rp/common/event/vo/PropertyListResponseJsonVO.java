package com.redwood.rp.common.event.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.redwood.rp.core.vo.json.BaseRestResponseVO;


@SuppressWarnings("deprecation")
@JsonPropertyOrder({ "status", "result" })
@JsonSerialize(include=Inclusion.NON_EMPTY)
@JsonIgnoreProperties
@JsonWriteNullProperties(false)
@JsonAutoDetect
public class PropertyListResponseJsonVO extends BaseRestResponseVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	public PropertyListResponseJsonVO(){
		
	}
	
    @XmlElement(name="result")
	@JsonProperty("result")		
	private SearchResultsVO searchResultsVO;
    
    public PropertyListResponseJsonVO(SearchResultsVO searchResultsVO) {
		super();
		this.searchResultsVO = searchResultsVO;
	}

	public SearchResultsVO getSearchResultsVO() {
		return searchResultsVO;
	}


	public void setSearchResultsVO(SearchResultsVO searchResultsVO) {
		this.searchResultsVO = searchResultsVO;
	}
}
