package com.redwood.rp.flaunt.vo.json.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import com.redwood.rp.core.vo.json.BaseRestResponseVO;

@JsonPropertyOrder({ "status", "result" })
@JsonSerialize(include=Inclusion.NON_NULL)
@JsonWriteNullProperties(false)

public class UpdateUserResponseVO extends BaseRestResponseVO implements Serializable {	
		
	private static final long serialVersionUID = 1L;

	private String strStatus;
	
	public UpdateUserResponseVO () {
	}
	
	public UpdateUserResponseVO (String strStatus) {
		super();
		this.strStatus = strStatus;
	}
	


	/**
	 * @return the strStatus
	 */
	@JsonProperty("result")		
	public String getStrStatus() {
		return strStatus;
	}

	/**
	 * @param strStatus the strStatus to set
	 */
	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}	

	}

