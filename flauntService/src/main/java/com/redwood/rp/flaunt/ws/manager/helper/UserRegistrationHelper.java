package com.redwood.rp.flaunt.ws.manager.helper;

import static com.redwood.rp.flaunt.constant.UserServiceConstant.COUNTRY_ID_USA;
import static com.redwood.rp.flaunt.constant.UserServiceConstant.COUNTRY_NAME_USA;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.redwood.rp.genericquery.constant.QueryType;
import com.redwood.rp.genericquery.vo.generic.GenericQueryRequest;
import com.redwood.rp.core.util.BooleanUtil;
import com.redwood.rp.flaunt.bo.UserAuthBO;
import com.redwood.rp.flaunt.bo.UserRegistrationDetailBO;
import com.redwood.rp.flaunt.constant.UserServiceConstant;
import com.redwood.rp.flaunt.vo.json.request.CreateUserRegistrationRequestVO;
import com.redwood.rp.flaunt.vo.json.request.UpdatePasswordRequestVO;
import com.redwood.rp.flaunt.vo.json.request.UpdateUserRequestVO;
import com.redwood.rp.flaunt.vo.json.request.UserAuthRequestVO;
import com.redwood.rp.flaunt.vo.json.response.RolesVO;

public class UserRegistrationHelper {


	public GenericQueryRequest getGenericQueryReqForUser(String userId){
		//Creates the genericQueryRequest object
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("USER_ID", userId);
		//paramMap.put("EMAIL_ADDRESS", emailId);
		GenericQueryRequest genericQueryRequest = new GenericQueryRequest();
		genericQueryRequest.setQueryType(QueryType.SELECT);
		genericQueryRequest.setSP(false);
		genericQueryRequest.setParameterMap(paramMap);

		return genericQueryRequest;
	}
	



	public GenericQueryRequest getGenericInsertQueryForUser(CreateUserRegistrationRequestVO createUserReqVO){
		//Creates the genericQueryRequest object
		Map<String,Object> paramMap = new HashMap<String,Object>();
		//paramMap.put("USERID", createUserReqVO.getUserId());
		paramMap.put("FIRST_NAME", createUserReqVO.getFirstName());
		paramMap.put("LAST_NAME", createUserReqVO.getLastName());
		paramMap.put("CITY", createUserReqVO.getCity());
		paramMap.put("COMPANY_NAME", createUserReqVO.getCompanyName());
		paramMap.put("EMAIL_ADDRESS", createUserReqVO.getEmailAddress());
		paramMap.put("COUNTRY_ID", createUserReqVO.getCountryId());
		paramMap.put("PASSWORD", createUserReqVO.getPassword());
		paramMap.put("PRIMARY_PHONE", createUserReqVO.getPrimaryPhone());
		
		if (!StringUtils.isEmpty(createUserReqVO.getMobile()))
			paramMap.put("MOBILE", createUserReqVO.getMobile());
		else 
			paramMap.put("MOBILE", UserServiceConstant.EMPTY);
		
		
		paramMap.put("ZIP", createUserReqVO.getZipCode());
		paramMap.put("STATE", createUserReqVO.getState());
		paramMap.put("STREET_ADDRESS", createUserReqVO.getStreetAddress());
		paramMap.put("TITLE", createUserReqVO.getTitle());
		paramMap.put("JOIN_DATE", new Date());
		paramMap.put("UID", createUserReqVO.getUniqueId());
		
		if(createUserReqVO.isEmailOptIn()) {
			paramMap.put("EMAIL_OPT_IN", UserServiceConstant.Yes);
		} else {
			paramMap.put("EMAIL_OPT_IN", UserServiceConstant.NO);
		}
				
		//TODO paramMap.put("UID", getUid());

		GenericQueryRequest genericQueryRequest = new GenericQueryRequest();
		genericQueryRequest.setQueryType(QueryType.INSERT);
		genericQueryRequest.setSP(false);		
		genericQueryRequest.setParameterMap(paramMap);

		return genericQueryRequest;
	}
	
	
	public GenericQueryRequest getInsertQueryForRegistration(RolesVO createUserReqVO){
		//Creates the genericQueryRequest object
		Map<String,Object> paramMap = new HashMap<String,Object>();						

		GenericQueryRequest genericQueryRequest = new GenericQueryRequest();
		genericQueryRequest.setQueryType(QueryType.INSERT);
		genericQueryRequest.setSP(false);		
		genericQueryRequest.setParameterMap(paramMap);

		return genericQueryRequest;
	}
	



	

	public GenericQueryRequest getUpdateFirstLoginQuery(String userId) {
		//Creates the genericQueryRequest object
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("USER_ID", userId);
		paramMap.put("IS_FIRST_LOGIN", UserServiceConstant.ZERO_INT_NUM);

		GenericQueryRequest genericQueryRequest = new GenericQueryRequest();
		genericQueryRequest.setQueryType(QueryType.UPDATE);
		genericQueryRequest.setSP(false);
		genericQueryRequest.setParameterMap(paramMap);

		return genericQueryRequest;
	}

