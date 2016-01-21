package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.ValueDistrictNumber;
import com.pw.wbd.domain.repositories.DistrictRepository;
import com.pw.wbd.domain.repositories.PreferenceRepository;
import com.pw.wbd.domain.repositories.ValueDistrictNumberRepository;
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
public class ValueDistrictNumberController {

    @Autowired
    private ValueDistrictNumberRepository valueDistrictNumberRepository;

    @Autowired
    private PreferenceRepository preferenceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @RequestMapping("/valueDistrictNumber/new")
    public String newValueDistrictNumber(Model model) {
        model.addAttribute("object", new ValueDistrictNumber());
        model.addAttribute("preferences", preferenceRepository.findAll());
        model.addAttribute("districts", districtRepository.findAll());
        return "valueDistrictNumber/form";
    }

    @RequestMapping(value = "valueDistrictNumber", method = RequestMethod.POST)
    public String saveValueDistrictNumber(ValueDistrictNumber valueDistrictNumber) {
        preferenceRepository.save(valueDistrictNumber.getPreference());
        districtRepository.save(valueDistrictNumber.getDistrict());
        valueDistrictNumberRepository.save(valueDistrictNumber);
        return "redirect:/valueDistrictNumber/get/" + valueDistrictNumber.getId();
    }

    @RequestMapping("valueDistrictNumber/get/{id}")
    public String showValueDistrictNumber(@PathVariable Integer id, Model model) {
        model.addAttribute("object", valueDistrictNumberRepository.findOne(id));
        return "valueDistrictNumber/show";
    }

    @RequestMapping(value = "/valueDistrictNumbers", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("objects", valueDistrictNumberRepository.findAll());
        return "valueDistrictNumber/list";
    }

    @RequestMapping("valueDistrictNumber/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("object", valueDistrictNumberRepository.getOne(id));
        model.addAttribute("preferences", preferenceRepository.findAll());
        model.addAttribute("districts", districtRepository.findAll());
        return "valueDistrictNumber/form";
    }

    @RequestMapping("valueDistrictNumber/delete/{id}")
    public String delete(@PathVariable Integer id){
        valueDistrictNumberRepository.delete(id);
        return "redirect:/valueDistrictNumbers";
    }
}
