package com.redwood.rp.genericquery.service;

import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.vo.generic.ACPRequest;
import com.redwood.rp.core.vo.generic.ACPResponse;


public interface ACPQueryService{
	
	public ACPResponse genericQueryCall(ACPRequest acpRequest) throws ServiceException;

}
