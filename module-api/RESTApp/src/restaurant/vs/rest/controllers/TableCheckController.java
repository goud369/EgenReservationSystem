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
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import restaurant.vs.dao.RestaurantDAO;
import restaurant.vs.dao.TablesDAO;
import restaurant.vs.exception.AppException;
import restaurant.vs.model.Reservation;
import restaurant.vs.model.TableStatus;
import restaurant.vs.model.Tables;

/**
 *
 * @author goud
 */
@Path("/tablecheck")
@Api(tags = {"/tablecheck"})
public class TableCheckController {

    @GET
    @Path("/{reserveDate}/{reserveTime}/{tableCapacity}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Find One",
            notes = "Finds an employee by it's id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public TableStatus findOne(@PathParam("reserveDate") String reservaeDate, @PathParam("reserveTime") Time reserveTime,@PathParam("tableCapacity") int tableCapacity) {
        try {
            TablesDAO dao = new TablesDAO();
            TableStatus t = dao.checkAvailability(reservaeDate, reserveTime, tableCapacity);
            if (t == null) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            } else {
                return t;
            }
        } catch (AppException e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    /*	@POST
     @Consumes(MediaType.APPLICATION_JSON)
     @Produces(MediaType.APPLICATION_JSON)
     @ApiOperation (value="Create",
     notes = "Create an employee")
     @ApiResponses(value = {
     @ApiResponse (code=200, message="Success"),
     @ApiResponse (code=500, message="Internal Server Error")
     })
     public Employee create(Employee emp) {
     try {
     RestaurantDAO dao = new RestaurantDAO();
     return dao.create(emp);
     } catch (AppException e) {
     throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
     }
     }
	
	
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
