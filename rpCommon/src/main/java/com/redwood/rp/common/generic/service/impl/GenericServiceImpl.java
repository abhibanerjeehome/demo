package com.redwood.rp.common.generic.service.impl;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.redwood.rp.core.vo.generic.ACPRequest;
import com.redwood.rp.core.vo.generic.ACPResponse;
import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.genericquery.service.ACPQueryService;
import com.redwood.rp.common.generic.service.GenericService;
import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.exception.ValidationException;
import com.redwood.rp.core.util.MessageFormattor;

@Named
public class GenericServiceImpl implements GenericService {

	private static Logger logger = LoggerFactory.getLogger(GenericServiceImpl.class.getName());
	
	@Autowired
	private ACPQueryService acpQueryService;
	
	@Override
	public ACPResponse getGenericService(ACPRequest acpRequest)
			throws ServiceException {
		ACPResponse acpResponse = new ACPResponse(acpRequest);
		String errorDescription = null;
		//logger.error("getting called PropertyServiceImpl");
		try{
			try {
				//Validates the input acp request
				ACPRequest.validateACPRequest(acpRequest);
			} catch (ValidationException e) {
				//System.out.println(e);
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,e.getErrorVO());
			}
			
			try{
				//Converts the generic payload object to specific request object.
				 acpResponse=acpQueryService.genericQueryCall(acpRequest);
									 

				 
		/*		 if(acpResponseMap!=null && acpResponseMap.size() > 0){ 
					 for(String fieldName : acpResponseMap.keySet()){
					        ConvertUtils.register(new TimeStampConverter(), java.sql.Timestamp.class);
					        BeanUtils.setProperty(geoBannerBO, fieldName, acpResponseMap.get(fieldName));
					 }
	  
				 }*/
						 
				 

				
			}catch(Exception e){
				errorDescription = "Cannot convert a object from type "+acpRequest.getPayload().getClass().getName()+" to String. ";
				logger.error(MessageFormattor.errorFormat(GenericServiceImpl.class.getName(),
		                "getGenericService", ExceptionType.EXCEPTION_SERVICE, errorDescription), e);
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,
						new ErrorVO(ErrorDescription.ERR_CD_COMMON_OBJECT_CONVERSION,ErrorDescription.ERR_MSG_COMMON_OBJECT_CONVERSION, errorDescription));
			}
			
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			errorDescription = "Internal error: " + e.getMessage();
			logger.error(MessageFormattor.errorFormat(GenericServiceImpl.class.getName(),
	                "getGenericService", ExceptionType.EXCEPTION_SERVICE, errorDescription), e);
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,
					new ErrorVO(ErrorDescription.ERR_CD_COMMON_INTERNAL,ErrorDescription.ERR_MSG_COMMON_INTERNAL, errorDescription));
		}
		return acpResponse;
	}

	public ACPQueryService getAcpQueryService() {
		return acpQueryService;
	}


	public void setAcpQueryService(ACPQueryService acpQueryService) {
		this.acpQueryService = acpQueryService;
	}
}
