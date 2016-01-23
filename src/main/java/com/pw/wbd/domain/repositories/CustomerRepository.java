package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.crypto.CipherSpi;
import java.util.List;

/**
 * Created by arade on 20-Jan-16.
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByFirstname(String firstname);
    List<Customer> findByLastname(String lastname);
    List<Customer> findByEmail(String email);
    List<Customer> findBySecondname(String secondname);
    List<Customer> findByServiceType(String serviceType);
    List<Customer> findBySalesType(String salesType);
    List<Customer> findByServiceState(String serviceState);
    List<Customer> findByPhone(String phone);
    List<Customer> findByPesel(String pesel);

}
