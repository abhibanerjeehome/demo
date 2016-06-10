package com.redwood.rp.flaunt.das.dao;

import java.util.List;

import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.flaunt.bo.BiddingPropertyBO;
import com.redwood.rp.flaunt.bo.ContractInfo;
import com.redwood.rp.flaunt.vo.json.response.AssetTypeVO;
import com.redwood.rp.flaunt.vo.json.response.AudienceVO;
import com.redwood.rp.flaunt.vo.json.response.EditorialBriefVO;
import com.redwood.rp.flaunt.vo.json.response.FlauntContentVO;
import com.redwood.rp.flaunt.vo.json.response.FlauntTaskVO;
import com.redwood.rp.flaunt.vo.json.response.ObjectiveVO;

public interface FlauntDAO {
	
	public Integer totalCountOfLastQuery() throws DaoException;

	public int deleteTask(String taskId) throws DaoException;

	public int editTask(FlauntTaskVO flauntTask) throws DaoException;

	public FlauntTaskVO getTaskDetails(String taskId) throws DaoException;

	public int createTask(FlauntTaskVO flauntTask) throws DaoException;
	
	public int deleteContent(String contentId) throws DaoException;

	public int editContent(FlauntContentVO flauntContent) throws DaoException;

	public FlauntContentVO getContentDetails(String contentId) throws DaoException;

	public int createContent(FlauntContentVO flauntContent) throws DaoException;

	public int updateAssetType(AssetTypeVO assetType) throws DaoException;

	public int removeAssetType(String assetTypeId) throws DaoException;

	public int createAssetType(String assetType) throws DaoException;

	public List<AssetTypeVO> getAssetTypes() throws DaoException;

	public int createBrief(EditorialBriefVO brief) throws DaoException;

	public int deleteBrief(String briefId) throws DaoException;

	public int editBrief(EditorialBriefVO brief) throws DaoException;

	public EditorialBriefVO getBrief(String briefId) throws DaoException;

	public List<AudienceVO> getAudience() throws DaoException;

	public List<ObjectiveVO> getObjective() throws DaoException;

	public List<EditorialBriefVO> getBriefs() throws DaoException;

	public List<FlauntTaskVO> getTasks() throws DaoException;
	
}
