package com.redwood.rp.flaunt.das.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.redwood.rp.flaunt.das.dao.FlauntDAO;
import com.redwood.rp.flaunt.vo.json.response.AssetTypeVO;
import com.redwood.rp.flaunt.vo.json.response.AudienceVO;
import com.redwood.rp.flaunt.vo.json.response.EditorialBriefVO;
import com.redwood.rp.flaunt.vo.json.response.FlauntContentVO;
import com.redwood.rp.flaunt.vo.json.response.FlauntTaskVO;
import com.redwood.rp.flaunt.vo.json.response.ObjectiveVO;


public class FlauntDAOImpl extends AbstractJdbcDaoImpl implements FlauntDAO {

	private Logger mLogger = LoggerFactory.getLogger(FlauntDAOImpl.class.getName());
	private QueryDefinitionBean namedQueryBean;	

	public Integer totalCountOfLastQuery() throws DaoException{
		String  sqlStr = " select found_rows() as totalAmount";
		
		List<Map<String, Object>> resultList = executeJdbcQuery(sqlStr,null);
		if (resultList == null || resultList.size()==0) {
			return 0;
		} else {
			Map<String, Object> map =  resultList.get(0);
			Object globalPropIdObj = map.get("totalAmount");
			return globalPropIdObj==null?0:Integer.parseInt(globalPropIdObj.toString());
		}
	}
	
		
	public QueryDefinitionBean getNamedQueryBean() {
		return namedQueryBean;
	}

	public void setNamedQueryBean(QueryDefinitionBean namedQueryBean) {
		this.namedQueryBean = namedQueryBean;
	}

