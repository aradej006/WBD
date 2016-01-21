package com.pw.wbd.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by arade on 20-Jan-16.
 */
@Entity
public class Segment {

    @Id
    @GeneratedValue private int id;

    private double area;
    private double pricePerMeter;
    @ManyToOne
    private Building building;
    @ManyToOne
    private Employee employee;
    private int flor;
    private int roomsQuantity;

    public int getRoomsQuantity() {
        return roomsQuantity;
    }

    public void setRoomsQuantity(int roomsQuantity) {
        this.roomsQuantity = roomsQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getFlor() {
        return flor;
    }

    public void setFlor(int flor) {
        this.flor = flor;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDesc(){
        return toString();
    }

    @Override
    public String toString() {
        return "Segment{" +
                "pricePerMeter=" + pricePerMeter +
                ", area=" + area +
                ", flor=" + flor +
                '}';
    }
}
