package com.redwood.rp.rest.ws;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@WebService
@Path("/checkService")
public interface CheckService {
	@GET
	@Path("/")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getPropertyDetails();
}
