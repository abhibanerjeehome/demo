package com.redwood.rp.flaunt.das.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import com.redwood.rp.core.annotation.Loggable;
import com.redwood.rp.core.base.message.publish.MessageProducer;
import com.redwood.rp.core.vo.generic.ACPRequest;
import com.redwood.rp.core.vo.generic.ACPResponse;
import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.genericquery.constant.QueryType;
import com.redwood.rp.genericquery.service.ACPQueryService;
import com.redwood.rp.genericquery.service.impl.ACPQueryServiceImpl;
import com.redwood.rp.genericquery.vo.generic.GenericQueryRequest;
import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.exception.UtilityException;
import com.redwood.rp.core.exception.ValidationException;
import com.redwood.rp.core.util.BooleanUtil;
import com.redwood.rp.core.util.CryptoUtil;
import com.redwood.rp.core.util.MessageFormattor;
import com.redwood.rp.core.util.QueryDefinitionBean;
import com.redwood.rp.flaunt.bo.UserAuthBO;
import com.redwood.rp.flaunt.bo.UserRegistrationDetailBO;
import com.redwood.rp.flaunt.constant.UserServiceConstant;
import com.redwood.rp.flaunt.constant.UserServiceErrorConstant;
import com.redwood.rp.flaunt.das.dao.ACPUserQueryDAO;
import com.redwood.rp.flaunt.das.dao.AppAuthorizeDAO;
import com.redwood.rp.flaunt.das.dao.ForeclosureQueryDAO;
import com.redwood.rp.flaunt.das.dto.StateVO;
import com.redwood.rp.flaunt.das.service.UserRegistrationService;
import com.redwood.rp.flaunt.vo.json.request.AppAuthorizeVO;
import com.redwood.rp.flaunt.vo.json.response.UpdateUserResponseVO;

@Named
public class UserRegistrationServiceImpl implements UserRegistrationService {

	private static Logger logger = LoggerFactory
			.getLogger(UserRegistrationServiceImpl.class.getName());


	@Autowired
	private ACPUserQueryDAO acpUserQueryDao;


	private ForeclosureQueryDAO foreclosureQueryDao;
	
	@Autowired
	private AppAuthorizeDAO appAuthorizeDao;
	

	@Inject
	private CryptoUtil cryptoUtil;

	@Inject
	private QueryDefinitionBean namedQueryBean;

	@Inject
	private MessageProducer messageProducer;

	@Value("${auction.exchange}")
	private String amqExchange;

	@Value("${auction.queue.routing.key}")
	private String amqRoutingKey;

	@Value("${registration.email.url.host}")
	private String registrationUrlHost;

	@Loggable
	@Transactional("jdbc_userInformation")
	public ACPResponse getUserRegistrationDetails(ACPRequest acpRequest) throws ServiceException {

		String errorDescription;
		ACPResponse acpResponse;

		try {
			// Converts the generic payload object to specific request object.
			acpResponse = userGenericQueryCall(acpRequest, UserServiceConstant.USER_SCHEMA);

			@SuppressWarnings("unchecked")
			List<Map<String, Object>> acpResponseList = (ArrayList<Map<String, Object>>) acpResponse.getPayload();
			Map<String, Object> acpResponseMap = acpResponseList.get(0);

			UserRegistrationDetailBO userRegistrationDetailBO = new UserRegistrationDetailBO();
			//StateVO stateVO = new StateVO();
			//String stateId = acpResponseMap.get("State").toString();

			// TODO populate registrationBO
			userRegistrationDetailBO.setRole(acpResponseMap.get("role").toString());
			userRegistrationDetailBO.setRoleId((Integer)acpResponseMap.get("role_id"));
			

			acpResponse.setPayload(userRegistrationDetailBO);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			errorDescription = "Internal error: " + e.getMessage();
			logger.error(MessageFormattor.errorFormat(ACPQueryServiceImpl.class.getName(), "getUserRegistrationDetails",
					ExceptionType.EXCEPTION_SERVICE, errorDescription));
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,
							ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
		}
		return acpResponse;
	}

	@Loggable
	@Transactional("jdbc_mlh")
	private StateVO getStateName(String stateId) throws ServiceException {
		String queryStr = null;
		StateVO stateVO = null;

		try {
			// TODO - Check Cache for StateVO Map first before running query
			queryStr = namedQueryBean.getQueryByName("GET_STATE_NAME");
		} catch (UtilityException e) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,
					e.getErrorVO());
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("STATEID", stateId);

