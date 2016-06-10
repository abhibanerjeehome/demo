package com.redwood.rp.solr.service.external.impl;

import static com.redwood.rp.solr.constant.SolrErrorConstant.GLOBAL_PROP_ID_AND_AUCTION_NUMBER_NOT_FOUND;
import static com.redwood.rp.solr.constant.SolrErrorConstant.GLOBAL_PROP_ID_NOT_FOUND;
import static com.redwood.rp.solr.constant.SolrErrorConstant.SOLR_CALL_FAILED;
import static com.redwood.rp.solr.constant.SolrErrorConstant.SOLR_FEATURED_PROPERTY_CALL_FAILED;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redwood.rp.caching.service.CachingService;
import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.common.exception.PropertyInfoNotFoundException;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.util.BooleanUtil;
import com.redwood.rp.core.util.PropertyUtil;
import com.redwood.rp.core.util.StringUtil;
import com.redwood.rp.solr.bo.FeaturedPropertiesBO;
import com.redwood.rp.solr.bo.PropertyInfoBO;
import com.redwood.rp.solr.service.external.ExternalSolrSearch;



@Named
public class ExternalSolrSearchImpl implements ExternalSolrSearch {

	private static Logger logger = LoggerFactory.getLogger(ExternalSolrSearchImpl.class.getName());

	@Inject
	private CachingService cachingService;

