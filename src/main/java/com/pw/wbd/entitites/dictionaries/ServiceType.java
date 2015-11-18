package com.pw.wbd.entitites.dictionaries;

import com.pw.wbd.entitites.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by arade on 11/18/2015.
 */
@Entity
public class ServiceType extends AbstractEntity {

    @NotNull
    String name;

    @Override
    public String toString() {
        return "ServiceType{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
