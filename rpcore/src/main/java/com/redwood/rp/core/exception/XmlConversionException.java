package com.redwood.rp.core.exception;
/**
 *=====================================================================
 * ACP XML Conversion Exception
 *
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 

import com.redwood.rp.core.vo.json.ErrorVO;

public class XmlConversionException extends BaseException {
	private static final long serialVersionUID = 9124757008178353203L;

	/**
	 * Constructor
	 * 
	 * @param exceptionType
	 * @param errvo
	 */
	public XmlConversionException(String exceptionType, ErrorVO errvo) {
		super(exceptionType, errvo);
	}

}
