/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Configuration;
import java.util.List;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import model.InspectionStation;
import model.enums.RoleEnums;

/**
 *
 * @author DAT
 */
public class UserDao implements Dao<User> {
    
    Connection connect = DBContext.getInstance().getConnection();
    StationDao sd = new StationDao();

    @Override
    public int save(User t) {
        String sql = "INSERT INTO Users(FullName, Email, Password, Role, Phone, Address) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, t.getFullName());
            ps.setString(2, t.getEmail());
            ps.setString(3, Configuration.hashPasswordByMD5(t.getPassword()));
            ps.setString(4, t.getRole().toString());
            ps.setString(5, t.getPhone());
            ps.setString(6, t.getAddress());
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(User t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public User findUserByEmailAndPasswordAndRole(String email, String password, String role) {
        String sql = "SELECT * FROM Users WHERE Email = ? AND Password = ? And Role = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, Configuration.hashPasswordByMD5(password));
            ps.setString(3, role);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                InspectionStation is = sd.findStationById(rs.getInt("StationID")) != null ? sd.findStationById(rs.getInt("StationID")) : new InspectionStation();
                User user = new User(rs.getInt("UserID"), 
                        rs.getString("FullName"), 
                        rs.getString("Email"), 
                        rs.getString("Password"), 
                        RoleEnums.valueOf(rs.getString("Role")), 
                        rs.getString("Phone"), 
                        rs.getString("Address"),
                        is);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int existedUserWithEmail(String emailRequest) {
        String sql = "SELECT * FROM Users WHERE Email = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            ResultSet rs = pt.executeQuery();
            if(rs.next()) {
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }     
        return 0;
    }
    
    public int updatePassword(String newPassword, int userId) {
        int updatedRow = 0;
        String sql = "UPDATE Users SET Password = ? WHERE UserID = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setString(1, newPassword);
            pt.setInt(2, userId);
            updatedRow = pt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(updatedRow > 0) {
            return updatedRow;
        }
        return 0;
    }

}
