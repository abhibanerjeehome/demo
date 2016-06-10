package com.redwood.rp.flaunt.das.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import com.redwood.rp.core.base.message.publish.MessageProducer;
import com.redwood.rp.core.vo.generic.ACPRequest;
import com.redwood.rp.core.vo.json.Error;
import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.security.service.OauthService;
import com.redwood.rp.security.vo.UserRequestVO;
import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.util.StringUtil;
import com.redwood.rp.flaunt.bo.UserRegistrationDetailBO;
import com.redwood.rp.flaunt.constant.UserServiceErrorConstant;
import com.redwood.rp.flaunt.das.dao.UserDAO;
import com.redwood.rp.flaunt.das.service.UserService;
import com.redwood.rp.flaunt.vo.json.request.CreateUserRegistrationRequestVO;
import com.redwood.rp.flaunt.vo.json.response.RolesVO;
import com.redwood.rp.flaunt.vo.json.response.UserVO;

@Named
@Transactional(value="jdbc_userInformation")
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserDAO userDAO;
	
	@Inject
	private MessageProducer messageProducer;

	@Value("${auction.exchange}")
	private String amqExchange;

	@Value("${auction.queue.routing.key}")
	private String amqRoutingKey;

	@Value("${registration.email.url.host}")
	private String registrationUrlHost;
	
	@Value("${forgot.password.email.expire.in}")
	private String forgotPasswordEmailExpireIn;
	
	@Value("${change.email.confirm.expire.in}")
	private String updateEmailConfirmExpireIn;
	
	@Value("${realauction.email.suffix}")
	private String realAuctionEmailSuffix;
	
	@Value("${realauction.password.default}")
	private String realAuctionPasswordDefault;
	
	
	public void createUserRegistration(CreateUserRegistrationRequestVO createUserReqVO) throws ServiceException{
		String errorMsg = "";
		if(createUserReqVO==null){
			errorMsg = "CreateUserReqVO canot be null. ";
			ErrorVO errorVO = new ErrorVO(UserServiceErrorConstant.US_INVALID_INPUT_EXCEPTION,UserServiceErrorConstant.ERR_MSG_US_INVALID_INPUT,errorMsg);
			throw new ServiceException(ExceptionType.EXCEPTION_VALIDATION,errorVO);
		}
		//Check this email has been taken by other users
		UserRegistrationDetailBO user = null;
		try {
			 user = userDAO.getBaseUserInfoByEmail(createUserReqVO.getEmailAddress());
		} catch (DaoException e) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, e.getErrorVO());
		}
		if(user!=null){
			errorMsg = "Email Address is already taken: "+createUserReqVO.getEmailAddress();
			ErrorVO errorVO = new ErrorVO(UserServiceErrorConstant.US_USER_EXISTS_EXCEPTION,UserServiceErrorConstant.ERR_MSG_USER_EXISTS,errorMsg);
			throw new ServiceException(ExceptionType.EXCEPTION_VALIDATION,errorVO);
		}
		//Check email from falseUser table
		boolean emailFromFlaseUser = false;
		try {
			emailFromFlaseUser = userDAO.checkUserEmailFromFlaseUser(createUserReqVO.getEmailAddress());
		} catch (DaoException e) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, e.getErrorVO());
		}
		if(emailFromFlaseUser){
			errorMsg = "Email Address is already taken: "+createUserReqVO.getEmailAddress();
			ErrorVO errorVO = new ErrorVO(UserServiceErrorConstant.US_USER_UNCONFIRMED_EXCEPTION,UserServiceErrorConstant.ERR_MSG_USER_UNCONFIRMED,errorMsg);
			throw new ServiceException(ExceptionType.EXCEPTION_VALIDATION,errorVO);
		}
	}
	
	/**  
	 * Change password after login
	 */
	@Override
	public void changePassword(String oldPassword, String newPassword) throws ServiceException{
		String errorMsg = "";
		if(StringUtil.isBlank(oldPassword) || StringUtil.isBlank(newPassword)){
			errorMsg = "Both new password and old password canot be null. ";
			ErrorVO errorVO = new ErrorVO(UserServiceErrorConstant.US_INVALID_INPUT_EXCEPTION,UserServiceErrorConstant.ERR_MSG_US_INVALID_INPUT,errorMsg);
			throw new ServiceException(ExceptionType.EXCEPTION_VALIDATION,errorVO);
		}
		UserRequestVO userRequestVO = getUserReuqest();
		//Get user info by user id
		UserRegistrationDetailBO user = null;
		try {
			 user = userDAO.getBaseUserInfoByUserId(Long.valueOf(userRequestVO.getUserId()));
		} catch (DaoException e) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, e.getErrorVO());
		}
		if(user==null){
			errorMsg = "Cannot find a user by user id: "+userRequestVO.getUserId();
			ErrorVO errorVO = new ErrorVO(UserServiceErrorConstant.US_INVALID_USER_EXCEPTION,UserServiceErrorConstant.ERR_MSG_FOR_INVALID_USER,errorMsg);
			throw new ServiceException(ExceptionType.EXCEPTION_VALIDATION,errorVO);
		}
		//Check whether this user is suspended or not
	/*	if(user.getSuspend()==1){
			errorMsg = "Suspended user: "+user.getEmail();
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(UserServiceErrorConstant.US_USER_SUSPEND,
							UserServiceErrorConstant.ERR_MSG_USER_SUSPEND, errorMsg));
		}
		//Check the old password is correct
		if(!oldPassword.equals(user.getPassword())){
			errorMsg = "Invalid password: " + oldPassword;
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(UserServiceErrorConstant.US_PASSWORD_EXCEPTION,
							UserServiceErrorConstant.ERR_INVALID_PASSWORD, errorMsg));
		}
		try {
			userDAO.updatePassWordByUserId(user.getUserId(), user.getPassword());
		} catch (DaoException e) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, e.getErrorVO());
		}*/
	}
	


	
	/**  
	 * Forgot password before login, will send a email with a confirmation link
	 */
	@Override
	public void forgotpassword(String email) throws ServiceException{
		String errorMsg = "";
		if(StringUtil.isBlank(email)){
			errorMsg = "Email canot be null. ";
			ErrorVO errorVO = new ErrorVO(UserServiceErrorConstant.US_INVALID_INPUT_EXCEPTION,UserServiceErrorConstant.ERR_MSG_US_INVALID_INPUT,errorMsg);
			throw new ServiceException(ExceptionType.EXCEPTION_VALIDATION,errorVO);
		}
		UserRegistrationDetailBO user = null;
		try {
			 user = userDAO.getBaseUserInfoByEmail(email);
		} catch (DaoException e) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, e.getErrorVO());
		}
		if(user==null){
			errorMsg = "User cannot be found by email: "+email;
			ErrorVO errorVO = new ErrorVO(UserServiceErrorConstant.US_INVALID_USER,UserServiceErrorConstant.ERR_MSG_INVALID_USER,errorMsg);
			throw new ServiceException(ExceptionType.EXCEPTION_VALIDATION,errorVO);
		}
		//Check whether this user is suspended or not
		/*if(user.getSuspend()==1){
			errorMsg = "Suspended user: "+email;
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(UserServiceErrorConstant.US_USER_SUSPEND,
							UserServiceErrorConstant.ERR_MSG_USER_SUSPEND, errorMsg));
		}*/
		
		//Generate a unique string 
		String uniqueStr = UUID.randomUUID().toString();
		
		//Store the unique string and current time to registration table
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.SECOND, Integer.valueOf(forgotPasswordEmailExpireIn));
			userDAO.updateFpwdRandomStrNExpTimeByEmail(uniqueStr, calendar.getTime(), email);
		} catch (DaoException e) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, e.getErrorVO());
		}
		
		//Send out a email to user
		sendResetPasswordEmail(user, uniqueStr);
	}
	
	/**  
	 * When user clicks the confirmation link from email, this method will be called
	 */
	@Override
	public void resetForgotPassword(String uuid, String newPassword) throws ServiceException{
		String errorMsg = "";
		if(StringUtil.isBlank(uuid) || StringUtils.isBlank(newPassword)){
			errorMsg = "Uid and newPassword canot be null. ";
			ErrorVO errorVO = new ErrorVO(UserServiceErrorConstant.US_INVALID_INPUT_EXCEPTION,UserServiceErrorConstant.ERR_MSG_US_INVALID_INPUT,errorMsg);
			throw new ServiceException(ExceptionType.EXCEPTION_VALIDATION,errorVO);
		}
		UserRegistrationDetailBO user = null;
		try {
			 user = userDAO.getBaseUserInfoByUUID(uuid);
		} catch (DaoException e) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, e.getErrorVO());
		}
		if(user==null){
			errorMsg = "Invalid uid: "+uuid;
			ErrorVO errorVO = new ErrorVO(UserServiceErrorConstant.US_RESET_PASSWORD_INVALID_UID,UserServiceErrorConstant.ERR_MSG_RESET_PASSWORD_INVALID_UID,errorMsg);
			throw new ServiceException(ExceptionType.EXCEPTION_VALIDATION,errorVO);
		}
		
	/*	if(user.getFpwdExpTime()==null){
			errorMsg = "FpwdExpTime is null";
			ErrorVO errorVO = new ErrorVO(UserServiceErrorConstant.US_INVALID_INPUT_EXCEPTION,UserServiceErrorConstant.ERR_MSG_US_INVALID_INPUT,errorMsg);
			throw new ServiceException(ExceptionType.EXCEPTION_VALIDATION,errorVO);
		}*/
		
		Calendar eCalendar = Calendar.getInstance();
		//eCalendar.setTime(user.getFpwdExpTime());
		if(eCalendar.before(Calendar.getInstance())){
			errorMsg = "Expired uid : "+ uuid;
			ErrorVO errorVO = new ErrorVO(UserServiceErrorConstant.US_RESET_PASSWORD_EXPIRED,UserServiceErrorConstant.ERR_MSG_RESET_PASSWORD_EXPIRED,errorMsg);
			throw new ServiceException(ExceptionType.EXCEPTION_VALIDATION,errorVO);
		}
		try {
			//userDAO.updatePassWordByUserId(user.getUserId(), newPassword);
			userDAO.updateFpwdRandomStrNExpTimeByEmail(null, null, null);
		} catch (DaoException e) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, e.getErrorVO());
		}
	}
	
	
	/**
	 * Send out a email for reset password
	 * 
	 * @param user
	 * @param uniqueStr
	 */
	private void sendResetPasswordEmail(UserRegistrationDetailBO user, String uniqueStr) {
		ACPRequest emailRequest = new ACPRequest();
		Map<String, Object> payload = new HashMap<String, Object>();
		payload.put("subject", "Password Information(auction.com)");
		payload.put("content", "");
		//payload.put("toemail", user.getEmail());
		payload.put("fromemail", "customerservice@auction.com");
		payload.put("emailtype", "Forgot Password(Registered Clients Auction.com)");
		
		Map<String, String> dataMap = new HashMap<String, String>();  //for data_set

		//dataMap.put("[First Name]", user.getFirstName());
		//.put("[LastName]", user.getLastName());
		
		String url = registrationUrlHost + "/user/reset-password?uid=" + uniqueStr;
		dataMap.put("[URL]", "<a href=\"" + url + "\" target=\"_blank\">" + url + "</a>");

		if (!dataMap.isEmpty()) {
			payload.put("datamap", dataMap);
		}
		emailRequest.setServiceName("sendEmail");
		emailRequest.setPayload(payload);

		messageProducer.sendMessage(emailRequest, amqExchange, amqRoutingKey);
	}
	
	
	private UserRequestVO getUserReuqest() throws ServiceException{
		UserRequestVO userRequestVO = OauthService.getUserRequest();
		if(userRequestVO==null || userRequestVO.getUserId()==null){
			String errorMsg = "userRequestVO is null or userid is null";
			ErrorVO errorVO = new ErrorVO(UserServiceErrorConstant.US_INVALID_INPUT_EXCEPTION,UserServiceErrorConstant.ERR_MSG_US_INVALID_INPUT,errorMsg);
			throw new ServiceException(ExceptionType.EXCEPTION_VALIDATION,errorVO);
		}
		return userRequestVO;
	}

	@Override
	public List<RolesVO> getRoles() throws ServiceException {
		String errorDescription;
		List<RolesVO> rolesVOList;

		try {			
			rolesVOList = userDAO.getRoles();
		} catch (DaoException e) {
			errorDescription = "Internal error: " + e.getMessage();
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,
							ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
		}
		return rolesVOList;
	}

	@Override
	public int createRoles(String newRoles) throws ServiceException {
		String errorDescription;
		int insertedId;
		try {			
			insertedId = userDAO.createRoles(newRoles);
		} catch (DaoException e) {
			errorDescription = "Internal error: " + e.getMessage();
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,
							ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
		}
		return insertedId;
	}

	@Override
	public int removeRoles(String roleIds) throws ServiceException {
		String errorDescription;
		int deletedRows;
		try {			
			deletedRows = userDAO.removeRoles(roleIds);
		} catch (DaoException e) {
			errorDescription = "Internal error: " + e.getMessage();
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,
							ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
		}
		return deletedRows;
	}

	@Override
	public int editRoles(RolesVO rolesVO) throws ServiceException {
		String errorDescription;
		int editedRows;
		try {			
			editedRows = userDAO.editRoles(rolesVO);
		} catch (DaoException e) {
			errorDescription = "Internal error: " + e.getMessage();
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,
							ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
		}
		return editedRows;
	}

	@Override
	public List<UserVO> getUserList(String roleIds) throws ServiceException {
		String errorDescription;
		List<UserVO> userVOList;

		try {			
			userVOList = userDAO.getUserList(roleIds);
		} catch (DaoException e) {
			errorDescription = "Internal error: " + e.getMessage();
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,
							ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
		}
		return userVOList;
	}

	@Override
	public int createUser(UserVO newUserToAdd) throws ServiceException {
		String errorDescription;
		int insertedId;
		try {			
			insertedId = userDAO.createUser(newUserToAdd);
		} catch (DaoException e) {
			errorDescription = "Internal error: " + e.getMessage();
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,
							ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
		}
		return insertedId;
	}

}
