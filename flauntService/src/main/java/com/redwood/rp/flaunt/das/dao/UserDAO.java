package com.redwood.rp.flaunt.das.dao;

import java.util.Date;
import java.util.List;

import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.flaunt.bo.EmailUpdateVO;
import com.redwood.rp.flaunt.bo.UserRegistrationDetailBO;
import com.redwood.rp.flaunt.vo.json.response.RolesVO;
import com.redwood.rp.flaunt.vo.json.response.UserVO;



public interface UserDAO {
	
	/**
	 * @param email
	 * @return
	 * @throws DaoException
	 */
	public UserRegistrationDetailBO getBaseUserInfoByEmail(String email) throws DaoException;
	
	/**
	 * @param userId
	 * @return
	 * @throws DaoException
	 */
	public UserRegistrationDetailBO getBaseUserInfoByUserId(Long userId) throws DaoException;
	/**
	 * @param randomStr
	 * @param expTime
	 * @param email
	 * @return
	 * @throws DaoException
	 */
	public Integer updateFpwdRandomStrNExpTimeByEmail(String randomStr, Date expTime, String email) throws DaoException;

	/**
	 * @param uuid
	 * @return
	 * @throws DaoException
	 */
	public UserRegistrationDetailBO getBaseUserInfoByUUID(String uuid) throws DaoException;
	
	/**
	 * @param userId
	 * @param password
	 * @return
	 * @throws DaoException
	 */
	public Integer updatePassWordByUserId(Long userId, String password) throws DaoException;
	
	/**
	 * @param email
	 * @return
	 * @throws DaoException
	 */
	public boolean checkUserEmailFromFlaseUser(String email) throws DaoException;
	
	
	public String getPasswordByEmail(String email) throws DaoException;
	

	/**
	 * @return
	 * @throws DaoException
	 */
	public List<RolesVO> getRoles() throws DaoException;

	/**
	 * @param newRoles
	 * @return
	 */
	public int createRoles(String newRoles) throws DaoException;

	/**
	 * @param roleIds
	 * @return
	 * @throws DaoException
	 */
	public int removeRoles(String roleIds) throws DaoException;

	/**
	 * @param rolesVO
	 * @return
	 * @throws DaoException
	 */
	public int editRoles(RolesVO rolesVO) throws DaoException;

	/**
	 * @param roleIds
	 * @return
	 * @throws DaoException
	 */
	public List<UserVO> getUserList(String roleIds) throws DaoException;

	/**
	 * @param newUserToAdd
	 * @return
	 * @throws DaoException
	 */
	public int createUser(UserVO newUserToAdd) throws DaoException;
}