	@Override
	public PropertyInfoBO getGlobalPropertyDetails(String globalPropId) throws PropertyInfoNotFoundException{
		// TODO Auto-generated method stub

		PropertyInfoBO propertyAssetInfoBO=null;
		String solrSearchKeyForGlobalPropID=this.getClass().getName()+"_"+globalPropId;

		try {

			propertyAssetInfoBO=(PropertyInfoBO)cachingService.getMemcacheObject(solrSearchKeyForGlobalPropID);
			if(propertyAssetInfoBO!=null)
				return propertyAssetInfoBO;
		}catch(Exception ex){
			logger.error("Error while retrieving global Asset details from cache", ex);
		}

		String cacheExpiration=PropertyUtil.get("acpcommon.solr.globalpropertyid.cache.timeout");
		String urlString =PropertyUtil.get("acpcommon.solr.url");

		try{
			SolrServer solr = new HttpSolrServer(urlString);
			SolrQuery parameters = new SolrQuery();
			parameters.set("wt", "json");
			String query="globalPropID:"+globalPropId;
			parameters.setParam("collection", "auctionCom");
			parameters.addFilterQuery("(collectionName:\"auctionCom\" AND -soldDate:[* TO *])");
			parameters.setQuery(query);	
			//parameters.setSortField("collectionName", ORDER.asc);

			QueryResponse solrResponse = solr.query(parameters);

			if (solrResponse != null) {
				SolrDocumentList documentsFromSOLR = solrResponse.getResults();
				if (documentsFromSOLR != null) {
					Iterator<SolrDocument> globalPropertyDetailsItr= documentsFromSOLR.iterator();

					if (globalPropertyDetailsItr.hasNext()) {
						SolrDocument document = globalPropertyDetailsItr.next();
						propertyAssetInfoBO = createPropertyInfoBO(document);
					}

				}

			}
			if(propertyAssetInfoBO==null){
				
				try{
					propertyAssetInfoBO=getGlobalPropertyDetailsForSoldAssets(globalPropId);

				}catch(Exception ex){
				
				String exceptionMsg=String.format(GLOBAL_PROP_ID_NOT_FOUND, globalPropId,urlString);
				logger.info(exceptionMsg );
				throw new PropertyInfoNotFoundException(exceptionMsg);
				}
			}
		}catch(Exception ex){
			String exceptionMsg=String.format(SOLR_CALL_FAILED,globalPropId,urlString  );
			logger.error(exceptionMsg, ex);

			throw new PropertyInfoNotFoundException(exceptionMsg);
		}


		try{
			cachingService.setMemcacheObject(solrSearchKeyForGlobalPropID, Integer.parseInt(cacheExpiration), propertyAssetInfoBO);
		}catch(Exception ex){
			logger.error("Error while setting global Asset details from cache", ex);
	}
		return propertyAssetInfoBO;
	}
	
	
	@Override
	public PropertyInfoBO getGlobalPropertyDetailsForSoldAssets(String globalPropId) throws PropertyInfoNotFoundException{
		// TODO Auto-generated method stub

		PropertyInfoBO propertyAssetInfoBO=null;
		String solrSearchKeyForGlobalPropID=this.getClass().getName()+"_"+globalPropId;

		try {

			propertyAssetInfoBO=(PropertyInfoBO)cachingService.getMemcacheObject(solrSearchKeyForGlobalPropID);
			if(propertyAssetInfoBO!=null)
				return propertyAssetInfoBO;
		}catch(Exception ex){
			logger.error("Error while retrieving global Asset details from cache", ex);
		}

		String cacheExpiration=PropertyUtil.get("acpcommon.solr.globalpropertyid.cache.timeout");
		String urlString = PropertyUtil.get("acpcommon.solr.url");

		try{
			SolrServer solr = new HttpSolrServer(urlString);
			SolrQuery parameters = new SolrQuery();
			parameters.set("wt", "json");
			String query="globalPropID:"+globalPropId;
			parameters.setParam("collection", "auctionComSold");
			parameters.addFilterQuery("(collectionName:\"auctionComSold\" AND soldDate:[NOW-14DAY TO *])");
			parameters.setQuery(query);	
			//parameters.setSortField("collectionName", ORDER.asc);

			QueryResponse solrResponse = solr.query(parameters);

			if (solrResponse != null) {
				SolrDocumentList documentsFromSOLR = solrResponse.getResults();
				if (documentsFromSOLR != null) {
					Iterator<SolrDocument> globalPropertyDetailsItr= documentsFromSOLR.iterator();

					if (globalPropertyDetailsItr.hasNext()) {
						SolrDocument document = globalPropertyDetailsItr.next();
						propertyAssetInfoBO = createPropertyInfoBO(document);
					}

				}

			}
			if(propertyAssetInfoBO==null){
				String exceptionMsg=String.format(GLOBAL_PROP_ID_NOT_FOUND, globalPropId,urlString);
				logger.info(exceptionMsg );
				throw new PropertyInfoNotFoundException(exceptionMsg); 
			}
		}catch(Exception ex){
			String exceptionMsg=String.format(SOLR_CALL_FAILED,globalPropId,urlString  );
			logger.error(exceptionMsg, ex);

			throw new PropertyInfoNotFoundException(exceptionMsg);
		}


		try{
			cachingService.setMemcacheObject(solrSearchKeyForGlobalPropID, Integer.parseInt(cacheExpiration), propertyAssetInfoBO);
		}catch(Exception ex){
			logger.error("Error while setting global Asset details from cache", ex);
	}
		return propertyAssetInfoBO;
	}
	
	
	@Override
	public PropertyInfoBO getGlobalPropertyDetails(String globalPropId,String auctionNumber) throws PropertyInfoNotFoundException{
		// TODO Auto-generated method stub

		PropertyInfoBO propertyAssetInfoBO=null;
		String solrSearchKeyForGlobalPropIDAndAuctionNumber=this.getClass().getName()+"_"+globalPropId+"_"+auctionNumber;

		try {

			propertyAssetInfoBO=(PropertyInfoBO)cachingService.getMemcacheObject(solrSearchKeyForGlobalPropIDAndAuctionNumber);
			if(propertyAssetInfoBO!=null)
				return propertyAssetInfoBO;
		}catch(Exception ex){
			logger.error("Error while retrieving global Asset details from cache"+ex);
		}

		String cacheExpiration=PropertyUtil.get("acpcommon.solr.globalpropertyid.cache.timeout");
		String urlString = PropertyUtil.get("acpcommon.solr.url");
		
		if(StringUtils.isBlank(auctionNumber)){
			try{
				propertyAssetInfoBO=getGlobalPropertyDetails(globalPropId);
			}catch(Exception ex){
				String exceptionMsg=String.format(GLOBAL_PROP_ID_AND_AUCTION_NUMBER_NOT_FOUND, globalPropId,auctionNumber,urlString);
				logger.info(exceptionMsg );
				throw new PropertyInfoNotFoundException(exceptionMsg);	
			}
					
			
		}else{

		try{
			SolrServer solr = new HttpSolrServer(urlString);
			SolrQuery parameters = new SolrQuery();
			parameters.set("wt", "json");
			String query="globalPropID:"+globalPropId;
			if(StringUtil.isNotBlank(auctionNumber))
				query=query+" AND auctionNumber:"+"\""+auctionNumber+"\"";
			parameters.setParam("collection", "auctionCom");
			parameters.addFilterQuery("(collectionName:\"auctionCom\" AND -soldDate:[* TO *])");
			//parameters.setSortField("collectionName", ORDER.asc);
			parameters.setQuery(query);	

			QueryResponse solrResponse = solr.query(parameters);

			if (solrResponse != null) {
				SolrDocumentList documentsFromSOLR = solrResponse.getResults();
				if (documentsFromSOLR != null) {
					Iterator<SolrDocument> globalPropertyDetailsItr= documentsFromSOLR.iterator();

					if (globalPropertyDetailsItr.hasNext()) {
						SolrDocument document = globalPropertyDetailsItr.next();
						propertyAssetInfoBO = createPropertyInfoBO(document);
					}

				}

			}
			if(propertyAssetInfoBO==null){
				try{
					propertyAssetInfoBO=getGlobalPropertyDetailsForSoldAssets(globalPropId,auctionNumber);
				}catch(Exception ex){
					String exceptionMsg=String.format(GLOBAL_PROP_ID_AND_AUCTION_NUMBER_NOT_FOUND, globalPropId,auctionNumber,urlString);
					logger.info(exceptionMsg );
					throw new PropertyInfoNotFoundException(exceptionMsg);	
				}
				
				try{
					propertyAssetInfoBO=getGlobalPropertyDetails(globalPropId);
				}catch(Exception ex){
					String exceptionMsg=String.format(GLOBAL_PROP_ID_AND_AUCTION_NUMBER_NOT_FOUND, globalPropId,auctionNumber,urlString);
					logger.info(exceptionMsg );
					throw new PropertyInfoNotFoundException(exceptionMsg);	
				}
				
				 
			}
		}catch(Exception ex){
			String exceptionMsg=String.format(SOLR_CALL_FAILED,globalPropId,urlString  );
			logger.error(exceptionMsg + ex);

			throw new PropertyInfoNotFoundException(exceptionMsg);
		}
		
		}


		try{
			cachingService.setMemcacheObject(solrSearchKeyForGlobalPropIDAndAuctionNumber, Integer.parseInt(cacheExpiration), propertyAssetInfoBO);
		}catch(Exception ex){
			logger.error("Error while setting global Asset details from cache"+ex);
		}

		return propertyAssetInfoBO;
	}
	
