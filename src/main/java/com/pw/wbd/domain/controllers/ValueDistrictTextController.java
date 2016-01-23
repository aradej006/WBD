package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.ValueDistrictText;
import com.pw.wbd.domain.repositories.DistrictRepository;
import com.pw.wbd.domain.repositories.PreferenceRepository;
import com.pw.wbd.domain.repositories.ValueDistrictTextRepository;
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
public class ValueDistrictTextController {

    @Autowired
    private ValueDistrictTextRepository valueDistrictTextRepository;

    @Autowired
    private PreferenceRepository preferenceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @RequestMapping("/valueDistrictText/new")
    public String newValueDistrictText(Model model) {
        model.addAttribute("object", new ValueDistrictText());
        model.addAttribute("preferences", preferenceRepository.findAll());
        model.addAttribute("districts", districtRepository.findAll());
        return "valueDistrictText/form";
    }

    @RequestMapping(value = "valueDistrictText", method = RequestMethod.POST)
    public String saveValueDistrictText(ValueDistrictText valueDistrictText) {
        preferenceRepository.save(valueDistrictText.getPreference());
        districtRepository.save(valueDistrictText.getDistrict());
        valueDistrictTextRepository.save(valueDistrictText);
        return "redirect:/valueDistrictText/get/" + valueDistrictText.getId();
    }

    @RequestMapping("valueDistrictText/get/{id}")
    public String showValueDistrictText(@PathVariable Integer id, Model model) {
        model.addAttribute("object", valueDistrictTextRepository.findOne(id));
        return "valueDistrictText/show";
    }

    @RequestMapping(value = "/valueDistrictTexts", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("objects", valueDistrictTextRepository.findAll());
        List<String> attrs = new LinkedList<>();
        for (Field field : ValueDistrictText.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
        return "valueDistrictText/list";
    }

    @RequestMapping(value = "/valueDistrictTexts/{attribute}/{value}", method = RequestMethod.GET)
    public String listFiltered(@PathVariable String attribute, @PathVariable String value, Model model) {
        List<ValueDistrictText> objects = new LinkedList<>();
        if(attribute.equals("id")){
            objects.add(valueDistrictTextRepository.findOne(Integer.parseInt(value)));
            if(objects.get(0)==null) objects.remove(0);
        }else if(attribute.equals("district")){
            objects = valueDistrictTextRepository.findByDistrict_Id(Integer.parseInt(value));
        }else if(attribute.equals("preference")){
            objects = valueDistrictTextRepository.findByPreference_Id(Integer.parseInt(value));
        }else if(attribute.equals("value")){
            objects = valueDistrictTextRepository.findByValue(value);
        }

        model.addAttribute("objects", objects);
        List<String> attrs = new LinkedList<>();
        for (Field field : ValueDistrictText.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
        return "valueDistrictText/list";
    }


    @RequestMapping("valueDistrictText/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("object", valueDistrictTextRepository.getOne(id));
        model.addAttribute("preferences", preferenceRepository.findAll());
        model.addAttribute("districts", districtRepository.findAll());
        return "valueDistrictText/form";
    }

    @RequestMapping("valueDistrictText/delete/{id}")
    public String delete(@PathVariable Integer id){
        valueDistrictTextRepository.delete(id);
        return "redirect:/valueDistrictTexts";
    }
}
