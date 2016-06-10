package com.redwood.rp.flaunt.facade;

import com.redwood.rp.core.vo.generic.ACPRequest;
import com.redwood.rp.core.vo.generic.ACPResponse;
import com.redwood.rp.core.exception.ServiceException;

public interface UserFacade {
	
	public ACPResponse getUserRegistrationDetails(ACPRequest acpRequest) throws ServiceException;
	
	public ACPResponse updateUserPassword(ACPRequest acpRequest) throws ServiceException;
	
	public ACPResponse getUserPassword(ACPRequest acpRequest) throws ServiceException;
	
	public ACPResponse forgotPassword(ACPRequest acpRequest) throws ServiceException;

}
