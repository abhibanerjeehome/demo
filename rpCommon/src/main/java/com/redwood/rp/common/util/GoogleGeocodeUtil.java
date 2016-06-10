package com.redwood.rp.common.util;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.redwood.rp.core.util.PropertyUtil;

/**
 * This is a utility wrapper class for Google geocode map api
 * 
 * @author asengamalai
 *
 */
public class GoogleGeocodeUtil {
	private static Logger logger=LoggerFactory.getLogger(GoogleGeocodeUtil.class);
	private static final int TIME_OUT=500;//in milli
	private static final String XPATH_EXP_LOCATION="//geometry/location";
	private static final String XPATH_EXP_LAT="//lat/text()";
	private static final String XPATH_EXP_LNG="//lng/text()";
	private static final String XPATH_EXP_STATUS="/GeocodeResponse/status/text()";
	private static final String GOOGLE_GEO_URL=PropertyUtil.get("google.geocode.url");
	private static final String GOOGLE_OK_RESP="ok";
	private static final String GOOGLE_REQ_SENSOR="sensor";
	private static final String GOOGLE_REQ_ADDR="address";
	private static final String GOOGLE_REQ_REGION="region";
	private static final String GOOGLE_REQ_CLIENT="client";
	private static final String GOOGLE_REQ_SIGNATURE="signature";
	private static final String GOOGLE_CLIENT=PropertyUtil.get("google.geocode.clientid");
	private static final String GOOGLE_CLIENT_KEY=PropertyUtil.get("google.geocode.secret");
	private static final HttpClient client;
	static{
		MultiThreadedHttpConnectionManager connectionManager = 
	      		new MultiThreadedHttpConnectionManager();
		client=new HttpClient(connectionManager);
		client.getParams().setSoTimeout(TIME_OUT);
		client.getHttpConnectionManager().getParams().setConnectionTimeout(TIME_OUT);
	}
	/**
	 * This method returns the Latitude and Longitude for a given address and region
	 * The results are based on google geocode api.
	 * The method returns the first lat/long in the returned list from google(if more than one comes)
	 * or returns null. 
	 * @param address
	 * @param region
	 * @return
	 */
	public static GeoLocation getLatLongForAddress(String address,String region){
		HttpMethod method=null;
		if(address==null|| address.isEmpty())
		{
			return null;
		}
		try{
			List<NameValuePair> queryParams=new ArrayList<NameValuePair>();
			//for now the sensor is hardcoded, if it will be used by devices, please make this parameterized
			queryParams.add(new NameValuePair(GOOGLE_REQ_SENSOR,"false"));
			queryParams.add(new NameValuePair(GOOGLE_REQ_ADDR,address));
			if(region!=null && !region.isEmpty()){
				queryParams.add(new NameValuePair(GOOGLE_REQ_REGION,region));
			}							
			method=new GetMethod(GOOGLE_GEO_URL);
			addGoogleSignature(method,queryParams);
			logger.debug(method.getQueryString());
			int responsecode=client.executeMethod(method);
			if(responsecode == HttpStatus.SC_OK){
				XPathFactory factory=XPathFactory.newInstance();
				XPath xpath=factory.newXPath();
				Document doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(method.getResponseBodyAsStream());				
				String status=xpath.evaluate(XPATH_EXP_STATUS, doc);
				if(GOOGLE_OK_RESP.equalsIgnoreCase(status)){
					NodeList nodes=(NodeList)xpath.evaluate(XPATH_EXP_LOCATION, doc,XPathConstants.NODESET);
					if(nodes.getLength()>0){
						return new GeoLocation(xpath.evaluate(XPATH_EXP_LAT, nodes.item(0)),xpath.evaluate(XPATH_EXP_LNG, nodes.item(0)));
					}
				}

			}
			
		}
		catch(Exception ex){
			logger.error("Error trying to get LAT and LONG from google api",ex);
		}
		finally{
			if(method!=null ){
				method.releaseConnection();
			}			
		}		
		return null;
	}
	/**
	 * adds a google signature for business request, if it is configured otherwise does
	 * the free google api call
	 * @param method
	 * @param queryParams
	 * @param str
	 */
	private static void addGoogleSignature(HttpMethod method,List<NameValuePair>queryParams){
		if(GOOGLE_CLIENT_KEY !=null && GOOGLE_CLIENT!=null){			
			queryParams.add(new NameValuePair(GOOGLE_REQ_CLIENT,GOOGLE_CLIENT));
			method.setQueryString(queryParams.toArray(new NameValuePair[0]));
			 byte[] decodedKey = Base64.decodeBase64(GOOGLE_CLIENT_KEY.getBytes());
			 final SecretKeySpec sha1Key = new SecretKeySpec(decodedKey, "HmacSHA1");
			 try {
				final Mac key = Mac.getInstance("HmacSHA1");
				key.init(sha1Key);
				key.update((method.getPath()+"?"+method.getQueryString()).getBytes());
				String signedKey=new String(Base64.encodeBase64(key.doFinal()));
				signedKey=signedKey.replace("+", "-");
				signedKey=signedKey.replace("/", "_");				
				queryParams.add(new NameValuePair(GOOGLE_REQ_SIGNATURE,signedKey));
				method.setQueryString(queryParams.toArray(new NameValuePair[0]));
			} catch (Exception e) {
				logger.error("Error with google Key",e);					
			}			 
		}		

	}

	
	public static class GeoLocation{
		private double lat;
		private double lng;
		public GeoLocation(){}
		public GeoLocation(String latString,String lngString){
			this.lat=Double.parseDouble(latString);
			this.lng=Double.parseDouble(lngString);
		}
		public GeoLocation(double lat,double lng){
			this.lat=lat;
			this.lng=lng;
		}
		public double getLat() {
			return lat;
		}
		public void setLat(double lat) {
			this.lat = lat;
		}
		public double getLng() {
			return lng;
		}
		public void setLng(double lng) {
			this.lng = lng;
		}
		@Override
		public String toString() {
			return "GeoLocation [lat=" + lat + ", lng=" + lng + "]";
		}
		
	}

}