		List<Map<String, Object>> resultMapList = null;
		try {
			resultMapList = executeQuery(queryStr, paramMap, UserServiceConstant.FORECLOSURE_SCHEMA);

		} catch (ServiceException e) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	e.getErrorVO());
		}
		if (resultMapList == null || resultMapList.size() == 0) {
			return null;
		} else {
			logger.debug("After query execution:::"	+ resultMapList.get(0).toString());
			stateVO = new StateVO();
			stateVO.setStateName(resultMapList.get(0).get("state").toString());
			stateVO.setStateId((Integer) resultMapList.get(0).get("stateID"));
			stateVO.setStateCode(resultMapList.get(0).get("abbrevation").toString());
			stateVO.setStatus((Integer) resultMapList.get(0).get("status"));
			stateVO.setCountryId((Integer) resultMapList.get(0).get("country"));
			stateVO.setRegion((Integer) resultMapList.get(0).get("region"));
			stateVO.setDisplayOrder((Integer) resultMapList.get(0).get("displayOrder"));
			stateVO.setPercent((Integer) resultMapList.get(0).get("percent"));
			stateVO.setMaxPercent((Integer) resultMapList.get(0).get("maxPercent"));
		}
		return stateVO;
	}

	@Loggable
	private ACPResponse userGenericQueryCall(ACPRequest acpRequest,
			String schema) throws ServiceException {
		String errorDescription = "";
		ACPResponse acpResponse = new ACPResponse(acpRequest);
		try {
			try {
				// Validates the input acp request
				ACPRequest.validateACPRequest(acpRequest);
			} catch (ValidationException e) {
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,
						e.getErrorVO());
			}
			GenericQueryRequest dataCallRequest = null;
			try {
				// Converts the generic payload object to specific request
				// object.
				dataCallRequest = (GenericQueryRequest) acpRequest.getPayload();
			} catch (Exception e) {
				errorDescription = "Cannot convert a object from type "	+ acpRequest.getPayload().getClass().getName()	+ " to SimpleDataCallRequest. ";
				logger.error(MessageFormattor.errorFormat(
						ACPQueryServiceImpl.class.getName(), "executeQuery",
						ExceptionType.EXCEPTION_SERVICE, errorDescription));
				throw new ServiceException(
						ExceptionType.EXCEPTION_SERVICE, new ErrorVO(ErrorDescription.ERR_CD_COMMON_OBJECT_CONVERSION,
								ErrorDescription.ERR_MSG_COMMON_OBJECT_CONVERSION, errorDescription));
			}
			// Validates the date call request
			try {
				GenericQueryRequest.validateGenericQueryRequest(dataCallRequest);
			} catch (ValidationException e) {
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	e.getErrorVO());
			}

			// Gets the query string
			String queryStr = "";
			try {
				queryStr = namedQueryBean.getQueryByName(dataCallRequest.getQueryName());
			} catch (UtilityException e) {
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	e.getErrorVO());
			}

			if (dataCallRequest.isSP()) {

			} else {
				logger.debug("Query Str : " + queryStr);
				if (QueryType.SELECT.equals(dataCallRequest.getQueryType())) {
					List<Map<String, Object>> resultMapList = executeQuery(
							queryStr, dataCallRequest.getParameterMap(), schema);
					logger.debug("resultMapList : " + resultMapList);
					if (resultMapList != null && resultMapList.size() > 0) {
						for (Map<String, Object> resultCol : resultMapList) {
							if (resultCol != null && resultCol.size() > 0) {
								for (String key : resultCol.keySet()) {
									logger.debug(key + " " + resultCol.get(key));
								}
							}
						}
					}
					acpResponse.setPayload(resultMapList);
				} else {
					int affectedRows = executeUpdate(queryStr, dataCallRequest.getParameterMap(), schema);
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
			logger.error(MessageFormattor.errorFormat(ACPQueryServiceImpl.class.getName(), "simpleDataCallService", ExceptionType.EXCEPTION_SERVICE,
					errorDescription));
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(ErrorDescription.ERR_CD_COMMON_INTERNAL,
							ErrorDescription.ERR_MSG_COMMON_INTERNAL, errorDescription));
		}
	}





	@Transactional("jdbc_userInformation")
	public ACPResponse updateUserPassword(ACPRequest acpRequest)
			throws ServiceException {
		ACPResponse acpResponse = new ACPResponse(acpRequest);
		String errorDescription = null;
		try {
			try {
				// Validates the input acp request
				ACPRequest.validateACPRequest(acpRequest);
			} catch (ValidationException e) {
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	e.getErrorVO());
			}

			try {
				// Converts the generic payload object to specific request
				// object.
				acpResponse = userGenericQueryCall(acpRequest, UserServiceConstant.USER_SCHEMA);

				int affectedRows = acpResponse.getAffectedRows();

				// TODO populate response object
				logger.debug("afterInsert user affected rows: = "  + affectedRows);
			} catch (Exception e) {
				e.printStackTrace();
				errorDescription = "Cannot convert a object from type " + acpRequest.getPayload().getClass().getName()	+ " to String. ";
				logger.error(MessageFormattor.errorFormat(ACPQueryServiceImpl.class.getName(),
						"updateUserPassword", ExceptionType.EXCEPTION_SERVICE, errorDescription));
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(ErrorDescription.ERR_CD_COMMON_OBJECT_CONVERSION, ErrorDescription.ERR_MSG_COMMON_OBJECT_CONVERSION,
								errorDescription));
			}
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			errorDescription = "Internal error: " + e.getMessage();
			logger.error(MessageFormattor.errorFormat(ACPQueryServiceImpl.class.getName(), "updateUserPassword",
					ExceptionType.EXCEPTION_SERVICE, errorDescription));
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(ErrorDescription.ERR_CD_COMMON_INTERNAL,
							ErrorDescription.ERR_MSG_COMMON_INTERNAL, errorDescription));
		}
		return acpResponse;
	}
	
	
	@Loggable
	public ACPResponse forgotPassword(ACPRequest acpRequest) throws ServiceException {
		ACPResponse acpResponse = new ACPResponse(acpRequest);

		String errorDescription = null;

		try {
			try {
				// Validates the input acp request
				ACPRequest.validateACPRequest(acpRequest);
			} catch (ValidationException e) {
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, e.getErrorVO());
			}

			try {
				// Converts the generic payload object to specific request
				// object.
				acpResponse = userGenericQueryCall(acpRequest, UserServiceConstant.USER_SCHEMA);

			} catch (Exception e) {
				e.printStackTrace();
				errorDescription = "Cannot convert a object from type "	+ acpRequest.getPayload().getClass().getName() + " to String. ";
				logger.error(MessageFormattor.errorFormat(ACPQueryServiceImpl.class.getName(),
						"updateUserPassword", ExceptionType.EXCEPTION_SERVICE, errorDescription));
				throw new ServiceException(
						ExceptionType.EXCEPTION_SERVICE, new ErrorVO(ErrorDescription.ERR_CD_COMMON_OBJECT_CONVERSION,
								ErrorDescription.ERR_MSG_COMMON_OBJECT_CONVERSION, errorDescription));
			}
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			errorDescription = "Internal error: " + e.getMessage();
			logger.error(MessageFormattor.errorFormat(ACPQueryServiceImpl.class.getName(), "forgotPassword",
					ExceptionType.EXCEPTION_SERVICE, errorDescription));
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, new ErrorVO(ErrorDescription.ERR_CD_COMMON_INTERNAL,
							ErrorDescription.ERR_MSG_COMMON_INTERNAL, errorDescription));
		}
		return acpResponse;
	}
	

	@Loggable
	@Transactional("jdbc_userInformation")
	public ACPResponse getUserPassword(ACPRequest acpRequest) throws ServiceException {
		String errorDescription;
		ACPResponse acpResponse;

		try {
			// Converts the generic payload object to specific request object.
			acpResponse = userGenericQueryCall(acpRequest, UserServiceConstant.USER_SCHEMA);

			@SuppressWarnings("unchecked")
			List<Map<String, Object>> acpResponseList = (ArrayList<Map<String, Object>>) acpResponse.getPayload();
			Map<String, Object> acpResponseMap = acpResponseList.get(0);

			UserRegistrationDetailBO userRegistrationDetailBO = new UserRegistrationDetailBO();

			// populate registrationBO
			if (acpResponseMap.get("password") != null)
				//userRegistrationDetailBO.setPassword(acpResponseMap.get("password").toString());

			acpResponse.setPayload(userRegistrationDetailBO);

		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			errorDescription = "Internal error: " + e.getMessage();
			logger.error(MessageFormattor.errorFormat(ACPQueryServiceImpl.class.getName(), "getUserPassword",
					ExceptionType.EXCEPTION_SERVICE, errorDescription));
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(ErrorDescription.ERR_CD_COMMON_INTERNAL,
							ErrorDescription.ERR_MSG_COMMON_INTERNAL, errorDescription));
		}

		return acpResponse;
	}

	@Loggable
	@Transactional("jdbc_mlh")
	public List<StateVO> getUserStates(Integer countryId) throws ServiceException {
		String queryStr = null;
		StateVO stateVO = null;
		List<StateVO> statesList = new ArrayList<StateVO>();

		try {

			// TODO - Check Cache for StateVO Map first before running query

			// logger.debug("get query string:::" );

			queryStr = namedQueryBean.getQueryByName("GET_USER_STATES");
		} catch (UtilityException e) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	e.getErrorVO());
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("COUNTRYID", countryId);

		List<Map<String, Object>> resultMapList = null;
		try {
			resultMapList = executeQuery(queryStr, paramMap, UserServiceConstant.FORECLOSURE_SCHEMA);

			if (resultMapList == null || resultMapList.size() == 0) {
				return null;
			} else {
				// logger.debug("After query execution:::" +
				// resultMapList.get(0).toString());

				// for (com.redwood.rp.documents.das.dto.FileDetail fileDetail
				// : filesForProperty)
				for (Map<String, Object> state : resultMapList) {
					stateVO = new StateVO();
					stateVO.setStateName(state.get("state").toString());
					stateVO.setStateId((Integer) state.get("stateID"));
					stateVO.setStateCode(state.get("abbrevation").toString());
					// stateVO.setStatus((Integer)state.get("status"));
					stateVO.setCountryId((Integer) state.get("country"));
					stateVO.setRegion((Integer) state.get("region"));
					stateVO.setDisplayOrder((Integer) state.get("displayOrder"));
					stateVO.setPercent((Integer) state.get("percent"));
					stateVO.setMaxPercent((Integer) state.get("maxPercent"));

					statesList.add(stateVO);
				}
			}
		} catch (ServiceException e) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, e.getErrorVO());
		}
		return statesList;
	}


	@Transactional("jdbc_mlh")
	public List<String> getCitiesByStateCode(String stateCode)
			throws ServiceException {
		String queryStr = null;

		try {

			// TODO - Check Cache for StateVO Map first before running query

			// logger.debug("get query string:::" );

			queryStr = namedQueryBean.getQueryByName("GET_CITIES_BY_STATE_CODE");
		} catch (UtilityException e) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	e.getErrorVO());
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("STATE_CODE", stateCode);

		List<Map<String, Object>> resultMapList = null;
		List<String> citiesList = new ArrayList<String>();

		try {
			resultMapList = executeQuery(queryStr, paramMap, UserServiceConstant.FORECLOSURE_SCHEMA);

			if (resultMapList == null || resultMapList.size() == 0) {
				return null;
			} else {
				// logger.debug("After query execution:::" +
				// resultMapList.get(0).toString());

				// for (com.redwood.rp.documents.das.dto.FileDetail fileDetail
				// : filesForProperty)
				for (Map<String, Object> value : resultMapList) {
					citiesList.add(value.get("City").toString());
				}
			}
		} catch (ServiceException e) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, e.getErrorVO());
		}
		return citiesList;
	}



	/**
	 * @param acpRequest
	 * @return
	 * @throws ServiceException
	 */
	private List<Map<String, Object>> executeQuery(String queryStr,	Map<String, Object> parameterMap, String schema) throws ServiceException {
		List<Map<String, Object>> resultMapList = null;
		try {
			if (schema.equals(UserServiceConstant.USER_SCHEMA)) {
				resultMapList = acpUserQueryDao.executeJdbcQuery(queryStr, parameterMap);
			} else if (schema.equals(UserServiceConstant.FORECLOSURE_SCHEMA)) {
				resultMapList = foreclosureQueryDao.executeJdbcQuery(queryStr, parameterMap);
			}
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
	private int executeUpdate(String queryStr, Map<String, Object> parameterMap, String schema)	throws ServiceException {
		int affectedRows = 0;
		try {
			if (schema.equals(UserServiceConstant.USER_SCHEMA)) {
				affectedRows = acpUserQueryDao.executeJdbcUpdate(queryStr, parameterMap);
			} else if (schema.equals(UserServiceConstant.FORECLOSURE_SCHEMA)) {
				affectedRows = foreclosureQueryDao.executeJdbcUpdate(queryStr, parameterMap);
			}
		} catch (DaoException e) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, e.getErrorVO());
		}

		return affectedRows;
	}



	public UpdateUserResponseVO addUserPermission(AppAuthorizeVO appAuthorizeVO) throws ServiceException {
		UpdateUserResponseVO response = new UpdateUserResponseVO();
		try {
			int rowsUpdated = appAuthorizeDao.insertUserPermission(appAuthorizeVO);
			if (rowsUpdated == 1) {
				response.setStrStatus(UserServiceConstant.SUCCESS);
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

}
