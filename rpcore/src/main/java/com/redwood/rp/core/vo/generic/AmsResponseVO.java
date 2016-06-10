package com.redwood.rp.core.vo.generic;
/**
 *=====================================================================
 * ACP Messaging Based Request Value Object 
 *
 * Message Response Value Object for carrying on response information
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 


import java.io.Serializable;

import com.redwood.rp.core.constant.AmsConst;
import com.redwood.rp.core.constant.CoreConst;

public class AmsResponseVO implements Serializable {
	private static final long serialVersionUID = -2183258813564749050L;

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
	
	/**
	 * Constructor
	 */
	public AmsResponseVO (AmsRequestVO msgReqVo) {
		if ( msgReqVo != null ) {
			// convert to DasResponseVO
	 		this.destinationName = msgReqVo.getDestinationName();
			//this.correlationID = msgReqVo.getCorrelationID();
			this.priority = msgReqVo.getPriority();
		    this.messageID = msgReqVo.getMessageID();
			this.serviceName =  msgReqVo.getServiceName();
		    
		    // clean up by default, Should be updated by DAS
		    cleanUpForDasSetting();
	 		
		} else {
			init();			
		}
	}
	
	/**
	 * reset properties 
	 */
	private void init() {
		this.serviceName =  "";
 		this.destinationName = "";
		//this.correlationID = "";
		this.priority = 4;
	    this.messageID = "";
	    cleanUpForDasSetting();
	}

	private void cleanUpForDasSetting() {
 		// signal for auction engine, data broker or system Manager
		this.clientID = CoreConst.MESSAGE_DESTINATION_SERVICE_REDWOODENGINE;

 		// Note: the request's expiration is the sum of the time-to-live value specified 
 		//       by the sender and the GMT time at the send out. MUST clean up here.
        this.expiration = 0L;
	    this.messageType = AmsConst.MESSAGE_TYPE_XML;
	    this.dasIssued = false;
 		this.messagePayload = null;
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
		if ( priority <= 0 ) {
			this.priority = 4;
		} else {
			this.priority = priority;
		}
	}
	public void setPriority(Long priority) {
		if (priority == null || priority <= 0 ) {
			this.priority = 4;
		} else {
			this.priority = priority.intValue();
		}
	}
	public void setPriority(String priorityString) {
		if (priorityString == null || priorityString.length() == 0 ) {
			this.priority = 4;
		} else {
			setPriority(new Long(priorityString));
		}
	}
	
	public long getExpiration() {
		return expiration;
	}
	public void setExpiration(long expiration) {
		if ( expiration <= 0L) {
			this.expiration = 0L;
		} else {
			// change to without expiration setting for test
			// this.expiration = expiration;
			this.expiration = expiration;
		}
	}
	public void setExpiration(Long expiration) {
		if (expiration == null || expiration <= 0L) {
			this.expiration = 0L;
		} else {
			// change to without expiration setting for test
			// this.expiration = expiration.longValue();
			this.expiration = expiration;
		}
	}
	public void setExpiration(String expirationString) {
		if (expirationString == null || expirationString.length() == 0 ) {
			this.expiration = 0L;
		} else {
			setExpiration(new Long(expirationString));
		}
	}
	
	public boolean isDasIssued() {
		return dasIssued;
	}
	public void setDasIssued(boolean dasIssued) {
		this.dasIssued = dasIssued;
	}
	
}
