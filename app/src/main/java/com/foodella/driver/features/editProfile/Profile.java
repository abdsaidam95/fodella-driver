package com.foodella.driver.features.editProfile;

import java.io.Serializable;

public class Profile implements Serializable {

    private String name;
    private String dateOfBirth;
    private String email;
    private String nationality;
    private String city;
    private String Job;
    private String nationalityNumber;
    private  String personalImage;
    private  String DriverImage;
    private  String ImgeUrl;

    public Profile(String name, String dateOfBirth, String email, String nationality, String city, String job, String nationalityNumber, String personalImage, String driverImage, String imgeUrl) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.nationality = nationality;
        this.city = city;
        Job = job;
        this.nationalityNumber = nationalityNumber;
        this.personalImage = personalImage;
        DriverImage = driverImage;
        ImgeUrl = imgeUrl;
    }

    public String getImgeUrl() {
        return ImgeUrl;
    }

    public void setImgeUrl(String imgeUrl) {
        ImgeUrl = imgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }

    public String getNationalityNumber() {
        return nationalityNumber;
    }

    public void setNationalityNumber(String nationalityNumber) {
        this.nationalityNumber = nationalityNumber;
    }

    public Profile(String name, String dateOfBirth, String email, String nationality, String city, String job, String nationalityNumber, String personalImage, String driverImage) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.nationality = nationality;
        this.city = city;
        Job = job;
        this.nationalityNumber = nationalityNumber;
        this.personalImage = personalImage;
        DriverImage = driverImage;
    }

    public String getPersonalImage() {
        return personalImage;
    }

    public void setPersonalImage(String personalImage) {
        this.personalImage = personalImage;
    }

    public String getDriverImage() {
        return DriverImage;
    }

    public void setDriverImage(String driverImage) {
        DriverImage = driverImage;
    }
}

