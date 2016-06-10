package com.redwood.rp.flaunt.das.dao;

import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.flaunt.das.dto.StateVO;


public interface StatesDAO {
	
	/**
	 * @param countryID
	 * @return
	 */
	
	public StateVO getStatesByCountryId (String countryId) throws DaoException;

}
