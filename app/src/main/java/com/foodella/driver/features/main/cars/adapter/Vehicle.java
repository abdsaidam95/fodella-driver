package com.foodella.driver.features.main.cars.adapter;

import java.io.Serializable;

public class Vehicle  implements Serializable {

    private String vehicleName = "";
    private String vehicleNumber = "";
    private String manufacturingYear = "";
    private String vehicleColor = "";
    private String insuranceType = "";
    private String dateOfBuild = "";
    private String status = "";
    private String vehicleType = "";
    private String vehicleImage = "";
    private String vehicleLicenseImage = "";
    private String insuranceCertificateImage = "";


    boolean isSelected = false;

    public Vehicle() {
    }

    public Vehicle(String cityName, boolean isSelected) {
        this.vehicleName = cityName;
        this.isSelected = isSelected;
    }

    public Vehicle(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public Vehicle(String vehicleName, String vehicleNumber, String dateOfBuild, String status) {
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
        this.dateOfBuild = dateOfBuild;
        this.status = status;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getDateOfBuild() {
        return dateOfBuild;
    }

    public void setDateOfBuild(String dateOfBuild) {
        this.dateOfBuild = dateOfBuild;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getManufacturingYear() {
        return manufacturingYear;
    }

    public void setManufacturingYear(String manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(String vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleLicenseImage() {
        return vehicleLicenseImage;
    }

    public void setVehicleLicenseImage(String vehicleLicenseImage) {
        this.vehicleLicenseImage = vehicleLicenseImage;
    }

    public String getInsuranceCertificateImage() {
        return insuranceCertificateImage;
    }

    public void setInsuranceCertificateImage(String insuranceCertificateImage) {
        this.insuranceCertificateImage = insuranceCertificateImage;
    }
}
