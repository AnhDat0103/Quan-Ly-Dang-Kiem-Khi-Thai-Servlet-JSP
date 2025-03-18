/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import model.ResetPassword;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author DAT
 */
public class ResetPasswordDao implements Dao<ResetPassword> {

    Connection connection = DBContext.getInstance().getConnection();
    UserDao ud = new UserDao();

    @Override
    public int save(ResetPassword t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ResetPassword> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(ResetPassword t) {
        int result = 0;
        String sql = "Update password_reset_tokens set Token = ?, Expiry_date = ? where UserID = ?";
        try {
            PreparedStatement pt = connection.prepareStatement(sql);
            pt.setString(1, t.getToken());
            pt.setTimestamp(2, new Timestamp(t.getExpiryDate().getTime()));
            pt.setInt(3, t.getUser().getUserId());
            result = pt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(String id) {
        try {
            String sql = "DELETE FROM password_reset_tokens WHERE Token = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, id);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void saveResetToken(int userId, String token, LocalDateTime expiryTime) {
        try {
            String sql = "INSERT INTO password_reset_tokens (UserID, Token, Expiry_date) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setString(2, token);
            stmt.setTimestamp(3, Timestamp.valueOf(expiryTime));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Date getExpiryTimeByToken(String token) {
        try {
            String sql = "SELECT Expiry_date FROM password_reset_tokens WHERE Token = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, token);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("Expiry_date");
                return timestamp != null ? new Date(timestamp.getTime()) : null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

   public boolean checkIsResetToken(String token) {
    String sql = "SELECT COUNT(*) FROM password_reset_tokens WHERE Token = ? AND Expiry_date > ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, token);
        stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
        
        try (ResultSet rs = stmt.executeQuery()) {
            return rs.next() && rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace(); 
    }
    return false;
}

    @Override
    public int delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<ResetPassword> findAllByUserId(int userId) {
        List<ResetPassword> rps = new ArrayList<>();
        try {
            String sql = "select * from password_reset_tokens where UserID = ?";
            PreparedStatement pt = connection.prepareStatement(sql);
            pt.setInt(1, userId);
            ResultSet rs =  pt.executeQuery();
            while(rs.next()) {
                rps.add(new ResetPassword(rs.getString("Token"), rs.getDate("Expiry_date"), ud.findUserById(rs.getInt("UserID"))));
            }
            return rps;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
