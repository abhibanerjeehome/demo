package com.redwood.rp.core.exception;
/**
 *=====================================================================
 * ACP Base Exception Test Suite 
 * 
 *
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.redwood.rp.core.constant.CoreConst;
import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.vo.json.ErrorVO;

public class BaseExceptionTest {
	
//	private BaseException baseException;
//
//	@Test
//	public void testGetErrorMessageXML() {
//		baseException = new BaseException();
//		String errorXml = baseException.getErrorMessageXML();
//		assertNotNull(errorXml);
//		assertFalse("".equals(errorXml));
//	}
//
//	@Test
//	public void testGetErrorMessageXMLWithErrorVO() {
//		baseException = new BaseException();
//		String errorXml = baseException.getErrorMessageXML(new ErrorVO());
//		assertNotNull(errorXml);
//		assertFalse("".equals(errorXml));
//	}
//
//	@Test
//	public void testGetFormattedExceptionMessage() {
//		baseException = new BaseException();
//		String errorMsg = baseException.getFormattedExceptionMessage();
//		assertFalse("".equals(errorMsg));
//		assertEquals("Error Code: AE-ERROR-DEFAULT; Correlation Id: ; User ID: ; User Name: ; Error : There are no error message.", errorMsg);
//	}
//
//	@Test
//	public void testGetFormattedExceptionMessageWithType() {
//		ErrorVO errorVO = new ErrorVO();
//		errorVO.setErrCd(ErrorDescription.ERR_CD_FAILED_BID);
//		errorVO.setErrMsg(ErrorDescription.ERR_MSG_FAILED_BID);
//		
//		// using overloaded constructor
//		baseException = new BaseException(ExceptionType.EXCEPTION_SERVICE, errorVO);
//		// do assertions on getters
//		assertEquals(ExceptionType.EXCEPTION_SERVICE, baseException.getExceptionType());
//		assertEquals(errorVO, baseException.getErrorVO());
//		
//		// call actual method
//		String errorMsg = baseException.getFormattedExceptionMessageWithType();
//		assertFalse("".equals(errorMsg));
//		assertEquals("Exception Type: ServiceException; Error Code: "+ 
//				ErrorDescription.ERR_CD_FAILED_BID +"; Correlation Id: null; User ID: null; User Name: null; Error : "+ 
//				ErrorDescription.ERR_MSG_FAILED_BID +".", errorMsg);
//	}
//	
//	@Test
//	public void testGetFormattedExceptionMessageWithTypeUsingSetAndGet() {
//		ErrorVO errorVO = new ErrorVO();
//		errorVO.setErrCd(ErrorDescription.ERR_CD_FAILED_BID);
//		errorVO.setErrMsg(ErrorDescription.ERR_MSG_FAILED_BID);
//
//		// using default constructor and setter methods
//		baseException = new BaseException();
//		baseException.setExceptionType(ExceptionType.EXCEPTION_SERVICE);
//		baseException.setErrorVO(errorVO);
//		
//		// do assertions on getters
//		assertEquals(ExceptionType.EXCEPTION_SERVICE, baseException.getExceptionType());
//		assertEquals(errorVO, baseException.getErrorVO());
//		
//		// call actual method
//		String errorMsg = baseException.getFormattedExceptionMessageWithType();
//		assertFalse("".equals(errorMsg));
//		assertEquals("Exception Type: ServiceException; Error Code: "+ 
//				ErrorDescription.ERR_CD_FAILED_BID +"; Correlation Id: null; User ID: null; User Name: null; Error : "+ 
//				ErrorDescription.ERR_MSG_FAILED_BID +".", errorMsg);
//	}
//
//	@Test
//	public void testLogFormattedExceptionMessage() {
//		baseException = new BaseException();
//		
//		baseException.logFormattedExceptionMessage(ApplConst.LOGGING_LEVEL_FATAL, "testClass", "testMethod");
//		baseException.logFormattedExceptionMessage(ApplConst.LOGGING_LEVEL_ERROR, "testClass", "testMethod");
//		baseException.logFormattedExceptionMessage(ApplConst.LOGGING_LEVEL_WARN, "testClass", "testMethod");
//		baseException.logFormattedExceptionMessage(ApplConst.LOGGING_LEVEL_INFO, "testClass", "testMethod");
//		baseException.logFormattedExceptionMessage(ApplConst.LOGGING_LEVEL_DEBUG, "testClass", "testMethod");
//		baseException.logFormattedExceptionMessage('K', null, null);
//	}

}
