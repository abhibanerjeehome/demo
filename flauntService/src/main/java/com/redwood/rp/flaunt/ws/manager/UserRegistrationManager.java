package com.redwood.rp.flaunt.ws.manager;

import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.flaunt.vo.json.request.AppAuthorizeVO;
import com.redwood.rp.flaunt.vo.json.request.UpdatePasswordRequestVO;
import com.redwood.rp.flaunt.vo.json.request.UserDetailsRequestVO;
import com.redwood.rp.flaunt.vo.json.response.CitiesResponseVO;
import com.redwood.rp.flaunt.vo.json.response.RolesResponseVO;
import com.redwood.rp.flaunt.vo.json.response.StatesResponseVO;
import com.redwood.rp.flaunt.vo.json.response.UpdateUserResponseVO;


public interface UserRegistrationManager {
	
	public RolesResponseVO getUserRegistrationDetails () throws ServiceException;

	public RolesResponseVO getUserById (UserDetailsRequestVO userDetailsRequestVO) throws ServiceException;
		
	public StatesResponseVO getUserStates(Integer countryId) throws ServiceException;

	public CitiesResponseVO getCitiesByStateCode(String stateCode)throws ServiceException;

	public UpdateUserResponseVO updateUserPassword(UpdatePasswordRequestVO updatePasswordRequestVO) throws ServiceException;

	public UpdateUserResponseVO forgotPassword(UpdatePasswordRequestVO updatePasswordRequestVO) throws ServiceException;

	public UpdateUserResponseVO updateForgottenPassword(UpdatePasswordRequestVO updatePasswordRequestVO) throws ServiceException;

	public UpdateUserResponseVO updateFirstLogin() throws ServiceException;

	public UpdateUserResponseVO addUserPermission(AppAuthorizeVO appAuthorizeVO) throws ServiceException;

}
