package com.redwood.rp.core.vo.generic;
/**
 *=====================================================================
 * ACP Messaging Based Request Value Object 
 *
 * Message Request Value Object for carrying on request information
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 

import java.io.Serializable;

public class AmsRequestVO implements Serializable {
	private static final long serialVersionUID = 7234405432088829046L;
	private String  clientID;
	private String  destinationName;
	//private String  correlationID;
	private String  serviceName;
	private int     priority;
	private long    expiration;
	private String  messageID;
	private String  messageType;
	private boolean dasIssued; 
	private Object  messagePayload;
	private String  operationType;
	
	/**
	 * No Argument Constructor
	 */
	public AmsRequestVO() {
		reset();
	}
	
	/**
	 * Assign default values
	 */
	public void reset() {
		this.clientID = "";
 		this.destinationName = "";
		//this.correlationID = "";
		this.serviceName =  "";
		this.priority = 4;
	    this.expiration = 0L;
	    this.messageID = "";
	    this.messageType = "";
	    this.dasIssued = false;
 		this.messagePayload = "";
 		this.operationType = "";
	}

	/**
	 * setters and getters 
	 */
	public String getDestinationName() {
	    return destinationName;
    }

    public void setDestinationName(String destinationName) {
	   this.destinationName = destinationName;
    }	
    
//	public String getCorrelationID() {
//		return correlationID;
//	}
//
//	public void setCorrelationID(String correlationID) {
//		this.correlationID = correlationID;
//	}
	
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	

	public String getMessageID() {
		return messageID;
	}
	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	

	public Object getMessagePayload() {
		return messagePayload;
	}
	public void setMessagePayload(Object messagePayload) {
		this.messagePayload = messagePayload;
	}

	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}


	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public Long getExpiration() {
		return expiration;
	}
	public void setExpiration(Long expiration) {
		this.expiration = expiration;
	}

	
	public boolean isDasIssued() {
		return dasIssued;
	}
	public void setDasIssued(boolean dasIssued) {
		this.dasIssued = dasIssued;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	
	
}
