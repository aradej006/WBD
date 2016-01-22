package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.Customer;
import com.pw.wbd.domain.entities.Preference;
import com.pw.wbd.domain.entities.ValueCustomerText;
import oracle.jdbc.proxy.annotation.Pre;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by arade on 20-Jan-16.
 */
public interface ValueCustomerTextRepository extends JpaRepository<ValueCustomerText, Integer> {

    List<ValueCustomerText> findByPreference(Preference preference);
    List<ValueCustomerText> findByCustomer(Customer customer);
    List<ValueCustomerText> findByCustomerAndPreference(Customer customer, Preference preference);
}
