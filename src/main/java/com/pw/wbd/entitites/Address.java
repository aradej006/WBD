package com.pw.wbd.entitites;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Address extends AbstractEntity {

    @NotNull
    String city;
    @NotNull
    String street;
    @NotNull
    String postCode;
    @NotNull
    String buildingNumber;
    @NotNull
    String district;
    @NotNull
    String faltNumber;

    @Override
    public String toString() {
        return "Address{" +
                "buildingNumber='" + buildingNumber + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", postCode='" + postCode + '\'' +
                ", district='" + district + '\'' +
                ", falatNumber='" + faltNumber + '\'' +
                '}';
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getFaltNumber() {
        return faltNumber;
    }

    public void setFaltNumber(String faltNumber) {
        this.faltNumber = faltNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
