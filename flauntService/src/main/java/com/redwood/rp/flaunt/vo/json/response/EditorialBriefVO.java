package com.redwood.rp.flaunt.vo.json.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;


@JsonWriteNullProperties(false)
@JsonIgnoreProperties
@JsonSerialize(include=Inclusion.NON_EMPTY)

public class EditorialBriefVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int briefId;
	private String briefName;
	private int audienceId;
	private String audienceDescription;
	private int objectiveId;
	private String objectiveDescription;
	private String sourcingReferences;
	private int status;
	private String createdDate;
	private String updatedDate;
	private int createdBy;
	private int updatedBy;

	/**
	 * @return the status
	 */
	@JsonProperty("status")
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the briefId
	 */
	@JsonProperty("brief_id")
	public int getBriefId() {
		return briefId;
	}
	/**
	 * @param briefId the briefId to set
	 */
	public void setBriefId(int briefId) {
		this.briefId = briefId;
	}
	/**
	 * @return the briefName
	 */
	@JsonProperty("brief_name")
	public String getBriefName() {
		return briefName;
	}
	/**
	 * @param briefName the briefName to set
	 */
	public void setBriefName(String briefName) {
		this.briefName = briefName;
	}
	/**
	 * @return the audienceId
	 */
	@JsonProperty("target_audience")
	public int getTargetAudience() {
		return audienceId;
	}
	/**
	 * @param audienceId the audienceId to set
	 */
	public void setTargetAudience(int targetPersona) {
		this.audienceId = targetPersona;
	}
	/**
	 * @return the audienceDescription
	 */
	@JsonProperty("audience_description")
	public String getAudienceDescription() {
		return audienceDescription;
	}
	/**
	 * @param audienceDescription the audienceDescription to set
	 */
	public void setAudienceDescription(String audienceDescription) {
		this.audienceDescription = audienceDescription;
	}
	/**
	 * @return the objectiveId
	 */
	@JsonProperty("objective")
	public int getObjective() {
		return objectiveId;
	}
	/**
	 * @param objectiveId the objectiveId to set
	 */
	public void setObjective(int objective) {
		this.objectiveId = objective;
	}
	/**
	 * @return the objectiveDescription
	 */
	@JsonProperty("objective_description")
	public String getObjectiveDescription() {
		return objectiveDescription;
	}
	/**
	 * @param objectiveDescription the objectiveDescription to set
	 */
	public void setObjectiveDescription(String objectiveDescription) {
		this.objectiveDescription = objectiveDescription;
	}
	/**
	 * @return the sourcingReferences
	 */
	@JsonProperty("sourcing_references")
	public String getSourcingReferences() {
		return sourcingReferences;
	}
	/**
	 * @param sourcingReferences the sourcingReferences to set
	 */
	public void setSourcingReferences(String sourcingReferences) {
		this.sourcingReferences = sourcingReferences;
	}

	/**
	 * @return the createdDate
	 */
	@JsonProperty("created_date")
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the updatedDate
	 */
	@JsonProperty("updated_date")
	public String getUpdatedDate() {
		return updatedDate;
	}
	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the createdBy
	 */
	@JsonProperty("created_by")
	public int getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the updatedBy
	 */
	@JsonProperty("updated_by")
	public int getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
}
