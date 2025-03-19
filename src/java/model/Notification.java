/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author DAT
 */
public class Notification {
    
    private int notificationId;
    private String message;
    private Date sentDate;
    private boolean isRead;
    private User user;

    public Notification() {
    }

    public Notification(String message) {
        this.message = message;
    }
    
    
    
    public Notification(int notificationId, String message, Date sentDate, boolean isRead, User user) {
        this.notificationId = notificationId;
        this.message = message;
        this.sentDate = sentDate;
        this.isRead = isRead;
        this.user = user;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
