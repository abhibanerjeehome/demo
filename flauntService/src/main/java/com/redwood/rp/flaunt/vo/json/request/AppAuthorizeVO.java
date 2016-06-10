package com.redwood.rp.flaunt.vo.json.request;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class AppAuthorizeVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public AppAuthorizeVO() {
		// TODO Auto-generated constructor stub
	}
	public AppAuthorizeVO(int oauthId, String email,
			int createdBy, int updatedBy, int permissionEnable,
			Date createdDate, Date updatedDate) {

		this.oauthId=oauthId;
		this.email=email;
		this.createdBy=createdBy;
		this.updatedBy=updatedBy;
		this.permissionEnable=permissionEnable;
		this.createdDate=createdDate;
		this.updatedDate=updatedDate;
	}
	
	private int oauthId;
	private String email;
	private int createdBy;
	private int updatedBy;
	private int permissionEnable;
	private Date createdDate;
	private Date updatedDate;
	
	@JsonProperty("oauth_id")
	public int getOauthId() {
		return oauthId;
	}
	public void setOauthId(int oauthId) {
		this.oauthId = oauthId;
	}
	
	@JsonProperty("email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@JsonProperty("created_by")
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	
	@JsonProperty("updated_by")
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@JsonProperty("permission_enable")
	public int getPermissionEnable() {
		return permissionEnable;
	}
	public void setPermissionEnable(int permissionEnable) {
		this.permissionEnable = permissionEnable;
	}
	
	@JsonProperty("created_date")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@JsonProperty("updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
