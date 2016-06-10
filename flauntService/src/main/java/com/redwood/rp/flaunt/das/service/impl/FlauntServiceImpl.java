package com.redwood.rp.flaunt.das.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import com.redwood.rp.caching.service.CachingService;
import com.redwood.rp.common.user.das.UserInformationDaoService;
import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.security.service.OauthService;
import com.redwood.rp.security.vo.UserRequestVO;
import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.flaunt.constant.UserServiceErrorConstant;
import com.redwood.rp.flaunt.das.dao.FlauntDAO;
import com.redwood.rp.flaunt.das.dao.UserDAO;
import com.redwood.rp.flaunt.das.service.FlauntService;
import com.redwood.rp.flaunt.vo.json.response.AssetTypeVO;
import com.redwood.rp.flaunt.vo.json.response.EditorialBriefVO;
import com.redwood.rp.flaunt.vo.json.response.FlauntContentVO;
import com.redwood.rp.flaunt.vo.json.response.FlauntTaskVO;
import com.redwood.rp.flaunt.vo.json.response.RolesVO;

@Named
@Transactional(value = "jdbc_userInformation",rollbackFor=Exception.class)
public class FlauntServiceImpl implements FlauntService{

	private Logger mLogger = LoggerFactory.getLogger(FlauntServiceImpl.class.getName());
	
	@Inject
	private FlauntDAO flauntDAO;
	
	//Use this service from acpcommon in order to set the latest saved asset list to memcached.
	
	UserInformationDaoService userDao;	
	
	UserDAO userDetailDao;	
	
	private CachingService cachingService;

	private String SAVED_PROP_CACHE_PRE_KEY = "USER_SAVED_PROPERTY_";
	
	@Value("${acpcommon.user.savedproperty.cache.timeout}")
	private String  user_savedproperty_cache_timeout;
	
	private Pattern venueCodePattern = Pattern.compile("^[a-zA-Z]+([0-9]+)");
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	
	
	private UserRequestVO getUserRequest() throws ServiceException{
		UserRequestVO userRequestVO = OauthService.getUserRequest();
		if(userRequestVO==null || userRequestVO.getUserId()==null){
			String errorMsg = "userRequestVO is null or userid is null";
			ErrorVO errorVO = new ErrorVO(UserServiceErrorConstant.US_INVALID_INPUT_EXCEPTION,UserServiceErrorConstant.ERR_MSG_US_INVALID_INPUT,errorMsg);
			throw new ServiceException(ExceptionType.EXCEPTION_VALIDATION,errorVO);
		}
		return userRequestVO;
	}
	
	

