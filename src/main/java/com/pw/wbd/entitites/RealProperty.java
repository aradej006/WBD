package com.pw.wbd.entitites;

import com.pw.wbd.entitites.dictionaries.RealPropertyCondion;
import com.pw.wbd.entitites.dictionaries.RealPropertyType;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class RealProperty extends AbstractEntity{

    @NotNull
    Integer flor;

    @NotNull
    Double pricePerMeter;

    @NotNull
    @OneToOne
    RealPropertyType realPropertyType;

    @NotNull
    Double area;

    @NotNull
    Integer florQuantity;

    @NotNull
    @OneToOne
    Address address;

    @NotNull
    @JoinColumn
    String district;

    @OneToOne
    @JoinColumn(name="realPropertyCondion")
    RealPropertyState realPropertyState;

}
