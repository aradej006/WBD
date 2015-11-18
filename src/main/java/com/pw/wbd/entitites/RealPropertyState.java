package com.pw.wbd.entitites;

import com.pw.wbd.entitites.dictionaries.RealPropertyCondion;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by arade on 11/18/2015.
 */
@Entity
public class RealPropertyState extends AbstractEntity {

    @NotNull
    @OneToOne
    RealPropertyCondion realPropertyCondion;

    @ManyToOne
    Developer developer;

    Date to;
}
