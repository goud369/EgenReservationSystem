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
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import restaurant.vs.exception.AppException;
import restaurant.vs.model.TableStatus;
import restaurant.vs.model.Tables;
import restaurant.vs.util.DBUtils;

/**
 *
 * @author goud
 */
public class TablesDAO {

    public List<Tables> fetchAll() throws AppException {

        List<Tables> tables = new ArrayList<Tables>();

        Connection con = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `tables`LIMIT 0 , 30");
            rs = ps.executeQuery();

            while (rs.next()) {
                Tables t = new Tables();
                t.setTable_id(rs.getInt("table_Id"));
                t.setTable_info(rs.getString("table_info"));
                t.setTable_Capacity(rs.getInt("table_Capacity"));

                tables.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new AppException(e.getMessage(), e.getCause());
        } finally {
            DBUtils.closeResource(ps, rs, con);
        }

        return tables;
    }

    public Tables fetchOne(int table_Id) throws AppException {

        Tables t = null;
        Connection con = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM tables WHERE table_Id =?");
            ps.setInt(1, table_Id);
            rs = ps.executeQuery();
            if (rs.next()) {
                t = new Tables();
                t.setTable_id(rs.getInt("table_Id"));
                t.setTable_info(rs.getString("table_info"));
                t.setTable_Capacity(rs.getInt("table_Capacity"));

            }
        } catch (SQLException e) {
            throw new AppException(e.getMessage(), e.getCause());
        } finally {
            DBUtils.closeResource(ps, rs, con);
        }

        return t;
    }

    public TableStatus checkAvailability(String d, Time time, int Capacity) throws AppException {
        TableStatus t = null;
        TablesDAO td = new TablesDAO();
        Connection con = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps1 = null;
        ResultSet rs1 = null;
        try {
            ps = con.prepareStatement("SELECT `table_id` FROM `reservations` WHERE `reservation_date` = ? AND `reservation_time` = ? AND `Number_Of_Guests`=?");
            ps.setString(1,  d);
            ps.setTime(2, time);
            ps.setInt(3, Capacity);
            rs = ps.executeQuery();
            if (rs.next()) {
                t = new TableStatus();
                t.setStatus("Waiting");
                t.setTable_id(rs.getInt("table_id"));
            } else {
                Calendar cal = Calendar.getInstance();
                cal.setTime(time);
                cal.add(Calendar.MINUTE,15);
                String Forward=cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
                Time ForwardTime=java.sql.Time.valueOf(Forward);
                cal.add(Calendar.MINUTE,-30);
                String Backward=cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
                Time  BackwardTime=java.sql.Time.valueOf(Backward);
                ps1 = con.prepareStatement("SELECT * FROM reservations AS r LEFT JOIN tables AS t ON r.`table_id` = t.`table_Id` WHERE `reservation_time` BETWEEN ? AND ? AND `reservation_date` = ? AND t.table_Capacity =?");
                ps1.setTime(1, BackwardTime);
                ps1.setTime(2, ForwardTime);
                ps1.setString(3,  d);
                ps1.setInt(4, Capacity);
                rs1 = ps1.executeQuery();
            if (rs1.next()) {
                t = new TableStatus();
                t.setStatus("Waiting");
                t.setTable_id(rs1.getInt("table_id"));
            }
            else {
                 t = new TableStatus();
                t.setStatus("Available");
                t.setTable_id(td.getTableId(Capacity));
                t.setTable(td.getTable(Capacity));
            }

            }
        } catch (SQLException e) {
            
        } finally {
            DBUtils.closeResource(ps, rs);
            DBUtils.closeResource(ps1, rs1,con);
        }

        return t;
    }

    public Tables getTable(int table_Capacity) throws AppException {

        Tables t = null;
        Connection con = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM tables WHERE table_Capacity =?");
            ps.setInt(1, table_Capacity);
            rs = ps.executeQuery();
            if (rs.next()) {
                t = new Tables();
                t.setTable_id(rs.getInt("table_Id"));
                t.setTable_info(rs.getString("table_info"));
                t.setTable_Capacity(rs.getInt("table_Capacity"));

            }
        } catch (SQLException e) {
            throw new AppException(e.getMessage(), e.getCause());
        } finally {
            DBUtils.closeResource(ps, rs, con);
        }

        return t;
    }
       public int getTableId(int table_Capacity) throws AppException {

        int table_Id=0;
        Connection con = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT table_Id FROM tables WHERE table_Capacity =?");
            ps.setInt(1, table_Capacity);
            rs = ps.executeQuery();
            if (rs.next()) {
           
                table_Id=rs.getInt("table_Id");
                

            }
        } catch (SQLException e) {
            throw new AppException(e.getMessage(), e.getCause());
        } finally {
            DBUtils.closeResource(ps, rs, con);
        }

        return table_Id;
    }
}
