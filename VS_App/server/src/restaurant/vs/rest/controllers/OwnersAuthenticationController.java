/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.vs.rest.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import restaurant.vs.dao.OwnersDAO;
import restaurant.vs.exception.AppException;
import restaurant.vs.model.Owners;

/**
 *
 * @author goud
 */
@Path("/ownerscheck")
@Api(tags= {"ownerscheck"})
public class OwnersAuthenticationController {
        @GET
	@Path("/{email}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation (value="Find One",
		notes = "Finds customers by it's id")
	@ApiResponses(value = {
		@ApiResponse (code=200, message="Success"),
		@ApiResponse (code=404, message="Not Found please find"),
		@ApiResponse (code=500, message="Internal Server Error")
	})
	public Owners check(@PathParam("email") String email,@PathParam("password") String password) {
		try {
			OwnersDAO wdao = new OwnersDAO();
			return wdao.checkAuthentication(email,password);
		} catch (AppException e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
           
}
