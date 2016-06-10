package com.redwood.rp.flaunt.vo.json.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonPropertyOrder({ "audience_id", "audience", "status"})
@JsonWriteNullProperties(false)
@JsonIgnoreProperties
@JsonSerialize(include=Inclusion.NON_EMPTY)

public class AudienceVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int audienceId;
	private String audience;
	private int status;
	
	@JsonProperty("audience_id")
	public int getAudienceId() {
		return audienceId;
	}
	public void setAudienceId(int roleId) {
		this.audienceId = roleId;
	}
	
	@JsonProperty("target_audience")
	public String getAudience() {
		return audience;
	}
	public void setAudience(String role) {
		this.audience = role;
	}

	@JsonProperty("status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