	@Override
	public int deleteTask(String taskId) throws ServiceException {
		int deletedRows = 0;
		try {
		 deletedRows = flauntDAO.deleteTask(taskId);
		} catch (DaoException dex) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, dex.getErrorVO());
		}
		return deletedRows;
	}

	@Override
	public FlauntTaskVO getTaskDetails(String taskId) throws ServiceException {
		FlauntTaskVO flauntTaskVO = new FlauntTaskVO();
		try {
			flauntTaskVO = flauntDAO.getTaskDetails(taskId);
			//flauntTaskVO.setContent(getContentDetails(flauntTaskVO.getContentId()+""));
		
		} catch (DaoException dex) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, dex.getErrorVO());
		}
		return flauntTaskVO;
	}

	@Override
	public int createTask(FlauntTaskVO flauntTask) throws ServiceException {
		int i = 0;
		try {
			i = flauntDAO.createTask(flauntTask);
		} catch (DaoException dex) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, dex.getErrorVO());
		}
		return i;
	}

	@Override
	public int editTask(FlauntTaskVO flauntTask) throws ServiceException {
		int i = 0;
		try {
			i = flauntDAO.editTask(flauntTask);
		} catch (DaoException dex) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, dex.getErrorVO());
		}
		return i;
	}
	
	@Override
	public int deleteContent(String contentId) throws ServiceException {
		int deletedRows = 0;
		try {
		 deletedRows = flauntDAO.deleteContent(contentId);
		} catch (DaoException dex) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,dex.getErrorVO());
		}
		return deletedRows;
	}

	@Override
	public FlauntContentVO getContentDetails(String contentId) throws ServiceException {
		FlauntContentVO flauntContentVO = new FlauntContentVO();
		try {
			flauntContentVO = flauntDAO.getContentDetails(contentId);
		
		} catch (DaoException dex) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, dex.getErrorVO());
		}
		return flauntContentVO;
	}

	@Override
	public int createContent(FlauntContentVO flauntContent) throws ServiceException {
		int i = 0;
		try {
			i = flauntDAO.createContent(flauntContent);
		} catch (DaoException dex) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, dex.getErrorVO());
		}
		return i;
	}

	@Override
	public int editContent(FlauntContentVO flauntContent) throws ServiceException {
		int i = 0;
		try {
			i = flauntDAO.editContent(flauntContent);
		} catch (DaoException dex) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, dex.getErrorVO());
		}
		return i;
	}



	@Override
	public int updateAssetType(AssetTypeVO assetType) throws ServiceException {
		String errorDescription;
		int editedRows;
		try {			
			editedRows = flauntDAO.updateAssetType(assetType);
		} catch (DaoException e) {
			errorDescription = "Internal error: " + e.getMessage();
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,
							ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
		}
		return editedRows;
	}



	@Override
	public int removeAssetType(String assetTypeId) throws ServiceException {
		String errorDescription;
		int deletedRows;
		try {			
			deletedRows = flauntDAO.removeAssetType(assetTypeId);
		} catch (DaoException e) {
			errorDescription = "Internal error: " + e.getMessage();
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,
							ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
		}
		return deletedRows;
	}



	@Override
	public int createAssetType(String assetType) throws ServiceException {
		String errorDescription;
		int insertedId;
		try {			
			insertedId = flauntDAO.createAssetType(assetType);
		} catch (DaoException e) {
			errorDescription = "Internal error: " + e.getMessage();
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,
							ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
		}
		return insertedId;
	}



	@Override
	public List<AssetTypeVO> getAssetTypes() throws ServiceException {
		String errorDescription;
		List<AssetTypeVO> assetTypeVOList;

		try {			
			assetTypeVOList = flauntDAO.getAssetTypes();
		} catch (DaoException e) {
			errorDescription = "Internal error: " + e.getMessage();
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,
							ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
		}
		return assetTypeVOList;
	}



	@Override
	public EditorialBriefVO getBrief(String briefId) throws ServiceException {
		EditorialBriefVO editorialBriefVO = new EditorialBriefVO();
		try {
			editorialBriefVO = flauntDAO.getBrief(briefId);
		
		} catch (DaoException dex) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, dex.getErrorVO());
		}
		return editorialBriefVO;
	}



	@Override
	public int editBrief(EditorialBriefVO brief) throws ServiceException {
		int i = 0;
		try {
			i = flauntDAO.editBrief(brief);
		} catch (DaoException dex) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, dex.getErrorVO());
		}
		return i;
	}



	@Override
	public int deleteBrief(String briefId) throws ServiceException {
		int deletedRows = 0;
		try {
		 deletedRows = flauntDAO.deleteBrief(briefId);
		} catch (DaoException dex) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,dex.getErrorVO());
		}
		return deletedRows;
	}



	@Override
	public int createBrief(EditorialBriefVO brief) throws ServiceException {
		int i = 0;
		try {
			i = flauntDAO.createBrief(brief);
		} catch (DaoException dex) {
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, dex.getErrorVO());
		}
		return i;
	}



	@Override
	public List<EditorialBriefVO> getBriefs() throws ServiceException {
		String errorDescription;
		List<EditorialBriefVO> briefVOList;

		try {			
			briefVOList = flauntDAO.getBriefs();
		} catch (DaoException e) {
			errorDescription = "Internal error: " + e.getMessage();
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,
							ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
		}
		return briefVOList;
	}



	@Override
	public List<FlauntTaskVO> getTasks() throws ServiceException {
		String errorDescription;
		List<FlauntTaskVO> taskVOList;

		try {			
			taskVOList = flauntDAO.getTasks();
		} catch (DaoException e) {
			errorDescription = "Internal error: " + e.getMessage();
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,	new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,
							ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
		}
		return taskVOList;
	}
	
} 
