package com.redwood.rp.common.bidderreg.cre.das.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.redwood.rp.core.base.das.jdbc.impl.AbstractJdbcDaoImpl;
import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.common.bidderreg.cre.constants.BidderRegistrationErrors;
import com.redwood.rp.common.bidderreg.cre.constants.NamedQueryMapping;
import com.redwood.rp.common.bidderreg.cre.constants.TableNameMapping;
import com.redwood.rp.common.bidderreg.cre.das.POFDAO;
import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.core.util.MessageFormattor;
import com.redwood.rp.core.util.QueryDefinitionBean;

public class POFDAOImpl extends AbstractJdbcDaoImpl implements
		POFDAO {
	
	private static final String FILENAME = POFDAOImpl.class.getName();
	
	private static Logger mLogger = LoggerFactory.getLogger(FILENAME);
	
	@Autowired(required = false)
	private HashMap<String,String> namedQueryBean;

	@Override
	public List<Map<String, Object>> getPOFDetails(Long userId)
			throws DaoException {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		List<Map<String, Object>> responsePayLoad = null;
		String errorDescription  = null;
		try{
		
			
			if(userId==null||userId==0)	{
					errorDescription  = BidderRegistrationErrors.ERROR_USER_ID_REQUIRED ;
					throw new DaoException(ExceptionType.EXCEPTION_DAO,
							new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));	
				}

			
			String strQuery= (String)namedQueryBean.get(NamedQueryMapping.GET_POF_BY_USER);
			paramMap.put(TableNameMapping.POF_USER_ID, userId);
			responsePayLoad = executeJdbcQuery(strQuery,paramMap);
		} catch(DaoException e){
			throw e;			
		} catch(Exception e){
			errorDescription  = BidderRegistrationErrors.ERROR_FETCH_POF_DETAILS + e.getMessage();
			mLogger.error(MessageFormattor.errorFormat(
					FILENAME, "getRegStatus",
					ExceptionType.EXCEPTION_DAO, errorDescription));
			throw new DaoException(ExceptionType.EXCEPTION_DAO,
					new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));			
		} 
		return responsePayLoad;
	}

	
	public HashMap getNamedQueryBean() {
		return namedQueryBean;
	}

	public void setNamedQueryBean(HashMap namedQueryBean) {
		this.namedQueryBean = namedQueryBean;
	}
}
