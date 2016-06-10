package com.redwood.rp.genericquery.vo.generic;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.ValidationException;
import com.redwood.rp.core.util.MessageFormattor;
import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.genericquery.constant.QueryType;

/**
 *=====================================================================
 * Simple DataCall Request Object
 *
 * Request Value Object for simple data call
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 01/16/2013   create
 *
 */	
public class GenericQueryRequest {
	
	private static Logger logger = LoggerFactory.getLogger(GenericQueryRequest.class.getName());
	
	private String queryName;
	
	private QueryType queryType;
	
	/** True if it is stored procedure */
	private boolean isSP = false;
	
	/** just need to setup one of following */
	private Map<String,Object> parameterMap;
	private Map<String,Object>[] batchParameterMaps;
	
	/**
	 * @param request
	 * @throws ValidationException
	 */
	public static void validateGenericQueryRequest(GenericQueryRequest request) throws ValidationException{
		String errorDescription = "";
		if (StringUtils.isBlank(request.getQueryName())) {
			errorDescription = "The input ACPRequest object cannot be null.";
			logger.error(MessageFormattor.errorFormat(GenericQueryRequest.class.getName(),
	                "validateGenericQueryRequest", ExceptionType.EXCEPTION_VALIDATION, errorDescription));
			throw new ValidationException(ExceptionType.EXCEPTION_VALIDATION,
					new ErrorVO(ErrorDescription.ERR_CD_VALIDATION_NULLPOINTER,ErrorDescription.ERR_MSG_VALIDATION_NULLPOINTER, errorDescription));
		}
	}
	
	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public Map<String, Object> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(Map<String, Object> parameterMap) {
		this.parameterMap = parameterMap;
	}

	public Map<String, Object>[] getBatchParameterMaps() {
		return batchParameterMaps;
	}

	public void setBatchParameterMap(Map<String, Object>[] batchParameterMaps) {
		this.batchParameterMaps = batchParameterMaps;
	}

	public QueryType getQueryType() {
		return queryType;
	}

	public void setQueryType(QueryType queryType) {
		this.queryType = queryType;
	}

	public boolean isSP() {
		return isSP;
	}

	public void setSP(boolean isSP) {
		this.isSP = isSP;
	}

}
