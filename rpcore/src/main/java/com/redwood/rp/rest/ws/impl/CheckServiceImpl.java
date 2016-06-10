package com.redwood.rp.rest.ws.impl;

import javax.inject.Named;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.redwood.rp.core.base.ws.service.BaseRestService;
import com.redwood.rp.rest.ws.CheckService;


@Named
public class CheckServiceImpl extends BaseRestService implements  CheckService{
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getPropertyDetails(){
		
		ResponseBuilder builder = Response.ok(request.getContextPath());
		builder.type("Application/json");
		return builder.build();
	}
}
