package com.redwood.rp.flaunt.ws.manager.impl;

import static com.redwood.rp.flaunt.constant.UserServiceConstant.REQUEST_EXCEPTION;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.flaunt.das.service.UserService;
import com.redwood.rp.flaunt.vo.json.response.RolesResponseVO;
import com.redwood.rp.flaunt.vo.json.response.RolesVO;
import com.redwood.rp.flaunt.vo.json.response.UserVO;
import com.redwood.rp.flaunt.vo.json.response.UsersResponseVO;
import com.redwood.rp.flaunt.ws.manager.UserManager;

@Named
public class UserManagerImpl implements UserManager {
	
	@Inject
	private UserService userService;
	
	@Override
	public void forgotpassword(String email) throws ServiceException{
		userService.forgotpassword(email);
		
	}
	
	@Override
	public void resetForgotPassword(String uuid, String newPassword) throws ServiceException{
		userService.resetForgotPassword(uuid, newPassword);
	}

	@Override
	public RolesResponseVO getRoles() throws ServiceException {
		List<RolesVO> rolesVOList = new ArrayList<RolesVO>();
		RolesResponseVO rolesResponseVO = new RolesResponseVO();
		try {
			rolesVOList = userService.getRoles();
			
		} catch (Exception e) {
			ErrorVO errorVO = new ErrorVO(ErrorDescription.SEVERITY_CODE_FATAL,
					ErrorDescription.ERR_MSG_OPERATION, REQUEST_EXCEPTION);
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
		}
		rolesResponseVO.setRolesVO(rolesVOList);
		return rolesResponseVO;
	}

	@Override
	public int createRoles(String newRoles) throws ServiceException {
		if(StringUtils.isEmpty(newRoles)) {
			String errMsg = "New Role Names not provided";
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrCd("US120");
			errorVO.setErrMsg(errMsg);
			throw new ServiceException(errMsg, errorVO);
		}
		int insertedIds = userService.createRoles(newRoles);
		return insertedIds;
	}

	@Override
	public int removeRoles(String roleIds) throws ServiceException {
		if(StringUtils.isEmpty(roleIds)) {
			String errMsg = "New Role Names not provided";
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrCd("US120");
			errorVO.setErrMsg(errMsg);
			throw new ServiceException(errMsg, errorVO);
		}
		int deletedRows = userService.removeRoles(roleIds);
		return deletedRows;
	}

	@Override
	public int editRoles(RolesVO rolesVO) throws ServiceException {
		if(rolesVO == null) {
			String errMsg = "Role Names not provided";
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrCd("US120");
			errorVO.setErrMsg(errMsg);
			throw new ServiceException(errMsg, errorVO);
		}
		int editedRows = userService.editRoles(rolesVO);
		return editedRows;
	}

	@Override
	public UsersResponseVO getUserList(String roleIds) throws ServiceException {
		List<UserVO> userVOList = new ArrayList<UserVO>();
		UsersResponseVO usersResponseVO = new UsersResponseVO();
		try {
			userVOList = userService.getUserList(roleIds);
			
		} catch (Exception e) {
			ErrorVO errorVO = new ErrorVO(ErrorDescription.SEVERITY_CODE_FATAL,
					ErrorDescription.ERR_MSG_OPERATION, REQUEST_EXCEPTION);
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
		}
		usersResponseVO.setUserVO(userVOList);
		return usersResponseVO;
	}

	@Override
	public int createUser(UserVO newUserToAdd) throws ServiceException {
		if(newUserToAdd == null) {
			String errMsg = "New User details not provided";
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrCd("US120");
			errorVO.setErrMsg(errMsg);
			throw new ServiceException(errMsg, errorVO);
		}
		int insertedIds = userService.createUser(newUserToAdd);
		return insertedIds;
	}
}
