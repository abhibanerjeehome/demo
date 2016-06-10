package com.redwood.rp.core.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.core.exception.UtilityException;
import com.redwood.rp.core.vo.json.ErrorVO;

/**
 *=====================================================================
 *
 * The bean is used to define all the  queries.
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 01/16/2013   create
 *
 */	
public class QueryDefinitionBean {

	private Map<String, String> namedQueryMap = new HashMap<String, String>();
	private Map<String, String> commonQueries = new HashMap<String, String>();
	private Map<String, String> boxQueries = new HashMap<String, String>();


	private Logger logger = LoggerFactory.getLogger(QueryDefinitionBean.class.getName());

	/**
	 * @param queryName
	 * @return
	 * @throws DaoException
	 */
	public String getQueryByName(String queryName) throws UtilityException {
		String queryStr = "";
		String errorDescription = "";
		if (namedQueryMap != null) {
			if (commonQueries != null && !commonQueries.isEmpty()) {
				namedQueryMap.putAll(commonQueries);
			}
			if (boxQueries != null && !boxQueries.isEmpty()) {
				namedQueryMap.putAll(boxQueries);
			}
			queryStr = namedQueryMap.get(queryName);
			if(StringUtils.isBlank(queryStr)){
				errorDescription = "Failed to get the query string by name ' "+queryName+"'.";
				logger.error(MessageFormattor.errorFormat(QueryDefinitionBean.class.getName(),
						"getQueryByName", ExceptionType.EXCEPTION_UTILITY, errorDescription));
				throw new UtilityException(ExceptionType.EXCEPTION_UTILITY,
						new ErrorVO(ErrorDescription.ERR_CD_UT_QUERY_NOT_FOUND,ErrorDescription.ERR_MSG_UT_QUERY_NOT_FOUND, errorDescription));
			}
			return queryStr;
		} else {
			errorDescription = "Failed to get the query string by name ' "+queryName+"'. The namedQueryMap is null. Please check the spring bean configuraion file in Java.";
			logger.error(MessageFormattor.errorFormat(QueryDefinitionBean.class.getName(),
					"getQueryByName", ExceptionType.EXCEPTION_UTILITY, errorDescription));
			throw new UtilityException(ExceptionType.EXCEPTION_UTILITY,
					new ErrorVO(ErrorDescription.ERR_CD_COMMON_INTERNAL,ErrorDescription.ERR_MSG_COMMON_INTERNAL, errorDescription));
		}
	}

	public Map<String, String> getNamedQueryMap() {
		return namedQueryMap;
	}

	public void setNamedQueryMap(Map<String, String> namedQueryMap) {
		this.namedQueryMap = namedQueryMap;
	}

	public Map<String, String> getCommonQueries() {
		return commonQueries;
	}

	public void setCommonQueries(Map<String, String> commonQueries) {
		this.commonQueries = commonQueries;
	}

	public Map<String, String> getBoxQueries() {
		return boxQueries;
	}

	public void setBoxQueries(Map<String, String> boxQueries) {
		this.boxQueries = boxQueries;
	}
}