package com.redwood.rp.flaunt.ws.manager;

import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.flaunt.vo.json.response.AssetTypeVO;
import com.redwood.rp.flaunt.vo.json.response.AssetTypesResponseVO;
import com.redwood.rp.flaunt.vo.json.response.BriefsResponseVO;
import com.redwood.rp.flaunt.vo.json.response.EditorialBriefVO;
import com.redwood.rp.flaunt.vo.json.response.FlauntContentVO;
import com.redwood.rp.flaunt.vo.json.response.FlauntTaskVO;
import com.redwood.rp.flaunt.vo.json.response.TaskResponseVO;

public interface FlauntManager {
	
	public FlauntTaskVO getTaskDetails(String taskId) throws ServiceException;
	
	public int deleteTask(String taskId) throws ServiceException;

	public int createTask(FlauntTaskVO flauntTask) throws ServiceException;

	public void updateTaskDetails(FlauntTaskVO flauntTask) throws ServiceException;
	
	public FlauntContentVO getContentDetails(String contentId) throws ServiceException;
	
	public int deleteContent(String contentId) throws ServiceException;

	public int createContent(FlauntContentVO flauntContent) throws ServiceException;

	public void updateContentDetails(FlauntContentVO flauntTask) throws ServiceException;

	public int updateAssetType(AssetTypeVO assetType) throws ServiceException;

	public int deleteAssetType(String assetTypeId) throws ServiceException;

	public int createAssetType(String assetType) throws ServiceException;

	public AssetTypesResponseVO getAssetTypes() throws ServiceException;

	public EditorialBriefVO getBrief(String briefId) throws ServiceException;

	public void updateBrief(EditorialBriefVO brief) throws ServiceException;

	public int deleteBrief(String briefId) throws ServiceException;

	public int createBrief(EditorialBriefVO brief) throws ServiceException;

	public BriefsResponseVO getBriefs() throws ServiceException;

	public TaskResponseVO getTasks() throws ServiceException;
	
}
