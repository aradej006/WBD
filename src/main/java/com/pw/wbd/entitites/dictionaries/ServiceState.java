package com.pw.wbd.entitites.dictionaries;

import com.pw.wbd.entitites.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class ServiceState extends AbstractEntity {

    @NotNull
    String state;

    @Override
    public String toString() {
        return "ServiceState{" +
                "state='" + state + '\'' +
                '}';
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