	@Override
	public PropertyInfoBO getGlobalPropertyDetailsForSoldAssets(String globalPropId,String auctionNumber) throws PropertyInfoNotFoundException{
		// TODO Auto-generated method stub

		PropertyInfoBO propertyAssetInfoBO=null;
		String solrSearchKeyForGlobalPropIDAndAuctionNumber=this.getClass().getName()+"_"+globalPropId+"_"+auctionNumber;

		try {

			propertyAssetInfoBO=(PropertyInfoBO)cachingService.getMemcacheObject(solrSearchKeyForGlobalPropIDAndAuctionNumber);
			if(propertyAssetInfoBO!=null)
				return propertyAssetInfoBO;
		}catch(Exception ex){
			logger.error("Error while retrieving global Asset details from cache"+ex);
		}

		String cacheExpiration=PropertyUtil.get("acpcommon.solr.globalpropertyid.cache.timeout");
		String urlString =PropertyUtil.get("acpcommon.solr.url");
		
		try{
			SolrServer solr = new HttpSolrServer(urlString);
			SolrQuery parameters = new SolrQuery();
			parameters.set("wt", "json");
			String query="globalPropID:"+globalPropId;
			if(StringUtil.isNotBlank(auctionNumber))
				query=query+" AND auctionNumber:"+"\""+auctionNumber+"\"";
			parameters.setParam("collection", "auctionComSold");
			parameters.addFilterQuery("(collectionName:\"auctionComSold\" AND soldDate:[NOW-14DAY TO *])");
			//parameters.setSortField("collectionName", ORDER.asc);
			parameters.setQuery(query);	

			QueryResponse solrResponse = solr.query(parameters);

			if (solrResponse != null) {
				SolrDocumentList documentsFromSOLR = solrResponse.getResults();
				if (documentsFromSOLR != null) {
					Iterator<SolrDocument> globalPropertyDetailsItr= documentsFromSOLR.iterator();

					if (globalPropertyDetailsItr.hasNext()) {
						SolrDocument document = globalPropertyDetailsItr.next();
						propertyAssetInfoBO = createPropertyInfoBO(document);
					}

				}

			}
			if(propertyAssetInfoBO==null){
				
				try{
					propertyAssetInfoBO=getGlobalPropertyDetails(globalPropId);
				}catch(Exception ex){
					String exceptionMsg=String.format(GLOBAL_PROP_ID_AND_AUCTION_NUMBER_NOT_FOUND, globalPropId,auctionNumber,urlString);
					logger.info(exceptionMsg );
					throw new PropertyInfoNotFoundException(exceptionMsg);	
				}
				
				 
			}
		}catch(Exception ex){
			String exceptionMsg=String.format(SOLR_CALL_FAILED,globalPropId,urlString  );
			logger.error(exceptionMsg + ex);

			throw new PropertyInfoNotFoundException(exceptionMsg);
		}
		
		


		try{
			cachingService.setMemcacheObject(solrSearchKeyForGlobalPropIDAndAuctionNumber, Integer.parseInt(cacheExpiration), propertyAssetInfoBO);
		}catch(Exception ex){
			logger.error("Error while setting global Asset details from cache"+ex);
		}

		return propertyAssetInfoBO;
	}
	

