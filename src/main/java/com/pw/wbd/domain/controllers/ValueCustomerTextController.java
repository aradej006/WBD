package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.ValueCustomerText;
import com.pw.wbd.domain.repositories.*;
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
public class ValueCustomerTextController {

    @Autowired
    private ValueCustomerTextRepository valueCustomerTextRepository;

    @Autowired
    private PreferenceRepository preferenceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/valueCustomerText/new")
    public String newValueCustomerText(Model model) {
        model.addAttribute("object", new ValueCustomerText());
        model.addAttribute("preferences", preferenceRepository.findAll());
        model.addAttribute("customers", customerRepository.findAll());
        return "valueCustomerText/form";
    }

    @RequestMapping(value = "valueCustomerText", method = RequestMethod.POST)
    public String saveValueCustomerText(ValueCustomerText valueCustomerText) {
        preferenceRepository.save(valueCustomerText.getPreference());
        customerRepository.save(valueCustomerText.getCustomer());
        valueCustomerTextRepository.save(valueCustomerText);
        return "redirect:/valueCustomerText/get/" + valueCustomerText.getId();
    }

    @RequestMapping("valueCustomerText/get/{id}")
    public String showValueCustomerText(@PathVariable Integer id, Model model) {
        model.addAttribute("object", valueCustomerTextRepository.findOne(id));
        return "valueCustomerText/show";
    }

    @RequestMapping(value = "/valueCustomerTexts", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("objects", valueCustomerTextRepository.findAll());
        List<String> attrs = new LinkedList<>();
        for (Field field : ValueCustomerText.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
        return "valueCustomerText/list";
    }

    @RequestMapping(value = "/valueCustomerTexts/{attribute}/{value}", method = RequestMethod.GET)
    public String listFiltered(@PathVariable String attribute, @PathVariable String value, Model model) {
        List<ValueCustomerText> objects = new LinkedList<>();
        if(attribute.equals("id")){
            objects.add(valueCustomerTextRepository.findOne(Integer.parseInt(value)));
            if(objects.get(0)==null) objects.remove(0);
        }else if(attribute.equals("customer")){
            objects = valueCustomerTextRepository.findByCustomer_Id(Integer.parseInt(value));
        }else if(attribute.equals("preference")){
            objects = valueCustomerTextRepository.findByPreference_Id(Integer.parseInt(value));
        }else if(attribute.equals("value")){
            objects = valueCustomerTextRepository.findByValue(value);
        }

        model.addAttribute("objects", objects);
        List<String> attrs = new LinkedList<>();
        for (Field field : ValueCustomerText.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
        return "valueCustomerText/list";
    }

    @RequestMapping("valueCustomerText/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("object", valueCustomerTextRepository.getOne(id));
        model.addAttribute("preferences", preferenceRepository.findAll());
        model.addAttribute("customers", customerRepository.findAll());
        return "valueCustomerText/form";
    }

    @RequestMapping("valueCustomerText/delete/{id}")
    public String delete(@PathVariable Integer id){
        valueCustomerTextRepository.delete(id);
        return "redirect:/valueCustomerTexts";
    }

}
