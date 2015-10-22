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
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import restaurant.vs.dao.OwnersDAO;
import restaurant.vs.exception.AppException;
import restaurant.vs.model.Owners;

/**
 *
 * @author goud
 */
@Path("/owners")
@Api(tags= {"owners"})
public class OwnersController {
    	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation (value="Find All",
			notes = "Finds all Owners in the system")
	@ApiResponses(value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=500, message="Internal Server Error")
	})
	public List<Owners> findAll () {
		try {
			OwnersDAO wdao = new OwnersDAO();
			return wdao.fetchAll();
		} catch (AppException e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation (value="Find One",
		notes = "Finds customers by it's id")
	@ApiResponses(value = {
		@ApiResponse (code=200, message="Success"),
		@ApiResponse (code=404, message="Not Found please find"),
		@ApiResponse (code=500, message="Internal Server Error")
	})
	public Owners findOne(@PathParam("id") int id) {
		try {
			OwnersDAO wdao = new OwnersDAO();
			Owners o =  wdao.fetchOne(id);
			if(o == null) {
				throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
			else {
				return o;
			}
		} catch (AppException e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
        @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation (value="Authentication",
		notes = "Create an employee")
	@ApiResponses(value = {
		@ApiResponse (code=200, message="Success"),
		@ApiResponse (code=500, message="Internal Server Error")
	})
	public Owners check(Owners o) {
		try {
			OwnersDAO wdao = new OwnersDAO();
			return wdao.checkAuthentication(o.getEmail(),o.getPassword());
		} catch (AppException e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
            
	
	@PUT
	@Path("/{id}/{imageName}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation (value="Update",
		notes = "Upload photo")
	@ApiResponses(value = {
		@ApiResponse (code=200, message="Success"),
		@ApiResponse (code=500, message="Internal Server Error")
	})
	public String update(@PathParam("id") int OwnerId, @PathParam("imageName") String imageName,String status) {
		
		
		return status;
	}
	/*
	@DELETE
	@Path("/{id}")
	@ApiOperation (value="Delete",
		notes = "Delete an employee")
	@ApiResponses(value = {
		@ApiResponse (code=200, message="Success"),
		@ApiResponse (code=500, message="Internal Server Error")
	})
	public void delete (@PathParam("id") int empId) {
		
	}*/
}
