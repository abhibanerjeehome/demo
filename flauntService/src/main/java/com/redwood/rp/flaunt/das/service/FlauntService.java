package com.redwood.rp.flaunt.das.service;

import java.util.List;

import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.flaunt.vo.json.response.AssetTypeVO;
import com.redwood.rp.flaunt.vo.json.response.EditorialBriefVO;
import com.redwood.rp.flaunt.vo.json.response.FlauntContentVO;
import com.redwood.rp.flaunt.vo.json.response.FlauntTaskVO;

public interface FlauntService {

	public int deleteTask(String taskId) throws ServiceException;

	public FlauntTaskVO getTaskDetails(String taskId) throws ServiceException;

	public int createTask(FlauntTaskVO flauntTask) throws ServiceException;

	public int editTask(FlauntTaskVO flauntTask) throws ServiceException;	
	
	public int deleteContent(String contentId) throws ServiceException;

	public FlauntContentVO getContentDetails(String contentId) throws ServiceException;

	public int createContent(FlauntContentVO flauntContent) throws ServiceException;

	public int editContent(FlauntContentVO flauntContent) throws ServiceException;

	public int updateAssetType(AssetTypeVO assetType) throws ServiceException;

	public int removeAssetType(String assetTypeId) throws ServiceException;

	public int createAssetType(String assetType) throws ServiceException;

	public List<AssetTypeVO> getAssetTypes() throws ServiceException;

	public EditorialBriefVO getBrief(String briefId) throws ServiceException;

	public int editBrief(EditorialBriefVO brief) throws ServiceException;

	public int deleteBrief(String briefId) throws ServiceException;

	public int createBrief(EditorialBriefVO brief) throws ServiceException;

	public List<EditorialBriefVO> getBriefs() throws ServiceException;

	public List<FlauntTaskVO> getTasks() throws ServiceException;
}
