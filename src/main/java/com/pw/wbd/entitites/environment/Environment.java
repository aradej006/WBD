package com.pw.wbd.entitites.environment;

import com.pw.wbd.entitites.AbstractEntity;
import com.pw.wbd.entitites.Address;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Environment extends AbstractEntity {

    @NotNull
    @ManyToOne
    Address address;

    @NotNull
    @JoinColumn
    String postCode;

    @NotNull
    @JoinColumn
    String district;


    @OneToMany
    List<Shop> shops;

    @OneToMany
    List<School> schools;

    @OneToMany
    List<ShoppingMall> shoppingMalls;

    @OneToMany
    List<Office> offices;

    @Override
    public String toString() {
        return "Environment{" +
                "address=" + address +
                ", postCode='" + postCode + '\'' +
                ", district='" + district + '\'' +
                ", shops=" + shops +
                ", schools=" + schools +
                ", shoppingMalls=" + shoppingMalls +
                ", offices=" + offices +
                '}';
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    public List<ShoppingMall> getShoppingMalls() {
        return shoppingMalls;
    }

    public void setShoppingMalls(List<ShoppingMall> shoppingMalls) {
        this.shoppingMalls = shoppingMalls;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
}
