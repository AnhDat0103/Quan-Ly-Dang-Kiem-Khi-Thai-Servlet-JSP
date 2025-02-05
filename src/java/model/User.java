/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.enums.RoleEnums;

/**
 *
 * @author DAT
 */
public class User {
    private int userId;
    private String fullName;
    private String email;
    private String password;
    private RoleEnums role;
    private String phone;
    private String address;
    private InspectionStation inspectionStation;

    public User() {
    }

    public User(int userId, String fullName, String email, String password, RoleEnums role, String phone, String address, InspectionStation inspectionStation) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.address = address;
        this.inspectionStation = inspectionStation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEnums getRole() {
        return role;
    }

    public void setRole(RoleEnums role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public InspectionStation getInspectionStation() {
        return inspectionStation;
    }

    public void setInspectionStation(InspectionStation inspectionStation) {
        this.inspectionStation = inspectionStation;
    }
    
    

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", fullName=" + fullName + ", email=" + email + ", password=" + password + ", role=" + role + ", phone=" + phone + ", address=" + address + '}';
    }
    
}
