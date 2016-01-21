package com.pw.wbd.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by arade on 20-Jan-16.
 */
@Entity
public class Building {

    @Id
    @GeneratedValue
    private Integer id;
    @Size(max = 30)
    @NotNull
    private String city;
    @Size(max = 30)
    private String street;
    @Size(max = 30)
    @NotNull
    private String buildingNumber;
    @Size(max = 30)
    @NotNull
    private String postCode;
    @Size(max = 30)
    private String district;
    @NotNull

    private int florQuantity;
    @NotNull
    @Size(max = 30)
    private String type;

    @ManyToOne
    private Project project;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getFlorQuantity() {
        return florQuantity;
    }

    public void setFlorQuantity(int florQuantity) {
        this.florQuantity = florQuantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getDesc(){
        String desc = city;
        if(street!=null) desc += "," + street;
        desc += "," + buildingNumber;
        desc += "," + district;
        desc += "," + postCode;
        return desc;
    }
}
