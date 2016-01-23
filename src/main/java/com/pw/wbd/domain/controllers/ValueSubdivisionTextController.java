package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.ValueSubdivisionText;
import com.pw.wbd.domain.repositories.SubdivisionRepository;
import com.pw.wbd.domain.repositories.PreferenceRepository;
import com.pw.wbd.domain.repositories.ValueSubdivisionTextRepository;
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
public class ValueSubdivisionTextController {

    @Autowired
    private ValueSubdivisionTextRepository valueSubdivisionTextRepository;

    @Autowired
    private PreferenceRepository preferenceRepository;

    @Autowired
    private SubdivisionRepository subdivisionRepository;

    @RequestMapping("/valueSubdivisionText/new")
    public String newValueSubdivisionText(Model model) {
        model.addAttribute("object", new ValueSubdivisionText());
        model.addAttribute("preferences", preferenceRepository.findAll());
        model.addAttribute("subdivisions", subdivisionRepository.findAll());
        return "valueSubdivisionText/form";
    }

    @RequestMapping(value = "valueSubdivisionText", method = RequestMethod.POST)
    public String saveValueSubdivisionText(ValueSubdivisionText valueSubdivisionText) {
        preferenceRepository.save(valueSubdivisionText.getPreference());
        subdivisionRepository.save(valueSubdivisionText.getSubdivision());
        valueSubdivisionTextRepository.save(valueSubdivisionText);
        return "redirect:/valueSubdivisionText/get/" + valueSubdivisionText.getId();
    }

    @RequestMapping("valueSubdivisionText/get/{id}")
    public String showValueSubdivisionText(@PathVariable Integer id, Model model) {
        model.addAttribute("object", valueSubdivisionTextRepository.findOne(id));
        return "valueSubdivisionText/show";
    }

    @RequestMapping(value = "/valueSubdivisionTexts", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("objects", valueSubdivisionTextRepository.findAll());
        List<String> attrs = new LinkedList<>();
        for (Field field : ValueSubdivisionText.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
        return "valueSubdivisionText/list";
    }

    @RequestMapping(value = "/valueSubdivisionTexts/{attribute}/{value}", method = RequestMethod.GET)
    public String listFiltered(@PathVariable String attribute, @PathVariable String value, Model model) {
        List<ValueSubdivisionText> objects = new LinkedList<>();
        if(attribute.equals("id")){
            objects.add(valueSubdivisionTextRepository.findOne(Integer.parseInt(value)));
            if(objects.get(0)==null) objects.remove(0);
        }else if(attribute.equals("subdivision")){
            objects = valueSubdivisionTextRepository.findBySubdivision_Id(Integer.parseInt(value));
        }else if(attribute.equals("preference")){
            objects = valueSubdivisionTextRepository.findByPreference_Id(Integer.parseInt(value));
        }else if(attribute.equals("value")){
            objects = valueSubdivisionTextRepository.findByValue(value);
        }

        model.addAttribute("objects", objects);
        List<String> attrs = new LinkedList<>();
        for (Field field : ValueSubdivisionText.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
        return "valueSubdivisionText/list";
    }


    @RequestMapping("valueSubdivisionText/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("object", valueSubdivisionTextRepository.getOne(id));
        model.addAttribute("preferences", preferenceRepository.findAll());
        model.addAttribute("subdivisions", subdivisionRepository.findAll());
        return "valueSubdivisionText/form";
    }

    @RequestMapping("valueSubdivisionText/delete/{id}")
    public String delete(@PathVariable Integer id){
        valueSubdivisionTextRepository.delete(id);
        return "redirect:/valueSubdivisionTexts";
    }
    
}
