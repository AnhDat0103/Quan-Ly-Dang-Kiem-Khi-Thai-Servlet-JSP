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
/**
 *
 * @author Lenovo
 */
public class InspectionRecordDao implements Dao<InspectionRecords>{
    
    Connection connect = DBContext.getInstance().getConnection();
    
    @Override
    public int save(InspectionRecords t) {
        String sql = "INSERT INTO InspectionRecords (VehicleID, StationID, InspectionDate, NextInspectionDate, Result) " +
                     "VALUES (?, ?, ?, ?,?)";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setInt(1, t.getVehicleID());
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
}
