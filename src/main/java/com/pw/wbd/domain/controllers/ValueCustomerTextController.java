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
