/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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

    public Vehicles() {
    }

    public Vehicles(int vehicleId,User owner, String plateNumber, String brand, String model, int manufactureYear, String engineNumber) {
        this.vehicleId = vehicleId;
        this.owner = owner;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.manufactureYear = manufactureYear;
        this.engineNumber = engineNumber;
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
