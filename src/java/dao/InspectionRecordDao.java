/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.util.List;
import model.InspectionRecords;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

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
        String sql = "select count(*) from InspectionRecords where WHERE CAST(InspectionDate AS DATE) = ? and Result <> 'Pedding'";
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

    public List<InspectionRecords> getInspecedtationRecords(int stationId) {
        List<InspectionRecords> recordses = new ArrayList<>();
        String sql = "select * from InspectionRecords where StationID = ? and Result <> 'Pending'";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setInt(1, stationId);
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

}
