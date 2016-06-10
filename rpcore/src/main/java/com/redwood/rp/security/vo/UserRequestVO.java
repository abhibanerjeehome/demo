package com.redwood.rp.security.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.security.oauth2.common.OAuth2AccessToken;

import com.redwood.rp.core.constant.CoreConst;

public class UserRequestVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tokenType;
	private String tokenValue;
	private Date   tokenExpiration;
	private String grantType;
	private String clientId;
	private String oauthId;

	private String requestIP;
	private String authResource;

	private String seviceName;
	
	private String username;
	private String email;
	private String userId;
	private String firstName;
	private String lastName;
	private String vipSellerAllowed;
	private String dmsUserName;

	private String clientIP;
	private String clientName;
	private String cookie;
	private String sessionId;
	
	private String geoZipCode;
	private String geoMetroCode;
	private String geoRegion;
	private String geoCity;
	private String geoCityState;
	

	public UserRequestVO() {

	}

	public String getOauthId() {
		return oauthId;
	}

	public void setOauthId(String oauthId) {
		this.oauthId = oauthId;
	}

	public String getDmsUserName() {
		return dmsUserName;
	}

	public String getRequestIP() {
		return requestIP;
	}

	public void setRequestIP(String requestIP) {
		this.requestIP = requestIP;
	}

	public void setDmsUserName(String dmsUserName) {
		this.dmsUserName = dmsUserName;
	}


	public String getVipSellerAllowed() {
		return vipSellerAllowed;
	}


	public void setVipSellerAllowed(String vipSellerAllowed) {
		this.vipSellerAllowed = vipSellerAllowed;
	}


	public UserRequestVO(OAuth2AccessToken token){
		this.tokenType = token.getTokenType();
		this.tokenValue = token.getValue();
		this.tokenExpiration = token.getExpiration();
	}

	public boolean isAnonymousToken(){
		if(CoreConst.OAUTH2_GRANT_TYPE_AUTHORIZATION_CODE.equals(grantType) || CoreConst.OAUTH2_GRANT_TYPE_PASSWORD.equals(grantType) 
				|| CoreConst.OAUTH2_GRANT_TYPE_REFRESH_TOKEN.equals(grantType)){
			return false;
		}else{
			return true;
		}
	}

	public String getSeviceName() {
		return seviceName;
	}

	public void setSeviceName(String seviceName) {
		this.seviceName = seviceName;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getTokenValue() {
		return tokenValue;
	}

	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}

	public Date getTokenExpiration() {
		return tokenExpiration;
	}

	public void setTokenExpiration(Date tokenExpiration) {
		this.tokenExpiration = tokenExpiration;
	}

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getGeoZipCode() {
		return geoZipCode;
	}

	public void setGeoZipCode(String geoZipCode) {
		this.geoZipCode = geoZipCode;
	}

	public String getGeoMetroCode() {
		return geoMetroCode;
	}

	public void setGeoMetroCode(String geoMetroCode) {
		this.geoMetroCode = geoMetroCode;
	}

	public String getGeoRegion() {
		return geoRegion;
	}

	public void setGeoRegion(String geoRegion) {
		this.geoRegion = geoRegion;
	}

	public String getGeoCity() {
		return geoCity;
	}

	public void setGeoCity(String geoCity) {
		this.geoCity = geoCity;
	}

	public String getGeoCityState() {
		return geoCityState;
	}

	public void setGeoCityState(String geoCityState) {
		this.geoCityState = geoCityState;
	}
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getAuthResource() {
		return authResource;
	}

	public void setAuthResource(String authResource) {
		this.authResource = authResource;
	}

	public void copyGeoLocation(EdgeLocationVO edgeLocationVO){
		if(edgeLocationVO!=null){
		this.geoCity=edgeLocationVO.getEdgeCity();
		this.geoMetroCode=edgeLocationVO.getEdgeMetroCode();
		this.geoRegion=edgeLocationVO.getEdgeRegion();
		this.geoZipCode=edgeLocationVO.getEdgePostalCode();
		if(this.geoCity!=null &&this.geoRegion!=null)
		this.geoCityState=this.geoCity+this.geoRegion;
		}
		
				
	}
	
	
}
