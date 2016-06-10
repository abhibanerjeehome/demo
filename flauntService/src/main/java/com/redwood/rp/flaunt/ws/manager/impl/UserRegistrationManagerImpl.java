package com.redwood.rp.flaunt.ws.manager.impl;

import static com.redwood.rp.flaunt.constant.UserServiceConstant.REQUEST_EXCEPTION;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.redwood.rp.core.annotation.CacheAction;
import com.redwood.rp.core.annotation.CacheObject;
import com.redwood.rp.core.annotation.Loggable;
import com.redwood.rp.core.base.message.publish.MessageProducer;
import com.redwood.rp.core.base.pattern.delegate.BusinessDelegate;
import com.redwood.rp.core.vo.generic.ACPRequest;
import com.redwood.rp.core.vo.generic.ACPResponse;
import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.genericquery.service.impl.ACPQueryServiceImpl;
import com.redwood.rp.genericquery.vo.generic.GenericQueryRequest;
import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.constant.RequestType;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.util.BooleanUtil;
import com.redwood.rp.core.util.MessageFormattor;
import com.redwood.rp.flaunt.bo.UserRegistrationDetailBO;
import com.redwood.rp.flaunt.constant.ServiceNameMapping;
import com.redwood.rp.flaunt.constant.UserServiceConstant;
import com.redwood.rp.flaunt.constant.UserServiceErrorConstant;
import com.redwood.rp.flaunt.das.dto.StateVO;
import com.redwood.rp.flaunt.das.service.impl.ExternalServiceImpl;
import com.redwood.rp.flaunt.das.service.impl.UserRegistrationServiceImpl;
import com.redwood.rp.flaunt.util.CommonUtils;
import com.redwood.rp.flaunt.vo.json.request.AppAuthorizeVO;
import com.redwood.rp.flaunt.vo.json.request.UpdatePasswordRequestVO;
import com.redwood.rp.flaunt.vo.json.request.UpdateUserRequestVO;
import com.redwood.rp.flaunt.vo.json.request.UserDetailsRequestVO;
import com.redwood.rp.flaunt.vo.json.response.CitiesResponseVO;
import com.redwood.rp.flaunt.vo.json.response.RolesResponseVO;
import com.redwood.rp.flaunt.vo.json.response.RolesVO;
import com.redwood.rp.flaunt.vo.json.response.StatesResponseVO;
import com.redwood.rp.flaunt.vo.json.response.UpdateUserResponseVO;
import com.redwood.rp.flaunt.ws.manager.UserRegistrationManager;
import com.redwood.rp.flaunt.ws.manager.helper.UserRegistrationHelper;


@Named
public class UserRegistrationManagerImpl implements UserRegistrationManager {

	private static Logger logger = LoggerFactory
			.getLogger(UserRegistrationManagerImpl.class.getName());
	private UserRegistrationHelper userRegistrationHelper = new UserRegistrationHelper();

	@Inject
	private CommonUtils commonUtils;
	
	@Inject
	private  ExternalServiceImpl externalService;

	@Inject
	private BusinessDelegate businessDelegate;

	@Inject
	private UserRegistrationServiceImpl userService;

	@Inject
	private MessageProducer messageProducer;

	@Value("${auction.exchange}")
	private String amqExchange;

	@Value("${auction.queue.routing.key}")
	private String amqRoutingKey;

	@Value("${registration.email.url.host}")
	private String registrationUrlHost;

