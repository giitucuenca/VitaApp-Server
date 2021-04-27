package com.vitaapp.backend.tesis.domain;

public class Elderly {

    private Integer elderlyId;

    private String name;

    private String surname;

    private String userName;

    private Integer carerId;

    private char gender;

    private Integer scholarityId;

    private char laterality;


    public Integer getElderlyId() {
        return elderlyId;
    }

    public void setElderlyId(Integer elderlyId) {
        this.elderlyId = elderlyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCarerId() {
        return carerId;
    }

    public void setCarerId(Integer carerId) {
        this.carerId = carerId;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Integer getScholarityId() {
        return scholarityId;
    }

    public void setScholarityId(Integer scholarityId) {
        this.scholarityId = scholarityId;
    }

    public char getLaterality() {
        return laterality;
    }

    public void setLaterality(char laterality) {
        this.laterality = laterality;
    }

    }
