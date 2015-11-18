package com.pw.wbd.entitites.dictionaries;

import com.pw.wbd.entitites.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by arade on 11/18/2015.
 */
@Entity
public class SalesType extends AbstractEntity {

    @NotNull
    String type;

    @Override
    public String toString() {
        return "SalesType{" +
                "type='" + type + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
