package com.redwood.rp.common.user.das.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.redwood.rp.genericquery.dao.ACPQueryDAO;
import com.redwood.rp.common.user.bo.FavPropertiesBO;
import com.redwood.rp.common.user.das.UserInformationDaoService;
import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.core.exception.UtilityException;
import com.redwood.rp.core.util.QueryDefinitionBean;

/**
 * Dao service class to fetch details related to user and user profile
 * information
 * 
 * @author asengamalai
 * 
 */
@Named
public class UserInformationDaoServiceImpl implements UserInformationDaoService  {

	private static final String GET_FAV_PROPERTIES_ALL = "GET_FAV_PROPERTIES_ALL";
	private static final String MAP_KEY_USERID = "USER_ID";

	@Autowired(required = false)
	private QueryDefinitionBean namedQueryBean;
	
	@Autowired(required = false)
	private ACPQueryDAO acpQueryDAO;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redwood.rp.common.user.das.UserInformationDaoService#
	 * getAllSavedProperties(java.lang.String)
	 */
	@Override
	public Map<String,FavPropertiesBO> getAllSavedProperties(String userid)
			throws DaoException {
		Map<String,FavPropertiesBO> favoritePropertiesMap=new HashMap<String,FavPropertiesBO>();
		try {
			Map<String, Object> paramMap = new LinkedHashMap<String, Object>();//using linkedhashmap to maintain order of values like list
			paramMap.put(MAP_KEY_USERID, userid);
			List<Map<String, Object>> result = acpQueryDAO.executeJdbcQuery(
					namedQueryBean.getQueryByName(GET_FAV_PROPERTIES_ALL),
					paramMap);
			
			for (Map<String, Object> record : result) {
				FavPropertiesBO favPropertyBO = new FavPropertiesBO();
				if (record.get("auctionID") != null) {
					favPropertyBO.setAuctionId(record.get("auctionID")
							.toString());
				}
				if (record.get("propertyId") != null) {
					favPropertyBO.setPropertyId(record.get("propertyId")
							.toString());
				}
				if (record.get("globalPropID") != null) {
					favPropertyBO.setGlobalPropertyId(record
							.get("globalPropID").toString());
				}
				if (record.get("userId") != null) {
					favPropertyBO.setUserId(record.get("userId").toString());
				}
				if (record.get("id") != null) {
					favPropertyBO.setId(record.get("id").toString());
				}
				if (record.get("status") != null) {
					favPropertyBO.setStatus(record.get("status").toString());
				}
				if (record.get("bidder_reg_status") != null) {
					favPropertyBO.setBidderRegStatus(record.get(
							"bidder_reg_status").toString());
				}
				favoritePropertiesMap.put(favPropertyBO.getGlobalPropertyId(), favPropertyBO);
			}
			return favoritePropertiesMap;
		} catch (UtilityException e) {
			// TODO Auto-generated catch block
			DaoException daoEx = new DaoException(e.getMessage(),
					e.getErrorVO());
			daoEx.setStackTrace(e.getStackTrace());
		}
		return favoritePropertiesMap;
	}



}
