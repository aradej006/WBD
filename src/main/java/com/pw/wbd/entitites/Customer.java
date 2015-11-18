package com.pw.wbd.entitites;

import com.pw.wbd.entitites.environment.Environment;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by arade on 11/18/2015.
 */
@Entity
public class Customer extends AbstractEntity {

    @NotNull
    @OneToOne
    Person person;

    @NotNull
    @OneToMany
    List<Environment> environmentList;



}
