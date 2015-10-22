/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.vs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import restaurant.vs.exception.AppException;
import restaurant.vs.model.Customers;
import restaurant.vs.model.Customers;
import restaurant.vs.model.Reservation;
import restaurant.vs.model.Tables;
import restaurant.vs.util.DBUtils;

/**
 *
 * @author goud
 */
public class CustomerDAO {
    public List<Customers> fetchAll() throws AppException {

		List<Customers> customers = new ArrayList<Customers>();

		Connection con = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM `customers`LIMIT 0 , 30");
			rs = ps.executeQuery();

			while (rs.next()) {
				Customers c = new Customers();
				  c.setId(rs.getInt("Customer_id"));
                                    c.setEmail(rs.getString("Email"));
                                    c.setName(rs.getString("Customer_Name"));
                                    c.setPhone(rs.getString("Phone_Number"));
                                    
				customers.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		} finally {
			DBUtils.closeResource(ps, rs, con);
		}

		return customers;
	}
	
	
public Customers fetchOne(int Customer_id) throws AppException {

		Customers c = null;

		Connection con = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM customers WHERE Customer_id =?");
			ps.setInt(1, Customer_id);
                        rs = ps.executeQuery();

			if (rs.next()) {
                                    c=new Customers();
                                    c.setId(rs.getInt("Customer_id"));
                                    c.setEmail(rs.getString("Email"));
                                    c.setName(rs.getString("Customer_Name"));
                                    c.setPhone(rs.getString("Phone_Number"));
			}
		} catch (SQLException e) {
			throw new AppException(e.getMessage(), e.getCause());
		} finally {
			DBUtils.closeResource(ps, rs, con);
		}

		return c;
	}

 public Customers create(Customers c) throws AppException {
     Connection con = DBUtils.getConnection();
     PreparedStatement ps = null;
     ResultSet rs = null;
     try {
     ps = con.prepareStatement("INSERT INTO `reservation_system`.`customers` (`Customer_Name`, `Email`, `Phone_Number`) VALUES (?,?,?);", PreparedStatement.RETURN_GENERATED_KEYS);
     ps.setString(1, c.getName());
     ps.setString(2, c.getEmail());
     ps.setString(3, c.getPhone());

			
     ps.executeUpdate();
			
     rs = ps.getGeneratedKeys();

     if (rs.next()) {
     c.setId(rs.getInt(1));
     }
     } catch (SQLException e) {
     e.printStackTrace();
     throw new AppException(e.getMessage(), e.getCause());
     } finally {
     DBUtils.closeResource(ps, rs, con);
     }

     return c;
     }
}