	@Override
	public List<FeaturedPropertiesBO> getFeaturePropertiesWithGeoLocations(
			String venueIDs, String propertyCity, String propertyState) throws PropertyInfoNotFoundException {

		List<FeaturedPropertiesBO> featuredePropertyList = new ArrayList<FeaturedPropertiesBO> ();
		try{


			/*     SELECT properties_image.thumbImage thumbImage,isLuxuryEvent,propertyType,globalAsset.propertyId propertyId,globalAsset.auctionID auctionId,propertyCity
			 * ,propertyState,startingBid,auctionType,globalPropID,propertyAddress,propertyZip,product_type 
			 * FROM globalAsset left join properties_image ON (properties_image.itemID=globalAsset.propertyID)  where 
							 (((featured='Yes' or platinum='Yes') and StatusCSV='Auction' and (preSaleStatus!='Sold' or preSaleStatus is NULL)
							  and (bidEndDT>=now() or bidEndDT is NULL or bidEndDT='0000-00-00 00:00:00') and propertyType!='') OR (isLuxuryEvent='Yes')) 
							  and (auctionDayStatus!=4 and auctionDayStatus!=7 and auctionDayStatus!=14 and auctionDayStatus!=18)
							 and properties_image.thumbImage<>''  and properties_image.defaultStatus='1' and StatusCSV='Auction'  
						GROUP BY globalAsset.propertyID ORDER BY isLuxuryEvent ASC limit 0,15 

			 * SELECT properties_image.thumbImage thumbImage,isLuxuryEvent isLuxuryEvent,propertyType,globalAsset.propertyId propertyId,globalAsset.auctionID auctionId,
							propertyAddress,propertyCity,propertyState,startingBid,auctionType,globalPropID,propertyZip,
							product_type FROM globalAsset left join properties_image ON (properties_image.itemID=globalAsset.propertyID)
							where (globalAsset.venueID in(:VENUE_ID_LIST)) and (
								propertyCity like :PROPERTY_CITY and propertyState like :PROPERTY_STATE and (properties_image.thumbImage<>'' 
								and (featured='Yes' or platinum='Yes') and StatusCSV='Auction' 
								and (preSaleStatus!='Sold' or preSaleStatus is NULL) 
								and (bidEndDT>=now() or bidEndDT is NULL or bidEndDT='0000-00-00 00:00:00') and propertyType!='') OR (isLuxuryEvent='Yes')
								) 
							and (auctionDayStatus!=4 and auctionDayStatus!=7 and auctionDayStatus!=14 and auctionDayStatus!=18)
							and StatusCSV='Auction'  GROUP BY globalAsset.propertyID ORDER BY isLuxuryEvent ASC limit 0,15 
			 */



			FeaturedPropertiesBO featuredPropertiesBO = null; 
			String urlString = PropertyUtil.get("acpcommon.solr.url");	
			SolrServer solr = new HttpSolrServer(urlString);
			SolrQuery parameters = new SolrQuery();
			parameters.set("wt", "json");
			parameters.set("rows", "15");
			parameters.set("group", "true");
			parameters.set("group.format", "simple");
			parameters.set("group.field", "propertyId");
			parameters.set("fl", "isLuxuryEvent");
			String query="venueId :(";
			if(StringUtils.isNotBlank(venueIDs)){
				for(String venueId : venueIDs.split(",")){
					query += venueId +" OR ";
				}
				query = query.substring(0, query.lastIndexOf("OR"));
				query += ")" ;
			}

			query += " AND (propertyCity : "+propertyCity;
			query += " AND propertyState : "+propertyState;
			query += " AND !(thumbImage : \""+"\""+")";
			query += " AND (featured : Yes OR platinum : Yes)";
			query += " AND ((!(preSaleStatus:Sold)) OR (!(preSaleStatus : ''))) ";
			//query += "AND (bidEndDT>=Date OR ((bidEndDT : \"\")) OR bidEndDT:'0000-00-00*')" ;
			query += 		" AND (!(propertyType:'')) OR (isLuxuryEvent='Yes'))";
			query += "AND ((!(auctionDayStatus:4)) and (!(auctionDayStatus:7)) AND (!(auctionDayStatus:14)) and (!(auctionDayStatus:18)))";
			query += "AND StatusCSV:Auction";
			parameters.setQuery(query);	

			QueryResponse solrResponse = solr.query(parameters);

			if (solrResponse != null) {
				SolrDocumentList featuredProperties = solrResponse.getResults();
				if (featuredProperties != null) {
					Iterator<SolrDocument> featuredPropertyDetailsItr= featuredProperties.iterator();

					while (featuredPropertyDetailsItr.hasNext()) {
						SolrDocument document = featuredPropertyDetailsItr.next();
						featuredPropertiesBO = createFeaturedPropertiesBO(document);
						featuredePropertyList.add(featuredPropertiesBO);
					}

				}

			}

		}catch(Exception ex){
			String exceptionMsg=String.format(SOLR_FEATURED_PROPERTY_CALL_FAILED,venueIDs,propertyCity, propertyState );
			logger.error(exceptionMsg, ex);

			throw new PropertyInfoNotFoundException(exceptionMsg);
		}


		// TODO Auto-generated method stub
		return featuredePropertyList;
	}

