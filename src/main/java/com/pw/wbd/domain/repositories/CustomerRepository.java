package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by arade on 20-Jan-16.
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
