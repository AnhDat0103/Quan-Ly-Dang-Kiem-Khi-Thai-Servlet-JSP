/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.util.List;
import model.InspectionStation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author DAT
 */
public class StationDao implements Dao<InspectionStation>{
    Connection connect = DBContext.getInstance().getConnection();

    @Override
    public int save(InspectionStation t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<InspectionStation> findAll() {
        List<InspectionStation> stations = new ArrayList<>();
        String sql = "select * from InspectionStations";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            ResultSet rs = pt.executeQuery();
            while(rs.next()) {
                stations.add(new InspectionStation(rs.getInt("StationID"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("Phone"),
                        rs.getString("Email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stations;
    }

    @Override
    public int update(InspectionStation t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public InspectionStation findStationById(int stationId) {
        String sql  = "SELECT * FROM InspectionStations WHERE StationID = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setInt(1, stationId);
            ResultSet rs = pt.executeQuery();
            if(rs.next()){
                InspectionStation station = new InspectionStation(rs.getInt("StationID"),
                        rs.getString("Name"), 
                        rs.getString("Address"), 
                        rs.getString("Phone"), 
                        rs.getString("Email"));
                return station;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
}
