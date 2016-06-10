package com.redwood.rp.common.bidderreg.cre.vo.json.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class AssetRequestVO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long assetId;
	
	private String eventNumber;
	
	private Long entityId;
	
	private String paymentStatus;
	
	private boolean docuSigned;
	
	@JsonProperty("asset_id")
	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	@JsonProperty("event_number")
	public String getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(String eventNumber) {
		this.eventNumber = eventNumber;
	}

	@JsonProperty("entity_id")
	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	@JsonProperty("payment_type")
	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@JsonProperty("docu_signed")
	public boolean isDocuSigned() {
		return docuSigned;
	}

	public void setDocuSigned(boolean docuSigned) {
		this.docuSigned = docuSigned;
	}
	
	
}
