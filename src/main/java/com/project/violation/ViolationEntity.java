package com.project.violation;


import java.util.Date;

public class ViolationEntity {

    private int violationId;
    private String violationDes;
    private int violationPoint;
    private int policeIdViolation;
    private String plateNoViolation;
    private int peopleIdViolation;
    private int violationCharge;
    private Date violationDate;
    private boolean violationActive;

    public int getViolationId() {
        return violationId;
    }

    public ViolationEntity setViolationId(int violationId) {
        this.violationId = violationId;
        return this;
    }

    public String getViolationDes() {
        return violationDes;
    }

    public ViolationEntity setViolationDes(String violationDes) {
        this.violationDes = violationDes;
        return this;
    }

    public int getViolationPoint() {
        return violationPoint;
    }

    public ViolationEntity setViolationPoint(int violationPoint) {
        this.violationPoint = violationPoint;
        return this;
    }

    public int getViolationCharge() {
        return violationCharge;
    }

    public ViolationEntity setViolationCharge(int violationCharge) {
        this.violationCharge = violationCharge;
        return this;
    }

    public Date getViolationDate() {
        return violationDate;
    }

    public ViolationEntity setViolationDate(Date violationDate) {
        this.violationDate = violationDate;
        return this;
    }

    public boolean isViolationActive() {
        return violationActive;
    }

    public ViolationEntity setViolationActive(boolean violationActive) {
        this.violationActive = violationActive;
        return this;
    }

    public int getPoliceIdViolation() {
        return policeIdViolation;
    }

    public ViolationEntity setPoliceIdViolation(int policeIdViolation) {
        this.policeIdViolation = policeIdViolation;
        return this;
    }

    public String getPlateNoViolation() {
        return plateNoViolation;
    }

    public ViolationEntity setPlateNoViolation(String plateNoViolation) {
        this.plateNoViolation = plateNoViolation;
        return this;
    }

    public int getPeopleIdViolation() {
        return peopleIdViolation;
    }

    public ViolationEntity setPeopleIdViolation(int peopleIdViolation) {
        this.peopleIdViolation = peopleIdViolation;
        return this;
    }

}
