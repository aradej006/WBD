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
