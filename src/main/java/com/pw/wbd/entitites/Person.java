package com.pw.wbd.entitites;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by arade on 11/18/2015.
 */
@Entity
public class Person extends AbstractEntity {

    @NotNull
    String firstname;

    String secondname;

    @NotNull
    String lastname;

    String mail;

    Integer phone;

    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", secondname='" + secondname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", mail='" + mail + '\'' +
                ", phone=" + phone +
                '}';
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }
}
