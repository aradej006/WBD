package com.pw.wbd.domain.entities;

import javax.persistence.*;
import javax.print.Doc;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by arade on 20-Jan-16.
 */
@Entity
public class House {

    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private double landArea;
    @NotNull
    private double area;

    @NotNull
    private double pricePerMeter;
    @Size(max = 30)
    private String state;

    @ManyToOne
    private Employee employee;
    @Size(max = 30)
    private String garage;
    @Size(max = 30)
    private String playground;

    @OneToOne
    private Building building;

    private int roomsQuantity;

    public int getRoomsQuantity() {
        return roomsQuantity;
    }

    public void setRoomsQuantity(int roomsQuantity) {
        this.roomsQuantity = roomsQuantity;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLandArea() {
        return landArea;
    }

    public void setLandArea(double landArea) {
        this.landArea = landArea;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPricePerMeter() {
        return pricePerMeter;
    }

    public void setPricePerMeter(double pricePerMeter) {
        this.pricePerMeter = pricePerMeter;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGarage() {
        return garage;
    }

    public void setGarage(String garage) {
        this.garage = garage;
    }

    public String getPlayground() {
        return playground;
    }

    public void setPlayground(String playground) {
        this.playground = playground;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public String getDesc(){
        return toString();
    }

    @Override
    public String toString() {
        return "House{" +
                "area=" + area +
                ", landArea=" + landArea +
                '}';
    }
}
