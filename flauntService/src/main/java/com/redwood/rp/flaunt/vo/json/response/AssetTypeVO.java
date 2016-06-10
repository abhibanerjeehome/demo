package com.redwood.rp.flaunt.vo.json.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonPropertyOrder({ "asset_type_id", "asset_type", "status"})
@JsonWriteNullProperties(false)
@JsonIgnoreProperties
@JsonSerialize(include=Inclusion.NON_EMPTY)

public class AssetTypeVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int assetTypeId;
	private String assetType;
	private int status;
	
	@JsonProperty("asset_type_id")
	public int getAssetTypeId() {
		return assetTypeId;
	}
	public void setAssetTypeId(int roleId) {
		this.assetTypeId = roleId;
	}
	
	@JsonProperty("asset_type")
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String role) {
		this.assetType = role;
	}

	@JsonProperty("status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
