package com.redwood.rp.core.constant;
/**
 *=====================================================================
 * ACP Messaging service based Constants
 *
 * Provide constant definitions on ACP core messaging layer. 
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 


import java.util.Map;
import java.util.LinkedHashMap;


public class AmsConst {
	
	//----------------------------------------------
    //  Messaging Configurations 
	//----------------------------------------------
	//public static final String CORRELATION_ID   = "CorrelationID";
	public static final String KEY_SERVICE_NAME = "serviceName";
	public static final String KEY_EOAM_ID      = "EventOfferActionMatrixId";
	public static final String LAST_VALUE_MSG   = "_HQ_LVQ_NAME";
	
	//----------------------------------------------
    //  Messaging Destination Configurations 
	//----------------------------------------------
	public static final String DESTINATION_ENGINE  = "ENGINE";
	public static final String DESTINATION_BROKER  = "BROKER";
	public static final String DESTINATION_MANAGER = "MANAGER";

	//----------------------------------------------
    //  Message Type Configurations 
	//----------------------------------------------
	public static final String MESSAGE_TYPE_XML  = "XML";
	public static final String MESSAGE_TYPE_OBJECT = "OBJECT";

	
	//----------------------------------------------
    //  Service Configurations 
	//----------------------------------------------
	
	/* ========= Analytics Services =========  */
	// Logging Services	
	public static final String SERVICE_ANALYTICS_ERRORLOGGING_OBJMSG   = "Analytics.loggingErrorObjectMessage";
	public static final String SERVICE_ANALYTICS_ERRORLOGGING_JSONMSG  = "Analytics.loggingErrorJSONMessage";

 	
	//----------------------------------------------
    //  Service Name Map 
	//----------------------------------------------
	public static Map<String,String> serviceMap= new LinkedHashMap<String,String>();
	
	static {
		
		/* ========= Analytics Services ========= */
		// Error Logging Services
		serviceMap.put(SERVICE_ANALYTICS_ERRORLOGGING_OBJMSG,  "loggingErrorObjectMessage");
		serviceMap.put(SERVICE_ANALYTICS_ERRORLOGGING_JSONMSG, "loggingErrorJSONMessage");

	}
	
	
	/**
	 * get service name by service name key
	 * @param  serviceNameKey
	 * @return service name
	 */
	public static String getServiceName(String serviceNameKey){
		return serviceMap.get(serviceNameKey);
	}

	/**
	 * get services
	 * @return Map<String,String>
	 */
	public static Map<String,String> getServiceMap(){
		return serviceMap;
	}
	
}
