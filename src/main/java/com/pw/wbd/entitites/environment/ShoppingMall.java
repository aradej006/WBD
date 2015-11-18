package com.pw.wbd.entitites.environment;

import com.pw.wbd.entitites.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ShoppingMall extends AbstractEntity {

    @NotNull
    String name;

    @ManyToOne
    Environment environment;

    @Override
    public String toString() {
        return "Shop{" +
                "environment=" + environment +
                ", name='" + name + '\'' +
                '}';
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
