package com.redwood.rp.common.bidderreg.cre.domain;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

import com.redwood.rp.core.vo.json.BaseRestResponseVO;


public class POF extends BaseRestResponseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 97646680712212935L;
	
	private POF pof;
	
	
   
	public POF() {
	    super();
    }

	Long documentId;
	Long userId;
	int documentType;
	String documentName;
	String documentDisplayName;
	String expirationDate;
	String registrationDocStatus;
	Float pofAmount;
	String docStatusUpdatedNotes; 
	 

	public POF getPof() {
		return pof;
	}

	public void setPof(POF pof) {
		this.pof = pof;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getDocumentType() {
		return documentType;
	}

	public void setDocumentType(int documentType) {
		this.documentType = documentType;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentDisplayName() {
		return documentDisplayName;
	}

	public void setDocumentDisplayName(String documentDisplayName) {
		this.documentDisplayName = documentDisplayName;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getRegistrationDocStatus() {
		return registrationDocStatus;
	}

	public void setRegistrationDocStatus(String registrationDocStatus) {
		this.registrationDocStatus = registrationDocStatus;
	}

	public Float getPofAmount() {
		return pofAmount;
	}

	public void setPofAmount(Float pofAmount) {
		this.pofAmount = pofAmount;
	}

	public String getDocStatusUpdatedNotes() {
		return docStatusUpdatedNotes;
	}

	public void setDocStatusUpdatedNotes(String docStatusUpdatedNotes) {
		this.docStatusUpdatedNotes = docStatusUpdatedNotes;
	}
	
	
	
	

}
