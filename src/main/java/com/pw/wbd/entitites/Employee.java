package com.pw.wbd.entitites;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Employee extends AbstractEntity {

    String position;

    @NotNull
    @OneToOne
    Person person;

    @Override
    public String toString() {
        return "Employee{" +
                "person=" + person +
                ", position='" + position + '\'' +
                '}';
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
