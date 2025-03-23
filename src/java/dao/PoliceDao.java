/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.util.List;
import model.Police;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.InspectionRecords;
import model.User;
import model.Vehicles;
import model.enums.vehicleEnums.vehicleEnums;

/**
 *
 * @author Lenovo
 */
public class PoliceDao implements Dao<Police> {

    Connection connect = DBContext.getInstance().getConnection();

    @Override
    public int save(Police t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Police> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Police t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Police> getAllInfoVehicleGoPolicePage() {
        List<Police> policeList = new ArrayList<>();
        String sql = "SELECT "
                + "u.FullName, "
                + "u.Phone, "
                + "u.Email, "
                + "v.PlateNumber, "
                + "v.Brand, "
                + "v.Model, "
                + "v.ManufactureYear, "
                + "v.EngineNumber, "
                + "id.InspectionDate, "
                + "id.CO2Emission, "
                + "id.HCEmission, "
                + "id.NextInspectionDate, "
                + "id.Result, "
                + "id.Comments "
                + "FROM Vehicles v "
                + "JOIN [Users] u ON v.ownerID = u.UserID "
                + "JOIN InspectionRecords id ON id.VehicleID = v.VehicleID "
                + "WHERE u.Role = 'Owner' "
                + "AND v.Status <> 'Ban' "
                + "ORDER BY u.FullName";

        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setFullName(rs.getString("FullName"));
                user.setPhone(rs.getString("Phone"));
                user.setEmail(rs.getString("Email"));

                Vehicles vehicle = new Vehicles();
                vehicle.setPlateNumber(rs.getString("PlateNumber"));
                vehicle.setBrand(rs.getString("Brand"));
                vehicle.setModel(rs.getString("Model"));
                vehicle.setManufactureYear(rs.getInt("ManufactureYear"));
                vehicle.setEngineNumber(rs.getString("EngineNumber"));

                InspectionRecords inspection = new InspectionRecords();
                inspection.setInspectionDate(rs.getDate("InspectionDate"));
                inspection.setCo2Emission(rs.getDouble("CO2Emission"));
                inspection.setHcEmission(rs.getDouble("HCEmission"));
                inspection.setNextInspectionDate(rs.getDate("NextInspectionDate"));
                inspection.setResult(rs.getString("Result"));
                inspection.setComments(rs.getString("Comments"));

                Police police = new Police(user, vehicle, inspection);
                policeList.add(police);
            }
        } catch (SQLException E) {
            E.printStackTrace();
        }
        return policeList;
    }

    public int countBannedVehicles() {
        int count = 0;
        String sql = "SELECT COUNT(*) AS BannedCount FROM Vehicles WHERE Status = 'Ban'"; // hoặc 'Ban' nếu dữ liệu là 'Ban'

        try (PreparedStatement ps = connect.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("BannedCount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Police> findByPlateNumber(String plateNumber) {
        List<Police> list = new ArrayList<>();
        String sql = "SELECT v.PlateNumber, u.FullName, v.Brand, v.Model, v.ManufactureYear, v.EngineNumber, v.Status "
                + "FROM Vehicles v "
                + "INNER JOIN [Users] u ON v.OwnerID = u.UserID  "
                + "WHERE v.PlateNumber = ? ";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, plateNumber);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setFullName(rs.getString("FullName"));

                Vehicles vehicle = new Vehicles();
                vehicle.setPlateNumber(rs.getString("PlateNumber"));
                vehicle.setBrand(rs.getString("Brand"));
                vehicle.setModel(rs.getString("Model"));
                vehicle.setManufactureYear(rs.getInt("ManufactureYear"));
                vehicle.setEngineNumber(rs.getString("EngineNumber"));
                vehicle.setStatus(vehicleEnums.valueOf(rs.getString("Status")));

                Police police = new Police(user, vehicle);
                list.add(police);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
