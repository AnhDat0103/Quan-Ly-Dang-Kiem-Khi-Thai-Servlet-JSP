/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dto;

/**
 *
 * @author DAT
 */
public class Report {
    
    private int sumNumOfVehicle;
    private int sumNumOfPass;
    private int sumNumOfFail;
    private String inspectionDate;

    public Report(int sumNumOfVehicle, int sumNumOfPass, int sumNumOfFail, String date) {
        this.sumNumOfVehicle = sumNumOfVehicle;
        this.sumNumOfPass = sumNumOfPass;
        this.sumNumOfFail = sumNumOfFail;
        this.inspectionDate = date;
    }

    public int getSumNumOfVehicle() {
        return sumNumOfVehicle;
    }

    public void setSumNumOfVehicle(int sumNumOfVehicle) {
        this.sumNumOfVehicle = sumNumOfVehicle;
    }

    public int getSumNumOfPass() {
        return sumNumOfPass;
    }

    public void setSumNumOfPass(int sumNumOfPass) {
        this.sumNumOfPass = sumNumOfPass;
    }

    public int getSumNumOfFail() {
        return sumNumOfFail;
    }

    public void setSumNumOfFail(int sumNumOfFail) {
        this.sumNumOfFail = sumNumOfFail;
    }

    public String getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(String inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    
    
    
    
}
