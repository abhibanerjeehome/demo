package com.redwood.rp.common.event.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@SuppressWarnings("deprecation")
@JsonIgnoreProperties
@JsonSerialize(include=Inclusion.NON_EMPTY)
@JsonWriteNullProperties(false)
public class PropertyInfoVO implements Serializable {

	private static final long serialVersionUID = -1L;

	@JsonProperty("property_address")
	private String propertyAddress;
	@JsonProperty("property_type")
	private String propertyType;
	@JsonProperty("est_opening_bid")
	private String estOpeningBid;
	@JsonProperty("square_footage")
	private String squareFootage;
	@JsonProperty("foreclosure_no")
	private String foreClosureNo;
	@JsonProperty("venue_location")
	private String venueLocation;
	@JsonProperty("item_number")
	private String itemNumber;
	@JsonProperty("global_prop_id")
	private String globalPropID;
	@JsonProperty("property_state")
	private String propertyState;
	@JsonProperty("property_city")
	private String propertyCity;
	@JsonProperty("property_county")
	private String propertyCounty;
	@JsonProperty("deposit_amount")
	private String depositAmount;
	@JsonProperty("property_zip")
	private String propertyZip;
	@JsonProperty("thumbnail")
	private String thumbnail;
	@JsonProperty("beds")
	private String beds;
	@JsonProperty("baths")
	private String baths;
	@JsonProperty("sqft")
	private String sqft;
	@JsonProperty("longitude")
	private String longitude;
	@JsonProperty("latitude")
	private String latitude;
	@JsonProperty("item_id")
	private String itemId;
	@JsonProperty("property_id")
	private String propertyId;
	@JsonProperty("seller_code")
	private String sellerCode;
	@JsonProperty("lot_size")
	private String lotSize;
	@JsonProperty("product_type")
	private String productType;
	@JsonProperty("auction_id")
	private String auctionId;
	@JsonProperty("auction_type")
	private String auctionType;
	@JsonProperty("auction_start_date")
	private String auctionStartDate;
	@JsonProperty("auction_end_date")
	private String auctionEndDate;
	@JsonProperty("previously_valued_to")
	private String previouslyValuedTo;
	@JsonProperty("total_est_dbt")
	private String totalEstimatedDebt;
	@JsonProperty("status")
	private String status;
	@JsonProperty("property_occupancy_status")
	private String propertyOccupancyStatus;
	@JsonProperty("venue_id")
	private String venueId;
	@JsonProperty("property_venue_code")
	private String propertyVenueCode;
	@JsonProperty("auction_number")
	private String auctionNumber;
	@JsonProperty("is_luxury_event")
	private String isLuxuryEvent;
	@JsonProperty("property_state_name")
	private String stateName;
	@JsonProperty("asset_level_deposit")
	private String assetLevelDeposit;
	@JsonProperty("bid_pending")
	private String bidPending;
	@JsonProperty("geo_region")
	private String geoRegion;
	@JsonProperty("property_description")
	private String propertyDescription;
	@JsonProperty("is_platinum")
	private String isPlatinum;