	@Override
	public int deleteTask(String taskId) throws DaoException {
		String sqlStr;
		Map<String, Object> paramMap = new HashMap<String, Object> ();
		int deletedRows = 0;
		try {
			sqlStr = namedQueryBean.getQueryByName("DELETE_TASK");			
						
			paramMap.put("TASK_ID", taskId);
			paramMap.put("STATUS", 0);
			deletedRows = executeJdbcUpdate(sqlStr, paramMap);				 
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(), e.getErrorVO());
		}		
		return deletedRows;
	}

	@Override
	public int editTask(FlauntTaskVO flauntTask) throws DaoException {
		String sqlStr;
		Map<String, Object> paramMap = new HashMap<String, Object> ();
		int editedRows = 0;
		try {
			sqlStr = namedQueryBean.getQueryByName("EDIT_TASK");
			
			paramMap.put("TASK_ID", flauntTask.getTaskId());
			paramMap.put("TITLE", flauntTask.getTitle());
			paramMap.put("ASSET_TYPE", flauntTask.getAssetType());
			paramMap.put("DESCRIPTION", flauntTask.getDescription());
			paramMap.put("SEO_INSTRUCTIONS", flauntTask.getSeoInstructions());
			paramMap.put("BRIEF", flauntTask.getBriefId());
			paramMap.put("PUBLISH_TIME", flauntTask.getPublishTime());
			paramMap.put("WORKING_TIME", flauntTask.getWorkingTime());
			paramMap.put("TEMPLATE_NAME", flauntTask.getTemplateName());
			paramMap.put("MIN_WORDS", flauntTask.getMinWords());
			paramMap.put("MAX_WORDS", flauntTask.getMaxWords());
			paramMap.put("STATUS", 1);
			paramMap.put("CONTENT_ID", flauntTask.getContentId());
			paramMap.put("TRACKING_ID", flauntTask.getTrackingId());
			paramMap.put("PRIORITY", flauntTask.getPriority());
			paramMap.put("REQUESTED_BY", flauntTask.getRequestedBy());
			paramMap.put("OWNER", flauntTask.getOwner());
			paramMap.put("ASSIGNED_TO", flauntTask.getAssignedTo());
			paramMap.put("AUTHOR", flauntTask.getAuthor());
			paramMap.put("UPDATED_BY", flauntTask.getUpdatedBy());
			paramMap.put("CREATED_BY", flauntTask.getCreatedDate());
			paramMap.put("START_DATE", flauntTask.getStartDate());
			paramMap.put("END_DATE", flauntTask.getEndDate());
			paramMap.put("PUBLISH_DATE", flauntTask.getPublishDate());
		
			editedRows = executeJdbcUpdate(sqlStr, paramMap);
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(), e.getErrorVO());
		}		
		return editedRows;
	}

	@Override
	public FlauntTaskVO getTaskDetails(String taskId) throws DaoException {
		if(taskId==null){
			String errorDescription = "task Id cannot be null";
			mLogger.error(MessageFormattor.errorFormat(
					UserDAOImpl.class.getName(), "getTaskDetails",
					ExceptionType.EXCEPTION_DAO, errorDescription));
			throw new DaoException(ExceptionType.EXCEPTION_DAO,
					new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
		}
		String sqlStr;
		try {
			sqlStr = namedQueryBean.getQueryByName("GET_TASK_BY_TASK_ID");
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(),e.getErrorVO());
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("TASK_ID", taskId);
		
		List<Map<String, Object>> resultList = executeJdbcQuery(sqlStr,paramMap);
		FlauntTaskVO flauntTask = new FlauntTaskVO();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd 'T' HH:mm:ss.SSS");
		if(resultList==null || resultList.size()==0){
			return null;
		}else{			
			for(Map<String, Object> map : resultList){
				Object id = map.get("taskId");
				Object title = map.get("title");
				Object assetType = map.get("assetTypeId");
				Object description = map.get("description");
				Object seo = map.get("seoInstructions");
				Object briefId = map.get("briefId");
				Object minWords = map.get("minWords");
				Object maxWords = map.get("maxWords");
				Object workingTime = map.get("workingTime");
				Object publishTime = map.get("publishTime");
				Object templateName = map.get("templateName");
				Object status = map.get("status");
				Object contentId = map.get("contentId");
				Object trackingId = map.get("trackingId");
				Object priority = map.get("priority");
				Object requestedBy = map.get("requestedBy");
				Object owner = map.get("owner");
				Object assignedTo = map.get("assignedTo");
				Object author = map.get("author");
				Object updatedBy = map.get("updatedBy");
				Object createdBy = map.get("createdBy");
				Object startDate = map.get("startDate");
				Object endDate = map.get("endDate");
				Object createdDate = map.get("createdDate");
				Object updatedDate = map.get("updatedDate");
				Object publishDate = map.get("publishDate");

				flauntTask.setAssignedTo(assignedTo==null?null:Integer.valueOf(assignedTo.toString()));
				flauntTask.setAuthor(author==null?null:Integer.valueOf(author.toString()));
				flauntTask.setContentId(contentId==null?null:Integer.valueOf(contentId.toString()));
				flauntTask.setAssetType(assetType==null?0:Integer.valueOf(assetType.toString()));
				flauntTask.setCreatedDate(createdDate==null?null:df.format((Date)createdDate));
				flauntTask.setDescription(description==null?"":description.toString());
				flauntTask.setSeoInstructions(seo==null?"":seo.toString());
				flauntTask.setBriefId(briefId==null?0:Integer.valueOf(briefId.toString()));
				flauntTask.setMinWords(minWords==null?null:Integer.valueOf(minWords.toString()));
				flauntTask.setMaxWords(maxWords==null?null:Integer.valueOf(maxWords.toString()));
				flauntTask.setTemplateName(templateName==null?null:templateName.toString());
				flauntTask.setWorkingTime(workingTime==null?null:Integer.valueOf(workingTime.toString()));
				flauntTask.setPublishTime(publishTime==null?null:Integer.valueOf(publishTime.toString()));
				flauntTask.setEndDate(endDate==null?null:df.format((Date)endDate));
				flauntTask.setOwner(owner==null?null:Integer.valueOf(owner.toString()));
				flauntTask.setPriority(priority==null?null:Integer.valueOf(priority.toString()));
				flauntTask.setPublishDate(publishDate==null?null:df.format((Date)publishDate));
				flauntTask.setRequestedBy(requestedBy==null?null:Integer.valueOf(requestedBy.toString()));
				flauntTask.setStartDate(startDate==null?null:df.format((Date)startDate));
				flauntTask.setStatus(status==null?null:Integer.valueOf(status.toString()));
				flauntTask.setTaskId(id==null?null:Integer.valueOf(id.toString()));
				flauntTask.setTitle(title==null?"":title.toString());
				flauntTask.setTrackingId(trackingId==null?null:Integer.valueOf(trackingId.toString()));
				flauntTask.setUpdatedBy(updatedBy==null?null:Integer.valueOf(updatedBy.toString()));
				flauntTask.setCreatedBy(createdBy==null?null:Integer.valueOf(createdBy.toString()));
				flauntTask.setUpdatedDate(updatedDate==null?null:df.format((Date)updatedDate));
			}
			return flauntTask;
		}
	}

	@Override
	public int createTask(FlauntTaskVO flauntTask) throws DaoException {
		String sqlStr;
		int insertedId;
		try {
			sqlStr = namedQueryBean.getQueryByName("CREATE_NEW_TASK");

				KeyHolder keyHolder = new GeneratedKeyHolder();
				MapSqlParameterSource paramMap =  new MapSqlParameterSource();
				
				paramMap.addValue("TITLE", flauntTask.getTitle());
				paramMap.addValue("DESCRIPTION", flauntTask.getDescription());
				paramMap.addValue("SEO_INSTRUCTIONS", flauntTask.getSeoInstructions());
				paramMap.addValue("ASSET_TYPE", flauntTask.getAssetType());
				paramMap.addValue("BRIEF", flauntTask.getBriefId());
				paramMap.addValue("TEMPLATE_NAME", flauntTask.getTemplateName());
				paramMap.addValue("MIN_WORDS", flauntTask.getMinWords());
				paramMap.addValue("MAX_WORDS", flauntTask.getMaxWords());
				paramMap.addValue("WORKING_TIME", flauntTask.getWorkingTime());
				paramMap.addValue("PUBLISH_TIME", flauntTask.getPublishTime());
				paramMap.addValue("STATUS", 1);
				paramMap.addValue("CONTENT_ID", flauntTask.getContentId());
				paramMap.addValue("TRACKING_ID", flauntTask.getTrackingId());
				paramMap.addValue("PRIORITY", flauntTask.getPriority());
				paramMap.addValue("REQUESTED_BY", flauntTask.getRequestedBy());
				paramMap.addValue("OWNER", flauntTask.getOwner());
				paramMap.addValue("ASSIGNED_TO", flauntTask.getAssignedTo());
				paramMap.addValue("AUTHOR", flauntTask.getAuthor());
				paramMap.addValue("UPDATED_BY", flauntTask.getUpdatedBy());
				paramMap.addValue("CREATED_BY", flauntTask.getCreatedBy());
				paramMap.addValue("START_DATE", flauntTask.getStartDate());
				paramMap.addValue("END_DATE", flauntTask.getEndDate());
				paramMap.addValue("PUBLISH_DATE", flauntTask.getPublishDate());
				insertedId = executeJdbcUpdate(sqlStr, paramMap, keyHolder);
				if (keyHolder.getKey()!=null) 
					insertedId = keyHolder.getKey().intValue();			 
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(), e.getErrorVO());
		}
		return insertedId;
	} 
	
	@Override
	public int deleteContent(String contentId) throws DaoException {
		String sqlStr;
		Map<String, Object> paramMap = new HashMap<String, Object> ();
		int deletedRows = 0;
		try {
			sqlStr = namedQueryBean.getQueryByName("DELETE_CONTENT");
						
			paramMap.put("CONTENT_ID", contentId);
			paramMap.put("STATUS", 0);
			deletedRows = executeJdbcUpdate(sqlStr, paramMap);				
				 
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(), e.getErrorVO());
		}		
		return deletedRows;
	}

	@Override
	public int editContent(FlauntContentVO flauntContent) throws DaoException {
		String sqlStr;
		Map<String, Object> paramMap = new HashMap<String, Object> ();
		int editedRows = 0;
		try {
			sqlStr = namedQueryBean.getQueryByName("EDIT_CONTENT");
			
			paramMap.put("TITLE", flauntContent.getTitle());
			paramMap.put("DESCRIPTION", flauntContent.getDescription());
			paramMap.put("STATUS", 1);
			paramMap.put("CONTENT_ID", flauntContent.getContentId());
			paramMap.put("OWNER", flauntContent.getOwner());
			paramMap.put("AUTHOR", flauntContent.getAuthor());
			paramMap.put("CONTENT_TYPE", flauntContent.getContentType());
			paramMap.put("CATEGORY", flauntContent.getCategory());
			paramMap.put("DOC_FORMAT", flauntContent.getDocumentFormat());
			paramMap.put("LAYOUT_SCHEMA", flauntContent.getFormattingSchema());
			paramMap.put("FULL_CONTENT", flauntContent.getFullContent());
			paramMap.put("PERSONAS", flauntContent.getPersonas());
			paramMap.put("RAW_CONTENT", flauntContent.getRawContent());
			paramMap.put("DESTINATION_ID", flauntContent.getDestination());
			paramMap.put("UPDATED_BY", flauntContent.getUpdatedBy());
			paramMap.put("CREATED_BY", flauntContent.getCreatedDate());
		
			editedRows = executeJdbcUpdate(sqlStr, paramMap);
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(), e.getErrorVO());
		}		
		return editedRows;
	}

	@Override
	public FlauntContentVO getContentDetails(String contentId) throws DaoException {
		if(contentId==null){
			String errorDescription = "content Id cannot be null";
			mLogger.error(MessageFormattor.errorFormat(
					UserDAOImpl.class.getName(), "getContentDetails",
					ExceptionType.EXCEPTION_DAO, errorDescription));
			throw new DaoException(ExceptionType.EXCEPTION_DAO,
					new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
		}
		String sqlStr;
		try {
			sqlStr = namedQueryBean.getQueryByName("GET_CONTENT_BY_ID");
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(),e.getErrorVO());
		}		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("CONTENT_ID", contentId);
		
		List<Map<String, Object>> resultList = executeJdbcQuery(sqlStr,paramMap);
		FlauntContentVO flauntContent = new FlauntContentVO();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd 'T' HH:mm:ss.SSS");
		if(resultList==null || resultList.size()==0){
			return null;
		}else{			
			for(Map<String, Object> map : resultList){
				Object id = map.get("contentId");
				Object title = map.get("title");
				Object description = map.get("description");

				Object status = map.get("status");
				Object owner = map.get("owner");
				Object author = map.get("author");
				Object category = map.get("category");
				Object docFormat = map.get("documentFormat");
				Object layoutSchema = map.get("formattingSchema");
				Object personas = map.get("personas");
				Object fullContent = map.get("fullContent");
				Object rawContent = map.get("rawContent");
				Object contentType = map.get("contentType");
				Object destinationId = map.get("destinationId");
				Object updatedBy = map.get("updatedBy");
				Object createdBy = map.get("createdBy");
				Object createdDate = map.get("createdDate");
				Object updatedDate = map.get("updatedDate");

				flauntContent.setAuthor(author==null?null:Integer.valueOf(author.toString()));
				flauntContent.setContentId(id==null?null:Integer.valueOf(contentId.toString()));
				flauntContent.setContentType(contentType==null?"":contentType.toString());
				flauntContent.setDescription(description==null?"":description.toString());				
				flauntContent.setDestination(destinationId==null?null:Integer.valueOf(destinationId.toString()));
				flauntContent.setOwner(owner==null?null:Integer.valueOf(owner.toString()));
				flauntContent.setStatus(status==null?null:Integer.valueOf(status.toString()));
				flauntContent.setCategory(category==null?"":category.toString());
				flauntContent.setFullContent(fullContent==null?"":fullContent.toString());
				flauntContent.setRawContent(rawContent==null?"":rawContent.toString());
				flauntContent.setDocumentFormat(docFormat==null?"":docFormat.toString());
				flauntContent.setPersonas(personas==null?"":personas.toString());
				flauntContent.setCategory(id==null?"":category.toString());
				flauntContent.setTitle(title==null?"":title.toString());
				flauntContent.setFormattingSchema(layoutSchema==null?"":layoutSchema.toString());
				flauntContent.setUpdatedBy(updatedBy==null?null:Integer.valueOf(updatedBy.toString()));
				flauntContent.setCreatedBy(createdBy==null?null:Integer.valueOf(createdBy.toString()));
				flauntContent.setCreatedDate(createdDate==null?null:df.format((Date)createdDate));
				flauntContent.setUpdatedDate(updatedDate==null?null:df.format((Date)updatedDate));
			}
			return flauntContent;
		}
	}

	@Override
	public int createContent(FlauntContentVO flauntContent) throws DaoException {
		String sqlStr;
		int insertedId;
		try {
			sqlStr = namedQueryBean.getQueryByName("CREATE_NEW_CONTENT");

				KeyHolder keyHolder = new GeneratedKeyHolder();
				MapSqlParameterSource paramMap =  new MapSqlParameterSource();
				
				paramMap.addValue("TITLE", flauntContent.getTitle());
				paramMap.addValue("DESCRIPTION", flauntContent.getDescription());
				paramMap.addValue("STATUS", 1);
				paramMap.addValue("CONTENT_ID", flauntContent.getContentId());
				paramMap.addValue("FULL_CONTENT", flauntContent.getFullContent());
				paramMap.addValue("OWNER", flauntContent.getOwner());
				paramMap.addValue("AUTHOR", flauntContent.getAuthor());
				paramMap.addValue("CONTENT_TYPE", flauntContent.getContentType());
				paramMap.addValue("UPDATED_BY", flauntContent.getUpdatedBy());
				paramMap.addValue("CREATED_BY", flauntContent.getCreatedBy());
				paramMap.addValue("CREATED_DATE", new Date());
				paramMap.addValue("CATEGORY", flauntContent.getCategory());
				paramMap.addValue("DOC_FORMAT", flauntContent.getDocumentFormat());
				paramMap.addValue("LAYOUT_SCHEMA", flauntContent.getFormattingSchema());
				paramMap.addValue("PERSONAS", flauntContent.getPersonas());
				paramMap.addValue("RAW_CONTENT", flauntContent.getRawContent());
				paramMap.addValue("DESTINATION_ID", flauntContent.getDestination());				
				
				insertedId = executeJdbcUpdate(sqlStr, paramMap, keyHolder);
				if (keyHolder.getKey()!=null) 
					insertedId = keyHolder.getKey().intValue();			 
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(), e.getErrorVO());
		}		
		return insertedId;
	}

	@Override
	public int updateAssetType(AssetTypeVO assetType) throws DaoException {
		String sqlStr;

		Map<String, Object> paramMap = new HashMap<String, Object> ();
		int editedRows = 0;
		try {
			sqlStr = namedQueryBean.getQueryByName("EDIT_ASSET_TYPE");
			paramMap.put("ASSET_TYPE_ID", assetType.getAssetTypeId());
			paramMap.put("ASSET_TYPE", assetType.getAssetType());
			paramMap.put("STATUS", assetType.getStatus());
		
			editedRows = executeJdbcUpdate(sqlStr, paramMap);
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(), e.getErrorVO());
		}		
		return editedRows;
	}

	@Override
	public int removeAssetType(String assetTypeId) throws DaoException {
		String sqlStr;
		String[] assetTypeArr = new String[20];
		Map<String, Object> paramMap = new HashMap<String, Object> ();
		int deletedRows = 0;
		try {
			assetTypeArr = assetTypeId.split(",");
			sqlStr = namedQueryBean.getQueryByName("REMOVE_ASSET_TYPES");
			
			for (String assetType : assetTypeArr) {				
				paramMap.put("ASSET_TYPE_ID", assetType);
				paramMap.put("STATUS", "0");
				executeJdbcUpdate(sqlStr, paramMap);
				deletedRows++;
			}		 
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(), e.getErrorVO());
		}		
		return deletedRows;
	}

	@Override
	public int createAssetType(String assetType) throws DaoException {
		String sqlStr;
		String[] assetTypeArr = new String[20];
		List<Long> insertedIds = new ArrayList<Long>();
		try {
			assetTypeArr = assetType.split(",");
			sqlStr = namedQueryBean.getQueryByName("CREATE_ASSET_TYPES");
			
			for (String newAssetType : assetTypeArr) {
				KeyHolder keyHolder = new GeneratedKeyHolder();
				MapSqlParameterSource paramMap =  new MapSqlParameterSource();
				paramMap.addValue("ASSET_TYPE", newAssetType);
				paramMap.addValue("STATUS", "1");
				executeJdbcUpdate(sqlStr, paramMap, keyHolder);
				insertedIds.add((Long)keyHolder.getKey());
			}		 
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(), e.getErrorVO());
		}		
		return insertedIds.indexOf(0);
	}

	@Override
	public List<AssetTypeVO> getAssetTypes() throws DaoException {
		String sqlStr;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<AssetTypeVO> assetTypeVOList = new ArrayList<AssetTypeVO>();
		try {
			sqlStr = namedQueryBean.getQueryByName("GET_ASSET_TYPES");
			
			List<Map<String, Object>> resultList = executeJdbcQuery(sqlStr,paramMap);
			if (resultList==null || resultList.size()==0){
				return null;
			} else {
				for (Map<String, Object> map : resultList) {
					AssetTypeVO assetTypeVO = new AssetTypeVO();
					assetTypeVO.setAssetTypeId((Integer) map.get("assetTypeId"));
					assetTypeVO.setAssetType(map.get("assetType").toString());
					assetTypeVO.setStatus((Integer) map.get("status"));
					
					assetTypeVOList.add(assetTypeVO);
				}
			}				
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(),e.getErrorVO());
		}
		return assetTypeVOList;
	}
	
	@Override
	public List<AudienceVO> getAudience() throws DaoException {
		String sqlStr;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<AudienceVO> audienceVOList = new ArrayList<AudienceVO>();
		try {
			sqlStr = namedQueryBean.getQueryByName("GET_AUDIENCE");
			
			List<Map<String, Object>> resultList = executeJdbcQuery(sqlStr,paramMap);
			if (resultList==null || resultList.size()==0){
				return null;
			} else {
				for (Map<String, Object> map : resultList) {
					AudienceVO assetTypeVO = new AudienceVO();
					assetTypeVO.setAudienceId((Integer) map.get("audienceId"));
					assetTypeVO.setAudience(map.get("audience").toString());
					assetTypeVO.setStatus((Integer) map.get("status"));
					
					audienceVOList.add(assetTypeVO);
				}
			}				
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(),e.getErrorVO());
		}
		return audienceVOList;
	}
	
	@Override
	public List<ObjectiveVO> getObjective() throws DaoException {
		String sqlStr;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<ObjectiveVO> ObjectiveVOList = new ArrayList<ObjectiveVO>();
		try {
			sqlStr = namedQueryBean.getQueryByName("GET_OBJECTIVE");
			
			List<Map<String, Object>> resultList = executeJdbcQuery(sqlStr,paramMap);
			if (resultList==null || resultList.size()==0){
				return null;
			} else {
				for (Map<String, Object> map : resultList) {
					ObjectiveVO assetTypeVO = new ObjectiveVO();
					assetTypeVO.setObjectiveId((Integer) map.get("objectiveId"));
					assetTypeVO.setObjective(map.get("objective").toString());
					assetTypeVO.setStatus((Integer) map.get("status"));
					
					ObjectiveVOList.add(assetTypeVO);
				}
			}				
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(),e.getErrorVO());
		}
		return ObjectiveVOList;
	}


	@Override
	public int createBrief(EditorialBriefVO brief) throws DaoException {
		String sqlStr;
		int insertedId;
		try {
			sqlStr = namedQueryBean.getQueryByName("CREATE_NEW_BRIEF");

				KeyHolder keyHolder = new GeneratedKeyHolder();
				MapSqlParameterSource paramMap =  new MapSqlParameterSource();
				
				paramMap.addValue("NAME", brief.getBriefName());
				paramMap.addValue("AUDIENCE_DESC", brief.getAudienceDescription());
				paramMap.addValue("STATUS", 1);
				paramMap.addValue("OBJECTIVE", brief.getObjective());
				paramMap.addValue("OBJ_DESC", brief.getObjectiveDescription());
				paramMap.addValue("SOURCING", brief.getSourcingReferences());
				paramMap.addValue("UPDATED_BY", brief.getUpdatedBy());
				paramMap.addValue("CREATED_BY", brief.getCreatedBy());
				paramMap.addValue("AUDIENCE", brief.getTargetAudience());				
				
				insertedId = executeJdbcUpdate(sqlStr, paramMap, keyHolder);
				if (keyHolder.getKey()!=null) 
					insertedId = keyHolder.getKey().intValue();			 
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(), e.getErrorVO());
		}		
		return insertedId;
	}


	@Override
	public int deleteBrief(String briefId) throws DaoException {
		String sqlStr;
		Map<String, Object> paramMap = new HashMap<String, Object> ();
		int deletedRows = 0;
		try {
			sqlStr = namedQueryBean.getQueryByName("DELETE_BRIEF");
						
			paramMap.put("BRIEF_ID", briefId);
			paramMap.put("STATUS", 0);
			deletedRows = executeJdbcUpdate(sqlStr, paramMap);				
				 
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(), e.getErrorVO());
		}		
		return deletedRows;
	}


	@Override
	public int editBrief(EditorialBriefVO brief) throws DaoException {
		String sqlStr;
		Map<String, Object> paramMap = new HashMap<String, Object> ();
		int editedRows = 0;
		try {
			sqlStr = namedQueryBean.getQueryByName("EDIT_BRIEF");
			
			paramMap.put("NAME", brief.getBriefName());
			paramMap.put("AUDIENCE_DESC", brief.getAudienceDescription());
			paramMap.put("STATUS", 1);
			paramMap.put("BRIEF_ID", brief.getBriefId());
			paramMap.put("SOURCING", brief.getSourcingReferences());
			paramMap.put("OBJ_DESC", brief.getObjectiveDescription());
			paramMap.put("OBJECTIVE", brief.getObjective());
			paramMap.put("AUDIENCE", brief.getTargetAudience());
			paramMap.put("UPDATED_BY", brief.getUpdatedBy());
			paramMap.put("CREATED_BY", brief.getCreatedBy());
		
			editedRows = executeJdbcUpdate(sqlStr, paramMap);
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(), e.getErrorVO());
		}		
		return editedRows;
	}


	@Override
	public EditorialBriefVO getBrief(String briefId) throws DaoException {
		if(briefId == null){
			String errorDescription = "brief Id missing";
			mLogger.error(MessageFormattor.errorFormat(
					UserDAOImpl.class.getName(), "getBrief",
					ExceptionType.EXCEPTION_DAO, errorDescription));
			throw new DaoException(ExceptionType.EXCEPTION_DAO,
					new ErrorVO(ErrorDescription.ERR_CD_DAO_QUERY,ErrorDescription.ERR_MSG_DAO_QUERY, errorDescription));
		}
		String sqlStr;
		try {
			sqlStr = namedQueryBean.getQueryByName("GET_BRIEF_BY_ID");
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(),e.getErrorVO());
		}		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("BRIEF_ID", briefId);
		
		List<Map<String, Object>> resultList = executeJdbcQuery(sqlStr,paramMap);
		EditorialBriefVO brief = new EditorialBriefVO();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd 'T' HH:mm:ss.SSS");
		if(resultList==null || resultList.size()==0){
			return null;
		}else{			
			for(Map<String, Object> map : resultList){
				Object name = map.get("name");
				Object audienceDesc = map.get("audienceDescription");
				Object objective = map.get("objectiveId");
				Object objectiveDesc = map.get("objectiveDescription");
				Object audience = map.get("audienceId");
				Object sourcing = map.get("sourcing");
				Object id = map.get("briefId");
				Object status = map.get("status");				
				Object updatedBy = map.get("updatedBy");
				Object createdBy = map.get("createdBy");
				Object createdDate = map.get("createdDate");
				Object updatedDate = map.get("updatedDate");
				
				brief.setStatus(status==null?null:Integer.valueOf(status.toString()));
				brief.setBriefId(id==null?null:Integer.valueOf(id.toString()));
				brief.setTargetAudience(audience==null?null:Integer.valueOf(audience.toString()));
				brief.setBriefName(name==null?"":name.toString());
				brief.setObjective(objective==null?null:Integer.valueOf(objective.toString()));
				brief.setSourcingReferences(sourcing==null?null:sourcing.toString());
				brief.setAudienceDescription(audienceDesc==null?null:audienceDesc.toString());
				brief.setObjectiveDescription(objectiveDesc==null?null:objectiveDesc.toString());
				brief.setUpdatedBy(updatedBy==null?null:Integer.valueOf(updatedBy.toString()));
				brief.setCreatedBy(createdBy==null?null:Integer.valueOf(createdBy.toString()));
				brief.setCreatedDate(createdDate==null?null:df.format((Date)createdDate));
				brief.setUpdatedDate(updatedDate==null?null:df.format((Date)updatedDate));
			}
			return brief;
		}
	}


	@Override
	public List<EditorialBriefVO> getBriefs() throws DaoException {
		String sqlStr;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<EditorialBriefVO> briefVOList = new ArrayList<EditorialBriefVO>();
		try {
			sqlStr = namedQueryBean.getQueryByName("GET_BRIEFS");
			
			List<Map<String, Object>> resultList = executeJdbcQuery(sqlStr,paramMap);
			if (resultList==null || resultList.size()==0){
				return null;
			} else {
				for (Map<String, Object> map : resultList) {
					EditorialBriefVO briefVO = new EditorialBriefVO();
					briefVO.setBriefId((Integer) map.get("briefId"));
					briefVO.setBriefName(map.get("name").toString());
					
					briefVOList.add(briefVO);
				}
			}				
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(),e.getErrorVO());
		}
		return briefVOList;
	}


	@Override
	public List<FlauntTaskVO> getTasks() throws DaoException {
		String sqlStr;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<FlauntTaskVO> taskVOList = new ArrayList<FlauntTaskVO>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd 'T' HH:mm:ss.SSS");
		try {
			sqlStr = namedQueryBean.getQueryByName("GET_TASKS");
			
			List<Map<String, Object>> resultList = executeJdbcQuery(sqlStr,paramMap);
			if (resultList==null || resultList.size()==0){
				return null;
			} else {
				for (Map<String, Object> map : resultList) {
					FlauntTaskVO taskVO = new FlauntTaskVO();
					taskVO.setTaskId((Integer) map.get("taskId"));
					taskVO.setTitle(map.get("title").toString());
					taskVO.setDescription(map.get("description").toString());
					taskVO.setTemplateName(map.get("templateName")==null?null:map.get("templateName").toString());
					taskVO.setAssignedTo((Integer) map.get("assignedTo"));
					taskVO.setBriefId((Integer) map.get("briefId"));
					taskVO.setCreatedBy((Integer) map.get("createdBy"));
					taskVO.setCreatedDate(map.get("createdDate")==null?null:df.format((Date)map.get("createdDate")));
					
					taskVOList.add(taskVO);
				}
			}				
		} catch (UtilityException e) {
			throw new DaoException(e.getErrorVO().getErrorType(),e.getErrorVO());
		}
		return taskVOList;
	}	
}
