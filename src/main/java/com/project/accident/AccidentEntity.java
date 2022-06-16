package com.project.accident;

import java.util.Date;

public class AccidentEntity {

    private int accidentId;
    private String accidentDes;
    private Date accidentDate;
    private int accidentPoliceId;
    private int CarNo;

    public int getAccidentId() {
        return accidentId;
    }

    public AccidentEntity setAccidentId(int accidentId) {
        this.accidentId = accidentId;
        return this;
    }

    public String getAccidentDes() {
        return accidentDes;
    }

    public AccidentEntity setAccidentDes(String accidentDes) {
        this.accidentDes = accidentDes;
        return this;
    }

    public Date getAccidentDate() {
        return accidentDate;
    }

    public AccidentEntity setAccidentDate(Date accidentDate) {
        this.accidentDate = accidentDate;
        return this;
    }


    public int getCarNo() {
        return CarNo;
    }

    public AccidentEntity setCarNo(int carNo) {
        CarNo = carNo;
        return this;
    }

    public int getAccidentPoliceId() {
        return accidentPoliceId;
    }

    public AccidentEntity setAccidentPoliceId(int accidentPoliceId) {
        this.accidentPoliceId = accidentPoliceId;
        return this;
    }
}
