package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.ValueCustomerNumber;
import com.pw.wbd.domain.repositories.CustomerRepository;
import com.pw.wbd.domain.repositories.PreferenceRepository;
import com.pw.wbd.domain.repositories.ValueCustomerNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by arade on 21-Jan-16.
 */
@Controller
@Transactional
public class ValueCustomerNumberController {

    @Autowired
    private ValueCustomerNumberRepository valueCustomerNumberRepository;

    @Autowired
    private PreferenceRepository preferenceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/valueCustomerNumber/new")
    public String newValueCustomerNumber(Model model) {
        model.addAttribute("object", new ValueCustomerNumber());
        model.addAttribute("preferences", preferenceRepository.findAll());
        model.addAttribute("customers", customerRepository.findAll());
        return "valueCustomerNumber/form";
    }

    @RequestMapping(value = "valueCustomerNumber", method = RequestMethod.POST)
    public String saveValueCustomerNumber(ValueCustomerNumber valueCustomerNumber) {
        preferenceRepository.save(valueCustomerNumber.getPreference());
        customerRepository.save(valueCustomerNumber.getCustomer());
        valueCustomerNumberRepository.save(valueCustomerNumber);
        return "redirect:/valueCustomerNumber/get/" + valueCustomerNumber.getId();
    }

    @RequestMapping("valueCustomerNumber/get/{id}")
    public String showValueCustomerNumber(@PathVariable Integer id, Model model) {
        model.addAttribute("object", valueCustomerNumberRepository.findOne(id));
        return "valueCustomerNumber/show";
    }

    @RequestMapping(value = "/valueCustomerNumbers", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("objects", valueCustomerNumberRepository.findAll());
        List<String> attrs = new LinkedList<>();
        for (Field field : ValueCustomerNumber.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
        return "valueCustomerNumber/list";
    }

    @RequestMapping(value = "/valueCustomerNumbers/{attribute}/{value}", method = RequestMethod.GET)
    public String listFiltered(@PathVariable String attribute, @PathVariable String value, Model model) {
        List<ValueCustomerNumber> objects = new LinkedList<>();
        if(attribute.equals("id")){
            objects.add(valueCustomerNumberRepository.findOne(Integer.parseInt(value)));
            if(objects.get(0)==null) objects.remove(0);
        }else if(attribute.equals("customer")){
            objects = valueCustomerNumberRepository.findByCustomer_Id(Integer.parseInt(value));
        }else if(attribute.equals("preference")){
            objects = valueCustomerNumberRepository.findByPreference_Id(Integer.parseInt(value));
        }else if(attribute.equals("value")){
            objects = valueCustomerNumberRepository.findByValue(Double.parseDouble(value));
        }

        model.addAttribute("objects", objects);
        List<String> attrs = new LinkedList<>();
        for (Field field : ValueCustomerNumber.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
        return "valueCustomerNumber/list";
    }

    @RequestMapping("valueCustomerNumber/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("object", valueCustomerNumberRepository.getOne(id));
        model.addAttribute("preferences", preferenceRepository.findAll());
        model.addAttribute("customers", customerRepository.findAll());
        return "valueCustomerNumber/form";
    }

    @RequestMapping("valueCustomerNumber/delete/{id}")
    public String delete(@PathVariable Integer id){
        valueCustomerNumberRepository.delete(id);
        return "redirect:/valueCustomerNumbers";
    }
    
}
