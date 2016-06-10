package com.redwood.rp.flaunt.ws.manager.impl;

import static com.redwood.rp.flaunt.constant.UserServiceConstant.REQUEST_EXCEPTION;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.flaunt.bo.SaveSearch;
import com.redwood.rp.flaunt.bo.SaveSearchResponse;
import com.redwood.rp.flaunt.das.service.FlauntService;
import com.redwood.rp.flaunt.das.service.SavedSearchService;
import com.redwood.rp.flaunt.vo.json.response.AssetTypeVO;
import com.redwood.rp.flaunt.vo.json.response.AssetTypesResponseVO;
import com.redwood.rp.flaunt.vo.json.response.BriefsResponseVO;
import com.redwood.rp.flaunt.vo.json.response.EditorialBriefVO;
import com.redwood.rp.flaunt.vo.json.response.FlauntContentVO;
import com.redwood.rp.flaunt.vo.json.response.FlauntTaskVO;
import com.redwood.rp.flaunt.vo.json.response.RolesResponseVO;
import com.redwood.rp.flaunt.vo.json.response.RolesVO;
import com.redwood.rp.flaunt.vo.json.response.TaskResponseVO;
import com.redwood.rp.flaunt.ws.manager.FlauntManager;

@Named
public class FlauntManagerImpl implements FlauntManager{

	
	@Inject
	private FlauntService flauntService;

	
	@Override
	public FlauntTaskVO getTaskDetails(String taskId) throws ServiceException {
		
		FlauntTaskVO flauntTask = new FlauntTaskVO();
		try {
			flauntTask = flauntService.getTaskDetails(taskId);
			
		} catch (Exception e) {
			ErrorVO errorVO = new ErrorVO(ErrorDescription.SEVERITY_CODE_FATAL,
					ErrorDescription.ERR_MSG_OPERATION, REQUEST_EXCEPTION);
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
		}
		return flauntTask;
	}

	@Override
	public int deleteTask(String taskId) throws ServiceException {
		if(StringUtils.isEmpty(taskId)) {
			String errMsg = "Task ID not provided";
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrCd("US121");
			errorVO.setErrMsg(errMsg);
			throw new ServiceException(errMsg, errorVO);
		}
		int deletedRows = flauntService.deleteTask(taskId);
		return deletedRows;
	}

	@Override
	public int createTask(FlauntTaskVO flauntTask) throws ServiceException {
		if(flauntTask==null) {
			String errMsg = "New Task details not provided";
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrCd("US122");
			errorVO.setErrMsg(errMsg);
			throw new ServiceException(errMsg, errorVO);
		}
		int insertedIds = flauntService.createTask(flauntTask);
		return insertedIds;
	}

	@Override
	public void updateTaskDetails(FlauntTaskVO flauntTask)
			throws ServiceException {
		if(flauntTask == null) {
			String errMsg = "Task details not provided";
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrCd("US121");
			errorVO.setErrMsg(errMsg);
			throw new ServiceException(errMsg, errorVO);
		}
		int editedRows = flauntService.editTask(flauntTask);
		//return editedRows;
	}
	
	@Override
	public FlauntContentVO getContentDetails(String contentId) throws ServiceException {
		
		FlauntContentVO flauntContent = new FlauntContentVO();
		try {
			flauntContent = flauntService.getContentDetails(contentId);
			
		} catch (Exception e) {
			ErrorVO errorVO = new ErrorVO(ErrorDescription.SEVERITY_CODE_FATAL,
					ErrorDescription.ERR_MSG_OPERATION, REQUEST_EXCEPTION);
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
		}
		return flauntContent;
	}

	@Override
	public int deleteContent(String contentId) throws ServiceException {
		if(StringUtils.isEmpty(contentId)) {
			String errMsg = "Content ID not provided";
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrCd("US121");
			errorVO.setErrMsg(errMsg);
			throw new ServiceException(errMsg, errorVO);
		}
		int deletedRows = flauntService.deleteContent(contentId);
		return deletedRows;
	}

	@Override
	public int createContent(FlauntContentVO flauntContent) throws ServiceException {
		if(flauntContent==null) {
			String errMsg = "New Task details not provided";
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrCd("US122");
			errorVO.setErrMsg(errMsg);
			throw new ServiceException(errMsg, errorVO);
		}
		int insertedIds = flauntService.createContent(flauntContent);
		return insertedIds;
	}

	@Override
	public void updateContentDetails(FlauntContentVO flauntContent)
			throws ServiceException {
		if(flauntContent == null) {
			String errMsg = "Content details not provided";
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrCd("US123");
			errorVO.setErrMsg(errMsg);
			throw new ServiceException(errMsg, errorVO);
		}
		int editedRows = flauntService.editContent(flauntContent);
		//return editedRows;
	}