	@Loggable
	// @CacheObject(cacheAction=CacheAction.AddToCache)
	public RolesResponseVO getUserRegistrationDetails() throws ServiceException {
		RolesResponseVO userDetailResponseVO = new RolesResponseVO();

		UserRegistrationDetailBO userRegistrationBO = null;
		String userId = null;

		//userId = commonUtils.getUserId();
		
		GenericQueryRequest genericQueryRequest = null;
		genericQueryRequest = userRegistrationHelper.getGenericQueryReqForUser(userId);

		// Create acpRequest object with service name "genericQueryCall"
		genericQueryRequest.setQueryName("GET_USER_REGISTRATION_DETAILS");
		ACPRequest acpUserRegistrationRequest = new ACPRequest(ServiceNameMapping.GET_USER_REGISTRATION_DETAILS, RequestType.JSON, genericQueryRequest);

		// Call the real service and get the response
		ACPResponse acpUserRegistrationResponse = businessDelegate.execute(acpUserRegistrationRequest);
		RolesVO userDetailVO = null;
		userRegistrationBO = (UserRegistrationDetailBO) acpUserRegistrationResponse.getPayload();
		try {
			userDetailVO = userRegistrationHelper.getUserRegistrationDetailVO(userRegistrationBO);
			
		} catch (Exception e) {
			ErrorVO errorVO = new ErrorVO(ErrorDescription.SEVERITY_CODE_FATAL,
					ErrorDescription.ERR_MSG_OPERATION, REQUEST_EXCEPTION);
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
		}
		//userDetailResponseVO.setUserRegistrationDetailVO(userDetailVO);
		return userDetailResponseVO;
	}
	
	@Loggable
	public UpdateUserResponseVO updateFirstLogin() throws ServiceException {
		UpdateUserResponseVO updateUserResponseVO = new UpdateUserResponseVO();
		
		GenericQueryRequest genericQueryRequest = null;
		String userId = commonUtils.getUserId();
		String errorDescription;
		
		genericQueryRequest = userRegistrationHelper.getUpdateFirstLoginQuery(userId);

		// Create acpRequest object with service name "genericQueryCall"
		genericQueryRequest.setQueryName("UPDATE_FIRST_LOGIN");
		ACPRequest acpUpdateUserRequest = new ACPRequest(ServiceNameMapping.UPDATE_USER_REGISTRATION, RequestType.JSON,	genericQueryRequest);

		// Call the real service and get the response
		ACPResponse acpUpdateUserResponse = businessDelegate.execute(acpUpdateUserRequest);
		// if (acpUpdateUserResponse != null) {
		// updateUserResponseVO.setStrStatus((String)acpUpdateUserResponse.getPayload());
		// }

		if (acpUpdateUserResponse.getAffectedRows() > 0) {
			updateUserResponseVO.setStrStatus(UserServiceConstant.SUCCESS);
		}

		return updateUserResponseVO;
	}

	@Loggable
	public RolesResponseVO getUserById(UserDetailsRequestVO userDetailsRequestVO) throws ServiceException {

		UserRegistrationDetailBO userRegistrationBO = null;
		RolesResponseVO userDetailResponseVO = new RolesResponseVO();
		GenericQueryRequest genericQueryRequest = null;
		ACPRequest acpUserRegistrationRequest = null;
		if (userDetailsRequestVO != null) {
			if (userDetailsRequestVO.getUserId() != null) {

				genericQueryRequest = userRegistrationHelper.getGenericQueryReqForUser(userDetailsRequestVO.getUserId());

				// Create acpRequest object with service name "genericQueryCall"
				genericQueryRequest.setQueryName("GET_USER_DETAILS_BY_USER_ID");
				acpUserRegistrationRequest = new ACPRequest(ServiceNameMapping.GET_USER_REGISTRATION_DETAILS, RequestType.JSON, genericQueryRequest);

			} else if (userDetailsRequestVO.getUserEmailId() != null) {
				genericQueryRequest = userRegistrationHelper.getGenericQueryReqForUserByEmail(userDetailsRequestVO.getUserEmailId());

				// Create acpRequest object with service name "genericQueryCall"
				genericQueryRequest.setQueryName("GET_USER_DETAILS_BY_USER_EMAIL_ID");
				acpUserRegistrationRequest = new ACPRequest(ServiceNameMapping.GET_USER_REGISTRATION_DETAILS, RequestType.JSON, genericQueryRequest);
			}
		}

		// Call the real service and get the response
		ACPResponse acpUserRegistrationResponse = businessDelegate.execute(acpUserRegistrationRequest);
		RolesVO userDetailVO = null;
		userRegistrationBO = (UserRegistrationDetailBO) acpUserRegistrationResponse.getPayload();
		try {
			userDetailVO = userRegistrationHelper.getUserRegistrationDetailVO(userRegistrationBO);
		} catch (Exception e) {
			ErrorVO errorVO = new ErrorVO(ErrorDescription.SEVERITY_CODE_FATAL,	ErrorDescription.ERR_MSG_OPERATION, REQUEST_EXCEPTION);
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
		}
		//userDetailResponseVO.setUserRegistrationDetailVO(userDetailVO);
		return userDetailResponseVO;
	}




