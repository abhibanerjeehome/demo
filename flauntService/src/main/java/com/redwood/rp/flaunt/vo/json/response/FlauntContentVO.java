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

public class FlauntContentVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int contentId;
	private String fullContent;
	private String description;
	private String title;
	private int status;
	private int owner;
	private int author;
	private int destination;
	private String rawContent;
	private String formattingSchema;
	private String documentFormat;
	private String category;
	private String personas;
	private String contentType;
	private String createdDate;
	private String updatedDate;
	private int createdBy;
	private int updatedBy;
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
	 * @return the fullContent
	 */
	@JsonProperty("full_content")
	public String getFullContent() {
		return fullContent;
	}
	/**
	 * @param fullContent the fullContent to set
	 */
	public void setFullContent(String fullContent) {
		this.fullContent = fullContent;
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
	 * @return the rawContent
	 */
	@JsonProperty("raw_content")
	public String getRawContent() {
		return rawContent;
	}
	/**
	 * @param rawContent the rawContent to set
	 */
	public void setRawContent(String rawContent) {
		this.rawContent = rawContent;
	}
	/**
	 * @return the formattingSchema
	 */
	@JsonProperty("formatting_schema")
	public String getFormattingSchema() {
		return formattingSchema;
	}
	/**
	 * @param formattingSchema the formattingSchema to set
	 */
	public void setFormattingSchema(String formattingSchema) {
		this.formattingSchema = formattingSchema;
	}
	/**
	 * @return the documentFormat
	 */
	@JsonProperty("document_format")
	public String getDocumentFormat() {
		return documentFormat;
	}
	/**
	 * @param documentFormat the documentFormat to set
	 */
	public void setDocumentFormat(String documentFormat) {
		this.documentFormat = documentFormat;
	}
	/**
	 * @return the category
	 */
	@JsonProperty("category")
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the personas
	 */
	@JsonProperty("personas")
	public String getPersonas() {
		return personas;
	}
	/**
	 * @param personas the personas to set
	 */
	public void setPersonas(String personas) {
		this.personas = personas;
	}
	/**
	 * @return the contentType
	 */
	@JsonProperty("content_type")
	public String getContentType() {
		return contentType;
	}
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
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
	 * @return the destination
	 */
	@JsonProperty("destination")
	public int getDestination() {
		return destination;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(int destination) {
		this.destination = destination;
	}
}