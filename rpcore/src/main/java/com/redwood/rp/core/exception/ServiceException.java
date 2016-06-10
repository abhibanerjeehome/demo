package com.redwood.rp.core.exception;
/**
 *=====================================================================
 * ACP Service Layer Exception
 *
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 


import com.redwood.rp.core.vo.json.ErrorVO;

public class ServiceException extends BaseException {
	private static final long serialVersionUID = -8190787260758587475L;
	

	/**
	 * Default constructor
	 */
//	public ServiceException() {
//		 super();
//	}
	
	/**
	 * Constructor
     *
	 * @param exceptionType
	 * @param errvo
	 */
	public ServiceException( String exceptionType, ErrorVO errvo) {
		super( exceptionType, errvo );
	}


}
