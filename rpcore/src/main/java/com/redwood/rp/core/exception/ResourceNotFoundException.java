package com.redwood.rp.core.exception;

import com.redwood.rp.core.vo.json.ErrorVO;

/**
 *=====================================================================
 * ACP ResourceNotFoundException Exception
 *
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 09/17/2014   create
 *
 */	
public class ResourceNotFoundException  extends BaseException  {

	private static final long serialVersionUID = 7895723562145223930L;
	
	/**
	 * @param exceptionType
	 * @param errvo
	 */
	public ResourceNotFoundException(String exceptionType, ErrorVO errvo) {
		super(exceptionType, errvo);
	}

}
