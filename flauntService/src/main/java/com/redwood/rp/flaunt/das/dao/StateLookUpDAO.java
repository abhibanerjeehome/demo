package com.redwood.rp.flaunt.das.dao;

import com.redwood.rp.core.exception.DaoException;

public interface StateLookUpDAO {

	public String getCountryNameById(String countryId) throws DaoException;
	
	public String getStateNameById(String stateId) throws DaoException;
}
