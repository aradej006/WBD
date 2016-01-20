package com.pw.wbd.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by arade on 20-Jan-16.
 */
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private int id;
    @Size(max = 30)
    private String position;
    @NotNull
    @Size(max = 30)
    private String firstname;
    @NotNull
    @Size(max = 30)
    private String lastname;
    @Size(max = 30)
    private String email;

    private BigDecimal phone;
    @Size(max = 15)
    private String secondname;
    @Size(max = 30)
    private BigInteger pesel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getPhone() {
        return phone;
    }

    public void setPhone(BigDecimal phone) {
        this.phone = phone;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public BigInteger getPesel() {
        return pesel;
    }

    public void setPesel(BigInteger pesel) {
        this.pesel = pesel;
    }
}
