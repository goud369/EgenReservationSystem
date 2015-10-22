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
import restaurant.vs.model.Owners;
import restaurant.vs.util.DBUtils;

/**
 *
 * @author goud
 */
public class OwnersDAO {

    public List<Owners> fetchAll() throws AppException {

        List<Owners> owners = new ArrayList<Owners>();

        Connection con = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `ownersDetails` LIMIT 0 , 30");
            rs = ps.executeQuery();

            while (rs.next()) {
                Owners o = new Owners();
                o.setId(rs.getInt("id"));
                o.setEmail(rs.getString("email"));
                o.setName(rs.getString("Name"));
                o.setPhone(rs.getString("phone"));
                o.setAvatar(rs.getString("avatar"));
                o.setAddress(rs.getString("address"));
                owners.add(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new AppException(e.getMessage(), e.getCause());
        } finally {
            DBUtils.closeResource(ps, rs, con);
        }

        return owners;
    }

    public Owners fetchOne(int id) throws AppException {

        Owners o = null;

        Connection con = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM ownersDetails WHERE id =?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                o = new Owners();
                o.setId(rs.getInt("id"));
                o.setEmail(rs.getString("email"));
                o.setName(rs.getString("Name"));
                o.setPhone(rs.getString("phone"));
                o.setAvatar(rs.getString("avatar"));
                o.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            throw new AppException(e.getMessage(), e.getCause());
        } finally {
            DBUtils.closeResource(ps, rs, con);
        }

        return o;
    }

    public Owners checkAuthentication(String email, String password) throws AppException {
        Owners o = null;
        Connection con = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `ownersDetails` WHERE  email=? AND password=?");
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                o=new Owners();
                o.setId(rs.getInt("id"));
                o.setEmail(rs.getString("email"));
                o.setName(rs.getString("Name"));
                o.setAddress(rs.getString("address"));
                o.setPhone(rs.getString("phone"));
                o.setAvatar(rs.getString("avatar"));
                
            }
        } catch (SQLException e) {
            throw new AppException(e.getMessage(), e.getCause());
        } finally {
            DBUtils.closeResource(ps, rs, con);
        }

        return o;
    }
    
    public Owners uploadImageName(int id, String imageName, String Success) throws AppException {

        Owners o = null;

        Connection con = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("UPDATE `reservation_system`.`ownersDetails` SET `avatar` = '?' WHERE `ownersDetails`.`id` =?;");
            ps.setString(1, imageName);
            ps.setInt(2, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Success="true";
                
            }
            else{
            Success="false";
            }
        } catch (SQLException e) {
            throw new AppException(e.getMessage(), e.getCause());
        } finally {
            DBUtils.closeResource(ps, rs, con);
        }

        return o;
    }
}
