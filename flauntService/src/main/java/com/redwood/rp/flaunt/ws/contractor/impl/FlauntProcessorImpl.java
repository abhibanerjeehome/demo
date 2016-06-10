package com.redwood.rp.flaunt.ws.contractor.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.redwood.rp.core.base.ws.service.BaseRestService;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.flaunt.constant.UserServiceConstant;
import com.redwood.rp.flaunt.vo.json.response.AssetTypeVO;
import com.redwood.rp.flaunt.vo.json.response.AssetTypesResponseVO;
import com.redwood.rp.flaunt.vo.json.response.BriefsResponseVO;
import com.redwood.rp.flaunt.vo.json.response.EditorialBriefVO;
import com.redwood.rp.flaunt.vo.json.response.FlauntContentVO;
import com.redwood.rp.flaunt.vo.json.response.FlauntTaskVO;
import com.redwood.rp.flaunt.vo.json.response.GenericResponseVO;
import com.redwood.rp.flaunt.vo.json.response.TaskResponseVO;
import com.redwood.rp.flaunt.ws.contractor.FlauntProcessor;
import com.redwood.rp.flaunt.ws.manager.FlauntManager;
import com.redwood.rp.flaunt.ws.manager.UserManager;

@Named
public class FlauntProcessorImpl extends BaseRestService implements FlauntProcessor {
	

	@Inject
	private FlauntManager flauntManager;
	
	@Inject
	private UserManager userManager;

	public Response getTaskDetails(String taskId) {
		try {
			FlauntTaskVO flauntTask = flauntManager.getTaskDetails(taskId);
			return setResponseData(new GenericResponseVO(flauntTask));
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}


	public Response updateTaskDetails(FlauntTaskVO flauntTask) {
		try {
			flauntManager.updateTaskDetails(flauntTask);
			return setResponseData(new GenericResponseVO(UserServiceConstant.SUCCESS));	
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}


	public Response deleteTask(String taskId) {
		try {
			int deletedRows = flauntManager.deleteTask(taskId);  
			return setResponseData(new GenericResponseVO(deletedRows));
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}


	public Response createTask(FlauntTaskVO flauntTask) {
		try {
			int insertedId = flauntManager.createTask(flauntTask);  
			return setResponseData(new GenericResponseVO(insertedId));
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}
	
	public Response getContentDetails(String contentId) {
		try {
			FlauntContentVO flauntContent = flauntManager.getContentDetails(contentId);
			return setResponseData(new GenericResponseVO(flauntContent));
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}


	public Response updateContentDetails(FlauntContentVO flauntContent) {
		try {
			flauntManager.updateContentDetails(flauntContent);
			return setResponseData(new GenericResponseVO(UserServiceConstant.SUCCESS));	
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}


	public Response deleteContent(String contentId) {
		try {
			int deletedRows = flauntManager.deleteContent(contentId);  
			return setResponseData(new GenericResponseVO(deletedRows));
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}


	public Response createContent(FlauntContentVO flauntContent) {
		try {
			int insertedId = flauntManager.createContent(flauntContent);  
			return setResponseData(new GenericResponseVO(insertedId));
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}



	public Response getAssetTypes() {
		try {
			AssetTypesResponseVO responseJsonVO = flauntManager.getAssetTypes();  
			return setResponseData(responseJsonVO);
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}



	public Response updateAssetType(AssetTypeVO assetType) {
		try {
			flauntManager.updateAssetType(assetType);
			return setResponseData(new GenericResponseVO(UserServiceConstant.SUCCESS));	
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}



	public Response deleteAssetType(String assetTypeId) {
		try {
			int deletedRows = flauntManager.deleteAssetType(assetTypeId);  
			return setResponseData(new GenericResponseVO(deletedRows));
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}



	public Response createAssetType(String assetType) {
		try {
			int insertedId = flauntManager.createAssetType(assetType);  
			return setResponseData(new GenericResponseVO(insertedId));
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}

	public Response getBrief(String briefId) {
		try {
			EditorialBriefVO responseJsonVO = flauntManager.getBrief(briefId);  
			return setResponseData(new GenericResponseVO(responseJsonVO));
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}

	public Response updateBrief(EditorialBriefVO brief) {
		try {
			flauntManager.updateBrief(brief);
			return setResponseData(new GenericResponseVO(UserServiceConstant.SUCCESS));	
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}


	public Response deleteBrief(String briefId) {
		try {
			int deletedRows = flauntManager.deleteBrief(briefId);  
			return setResponseData(new GenericResponseVO(deletedRows));
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}


	public Response createBrief(EditorialBriefVO brief) {
		try {
			int insertedId = flauntManager.createBrief(brief);  
			return setResponseData(new GenericResponseVO(insertedId));
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}


	public Response getBriefs() {
		try {
			BriefsResponseVO responseJsonVO = flauntManager.getBriefs();  
			return setResponseData(responseJsonVO);
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}

	public Response getTasks() {
		try {
			TaskResponseVO responseJsonVO = flauntManager.getTasks();  
			return setResponseData(responseJsonVO);
		} catch (ServiceException e) {
			return setErrorInfoResponse(e);
		}
	}

}
