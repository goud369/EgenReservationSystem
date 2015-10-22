package restaurant.vs.app;

import io.swagger.jaxrs.config.BeanConfig;
import java.sql.Date;
import java.sql.Time;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import restaurant.vs.dao.RestaurantDAO;
import restaurant.vs.exception.AppException;
import restaurant.vs.model.Reservation;

@ApplicationPath("/vs")
public class RESTApp extends ResourceConfig {
	
	public RESTApp () {
		packages("restaurant.vs.rest");
		//swagger
		
		register(io.swagger.jaxrs.listing.ApiListingResource.class);
		register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
		
		BeanConfig config = new BeanConfig();
		config.setBasePath("/RestaurantRESTApp/vs");
		config.setDescription("REST API Demo");
		config.setVersion("1.0");
		config.setSchemes(new String [] {"http"});
		config.setResourcePackage("restaurant.vs");
		config.setTitle("RestaurantRESTApp");
         	config.setScan(true);
}
        public static void main(String args []) throws AppException{
            RestaurantDAO pig=new RestaurantDAO();
            Reservation r=new Reservation();
            r.setCustomer_id(4);
            r.setTable_id(25);
            r.setNumberOfGuests(5);
            r.setReservation_Date(new Date(1456231455));
            r.setReservation_Time(new Time(456137989));
            r.setReservation_Status("Available");
            pig.create(r);
            System.out.println(pig.create(r).getReservation_Date());
                    }
            
}
