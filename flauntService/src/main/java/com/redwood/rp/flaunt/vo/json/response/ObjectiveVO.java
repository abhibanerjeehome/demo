package com.redwood.rp.flaunt.vo.json.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonPropertyOrder({ "objective_id", "objective", "status"})
@JsonWriteNullProperties(false)
@JsonIgnoreProperties
@JsonSerialize(include=Inclusion.NON_EMPTY)

public class ObjectiveVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int objectiveId;
	private String objective;
	private int status;
	
	@JsonProperty("objective_id")
	public int getObjectiveId() {
		return objectiveId;
	}
	public void setObjectiveId(int roleId) {
		this.objectiveId = roleId;
	}
	
	@JsonProperty("objective")
	public String getObjective() {
		return objective;
	}
	public void setObjective(String role) {
		this.objective = role;
	}

	@JsonProperty("status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
