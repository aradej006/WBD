package com.pw.wbd.entitites.dictionaries;

import com.pw.wbd.entitites.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class RealPropertyCondion extends AbstractEntity {

    @NotNull
    String condition;

    @Override
    public String toString() {
        return "RealPropertyCondion{" +
                "condition='" + condition + '\'' +
                '}';
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