	@Override
	public List<FeaturedPropertiesBO> getFeaturePropertiesWithPropertyIDs()
			throws PropertyInfoNotFoundException {

		List<FeaturedPropertiesBO> featuredePropertyList = new ArrayList<FeaturedPropertiesBO> ();
		try{
			/*
			 * SELECT properties_image.thumbImage thumbImage,isLuxuryEvent,propertyType,globalAsset.propertyId propertyId,globalAsset.auctionID auctionId,propertyCity
			 * ,propertyState,startingBid,auctionType,globalPropID,propertyAddress,propertyZip,product_type 
			 * FROM globalAsset left join properties_image ON (properties_image.itemID=globalAsset.propertyID)  where 
							 (((featured='Yes' or platinum='Yes') and StatusCSV='Auction' and (preSaleStatus!='Sold' or preSaleStatus is NULL)
							  and (bidEndDT>=now() or bidEndDT is NULL or bidEndDT='0000-00-00 00:00:00') and propertyType!='') OR (isLuxuryEvent='Yes')) 
							  and (auctionDayStatus!=4 and auctionDayStatus!=7 and auctionDayStatus!=14 and auctionDayStatus!=18)
							 and properties_image.thumbImage<>''  and properties_image.defaultStatus='1' and StatusCSV='Auction'  
						GROUP BY globalAsset.propertyID ORDER BY isLuxuryEvent ASC limit 0,15 
			 */

			FeaturedPropertiesBO featuredPropertiesBO = null; 
			String urlString = PropertyUtil.get("acpcommon.solr.url");	
			SolrServer solr = new HttpSolrServer(urlString);
			SolrQuery parameters = new SolrQuery();
			parameters.set("wt", "json");
			parameters.set("rows", "15");
			parameters.set("group", "true");
			parameters.set("group.format", "simple");
			parameters.set("group.field", "propertyId");
			parameters.set("fl", "isLuxuryEvent");
			//			String query="venueId :(";
			//			if(StringUtils.isNotBlank(venueIDs)){
			//				for(String venueId : venueIDs.split(",")){
			//					query += venueId +" OR ";
			//				}
			//				query = query.substring(0, query.lastIndexOf("OR"));
			//				query += ")" ;
			//			}


			String query = "  (featured : Yes OR platinum : Yes)";
			query += " AND  ((!(preSaleStatus:Sold)) OR ((preSaleStatus : ''))) ";
			//query += "AND (bidEndDT>=Date OR ((bidEndDT : \"\")) OR bidEndDT:'0000-00-00*')";
			query += "AND (!(propertyType:''))) OR (isLuxuryEvent:'Yes')";
			query += "AND ((!(auctionDayStatus:4)) and (!(auctionDayStatus:7)) and (!(auctionDayStatus:14)) and (!(auctionDayStatus:18)))";
			query += "AND StatusCSV:Auction";
			query += " AND !(thumbImage : \""+"\""+")";
			query += " AND defaultStatus:1";
			parameters.setQuery(query);	

			QueryResponse solrResponse = solr.query(parameters);

			if (solrResponse != null) {
				SolrDocumentList featuredProperties = solrResponse.getResults();
				if (featuredProperties != null) {
					Iterator<SolrDocument> featuredPropertyDetailsItr= featuredProperties.iterator();

					while (featuredPropertyDetailsItr.hasNext()) {
						SolrDocument document = featuredPropertyDetailsItr.next();
						featuredPropertiesBO = createFeaturedPropertiesBO(document);
						featuredePropertyList.add(featuredPropertiesBO);
					}

				}

			}

		}catch(Exception ex){
			String exceptionMsg=String.format(SOLR_FEATURED_PROPERTY_CALL_FAILED,null,null,null );
			logger.error(exceptionMsg, ex);

			throw new PropertyInfoNotFoundException(exceptionMsg);
		}


		// TODO Auto-generated method stub
		return featuredePropertyList;
	}

