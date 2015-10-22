package restaurant.vs.rest.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import restaurant.vs.dao.RestaurantDAO;
import restaurant.vs.exception.AppException;
import restaurant.vs.model.Reservation;

@Path("/reservations")
@Api(tags= {"reservations"})
public class ReservationController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation (value="Find All",
			notes = "Finds all employees in the system")
	@ApiResponses(value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=500, message="Internal Server Error")
	})
	public List<Reservation> findAll () {
		try {
			RestaurantDAO dao = new RestaurantDAO();
			return dao.fetchAll();
		} catch (AppException e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GET
	@Path("/{reservation_id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation (value="Find One",
		notes = "Finds an employee by it's id")
	@ApiResponses(value = {
		@ApiResponse (code=200, message="Success"),
		@ApiResponse (code=404, message="Not Found"),
		@ApiResponse (code=500, message="Internal Server Error")
	})
	public Reservation findOne(@PathParam("reservation_id") int reservation_id) {
		try {
			RestaurantDAO dao = new RestaurantDAO();
			Reservation rstn =  dao.fetchOne(reservation_id);
			if(rstn == null) {
				throw new WebApplicationException(Status.NOT_FOUND);
			}
			else {
				return rstn;
			}
		} catch (AppException e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation (value="Create",
		notes = "Create an Reservation")
	@ApiResponses(value = {
		@ApiResponse (code=200, message="Success"),
		@ApiResponse (code=500, message="Internal Server Error")
	})
	public Reservation create(Reservation rstn) {
		try {
			RestaurantDAO dao = new RestaurantDAO();
                        System.out.println("First"+rstn.getReservation_Date());
			return dao.create(rstn);
		} catch (AppException e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DELETE
	@Path("/{id}")
        @Produces(MediaType.TEXT_PLAIN)
	@ApiOperation (value="Delete",
		notes = "Delete an Reservation")
	@ApiResponses(value = {
		@ApiResponse (code=200, message="Success"),
		@ApiResponse (code=500, message="Internal Server Error")
	})
	public String delete (@PathParam("id") int rsId) {
		try {
			RestaurantDAO dao = new RestaurantDAO();
			return dao.delete(rsId);
		} catch (AppException e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
}
