/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class InspectionRecords {
    private int vehicleID;
    private int stationID;
    private Integer inspectorID; // Có thể null, nên dùng Integer
    private Date inspectionDate;
    private Date nextInspectionDate;
    private String result;
    private Double co2Emission; // Có thể null, nên dùng Double
    private Double hcEmission;  // Có thể null, nên dùng Double
    private String comments;

    // Constructor không tham số
    public InspectionRecords() {}

    // Constructor đầy đủ tham số (không có recordID)
    public InspectionRecords(int vehicleID, int stationID, Integer inspectorID, Date inspectionDate, Date nextInspectionDate, String result, Double co2Emission, Double hcEmission, String comments) {
        this.vehicleID = vehicleID;
        this.stationID = stationID;
        this.inspectorID = inspectorID;
        this.inspectionDate = inspectionDate;
        this.nextInspectionDate = nextInspectionDate;
        this.result = result;
        this.co2Emission = co2Emission;
        this.hcEmission = hcEmission;
        this.comments = comments;
    }

    // Getters và Setters
    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public Integer getInspectorID() {
        return inspectorID;
    }

    public void setInspectorID(Integer inspectorID) {
        this.inspectorID = inspectorID;
    }

    public Date getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(Date inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public Date getNextInspectionDate() {
        return nextInspectionDate;
    }

    public void setNextInspectionDate(Date nextInspectionDate) {
        this.nextInspectionDate = nextInspectionDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Double getCo2Emission() {
        return co2Emission;
    }

    public void setCo2Emission(Double co2Emission) {
        this.co2Emission = co2Emission;
    }

    public Double getHcEmission() {
        return hcEmission;
    }

    public void setHcEmission(Double hcEmission) {
        this.hcEmission = hcEmission;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    // Phương thức hiển thị thông tin
    @Override
    public String toString() {
        return "InspectionRecord{" +
                "vehicleID=" + vehicleID +
                ", stationID=" + stationID +
                ", inspectorID=" + inspectorID +
                ", inspectionDate=" + inspectionDate +
                ", nextInspectionDate=" + nextInspectionDate +
                ", result='" + result + '\'' +
                ", co2Emission=" + co2Emission +
                ", hcEmission=" + hcEmission +
                ", comments='" + comments + '\'' +
                '}';
    }
}
