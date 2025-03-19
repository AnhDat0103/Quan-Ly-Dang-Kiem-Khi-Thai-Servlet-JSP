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

    public List<Police> getViolatingVehicles() {
        List<Police> violatingVehicles = new ArrayList<>();
        String sql = "SELECT "
                + "    u.UserID, "
                + "    u.FullName, "
                + "    ve.PlateNumber, "
                + "    ve.Brand, "
                + "    ve.Model, "
                + "    STRING_AGG(CAST(Violations.Violation AS VARCHAR), ',') AS ViolationType "
                + "FROM Vehicles ve "
                + "JOIN [Users] u ON ve.ownerID = u.UserID "
                + "JOIN ( "
                + "    SELECT VehicleID, 1 AS Violation "
                + "    FROM InspectionRecords "
                + "    WHERE Result = 'Fail' "
                + "    GROUP BY VehicleID "
                + "    HAVING COUNT(*) >= 3 "
                + "    UNION "
                + "    SELECT VehicleID, 2 AS Violation "
                + "    FROM InspectionRecords "
                + "    WHERE DATEDIFF(DAY, NextInspectionDate, GETDATE()) >= 10 "
                + ") AS Violations ON ve.VehicleID = Violations.VehicleID "
                + "WHERE Status <> 'Ban' "
                + "GROUP BY u.UserID ,u.FullName, ve.PlateNumber, ve.Brand, ve.Model "
                + "ORDER BY ve.PlateNumber;";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int UserId = Integer.parseInt(rs.getString("UserID"));
                String fullName = rs.getString("FullName");
                String plateNumber = rs.getString("PlateNumber");
                String brand = rs.getString("Brand");
                String model = rs.getString("Model");
                int violationType = Integer.parseInt(rs.getString("violationType"));

                User user = new User();
                user.setUserId(UserId);
                user.setFullName(fullName);

                Vehicles vehicle = new Vehicles();
                vehicle.setPlateNumber(plateNumber);
                vehicle.setBrand(brand);
                vehicle.setModel(model);
                vehicle.setViolationType(violationType);

                // Tạo đối tượng Police và thêm vào danh sách
                Police police = new Police(user, vehicle);
                violatingVehicles.add(police);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return violatingVehicles;
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

}
