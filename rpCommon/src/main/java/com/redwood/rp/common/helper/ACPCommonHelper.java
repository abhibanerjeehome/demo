package com.redwood.rp.common.helper;

import java.util.HashMap;
import java.util.Map;

import com.redwood.rp.genericquery.constant.QueryType;
import com.redwood.rp.genericquery.vo.generic.GenericQueryRequest;

public class ACPCommonHelper {
	public GenericQueryRequest getGenericQueryRequest(String requestType,Map<String,Object> paramMap){
		GenericQueryRequest genericQueryRequest =getGenericQueryRequest(requestType);
		genericQueryRequest.setParameterMap(paramMap);
		return genericQueryRequest;
	}
	
	public GenericQueryRequest getGenericQueryRequest(String requestType){
			Map<String,Object> paramMap = new HashMap<String,Object>();
					
					GenericQueryRequest genericQueryRequest = new GenericQueryRequest();
					genericQueryRequest.setQueryName(requestType);
					genericQueryRequest.setQueryType(QueryType.SELECT);
					genericQueryRequest.setSP(false);
					genericQueryRequest.setParameterMap(paramMap);
					
					return genericQueryRequest;
	}
}
