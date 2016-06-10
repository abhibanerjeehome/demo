package com.redwood.rp.flaunt.ws.manager;

import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.flaunt.vo.json.response.RolesResponseVO;
import com.redwood.rp.flaunt.vo.json.response.RolesVO;
import com.redwood.rp.flaunt.vo.json.response.UserVO;
import com.redwood.rp.flaunt.vo.json.response.UsersResponseVO;

public interface UserManager {
	
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
	public RolesResponseVO getRoles() throws ServiceException;

	/**
	 * @param newRoles 
	 * @return
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
	public UsersResponseVO getUserList(String roleIds) throws ServiceException;

	
    /**
     * @param newUserToAdd
     * @return
     * @throws ServiceException
     */
	public int createUser(UserVO newUserToAdd) throws ServiceException;

}