	@Override
	public List<FeaturedPropertiesBO> getFeaturePropertiesAll(String venueIDs)
			throws PropertyInfoNotFoundException {
		// TODO Auto-generated method stub
		/*
		 * 							SELECT properties_image.thumbImage thumbImage,isLuxuryEvent isLuxuryEvent,propertyType,globalAsset.propertyId propertyId,globalAsset.auctionID auctionId,propertyAddress,propertyCity,propertyState,startingBid,auctionType,globalPropID,propertyZip,product_type  FROM globalAsset 
							left join properties_image ON (properties_image.itemID=globalAsset.propertyID) 
							left join property_trustee  as pt on pt.propertyId=globalAsset.propertyID
							where (((globalAsset.venueID in(:VENUE_ID_LIST) )and (featured='Yes' or platinum='Yes') and StatusCSV='Auction' and (preSaleStatus!='Sold' or preSaleStatus is NULL) 
							and (bidEndDT>=now() or bidEndDT is NULL or bidEndDT='0000-00-00 00:00:00') and propertyType!='') OR (isLuxuryEvent='Yes')) 
							and (auctionDayStatus!=4 and auctionDayStatus!=7 and auctionDayStatus!=14 and auctionDayStatus!=18) and properties_image.thumbImage<>''  

							//TODO --- county Auction date check
							and properties_image.defaultStatus='1'  and if( globalAsset.product_type='Post Foreclosure' and globalAsset.statusCSV='Auction',if(pt.countyAuctionDate is Not NULL  
							and pt.countyAuctionDate!='0000-00-00' and DATEDIFF(pt.countyAuctionDate,CURDATE())>=20 ,0,if( ( pt.countyAuctionDate=''  or pt.countyAuctionDate='0000-00-00' or pt.countyAuctionDate is NULL ) 
							and  DATEDIFF(globalAsset.PropertyAuctionDate,CURDATE())>=23,0,1 )) , 1 )
							 and StatusCSV='Auction'  GROUP BY globalAsset.propertyID ORDER BY isLuxuryEvent ASC limit 0,15
		 */
		List<FeaturedPropertiesBO> featuredePropertyList = new ArrayList<FeaturedPropertiesBO> ();
		try{


			FeaturedPropertiesBO featuredPropertiesBO = null; 
			String urlString = PropertyUtil.get("acpcommon.solr.url");	
			SolrServer solr = new HttpSolrServer(urlString);
			SolrQuery parameters = new SolrQuery();
			parameters.set("wt", "json");
			parameters.set("rows", "15");
			parameters.set("group", "true");
			parameters.set("group.format", "simple");
			parameters.set("group.field", "propertyId");
			parameters.set("fl", "isLuxuryEvent");
			String query="venueId:(";
			if(StringUtils.isNotBlank(venueIDs)){
				for(String venueId : venueIDs.split(",")){
					query = query+venueId +" OR ";
				}
				query = query.substring(0, query.lastIndexOf("OR"));
				query = query+")" ;
			}
			query = query+"  (featured : Yes OR platinum : Yes)";
			query += " AND  ((!(preSaleStatus:Sold)) OR ((preSaleStatus : ''))) ";
			//query += "AND (bidEndDT>=Date OR ((bidEndDT : \"\")) OR bidEndDT:'0000-00-00*')";
			query += "AND (!(propertyType:''))) OR (isLuxuryEvent:'Yes')";
			query += "AND ((!(auctionDayStatus:4)) and (!(auctionDayStatus:7)) and (!(auctionDayStatus:14)) and (!(auctionDayStatus:18)))";
			query += "AND StatusCSV:Auction";
			query += " AND !(thumbImage : \""+"\""+")";
			query += " AND defaultStatus:1";
			logger.info(query);
			parameters.setQuery(query);	

			QueryResponse solrResponse = solr.query(parameters);

			if (solrResponse != null) {
				SolrDocumentList featuredProperties = solrResponse.getResults();
				if (featuredProperties != null) {
					Iterator<SolrDocument> featuredPropertyDetailsItr= featuredProperties.iterator();

					while (featuredPropertyDetailsItr.hasNext()) {
						SolrDocument document = featuredPropertyDetailsItr.next();
						featuredPropertiesBO = createFeaturedPropertiesBO(document);
						featuredePropertyList.add(featuredPropertiesBO);
					}

				}

			}

		}catch(Exception ex){
			String exceptionMsg=String.format(SOLR_FEATURED_PROPERTY_CALL_FAILED,null,null,null );
			logger.error(exceptionMsg, ex);

			throw new PropertyInfoNotFoundException(exceptionMsg);
		}


		// TODO Auto-generated method stub
		return featuredePropertyList;
	}


