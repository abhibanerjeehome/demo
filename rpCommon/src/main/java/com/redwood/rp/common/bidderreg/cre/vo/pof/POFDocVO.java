package com.redwood.rp.common.bidderreg.cre.vo.pof;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@SuppressWarnings("deprecation")
@JsonIgnoreProperties
@JsonSerialize(include=Inclusion.NON_EMPTY)
@JsonWriteNullProperties(false)
public class POFDocVO implements Serializable{
	
	private static final long serialVersionUID = -564710000430829L;

	private String originalDocName;
	
	private String displayDocName;
	
	
	public POFDocVO() {
	    super();
    }
	
	@JsonProperty("original_name")
	public String getOriginalDocName() {
		return originalDocName;
	}


	public void setOriginalDocName(String originalDocName) {
		this.originalDocName = originalDocName;
	}

	@JsonProperty("display_name")
	public String getDisplayDocName() {
		return displayDocName;
	}


	public void setDisplayDocName(String displayDocName) {
		this.displayDocName = displayDocName;
	}


}
