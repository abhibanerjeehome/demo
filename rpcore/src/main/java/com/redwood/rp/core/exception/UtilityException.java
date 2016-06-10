package com.redwood.rp.core.exception;
/**
 *=====================================================================
 * ACP Utility Exception
 *
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 

import com.redwood.rp.core.vo.json.ErrorVO;

public class UtilityException extends BaseException {
	private static final long serialVersionUID = -9161573885186673622L;

	/**
	 * Constructor
	 * 
	 * @param exceptionType
	 * @param errvo
	 */
	public UtilityException(String exceptionType, ErrorVO errvo) {
		super(exceptionType, errvo);
	}
}
