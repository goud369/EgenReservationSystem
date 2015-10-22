package restaurant.vs.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import restaurant.vs.exception.AppException;
import restaurant.vs.model.Reservation;
import restaurant.vs.util.DBUtils;

public class RestaurantDAO {

    public List<Reservation> fetchAll() throws AppException {

        List<Reservation> reservations = new ArrayList<Reservation>();
        CustomerDAO cs = new CustomerDAO();
        TablesDAO td = new TablesDAO();
        Connection con = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `reservations`LIMIT 0 , 30");

            rs = ps.executeQuery();

            while (rs.next()) {
                Reservation rstn = new Reservation();
                rstn.setReservation_id(rs.getInt("reservation_id"));
                rstn.setNumberOfGuests(rs.getInt("Number_Of_Guests"));
                    java.sql.Date sqlDate = new java.sql.Date(rs.getDate("reservation_date").getTime());
          
                rstn.setReservation_Date(sqlDate);
                rstn.setReservation_Time(rs.getTime("reservation_time"));
                rstn.setCustomer_id(rs.getInt("customer_id"));
                rstn.setTable_id(rs.getInt("table_id"));
                rstn.setReservation_Status(rs.getString("reservation_Status"));
                rstn.setCustomer(cs.fetchOne(rs.getInt("customer_id")));
                rstn.setTable(td.fetchOne(rs.getInt("table_id")));
                reservations.add(rstn);
            }

        } catch (SQLException e) {
//            throw new AppException(e.getMessage(), e.getCause());
        } finally {
            DBUtils.closeResource(ps, rs, con);
        }

        return reservations;
    }

    public Reservation fetchOne(int reservation_id) throws AppException {

        Reservation rstn = null;
        CustomerDAO cs = new CustomerDAO();
        TablesDAO td = new TablesDAO();
        Connection con = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
           
            ps = con.prepareStatement("SELECT * FROM reservations WHERE reservation_id=?");
            ps.setInt(1, reservation_id);
            rs = ps.executeQuery();
              
            if (rs.next()) {
                rstn = new Reservation();
                rstn.setReservation_id(rs.getInt("reservation_id"));
                rstn.setNumberOfGuests(rs.getInt("Number_Of_Guests"));
               java.sql.Date sqlDate = new java.sql.Date(rs.getDate("reservation_date").getTime());
                rstn.setReservation_Date(sqlDate);
                rstn.setReservation_Time(rs.getTime("reservation_time"));
                rstn.setCustomer_id(rs.getInt("customer_id"));
                rstn.setTable_id(rs.getInt("table_id"));
                rstn.setCustomer_id(rs.getInt("customer_id"));
                 rstn.setReservation_Status(rs.getString("reservation_Status"));
                rstn.setCustomer(cs.fetchOne(rs.getInt("customer_id")));
                rstn.setTable(td.fetchOne(rs.getInt("table_id")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AppException(e.getMessage(), e.getCause());
        } finally {
            DBUtils.closeResource(ps, rs, con);
        }

        return rstn;
    }

    public Reservation create(Reservation rstn) throws AppException {
        Connection con = DBUtils.getConnection();
        CustomerDAO cd=new CustomerDAO();
        TablesDAO td=new TablesDAO();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            System.out.println("SetDate"+rstn.getReservation_Date());
            java.sql.Date sqlDate = new java.sql.Date(rstn.getReservation_Date().getTime());
            System.out.println("SqlDate"+sqlDate);
            ps = con.prepareStatement("INSERT INTO `reservation_system`.`reservations` (`Number_Of_Guests`, `reservation_date`, `reservation_time`, `customer_id`, `table_id`,`reservation_Status`) VALUES (?,?,?,(select Customer_id from customers where Customer_id=?),(select table_Id from tables where table_Id=?),?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, rstn.getNumberOfGuests());
            ps.setDate(2, rstn.getReservation_Date());
            ps.setTime(3,rstn.getReservation_Time());
            ps.setInt(4,rstn.getCustomer_id());
            ps.setInt(5, rstn.getTable_id());
            ps.setString(6, rstn.getReservation_Status());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                rstn.setReservation_id(rs.getInt(1));
                
            }
            rstn.setCustomer(cd.fetchOne(rstn.getCustomer_id()));
            rstn.setTable(td.fetchOne(rstn.getTable_id()));
        } catch (SQLException e) {
     
        } finally {
            DBUtils.closeResource(ps, rs, con);
        }

        return rstn;
    }
    
    public String delete(int d) throws AppException {

        Connection con = DBUtils.getConnection();
        PreparedStatement ps = null;
        String sucess=null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("DELETE FROM `reservation_system`.`reservations` WHERE `reservations`.`reservation_id`=?");
            ps.setInt(1, d);
            int val=ps.executeUpdate();
            if (val==1) {
                System.out.println("sucess");
                sucess="sucess";                
            }
        } catch (SQLException e) {
//            throw new AppException(e.getMessage(), e.getCause());
        } finally {
            DBUtils.closeResource(ps, rs, con);
        }
        return sucess;
    }
}
