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

import com.redwood.rp.flaunt.vo.json.request.AppAuthorizeVO;
import com.redwood.rp.flaunt.vo.json.request.UpdatePasswordRequestVO;
import com.redwood.rp.flaunt.vo.json.request.UserDetailsRequestVO;
import com.redwood.rp.flaunt.vo.json.response.RolesVO;
import com.redwood.rp.flaunt.vo.json.response.UserVO;


/**
 * 		Sample 1. getUserById is used to demonstrate HTTP 'GET' method.
 * 
 * 			URL: http://localhost:8080/securityService/services/sample/user/abhi   or
 *               http://localhost:8080/securityService-0.0.1-SNAPSHOT/services/sample/user/abhi
 * 			Method: GET             
 *
 */
@Path("/user")
@WebService
public interface UserProcessor {		
@GET
@Path("/roles")
@Produces({MediaType.APPLICATION_JSON })
public Response getRoles();

@POST
@Path("/roles")
@Consumes({MediaType.APPLICATION_JSON })
@Produces({MediaType.APPLICATION_JSON })
public Response editRoles(RolesVO rolesVO);

@PUT
@Path("/roles/{newRolesToAdd}")
@Produces({MediaType.APPLICATION_JSON })
public Response createRoles(@PathParam("newRolesToAdd") String newRolesToAdd);

@DELETE
@Path("/roles/{roleIds}")
@Produces({MediaType.APPLICATION_JSON })
public Response removeRoles(@PathParam("roleIds") String roleIds);

@GET
@Path("/list/{roleIds}")
@Produces({MediaType.APPLICATION_JSON })
public Response getUserList(@PathParam("roleIds") String roleIds);

@GET
@Path("/profile")
@Produces({MediaType.APPLICATION_JSON })
public Response getUser();

@POST
@Path("/profile")
@Consumes({MediaType.APPLICATION_JSON })
@Produces({MediaType.APPLICATION_JSON })
public Response editUser(UserVO userVO);

@PUT
@Path("/profile")
@Produces({MediaType.APPLICATION_JSON })
public Response createUser(UserVO newUserToAdd);

@DELETE
@Path("/profile/{userIds}")
@Produces({MediaType.APPLICATION_JSON })
public Response removeUser(@PathParam("userIds") String userIds);

@POST
@Path("/detail")
@Consumes({MediaType.APPLICATION_JSON })
@Produces({MediaType.APPLICATION_JSON })
public Response getUserById(UserDetailsRequestVO userDetailsRequestVO);

@POST
@Path("/pwd/update")
@Consumes({MediaType.APPLICATION_JSON })
@Produces({MediaType.APPLICATION_JSON })
public Response updatePassword(UpdatePasswordRequestVO updatePasswordRequestVO);

@POST
@Path("/pwd/reset")
@Consumes({MediaType.APPLICATION_JSON })
@Produces({MediaType.APPLICATION_JSON })
public Response updateForgottenPassword(UpdatePasswordRequestVO updatePasswordRequestVO);

@POST
@Path("/pwd/forgot")
@Consumes({MediaType.APPLICATION_JSON })
@Produces({MediaType.APPLICATION_JSON })
public Response forgotPassword(UpdatePasswordRequestVO updatePasswordRequestVO);


@POST
@Path("/addpermission")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public Response addUserPermission(AppAuthorizeVO appAuthorizeVO);

}

