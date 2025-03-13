/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author DAT
 */
public class InspectionStation {
    private int stationId;
    private String name;
    private String address;
    private String phone;
    private String email;
    private List<User> users;

    public InspectionStation() {
    }

    public InspectionStation(int stationId, String name) {
        this.stationId = stationId;
        this.name = name;  
    } 
    
    public InspectionStation(int stationId, String name, String address, String phone, String email) {
        this.stationId = stationId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    

    @Override
    public String toString() {
        return "InspectionStation{" + "StationId=" + stationId + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email=" + email + '}';
    }
    
    
}
