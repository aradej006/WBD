package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.Customer;
import com.pw.wbd.domain.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by arade on 20-Jan-16.
 */
@Controller
@Transactional
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/customer/new")
    public String newCustomer(Model model) {
        model.addAttribute("object", new Customer());
        return "customer/form";
    }

    @RequestMapping(value = "customer", method = RequestMethod.POST)
    public String saveCustomer(Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customer/get/" + customer.getId();
    }

    @RequestMapping("customer/get/{id}")
    public String showCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("object", customerRepository.findOne(id));
        return "customer/show";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("objects", customerRepository.findAll());
        return "customer/list";
    }

    @RequestMapping("customer/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("object", customerRepository.getOne(id));
        return "customer/form";
    }

    @RequestMapping("customer/delete/{id}")
    public String delete(@PathVariable Integer id){
        customerRepository.delete(id);
        return "redirect:/customers";
    }


}
