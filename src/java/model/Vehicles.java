/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.enums.vehicleEnums.vehicleEnums;

/**
 *
 * @author Lenovo
 */
public class Vehicles {
    private int vehicleId;
    private User owner;
    private String plateNumber;
    private String brand;
    private String model;
    private int manufactureYear;
    private String engineNumber;
    private vehicleEnums status;
    

    public Vehicles() {
    }
    
    public Vehicles(String plateNumber, String brand, String model){
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
    }
    

    public Vehicles(int vehicleId, User owner, String plateNumber, String brand, String model, int manufactureYear, String engineNumber, vehicleEnums status) {
        this.vehicleId = vehicleId;
        this.owner = owner;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.manufactureYear = manufactureYear;
        this.engineNumber = engineNumber;
        this.status = status;
    }

    public vehicleEnums getStatus() {
        return status;
    }

    public void setStatus(vehicleEnums status) {
        this.status = status;
    }
    
    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    } 
}
