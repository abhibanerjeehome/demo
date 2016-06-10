package com.redwood.rp.core.constant;
/**
 *=====================================================================
 * ACP JSF and IceFaces based Constants
 *
  * Provide constant definitions on ACP JSF and Icefaces web layer. 
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 

public final class FacesConst {

	//--------------------------------------
    // Application Configurations
	//--------------------------------------
	public static final String APPLICATION_DV = "dev";
	public static final String APPLICATION_SG = "staging";
	public static final String APPLICATION_PR = "production";
	public static final String APPLICATION_FC = "failover";
	public static final String APPLICATION_URL_DV = "ladv";
	public static final String APPLICATION_URL_SG = "laqa";
	public static final String APPLICATION_URL_PR = "lapr";
	
	public static final String APPLICATION_INSTANCE_MANAGER = "manager";
	public static final String APPLICATION_INSTANCE_ENGINE  = "engine";
	public static final String APPLICATION_INSTANCE_BROKER  = "broker";
	
	public static final String SELECT_VALUE_NON  = "";
	public static final String SELECT_LABLE_NON  = "No select";

	public static final String MESSAGE_TYPE_XML    = "xml";
	public static final String MESSAGE_TYPE_OBJECT = "object";
	
	public static final int    DT_PAGESIZE_DEFAULT = 20;
	public static final int    DT_PAGESIZE_10      = 10;
	public static final int    DT_PAGESIZE_20      = 20;
	public static final int    DT_PAGESIZE_30      = 30;
	public static final int    DT_PAGESIZE_40      = 40;
	public static final int    DT_PAGESIZE_50      = 50;
	
	
	//--------------------------------------
    //  Menu Bar Configurations
	//--------------------------------------
	/* Menu Bar */
	public static final String MENU_HOME = "Home";
	public static final String PAGE_SELECTED = "selectedPage";
	public static final String PAGE_SHOW_MESSAGE = "Show Message";
	public static final String PAGE_WEBSERVICE_RESPONSE = "Webservice Response";
	
	
	//--------------------------------------
    //  Side Tree Section Configurations
	//--------------------------------------
	/* Side Section : Root */
	public static final String SIDE_ROOT = "Home";

	/* Side Section : MQM */
	public static final String SIDE_MQM            = "MQM";
	public static final String SIDE_MQM_MONITOR    = "MQ Monitor";
	public static final String SIDE_MQM_AQUEUE     = "Redwood Queues";
	public static final String SIDE_MQM_MSGLOG     = "Logged Messages";
	public static final String SIDE_MQM_MSGTRAFFIC = "Messaging Traffic";
	
	/* Side Section : ADM */
	public static final String SIDE_ADM                  = "ADM";
	public static final String PAGE_REDWOODDATA_MANAGER  = "Redwood Data Manager";

	/* Side Section : AJM */
	public static final String SIDE_AJM                     = "AJM";
	public static final String SIDE_AJM_REDWOODJOB_MONITOR  = "Redwood Job Monitor";
	
	/* Side Section : UTILITY */
	public static final String SIDE_TOOL                = "Tool";
	public static final String SIDE_TOOL_MSGUT          = "Messaging Utility";
	public static final String SIDE_TOOL_WSUT           = "Webservice Utility";
	public static final String SIDE_TOOL_AUTOBID_DEMO   = "Auto Bid Demo";
	
	//--------------------------------------
    // MQM Admin Configurations
	//--------------------------------------
	public static final String HQGROUP_LBPOLICYTYPE_RANDOM  = "Random";
	public static final String HQGROUP_LBPOLICYTYPE_ROUND   = "Round Robin";
	public static final String HQGROUP_LBPOLICYTYPE_USER    = "User Policy";

	public static final String HQINSTANCE_TYPE_LIVE         = "Live";
	public static final String HQINSTANCE_TYPE_BACKUP       = "Backup";
	public static final String HQINSTANCE_STATUS_STARTED    = "Started";
	public static final String HQINSTANCE_STATUS_STOPPED     = "Stopped";

	public static final String DESTINATION_TYPE_ALL         = "all";
	public static final String DESTINATION_TYPE_MANAGER     = "manager";
	public static final String DESTINATION_TYPE_ENGINE      = "engine";
	public static final String DESTINATION_TYPE_BROKER      = "broker";
	public static final String DESTINATION_TYPE_ALL_LABEL   = "All Services";
	
	public static final String DESTINATION_TYPE_MANAGER_LABEL = "System Manager";
	public static final String DESTINATION_TYPE_ENGINE_LABEL  = "Redwood Engine";
	public static final String DESTINATION_TYPE_BROKER_LABEL  = "Data Broker";
	
	public static final String DESTINATION_STATUS_STARTED = "Started";
	public static final String DESTINATION_STATUS_STOPED  = "Stoped";
	
	public static final String DESTINATION_TYPE_QUEUE = "queue";
	public static final String DESTINATION_TYPE_TOPIC = "topic";

	public static final String DESTINATION_SERVICE_MANAGER = "system manager";
	public static final String DESTINATION_SERVICE_ENGINE  = "redwood engine";
	public static final String DESTINATION_SERVICE_BROKER  = "data broker";

	public static final String DESTINATION_FUNCTION_REQUEST = "request";
	public static final String DESTINATION_FUNCTION_RESPONSE = "response";
	
	public static final String TAG_TABLE_MQM_GROUP        = "mqmMainForm:hqGroupTable:";
	public static final String TAG_TABLE_MQM_INSTANCE     = "mqmMainForm:hqInstanceTable:";
	public static final String TAG_TABLE_MQM_DEST         = "mqmMainForm:hqDestinationTable:";

	public static final String TAG_BTN_MQM_GROUP_ADD      = ":btnGroupAdd";
	public static final String TAG_BTN_MQM_GROUP_EDIT     = ":btnGroupEdit";
	public static final String TAG_BTN_MQM_GROUP_REMOVE   = ":btnGroupRemove";

	public static final String TAG_BTN_MQM_INSTANCE_ADD    = ":btnInstanceAdd";
	public static final String TAG_BTN_MQM_INSTANCE_EDIT   = ":btnInstanceEdit";
	public static final String TAG_BTN_MQM_INSTANCE_REMOVE = ":btnInstanceRemove";
	
	public static final String TAG_BTN_MQM_DEST_ADD    = ":btnDestinationAdd";
	public static final String TAG_BTN_MQM_DEST_EDIT   = ":btnDestinationEdit";
	public static final String TAG_BTN_MQM_DEST_REMOVE = ":btnDestinationRemove";
	public static final String TAG_BTN_MQM_COUNT       = ":btnCount";
	public static final String TAG_BTN_MQM_LIST        = ":btnList";
	public static final String TAG_BTN_MQM_DROP        = ":btnDrop";
	public static final String TAG_BTN_MQM_PAUSE       = ":btnPause";
	public static final String TAG_BTN_MQM_RESUME      = ":btnResume";
	
	public static final String TAG_TABLE_MSG_LOG       = "loggedMessageMainForm:msgLogTable:";
	public static final String TAG_BTN_MSG_LOG_VIEW      = ":btnPayloadView";
	
	public static final String SELECTED_ROW_INDEX = "selectedRowIndex";
	
	public static final String DEST_MSG_OPTION_ALL_LABEL = "All";
	public static final String DEST_MSG_OPTION_ALL = "all";
	public static final String DEST_MSG_OPTION_BY_CORR_ID_LABEL = "By Correlation Id";
	public static final String DEST_MSG_OPTION_BY_CORR_ID = "byCorrelationId";
	
	public static final String PROP_JMS_CORRELATION_ID = "JMSCorrelationID";
	public static final String PROP_JMS_PRIORITY = "JMSPriority";
	public static final String PROP_JMS_EXPIRATION = "JMSExpiration";
	public static final String PROP_JMS_TIMESTAMP = "JMSTimestamp";
	public static final String PROP_JMS_MESSAGE_ID = "JMSMessageID";
	public static final String PROP_MESSAGE_ID = "messageID";
	
	//--------------------------------------
    // MQM AQI Configurations
	//--------------------------------------
	public static final String AQI_USECASE_ALL        = "all";
	public static final String AQI_USECASE_AQ         = "available";
	public static final String AQI_USECASE_SQ         = "scheduled";
	public static final String AQI_USECASE_UQ         = "used history";
	
	public static final String AQI_USECASE_LABEL_ALL  = "All Assignments";
	public static final String AQI_USECASE_LABEL_AQ   = "Available Queues";
	public static final String AQI_USECASE_LABEL_SQ   = "Scheduled Queues";
	public static final String AQI_USECASE_LABEL_UQ   = "Used Queue History";
	
    public static final String PROCESS_STATE_LIVE       = "Live";
	public static final String PROCESS_STATE_SUSPENDED  = "Suspended";
	
	public static final String COL_EVENT_DEST_MAP_ID = "eventDestinationMapId";
	public static final String COL_QUEUE_NAME = "queueName";
	public static final String COL_QUEUE_TYPE = "queueType";
	public static final String COL_QUEUE_NO = "queueNo";
	
	public static final String COL_CAMPAIGN_CODE = "campaignCode";
	public static final String COL_CAMPAIGN_TITLE = "campaignTitle";
	public static final String COL_MARKET_KEY = "marketKey";
	public static final String COL_MARKET_TITLE = "marketTitle";
	public static final String COL_EVENT_ID = "eventId";
	public static final String COL_EVENT_CODE = "eventCode";
	public static final String COL_SALE_TYPE = "saleType";
	public static final String COL_EVENT_START_DATE = "eventStartDate";
	public static final String COL_EVENT_END_DATE = "eventEndDate";
	public static final String COL_PROCESS_STATE = "processState";
	public static final String COL_EVENT_STATUS = "eventStatus";
	public static final String COL_CURRENT_ROUND = "currentRound";
	
	// Chart related constants
	public static final String CHART_REDWOOD_QUEUE_VS_SALE_TYPE = "Redwood Queue Usage Statistics By Sale Type";
	public static final String CHART_REDWOOD_QUEUE_VS_ASSET_TYPE = "Redwood Queue Usage Statistics By Asset Type";
	
	public static final String CHART_TITLE_RQ_VS_ST = "Redwood Queues Vs Sale Types";
	public static final String CHART_XAXIS_TITLE_RQ_VS_ST = "Sale Types";
	public static final String CHART_YAXIS_TITLE_RQ_VS_ST = "Redwood Queues";
	
	public static final String LABEL_CHART_TYPE_BAR = "Bar Chart";
	public static final String LABEL_CHART_TYPE_LINE = "Line Chart";
	public static final String LABEL_CHART_TYPE_PIE_3D = "Pie 3D Chart";
	
	public static final String CHART_TYPE_BAR = "bar";
	public static final String CHART_TYPE_LINE = "line";
	public static final String CHART_TYPE_PIE_3D = "pie3d";
	
	
	//--------------------------------------------
    // MQ Message Log and Traffic Configurations
	//--------------------------------------------
	public static final String MQM_USECASE_ALL        = "all";
	public static final String MQM_USECASE_AE         = "engine";
	public static final String MQM_USECASE_DAC        = "broker";
	public static final String MQM_USECASE_SM         = "manager";
	
	public static final String MQM_USECASE_LABEL_ALL  = "All Services";
	public static final String MQM_USECASE_LABEL_AE   = "Redwood Engine";
	public static final String MQM_USECASE_LABEL_DAC  = "Data Broker";
	public static final String MQM_USECASE_LABEL_SM   = "System Manager";
	
	//--------------------------------------------
	// Redwood Jobs Management Configurations
	//--------------------------------------------
	public static final String AJM_TYPE_EVENT = "Event";
	public static final String AJM_TYPE_EVENT_OFFER = "Event Offer Finalization";
	public static final String AJM_TYPE_AUTO_BID = "Auto Bid";
	public static final String AJM_TYPE_PROXY_BID = "Proxy Bid";
	public static final String AJM_TYPE_OVERTIME_AUTOMATION_BID = "Overtime Automation Bid";

	public static final String AJM_OPTION_ACTIVE_LABEL = "Active";
	public static final String AJM_OPTION_ACTIVE = "active";
	public static final String AJM_OPTION_HISTORY_LABEL = "History";
	public static final String AJM_OPTION_HISTORY = "history";
	public static final String AJM_OPTION_ALL_LABEL = "All";
	public static final String AJM_OPTION_ALL = "all";
	
	public static final String AJM_COL_ENTITY_ID = "entityId";
	public static final String AJM_COL_JOB_GROUP = "jobGroup";
	public static final String AJM_COL_JOB_NAME = "jobName";
	public static final String AJM_COL_SCHEDULED_TRIGGERING_DATE = "scheduledTriggeringDate";
	
	public static final String GROUP_CREATE_EVENT = "createEvent";
	public static final String GROUP_FINALIZE_EVENT_OFFER = "finilizeEventOffer";
	
	public static final int DEFAULT_PAGE_NO = 1;
	public static final int DEFAULT_PAGE_SIZE = 10;
	
	//--------------------------------------
    //  Faces Message key Configurations
	//--------------------------------------
	public static final String KEY_APPLICATION_EXCEPTION = "exception.application";
	public static final String KEY_MESSAGE_REQUIRED = "error.messaging.utility.message.required";

	// MQM 
	public static final String KEY_MQM_ROW_INVALID = "error.mqm.message.row.selected.invalid";
	public static final String KEY_MQM_QUERY_NODATA  = "info.mqm.message.query.nodata";
	public static final String KEY_MQM_QUERY_INVALID = "error.mqm.message.query.invalid";
	public static final String KEY_MQM_ADD_SUCCESS = "info.mqm.message.add.success";
	public static final String KEY_MQM_ADD_FAILURE = "error.mqm.message.add.failure";
	public static final String KEY_MQM_UPDATE_SUCCESS = "info.mqm.message.update.success";
	public static final String KEY_MQM_UPDATE_FAILURE = "error.mqm.message.update.failure";
	public static final String KEY_MQM_DELETE_SUCCESS = "info.mqm.message.delete.success";
	public static final String KEY_MQM_DELETE_FAILURE = "error.mqm.message.delete.failure";
	public static final String KEY_MQM_PAUSE_FAILURE = "error.mqm.message.pause.failure";
	public static final String KEY_MQM_RESUME_FAILURE = "error.mqm.message.resume.failure";
	public static final String KEY_MQM_CONNECTION_FAILURE = "error.mqm.message.connect.failure";
	public static final String KEY_MQM_HQINSTANCE_STATUS_BACKUP = "error.mqm.hqinstalnce.status.backup";

	public static final String KEY_MQM_DROP_MSGS_SUCCESS = "info.mqm.drop.messages.success";
	public static final String KEY_MQM_DROP_MSGS_FAILURE = "error.mqm.drop.messages.failure";
	public static final String KEY_MQM_NO_MSGS_DROP = "error.mqm.no.msgs.drop";
	public static final String KEY_MQM_NO_MSGS_DROP_BY_SELECTOR = "error.mqm.no.msgs.drop.by.selector";
	public static final String KEY_MQM_CORRELATION_ID_NULL="error.mqm.correlation.id.null";

	public static final String KEY_MQM_LIST_MSGS_FAILURE = "error.mqm.list.msgs.failure";
	public static final String KEY_MQM_VIEW_MSG_FAILURE = "error.mqm.view.msgs.failure";
	
	public static final String KEY_MQM_TOPIC_NOT_SUPPORTED = "info.mqm.topic.not.supported";
	public static final String KEY_MQM_NO_MSGS_FOUND = "info.mqm.no.msgs.found";
	public static final String KEY_MQM_NO_MSG_FOUND = "info.mqm.no.msg.found";
	
	// AQM
	public static final String KEY_AQM_FROM_DATE_INVALID = "error.aqm.fromdate.invalid";
	
	// Auto Bid Demo
	public static final String KEY_ABD_BID_AMT_NULL = "error.abd.bid.amt.null";
	public static final String KEY_ABD_BID_REQUEST_INVALID = "error.abd.bid.request.invalid";
	public static final String KEY_ABD_BID_UPDATE_FAILURE = "error.abd.bid.update.failure";
	public static final String KEY_ABD_BID_UPDATE_SUCCESS = "info.abd.bid.update.success";
	
	// AJM
	public static final String KEY_AJM_FROM_DATE_REQUIRED = "error.ajm.from.date.required";
	public static final String KEY_AJM_TO_DATE_REQUIRED = "error.ajm.to.date.required";
	public static final String KEY_AJM_FROM_DATE_INVALID = "error.ajm.from.date.invalid";
	public static final String KEY_AJM_TRIGGERING_DATE_REQUIRED = "error.ajm.triggering.date.required";
	public static final String KEY_AJM_TRIGGERING_DATE_INVALID = "error.ajm.triggering.date.invalid";

	public static final String KEY_AJM_NO_JOBS_FOUND = "info.ajm.no.jobs.found";
	public static final String KEY_AJM_SEARCH_CRITERIA_INVALID = "error.ajm.search.criteria.invalid";
	public static final String KEY_AJM_JOB_UPDATE_SUCCESS = "info.ajm.job.update.success";
	public static final String KEY_AJM_JOB_UPDATE_FAIL = "error.ajm.job.update.fail";
	public static final String KEY_AJM_JOB_DELETE_SUCCESS = "info.ajm.job.delete.success";
	public static final String KEY_AJM_JOB_DELETE_FAIL = "error.ajm.job.delete.fail";
	
	// ADM
	public static final String KEY_ADM_NO_DATA_FOUND = "info.adm.no.data.found";
	public static final String KEY_ADM_SEARCH_CRITERIA_INVALID = "error.adm.search.criteria.invalid";
	public static final String KEY_ADM_AUTOBID_ID_INVALID = "error.adm.autobid.id.invalid";
	public static final String KEY_ADM_START_DATE_REQUIRED = "error.adm.start.date.required";
	public static final String KEY_ADM_END_DATE_REQUIRED = "error.adm.end.date.required";
	public static final String KEY_ADM_END_DATE_REVISED_REQUIRED = "error.adm.end.date.revised.required";
	public static final String KEY_ADM_START_DATE_INVALID = "error.adm.start.date.invalid";
	public static final String KEY_ADM_END_DATE_INVALID = "error.adm.end.date.invalid";
	public static final String KEY_ADM_END_DATE_REVISED_INVALID = "error.adm.end.date.revised.invalid";
	
	public static final String KEY_ADM_RESET_SUCCESS = "info.adm.reset.success";
	public static final String KEY_ADM_RESET_FAILURE = "error.adm.reset.failure";
	public static final String KEY_ADM_PAGE_NO_INVALID = "error.adm.page.no.invalid";
	
	public static final String KEY_ADM_EVENT_TIMER_NOT_REQUIRED = "info.event.timer.not.required";
	public static final String KEY_ADM_EO_TIMER_NOT_REQUIRED = "info.eo.timer.not.required";
	public static final String KEY_ADM_EOAM_CACHING_NOT_REQUIRED = "info.eoam.caching.not.required";
	public static final String KEY_ADM_TIMER_NOT_CREATED = "info.timer.not.created";
	public static final String KEY_ADM_TIMER_NOT_UPDATED = "info.timer.not.updated";
	
}