	// -- newly added for reporting
	@JsonProperty("property_name")
	private String propertyName=null;
	@JsonProperty("comments")
	private String comments=null;
	@JsonProperty("nrasf_unit")
	private String NRASFUnit=null;
	@JsonProperty("number_of_buildings")
	private String numberOfBuildings=null;
	@JsonProperty("total_room_count")
	private String totalRoomCount=null;
	@JsonProperty("number_of_stories")
	private String numberOfstories=null;
	@JsonProperty("number_of_units")
	private String numberOfUnits=null;
	@JsonProperty("occupancy_asof_date")
	private String occupancyAsOfDate=null;
	@JsonProperty("noi")
	private String noi=null;
	@JsonProperty("noi_date")
	private String noiDate=null;
	@JsonProperty("noi_period")
	private String noiPeriod=null;
	@JsonProperty("lien_position")
	private String lienPosition=null;
	@JsonProperty("property_original_principal")
	private String propertyOriginalPrincipal=null;
	@JsonProperty("property_original_principal_unit")
	private String propertyOriginalPrincipalPerUnit=null;
	@JsonProperty("property_current_principal")
	private String propertyCurrentPrincipal=null;
	@JsonProperty("property_principal_perunit")
	private String propertyCurrentPrincipalPerUnit=null;
	@JsonProperty("property_loan_term")
	private String propertyLoanTerm=null;
	@JsonProperty("property_loan_term_months")
	private String propertyLoanTermMos=null;
	@JsonProperty("note_date")
	private String noteDate=null;
	@JsonProperty("month_seasoned")
	private String monthSeasoned=null;
	@JsonProperty("remaining_term")
	private String remainingTerm=null;
	@JsonProperty("remaining_term_month")
	private String remainingTermMonth=null;
	@JsonProperty("property_io_term")
	private String propertyIoTerm=null;
	@JsonProperty("property_io_term_month")
	private String propertyIoTermMonth=null;
	@JsonProperty("property_repayment_type")
	private String propertyRepaymentType=null;
	@JsonProperty("amortization_period")
	private String amortizationPeriod=null;
	@JsonProperty("amortization_period_months")
	private String amortizationPeriodMonths=null;
	@JsonProperty("property_balloon")
	private String propertyBalloon=null;
	@JsonProperty("last_payment_date")
	private String lastPaymentDate=null;
	@JsonProperty("maturity_date")
	private String maturityDate=null;
	@JsonProperty("monthly_payment_amount")
	private String monthlyPaymentAmount=null;
	@JsonProperty("annual_payment_amount")
	private String annualPaymentAmount=null;
	@JsonProperty("rate_type")
	private String rateType=null;
	@JsonProperty("property_index_rate")
	private String propertyIndexRate=null;
	@JsonProperty("avg_daily_rate")
	private String avgDailyRate=null;
	@JsonProperty("property_original_interest_rate")
	private String propertyOriginalInterestRate=null;
	@JsonProperty("ceiling_rate")
	private String ceilingRate=null;
	@JsonProperty("property_interest_floor")
	private String propertyInterestFloor=null;
	@JsonProperty("property_margin")
	private String propertyMargin=null;
	@JsonProperty("property_initial_fixed_period")
	private String propertyInitialFixedPeriod=null;
	@JsonProperty("property_caps")
	private String propertyCaps=null;
	@JsonProperty("property_frequencyof_adj")
	private String propertyFrequencyOfAdjustments=null;
	@JsonProperty("property_firstrate_adj")
	private String propertyFirstRateAdjustment=null;
	@JsonProperty("property_next_adj_date")
	private String propertyNextAdjustmentDate=null;
	@JsonProperty("accural_method")
	private String accrualMethod=null;
	@JsonProperty("current_ltv")
	private String currentLTV=null;
	@JsonProperty("current_dscr")
	private String currentDSCR=null;
	@JsonProperty("recourse")
	private String recourse=null;
	@JsonProperty("impounds")
	private String impounds=null;
	@JsonProperty("prepayment_provision")
	private String prepaymentProvision=null;
	@JsonProperty("participation")
	private String participation=null;
	@JsonProperty("participation_lead")
	private String participationLead=null;
	@JsonProperty("appraisal_date")
	private String appraisalDate=null;
	@JsonProperty("most_recent_appraised_value")
	private String mostRecentAppraisedValue=null;
	@JsonProperty("prev_value_date")
	private String previouslyValuedDate=null;
	@JsonProperty("original_appraised_value")
	private String originalAppraisedValue=null;
	@JsonProperty("broker_firm")
	private String brokerFirm=null;
	@JsonProperty("current_broker_agent")
	private String currentBrokerAgent=null;
	@JsonProperty("broker_email")
	private String brokerEmail=null;
	@JsonProperty("broker_phone")
	private String brokerPhone=null;
	@JsonProperty("broker_firm2")
	private String brokerFirm2=null;
	@JsonProperty("current_broker_agent2")
	private String currentBrokerAgent2=null;
	@JsonProperty("broker_email2")
	private String brokerEmail2=null;
	@JsonProperty("broker_phone2")
	private String brokerPhone2=null;
	@JsonProperty("broker_firm_3")
	private String brokerFirm3=null;
	@JsonProperty("current_broker_agent3")
	private String currentBrokerAgent3=null;
	@JsonProperty("broker_email3")
	private String brokerEmail3=null;
	@JsonProperty("broker_phone3")
	private String brokerPhone3=null;
	@JsonProperty("property_sub_type")		 
	private String propertySubType=null;
	@JsonProperty("net_rentable_area")
	private String netRentableArea=null;
	@JsonProperty("apn")
	private String apn=null;
	@JsonProperty("year_built")
	private Integer yearBuilt=null;
	@JsonProperty("loan_status")
	private String loanStatus=null;
	@JsonProperty("special_property_order")
	private String specialPropertyOrder=null;	
	@JsonProperty("sba_loan")
	private String sbaLoan=null;
	@JsonProperty("auction_title")
	private String auctionTitle=null;
	@JsonProperty("auction_name")
	private String auctionName=null;
	
