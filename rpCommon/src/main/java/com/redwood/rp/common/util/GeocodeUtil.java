package com.redwood.rp.common.util;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redwood.rp.common.util.GoogleGeocodeUtil.GeoLocation;
import com.redwood.rp.core.util.PropertyUtil;

public class GeocodeUtil {
	private static Logger logger = LoggerFactory.getLogger(GeocodeUtil.class.getName());
	private static final String SOLR_LOCATION_URL = PropertyUtil.get("URL")+"/locations";
	private static final String SELECT_QT="/select";

	
	public static GeoLocation getCityCountyStateZipGeoLocation(String val)
			throws SolrServerException {
		GeoLocation geo=null;
		HttpSolrServer solr = new HttpSolrServer(SOLR_LOCATION_URL);
		try{
		SolrQuery parameters = new SolrQuery();
		parameters.setQueryType(SELECT_QT);
		parameters.set("wt", "java");
		val=!("*:*".equals(val))?val.replace(",", "").replace(" ", ""):"";
		parameters.set("q", "\""+val+"\"");				
		parameters.set("rows", "1");
		parameters.set("defType","edismax");
        parameters.set("qf","citystateCodeZip^10.0 cityStateZip^10.0 stateCityZip^10.0 cityStateCode^10.0 stateCodeCity^10.0 stateCity^10.0 cityState^10 cityZip^10.0 state_code^10.0 stateName^10.0 stateCodeZip^10 countyName^1.0 countyNameStateCode^1.0 cityName^1.0 zip^1.0");		
        parameters.set("hl.fl", "citystateCodeZip,cityStateZip,stateCityZip,cityStateCode,stateCodeCity,stateCity,cityState,cityZip,state_code,stateName,stateCodeZip^10,countyName,countyNameStateCode,cityName,zip");
        parameters.set("hl","true");
        //parameters.set("sort","state_code asc");
		logger.debug("suggest req params>>"+parameters);
		QueryResponse solrResponse;
			solrResponse = solr.query(parameters);
			if (solrResponse != null && solrResponse.getStatus()==0 && 
					solrResponse.getResults()!=null && 
							solrResponse.getResults().getNumFound()>0) {
				SolrDocument doc=solrResponse.getResults().get(0);
				
				if(solrResponse.getHighlighting()!=null && solrResponse.getHighlighting().values().size()>0){
					//check if the match happened on county then put state and county
					geo=new GeoLocation();
					Map<String,List<String>> highlight=solrResponse.getHighlighting().values().iterator().next();
					if(highlight.containsKey("citystateCodeZip") || highlight.containsKey("cityStateZip") ||
							highlight.containsKey("stateCityZip") || highlight.containsKey("cityZip") ||
							highlight.containsKey("stateCodeZip") ||highlight.containsKey("zip")){
						geo.setLat((Double)doc.getFieldValue("zip_lat"));
						geo.setLng((Double)doc.getFieldValue("zip_lng"));
					}else if(highlight.containsKey("cityName") || highlight.containsKey("cityStateCode") ||
							highlight.containsKey("stateCodeCity")||highlight.containsKey("stateCity") ||
							highlight.containsKey("cityState")||highlight.containsKey("stateCity")){
						geo.setLat((Double)doc.getFieldValue("city_lat"));
						geo.setLng((Double)doc.getFieldValue("city_lng"));
					}else if(highlight.containsKey("countyName") || highlight.containsKey("countyNameStateCode")){
						geo.setLat((Double)doc.getFieldValue("county_lat"));
						geo.setLng((Double)doc.getFieldValue("county_lng"));
					}else if(highlight.containsKey("state_code") || highlight.containsKey("stateName") ){
						geo.setLat((Double)doc.getFieldValue("state_lat"));
						geo.setLng((Double)doc.getFieldValue("state_lng"));
					}
					
				}
			}
		}finally{
			try {
				if(solr!=null)solr.shutdown();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return geo;
	}	
}
