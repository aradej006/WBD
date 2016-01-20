package com.pw.wbd.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

/**
 * Created by arade on 20-Jan-16.
 */
@Entity
public class ValueDistrictText {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private District district;

    @ManyToOne
    private Preference preference;
    @Size(max = 30)
    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }
}
