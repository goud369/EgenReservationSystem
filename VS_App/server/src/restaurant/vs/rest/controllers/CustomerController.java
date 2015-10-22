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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import restaurant.vs.dao.CustomerDAO;
import restaurant.vs.exception.AppException;
import restaurant.vs.model.Customers;

/**
 *
 * @author goud
 */
@Path("/customers")
@Api(tags= {"customers"})
public class CustomerController {
    	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation (value="Find All",
			notes = "Finds all customers in the system")
	@ApiResponses(value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=500, message="Internal Server Error")
	})
	public List<Customers> findAll () {
		try {
			CustomerDAO cdao = new CustomerDAO();
			return cdao.fetchAll();
		} catch (AppException e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GET
	@Path("/{Customer_id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation (value="Find One",
		notes = "Finds customers by it's id")
	@ApiResponses(value = {
		@ApiResponse (code=200, message="Success"),
		@ApiResponse (code=404, message="Not Found please find"),
		@ApiResponse (code=500, message="Internal Server Error")
	})
	public Customers findOne(@PathParam("Customer_id") int Customer_id) {
		try {
			CustomerDAO cdao = new CustomerDAO();
			Customers c =  cdao.fetchOne(Customer_id);
			if(c == null) {
				throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
			else {
				return c;
			}
		} catch (AppException e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation (value="Create",
		notes = "Create an Customer")
	@ApiResponses(value = {
		@ApiResponse (code=200, message="Success"),
		@ApiResponse (code=500, message="Internal Server Error")
	})
	public Customers create(Customers c) {
		try {
			CustomerDAO cdao = new CustomerDAO();
			return cdao.create(c);
		} catch (AppException e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation (value="Update",
		notes = "Update an employee")
	@ApiResponses(value = {
		@ApiResponse (code=200, message="Success"),
		@ApiResponse (code=500, message="Internal Server Error")
	})
	public Employee update(@PathParam("id") int empId, Employee emp) {
		
		
		return emp;
	}
	
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
