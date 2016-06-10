package com.redwood.rp.flaunt.das.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.redwood.rp.core.base.das.jdbc.impl.AbstractJdbcDaoImpl;
import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.core.exception.UtilityException;
import com.redwood.rp.core.util.MessageFormattor;
import com.redwood.rp.core.util.QueryDefinitionBean;
import com.redwood.rp.flaunt.bo.UserRegistrationDetailBO;
import com.redwood.rp.flaunt.das.dao.UserDAO;
import com.redwood.rp.flaunt.vo.json.response.RolesVO;
import com.redwood.rp.flaunt.vo.json.response.UserVO;

public class UserDAOImpl extends AbstractJdbcDaoImpl implements UserDAO {

	private Logger mLogger = LoggerFactory.getLogger(UserDAOImpl.class.getName());
	private QueryDefinitionBean namedQueryBean;
	
	
	@Override
	public UserRegistrationDetailBO getBaseUserInfoByEmail(String email) throws DaoException{
		if(StringUtils.isBlank(email)){
			String errorDescription = "Email cannot be null";
			mLogger.error(MessageFormattor.errorFormat(
					UserDAOImpl.class.getName(), "getBaseUserInfoByEmail",
					ExceptionType.EXCEPTION_DAO, errorDescription));
			throw new DaoException(ExceptionType.EXCEPTION_DAO,
					new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
		}
		String sqlStr;
		try {
			sqlStr = namedQueryBean.getQueryByName("GET_BASE_USER_INFO_BY_EMAIL");
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(),e.getErrorVO());
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("email", email);
		List<Map<String, Object>> resultList = executeJdbcQuery(sqlStr,paramMap);
		if(resultList==null || resultList.size()==0){
			return null;
		}else{
			Map<String, Object> map = resultList.get(0);
			Object userIdObj = map.get("userId");
			Object emailObj = map.get("email");
			Object firstNameObj = map.get("firstName");
			Object lastNameObj = map.get("lastName");
			Object suspendObj = map.get("suspend");
			
			UserRegistrationDetailBO user = new UserRegistrationDetailBO();
			return user;
		}
	}
	
	@Override
	public UserRegistrationDetailBO getBaseUserInfoByUserId(Long userId) throws DaoException{
		if(userId==null){
			String errorDescription = "UserId cannot be null";
			mLogger.error(MessageFormattor.errorFormat(
					UserDAOImpl.class.getName(), "getBaseUserInfoByUserId",
					ExceptionType.EXCEPTION_DAO, errorDescription));
			throw new DaoException(ExceptionType.EXCEPTION_DAO,
					new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
		}
		String sqlStr;
		try {
			sqlStr = namedQueryBean.getQueryByName("GET_BASE_USER_INFO_BY_USER_ID");
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(),e.getErrorVO());
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		List<Map<String, Object>> resultList = executeJdbcQuery(sqlStr,paramMap);
		if(resultList==null || resultList.size()==0){
			return null;
		}else{
			Map<String, Object> map = resultList.get(0);
			Object userIdObj = map.get("userId");
			Object emailObj = map.get("email");
			Object firstNameObj = map.get("firstName");
			Object lastNameObj = map.get("lastName");
			Object suspendObj = map.get("suspend");
			Object passwordObj = map.get("password");
			
			UserRegistrationDetailBO user = new UserRegistrationDetailBO();

			return user;
		}
	}
	
	@Override
	public boolean checkUserEmailFromFlaseUser(String email) throws DaoException{
		if(StringUtils.isBlank(email)){
			String errorDescription = "Email cannot be null";
			mLogger.error(MessageFormattor.errorFormat(
					UserDAOImpl.class.getName(), "checkUserEmailFromFlaseUser",
					ExceptionType.EXCEPTION_DAO, errorDescription));
			throw new DaoException(ExceptionType.EXCEPTION_DAO,
					new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
		}
		String sqlStr;
		try {
			sqlStr = namedQueryBean.getQueryByName("CHECK_EMAIL_FROM_FALSE_USER");
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(),e.getErrorVO());
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("email", email);
		List<Map<String, Object>> resultList = executeJdbcQuery(sqlStr,paramMap);
		if(resultList==null || resultList.size()==0){
			return false;
		}else{
			return true; 
		}
	}
			
	@Override
	public UserRegistrationDetailBO getBaseUserInfoByUUID(String uuid) throws DaoException{
		String sqlStr;
		try {
			sqlStr = namedQueryBean.getQueryByName("GET_BASE_USER_INFO_BY_UUID");
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(),e.getErrorVO());
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fpwd_random_str", uuid);
		List<Map<String, Object>> resultList = executeJdbcQuery(sqlStr,paramMap);
		if(resultList==null || resultList.size()==0){
			return null;
		}else{
			Map<String, Object> map = resultList.get(0);
			Object emailObj = map.get("email");
			Object userIdObj = map.get("userId");
			Object expTimeObj = map.get("fpwd_exp_time");
			
			UserRegistrationDetailBO user = new UserRegistrationDetailBO();
			return user;
		}
	}
	
	@Override
	public Integer updateFpwdRandomStrNExpTimeByEmail(String randomStr, Date expTime, String email) throws DaoException{
		String sqlStr;
		try {
			sqlStr = namedQueryBean.getQueryByName("UPDATE_FPWD_INFO_BY_EMAIL");
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(),e.getErrorVO());
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fpwd_random_str", randomStr);
		paramMap.put("fpwd_exp_time", expTime);
		paramMap.put("email", email);
		Integer affectedRows = executeJdbcUpdate(sqlStr,paramMap);
		return affectedRows; 
	}
	
	@Override
	public Integer updatePassWordByUserId(Long userId, String newPassword) throws DaoException{
		String sqlStr;
		try {
			sqlStr = namedQueryBean.getQueryByName("UPDATE_USER_PASSWORD");
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(),e.getErrorVO());
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("NEW_PASSWORD", newPassword);
		paramMap.put("USER_ID", userId);
		Integer affectedRows = executeJdbcUpdate(sqlStr,paramMap);
		return affectedRows; 
	}
	
	public Long getNewUserUID() throws DaoException{
		String sqlStr;
		try {
			sqlStr = namedQueryBean.getQueryByName("GET_NEW_USER_UID");
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(),e.getErrorVO());
		}
		List<Map<String, Object>> resultList = executeJdbcQuery(sqlStr,null);
		if(resultList==null || resultList.size()==0){
			String errorDescription = "Failed to get new user uid form db. ";
			mLogger.error(MessageFormattor.errorFormat(this.getClass().getName(),
	                "getNewUserUID", ExceptionType.EXCEPTION_DAO, errorDescription));
			throw new DaoException(ExceptionType.EXCEPTION_DAO,
					new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
		}else{
			Map<String, Object> map = resultList.get(0);
			Object maxUidObject = map.get("max_uid");
			if(maxUidObject==null){
				return 10000001L;
			}else{
				Long newUID = null;
				try{
					newUID = Long.valueOf(maxUidObject.toString())+1;
				}catch(Exception e){
					String errorDescription = "Invaid max uid: "+maxUidObject.toString();
					mLogger.error(MessageFormattor.errorFormat(this.getClass().getName(),
			                "getNewUserUID", ExceptionType.EXCEPTION_DAO, errorDescription));
					throw new DaoException(ExceptionType.EXCEPTION_DAO,
							new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
				}
				return newUID;
			}
		}
	}
	

	
	public String getPasswordByEmail(String email) throws DaoException{
		if(StringUtils.isBlank(email)){
			return null;
		}
		String sqlStr;
		try {
			sqlStr = namedQueryBean.getQueryByName("GET_PASSWORD_BY_EMAIL");
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(),e.getErrorVO());
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("email", email);
		List<Map<String, Object>> resultList = executeJdbcQuery(sqlStr,paramMap);
		if(resultList==null || resultList.size()==0){
			return null;
		}else{
			Map<String, Object> map = resultList.get(0);
			Object passwordObj = map.get("password");
			return passwordObj==null?null:passwordObj.toString();
		}
	}
	
	
	//Setters and getters
	public QueryDefinitionBean getNamedQueryBean() {
		return namedQueryBean;
	}
	public void setNamedQueryBean(QueryDefinitionBean namedQueryBean) {
		this.namedQueryBean = namedQueryBean;
	}
	


	@Override
	public List<RolesVO> getRoles() throws DaoException {
		String sqlStr;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<RolesVO> rolesVOList = new ArrayList<RolesVO>();
		try {
			sqlStr = namedQueryBean.getQueryByName("GET_ROLES");
			
			List<Map<String, Object>> resultList = executeJdbcQuery(sqlStr,paramMap);
			if (resultList==null || resultList.size()==0){
				return null;
			} else {
				for (Map<String, Object> map : resultList) {
					RolesVO rolesVO = new RolesVO();
					rolesVO.setRoleId((Integer) map.get("role_id"));
					rolesVO.setRole(map.get("role").toString());
					rolesVO.setStatus((Integer) map.get("status"));
					
					rolesVOList.add(rolesVO);
				}
			}
				
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(),e.getErrorVO());
		}
		return rolesVOList;
	}
	
	/**
	 * 
	 */
	@Override
	public int createRoles(String newRoles) throws DaoException {
		String sqlStr;
		String[] rolesArr = new String[20];
		List<Long> insertedIds = new ArrayList<Long>();
		int insertedId;
		try {
			rolesArr = newRoles.split(",");
			sqlStr = namedQueryBean.getQueryByName("CREATE_ROLES");
			
			for (String newRole : rolesArr) {
				KeyHolder keyHolder = new GeneratedKeyHolder();
				MapSqlParameterSource paramMap =  new MapSqlParameterSource();
				paramMap.addValue("ROLE_NAME", newRole);
				paramMap.addValue("ROLE_STATUS", 1);
				executeJdbcUpdate(sqlStr, paramMap, keyHolder);
				insertedIds.add((Long)keyHolder.getKey());
			}		 
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(), e.getErrorVO());
		}
		
		return insertedIds.indexOf(0);
	}

	@Override
	public int removeRoles(String roleIds) throws DaoException {
		String sqlStr;
		String[] rolesArr = new String[20];
		Map<String, Object> paramMap = new HashMap<String, Object> ();
		int deletedRows = 0;
		try {
			rolesArr = roleIds.split(",");
			sqlStr = namedQueryBean.getQueryByName("REMOVE_ROLES");
			
			for (String roleId : rolesArr) {				
				paramMap.put("ROLE_ID", roleId);
				paramMap.put("ROLE_STATUS", 0);
				executeJdbcUpdate(sqlStr, paramMap);
				deletedRows++;
			}		 
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(), e.getErrorVO());
		}
		
		return deletedRows;
	}

	@Override
	public int editRoles(RolesVO rolesVO) throws DaoException {
		String sqlStr;

		Map<String, Object> paramMap = new HashMap<String, Object> ();
		int editedRows = 0;
		try {
			sqlStr = namedQueryBean.getQueryByName("EDIT_ROLES");
			paramMap.put("ROLE_ID", rolesVO.getRoleId());
			paramMap.put("ROLE_NAME", rolesVO.getRole());
			paramMap.put("ROLE_STATUS", rolesVO.getStatus());
		
			editedRows = executeJdbcUpdate(sqlStr, paramMap);
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(), e.getErrorVO());
		}
		
		return editedRows;
	}

