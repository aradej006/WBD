package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.Agreement;
import com.pw.wbd.domain.entities.Customer;
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
public class AgreementController {

    @Autowired
    private AgreementRepository agreementRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SegmentRepository segmentRepository;

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private HouseRepository houseRepository;

    @RequestMapping("/agreement/new")
    public String newAgreement(Model model) {
        model.addAttribute("object", new Agreement());
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("houses", houseRepository.findAll());
        model.addAttribute("segments", segmentRepository.findAll());
        model.addAttribute("flats", flatRepository.findAll());
        return "agreement/form";
    }

    @RequestMapping(value = "agreement", method = RequestMethod.POST)
    public String saveAgreement(Agreement agreement) {
        customerRepository.save(agreement.getCustomer());
        employeeRepository.save(agreement.getEmployee());
        if(agreement.getFlat()!=null) flatRepository.save(agreement.getFlat());
        if(agreement.getHouse()!=null) houseRepository.save(agreement.getHouse());
        if(agreement.getSegment()!=null) segmentRepository.save(agreement.getSegment());
        agreementRepository.save(agreement);
        return "redirect:/agreement/get/" + agreement.getId();
    }

    @RequestMapping("agreement/get/{id}")
    public String showAgreement(@PathVariable Integer id, Model model) {
        model.addAttribute("object", agreementRepository.findOne(id));
        return "agreement/show";
    }

    @RequestMapping(value = "/agreements", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("objects", agreementRepository.findAll());
        List<String> attrs = new LinkedList<>();
        for (Field field : Agreement.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
        return "agreement/list";
    }

    @RequestMapping(value = "/agreements/{attribute}/{value}", method = RequestMethod.GET)
    public String listFiltered(@PathVariable String attribute, @PathVariable String value, Model model) {
        List<Agreement> objects = new LinkedList<>();
        if(attribute.equals("id")){
            objects.add(agreementRepository.findOne(Integer.parseInt(value)));
            if(objects.get(0)==null) objects.remove(0);
        }else if(attribute.equals("type")){
            objects = agreementRepository.findByType(value);
        }else if(attribute.equals("customer")){
            objects = agreementRepository.findByCustomer_Id(Integer.parseInt(value));
        }else if(attribute.equals("employee")){
            objects = agreementRepository.findByEmployee_Id(Integer.parseInt(value));
        }else if(attribute.equals("segment")){
            objects = agreementRepository.findBySegment_Id(Integer.parseInt(value));
        }else if(attribute.equals("house")){
            objects = agreementRepository.findByHouse_Id(Integer.parseInt(value));
        }else if(attribute.equals("flat")){
            objects = agreementRepository.findByFlat_Id(Integer.parseInt(value));
        }

        model.addAttribute("objects", objects);
        List<String> attrs = new LinkedList<>();
        for (Field field : Agreement.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
        return "agreement/list";
    }

    @RequestMapping("agreement/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("object", agreementRepository.getOne(id));
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("houses", houseRepository.findAll());
        model.addAttribute("segments", segmentRepository.findAll());
        model.addAttribute("flats", flatRepository.findAll());
        return "agreement/form";
    }

    @RequestMapping("agreement/delete/{id}")
    public String delete(@PathVariable Integer id){
        agreementRepository.delete(id);
        return "redirect:/agreements";
    }
    
    
}
