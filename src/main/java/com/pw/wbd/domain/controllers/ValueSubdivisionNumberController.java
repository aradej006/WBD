package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.ValueSubdivisionNumber;
import com.pw.wbd.domain.repositories.SubdivisionRepository;
import com.pw.wbd.domain.repositories.PreferenceRepository;
import com.pw.wbd.domain.repositories.ValueSubdivisionNumberRepository;
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
public class ValueSubdivisionNumberController {

    @Autowired
    private ValueSubdivisionNumberRepository valueSubdivisionNumberRepository;

    @Autowired
    private PreferenceRepository preferenceRepository;

    @Autowired
    private SubdivisionRepository subdivisionRepository;

    @RequestMapping("/valueSubdivisionNumber/new")
    public String newValueSubdivisionNumber(Model model) {
        model.addAttribute("object", new ValueSubdivisionNumber());
        model.addAttribute("preferences", preferenceRepository.findAll());
        model.addAttribute("subdivisions", subdivisionRepository.findAll());
        return "valueSubdivisionNumber/form";
    }

    @RequestMapping(value = "valueSubdivisionNumber", method = RequestMethod.POST)
    public String saveValueSubdivisionNumber(ValueSubdivisionNumber valueSubdivisionNumber) {
        preferenceRepository.save(valueSubdivisionNumber.getPreference());
        subdivisionRepository.save(valueSubdivisionNumber.getSubdivision());
        valueSubdivisionNumberRepository.save(valueSubdivisionNumber);
        return "redirect:/valueSubdivisionNumber/get/" + valueSubdivisionNumber.getId();
    }

    @RequestMapping("valueSubdivisionNumber/get/{id}")
    public String showValueSubdivisionNumber(@PathVariable Integer id, Model model) {
        model.addAttribute("object", valueSubdivisionNumberRepository.findOne(id));
        return "valueSubdivisionNumber/show";
    }

    @RequestMapping(value = "/valueSubdivisionNumbers", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("objects", valueSubdivisionNumberRepository.findAll());
        return "valueSubdivisionNumber/list";
    }

    @RequestMapping("valueSubdivisionNumber/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("object", valueSubdivisionNumberRepository.getOne(id));
        model.addAttribute("preferences", preferenceRepository.findAll());
        model.addAttribute("subdivisions", subdivisionRepository.findAll());
        return "valueSubdivisionNumber/form";
    }

    @RequestMapping("valueSubdivisionNumber/delete/{id}")
    public String delete(@PathVariable Integer id){
        valueSubdivisionNumberRepository.delete(id);
        return "redirect:/valueSubdivisionNumbers";
    }
}