	@Override
	public List<UserVO> getUserList(String roleIds) throws DaoException {
		String sqlStr;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<UserVO> userVOList = new ArrayList<UserVO>();
		try {
			sqlStr = namedQueryBean.getQueryByName("GET_USERS");
			paramMap.put("ROLE_ID", roleIds);
			
			List<Map<String, Object>> resultList = executeJdbcQuery(sqlStr,paramMap);
			if (resultList==null || resultList.size()==0){
				return null;
			} else {
				for (Map<String, Object> map : resultList) {
					UserVO userVO = new UserVO();
					userVO.setRoleId((Integer) map.get("roleId"));
					userVO.setUserId((Integer) map.get("userId"));
					userVO.setStatus((Integer) map.get("status"));
					userVO.setCompany(map.get("company")==null?null:map.get("company").toString());
					userVO.setTitle(map.get("title")==null?null:map.get("title").toString());
					userVO.setEmail(map.get("email").toString());
					userVO.setFirstName(map.get("firstName")==null?null:map.get("firstName").toString());
					userVO.setLastName(map.get("lastName")==null?null:map.get("lastName").toString());
					
					userVOList.add(userVO);
				}
			}
				
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(),e.getErrorVO());
		}
		return userVOList;
	}

	@Override
	public int createUser(UserVO newUserToAdd) throws DaoException {
		String sqlStr;
		int insertedId;
		try {
			sqlStr = namedQueryBean.getQueryByName("CREATE_NEW_USER");

				KeyHolder keyHolder = new GeneratedKeyHolder();
				MapSqlParameterSource paramMap =  new MapSqlParameterSource();
				
				paramMap.addValue("TITLE", newUserToAdd.getTitle());
				paramMap.addValue("LAST_NAME", newUserToAdd.getLastName());
				paramMap.addValue("STATUS", 1);
				paramMap.addValue("FIRST_NAME", newUserToAdd.getFirstName());
				paramMap.addValue("COMPANY", newUserToAdd.getCompany());
				paramMap.addValue("ROLE_ID", newUserToAdd.getRoleId());
				paramMap.addValue("EMAIL", newUserToAdd.getEmail());	
				
				insertedId = executeJdbcUpdate(sqlStr, paramMap, keyHolder);
				if (keyHolder.getKey()!=null) 
					insertedId = keyHolder.getKey().intValue();			 
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(), e.getErrorVO());
		}		
		return insertedId;
	}

	
	
}