	@Loggable
	public UpdateUserResponseVO updateUserPassword(UpdatePasswordRequestVO updatePasswordRequestVO) throws ServiceException {
		UpdateUserResponseVO updateUserResponseVO = new UpdateUserResponseVO();
		GenericQueryRequest genericQueryRequest = null;

		String userId = commonUtils.getUserId();
		String errorDescription;

		if (isPasswordVerified(updatePasswordRequestVO, userId)) {
			genericQueryRequest = userRegistrationHelper.getUpdatePasswordQueryForUser(updatePasswordRequestVO,	userId);
			genericQueryRequest.setQueryName("UPDATE_USER_PASSWORD");
			ACPRequest acpUpdateUserRequest = new ACPRequest(ServiceNameMapping.UPDATE_USER_PASSWORD, RequestType.JSON,	genericQueryRequest);
			// Call the real service and get the response
			ACPResponse acpUpdateUserResponse = businessDelegate.execute(acpUpdateUserRequest);
			if (acpUpdateUserResponse.getAffectedRows() > 0) {
				updateUserResponseVO.setStrStatus(UserServiceConstant.SUCCESS);
				updateUserResponseVO = new UpdateUserResponseVO(UserServiceConstant.SUCCESS);
			} else {
				updateUserResponseVO = new UpdateUserResponseVO("Failed to Change Password.");
				errorDescription = "Failed to Change Password.";
				logger.error(MessageFormattor.errorFormat(ACPQueryServiceImpl.class.getName(), "updateUserPassword", ExceptionType.EXCEPTION_SERVICE, errorDescription));
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, new ErrorVO(UserServiceErrorConstant.US_CHANGE_PASSWORD_EXCEPTION,
								UserServiceErrorConstant.ERR_MSG_CHANGE_PASSWORD, errorDescription));
			}
		} else {
			updateUserResponseVO = new UpdateUserResponseVO("Failed to Verify old Password.");
			errorDescription = "Old Password provided could not be verified.  Password was not changed.";
			logger.error(MessageFormattor.errorFormat(ACPQueryServiceImpl.class.getName(), "updateUserPassword",
					ExceptionType.EXCEPTION_SERVICE, errorDescription));
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(UserServiceErrorConstant.US_PASSWORD_EXCEPTION,
							UserServiceErrorConstant.ERR_INVALID_PASSWORD, errorDescription));
		}
		return updateUserResponseVO;
	}
	
	
	
	@Loggable
	public UpdateUserResponseVO updateForgottenPassword(UpdatePasswordRequestVO updatePasswordRequestVO) throws ServiceException {
		UpdateUserResponseVO updateUserResponseVO = new UpdateUserResponseVO();
		GenericQueryRequest genericQueryRequest = null;

		String errorDescription;
		String email;

		if (StringUtils.isEmpty(updatePasswordRequestVO.getUid())) {
			updateUserResponseVO = new UpdateUserResponseVO("Invalid Request.");
			errorDescription = "Invalid Request.";
			logger.error(MessageFormattor.errorFormat(ACPQueryServiceImpl.class.getName(), "updateForgotUserPassword", ExceptionType.EXCEPTION_SERVICE, errorDescription));
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(
							UserServiceErrorConstant.US_CHANGE_PASSWORD_EXCEPTION,
							UserServiceErrorConstant.ERR_MSG_CHANGE_PASSWORD, errorDescription));
		}
			// Get unencrypted email address
			email = new String(Base64.decodeBase64(updatePasswordRequestVO.getUid().getBytes()));
			genericQueryRequest = userRegistrationHelper.getForgotPasswordQueryForUser(updatePasswordRequestVO, email);
			genericQueryRequest.setQueryName("UPDATE_FORGOT_PASSWORD");
			ACPRequest acpUpdateUserRequest = new ACPRequest(ServiceNameMapping.UPDATE_USER_PASSWORD, RequestType.JSON, genericQueryRequest);
			// Call the real service and get the response
			ACPResponse acpUpdateUserResponse = businessDelegate.execute(acpUpdateUserRequest);
			if (acpUpdateUserResponse.getAffectedRows() > 0) {
				updateUserResponseVO.setStrStatus(UserServiceConstant.SUCCESS);
				updateUserResponseVO = new UpdateUserResponseVO(UserServiceConstant.SUCCESS);
				return updateUserResponseVO;
			} else {
				updateUserResponseVO = new UpdateUserResponseVO("Failed to Change Password.");
				errorDescription = "Failed to Change Password.";
				logger.error(MessageFormattor.errorFormat(ACPQueryServiceImpl.class.getName(), "updateForgotUserPassword", ExceptionType.EXCEPTION_SERVICE, errorDescription));
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(UserServiceErrorConstant.US_CHANGE_PASSWORD_EXCEPTION,
								UserServiceErrorConstant.ERR_MSG_CHANGE_PASSWORD, errorDescription));
			}		
	}

	@Loggable
	public UpdateUserResponseVO forgotPassword(UpdatePasswordRequestVO updatePasswordRequestVO) throws ServiceException {

		byte[] encryptedUid = null;
		UpdateUserResponseVO forgotPasswordResponse = new UpdateUserResponseVO();
		GenericQueryRequest genericQueryRequest = null;
		String errorDescription;
		if (updatePasswordRequestVO == null) {
			return forgotPasswordResponse;
		}
		String email = updatePasswordRequestVO.getEmailAddress();

		// Check if Email Id exists in registration

		// Create acpRequest object with service name "genericQueryCall"
		genericQueryRequest = userRegistrationHelper.getCheckRegisteredQueryForUser(email);
		genericQueryRequest.setQueryName("CHECK_IS_USER_REGISTERED");
		ACPRequest acpEmailExistsRequest = new ACPRequest(ServiceNameMapping.FORGOT_PASSWORD, RequestType.JSON,	genericQueryRequest);

		// Call the real service and get the response
		ACPResponse acpEmailExistsResponse = businessDelegate.execute(acpEmailExistsRequest);

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> acpResponseList = (ArrayList<Map<String, Object>>) acpEmailExistsResponse.getPayload();

		if (acpResponseList.size() == 0) {
			errorDescription = "User is not Valid";
			forgotPasswordResponse = new UpdateUserResponseVO(errorDescription);
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(UserServiceErrorConstant.US_INVALID_USER,
							UserServiceErrorConstant.ERR_MSG_INVALID_USER, errorDescription));
		} 
		
		Map<String,Object> acpResponseMap=acpResponseList.get(0);
		
		if(BooleanUtil.toBoolean(Integer.parseInt(acpResponseMap.get("suspend").toString()))) {
			errorDescription = "User is Suspended";
			forgotPasswordResponse = new UpdateUserResponseVO(errorDescription);
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(UserServiceErrorConstant.US_USER_SUSPEND,
							UserServiceErrorConstant.ERR_MSG_USER_SUSPEND, errorDescription));
		}
		
		
		//TODO 
		//Insert timestamp in DB registration
		
		
		

		logger.debug("before uid decryption, sending email for reset password");
		// Send Email to User to confirm Registration
		if (!StringUtils.isEmpty(email))
			encryptedUid = Base64.encodeBase64(email.getBytes());

		logger.debug("after uid decryption, inserting false user");

		String changePasswordUrl = registrationUrlHost + "/user/reset-password?uid=" + new String(encryptedUid);

		ACPRequest emailRequest = new ACPRequest();
		Map<String, Object> payload = new HashMap<String, Object>();

		payload.put("subject", "Password Information(auction.com)");
		payload.put("content", "");
		payload.put("toemail", email);
		payload.put("fromemail", "customerservice@auction.com");
		payload.put("emailtype", "Forgot Password(Registered Clients Auction.com)");
		
		Map<String, String> dataMap = new HashMap<String, String>();  //for data_set

		dataMap.put("[First Name]", acpResponseMap.get("firstName").toString());
		dataMap.put("[LastName]", acpResponseMap.get("lastName").toString());
		dataMap.put("[URL]", changePasswordUrl);

		if (!dataMap.isEmpty()) {
			payload.put("datamap", dataMap);
		}

		emailRequest.setServiceName("sendEmail");
		emailRequest.setPayload(payload);

		messageProducer.sendMessage(emailRequest, amqExchange, amqRoutingKey);

		forgotPasswordResponse.setStrStatus(UserServiceConstant.SUCCESS);
		forgotPasswordResponse = new UpdateUserResponseVO(UserServiceConstant.SUCCESS);

		return forgotPasswordResponse;
	}

	private boolean isPasswordVerified(UpdatePasswordRequestVO updatePasswordRequestVO, String userId) throws ServiceException {
		GenericQueryRequest queryRequestVerify = null;
		queryRequestVerify = userRegistrationHelper.verifyPasswordQuery(userId);
		UserRegistrationDetailBO userBO = new UserRegistrationDetailBO();
		// Create acpRequest object with service name "genericQueryCall"
		queryRequestVerify.setQueryName("GET_USER_PASSWORD");
		ACPRequest acpUpdateUserRequest = new ACPRequest(ServiceNameMapping.GET_USER_PASSWORD, RequestType.JSON, queryRequestVerify);
		// Call the real service and get the response
		ACPResponse acpUpdateUserResponse = businessDelegate.execute(acpUpdateUserRequest);
		userBO = (UserRegistrationDetailBO) acpUpdateUserResponse.getPayload();
	//	if (!StringUtils.isEmpty(userBO.getPassword())) {
		//	if (userBO.getPassword().equals(updatePasswordRequestVO.getOldPassword())) {
			//	return true;
			//} else {
		//		return false;
		//	}
	//	}
		return false;
	}
	
	
	private boolean isPasswordVerifiedByEmail(UpdatePasswordRequestVO updatePasswordRequestVO, String email) throws ServiceException {
		GenericQueryRequest queryRequestVerify = null;
		queryRequestVerify = userRegistrationHelper.verifyPasswordQueryByEmail(email);
		UserRegistrationDetailBO userBO = new UserRegistrationDetailBO();
		// Create acpRequest object with service name "genericQueryCall"
		queryRequestVerify.setQueryName("GET_USER_PASSWORD_BY_EMAIL");
		ACPRequest acpUpdateUserRequest = new ACPRequest(ServiceNameMapping.GET_USER_PASSWORD, RequestType.JSON, queryRequestVerify);
		// Call the real service and get the response
		ACPResponse acpUpdateUserResponse = businessDelegate.execute(acpUpdateUserRequest);
		userBO = (UserRegistrationDetailBO) acpUpdateUserResponse.getPayload();
		//if (!StringUtils.isEmpty(userBO.getPassword())) {
			//if (userBO.getPassword().equals(updatePasswordRequestVO.getOldPassword())) { 
				//return true;
			//} else {
				//return false;
			//}
		//}
		return false;
	}

	@Loggable
	public StatesResponseVO getUserStates(Integer countryId) throws ServiceException {
		StatesResponseVO statesResponseVO;
		List<StateVO> statesList = new ArrayList<StateVO>();
		try {
			// statesMap = userService.getUserStates(countryId);
			statesList = userService.getUserStates(countryId);
			statesResponseVO = new StatesResponseVO(statesList);
		} catch (ServiceException e) {
			throw e;
		}
		return statesResponseVO;
	}



	@Loggable
	@CacheObject(cacheAction = CacheAction.AddToCache)
	public CitiesResponseVO getCitiesByStateCode(String stateCode) throws ServiceException {
		CitiesResponseVO citiesResponseVO;
		List<String> citiesList = new ArrayList<String>();
		try {
			citiesList = userService.getCitiesByStateCode(stateCode);
			citiesResponseVO = new CitiesResponseVO(citiesList);
		} catch (ServiceException e) {
			throw e;
		}
		return citiesResponseVO;
	}

	@Loggable
	public UpdateUserResponseVO addUserPermission(AppAuthorizeVO appAuthorizeVO) throws ServiceException {
		UpdateUserResponseVO response = userService.addUserPermission(appAuthorizeVO);
		return response;
	}

}
