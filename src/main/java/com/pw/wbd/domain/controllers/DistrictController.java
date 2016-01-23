package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.District;
import com.pw.wbd.domain.repositories.DistrictRepository;
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
public class DistrictController {

    @Autowired
    private DistrictRepository districtRepository;

    @RequestMapping("/district/new")
    public String newDistrict(Model model) {
        model.addAttribute("object", new District());
        return "district/form";
    }

    @RequestMapping(value = "district", method = RequestMethod.POST)
    public String saveDistrict(District district) {
        districtRepository.save(district);
        return "redirect:/district/get/" + district.getId();
    }

    @RequestMapping("district/get/{id}")
    public String showDistrict(@PathVariable Integer id, Model model) {
        model.addAttribute("object", districtRepository.findOne(id));
        return "district/show";
    }

    @RequestMapping(value = "/districts", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("objects", districtRepository.findAll());
        List<String> attrs = new LinkedList<>();
        for (Field field : District.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
        return "district/list";
    }

    @RequestMapping(value = "/districts/{attribute}/{value}", method = RequestMethod.GET)
    public String listFiltered(@PathVariable String attribute, @PathVariable String value, Model model) {
        List<District> objects = new LinkedList<>();
        if(attribute.equals("id")){
            objects.add(districtRepository.findOne(Integer.parseInt(value)));
            if(objects.get(0)==null) objects.remove(0);
        }else if(attribute.equals("name")){
            objects = districtRepository.findByName(value);
        }else if(attribute.equals("city")){
            objects = districtRepository.findByCity(value);
        }

        model.addAttribute("objects", objects);
        List<String> attrs = new LinkedList<>();
        for (Field field : District.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
        return "district/list";
    }


    @RequestMapping("district/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("object", districtRepository.getOne(id));
        return "district/form";
    }

    @RequestMapping("district/delete/{id}")
    public String delete(@PathVariable Integer id){
        districtRepository.delete(id);
        return "redirect:/districts";
    }




}
