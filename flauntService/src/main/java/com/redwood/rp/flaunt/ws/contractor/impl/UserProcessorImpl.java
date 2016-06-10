package com.redwood.rp.flaunt.ws.contractor.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.redwood.rp.core.base.ws.service.BaseRestService;
import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.flaunt.constant.UserServiceErrorConstant;
import com.redwood.rp.flaunt.vo.json.request.AppAuthorizeVO;
import com.redwood.rp.flaunt.vo.json.request.UpdatePasswordRequestVO;
import com.redwood.rp.flaunt.vo.json.request.UserDetailsRequestVO;
import com.redwood.rp.flaunt.vo.json.response.GenericResponseVO;
import com.redwood.rp.flaunt.vo.json.response.RolesResponseVO;
import com.redwood.rp.flaunt.vo.json.response.RolesVO;
import com.redwood.rp.flaunt.vo.json.response.UpdateUserResponseVO;
import com.redwood.rp.flaunt.vo.json.response.UserVO;
import com.redwood.rp.flaunt.vo.json.response.UsersResponseVO;
import com.redwood.rp.flaunt.ws.contractor.UserProcessor;
import com.redwood.rp.flaunt.ws.manager.UserManager;
import com.redwood.rp.flaunt.ws.manager.UserRegistrationManager;

@Named
public class UserProcessorImpl extends BaseRestService implements UserProcessor{

	@Inject
	private UserRegistrationManager userRegistrationManager;
	
	@Inject
	private UserManager userManager;

	public Response getUserRegistrationDetails() {
		try {
			RolesResponseVO responseJsonVO = userRegistrationManager.getUserRegistrationDetails();  
			return setResponseData(responseJsonVO);
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}	
	

	public Response getUserById(RolesVO rolesVO) {
		try {
			RolesResponseVO responseJsonVO = userRegistrationManager.getUserById(null);  
			return setResponseData(responseJsonVO);
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}

	
	public Response updatePassword(UpdatePasswordRequestVO updatePasswordRequestVO) {
		try {
			UpdateUserResponseVO responseJsonVO = null;
			if(updatePasswordRequestVO != null) {
				responseJsonVO = userRegistrationManager.updateUserPassword(updatePasswordRequestVO);
			} else {
				logger.info("updatePasswordRequest is null:   ");
			}
			return setResponseData(responseJsonVO);			
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}
	
	public Response forgotPassword(UpdatePasswordRequestVO updatePasswordRequestVO) {
//		try {
//			UpdateUserResponseVO responseJsonVO = null;
//			if(updatePasswordRequestVO != null) {
//				responseJsonVO = userRegistrationManager.forgotPassword(updatePasswordRequestVO);
//			} else {
//				logger.info("updatePasswordRequest is null:   ");
//			}
//			return setResponseData(responseJsonVO);			
//		} catch (ServiceException e) {
//			return setErrorInfoResponse(e);
//		}
		
        //======================================= added by Huan
		if(updatePasswordRequestVO==null){
			String errorMsg = "The incoming request is null. ";
			ErrorVO errorVO = new ErrorVO(UserServiceErrorConstant.US_INVALID_INPUT_EXCEPTION,UserServiceErrorConstant.ERR_MSG_US_INVALID_INPUT,errorMsg);
			return setErrorInfoResponse(new ServiceException(ExceptionType.EXCEPTION_SERVICE,errorVO));
		}
		try {
			userManager.forgotpassword(updatePasswordRequestVO.getEmailAddress());
			UpdateUserResponseVO responseJsonVO = new UpdateUserResponseVO();
			responseJsonVO.setStrStatus("success");
			return setResponseData(responseJsonVO);	
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}
	
	public Response updateForgottenPassword(UpdatePasswordRequestVO updatePasswordRequestVO) {
//		try {
//			UpdateUserResponseVO responseJsonVO = null;
//			if(updatePasswordRequestVO != null) {
//				responseJsonVO = userRegistrationManager.updateForgottenPassword(updatePasswordRequestVO);
//			} else {
//				logger.info("updatePasswordRequest is null:   ");
//			}
//			return setResponseData(responseJsonVO);			
//		} catch (ServiceException e) {
//			return setErrorInfoResponse(e);
//		}
		
		//======================================= added by Huan
		if(updatePasswordRequestVO==null){
			String errorMsg = "The incoming request is null. ";
			ErrorVO errorVO = new ErrorVO(UserServiceErrorConstant.US_INVALID_INPUT_EXCEPTION,UserServiceErrorConstant.ERR_MSG_US_INVALID_INPUT,errorMsg);
			return setErrorInfoResponse(new ServiceException(ExceptionType.EXCEPTION_SERVICE,errorVO));
		}
		try {
			userManager.resetForgotPassword(updatePasswordRequestVO.getUid(),updatePasswordRequestVO.getNewPassword());
			UpdateUserResponseVO responseJsonVO = new UpdateUserResponseVO();
			responseJsonVO.setStrStatus("success");
			return setResponseData(responseJsonVO);	
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}
	

	public Response addUserPermission(AppAuthorizeVO appAuthorizeVO) {
		UpdateUserResponseVO responseJsonVO = new UpdateUserResponseVO();
		try {
			if(appAuthorizeVO != null) {
				responseJsonVO = userRegistrationManager.addUserPermission(appAuthorizeVO);  
			}
			return setResponseData(responseJsonVO);			
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}
	
	public Response getRoles() {
		try {
			RolesResponseVO responseJsonVO = userManager.getRoles();  
			return setResponseData(responseJsonVO);
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}


	public Response editRoles(RolesVO rolesVO) {
		try {
			int editedRows = userManager.editRoles(rolesVO);  
			return setResponseData(new GenericResponseVO(editedRows));
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}


	public Response createRoles(String newRoles) {
		try {
			int insertedId = userManager.createRoles(newRoles);  
			return setResponseData(new GenericResponseVO(insertedId));
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}


	public Response removeRoles(String roleIds) {
		try {
			int deletedRows = userManager.removeRoles(roleIds);  
			return setResponseData(new GenericResponseVO(deletedRows));
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}
	
	public Response getUserList(String roleIds) {
		try {
			UsersResponseVO responseJsonVO = userManager.getUserList(roleIds);  
			return setResponseData(responseJsonVO);
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}

	public Response getUserById(UserDetailsRequestVO userDetailsRequestVO) {
		// TODO Auto-generated method stub
		return null;
	}


	public Response getUser() {
		// TODO Auto-generated method stub
		return null;
	}



	public Response editUser(UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}



	public Response createUser(UserVO newUserToAdd) {
		try {
			int insertedId = userManager.createUser(newUserToAdd);  
			return setResponseData(new GenericResponseVO(insertedId));
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}



	public Response removeUser(@PathParam("userIds") String userIds) {
		// TODO Auto-generated method stub
		return null;
	}


}
