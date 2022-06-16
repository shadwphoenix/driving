package com.project.plates;

public class PlatesEntity {

    private String plateNo;
    private int platePeopleId;
    private int plateCarNo;


    public String getPlateNo() {
        return plateNo;
    }

    public PlatesEntity setPlateNo(String plateNo) {
        this.plateNo = plateNo;
        return this;
    }

    public int getPlatePeopleId() {
        return platePeopleId;
    }

    public PlatesEntity setPlatePeopleId(int platePeopleId) {
        this.platePeopleId = platePeopleId;
        return this;
    }

    public int getPlateCarNo() {
        return plateCarNo;
    }

    public PlatesEntity setPlateCarNo(int plateCarNo) {
        this.plateCarNo = plateCarNo;
        return this;
    }
}
