package com.redwood.rp.flaunt.das.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redwood.rp.core.base.das.jdbc.impl.AbstractJdbcDaoImpl;
import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.exception.UtilityException;
import com.redwood.rp.core.util.MessageFormattor;
import com.redwood.rp.core.util.QueryDefinitionBean;
import com.redwood.rp.flaunt.bo.UserRegistrationDetailBO;
import com.redwood.rp.flaunt.constant.UserServiceConstant;
import com.redwood.rp.flaunt.das.dao.AppAuthorizeDAO;
import com.redwood.rp.flaunt.das.dao.UserDAO;
import com.redwood.rp.flaunt.vo.json.request.AppAuthorizeVO;
import com.redwood.rp.flaunt.vo.json.response.UpdateUserResponseVO;

public class AppAuthorizeDAOImpl extends AbstractJdbcDaoImpl implements AppAuthorizeDAO {
	
	private Logger mLogger = LoggerFactory.getLogger(AppAuthorizeDAOImpl.class.getName());
	private QueryDefinitionBean namedQueryBean;
	
	@Inject
	private UserDAO userDao;

	public void insertUserPermission() throws ServiceException {
		
	}

	@Override
	public Integer insertUserPermission(AppAuthorizeVO appAuthorizeVO) throws DaoException {
		
		if(appAuthorizeVO == null) {
			String errorDescription = "AppAuthorizeVO cannot be null";
			mLogger.error(MessageFormattor.errorFormat(
					UserDAOImpl.class.getName(), "insertUserPermission",
					ExceptionType.EXCEPTION_DAO, errorDescription));
			throw new DaoException(ExceptionType.EXCEPTION_DAO,
					new ErrorVO(ErrorDescription.ERR_CD_DAO_INSERT,ErrorDescription.ERR_MSG_DAO_INSERT, errorDescription));
		}
		String sqlStr;
		UserRegistrationDetailBO userDetails = new UserRegistrationDetailBO();
		try {
			userDetails = userDao.getBaseUserInfoByEmail(appAuthorizeVO.getEmail());
			
			sqlStr = namedQueryBean.getQueryByName("INSERT_USER_PERMISSION");
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(),e.getErrorVO());
		}
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("OAUTH_ID", appAuthorizeVO.getOauthId());
	//	paramMap.put("USER_ID", userDetails.getUserId());
		paramMap.put("CREATED_BY", appAuthorizeVO.getCreatedBy());
		paramMap.put("UPDATED_BY", appAuthorizeVO.getUpdatedBy());
		paramMap.put("PERMISSION_ENABLE", appAuthorizeVO.getPermissionEnable());
		paramMap.put("UPDATED_DATE", new Date());
		paramMap.put("CREATED_DATE", new Date());
		Integer affectedRows = executeJdbcUpdate(sqlStr,paramMap);
		return affectedRows;
	}
	
	//Setters and getters
	public QueryDefinitionBean getNamedQueryBean() {
		return namedQueryBean;
	}
	public void setNamedQueryBean(QueryDefinitionBean namedQueryBean) {
		this.namedQueryBean = namedQueryBean;
	}
}
