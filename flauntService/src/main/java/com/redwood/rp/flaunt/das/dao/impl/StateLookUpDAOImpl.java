package com.redwood.rp.flaunt.das.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.redwood.rp.core.base.das.jdbc.impl.AbstractJdbcDaoImpl;
import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.core.exception.UtilityException;
import com.redwood.rp.core.util.QueryDefinitionBean;
import com.redwood.rp.core.util.StringUtil;
import com.redwood.rp.flaunt.das.dao.StateLookUpDAO;

/**
 * Lookup DAO for countries and states
 * 
 */
public class StateLookUpDAOImpl extends AbstractJdbcDaoImpl implements StateLookUpDAO {

	private QueryDefinitionBean namedQueryBean;
 
	public String getCountryNameById(String countryId) throws DaoException {
		if (StringUtil.isBlank(countryId)) {
			return null;
		}
		String sqlStr;
		try {
			sqlStr = namedQueryBean.getQueryByName("GET_COUNTRY_NAME_BY_ID");
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(),
					e.getErrorVO());
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("countryID", countryId);
		List<Map<String, Object>> resultList = executeJdbcQuery(sqlStr,paramMap);
		if (resultList == null || resultList.size() == 0) {
			return null;
		} else {
			Map<String, Object> map = resultList.get(0);
			Object stateObj = map.get("state");
//			Object abbrevationObj = map.get("abbrevation");
			return stateObj==null?"":stateObj.toString();
		}
	}
	
	public String getStateNameById(String stateId) throws DaoException {
		if (StringUtil.isBlank(stateId)) {
			return null;
		}
		String sqlStr;
		try {
			sqlStr = namedQueryBean.getQueryByName("GET_STATE_NAME_BY_ID");
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(),
					e.getErrorVO());
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("stateID", stateId);
		List<Map<String, Object>> resultList = executeJdbcQuery(sqlStr,paramMap);
		if (resultList == null || resultList.size() == 0) {
			return null;
		} else {
			Map<String, Object> map = resultList.get(0);
//			Object stateObj = map.get("state");
			Object abbrevationObj = map.get("abbrevation");
			return abbrevationObj==null?"":abbrevationObj.toString();
		}
	}

	public QueryDefinitionBean getNamedQueryBean() {
		return namedQueryBean;
	}

	public void setNamedQueryBean(QueryDefinitionBean namedQueryBean) {
		this.namedQueryBean = namedQueryBean;
	}
	
	
}
