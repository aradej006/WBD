package com.pw.wbd.domain;

import com.pw.wbd.domain.entities.Customer;
import com.pw.wbd.domain.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by arade on 20-Jan-16.
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> listAllCustomers(){return customerRepository.findAll();}
    public Customer getById(Integer id){return customerRepository.findOne(id);}
    public Customer saveCustomer(Customer customer){return customerRepository.save(customer);}
    public void deleteCustomer(Integer id) {
        customerRepository.delete(id);
    }

}
