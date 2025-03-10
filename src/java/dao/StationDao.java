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
public class StationDao implements Dao<InspectionStation> {

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
            while (rs.next()) {
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
        String sql = "SELECT * FROM InspectionStations WHERE StationID = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setInt(1, stationId);
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
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


    public List<InspectionStation> getAllStations() {
        List<InspectionStation> stations = new ArrayList<>();
        String sql = "SELECT * FROM InspectionStations";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                InspectionStation station = new InspectionStation();
                station.setStationId(rs.getInt("stationID"));
                station.setName(rs.getString("name"));
                station.setAddress(rs.getString("address"));
                station.setPhone(rs.getString("phone"));
                station.setEmail(rs.getString("email"));
                stations.add(station);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return stations;
    }

    public List<String> getAllStationNames() {
        List<String> stationNames = new ArrayList<>();
        String sql = "SELECT Name FROM InspectionStations";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                stationNames.add(rs.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stationNames;
    }
    
    public int getStationIDByName(String stationName) {
    int stationID = 0;
    String sql = "SELECT StationID FROM InspectionStations WHERE Name = ?";
    try {
        PreparedStatement st = connect.prepareStatement(sql);
        st.setString(1, stationName);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            stationID = rs.getInt("StationID");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return stationID;
}
    
    public boolean updateStation(int stationId, String stationName, String address, String phoneNumber ,String email ) {
    String sql = "UPDATE Stations SET Name = ?, Address = ?, PhoneNumber = ? , Email = ? WHERE StationID = ?";
    
    try{
          PreparedStatement pt = connect.prepareStatement(sql);
        pt.setString(1, stationName);
        pt.setString(2, address);
        pt.setString(3, phoneNumber);
        pt.setInt(4, stationId);

        int rowsUpdated = pt.executeUpdate();
        return rowsUpdated > 0; // Trả về true nếu update thành công
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

}
