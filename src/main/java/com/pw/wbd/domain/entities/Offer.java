package com.pw.wbd.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

/**
 * Created by arade on 20-Jan-16.
 */
@Entity
public class Offer {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Flat flat;
    @ManyToOne
    private House house;
    @Size(max = 30)
    private String type;
    @ManyToOne
    private Segment segment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return toString();
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
