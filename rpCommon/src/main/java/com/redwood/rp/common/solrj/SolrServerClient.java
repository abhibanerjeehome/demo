package com.redwood.rp.common.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.redwood.rp.core.util.PropertyUtil;

public class SolrServerClient {

	final static SolrServer solrServer;
	public final static String AUCTIONCOM_COLLECTION="/auctionCom";
	public final static String AUCTIONCOM_SOLD_COLLECTION="/auctionComSold";
	public final static String LOCATIONS_COLLECTION="/locations";
	public final static String SELECT_QUERYTYPE="/select";
	public final static String SUGGEST_QUERYTYPE="/suggest";
	public final static String SOLR_URL=PropertyUtil.get("acpcommon.solr.generic.url");
	

	//final static SolrServer locationSolrServer;
	static{
		HttpSolrServer solr=new HttpSolrServer(SOLR_URL);
		solr.setAllowCompression(true);
		solrServer=solr;
	}
	/**
	 * queries the auctionCom collection in the solr server and returns the response
	 * queries are executed against /solr/auctionCom/select
	 * @param query  the solr query to execute
	 * @return
	 * @throws SolrServerException
	 */
	public static QueryResponse queryAuctionCom(SolrQuery query) throws SolrServerException{
		return query(query,AUCTIONCOM_COLLECTION);
	}
	/**
	 * queries the auctionComSold collection in the solr server and returns the response
	 * queries are executed against /solr/auctionComSold/select
	 * @param query  the solr query to execute
	 * @return
	 * @throws SolrServerException
	 */
	public static QueryResponse queryAuctionComSold(SolrQuery query) throws SolrServerException{
		return query(query,AUCTIONCOM_SOLD_COLLECTION);
	}	
	
	/**
	 * queries the locations collection in the solr server and returns the response
	 * queries are executed against /solr/locations/select
	 * @param query  the solr query to execute
	 * @return
	 * @throws SolrServerException
	 */
	public static QueryResponse queryLocations(SolrQuery query) throws SolrServerException{
		return query(query,LOCATIONS_COLLECTION);
	}	

	/**
	 * queries the locations collection in the solr server and returns the response
	 * queries are executed against /solr/locations/select
	 * @param query  the solr query to execute
	 * @return
	 * @throws SolrServerException
	 */
	public static QueryResponse querySuggestLocations(SolrQuery query) throws SolrServerException{
		return querySuggest(query,LOCATIONS_COLLECTION);
	}	

	/**
	 * queries the solr server, but make sure you set the qt parameter to the corresponding collection and request handler
	 * eg., if you want to execute a select query against auctionCom collection than in do query.set("qt","/auctionCom/select")
	 * queries are executed against /solr/auctionComSold/select
	 * @param query  the solr query to execute
	 * @return
	 * @throws SolrServerException
	 */
	public static QueryResponse query(SolrQuery query) throws SolrServerException{
		
		if(solrServer!=null){
			return solrServer.query(query,SolrRequest.METHOD.POST);
		}
		else{
			throw new SolrServerException("Solr Server is not initalized");
		}
	}	

	/**
	 * queryies the specified collection against the solr server, and it defaults to /select Requesthandler
	 * @param query
	 * @param collection
	 * @return
	 * @throws SolrServerException
	 */
	public static QueryResponse query(SolrQuery query,String collection) throws SolrServerException{
		
		if(solrServer!=null){
			if(query!=null){
				query.set("qt",collection+SELECT_QUERYTYPE);
			}
			return solrServer.query(query,SolrRequest.METHOD.POST);
		}
		else{
			throw new SolrServerException("Solr Server is not initalized");
		}
	}	
	
	/**
	 * queryies the specified collection against the solr server, and it defaults to /suggest Requesthandler
	 * @param query
	 * @param collection
	 * @return
	 * @throws SolrServerException
	 */
	public static QueryResponse querySuggest(SolrQuery query,String collection) throws SolrServerException{
		
		if(solrServer!=null){
			if(query!=null){
				query.set("qt",collection+SUGGEST_QUERYTYPE);
				System.out.println(query.getQuery()+"<<>>>"+query);
			}
			return solrServer.query(query,SolrRequest.METHOD.POST);
		}
		else{
			throw new SolrServerException("Solr Server is not initalized");
		}
	}		
	/**
	 * this returns the solrServer instance maintained by this class for other uses
	 * please make sure not to call shutdown
	 * @return
	 */
	public static SolrServer getSolrServer(){
		return solrServer;
	}
	
	public static void main(String args[]) throws SolrServerException{
		SolrQuery query=new SolrQuery();
		query.set("q","*:*");
		query.set("qt","/select");
		System.out.println(solrServer.query(query));
	}
}
