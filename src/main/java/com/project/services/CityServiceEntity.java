package com.project.services;

import java.util.Date;

public class CityServiceEntity {

    private int serviceCode;
    private int serviceCost;
    private Date serviceTime;
    private String serviceDes;
    private String plateNo;
    private int peopleId;
    private int carNo;


    public int getServiceCode() {
        return serviceCode;
    }

    public CityServiceEntity setServiceCode(int serviceCode) {
        this.serviceCode = serviceCode;
        return this;
    }

    public int getServiceCost() {
        return serviceCost;
    }

    public CityServiceEntity setServiceCost(int serviceCost) {
        this.serviceCost = serviceCost;
        return this;
    }

    public Date getServiceTime() {
        return serviceTime;
    }

    public CityServiceEntity setServiceTime(Date serviceTime) {
        this.serviceTime = serviceTime;
        return this;
    }

    public String getServiceDes() {
        return serviceDes;
    }

    public void setServiceDes(String serviceDes) {
        this.serviceDes = serviceDes;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public int getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(int peopleId) {
        this.peopleId = peopleId;
    }

    public int getCarNo() {
        return carNo;
    }

    public void setCarNo(int carNo) {
        this.carNo = carNo;
    }
}
