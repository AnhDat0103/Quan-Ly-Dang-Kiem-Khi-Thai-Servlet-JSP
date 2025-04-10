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
import java.util.ArrayList;
import model.InspectionStation;
import model.enums.ProviderClass;
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
        String sql = "INSERT INTO Users(FullName, Email, Password, Role, Phone, Address,Avatar, Provider) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, t.getFullName());
            ps.setString(2, t.getEmail());
            ps.setString(3, Configuration.hashPasswordByMD5(t.getPassword()));
            ps.setString(4, t.getRole().toString());
            ps.setString(5, t.getPhone());
            ps.setString(6, t.getAddress());
            ps.setString(7, t.getAvatar());
            ps.setString(8, t.getProvider().name());
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
        int result = 0;
        String sql = "UPDATE Users SET FullName = ?, Phone = ?, Address = ? where Email = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setString(1, t.getFullName());
            pt.setString(2, t.getPhone());
            pt.setString(3, t.getAddress());
            pt.setString(4, t.getEmail());
            result = pt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(int t) {
        int result = 0;
        String sql = "Delete from Users where UserID = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setInt(1, t);
            result = pt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public User findUserByEmailAndPasswordAndRole(String email, String password, String role) {
        String sql = "SELECT * FROM Users WHERE Email = ? AND Password = ? And Role = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, Configuration.hashPasswordByMD5(password));
            ps.setString(3, role);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                InspectionStation is = sd.findStationById(rs.getInt("StationID")) != null ? sd.findStationById(rs.getInt("StationID")) : new InspectionStation();
                User user = new User(rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        RoleEnums.valueOf(rs.getString("Role")),
                        rs.getString("Phone"),
                        rs.getString("Address"),
                        rs.getString("Avatar"),
                        ProviderClass.valueOf(rs.getString("Provider")),
                        is);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean existedUserWithEmail(String emailRequest) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE Email = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setString(1, emailRequest);
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                InspectionStation is = sd.findStationById(rs.getInt("StationID")) != null ? sd.findStationById(rs.getInt("StationID")) : new InspectionStation();
                users.add(new User(rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        RoleEnums.valueOf(rs.getString("Role")),
                        rs.getString("Phone"),
                        rs.getString("Address"),
                        rs.getString("Avatar"),
                        ProviderClass.valueOf(rs.getString("Provider")),
                        is
                ));
                return !users.isEmpty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int updatePassword(String newPassword, String email) {
        int updatedRow = 0;
        String sql = "UPDATE Users SET Password = ? WHERE Email = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setString(1, newPassword);
            pt.setString(2, email);
            updatedRow = pt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedRow;
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
        return updatedRow;
    }

    public int updateInspecStationId(int parseInt, String email) {
        int result = 0;
        String sql = "update Users set StationID = ? WHERE Email = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setInt(1, parseInt);
            pt.setString(2, email);
            result = pt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public User findUserById(int id) {
        String sql = "select * from Users where UserID = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                InspectionStation is = sd.findStationById(rs.getInt("StationID")) != null ? sd.findStationById(rs.getInt("StationID")) : new InspectionStation();
                User user = new User(rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        RoleEnums.valueOf(rs.getString("Role")),
                        rs.getString("Phone"),
                        rs.getString("Address"),
                        rs.getString("Avatar"),
                        ProviderClass.valueOf(rs.getString("Provider")),
                        is);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserbyTelAndName(String tel, String fullname) {
        String sql = "select * from Users where Phone = ? and FullName = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setString(1, tel);
            pt.setString(2, fullname);
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                InspectionStation is = sd.findStationById(rs.getInt("StationID")) != null ? sd.findStationById(rs.getInt("StationID")) : new InspectionStation();
                User user = new User(rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        RoleEnums.valueOf(rs.getString("Role")),
                        rs.getString("Phone"),
                        rs.getString("Address"),
                        rs.getString("Avatar"),
                        ProviderClass.valueOf(rs.getString("Provider")),
                        is);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUserByRole(String role) {
        List<User> users = new ArrayList<>();
        String sql = "select * from Users where Role = " + role;
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                InspectionStation is = sd.findStationById(rs.getInt("StationID")) != null ? sd.findStationById(rs.getInt("StationID")) : new InspectionStation();
                users.add(new User(rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        RoleEnums.valueOf(rs.getString("Role")),
                        rs.getString("Phone"),
                        rs.getString("Address"),
                        rs.getString("Avatar"),
                        ProviderClass.valueOf(rs.getString("Provider")),
                        is
                ));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllOwners() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE Role = ? ORDER BY UserID ASC";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setString(1, "owner");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                InspectionStation is = sd.findStationById(rs.getInt("StationID")) != null
                        ? sd.findStationById(rs.getInt("StationID"))
                        : new InspectionStation();
                User user = new User(
                        rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        RoleEnums.valueOf(rs.getString("Role")),
                        rs.getString("Phone"),
                        rs.getString("Address"),
                        rs.getString("Avatar"),
                        ProviderClass.valueOf(rs.getString("Provider")),
                        is
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User findUserByEmail(String email) {
        String sql = "select * from Users where Email = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setString(1, email);
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                InspectionStation is = sd.findStationById(rs.getInt("StationID")) != null
                        ? sd.findStationById(rs.getInt("StationID"))
                        : new InspectionStation();
                User user = new User(
                        rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        RoleEnums.valueOf(rs.getString("Role")),
                        rs.getString("Phone"),
                        rs.getString("Address"),
                        rs.getString("Avatar"),
                        ProviderClass.valueOf(rs.getString("Provider")),
                        is
                );
                return user;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public int updateAvatar(String newAvatar, String email) {
        int result = 0;
        String sql = "UPDATE Users SET Avatar = ? WHERE Email = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setString(1, newAvatar);
            pt.setString(2, email);
            result = pt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<User> getAllInspectors(int stationId) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE Role = ? AND StationID = ? ORDER BY UserID ASC";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setString(1, "Inspector");
            pt.setInt(2, stationId);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                InspectionStation is = sd.findStationById(rs.getInt("StationID")) != null
                        ? sd.findStationById(rs.getInt("StationID"))
                        : new InspectionStation();
                User user = new User(
                        rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        RoleEnums.valueOf(rs.getString("Role")),
                        rs.getString("Phone"),
                        rs.getString("Address"),
                        rs.getString("Avatar"),
                        ProviderClass.valueOf(rs.getString("Provider")),
                        is
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> findAllPolice() {
        List<User> polices = new ArrayList<>();
        String sql = "select * from Users where Role = 'Police'";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                InspectionStation is = sd.findStationById(rs.getInt("StationID")) != null
                        ? sd.findStationById(rs.getInt("StationID"))
                        : new InspectionStation();
                polices.add(new User(
                        rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        RoleEnums.valueOf(rs.getString("Role")),
                        rs.getString("Phone"),
                        rs.getString("Address"),
                        rs.getString("Avatar"),
                        ProviderClass.valueOf(rs.getString("Provider")),
                        is));
            }
            return polices;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