	@Override
	public int updateAssetType(AssetTypeVO assetType) throws ServiceException {
		if(assetType == null) {
			String errMsg = "Role Names not provided";
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrCd("US120");
			errorVO.setErrMsg(errMsg);
			throw new ServiceException(errMsg, errorVO);
		}
		int editedRows = flauntService.updateAssetType(assetType);
		return editedRows;
		
	}

	@Override
	public int deleteAssetType(String assetTypeId) throws ServiceException {
		if(StringUtils.isEmpty(assetTypeId)) {
			String errMsg = "New Role Names not provided";
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrCd("US120");
			errorVO.setErrMsg(errMsg);
			throw new ServiceException(errMsg, errorVO);
		}
		int deletedRows = flauntService.removeAssetType(assetTypeId);
		return deletedRows;
	}

	@Override
	public int createAssetType(String assetType) throws ServiceException {
		if(StringUtils.isEmpty(assetType)) {
			String errMsg = "New Asset Type not provided";
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrCd("US120");
			errorVO.setErrMsg(errMsg);
			throw new ServiceException(errMsg, errorVO);
		}
		int insertedIds = flauntService.createAssetType(assetType);
		return insertedIds;
	}

	@Override
	public AssetTypesResponseVO getAssetTypes() throws ServiceException {
		List<AssetTypeVO> assetTypeVOList = new ArrayList<AssetTypeVO>();
		AssetTypesResponseVO assetTypesResponseVO = new AssetTypesResponseVO();
		try {
			assetTypeVOList = flauntService.getAssetTypes();
			
		} catch (Exception e) {
			ErrorVO errorVO = new ErrorVO(ErrorDescription.SEVERITY_CODE_FATAL,
					ErrorDescription.ERR_MSG_OPERATION, REQUEST_EXCEPTION);
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
		}
		assetTypesResponseVO.setRolesVO(assetTypeVOList);
		return assetTypesResponseVO;
	}

	@Override
	public EditorialBriefVO getBrief(String briefId) throws ServiceException {
		EditorialBriefVO brief = new EditorialBriefVO();
		try {
			brief = flauntService.getBrief(briefId);
			
		} catch (Exception e) {
			ErrorVO errorVO = new ErrorVO(ErrorDescription.SEVERITY_CODE_FATAL,
					ErrorDescription.ERR_MSG_OPERATION, REQUEST_EXCEPTION);
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
		}
		return brief;
	}

	@Override
	public void updateBrief(EditorialBriefVO brief) throws ServiceException {
		if(brief == null) {
			String errMsg = "Editorial Brief details not provided";
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrCd("US123");
			errorVO.setErrMsg(errMsg);
			throw new ServiceException(errMsg, errorVO);
		}
		int editedRows = flauntService.editBrief(brief);
		
	}

	@Override
	public int deleteBrief(String briefId) throws ServiceException {
		if(StringUtils.isEmpty(briefId)) {
			String errMsg = "Brief ID not provided";
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrCd("US121");
			errorVO.setErrMsg(errMsg);
			throw new ServiceException(errMsg, errorVO);
		}
		int deletedRows = flauntService.deleteBrief(briefId);
		return deletedRows;
	}

	@Override
	public int createBrief(EditorialBriefVO brief) throws ServiceException {
		if(brief==null) {
			String errMsg = "New Brief details not provided";
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrCd("US122");
			errorVO.setErrMsg(errMsg);
			throw new ServiceException(errMsg, errorVO);
		}
		int insertedIds = flauntService.createBrief(brief);
		return insertedIds;
	}

	@Override
	public BriefsResponseVO getBriefs() throws ServiceException {
		List<EditorialBriefVO> briefVOList = new ArrayList<EditorialBriefVO>();
		BriefsResponseVO briefsResponseVO = new BriefsResponseVO();
		try {
			briefVOList = flauntService.getBriefs();
			
		} catch (Exception e) {
			ErrorVO errorVO = new ErrorVO(ErrorDescription.SEVERITY_CODE_FATAL,
					ErrorDescription.ERR_MSG_OPERATION, REQUEST_EXCEPTION);
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
		}
		briefsResponseVO.setBriefsVO(briefVOList);
		return briefsResponseVO;
	}

	@Override
	public TaskResponseVO getTasks() throws ServiceException {
		List<FlauntTaskVO> taskVOList = new ArrayList<FlauntTaskVO>();
		TaskResponseVO tasksResponseVO = new TaskResponseVO();
		try {
			taskVOList = flauntService.getTasks();
			
		} catch (Exception e) {
			ErrorVO errorVO = new ErrorVO(ErrorDescription.SEVERITY_CODE_FATAL,
					ErrorDescription.ERR_MSG_OPERATION, REQUEST_EXCEPTION);
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
		}
		tasksResponseVO.setTasksVO(taskVOList);
		return tasksResponseVO;
	}
}
