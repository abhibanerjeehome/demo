package com.redwood.rp.flaunt.das.service;

import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.flaunt.bo.UserAcceptance;

public interface AcceptanceService {

	public void insertAcceptanceNDA(UserAcceptance userAcceptance) throws ServiceException;
	
}
