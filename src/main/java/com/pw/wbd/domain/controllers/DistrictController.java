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
