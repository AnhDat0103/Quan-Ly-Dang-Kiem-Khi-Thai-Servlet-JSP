/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
public class Police {
    private User user;  // Đổi từ 'users' thành 'user' để tránh nhầm lẫn
    private Vehicles vehicles;
    private InspectionRecords inspectionRecords;

    // Constructor không tham số
    public Police() {
    }
    
    public Police(User user, Vehicles vehicles) {
        this.user = user;
        this.vehicles = vehicles;
    }

    public Police(User user, Vehicles vehicles, InspectionRecords inspectionRecords) {
        this.user = user;
        this.vehicles = vehicles;
        this.inspectionRecords = inspectionRecords;
    }
    

    // Getter và Setter cho User
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getter và Setter cho Vehicles
    public Vehicles getVehicles() {
        return vehicles;
    }

    public void setVehicles(Vehicles vehicles) {
        this.vehicles = vehicles;
    }

    // Getter và Setter cho InspectionRecords
    public InspectionRecords getInspectionRecords() {
        return inspectionRecords;
    }

    public void setInspectionRecords(InspectionRecords inspectionRecords) {
        this.inspectionRecords = inspectionRecords;
    }
}
