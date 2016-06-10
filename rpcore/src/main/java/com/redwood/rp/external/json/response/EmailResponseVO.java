package com.redwood.rp.external.json.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;

@SuppressWarnings("deprecation")
@JsonWriteNullProperties(false)
//@XmlRootElement(name = "message")
@XmlType
/**
 * The Class EmailResponseVO.
 * @author rpandya
 */
public class EmailResponseVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String status_message;
	
	@JsonProperty("status_message")
	@XmlElementWrapper(name="status_message")
	public String getStatus_message() {
		return status_message;
	}
	
	@JsonProperty("status_message")
	@XmlElementWrapper(name="status_message")
	public void setStatus_message(String status_message) {
		this.status_message = status_message;
	}
}
