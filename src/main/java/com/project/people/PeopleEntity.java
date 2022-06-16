package com.project.people;

import java.util.Date;

public class PeopleEntity {
    
    private int licenseId;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private String sex;
    private Date createTime;
    private Date lastLogin;

    public int getLicenseId() {
        return licenseId;
    }

    public PeopleEntity setLicenseId(int licenseId) {
        this.licenseId = licenseId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PeopleEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PeopleEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public PeopleEntity setBirthday(Date birth_day) {
        this.birthDay = birth_day;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public PeopleEntity setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public PeopleEntity setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public PeopleEntity setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }
}
