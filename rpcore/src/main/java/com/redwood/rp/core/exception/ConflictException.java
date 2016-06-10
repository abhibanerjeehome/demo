package com.redwood.rp.core.exception;

import com.redwood.rp.core.vo.json.ErrorVO;

/**
 *=====================================================================
 * ACP ConflictException Exception
 *
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 09/17/2014   create
 *
 */	
public class ConflictException  extends BaseException  {

	private static final long serialVersionUID = 7895723562145223930L;
	
	/**
	 * @param exceptionType
	 * @param errvo
	 */
	public ConflictException(String exceptionType, ErrorVO errvo) {
		super(exceptionType, errvo);
	}

}
