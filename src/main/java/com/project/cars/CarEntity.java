package com.project.cars;

import java.util.Date;

public class CarEntity {
    private int carNo;
    private int carInsuranceNo;
    private String carModel;
    private int peopleLId;
    private Date carDate;

    public int getCarNo() {
        return carNo;
    }

    public CarEntity setCarNo(int carNo) {
        this.carNo = carNo;
        return this;
    }

    public int getCarInsuranceNo() {
        return carInsuranceNo;
    }

    public CarEntity setCarInsuranceNo(int carInsuranceNo) {
        this.carInsuranceNo = carInsuranceNo;
        return this;
    }

    public String getCarModel() {
        return carModel;
    }

    public CarEntity setCarModel(String carModel) {
        this.carModel = carModel;
        return this;
    }

    public Date getCarDate() {
        return carDate;
    }

    public CarEntity setCarDate(Date carDate) {
        this.carDate = carDate;
        return this;
    }

    public int getPeopleLId() {
        return peopleLId;
    }

    public CarEntity setPeopleLId(int peopleLId) {
        this.peopleLId = peopleLId;
        return this;
    }
}
