/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.LogSystem;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.ResultSet;
import model.User;

/**
 *
 * @author DAT
 */
public class LogSystemDao implements Dao<LogSystem> {

    Connection connect = DBContext.getInstance().getConnection();

    @Override
    public int save(LogSystem log) {
        int result = 0;
        String sql;
        if (log.getTimestamp() != null) {
            sql = "INSERT INTO Logs (UserID, Action, Timestamp) VALUES (?, ?, ?)";
        } else {
            sql = "INSERT INTO Logs (UserID, Action) VALUES (?, ?)";
        }
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, log.getUser().getUserId());
            ps.setString(2, log.getAction());
            if (log.getTimestamp() != null) {
                ps.setTimestamp(3, new Timestamp(log.getTimestamp().getTime()));
            }
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<LogSystem> findAll() {
        List<LogSystem> logs = new ArrayList<>();
        String sql = "SELECT * FROM Logs";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int logId = rs.getInt("LogID");
                int userId = rs.getInt("UserID");
                String action = rs.getString("Action");
                Timestamp ts = rs.getTimestamp("Timestamp");
                
                User user = new User();
                user.setUserId(userId);
                
                LogSystem log = new LogSystem();
                log.setLogId(logId);
                log.setUser(user);
                log.setAction(action);
                if (ts != null) {
                    log.setTimestamp(new java.util.Date(ts.getTime()));
                }
                logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }

    @Override
    public int update(LogSystem t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int logId) {
        int result = 0;
        String sql = "DELETE FROM Logs WHERE LogID = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, logId);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<LogSystem> findAllByUserId(int userId) {
        List<LogSystem> logs = new ArrayList<>();
        String sql = "SELECT * FROM Logs WHERE UserID = ? ORDER BY LogID ASC";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int logId = rs.getInt("LogID");
                String action = rs.getString("Action");
                Timestamp ts = rs.getTimestamp("Timestamp");

                User user = new User();
                user.setUserId(userId);

                LogSystem log = new LogSystem();
                log.setLogId(logId);
                log.setUser(user);
                log.setAction(action);
                if (ts != null) {
                    log.setTimestamp(new java.util.Date(ts.getTime()));
                }
                logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }

}
