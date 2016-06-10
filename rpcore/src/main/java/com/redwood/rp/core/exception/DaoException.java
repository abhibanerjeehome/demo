package com.redwood.rp.core.exception;
/**
 *=====================================================================
 * ACP DAO Layer Exception
 *
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 

import com.redwood.rp.core.vo.json.ErrorVO;

public class DaoException extends BaseException {
	private static final long serialVersionUID = 5404733870677069564L;

	/**
	 * Constructor
	 * 
	 * @param exceptionType
	 * @param errvo
	 */
	public DaoException(String exceptionType, ErrorVO errvo) {
		super(exceptionType, errvo);
	}
	
	public DaoException() {
	}

}
