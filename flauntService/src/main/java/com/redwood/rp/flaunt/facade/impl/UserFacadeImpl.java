package com.redwood.rp.flaunt.facade.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.redwood.rp.core.base.pattern.facade.GenericServiceLocator;
import com.redwood.rp.core.vo.generic.ACPRequest;
import com.redwood.rp.core.vo.generic.ACPResponse;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.flaunt.das.service.UserRegistrationService;
import com.redwood.rp.flaunt.facade.UserFacade;

@Named
public class UserFacadeImpl implements UserFacade, GenericServiceLocator{
	
	@Inject
	private UserRegistrationService userRegistrationService;	


	public ACPResponse getUserRegistrationDetails(ACPRequest acpRequest)  throws ServiceException{
		return userRegistrationService.getUserRegistrationDetails(acpRequest);
	}
		

	public UserRegistrationService getUserRegistrationService() {
		return userRegistrationService;
	}

	public void setUserRegistrationService(UserRegistrationService userRegistrationService){
		this.userRegistrationService = userRegistrationService;
	}
	

	public ACPResponse updateUserPassword(ACPRequest acpRequest) throws ServiceException {
		return userRegistrationService.updateUserPassword(acpRequest);
	}


	public ACPResponse getUserPassword(ACPRequest acpRequest) throws ServiceException {
		return userRegistrationService.getUserPassword(acpRequest);
	}


	public ACPResponse forgotPassword(ACPRequest acpRequest) throws ServiceException {
		return userRegistrationService.forgotPassword(acpRequest);
	}

}
