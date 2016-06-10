package com.redwood.rp.flaunt.ws.contractor;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.redwood.rp.flaunt.vo.json.response.AssetTypeVO;
import com.redwood.rp.flaunt.vo.json.response.EditorialBriefVO;
import com.redwood.rp.flaunt.vo.json.response.FlauntContentVO;
import com.redwood.rp.flaunt.vo.json.response.FlauntTaskVO;

@Path("/")
@WebService
public interface FlauntProcessor {
	
	@GET
	@Path("/task/{taskId}")
	@Produces({MediaType.APPLICATION_JSON })
	public Response getTaskDetails(@PathParam("taskId") String taskId);
	
	@GET
	@Path("/tasks")
	@Produces({MediaType.APPLICATION_JSON })
	public Response getTasks();
	
	@POST
	@Path("/task")
	@Consumes({MediaType.APPLICATION_JSON })
	@Produces({MediaType.APPLICATION_JSON })
	public Response updateTaskDetails(FlauntTaskVO flauntTask);
	
	@DELETE
	@Path("/task/{taskId}")
	@Produces({MediaType.APPLICATION_JSON })
	public Response deleteTask(@PathParam("taskId") String taskId);
	
	@PUT
	@Path("/task")
	@Consumes({MediaType.APPLICATION_JSON })
	@Produces({MediaType.APPLICATION_JSON })
	public Response createTask(FlauntTaskVO flauntTask);
	
	
	@GET
	@Path("/briefs")
	@Produces({MediaType.APPLICATION_JSON })
	public Response getBriefs();
	
	@GET
	@Path("/brief/{briefId}")
	@Produces({MediaType.APPLICATION_JSON })
	public Response getBrief(@PathParam("briefId") String briefId);
	
	@POST
	@Path("/brief")
	@Consumes({MediaType.APPLICATION_JSON })
	@Produces({MediaType.APPLICATION_JSON })
	public Response updateBrief(EditorialBriefVO brief);
	
	@DELETE
	@Path("/brief/{briefId}")
	@Produces({MediaType.APPLICATION_JSON })
	public Response deleteBrief(@PathParam("briefId") String briefId);
	
	@PUT
	@Path("/brief")
	@Consumes({MediaType.APPLICATION_JSON })
	@Produces({MediaType.APPLICATION_JSON })
	public Response createBrief(EditorialBriefVO brief);
	
	
	@GET
	@Path("/content/{contentId}")
	@Produces({MediaType.APPLICATION_JSON })
	public Response getContentDetails(@PathParam("contentId") String contentId);
	
	@POST
	@Path("/content")
	@Consumes({MediaType.APPLICATION_JSON })
	@Produces({MediaType.APPLICATION_JSON })
	public Response updateContentDetails(FlauntContentVO flauntContent);
	
	@DELETE
	@Path("/content/{contentId}")
	@Produces({MediaType.APPLICATION_JSON })
	public Response deleteContent(@PathParam("contentId") String contentId);
	
	@PUT
	@Path("/content")
	@Consumes({MediaType.APPLICATION_JSON })
	@Produces({MediaType.APPLICATION_JSON })
	public Response createContent(FlauntContentVO flauntContent);
	
	@GET
	@Path("/assettypes")
	@Produces({MediaType.APPLICATION_JSON })
	public Response getAssetTypes();
	
	@POST
	@Path("/assettype")
	@Consumes({MediaType.APPLICATION_JSON })
	@Produces({MediaType.APPLICATION_JSON })
	public Response updateAssetType(AssetTypeVO assetType);
	
	@DELETE
	@Path("/assettype/{assetTypeId}")
	@Produces({MediaType.APPLICATION_JSON })
	public Response deleteAssetType(@PathParam("assetTypeId") String assetTypeId);
	
	@PUT
	@Path("/assettype/{assetType}")
	@Consumes({MediaType.APPLICATION_JSON })
	@Produces({MediaType.APPLICATION_JSON })
	public Response createAssetType(@PathParam("assetType") String assetType);

}