	public RolesVO getUserRegistrationDetailVO(UserRegistrationDetailBO userDetailBO) {		

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		//simpleDateFormat.format(new Date(bidStartDate.getTime())

		RolesVO userDetailVO = new RolesVO();
		
		userDetailVO.setRoleId(userDetailBO.getRoleId());
		userDetailVO.setRole(userDetailBO.getRole());

		return userDetailVO;
	}
	
	





	public GenericQueryRequest getGenericQueryReqForUserByEmail(String userEmailId){
		//Creates the genericQueryRequest object
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("USER_EMAIL_ID", userEmailId);
		//paramMap.put("EMAIL_ADDRESS", emailId);
		GenericQueryRequest genericQueryRequest = new GenericQueryRequest();
		genericQueryRequest.setQueryType(QueryType.SELECT);
		genericQueryRequest.setSP(false);
		genericQueryRequest.setParameterMap(paramMap);

		return genericQueryRequest;
	}
	
	public GenericQueryRequest getCheckRegisteredQueryForUser(String email) {
		//Creates the genericQueryRequest object
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("EMAIL_ADDRESS", email);
		paramMap.put("USER_EMAIL_ID", email);
		//paramMap.put("EMAIL_ADDRESS", emailId);
		GenericQueryRequest genericQueryRequest = new GenericQueryRequest();
		genericQueryRequest.setQueryType(QueryType.SELECT);
		genericQueryRequest.setSP(false);
		genericQueryRequest.setParameterMap(paramMap);

		return genericQueryRequest;
	}


	public GenericQueryRequest verifyPasswordQuery(String userId) {
		//Creates the genericQueryRequest object
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("USER_ID", userId);
		//paramMap.put("EMAIL_ADDRESS", emailId);
		GenericQueryRequest genericQueryRequest = new GenericQueryRequest();
		genericQueryRequest.setQueryType(QueryType.SELECT);
		genericQueryRequest.setSP(false);
		genericQueryRequest.setParameterMap(paramMap);

		return genericQueryRequest;
	}
	
	public GenericQueryRequest verifyPasswordQueryByEmail(String email) {
		//Creates the genericQueryRequest object
		Map<String,Object> paramMap = new HashMap<String,Object>();
		//paramMap.put("USER_ID", userId);
		paramMap.put("EMAIL_ADDRESS", email);
		GenericQueryRequest genericQueryRequest = new GenericQueryRequest();
		genericQueryRequest.setQueryType(QueryType.SELECT);
		genericQueryRequest.setSP(false);
		genericQueryRequest.setParameterMap(paramMap);

		return genericQueryRequest;
	}
	
	public GenericQueryRequest deleteFalseUserQuery(String userId) {
		//Creates the genericQueryRequest object
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("USER_ID", userId);
		//paramMap.put("EMAIL_ADDRESS", emailId);
		GenericQueryRequest genericQueryRequest = new GenericQueryRequest();
		genericQueryRequest.setQueryType(QueryType.UPDATE);
		genericQueryRequest.setSP(false);
		genericQueryRequest.setParameterMap(paramMap);

		return genericQueryRequest;
	}


	public GenericQueryRequest getUpdatePasswordQueryForUser(UpdatePasswordRequestVO updatePasswordRequestVO, String userId) {
		//Creates the genericQueryRequest object
		GenericQueryRequest genericQueryRequest = new GenericQueryRequest();
		Map<String,Object> paramMap = new HashMap<String,Object>();

		if(!StringUtils.isEmpty(updatePasswordRequestVO.getNewPassword())) {
			paramMap.put("USER_ID", userId);
			paramMap.put("NEW_PASSWORD", updatePasswordRequestVO.getNewPassword());		

			genericQueryRequest.setQueryType(QueryType.UPDATE);
			genericQueryRequest.setSP(false);
			genericQueryRequest.setParameterMap(paramMap);
		}

		return genericQueryRequest;
	}


	public GenericQueryRequest getForgotPasswordQueryForUser(UpdatePasswordRequestVO updatePasswordRequestVO, String email) {
		//Creates the genericQueryRequest object
		GenericQueryRequest genericQueryRequest = new GenericQueryRequest();
		Map<String,Object> paramMap = new HashMap<String,Object>();

		if(!StringUtils.isEmpty(updatePasswordRequestVO.getNewPassword())) {
			paramMap.put("EMAIL", email);
			paramMap.put("NEW_PASSWORD", updatePasswordRequestVO.getNewPassword());		

			genericQueryRequest.setQueryType(QueryType.UPDATE);
			genericQueryRequest.setSP(false);
			genericQueryRequest.setParameterMap(paramMap);
		}

		return genericQueryRequest;
	}


}
