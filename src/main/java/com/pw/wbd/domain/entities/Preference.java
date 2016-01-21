package com.pw.wbd.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * Created by arade on 20-Jan-16.
 */
@Entity
public class Preference {

    @Id
    @GeneratedValue
    private int id;
    @Size(max = 30)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc(){return toString();}

    @Override
    public String toString() {
        return "Preference{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