	/*
	 * (non-Javadoc)
	 * @see com.redwood.rp.solr.service.external.ExternalSolrSearch#getProperties(java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<PropertyInfoBO> getProperties(String collection, String mainQuery, String filterQuery, Integer rows) throws ServiceException {

		List<PropertyInfoBO> properties = null;
		PropertyInfoBO propertyAssetInfoBO = null;
		String urlString = PropertyUtil.get("acpcommon.solr.generic.url");

		try {
			if (StringUtils.isNotBlank(urlString)) {
				urlString = urlString + collection;
			}
			SolrServer solr = new HttpSolrServer(urlString);
			SolrQuery parameters = new SolrQuery();
			parameters.set("wt", "json");
			if (rows == null) {
				rows = Integer.valueOf(PropertyUtil.get("solr.results.rows.default"));
			}
			parameters.set("rows", rows);
			if (StringUtils.isNotBlank(mainQuery)) {
				parameters.setQuery(mainQuery);	
			}
			if (StringUtils.isNotBlank(filterQuery)) {
				parameters.setFilterQueries(filterQuery);
			}
			logger.info("querying solr to retrieve properties for " + parameters);
			QueryResponse solrResponse = solr.query(parameters);

			if (solrResponse != null) {
				SolrDocumentList documentsFromSOLR = solrResponse.getResults();
				if (documentsFromSOLR != null) {
					properties = new ArrayList<PropertyInfoBO>();
					Iterator<SolrDocument> documents = documentsFromSOLR.iterator();
					while (documents.hasNext()) {
						SolrDocument document = documents.next();
						propertyAssetInfoBO = createPropertyInfoBO(document);
						properties.add(propertyAssetInfoBO);
					}
				}
			}

			if (properties == null || properties.isEmpty()) {
				String exceptionMsg = "An error occurred when fetching property details from collection " + collection + " and parameters q : " + mainQuery + " and fq : " + filterQuery;
				logger.error(exceptionMsg);
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, new ErrorVO(null, exceptionMsg, null));
			}

		} catch(Exception exception) {
			String exceptionMsg = "An error occurred when fetching property details from collection " + collection + " and parameters q : " + mainQuery + " and fq : " + filterQuery;
			logger.error(exceptionMsg, exception);
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, new ErrorVO(null, exceptionMsg, null));
		}
		return properties;
	}
	public List<PropertyInfoBO> getGlobalPropertyDetails(String []globalPropIds,boolean includeSoldAssets) throws PropertyInfoNotFoundException{
		// TODO Auto-generated method stub

		List<PropertyInfoBO> propertyAssetInfoBOList=new ArrayList<PropertyInfoBO>();
		PropertyInfoBO propertyAssetInfoBO=null;
		
		
		String urlString = PropertyUtil.get("acpcommon.solr.url");//"http://10.1.15.244:8983/solr/auctionCom";
       
		String globalPropIdList= StringUtil.convertStringArrayToString(globalPropIds, " ");
		
		if(globalPropIdList ==null)
		{
			String exceptionMsg=String.format(GLOBAL_PROP_ID_NOT_FOUND, globalPropIdList,urlString);
			logger.info(exceptionMsg );
			throw new PropertyInfoNotFoundException(exceptionMsg); 		
		}
		
		
		try{
			SolrServer solr = new HttpSolrServer(urlString);
			SolrQuery parameters = new SolrQuery();
			
			String query="globalPropID:"+"("+globalPropIdList+")";
			parameters.setQuery(query);	
			parameters.set("wt", "json");
			//parameters.setParam("fl", "globalPropID");
			parameters.setParam("collection", "auctionCom,auctionComSold");
			parameters.addFilterQuery("(collectionName:\"auctionComSold\" AND soldDate:[NOW-14DAY TO *]) OR (collectionName:\"auctionCom\" AND -soldDate:[* TO *])");
			if(includeSoldAssets)
			parameters.setParam("collection", "auctionCom,auctionComSold");
				

			QueryResponse solrResponse = solr.query(parameters);

			if (solrResponse != null) {
				SolrDocumentList documentsFromSOLR = solrResponse.getResults();
				if (documentsFromSOLR != null) {
					Iterator<SolrDocument> globalPropertyDetailsItr= documentsFromSOLR.iterator();

					while (globalPropertyDetailsItr.hasNext()) {
						SolrDocument document = globalPropertyDetailsItr.next();
						propertyAssetInfoBO = createPropertyInfoBO(document);
						propertyAssetInfoBOList.add(propertyAssetInfoBO);
					}

				}

			}
			if(propertyAssetInfoBO==null){
				String exceptionMsg=String.format(GLOBAL_PROP_ID_NOT_FOUND, globalPropIdList,urlString);
				logger.info(exceptionMsg );
				throw new PropertyInfoNotFoundException(exceptionMsg); 
			}
		}catch(Exception ex){
			String exceptionMsg=String.format(SOLR_CALL_FAILED,globalPropIdList,urlString  );
			logger.error(exceptionMsg, ex);

			throw new PropertyInfoNotFoundException(exceptionMsg);
		}


		

		return propertyAssetInfoBOList;
	}
	
	private static PropertyInfoBO createPropertyInfoBO(SolrDocument document) {
		PropertyInfoBO propertyAssetInfoBO = new PropertyInfoBO();
		propertyAssetInfoBO.setGlobalPropertyId((String)document.getFieldValue("globalPropID"));
		propertyAssetInfoBO.setPropertyId((String) document.getFieldValue("propertyId"));
		propertyAssetInfoBO.setAuctionID((String) document.getFieldValue("auctionID"));
		propertyAssetInfoBO.setAuctionType((String) document.getFieldValue("auctionType"));
		propertyAssetInfoBO.setAuctionNumber((String) document.getFieldValue("auctionNumber"));
		propertyAssetInfoBO.setTrusteeSale((String) document.getFieldValue("trusteeSale"));
		propertyAssetInfoBO.setProductType((String) document.getFieldValue("product_type"));
		propertyAssetInfoBO.setPropertyType((String) document.getFieldValue("propertyType"));
		propertyAssetInfoBO.setPropertyState((String) document.getFieldValue("propertyState"));
		propertyAssetInfoBO.setPropertyOccupancyStatus((String) document.getFieldValue("propertyOccupancyStatus"));
		propertyAssetInfoBO.setPoolNumber((String) document.getFieldValue("poolNumber"));
		propertyAssetInfoBO.setVenueType((String) document.getFieldValue("venueType"));
		propertyAssetInfoBO.setVenueId((String) document.getFieldValue("venueId"));
		propertyAssetInfoBO.setCollectionName((String)document.getFieldValue("collectionName"));
		propertyAssetInfoBO.setDueDiligenceEnabled(BooleanUtil.toBoolean((String)document.getFieldValue("dueDiligence")));
		propertyAssetInfoBO.setTestEvent(BooleanUtil.toBoolean((String)(document.getFieldValue("isTestEvent"))));
		return propertyAssetInfoBO;
	}
	
	private static FeaturedPropertiesBO createFeaturedPropertiesBO(SolrDocument document) {
		FeaturedPropertiesBO featuredPropertiesBO = new FeaturedPropertiesBO();
		featuredPropertiesBO.setGlobalPropID((String)document.getFieldValue("globalPropID"));
		featuredPropertiesBO.setPropertyId((String) document.getFieldValue("propertyId"));
		featuredPropertiesBO.setAuctionID((String) document.getFieldValue("auctionID"));
		featuredPropertiesBO.setAuctionType((String) document.getFieldValue("auctionType"));
		featuredPropertiesBO.setProductType((String) document.getFieldValue("product_type"));
		featuredPropertiesBO.setPropertyState((String) document.getFieldValue("propertyState"));
		featuredPropertiesBO.setThumbImage((String) document.getFieldValue("thumbImage"));
		featuredPropertiesBO.setIsLuxuryEvent((String) document.getFieldValue("isLuxuryEvent"));
		featuredPropertiesBO.setPropertyAddress((String) document.getFieldValue("propertyAddress"));
		featuredPropertiesBO.setStartingBid((String) document.getFieldValue("startingBid"));
		featuredPropertiesBO.setPropertyZip((String) document.getFieldValue("propertyZip"));
		logger.info(featuredPropertiesBO.toString());
		return featuredPropertiesBO;
	}
}
