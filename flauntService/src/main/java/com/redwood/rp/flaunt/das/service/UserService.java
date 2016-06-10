package com.redwood.rp.flaunt.das.service;

import java.util.List;

import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.flaunt.vo.json.request.CreateUserRegistrationRequestVO;
import com.redwood.rp.flaunt.vo.json.response.RolesVO;
import com.redwood.rp.flaunt.vo.json.response.UserVO;

public interface UserService {
	
	/**
	 * @param oldPassword
	 * @param newPassword
	 * @throws ServiceException
	 */
	public void changePassword(String oldPassword, String newPassword) throws ServiceException;
	
	/**
	 * @param email
	 * @throws ServiceException
	 */
	public void forgotpassword(String email) throws ServiceException;
	
	/**
	 * @param uuid
	 * @param newPassword
	 * @throws ServiceException
	 */
	public void resetForgotPassword(String uuid, String newPassword) throws ServiceException;
	

	/**
	 * @return
	 * @throws ServiceException
	 */
	public List<RolesVO> getRoles() throws ServiceException;

	/**
	 * @param newRoles
	 * @return
	 * @throws ServiceException
	 */
	public int createRoles(String newRoles) throws ServiceException;

	/**
	 * @param roleIds
	 * @return
	 * @throws ServiceException
	 */
	public int removeRoles(String roleIds) throws ServiceException;

	/**
	 * @param rolesVO
	 * @return
	 * @throws ServiceException
	 */
	public int editRoles(RolesVO rolesVO) throws ServiceException;

	/**
	 * @param roleIds
	 * @return
	 */
	public List<UserVO> getUserList(String roleIds) throws ServiceException;
	
	/**
	 * @param newUserToAdd
	 * @return
	 * @throws ServiceException
	 */
	public int createUser(UserVO newUserToAdd) throws ServiceException;

}
