package com.pw.wbd.entitites;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Developer extends AbstractEntity {

    @NotNull
    @OneToOne
    Person person;


    @OneToMany
    List<RealProperty> realProperties;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<RealProperty> getRealProperties() {
        return realProperties;
    }

    public void setRealProperties(List<RealProperty> realProperties) {
        this.realProperties = realProperties;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "person=" + person +
                ", realProperties=" + realProperties +
                '}';
    }
}
