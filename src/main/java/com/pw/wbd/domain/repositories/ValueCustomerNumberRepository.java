package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.Customer;
import com.pw.wbd.domain.entities.Preference;
import com.pw.wbd.domain.entities.ValueCustomerNumber;
import oracle.jdbc.proxy.annotation.Pre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by arade on 20-Jan-16.
 */
public interface ValueCustomerNumberRepository extends JpaRepository<ValueCustomerNumber, Integer> {

    List<ValueCustomerNumber> findByPreference(Preference preference);
    List<ValueCustomerNumber> findByCustomer(Customer customer);
    List<ValueCustomerNumber> findByCustomerAndPreference(Customer customer,Preference preference);
}
