package com.pw.wbd.entitites;

import com.pw.wbd.entitites.dictionaries.ServiceType;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Service extends AbstractEntity {

    @NotNull
    @ManyToOne
    Customer customer;

    @NotNull
    @ManyToOne
    Employee employee;

    @NotNull
    @OneToOne
    ServiceType serviceType;

    @Override
    public String toString() {
        return "Service{" +
                "serviceType=" + serviceType +
                ", employee=" + employee +
                ", customer=" + customer +
                '}';
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
}
