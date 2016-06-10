package com.redwood.rp.common.generic.service;

import com.redwood.rp.core.vo.generic.ACPRequest;
import com.redwood.rp.core.vo.generic.ACPResponse;
import com.redwood.rp.core.exception.ServiceException;

public interface GenericService {

	public ACPResponse getGenericService(ACPRequest acpRequest)
			throws ServiceException;
	
}
