package com.redwood.rp.flaunt.util;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.security.service.OauthService;
import com.redwood.rp.security.vo.UserRequestVO;
import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.flaunt.constant.UserServiceConstant;

@Named
public class CommonUtils {

	private static Logger logger = LoggerFactory.getLogger(CommonUtils.class.getName());

	public CommonUtils() {
	}


	public String getUserId() throws ServiceException {		

		UserRequestVO userVO = OauthService.getUserRequest();

		if (userVO != null) {
			if(!userVO.isAnonymousToken()) {
				return userVO.getUserId();				
			} else {
				ErrorVO errorVO = new ErrorVO(ErrorDescription.SEVERITY_CODE_FATAL, ErrorDescription.ERR_MSG_OPERATION, UserServiceConstant.FETCH_CACHED_USER_EXCEPTION_ANONYMOUS);
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,errorVO);
			}
		} else {				
			ErrorVO errorVO = new ErrorVO(ErrorDescription.SEVERITY_CODE_FATAL, ErrorDescription.ERR_MSG_OPERATION, UserServiceConstant.FETCH_CACHED_USER_EXCEPTION_NOTOKEN);
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,errorVO);
		}
	}
	

	public String getClientIPAddress() throws ServiceException {		

		UserRequestVO userVO = OauthService.getUserRequest();
		String ip;

		if (userVO != null) {
			if (userVO.getClientIP()==null) {
				return userVO.getRequestIP();
			}
			return userVO.getClientIP();

		} else {				
			ErrorVO errorVO = new ErrorVO(ErrorDescription.SEVERITY_CODE_FATAL, ErrorDescription.ERR_MSG_OPERATION, UserServiceConstant.FETCH_CLIENT_IP_EXCEPTION_NOTOKEN);
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,errorVO);
		}
	}
	
	public String getClientId() throws ServiceException {		

		UserRequestVO userVO = OauthService.getUserRequest();

		if (userVO != null) {
			return userVO.getClientId();
		} else {				
			ErrorVO errorVO = new ErrorVO(ErrorDescription.SEVERITY_CODE_FATAL, ErrorDescription.ERR_MSG_OPERATION, UserServiceConstant.FETCH_CLIENT_ID_EXCEPTION_NOTOKEN);
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,errorVO);
		}
	}

	/**
	 * Method to convert JSONArray to List
	 * @param array
	 * @return List<String>
	 */
	public static List<String> jsonArrayToList(JSONArray array) {
		List<String> returnValue = null;
		try {
			if (array != null && array.length() > 0) {
				returnValue = new ArrayList<String>();
				for (int i = 0; i < array.length(); i ++) {
					returnValue.add(array.getString(i));
				}
			}
		} catch (JSONException exception) {
			logger.error("An error occurred when converting the JSON array " + array + " into a list");
		}
		return returnValue;
	}

}
