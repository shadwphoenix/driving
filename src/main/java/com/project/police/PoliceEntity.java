package com.project.police;

import com.project.people.PeopleEntity;

import java.util.Date;

public class PoliceEntity {

    private int policeId;
    private String policePass;
    private String policeFirstName;
    private String policeLastName;
    private Date joinTime;
    private Date lastLoginDate;

    public int getPoliceId() {
        return policeId;
    }

    public PoliceEntity setPoliceId(int policeId) {
        this.policeId = policeId;
        return this;
    }

    public String getPolicePass() {
        return policePass;
    }

    public PoliceEntity setPolicePass(String policePass) {
        this.policePass = policePass;
        return this;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public PoliceEntity setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
        return this;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public PoliceEntity setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }

    public String getPoliceFirstName() {
        return policeFirstName;
    }

    public PoliceEntity setPoliceFirstName(String policeFirstName) {
        this.policeFirstName = policeFirstName;
        return this;
    }

    public String getPoliceLastName() {
        return policeLastName;
    }

    public PoliceEntity setPoliceLastName(String policeLastName) {
        this.policeLastName = policeLastName;
        return this;
    }
}
