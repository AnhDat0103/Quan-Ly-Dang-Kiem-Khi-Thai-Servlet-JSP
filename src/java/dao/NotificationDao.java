package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Notification;
import model.User;
import java.sql.Timestamp;

public class NotificationDao implements Dao<Notification> {

    // Giả sử DBContext sử dụng kết nối đến database của bạn
    Connection connect = DBContext.getInstance().getConnection();

    @Override
    public int save(Notification notification) {
        int result = 0;
        String sql;
        // Nếu sentDate được set thì insert cả sentDate và isRead, ngược lại để SQL sử dụng DEFAULT GETDATE()
        if (notification.getSentDate() != null) {
            sql = "INSERT INTO Notifications (UserID, Message, SentDate, IsRead) VALUES (?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO Notifications (UserID, Message, IsRead) VALUES (?, ?, ?)";
        }
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, notification.getUser().getUserId());
            ps.setString(2, notification.getMessage());
            if (notification.getSentDate() != null) {
                ps.setTimestamp(3, new Timestamp(notification.getSentDate().getTime()));
                ps.setBoolean(4, notification.isIsRead());
            } else {
                ps.setBoolean(3, notification.isIsRead());
            }
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Notification> findAll() {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM Notifications";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int notificationId = rs.getInt("NotificationID");
                int userId = rs.getInt("UserID");
                String message = rs.getString("Message");
                Timestamp ts = rs.getTimestamp("SentDate");
                boolean isRead = rs.getBoolean("IsRead");

                User user = new User();
                user.setUserId(userId);

                Notification notification = new Notification();
                notification.setNotificationId(notificationId);
                notification.setUser(user);
                notification.setMessage(message);
                if (ts != null) {
                    notification.setSentDate(new Date(ts.getTime()));
                }
                notification.setIsRead(isRead);
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    @Override
    public int update(Notification t) {
        // Cài đặt update theo yêu cầu của dự án (không có trong yêu cầu hiện tại)
        return 0;
    }

    @Override
    public int delete(int notificationId) {
        int result = 0;
        String sql = "DELETE FROM Notifications WHERE NotificationID = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, notificationId);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Notification> findAllByUserId(int userId) {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM Notifications WHERE UserID = ? ORDER BY NotificationID ASC";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int notificationId = rs.getInt("NotificationID");
                String message = rs.getString("Message");
                Timestamp ts = rs.getTimestamp("SentDate");
                boolean isRead = rs.getBoolean("IsRead");

                User user = new User();
                user.setUserId(userId);

                Notification notification = new Notification();
                notification.setNotificationId(notificationId);
                notification.setUser(user);
                notification.setMessage(message);
                if (ts != null) {
                    notification.setSentDate(new Date(ts.getTime()));
                }
                notification.setIsRead(isRead);

                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    public List<Notification> getMessageByUserID(int userId) {
        List<Notification> message = new ArrayList<>();
        String sql = "SELECT Message FROM Notifications WHERE UserID = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Notification notification = new Notification(
                        rs.getString("Message")
                );
                message.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }

    public boolean addNotificationByUserID(int userId, String message) {
        String sql = "INSERT INTO Notifications (UserID, Message, SentDate, IsRead) VALUES (?, ?, GETDATE(), 0)";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, message);
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
