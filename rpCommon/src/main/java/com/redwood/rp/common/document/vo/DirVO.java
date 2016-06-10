/**
 * DirVO.java
 * DocumentService
 * 
 * Copyright (c) 2013, Auction.com
 * All rights reserved.
 */
package com.redwood.rp.common.document.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;

@SuppressWarnings("deprecation")
@JsonWriteNullProperties(false)
@XmlRootElement(name = "directory")
public class DirVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String dirName;

	private String dirPath;

	private String creationDate;

	private String createdBy;

	private String modifiedDate;

	private String modifiedBy;

	private List<FileVO> fileList;

	private List<DirVO> dirList;
	
	private boolean requiresAdditionalAuthentication;
	
	private String folderId;

	public DirVO() {
	}	

	/**
	 * @return the dirName
	 */
	@JsonProperty("dir_name")
	@XmlElement(name="dir_name")
	public String getDirName() {
		return dirName;
	}

	/**
	 * @param dirName the dirName to set
	 */
	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	/**
	 * @return the dirPath
	 */
	@JsonProperty("dir_path")
	@XmlElement(name="dir_path")
	public String getDirPath() {
		return dirPath;
	}

	/**
	 * @param dirPath the dirPath to set
	 */
	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
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

	/**
	 * @return the fileList
	 */
	@JsonProperty("file_list")
	@XmlElementWrapper(name="file_list")
	@XmlElements(value = { @XmlElement(name="file",type=FileVO.class)})
	public List<FileVO> getFileList() {
		return fileList;
	}

	/**
	 * @param fileList the fileList to set
	 */
	public void setFileList(List<FileVO> fileList) {
		this.fileList = fileList;
	}

	/**
	 * @return the dirList
	 */
	@JsonProperty("dir_list")
	@XmlElementWrapper(name="dir_list")
	@XmlElements(value = { @XmlElement(name="directory",type=DirVO.class)})
	public List<DirVO> getDirList() {
		return dirList;
	}

	/**
	 * @param dirList the dirList to set
	 */
	public void setDirList(List<DirVO> dirList) {
		this.dirList = dirList;
	}

	@JsonProperty("requires_additional_authentication")
	@XmlElement(name="requires_additional_authentication")
	public boolean isRequiresAdditionalAuthentication() {
		return requiresAdditionalAuthentication;
	}

	public void setRequiresAdditionalAuthentication(
			boolean requiresAdditionalAuthentication) {
		this.requiresAdditionalAuthentication = requiresAdditionalAuthentication;
	}

	@JsonProperty("folder_id")
	@XmlElement(name="folder_id")	
	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}
}
