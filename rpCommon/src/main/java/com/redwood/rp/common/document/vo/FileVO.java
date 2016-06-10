/**
 * FileVO.java
 * DocumentService
 * 
 * Copyright (c) 2013, Auction.com
 * All rights reserved.
 */
package com.redwood.rp.common.document.vo;

import java.math.BigInteger;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;

import com.redwood.rp.common.util.DateTimeUtil;

/**
 * @author kjain.ctr
 *
 */
@SuppressWarnings("deprecation")
@JsonWriteNullProperties(false)
@XmlRootElement(name = "file")
public class FileVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private static final String HTTP_SSL = "https://";
	private static final String HTTP = "http://";

	private String displayLabel;
	private String name;
	private BigInteger size;
	private String  path;
	private String stagingPath;
	private Long pathID;
	private String	index; 
	private String  type;
	private String  section;
	private String category;
	private String  anonymousAccess;
	private String  termCond;	
	private String  tracking;
	private String  storageEngine;
	private String  secure;
	private String  includeZip;	
	private String  passwordProtected;
	private String  regularURL;
	private String  webURL;
	private String  thumbnailURL;
	private String downloadBaseURL;
	private String creationDate;
	private String createdBy;
	private String modifiedDate;
	private String modifiedBy;
	private String access;
	private String description;
	private String formattedAcceptedDate;
	private Timestamp AcceptedDate;
	private boolean shouldDisplay = true;
	private boolean shouldAuthenticate;
	private String previewURL;	


	@JsonProperty("display_label")
	@XmlElement(name="display_label")
	public String getDisplayLabel() {
		// if display label is not present, then return the name as is.
		if (StringUtils.isBlank(displayLabel)) {
			displayLabel = getName();
		}
		return displayLabel;
	}

	public void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
		if (StringUtils.isNotBlank(displayLabel)) {
			this.displayLabel = StringEscapeUtils.unescapeHtml(displayLabel);
		}
	}

	/**
	 * @return name
	 */
	@JsonProperty("file_name")
	@XmlElement(name="file_name")
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
		if (StringUtils.isNotBlank(name)) {
			this.name = StringEscapeUtils.unescapeHtml(name);
		}
	}

	/**
	 * @return
	 */
	@JsonProperty("file_size")
	@XmlElement(name="file_size")
	public BigInteger getSize() {
		return size;
	}
	/**
	 * @param size
	 */
	public void setSize(BigInteger size) {
		this.size = size;
	}

	/**
	 * @return
	 */
	@JsonProperty("file_path")
	@XmlElement(name="file_path")
	public String getPath() {
		return path;
	}
	/**
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	@JsonProperty("file_staging_path")
	@XmlElement(name="file_staging_path")
	public String getStagingPath() {
		return stagingPath;
	}
	public void setStagingPath(String stagingPath) {
		this.stagingPath = stagingPath;
	}

	@JsonProperty("file_path_id")
	@XmlElement(name="file_path_id")
	public Long getPathID() {
		return pathID;
	}
	public void setPathID(Long pathID) {
		this.pathID = pathID;
	}

	@JsonProperty("file_index")
	@XmlElement(name="file_index")
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * @return
	 */
	@JsonProperty("file_type")
	@XmlElement(name="file_type")
	public String getType() {
		return type;
	}
	/**
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the section
	 */
	@JsonProperty("section")
	@XmlElement(name="section")
	public String getSection() {
		return section;
	}
	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}
	/**
	 * @return the anonymousAccess
	 */
	@JsonProperty("anonymous_access")
	@XmlElement(name="anonymous_access")
	public String getAnonymousAccess() {
		return anonymousAccess;
	}
	/**
	 * @param anonymousAccess the anonymousAccess to set
	 */
	public void setAnonymousAccess(String anonymousAccess) {
		this.anonymousAccess = anonymousAccess;
	}
	/**
	 * @return the termCond
	 */
	@JsonProperty("term_cond")
	@XmlElement(name="term_cond")
	public String getTermCond() {
		return termCond;
	}
	/**
	 * @param termCond the termCond to set
	 */
	public void setTermCond(String termCond) {
		this.termCond = termCond;
	}
	/**
	 * @return the tracking
	 */
	@JsonProperty("tracking")
	@XmlElement(name="tracking")
	public String getTracking() {
		return tracking;
	}
	/**
	 * @param tracking the tracking to set
	 */
	public void setTracking(String tracking) {
		this.tracking = tracking;
	}

	/**
	 * @return the secure
	 */
	@JsonProperty("secure")
	@XmlElement(name="secure")
	public String getSecure() {
		return secure;
	}
	/**
	 * @param secure the secure to set
	 */
	public void setSecure(String secure) {
		this.secure = secure;
	}
	/**
	 * @return the includeZip
	 */
	@JsonProperty("include_zip")
	@XmlElement(name="include_zip")
	public String getIncludeZip() {
		return includeZip;
	}
	/**
	 * @param includeZip the includeZip to set
	 */
	public void setIncludeZip(String includeZip) {
		this.includeZip = includeZip;
	}
	/**
	 * @return the passwordProtected
	 */
	@JsonProperty("password_protected")
	@XmlElement(name="password_protected")
	public String getPasswordProtected() {
		return passwordProtected;
	}
	/**
	 * @param passwordProtected the passwordProtected to set
	 */
	public void setPasswordProtected(String passwordProtected) {
		this.passwordProtected = passwordProtected;
	}

	/**
	 * @return the regularURL
	 */
	@JsonProperty("regular_url")
	@XmlElement(name="regular_url")
	public String getRegularURL() {
		if (StringUtils.isNotBlank(regularURL) && !StringUtils.startsWith(regularURL, HTTP)
				&& !StringUtils.startsWith(regularURL, HTTP_SSL)) {
			regularURL = HTTP + regularURL;
		}
		return regularURL;
	}
	/**
	 * @param passwordProtected the passwordProtected to set
	 */
	public void setRegularURL(String regularURL) {
		this.regularURL = regularURL;
	}

	/**
	 * @return the webURL
	 */
	@JsonProperty("web_url")
	@XmlElement(name="web_url")
	public String getWebURL() {
		if (StringUtils.isNotBlank(webURL) && !StringUtils.startsWith(webURL, HTTP)
				&& !StringUtils.startsWith(webURL, HTTP_SSL)) {
			webURL = HTTP + webURL;
		}
		return webURL;
	}
	/**
	 * @param webURL the webURL to set
	 */
	public void setWebURL(String webURL) {
		this.webURL = webURL;
	}

	/**
	 * @return the thumbnailURL
	 */
	@JsonProperty("thumbnail_url")
	@XmlElement(name="thumbnail_url")
	public String getThumbnailURL() {
		if (StringUtils.isNotBlank(thumbnailURL) && !StringUtils.startsWith(thumbnailURL, HTTP)
				&& !StringUtils.startsWith(thumbnailURL, HTTP_SSL)) {
			thumbnailURL = HTTP + thumbnailURL;
		}
		return thumbnailURL;
	}
	/**
	 * @param thumbnailURL the thumbnailURL to set
	 */
	public void setThumbnailURL(String thumbnailURL) {
		this.thumbnailURL = thumbnailURL;
	}

	@JsonProperty("category")
	@XmlElement(name="category")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	@JsonProperty("storage_engine")
	@XmlElement(name="storage_engine")
	public String getStorageEngine() {
		return storageEngine;
	}
	public void setStorageEngine(String storageEngine) {
		this.storageEngine = storageEngine;
	}

	@JsonProperty("download_base_url")
	@XmlElement(name="download_base_url")
	public String getDownloadBaseURL() {
		return downloadBaseURL;
	}

	public void setDownloadBaseURL(String downloadBaseURL) {
		this.downloadBaseURL = downloadBaseURL;
	}

	/**
	 * @return the dirPath
	 */
	@JsonProperty("creation_date")
	@XmlElement(name="creation_date")
	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the dirPath
	 */
	@JsonProperty("created_by")
	@XmlElement(name="created_by")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the dirPath
	 */
	@JsonProperty("modified_date")
	@XmlElement(name="modified_date")
	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@JsonProperty("modified_by")
	@XmlElement(name="modified_by")
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@JsonProperty("access")
	@XmlElement(name="access")
	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	@JsonProperty("description")
	@XmlElement(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the formattedAcceptedDate
	 */
	@JsonProperty("formatted_accepted_date")
	@XmlElement(name="formatted_accepted_date")	
	public String getFormattedAcceptedDate() {
		return formattedAcceptedDate;
	}

	/**
	 * @param formattedAcceptedDate the formattedAcceptedDate to set
	 */
	public void setFormattedAcceptedDate(String formattedAcceptedDate) {
		this.formattedAcceptedDate = formattedAcceptedDate;
	}

	/**
	 * @return the acceptedDate
	 */
	@JsonProperty("accepted_date")
	@XmlElement(name="accepted_date")
	@XmlJavaTypeAdapter(DateTimeUtil.class)	
	public Timestamp getAcceptedDate() {
		return AcceptedDate;
	}

	/**
	 * @param acceptedDate the acceptedDate to set
	 */
	public void setAcceptedDate(Timestamp acceptedDate) {
		AcceptedDate = acceptedDate;
	}

	@JsonProperty("should_authenticate")
	@XmlElement(name="should_authenticate")
	public boolean isShouldAuthenticate() {
		return shouldAuthenticate;
	}

	public void setShouldAuthenticate(boolean shouldAuthenticate) {
		this.shouldAuthenticate = shouldAuthenticate;
	}
	
	@JsonProperty("should_display")
	@XmlElement(name="should_display")
	public boolean isShouldDisplay() {
		return shouldDisplay;
	}
	public void setShouldDisplay(boolean shouldDisplay) {
		this.shouldDisplay = shouldDisplay;
	}

	@JsonProperty("preview_url")
	@XmlElement(name="preview_url")
	public String getPreviewURL() {
		return previewURL;
	}

	public void setPreviewURL(String previewURL) {
		this.previewURL = previewURL;
	}}