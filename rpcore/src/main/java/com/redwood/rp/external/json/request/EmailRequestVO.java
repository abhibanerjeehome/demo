package com.redwood.rp.external.json.request;

import java.io.Serializable;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

import com.redwood.rp.external.json.request.EMailDataSetRequestVO;



/**
 * The Class EmailRequestVO.
 */
public class EmailRequestVO implements Serializable{

	/** The Constant serialVersionUID. */
	
	
	private static final long serialVersionUID = 1L;
	
	private String toEmail ;
	private String fromEmail;
	private String toFirstName;
	private String toLastName;
	private String fromFullName;
	private String destination;
	private String source;
	private String content;
	private String subject;
	private String emailType;
	private String fromPhoneNumber;
	private String company = "Auction.com-Dev";
	
	private EMailDataSetRequestVO eMailDataSetRequestVO;
	private Map<String, String> dataMap;
	
	@JsonProperty("to_email")
	public String getToEmail() {
		return toEmail;
	}
	
	@JsonProperty("to_email")
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	
	@JsonProperty("from_email")
	public String getFromEmail() {
		return fromEmail;
	}
	
	@JsonProperty("from_email")
	public void setFromEmail(String from_email) {
		this.fromEmail = from_email;
	}
	
	@JsonProperty("to_first_name")
	public String getToFirstName() {
		return toFirstName;
	}

	@JsonProperty("to_first_name")
	public void setToFirstName(String toFirstName) {
		this.toFirstName = toFirstName;
	}
	
	@JsonProperty("to_last_name")
	public String getToLastName() {
		return toLastName;
	}

	@JsonProperty("to_last_name")
	public void setToLastName(String toLastName) {
		this.toLastName = toLastName;
	}

	@JsonProperty("destination")
	public String getDestination() {
		return destination;
	}
	
	@JsonProperty("destination")
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	@JsonProperty("source")
	public String getSource() {
		return source;
	}
	
	@JsonProperty("source")
	public void setSource(String source) {
		this.source = source;
	}
	
	@JsonProperty("content")
	public String getContent() {
		return content;
	}
	
	@JsonProperty("content")
	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonProperty("subject")
	public String getSubject() {
		return subject;
	}
	
	@JsonProperty("subject")
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@JsonProperty("email_type")
	public String getEmailType() {
		return emailType;
	}
	
	@JsonProperty("email_type")
	public void setEmailType(String email_type) {
		this.emailType = email_type;
	}
	
	@JsonProperty("company")
	public String getCompany() {
		return company;
	}
	
	@JsonProperty("company")
	public void setCompany(String company) {
		this.company = company;
	}
	
	@JsonProperty("data_set")
	public EMailDataSetRequestVO geteMailDataSetRequestVO() {
		return eMailDataSetRequestVO;
	}
	
	@JsonProperty("data_set")
	public void seteMailDataSetRequestVO(EMailDataSetRequestVO eMailDataSetRequestVO) {
		this.eMailDataSetRequestVO = eMailDataSetRequestVO;
	}

	@JsonProperty("datamap")
	public Map<String, String> getDataMap() {
		return dataMap;
	}

	@JsonProperty("datamap")
	public void setDataMap(Map<String, String> dataMap) {
		this.dataMap = dataMap;
	}

	@JsonProperty("fullName")
	public String getFromFullName() {
		return fromFullName;
	}

	@JsonProperty("fullName")
	public void setFromFullName(String toFullName) {
		this.fromFullName = toFullName;
	}

	@JsonProperty("fromPhoneNumber")
	public String getFromPhoneNumber() {
		return fromPhoneNumber;
	}

	@JsonProperty("fromPhoneNumber")
	public void setFromPhoneNumber(String fromPhoneNumber) {
		this.fromPhoneNumber = fromPhoneNumber;
	}
}
