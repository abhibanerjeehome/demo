package com.redwood.rp.core.base.pattern.delegate;
/**
 *=====================================================================
 * Abstract Business Delegate Interface
 *
 * define a base business delegate interface for messaging based 
 * services
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 

import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.vo.generic.ACPRequest;
import com.redwood.rp.core.vo.generic.ACPResponse;

public interface BusinessDelegate {

	/**
	 * @param requestVo
	 * @return
	 * @throws ServiceException
	 */
	public ACPResponse execute(ACPRequest requestVO) throws ServiceException;
	
}
