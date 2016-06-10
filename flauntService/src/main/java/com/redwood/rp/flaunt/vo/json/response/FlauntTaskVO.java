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
public class FlauntTaskVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int taskId;
	private String title;
	private int briefId;
	private int assetType;
	private int titleQuantity;
	private int minWords;
	private int maxWords;
	private String description;
	private String seoInstructions;
	private int status;
	private int contentId;
	private EditorialBriefVO brief;
	private FlauntContentVO content;
	private int trackingId;
	private int priority;
	private int workingTime;
	private int publishTime;
	private String templateName;
	private int folderId;
	private String folderName;
	private int requestedBy;
	private int owner;
	private int assignedTo;
	private int author;
	private String startDate;
	private String endDate;
	private String createdDate;
	private String updatedDate;
	private String publishDate;
	private int createdBy;
	private int updatedBy;
	/**
	 * @return the taskId
	 */
	@JsonProperty("task_id")
	public int getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	/**
	 * @return the title
	 */
	@JsonProperty("title")
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the seoInstructions
	 */
	@JsonProperty("seo_instructions")
	public String getSeoInstructions() {
		return seoInstructions;
	}
	/**
	 * @param seoInstructions the seoInstructions to set
	 */
	public void setSeoInstructions(String seoInstructions) {
		this.seoInstructions = seoInstructions;
	}
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
	 * @return the contentId
	 */
	@JsonProperty("content_id")
	public int getContentId() {
		return contentId;
	}
	/**
	 * @param contentId the contentId to set
	 */
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	/**
	 * @return the trackingId
	 */
	@JsonProperty("tracking_id")
	public int getTrackingId() {
		return trackingId;
	}
	/**
	 * @param trackingId the trackingId to set
	 */
	public void setTrackingId(int trackingId) {
		this.trackingId = trackingId;
	}
	/**
	 * @return the priority
	 */
	@JsonProperty("priority")
	public int getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	/**
	 * @return the assetType
	 */
	@JsonProperty("asset_type")
	public int getAssetType() {
		return assetType;
	}
	/**
	 * @param assetType the assetType to set
	 */
	public void setAssetType(int assetType) {
		this.assetType = assetType;
	}

	/**
	 * @return the titleQuantity
	 */
	@JsonProperty("title_quantity")
	public int getTitleQuantity() {
		return titleQuantity;
	}
	/**
	 * @param titleQuantity the titleQuantity to set
	 */
	public void setTitleQuantity(int titleQuantity) {
		this.titleQuantity = titleQuantity;
	}
	/**
	 * @return the minWords
	 */
	@JsonProperty("min_words")
	public int getMinWords() {
		return minWords;
	}
	/**
	 * @param minWords the minWords to set
	 */
	public void setMinWords(int minWords) {
		this.minWords = minWords;
	}
	/**
	 * @return the maxWords
	 */
	@JsonProperty("max_words")
	public int getMaxWords() {
		return maxWords;
	}
	/**
	 * @param maxWords the maxWords to set
	 */
	public void setMaxWords(int maxWords) {
		this.maxWords = maxWords;
	}
	/**
	 * @return the requestedBy
	 */
	@JsonProperty("requested_by")
	public int getRequestedBy() {
		return requestedBy;
	}
	/**
	 * @param requestedBy the requestedBy to set
	 */
	public void setRequestedBy(int requestedBy) {
		this.requestedBy = requestedBy;
	}
	/**
	 * @return the owner
	 */
	@JsonProperty("owner")
	public int getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(int owner) {
		this.owner = owner;
	}
	/**
	 * @return the assignedTo
	 */
	@JsonProperty("assigned_to")
	public int getAssignedTo() {
		return assignedTo;
	}
	/**
	 * @param assignedTo the assignedTo to set
	 */
	public void setAssignedTo(int assignedTo) {
		this.assignedTo = assignedTo;
	}
	/**
	 * @return the author
	 */
	@JsonProperty("author")
	public int getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(int author) {
		this.author = author;
	}

	/**
	 * @return the startDate
	 */
	@JsonProperty("start_date")
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	@JsonProperty("end_date")
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	 * @return the publishDate
	 */
	@JsonProperty("publish_date")
	public String getPublishDate() {
		return publishDate;
	}
	/**
	 * @param publishDate the publishDate to set
	 */
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
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
	/**
	 * @return the content
	 */
	@JsonProperty("content")
	public FlauntContentVO getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(FlauntContentVO content) {
		this.content = content;
	}
	/**
	 * @return the brief
	 */
	@JsonProperty("editorial_brief_details")
	public EditorialBriefVO getBrief() {
		return brief;
	}
	/**
	 * @param brief the brief to set
	 */
	public void setBrief(EditorialBriefVO brief) {
		this.brief = brief;
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
	public void setBriefId(int brief) {
		this.briefId = brief;
	}
	/**
	 * @return the workingTime
	 */
	@JsonProperty("working_time")
	public int getWorkingTime() {
		return workingTime;
	}
	/**
	 * @param workingTime the workingTime to set
	 */
	public void setWorkingTime(int writerWorkingTime) {
		this.workingTime = writerWorkingTime;
	}
	/**
	 * @return the publishTime
	 */
	@JsonProperty("publish_time")
	public int getPublishTime() {
		return publishTime;
	}
	/**
	 * @param publishTime the publishTime to set
	 */
	public void setPublishTime(int writerRevisionTime) {
		this.publishTime = writerRevisionTime;
	}
	/**
	 * @return the templateName
	 */
	@JsonProperty("template_name")
	public String getTemplateName() {
		return templateName;
	}
	/**
	 * @param templateName the templateName to set
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	/**
	 * @return the folderId
	 */
	@JsonProperty("folder_id")
	public int getFolderId() {
		return folderId;
	}
	/**
	 * @param folderId the folderId to set
	 */
	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}
	/**
	 * @return the folderName
	 */
	@JsonProperty("folder_name")
	public String getFolderName() {
		return folderName;
	}
	/**
	 * @param folderName the folderName to set
	 */
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}


}
