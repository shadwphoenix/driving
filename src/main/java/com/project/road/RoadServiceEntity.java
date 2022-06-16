package com.project.road;


import java.util.Date;

public class RoadServiceEntity {

    private int roadCode;
    private int roadCost;
    private Date roadTime;
    private String PlateNumber;
    private int peopleId;
    private int carNumber;
    private String plateNo;
    private String desRoad;


    public String getPlateNo() {
        return plateNo;
    }

    public RoadServiceEntity setPlateNo(String plateNo) {
        this.plateNo = plateNo;
        return this;
    }

    public int getRoadCode() {
        return roadCode;
    }

    public RoadServiceEntity setRoadCode(int roadCode) {
        this.roadCode = roadCode;
        return this;
    }

    public int getRoadCost() {
        return roadCost;
    }

    public RoadServiceEntity setRoadCost(int roadCost) {
        this.roadCost = roadCost;
        return this;
    }

    public Date getRoadTime() {
        return roadTime;
    }

    public RoadServiceEntity setRoadTime(Date roadTime) {
        this.roadTime = roadTime;
        return this;
    }

    public String getPlateNumber() {
        return PlateNumber;
    }

    public RoadServiceEntity setPlateNumber(String plateNumber) {
        PlateNumber = plateNumber;
        return this;
    }

    public int getPeopleId() {
        return peopleId;
    }

    public RoadServiceEntity setPeopleId(int peopleId) {
        this.peopleId = peopleId;
        return this;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public RoadServiceEntity setCarNumber(int carNumber) {
        this.carNumber = carNumber;
        return this;
    }

    public String getDesRoad() {
        return desRoad;
    }

    public RoadServiceEntity setDesRoad(String desRoad) {
        this.desRoad = desRoad;
        return this;
    }
}
