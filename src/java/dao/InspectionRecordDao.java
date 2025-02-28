/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.lang.invoke.VarHandle;
import config.Configuration;

import java.sql.Connection;
import java.util.List;
import model.InspectionRecords;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import microsoft.sql.Types;
import java.sql.*;
import model.Vehicles;


/**
 *
 * @author Lenovo
 */
public class InspectionRecordDao implements Dao<InspectionRecords> {

    Connection connect = DBContext.getInstance().getConnection();
    VehicleDao vd = new VehicleDao();

    @Override
    public int save(InspectionRecords t) {
        String sql = "INSERT INTO InspectionRecords (VehicleID, StationID, InspectionDate, NextInspectionDate, Result) "
                + "VALUES (?, ?, ?, ?,?)";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setInt(1, t.getVehicle().getVehicleId());
            st.setInt(2, t.getStationID());
            st.setDate(3, new java.sql.Date(t.getInspectionDate().getTime()));
            st.setDate(4, new java.sql.Date(t.getNextInspectionDate().getTime()));
            st.setString(5, "Pending");
            return st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<InspectionRecords> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(InspectionRecords t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean isInspectionDateExists(int vehicleId, Date inspectionDate) {
        String sql = "SELECT COUNT(*) FROM InspectionRecords WHERE VehicleID = ? AND InspectionDate = ?";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setInt(1, vehicleId);
            st.setDate(2, inspectionDate);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getNumberOfInspectionRecordsInCurrentDay() {
        int numberRecordsInDay = 0;
        String currentDate = LocalDate.now().toString();
        String sql = "SELECT COUNT(*)  \n"
                + "FROM InspectionRecords  \n"
                + "WHERE CAST(InspectionDate AS DATE) = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setString(1, currentDate);
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                numberRecordsInDay = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberRecordsInDay;
    }

    public int getNumberOfInspectionRecordsIsInspected() {
        int numberRecordsIsInspectedInDay = 0;
        String currentDate = LocalDate.now().toString();
        String sql = "select count(*) from InspectionRecords WHERE CAST(InspectionDate AS DATE) = ? and Result <> 'Pending'";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setString(1, currentDate);
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                numberRecordsIsInspectedInDay = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberRecordsIsInspectedInDay;
    }

    public List<InspectionRecords> getInspecedtationRecords(int stationId, int startRecord, int recordsPerPage) {
        List<InspectionRecords> recordses = new ArrayList<>();
        String sql = "SELECT * FROM InspectionRecords where StationID = ? and Result <> 'Pending' ORDER BY RecordID desc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setInt(1, stationId);
            pt.setInt(2, startRecord);
            pt.setInt(3, recordsPerPage);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                recordses.add(new InspectionRecords(rs.getInt("RecordID"),
                        vd.getVehiclesById(rs.getInt("VehicleID")),
                        rs.getInt("StationID"),
                        rs.getInt("InspectorID"),
                        rs.getDate("InspectionDate"),
                        rs.getDate("NextInspectionDate"),
                        rs.getString("Result"),
                        rs.getDouble("CO2Emission"),
                        rs.getDouble("HCEmission"),
                        rs.getString("Comments")));
            }
            return recordses;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getNoOfRecord(int stationId) {
        int noOfRecords = 0;
        String sql = "SELECT count(*) FROM InspectionRecords where StationID = ? and Result <> 'Pending'";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setInt(1, stationId);
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noOfRecords;
    }

    public int getNoOfRecordsPending(int stationId) {
        int noOfRecords = 0;
        String sql = "SELECT count(*) FROM InspectionRecords where StationID = ? and Result = 'Pending'";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setInt(1, stationId);
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noOfRecords;
    }

    public List<InspectionRecords> getListInspectionRecordsPending(int stationId, int startRecord, int recordsPerPage) {
        List<InspectionRecords> recordses = new ArrayList<>();
        String sql = "SELECT * FROM InspectionRecords where StationID = ? and Result = 'Pending' ORDER BY RecordID desc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setInt(1, stationId);
            pt.setInt(2, startRecord);
            pt.setInt(3, recordsPerPage);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                recordses.add(new InspectionRecords(rs.getInt("RecordID"),
                        vd.getVehiclesById(rs.getInt("VehicleID")),
                        rs.getInt("StationID"),
                        rs.getInt("InspectorID"),
                        rs.getDate("InspectionDate"),
                        rs.getDate("NextInspectionDate"),
                        rs.getString("Result"),
                        rs.getDouble("CO2Emission"),
                        rs.getDouble("HCEmission"),
                        rs.getString("Comments")));
            }
            return recordses;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<InspectionRecords> getListInspectionRecordsPendingBySearching(String searchDetails, int stationId, int startRecord, int recordPerPage) {
        List<InspectionRecords> recordses = new ArrayList<>();
        int id = vd.getVehicleIDByPlateNumber(searchDetails.trim().toUpperCase());
        String sql = "SELECT * FROM InspectionRecords where StationID = ? and VehicleID = ?  ORDER BY RecordID desc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setInt(1, stationId);
            pt.setInt(2, id);
            pt.setInt(3, startRecord);
            pt.setInt(4, recordPerPage);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                recordses.add(new InspectionRecords(rs.getInt("RecordID"),
                        vd.getVehiclesById(rs.getInt("VehicleID")),
                        rs.getInt("StationID"),
                        rs.getInt("InspectorID"),
                        rs.getDate("InspectionDate"),
                        rs.getDate("NextInspectionDate"),
                        rs.getString("Result"),
                        rs.getDouble("CO2Emission"),
                        rs.getDouble("HCEmission"),
                        rs.getString("Comments")));
            }
            return recordses;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getNoOfRecordPendingByResearch(int stationId, String searchDetails) {
        int noOfRecords = 0;
        int id = vd.getVehicleIDByPlateNumber(searchDetails.trim().toUpperCase());
        String sql = "SELECT count(*) FROM InspectionRecords where StationID = ? and VehicleID = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setInt(1, stationId);
            pt.setInt(2, id);
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noOfRecords;
    }
  
    public void removeInspectionRecordsWithPlateNumber(String plateNumber) {
        int vehicleId = vd.getVehicleIDByPlateNumber(plateNumber);
        String sql = "DELETE FROM InspectionRecords Where VehicleID = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setInt(1, vehicleId);
            pt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkResultOfVehicleID(int vehicleID) {
        String sql = "SELECT TOP 1 Result FROM InspectionRecords WHERE VehicleID = ? ORDER BY InspectionDate DESC";

        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, vehicleID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String result = rs.getString("Result");
                    return "Pass".equalsIgnoreCase(result); // Nếu result là "Pass" thì return true
                }
            } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Nếu không tìm thấy bản ghi nào thì trả về false
    }
    public List<InspectionRecords> getListInspectionRecordsWithTime(String status, String startDate, String endDate, int stationId, int startRecord, int recordPerPage) {
        List<InspectionRecords> recordses = new ArrayList<>();
        String sql = "SELECT * FROM InspectionRecords where StationID = ? " + status + " and InspectionDate BETWEEN ? AND ?  ORDER BY RecordID desc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setInt(1, stationId);
            pt.setDate(2, new java.sql.Date(Configuration.convertStringToDate(startDate).getTime()));
            pt.setDate(3, new java.sql.Date(Configuration.convertStringToDate(endDate).getTime()));
            pt.setInt(4, startRecord);
            pt.setInt(5, recordPerPage);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                recordses.add(new InspectionRecords(rs.getInt("RecordID"),
                        vd.getVehiclesById(rs.getInt("VehicleID")),
                        rs.getInt("StationID"),
                        rs.getInt("InspectorID"),
                        rs.getDate("InspectionDate"),
                        rs.getDate("NextInspectionDate"),
                        rs.getString("Result"),
                        rs.getDouble("CO2Emission"),
                        rs.getDouble("HCEmission"),
                        rs.getString("Comments")));
            }catch (SQLException e) {
            e.printStackTrace();
        }
        return recordses;
    }

    public boolean updateEmissions(int recordId, double co2Emission, double hcEmission, String comment, String result) {

        try {
            String sql = "UPDATE InspectionRecords SET CO2Emission = ?, HCEmission = ? , Comments = ? , Result = ?  WHERE RecordID = ?";
            PreparedStatement st = connect.prepareStatement(sql);

            // Kiểm tra và gán giá trị mặc định nếu null
            st.setDouble(1, co2Emission);
            st.setDouble(2, hcEmission);
            st.setString(3, comment);
             st.setString(4, result);
            st.setInt(5, recordId);
            

            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<InspectionRecords> getListInspectionRecordsByPendingAndInspectId(int inspectorId) {
        List<InspectionRecords> recordses = new ArrayList<>();

        String sql = "SELECT * FROM InspectionRecords WHERE InspectorID = ? and Result = 'Pending'";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setInt(1, inspectorId);

            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                recordses.add(new InspectionRecords(rs.getInt("RecordID"),
                        vd.getVehiclesById(rs.getInt("VehicleID")),
                        rs.getInt("StationID"),
                        rs.getInt("InspectorID"),
                        rs.getDate("InspectionDate"),
                        rs.getDate("NextInspectionDate"),
                        rs.getString("Result"),
                        rs.getDouble("CO2Emission"),
                        rs.getDouble("HCEmission"),
                        rs.getString("Comments")));
            }
            return recordses;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isVehicleInspectedToday(int vehicleID) {
        String sql = "SELECT COUNT(*) FROM InspectionRecords WHERE VehicleID = ? AND CAST(InspectionDate AS DATE) = ?";
        
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, vehicleID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // true neu phuong tien da duoc dang kiem qua 1 lan trong ngay 
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }

    public int getNoOfRecordsWithTime(String status, String startDate, String endDate, int stationId) {
        int noOfRecords = 0;
        String sql = "SELECT count(*) FROM InspectionRecords where StationID = ? " + status + " and InspectionDate BETWEEN ? AND ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setInt(1, stationId);
            pt.setString(2, startDate);
            pt.setString(3, endDate);
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noOfRecords;
    }

    public List<InspectionRecords> getInspectedVehilce(int vehicleID) {
        List<InspectionRecords> listhistory = new ArrayList<>();
        String sql = "SELECT v.PlateNumber, v.Brand, v.Model, id.InspectionDate, id.Result, id.NextInspectionDate "
                + "FROM Vehicles v "
                + "INNER JOIN InspectionRecords id ON id.VehicleID = v.VehicleID "
                + "WHERE v.VehicleID = ? "
                + "ORDER BY id.InspectionDate DESC ";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, vehicleID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vehicles vehicle = new Vehicles(
                        rs.getString("PlateNumber"),
                        rs.getString("Brand"),
                        rs.getString("Model")
                );
                InspectionRecords record = new InspectionRecords(
                        rs.getDate("InspectionDate"),
                        rs.getString("Result"),
                        rs.getDate("NextInspectionDate"),
                        vehicle
                );
                listhistory.add(record);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listhistory;
    }
}
