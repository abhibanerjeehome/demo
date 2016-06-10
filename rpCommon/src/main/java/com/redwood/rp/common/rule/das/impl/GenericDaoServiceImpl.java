package com.redwood.rp.common.rule.das.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redwood.rp.core.vo.generic.ACPRequest;
import com.redwood.rp.core.vo.generic.ACPResponse;
import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.genericquery.service.ACPQueryService;
import com.redwood.rp.common.rule.das.GenericDaoService;
import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.exception.ValidationException;
import com.redwood.rp.core.util.MessageFormattor;

@Named
public class GenericDaoServiceImpl implements GenericDaoService{
	
	
	private static Logger logger = LoggerFactory.getLogger(GenericDaoServiceImpl.class.getName());
	
	@Inject
	private ACPQueryService acpQueryService;

	@Override
	public ACPResponse fetchDetailsAsMap(ACPRequest acpRequest)
			throws ServiceException, DaoException {
		
		ACPResponse acpResponse = new ACPResponse(acpRequest);
		String errorDescription = null;
		try{
			try {
				//Validates the input acp request
				ACPRequest.validateACPRequest(acpRequest);
			} catch (ValidationException e) {
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,e.getErrorVO());
			}
			acpResponse=acpQueryService.genericQueryCall(acpRequest);
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> acpResponseList=(ArrayList<Map<String, Object>>)acpResponse.getPayload();
			@SuppressWarnings("unused")
			Object bo = null;
			@SuppressWarnings({ "unused", "rawtypes" })
			List boList = new ArrayList(); 
			if(acpResponseList != null && acpResponseList.size() > 0){
//				for(Map<String, Object> recordMap : acpResponseList){
//					bo = Class.forName(boClazz.toString());
//					BeanUtils.copyProperties(recordMap, ((T)bo) );
//					boList.add(bo);
//				}
				
				//acpResponse.setPayload(boList);
				acpResponse.setPayload(acpResponseList);
			}
		return acpResponse;
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			errorDescription = "Internal error: " + e.getMessage();
			logger.error(MessageFormattor.errorFormat(GenericDaoServiceImpl.class.getName(),
	                "error while executing query", ExceptionType.EXCEPTION_SERVICE, errorDescription), e);
			throw new DaoException(ExceptionType.EXCEPTION_SERVICE,
					new ErrorVO(ErrorDescription.ERR_CD_COMMON_INTERNAL,ErrorDescription.ERR_MSG_COMMON_INTERNAL, errorDescription));
		}
	}
}
