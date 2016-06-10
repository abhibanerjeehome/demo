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
@XmlRootElement(name = "documents")
public class DocumentVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<FileVO> documents;	
	private DirVO dirDocuments;	
	private boolean shouldAuthenticate;
	// sections to be shown by default.
	private boolean shouldDisplay = true;
	private Boolean requiresAccessCode;

	
	@JsonProperty("documents")
	@XmlElementWrapper(name="documents")
	@XmlElements(value = { @XmlElement(name="file",type=FileVO.class)})
	public List<FileVO> getDocuments() {
		return documents;
	}
	public void setDocuments(List<FileVO> documents) {
		this.documents = documents;
	}

	@JsonProperty("dir_documents")
	@XmlElement(name="dir_documents",type=DirVO.class)
	public DirVO getDirDocuments() {
		return dirDocuments;
	}
	public void setDirDocuments(DirVO dirDocuments) {
		this.dirDocuments = dirDocuments;
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
	/**
	 * @return the requiresAccessCode
	 */
	@JsonProperty("requires_access_code")
	@XmlElement(name="requires_access_code")	
	public Boolean getRequiresAccessCode() {
		return requiresAccessCode;
	}
	/**
	 * @param requiresAccessCode the requiresAccessCode to set
	 */
	public void setRequiresAccessCode(Boolean requiresAccessCode) {
		this.requiresAccessCode = requiresAccessCode;
	}
}
