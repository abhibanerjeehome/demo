package com.redwood.rp.flaunt.bo;

import java.io.Serializable;
import java.util.Date;

public class AppAuthorizeBO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public AppAuthorizeBO() {
		// TODO Auto-generated constructor stub
	}
	
	private int oauthId;
	private int userId;
	private String email;
	private int createdBy;
	private int updatedBy;
	private int permissionEnable;
	private Date createdDate;
	private Date updatedDate;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getOauthId() {
		return oauthId;
	}
	public void setOauthId(int oauthId) {
		this.oauthId = oauthId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public int getPermissionEnable() {
		return permissionEnable;
	}
	public void setPermissionEnable(int permissionEnable) {
		this.permissionEnable = permissionEnable;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


}
