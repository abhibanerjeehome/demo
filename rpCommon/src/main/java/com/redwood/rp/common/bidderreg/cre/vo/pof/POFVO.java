package com.redwood.rp.common.bidderreg.cre.vo.pof;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@SuppressWarnings("deprecation")
@JsonIgnoreProperties
@JsonSerialize(include=Inclusion.NON_EMPTY)
@JsonWriteNullProperties(false)
public class POFVO implements Serializable{
	
	private static final long serialVersionUID = -564710000430829L;

	private Float currentPOFAmount;
	
	private List<POFDocVO> pendingDocs;
	
	private String status;

	public POFVO() {
	    super();
    }
	
	@JsonProperty("current_pof_amount")
	public Float getCurrentPOFAmount() {
		return currentPOFAmount;
	}
	
	public void setCurrentPOFAmount(Float currentPOFAmount) {
		this.currentPOFAmount = currentPOFAmount;
	}
	
	@JsonProperty("pending_doc_list")
	public List<POFDocVO> getPendingDocs() {
		return pendingDocs;
	}

	public void setPendingDocs(List<POFDocVO> pendingDocs) {
		this.pendingDocs = pendingDocs;
	}
	
	@JsonProperty("pof_status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