	//Added for Event detail export report
	@JsonProperty("postponed_auction_date")
	private String postponementNewAuctionDate =null;
	@JsonProperty("venue_title")
	private String venueTitle=null;
	@JsonProperty("previous_list_price")
	private String prevListPrice=null;
	@JsonProperty("auctionday_seq")
	private String auctionDaySequence=null;
	@JsonProperty("receiver")
	private String receiver=null;
	@JsonProperty("num_prop_order")
	private Integer numPropOrder=null;
	@JsonProperty("trustee_sale_not_alt_text")
	private String trusteeSaleNoAltText=null;
	@JsonProperty("suppress_opening_bid")
	private boolean suppressOpeningBid;
	@JsonProperty("display_trustee_sale_no")
	private boolean displayTrusteeSaleNumber;
	@JsonProperty("debt_label")
	private String totalEstDebtLabel;
	@JsonProperty("final_judgment_amount")
	private String finalJudgmentAmount;
	//--
	/**
	 * @return the propertyAddress
	 */
	public String getPropertyAddress() {
		return propertyAddress;
	}
	/**
	 * @param propertyAddress the propertyAddress to set
	 */
	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}
	/**
	 * @return the propertyType
	 */
	public String getPropertyType() {
		return propertyType;
	}
	/**
	 * @param propertyType the propertyType to set
	 */
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	/**
	 * @return the estOpeningBid
	 */
	public String getEstOpeningBid() {
		return estOpeningBid;
	}
	/**
	 * @param estOpeningBid the estOpeningBid to set
	 */
	public void setEstOpeningBid(String estOpeningBid) {
		this.estOpeningBid = estOpeningBid;
	}
	/**
	 * @return the squareFootage
	 */
	public String getSquareFootage() {
		return squareFootage;
	}
	/**
	 * @param squareFootage the squareFootage to set
	 */
	public void setSquareFootage(String squareFootage) {
		this.squareFootage = squareFootage;
	}
	/**
	 * @return the foreClosureNo
	 */
	public String getForeClosureNo() {
		return foreClosureNo;
	}
	/**
	 * @param foreClosureNo the foreClosureNo to set
	 */
	public void setForeClosureNo(String foreClosureNo) {
		this.foreClosureNo = foreClosureNo;
	}
	/**
	 * @return the venueLocation
	 */
	public String getVenueLocation() {
		return venueLocation;
	}
	/**
	 * @param venueLocation the venueLocation to set
	 */
	public void setVenueLocation(String venueLocation) {
		this.venueLocation = venueLocation;
	}
	/**
	 * @return the itemNumber
	 */
	public String getItemNumber() {
		return itemNumber;
	}
	/**
	 * @param itemNumber the itemNumber to set
	 */
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	/**
	 * @return the globalPropID
	 */
	public String getGlobalPropID() {
		return globalPropID;
	}
	/**
	 * @param globalPropID the globalPropID to set
	 */
	public void setGlobalPropID(String globalPropID) {
		this.globalPropID = globalPropID;
	}
	/**
	 * @return the propertyState
	 */
	public String getPropertyState() {
		return propertyState;
	}
	/**
	 * @param propertyState the propertyState to set
	 */
	public void setPropertyState(String propertyState) {
		this.propertyState = propertyState;
	}
	/**
	 * @return the propertyCity
	 */
	public String getPropertyCity() {
		return propertyCity;
	}
	/**
	 * @param propertyCity the propertyCity to set
	 */
	public void setPropertyCity(String propertyCity) {
		this.propertyCity = propertyCity;
	}
	/**
	 * @return the propertyCounty
	 */
	public String getPropertyCounty() {
		return propertyCounty;
	}
	/**
	 * @param propertyCounty the propertyCounty to set
	 */
	public void setPropertyCounty(String propertyCounty) {
		this.propertyCounty = propertyCounty;
	}
	/**
	 * @return the depositAmount
	 */
	public String getDepositAmount() {
		return depositAmount;
	}
	/**
	 * @param depositAmount the depositAmount to set
	 */
	public void setDepositAmount(String depositAmount) {
		this.depositAmount = depositAmount;
	}
	/**
	 * @return the propertyZip
	 */
	public String getPropertyZip() {
		return propertyZip;
	}
	/**
	 * @param propertyZip the propertyZip to set
	 */
	public void setPropertyZip(String propertyZip) {
		this.propertyZip = propertyZip;
	}
	/**
	 * @return the thumbnail
	 */
	public String getThumbnail() {
		return thumbnail;
	}
	/**
	 * @param thumbnail the thumbnail to set
	 */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	/**
	 * @return the beds
	 */
	public String getBeds() {
		return beds;
	}
	/**
	 * @param beds the beds to set
	 */
	public void setBeds(String beds) {
		this.beds = beds;
	}
	/**
	 * @return the baths
	 */
	public String getBaths() {
		return baths;
	}
	/**
	 * @param baths the baths to set
	 */
	public void setBaths(String baths) {
		this.baths = baths;
	}
	/**
	 * @return the sqft
	 */
	public String getSqft() {
		return sqft;
	}
	/**
	 * @param sqft the sqft to set
	 */
	public void setSqft(String sqft) {
		this.sqft = sqft;
	}
	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the propertyId
	 */
	public String getPropertyId() {
		return propertyId;
	}
	/**
	 * @param propertyId the propertyId to set
	 */
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	/**
	 * @return the sellerCode
	 */
	public String getSellerCode() {
		return sellerCode;
	}
	/**
	 * @param sellerCode the sellerCode to set
	 */
	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}
	/**
	 * @return the lotSize
	 */
	public String getLotSize() {
		return lotSize;
	}
	/**
	 * @param lotSize the lotSize to set
	 */
	public void setLotSize(String lotSize) {
		this.lotSize = lotSize;
	}
	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * @return the auctionId
	 */
	public String getAuctionId() {
		return auctionId;
	}
	/**
	 * @param auctionId the auctionId to set
	 */
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}
	/**
	 * @return the auctionType
	 */
	public String getAuctionType() {
		return auctionType;
	}
	/**
	 * @param auctionType the auctionType to set
	 */
	public void setAuctionType(String auctionType) {
		this.auctionType = auctionType;
	}
	/**
	 * @return the auctionStartDate
	 */
	public String getAuctionStartDate() {
		return auctionStartDate;
	}
	/**
	 * @param auctionStartDate the auctionStartDate to set
	 */
	public void setAuctionStartDate(String auctionStartDate) {
		this.auctionStartDate = auctionStartDate;
	}
	/**
	 * @return the auctionEndDate
	 */
	public String getAuctionEndDate() {
		return auctionEndDate;
	}
	/**
	 * @param auctionEndDate the auctionEndDate to set
	 */
	public void setAuctionEndDate(String auctionEndDate) {
		this.auctionEndDate = auctionEndDate;
	}
	/**
	 * @return the previouslyValuedTo
	 */
	public String getPreviouslyValuedTo() {
		return previouslyValuedTo;
	}
	/**
	 * @param previouslyValuedTo the previouslyValuedTo to set
	 */
	public void setPreviouslyValuedTo(String previouslyValuedTo) {
		this.previouslyValuedTo = previouslyValuedTo;
	}
	/**
	 * @return the totalEstimatedDebt
	 */
	public String getTotalEstimatedDebt() {
		return totalEstimatedDebt;
	}
	/**
	 * @param totalEstimatedDebt the totalEstimatedDebt to set
	 */
	public void setTotalEstimatedDebt(String totalEstimatedDebt) {
		this.totalEstimatedDebt = totalEstimatedDebt;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the propertyOccupancyStatus
	 */
	public String getPropertyOccupancyStatus() {
		return propertyOccupancyStatus;
	}
	/**
	 * @param propertyOccupancyStatus the propertyOccupancyStatus to set
	 */
	public void setPropertyOccupancyStatus(String propertyOccupancyStatus) {
		this.propertyOccupancyStatus = propertyOccupancyStatus;
	}
	/**
	 * @return the venueId
	 */
	public String getVenueId() {
		return venueId;
	}
	/**
	 * @param venueId the venueId to set
	 */
	public void setVenueId(String venueId) {
		this.venueId = venueId;
	}
	/**
	 * @return the propertyVenueCode
	 */
	public String getPropertyVenueCode() {
		return propertyVenueCode;
	}
	/**
	 * @param propertyVenueCode the propertyVenueCode to set
	 */
	public void setPropertyVenueCode(String propertyVenueCode) {
		this.propertyVenueCode = propertyVenueCode;
	}
	/**
	 * @return the auctionNumber
	 */
	public String getAuctionNumber() {
		return auctionNumber;
	}
	/**
	 * @param auctionNumber the auctionNumber to set
	 */
	public void setAuctionNumber(String auctionNumber) {
		this.auctionNumber = auctionNumber;
	}
	/**
	 * @return the isLuxuryEvent
	 */
	public String getIsLuxuryEvent() {
		return isLuxuryEvent;
	}
	/**
	 * @param isLuxuryEvent the isLuxuryEvent to set
	 */
	public void setIsLuxuryEvent(String isLuxuryEvent) {
		this.isLuxuryEvent = isLuxuryEvent;
	}
	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}
	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	/**
	 * @return the assetLevelDeposit
	 */
	public String getAssetLevelDeposit() {
		return assetLevelDeposit;
	}
	/**
	 * @param assetLevelDeposit the assetLevelDeposit to set
	 */
	public void setAssetLevelDeposit(String assetLevelDeposit) {
		this.assetLevelDeposit = assetLevelDeposit;
	}
	/**
	 * @return the bidPending
	 */
	public String getBidPending() {
		return bidPending;
	}
	/**
	 * @param bidPending the bidPending to set
	 */
	public void setBidPending(String bidPending) {
		this.bidPending = bidPending;
	}
	/**
	 * @return the geoRegion
	 */
	public String getGeoRegion() {
		return geoRegion;
	}
	/**
	 * @param geoRegion the geoRegion to set
	 */
	public void setGeoRegion(String geoRegion) {
		this.geoRegion = geoRegion;
	}
	/**
	 * @return the propertyDescription
	 */
	public String getPropertyDescription() {
		return propertyDescription;
	}
	/**
	 * @param propertyDescription the propertyDescription to set
	 */
	public void setPropertyDescription(String propertyDescription) {
		this.propertyDescription = propertyDescription;
	}
	/**
	 * @return the isPlatinum
	 */
	public String getIsPlatinum() {
		return isPlatinum;
	}
	/**
	 * @param isPlatinum the isPlatinum to set
	 */
	public void setIsPlatinum(String isPlatinum) {
		this.isPlatinum = isPlatinum;
	}
	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}
	/**
	 * @param propertyName the propertyName to set
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the nRASFUnit
	 */
	public String getNRASFUnit() {
		return NRASFUnit;
	}
	/**
	 * @param nRASFUnit the nRASFUnit to set
	 */
	public void setNRASFUnit(String nRASFUnit) {
		NRASFUnit = nRASFUnit;
	}
	/**
	 * @return the numberOfBuildings
	 */
	public String getNumberOfBuildings() {
		return numberOfBuildings;
	}
	/**
	 * @param numberOfBuildings the numberOfBuildings to set
	 */
	public void setNumberOfBuildings(String numberOfBuildings) {
		this.numberOfBuildings = numberOfBuildings;
	}
	/**
	 * @return the totalRoomCount
	 */
	public String getTotalRoomCount() {
		return totalRoomCount;
	}
	/**
	 * @param totalRoomCount the totalRoomCount to set
	 */
	public void setTotalRoomCount(String totalRoomCount) {
		this.totalRoomCount = totalRoomCount;
	}
	/**
	 * @return the numberOfstories
	 */
	public String getNumberOfstories() {
		return numberOfstories;
	}
	/**
	 * @param numberOfstories the numberOfstories to set
	 */
	public void setNumberOfstories(String numberOfstories) {
		this.numberOfstories = numberOfstories;
	}
	/**
	 * @return the numberOfUnits
	 */
	public String getNumberOfUnits() {
		return numberOfUnits;
	}
	/**
	 * @param numberOfUnits the numberOfUnits to set
	 */
	public void setNumberOfUnits(String numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}
	/**
	 * @return the occupancyAsOfDate
	 */
	public String getOccupancyAsOfDate() {
		return occupancyAsOfDate;
	}
	/**
	 * @param occupancyAsOfDate the occupancyAsOfDate to set
	 */
	public void setOccupancyAsOfDate(String occupancyAsOfDate) {
		this.occupancyAsOfDate = occupancyAsOfDate;
	}
	/**
	 * @return the noi
	 */
	public String getNoi() {
		return noi;
	}
	/**
	 * @param noi the noi to set
	 */
	public void setNoi(String noi) {
		this.noi = noi;
	}
	/**
	 * @return the noiDate
	 */
	public String getNoiDate() {
		return noiDate;
	}
	/**
	 * @param noiDate the noiDate to set
	 */
	public void setNoiDate(String noiDate) {
		this.noiDate = noiDate;
	}
	/**
	 * @return the noiPeriod
	 */
	public String getNoiPeriod() {
		return noiPeriod;
	}
	/**
	 * @param noiPeriod the noiPeriod to set
	 */
	public void setNoiPeriod(String noiPeriod) {
		this.noiPeriod = noiPeriod;
	}
	/**
	 * @return the lienPosition
	 */
	public String getLienPosition() {
		return lienPosition;
	}
	/**
	 * @param lienPosition the lienPosition to set
	 */
	public void setLienPosition(String lienPosition) {
		this.lienPosition = lienPosition;
	}
	/**
	 * @return the propertyOriginalPrincipal
	 */
	public String getPropertyOriginalPrincipal() {
		return propertyOriginalPrincipal;
	}
	/**
	 * @param propertyOriginalPrincipal the propertyOriginalPrincipal to set
	 */
	public void setPropertyOriginalPrincipal(String propertyOriginalPrincipal) {
		this.propertyOriginalPrincipal = propertyOriginalPrincipal;
	}
	/**
	 * @return the propertyOriginalPrincipalPerUnit
	 */
	public String getPropertyOriginalPrincipalPerUnit() {
		return propertyOriginalPrincipalPerUnit;
	}
	/**
	 * @param propertyOriginalPrincipalPerUnit the propertyOriginalPrincipalPerUnit to set
	 */
	public void setPropertyOriginalPrincipalPerUnit(
			String propertyOriginalPrincipalPerUnit) {
		this.propertyOriginalPrincipalPerUnit = propertyOriginalPrincipalPerUnit;
	}
	/**
	 * @return the propertyCurrentPrincipal
	 */
	public String getPropertyCurrentPrincipal() {
		return propertyCurrentPrincipal;
	}
	/**
	 * @param propertyCurrentPrincipal the propertyCurrentPrincipal to set
	 */
	public void setPropertyCurrentPrincipal(String propertyCurrentPrincipal) {
		this.propertyCurrentPrincipal = propertyCurrentPrincipal;
	}
	/**
	 * @return the propertyCurrentPrincipalPerUnit
	 */
	public String getPropertyCurrentPrincipalPerUnit() {
		return propertyCurrentPrincipalPerUnit;
	}
	/**
	 * @param propertyCurrentPrincipalPerUnit the propertyCurrentPrincipalPerUnit to set
	 */
	public void setPropertyCurrentPrincipalPerUnit(
			String propertyCurrentPrincipalPerUnit) {
		this.propertyCurrentPrincipalPerUnit = propertyCurrentPrincipalPerUnit;
	}
	/**
	 * @return the propertyLoanTerm
	 */
	public String getPropertyLoanTerm() {
		return propertyLoanTerm;
	}
	/**
	 * @param propertyLoanTerm the propertyLoanTerm to set
	 */
	public void setPropertyLoanTerm(String propertyLoanTerm) {
		this.propertyLoanTerm = propertyLoanTerm;
	}
	/**
	 * @return the propertyLoanTermMos
	 */
	public String getPropertyLoanTermMos() {
		return propertyLoanTermMos;
	}
	/**
	 * @param propertyLoanTermMos the propertyLoanTermMos to set
	 */
	public void setPropertyLoanTermMos(String propertyLoanTermMos) {
		this.propertyLoanTermMos = propertyLoanTermMos;
	}
	/**
	 * @return the noteDate
	 */
	public String getNoteDate() {
		return noteDate;
	}
	/**
	 * @param noteDate the noteDate to set
	 */
	public void setNoteDate(String noteDate) {
		this.noteDate = noteDate;
	}
	/**
	 * @return the monthSeasoned
	 */
	public String getMonthSeasoned() {
		return monthSeasoned;
	}
	/**
	 * @param monthSeasoned the monthSeasoned to set
	 */
	public void setMonthSeasoned(String monthSeasoned) {
		this.monthSeasoned = monthSeasoned;
	}
	/**
	 * @return the remainingTerm
	 */
	public String getRemainingTerm() {
		return remainingTerm;
	}
	/**
	 * @param remainingTerm the remainingTerm to set
	 */
	public void setRemainingTerm(String remainingTerm) {
		this.remainingTerm = remainingTerm;
	}
	/**
	 * @return the remainingTermMonth
	 */
	public String getRemainingTermMonth() {
		return remainingTermMonth;
	}
	/**
	 * @param remainingTermMonth the remainingTermMonth to set
	 */
	public void setRemainingTermMonth(String remainingTermMonth) {
		this.remainingTermMonth = remainingTermMonth;
	}
	/**
	 * @return the propertyIoTerm
	 */
	public String getPropertyIoTerm() {
		return propertyIoTerm;
	}
	/**
	 * @param propertyIoTerm the propertyIoTerm to set
	 */
	public void setPropertyIoTerm(String propertyIoTerm) {
		this.propertyIoTerm = propertyIoTerm;
	}
	/**
	 * @return the propertyIoTermMonth
	 */
	public String getPropertyIoTermMonth() {
		return propertyIoTermMonth;
	}
	/**
	 * @param propertyIoTermMonth the propertyIoTermMonth to set
	 */
	public void setPropertyIoTermMonth(String propertyIoTermMonth) {
		this.propertyIoTermMonth = propertyIoTermMonth;
	}
	/**
	 * @return the propertyRepaymentType
	 */
	public String getPropertyRepaymentType() {
		return propertyRepaymentType;
	}
	/**
	 * @param propertyRepaymentType the propertyRepaymentType to set
	 */
	public void setPropertyRepaymentType(String propertyRepaymentType) {
		this.propertyRepaymentType = propertyRepaymentType;
	}
	/**
	 * @return the amortizationPeriod
	 */
	public String getAmortizationPeriod() {
		return amortizationPeriod;
	}
	/**
	 * @param amortizationPeriod the amortizationPeriod to set
	 */
	public void setAmortizationPeriod(String amortizationPeriod) {
		this.amortizationPeriod = amortizationPeriod;
	}
	/**
	 * @return the amortizationPeriodMonths
	 */
	public String getAmortizationPeriodMonths() {
		return amortizationPeriodMonths;
	}
	/**
	 * @param amortizationPeriodMonths the amortizationPeriodMonths to set
	 */
	public void setAmortizationPeriodMonths(String amortizationPeriodMonths) {
		this.amortizationPeriodMonths = amortizationPeriodMonths;
	}
	/**
	 * @return the propertyBalloon
	 */
	public String getPropertyBalloon() {
		return propertyBalloon;
	}
	/**
	 * @param propertyBalloon the propertyBalloon to set
	 */
	public void setPropertyBalloon(String propertyBalloon) {
		this.propertyBalloon = propertyBalloon;
	}
	/**
	 * @return the lastPaymentDate
	 */
	public String getLastPaymentDate() {
		return lastPaymentDate;
	}
	/**
	 * @param lastPaymentDate the lastPaymentDate to set
	 */
	public void setLastPaymentDate(String lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}
	/**
	 * @return the maturityDate
	 */
	public String getMaturityDate() {
		return maturityDate;
	}
	/**
	 * @param maturityDate the maturityDate to set
	 */
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}
	/**
	 * @return the monthlyPaymentAmount
	 */
	public String getMonthlyPaymentAmount() {
		return monthlyPaymentAmount;
	}
	/**
	 * @param monthlyPaymentAmount the monthlyPaymentAmount to set
	 */
	public void setMonthlyPaymentAmount(String monthlyPaymentAmount) {
		this.monthlyPaymentAmount = monthlyPaymentAmount;
	}
	/**
	 * @return the annualPaymentAmount
	 */
	public String getAnnualPaymentAmount() {
		return annualPaymentAmount;
	}
	/**
	 * @param annualPaymentAmount the annualPaymentAmount to set
	 */
	public void setAnnualPaymentAmount(String annualPaymentAmount) {
		this.annualPaymentAmount = annualPaymentAmount;
	}
	/**
	 * @return the rateType
	 */
	public String getRateType() {
		return rateType;
	}
	/**
	 * @param rateType the rateType to set
	 */
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}
	/**
	 * @return the propertyIndexRate
	 */
	public String getPropertyIndexRate() {
		return propertyIndexRate;
	}
	/**
	 * @param propertyIndexRate the propertyIndexRate to set
	 */
	public void setPropertyIndexRate(String propertyIndexRate) {
		this.propertyIndexRate = propertyIndexRate;
	}
	/**
	 * @return the avgDailyRate
	 */
	public String getAvgDailyRate() {
		return avgDailyRate;
	}
	/**
	 * @param avgDailyRate the avgDailyRate to set
	 */
	public void setAvgDailyRate(String avgDailyRate) {
		this.avgDailyRate = avgDailyRate;
	}
	/**
	 * @return the propertyOriginalInterestRate
	 */
	public String getPropertyOriginalInterestRate() {
		return propertyOriginalInterestRate;
	}
	/**
	 * @param propertyOriginalInterestRate the propertyOriginalInterestRate to set
	 */
	public void setPropertyOriginalInterestRate(String propertyOriginalInterestRate) {
		this.propertyOriginalInterestRate = propertyOriginalInterestRate;
	}
	/**
	 * @return the ceilingRate
	 */
	public String getCeilingRate() {
		return ceilingRate;
	}
	/**
	 * @param ceilingRate the ceilingRate to set
	 */
	public void setCeilingRate(String ceilingRate) {
		this.ceilingRate = ceilingRate;
	}
	/**
	 * @return the propertyInterestFloor
	 */
	public String getPropertyInterestFloor() {
		return propertyInterestFloor;
	}
	/**
	 * @param propertyInterestFloor the propertyInterestFloor to set
	 */
	public void setPropertyInterestFloor(String propertyInterestFloor) {
		this.propertyInterestFloor = propertyInterestFloor;
	}
	/**
	 * @return the propertyMargin
	 */
	public String getPropertyMargin() {
		return propertyMargin;
	}
	/**
	 * @param propertyMargin the propertyMargin to set
	 */
	public void setPropertyMargin(String propertyMargin) {
		this.propertyMargin = propertyMargin;
	}
	/**
	 * @return the propertyInitialFixedPeriod
	 */
	public String getPropertyInitialFixedPeriod() {
		return propertyInitialFixedPeriod;
	}
	/**
	 * @param propertyInitialFixedPeriod the propertyInitialFixedPeriod to set
	 */
	public void setPropertyInitialFixedPeriod(String propertyInitialFixedPeriod) {
		this.propertyInitialFixedPeriod = propertyInitialFixedPeriod;
	}
	/**
	 * @return the propertyCaps
	 */
	public String getPropertyCaps() {
		return propertyCaps;
	}
	/**
	 * @param propertyCaps the propertyCaps to set
	 */
	public void setPropertyCaps(String propertyCaps) {
		this.propertyCaps = propertyCaps;
	}
	/**
	 * @return the propertyFrequencyOfAdjustments
	 */
	public String getPropertyFrequencyOfAdjustments() {
		return propertyFrequencyOfAdjustments;
	}
	/**
	 * @param propertyFrequencyOfAdjustments the propertyFrequencyOfAdjustments to set
	 */
	public void setPropertyFrequencyOfAdjustments(
			String propertyFrequencyOfAdjustments) {
		this.propertyFrequencyOfAdjustments = propertyFrequencyOfAdjustments;
	}
	/**
	 * @return the propertyFirstRateAdjustment
	 */
	public String getPropertyFirstRateAdjustment() {
		return propertyFirstRateAdjustment;
	}
	/**
	 * @param propertyFirstRateAdjustment the propertyFirstRateAdjustment to set
	 */
	public void setPropertyFirstRateAdjustment(String propertyFirstRateAdjustment) {
		this.propertyFirstRateAdjustment = propertyFirstRateAdjustment;
	}
	/**
	 * @return the propertyNextAdjustmentDate
	 */
	public String getPropertyNextAdjustmentDate() {
		return propertyNextAdjustmentDate;
	}
	/**
	 * @param propertyNextAdjustmentDate the propertyNextAdjustmentDate to set
	 */
	public void setPropertyNextAdjustmentDate(String propertyNextAdjustmentDate) {
		this.propertyNextAdjustmentDate = propertyNextAdjustmentDate;
	}
	/**
	 * @return the accrualMethod
	 */
	public String getAccrualMethod() {
		return accrualMethod;
	}
	/**
	 * @param accrualMethod the accrualMethod to set
	 */
	public void setAccrualMethod(String accrualMethod) {
		this.accrualMethod = accrualMethod;
	}
	/**
	 * @return the currentLTV
	 */
	public String getCurrentLTV() {
		return currentLTV;
	}
	/**
	 * @param currentLTV the currentLTV to set
	 */
	public void setCurrentLTV(String currentLTV) {
		this.currentLTV = currentLTV;
	}
	/**
	 * @return the currentDSCR
	 */
	public String getCurrentDSCR() {
		return currentDSCR;
	}
	/**
	 * @param currentDSCR the currentDSCR to set
	 */
	public void setCurrentDSCR(String currentDSCR) {
		this.currentDSCR = currentDSCR;
	}
	/**
	 * @return the recourse
	 */
	public String getRecourse() {
		return recourse;
	}
	/**
	 * @param recourse the recourse to set
	 */
	public void setRecourse(String recourse) {
		this.recourse = recourse;
	}
	/**
	 * @return the impounds
	 */
	public String getImpounds() {
		return impounds;
	}
	/**
	 * @param impounds the impounds to set
	 */
	public void setImpounds(String impounds) {
		this.impounds = impounds;
	}
	/**
	 * @return the prepaymentProvision
	 */
	public String getPrepaymentProvision() {
		return prepaymentProvision;
	}
	/**
	 * @param prepaymentProvision the prepaymentProvision to set
	 */
	public void setPrepaymentProvision(String prepaymentProvision) {
		this.prepaymentProvision = prepaymentProvision;
	}
	/**
	 * @return the participation
	 */
	public String getParticipation() {
		return participation;
	}
	/**
	 * @param participation the participation to set
	 */
	public void setParticipation(String participation) {
		this.participation = participation;
	}
	/**
	 * @return the participationLead
	 */
	public String getParticipationLead() {
		return participationLead;
	}
	/**
	 * @param participationLead the participationLead to set
	 */
	public void setParticipationLead(String participationLead) {
		this.participationLead = participationLead;
	}
	/**
	 * @return the appraisalDate
	 */
	public String getAppraisalDate() {
		return appraisalDate;
	}
	/**
	 * @param appraisalDate the appraisalDate to set
	 */
	public void setAppraisalDate(String appraisalDate) {
		this.appraisalDate = appraisalDate;
	}
	/**
	 * @return the mostRecentAppraisedValue
	 */
	public String getMostRecentAppraisedValue() {
		return mostRecentAppraisedValue;
	}
	/**
	 * @param mostRecentAppraisedValue the mostRecentAppraisedValue to set
	 */
	public void setMostRecentAppraisedValue(String mostRecentAppraisedValue) {
		this.mostRecentAppraisedValue = mostRecentAppraisedValue;
	}
	/**
	 * @return the previouslyValuedDate
	 */
	public String getPreviouslyValuedDate() {
		return previouslyValuedDate;
	}
	/**
	 * @param previouslyValuedDate the previouslyValuedDate to set
	 */
	public void setPreviouslyValuedDate(String previouslyValuedDate) {
		this.previouslyValuedDate = previouslyValuedDate;
	}
	/**
	 * @return the originalAppraisedValue
	 */
	public String getOriginalAppraisedValue() {
		return originalAppraisedValue;
	}
	/**
	 * @param originalAppraisedValue the originalAppraisedValue to set
	 */
	public void setOriginalAppraisedValue(String originalAppraisedValue) {
		this.originalAppraisedValue = originalAppraisedValue;
	}
	/**
	 * @return the brokerFirm
	 */
	public String getBrokerFirm() {
		return brokerFirm;
	}
	/**
	 * @param brokerFirm the brokerFirm to set
	 */
	public void setBrokerFirm(String brokerFirm) {
		this.brokerFirm = brokerFirm;
	}
	/**
	 * @return the currentBrokerAgent
	 */
	public String getCurrentBrokerAgent() {
		return currentBrokerAgent;
	}
	/**
	 * @param currentBrokerAgent the currentBrokerAgent to set
	 */
	public void setCurrentBrokerAgent(String currentBrokerAgent) {
		this.currentBrokerAgent = currentBrokerAgent;
	}
	/**
	 * @return the brokerEmail
	 */
	public String getBrokerEmail() {
		return brokerEmail;
	}
	/**
	 * @param brokerEmail the brokerEmail to set
	 */
	public void setBrokerEmail(String brokerEmail) {
		this.brokerEmail = brokerEmail;
	}
	/**
	 * @return the brokerPhone
	 */
	public String getBrokerPhone() {
		return brokerPhone;
	}
	/**
	 * @param brokerPhone the brokerPhone to set
	 */
	public void setBrokerPhone(String brokerPhone) {
		this.brokerPhone = brokerPhone;
	}
	/**
	 * @return the brokerFirm2
	 */
	public String getBrokerFirm2() {
		return brokerFirm2;
	}
	/**
	 * @param brokerFirm2 the brokerFirm2 to set
	 */
	public void setBrokerFirm2(String brokerFirm2) {
		this.brokerFirm2 = brokerFirm2;
	}
	/**
	 * @return the currentBrokerAgent2
	 */
	public String getCurrentBrokerAgent2() {
		return currentBrokerAgent2;
	}
	/**
	 * @param currentBrokerAgent2 the currentBrokerAgent2 to set
	 */
	public void setCurrentBrokerAgent2(String currentBrokerAgent2) {
		this.currentBrokerAgent2 = currentBrokerAgent2;
	}
	/**
	 * @return the brokerEmail2
	 */
	public String getBrokerEmail2() {
		return brokerEmail2;
	}
	/**
	 * @param brokerEmail2 the brokerEmail2 to set
	 */
	public void setBrokerEmail2(String brokerEmail2) {
		this.brokerEmail2 = brokerEmail2;
	}
	/**
	 * @return the brokerPhone2
	 */
	public String getBrokerPhone2() {
		return brokerPhone2;
	}
	/**
	 * @param brokerPhone2 the brokerPhone2 to set
	 */
	public void setBrokerPhone2(String brokerPhone2) {
		this.brokerPhone2 = brokerPhone2;
	}
	/**
	 * @return the brokerFirm3
	 */
	public String getBrokerFirm3() {
		return brokerFirm3;
	}
	/**
	 * @param brokerFirm3 the brokerFirm3 to set
	 */
	public void setBrokerFirm3(String brokerFirm3) {
		this.brokerFirm3 = brokerFirm3;
	}
	/**
	 * @return the currentBrokerAgent3
	 */
	public String getCurrentBrokerAgent3() {
		return currentBrokerAgent3;
	}
	/**
	 * @param currentBrokerAgent3 the currentBrokerAgent3 to set
	 */
	public void setCurrentBrokerAgent3(String currentBrokerAgent3) {
		this.currentBrokerAgent3 = currentBrokerAgent3;
	}
	/**
	 * @return the brokerEmail3
	 */
	public String getBrokerEmail3() {
		return brokerEmail3;
	}
	/**
	 * @param brokerEmail3 the brokerEmail3 to set
	 */
	public void setBrokerEmail3(String brokerEmail3) {
		this.brokerEmail3 = brokerEmail3;
	}
	/**
	 * @return the brokerPhone3
	 */
	public String getBrokerPhone3() {
		return brokerPhone3;
	}
	/**
	 * @param brokerPhone3 the brokerPhone3 to set
	 */
	public void setBrokerPhone3(String brokerPhone3) {
		this.brokerPhone3 = brokerPhone3;
	}
	/**
	 * @return the propertySubType
	 */
	public String getPropertySubType() {
		return propertySubType;
	}
	/**
	 * @param propertySubType the propertySubType to set
	 */
	public void setPropertySubType(String propertySubType) {
		this.propertySubType = propertySubType;
	}
	/**
	 * @return the netRentableArea
	 */
	public String getNetRentableArea() {
		return netRentableArea;
	}
	/**
	 * @param netRentableArea the netRentableArea to set
	 */
	public void setNetRentableArea(String netRentableArea) {
		this.netRentableArea = netRentableArea;
	}
	/**
	 * @return the apn
	 */
	public String getApn() {
		return apn;
	}
	/**
	 * @param apn the apn to set
	 */
	public void setApn(String apn) {
		this.apn = apn;
	}
	/**
	 * @return the yearBuilt
	 */
	public Integer getYearBuilt() {
		return yearBuilt;
	}
	/**
	 * @param yearBuilt the yearBuilt to set
	 */
	public void setYearBuilt(Integer yearBuilt) {
		this.yearBuilt = yearBuilt;
	}
	/**
	 * @return the loanStatus
	 */
	public String getLoanStatus() {
		return loanStatus;
	}
	/**
	 * @param loanStatus the loanStatus to set
	 */
	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
	/**
	 * @return the specialPropertyOrder
	 */
	public String getSpecialPropertyOrder() {
		return specialPropertyOrder;
	}
	/**
	 * @param specialPropertyOrder the specialPropertyOrder to set
	 */
	public void setSpecialPropertyOrder(String specialPropertyOrder) {
		this.specialPropertyOrder = specialPropertyOrder;
	}
	/**
	 * @return the sbaLoan
	 */
	public String getSbaLoan() {
		return sbaLoan;
	}
	/**
	 * @param sbaLoan the sbaLoan to set
	 */
	public void setSbaLoan(String sbaLoan) {
		this.sbaLoan = sbaLoan;
	}
	/**
	 * @return the auctionTitle
	 */
	public String getAuctionTitle() {
		return auctionTitle;
	}
	/**
	 * @param auctionTitle the auctionTitle to set
	 */
	public void setAuctionTitle(String auctionTitle) {
		this.auctionTitle = auctionTitle;
	}
	/**
	 * @return the auctionName
	 */
	public String getAuctionName() {
		return auctionName;
	}
	/**
	 * @param auctionName the auctionName to set
	 */
	public void setAuctionName(String auctionName) {
		this.auctionName = auctionName;
	}
	public String getPostponementNewAuctionDate() {
		return postponementNewAuctionDate;
	}
	public void setPostponementNewAuctionDate(String postponementNewAuctionDate) {
		this.postponementNewAuctionDate = postponementNewAuctionDate;
	}
	public String getVenueTitle() {
		return venueTitle;
	}
	public void setVenueTitle(String venueTitle) {
		this.venueTitle = venueTitle;
	}
	public String getPrevListPrice() {
		return prevListPrice;
	}
	public void setPrevListPrice(String prevListPrice) {
		this.prevListPrice = prevListPrice;
	}
	public String getAuctionDaySequence() {
		return auctionDaySequence;
	}
	public void setAuctionDaySequence(String auctionDaySequence) {
		this.auctionDaySequence = auctionDaySequence;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public Integer getNumPropOrder() {
		return numPropOrder;
	}
	public void setNumPropOrder(Integer numPropOrder) {
		this.numPropOrder = numPropOrder;
	}
	public String getTrusteeSaleNoAltText() {
		return trusteeSaleNoAltText;
	}
	public void setTrusteeSaleNoAltText(String trusteeSaleNoAltText) {
		this.trusteeSaleNoAltText = trusteeSaleNoAltText;
	}
	public boolean isSuppressOpeningBid() {
		return suppressOpeningBid;
	}
	public void setSuppressOpeningBid(boolean suppressOpeningBid) {
		this.suppressOpeningBid = suppressOpeningBid;
	}
	public boolean isDisplayTrusteeSaleNumber() {
		return displayTrusteeSaleNumber;
	}

	public void setDisplayTrusteeSaleNumber(boolean displayTrusteeSaleNumber) {
		this.displayTrusteeSaleNumber = displayTrusteeSaleNumber;
	}
	public String getTotalEstDebtLabel() {
		return totalEstDebtLabel;
	}
	public void setTotalEstDebtLabel(String totalEstDebtLabel) {
		this.totalEstDebtLabel = totalEstDebtLabel;
	}	
	public String getFinalJudgmentAmount() {
		return finalJudgmentAmount;
	}
	public void setFinalJudgmentAmount(String finalJudgmentAmount) {
		this.finalJudgmentAmount = finalJudgmentAmount;
	}	
	
}
