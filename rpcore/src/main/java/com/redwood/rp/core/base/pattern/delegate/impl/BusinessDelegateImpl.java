package com.redwood.rp.core.base.pattern.delegate.impl;
/**
 *=====================================================================
 * Abstract Business Delegate Implementation
 *
 * a base business delegate patterned implementation for delivering 
 * service calls from application messaging layer to data accessing 
 * layer
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.redwood.rp.core.base.pattern.delegate.BusinessDelegate;
import com.redwood.rp.core.base.pattern.facade.GenericServiceLocator;
import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.util.MessageFormattor;
import com.redwood.rp.core.vo.generic.ACPRequest;
import com.redwood.rp.core.vo.generic.ACPResponse;
import com.redwood.rp.core.vo.json.ErrorVO;

@Named
public class BusinessDelegateImpl implements BusinessDelegate {
	private static Logger logger = LoggerFactory.getLogger(BusinessDelegateImpl.class); 

	@Autowired(required=false)
	private GenericServiceLocator facadeService = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.auction.ams.jms.delegate.BusinessDelegate#execute(java.lang.String)
	 */
	public ACPResponse execute(ACPRequest requestVO) throws ServiceException {
		ACPResponse response = null;
		String errorMsg = null;
		String serviceName = null;		
		Method method = null;
		ErrorVO errorVO = null;	
		try {
			if (requestVO != null) {
				if (requestVO.getPayload() == null) {
					logger.info("request payload is unavailable for service " + requestVO.getServiceName());
				}
				Class<?> facadeClass = facadeService.getClass();
				Class<?>[] params = { ACPRequest.class};
				//Gets service name
				serviceName = requestVO.getServiceName();
				if (StringUtils.isNotBlank(serviceName)) {
					method = facadeClass.getMethod(serviceName, params);
					response = (ACPResponse)method.invoke(facadeService, requestVO);
				} else {
					errorMsg = "service name mandatory in business delegate execute method.";
					errorVO = new ErrorVO(ErrorDescription.ERR_CD_INVALID_SERVICE, ErrorDescription.ERR_MSG_INVALID_SERVICE, errorMsg);					
					throw new ServiceException(ExceptionType.EXCEPTION_PARAMETER, errorVO);	
				}
			} else {
				logger.error("request object is null in the business delegate execute method");
			}
		} catch (NoSuchMethodException ex) {
			errorMsg = "Original exception is NoSuchMethodException. Error info is :"+ex.getMessage();
			logger.error(MessageFormattor.errorFormat(this.getClass().getName(), "execute", ExceptionType.EXCEPTION_BUSINESS_DELEGATION, errorMsg), ex);
			errorVO = new ErrorVO(ErrorDescription.ERR_CD_NO_OPERATION, ErrorDescription.ERR_MSG_NO_OPERATION, errorMsg);
			throw new ServiceException(ExceptionType.EXCEPTION_DELEGATE, errorVO);
		} catch (IllegalAccessException ex) {
			errorMsg = "Original exception is IllegalAccessException. Error info is :"+ex.getMessage();
			logger.error(MessageFormattor.errorFormat(this.getClass().getName(), "execute", ExceptionType.EXCEPTION_BUSINESS_DELEGATION, errorMsg), ex);			
			errorVO = new ErrorVO(ErrorDescription.ERR_CD_ILLEGAL_ACCESS, ErrorDescription.ERR_MSG__ILLEGAL_ACCESS, errorMsg);
			throw new ServiceException(ExceptionType.EXCEPTION_DELEGATE, errorVO);
		} catch (InvocationTargetException ex) {
			Throwable th = ex.getCause();			
			if (th instanceof ServiceException)
				throw (ServiceException)th;
			else {
				errorMsg = "Original exception is "+th.getClass().getName()+". The error info is: "+th.getMessage();
				logger.error(MessageFormattor.errorFormat(this.getClass().getName(), "execute", ExceptionType.EXCEPTION_BUSINESS_DELEGATION, errorMsg), ex);				
				errorVO = new ErrorVO(ErrorDescription.ERR_CD_SERVICE_INVOCATION, ErrorDescription.ERR_MSG_SERVICE_INVOCATION, errorMsg);				
				throw new ServiceException(ExceptionType.EXCEPTION_DELEGATE, errorVO);
			}
		} catch (ServiceException ex) {
			throw ex;
		} catch (Exception ex) {
			errorMsg = "Exception happened in BusinessDelegateImpl class. The error info is: "+ex.getMessage();
			logger.error(MessageFormattor.errorFormat(this.getClass().getName(), "execute", ExceptionType.EXCEPTION_BUSINESS_DELEGATION, errorMsg), ex);			
			errorVO = new ErrorVO(ErrorDescription.ERR_CD_SERVICE_INVOCATION, ErrorDescription.ERR_MSG_SERVICE_INVOCATION, errorMsg);
			throw new ServiceException(ExceptionType.EXCEPTION_DELEGATE, errorVO);
		}		
		return response;
	}

	public GenericServiceLocator getFacadeService() {
		return facadeService;
	}

	public void setFacadeService(GenericServiceLocator facadeService) {
		this.facadeService = facadeService;
	}	 
}