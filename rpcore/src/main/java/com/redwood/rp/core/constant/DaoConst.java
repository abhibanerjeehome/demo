package com.redwood.rp.core.constant;

/**
 *=====================================================================
 * ACP Core DAO Based Constants
 *
 * Provide constant definitions on ACP Core DAO layer. 
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 
 
public final class DaoConst {

	  //--------------------------------------
	  //  Obsolete Constants
	  //--------------------------------------
	  /* obsolete default*/
	  public static final boolean OBSOLETE_DEFAULT = false;

	  /* obsolete on */
	  public static final boolean OBSOLETE_ON = true;

	  /* obsolete off */
	  public static final boolean OBSOLETE_OFF = false;
	  
	  
	  //--------------------------------------
	  //  Order By Constants
	  //--------------------------------------
	  /* Order By ASC */
	  public static final String ORDER_ASC = "asc";

	  /* Order By DESC */
	  public static final String ORDER_DESC = "desc";
	  
	  
	  //--------------------------------------
	  //  Wild Search  Code Constants
	  //--------------------------------------
	  /* Like wild code */
	  public static final String LIKE_WILDCODE = "%";


	  //--------------------------------------
	  //  Account Status Code Constants
	  //--------------------------------------
	  public static final String ACCOUNT_STATUS_ACTIVE = "ACTIVE";
	  public static final String ACCOUNT_STATUS_INACTIVE = "INACTIVE";
	  public static final String ACCOUNT_STATUS_PENDING = "PENDING";

	  
	  //--------------------------------------------------------------------------------------
	  //  Named Query Constants
	  //  Naming Role:  
	  //    <Named Query>_<Query Type>_<CRUD Operation>_<Entity Names>_[BY|WITH|WITHOUT]_<Keys>
	  //    - <NamedQuery>: NQ
	  //    - <Query Type>: HQL:Hibernate queries, SQL: native query, SP: Store procedures
	  //    - <CRUD Operation>: GET for Read, CREATE for Insert, UPDATE for update, DELETE for delete 
	  //--------------------------------------------------------------------------------------
	  public static final String NQ_HQL_GET_EVENTOFFERLIST_BY_EVENTID = "getEventOfferListByEventId";
	  //public static final String NQ_HQL_GET_AUCTIONDASHDETAILS = "getAuctionDashDetails";
	  

	  //--------------------------------------
	  //  Auction Related Constants
	  //--------------------------------------
	  public static final String PROCESS_STATE_LIVE = "Live";
	  public static final String PROCESS_STATE_SUSPENDED = "Suspended";
	  public static final String PROCESS_STATE_ALL = "";
	  
	  public static final int DEFAULT_PAGE_SIZE = 10;
	  
	  public static final String AJM_TYPE_EVENT = "Event";
      public static final String AJM_TYPE_EVENT_OFFER = "Event Offer Finalization";
	  public static final String AJM_TYPE_AUTO_BID = "Auto Bid";
	  public static final String AJM_TYPE_PROXY_BID = "Proxy Bid";
	  public static final String AJM_TYPE_OVERTIME_AUTOMATION_BID = "Overtime Automation Bid";
	  
}
