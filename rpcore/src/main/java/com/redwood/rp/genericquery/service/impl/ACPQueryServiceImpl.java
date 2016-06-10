package com.redwood.rp.genericquery.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.exception.UtilityException;
import com.redwood.rp.core.exception.ValidationException;
import com.redwood.rp.core.util.MessageFormattor;
import com.redwood.rp.core.util.QueryDefinitionBean;
import com.redwood.rp.core.vo.generic.ACPRequest;
import com.redwood.rp.core.vo.generic.ACPResponse;
import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.genericquery.constant.QueryType;
import com.redwood.rp.genericquery.dao.ACPQueryDAO;
import com.redwood.rp.genericquery.service.ACPQueryService;
import com.redwood.rp.genericquery.vo.generic.GenericQueryRequest;

@Named
public class ACPQueryServiceImpl implements ACPQueryService {

	private static Logger logger = LoggerFactory.getLogger(ACPQueryServiceImpl.class.getName());

	/*
	 * changed from @Inject to @Autowired(required=false) because some services
	 * doesn't use database at all like analyticsProducer
	 */
	@Autowired(required = false)
	private ACPQueryDAO acpQueryDAO;

	/*
	 * changed from @Inject to @Autowired(required=false) because some services
	 * doesn't use database at all like analyticsProducer
	 */
	@Autowired(required = false)
	private QueryDefinitionBean namedQueryBean;

	/**
	 * @param acpRequest
	 * @return
	 * @throws ServiceException
	 */
	public ACPResponse genericQueryCall(ACPRequest acpRequest) throws ServiceException {
		String errorDescription = "";
		ACPResponse acpResponse = new ACPResponse(acpRequest);
		try {
			try {
				// Validates the input acp request
				ACPRequest.validateACPRequest(acpRequest);
			} catch (ValidationException e) {
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, e.getErrorVO());
			}
			GenericQueryRequest dataCallRequest = null;
			try {
				// Converts the generic payload object to specific request object.
				dataCallRequest = (GenericQueryRequest) acpRequest.getPayload();
			} catch (Exception e) {
				errorDescription = "Cannot convert a object from type " + acpRequest.getPayload().getClass().getName() + " to SimpleDataCallRequest. ";
				logger.error(MessageFormattor.errorFormat(ACPQueryServiceImpl.class.getName(), "executeQuery", ExceptionType.EXCEPTION_SERVICE, errorDescription), e);
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, new ErrorVO(ErrorDescription.ERR_CD_COMMON_OBJECT_CONVERSION,
						ErrorDescription.ERR_MSG_COMMON_OBJECT_CONVERSION, errorDescription));
			}
			// Validates the date call request
			try {
				GenericQueryRequest.validateGenericQueryRequest(dataCallRequest);
			} catch (ValidationException e) {
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, e.getErrorVO());
			}

			// Gets the query string
			String queryStr = "";
			try {
				queryStr = namedQueryBean.getQueryByName(dataCallRequest.getQueryName());
			} catch (UtilityException e) {
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, e.getErrorVO());
			}

			if (dataCallRequest.isSP()) {
				// SP pending
			} else {
				// if(dataCallRequest.getParameterMap() != null){
				// System.out.println("Query Details : ");
				// for(String key : dataCallRequest.getParameterMap().keySet()){
				// System.out.println(key +" "+
				// dataCallRequest.getParameterMap().get(key));
				// }
				// }
				// System.out.println("Query Str : "+queryStr);
				if (QueryType.SELECT.equals(dataCallRequest.getQueryType())) {
					List<Map<String, Object>> resultMapList = executeQuery(queryStr, dataCallRequest.getParameterMap());
					/*
					 * System.out.println("resultMapList : " +resultMapList);
					 * if(resultMapList != null && resultMapList.size()>0){
					 * for(Map<String, Object> resultCol : resultMapList){
					 * if(resultCol != null && resultCol.size() > 0){ for(String
					 * key : resultCol.keySet()){
					 * System.out.println(key+" "+resultCol.get(key)); } } } }
					 */
					acpResponse.setPayload(resultMapList);
				} else {
					int affectedRows = executeUpdate(queryStr, dataCallRequest.getParameterMap());
					acpResponse.setAffectedRows(affectedRows);
					acpResponse.setPayload(null);
				}
			}

			acpResponse.setSuccess(true);
			return acpResponse;
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			errorDescription = "Internal error: " + e.getMessage();
			logger.error(MessageFormattor.errorFormat(ACPQueryServiceImpl.class.getName(), "simpleDataCallService", ExceptionType.EXCEPTION_SERVICE, errorDescription), e);
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, new ErrorVO(ErrorDescription.ERR_CD_COMMON_INTERNAL, ErrorDescription.ERR_MSG_COMMON_INTERNAL,
					errorDescription));
		}
	}

	/**
	 * @param acpRequest
	 * @return
	 * @throws ServiceException
	 */
	private List<Map<String, Object>> executeQuery(String queryStr, Map<String, Object> parameterMap) throws ServiceException {
		List<Map<String, Object>> resultMapList = null;
		try {
			resultMapList = acpQueryDAO.executeJdbcQuery(queryStr, parameterMap);
		} catch (DaoException e) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, e.getErrorVO());
		}
		return resultMapList;
	}

	/**
	 * @param acpRequest
	 * @return
	 * @throws ServiceException
	 */
	private int executeUpdate(String queryStr, Map<String, Object> parameterMap) throws ServiceException {
		int affectedRows = 0;
		try {
			affectedRows = acpQueryDAO.executeJdbcUpdate(queryStr, parameterMap);
		} catch (DaoException e) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, e.getErrorVO());
		}

		return affectedRows;
	}

	public ACPQueryDAO getAcpQueryDAO() {
		return acpQueryDAO;
	}

	public void setAcpQueryDAO(ACPQueryDAO acpQueryDAO) {
		this.acpQueryDAO = acpQueryDAO;
	}

	public QueryDefinitionBean getNamedQueryBean() {
		return namedQueryBean;
	}

	public void setNamedQueryBean(QueryDefinitionBean namedQueryBean) {
		this.namedQueryBean = namedQueryBean;
	}

}
