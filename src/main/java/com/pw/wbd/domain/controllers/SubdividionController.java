package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.Subdivision;
import com.pw.wbd.domain.repositories.DeveloperRepository;
import com.pw.wbd.domain.repositories.DistrictRepository;
import com.pw.wbd.domain.repositories.SubdivisionRepository;
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
public class SubdividionController {

    @Autowired
    private SubdivisionRepository subdivisionRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @RequestMapping("/subdivision/new")
    public String newSubdivision(Model model) {
        model.addAttribute("object", new Subdivision());
        model.addAttribute("districts", districtRepository.findAll());
        return "subdivision/form";
    }

    @RequestMapping(value = "subdivision", method = RequestMethod.POST)
    public String saveSubdivision(Subdivision subdivision) {
        districtRepository.save(subdivision.getDistrict());
        subdivisionRepository.save(subdivision);
        return "redirect:/subdivision/get/" + subdivision.getId();
    }

    @RequestMapping("subdivision/get/{id}")
    public String showSubdivision(@PathVariable Integer id, Model model) {
        model.addAttribute("object", subdivisionRepository.findOne(id));
        return "subdivision/show";
    }

    @RequestMapping(value = "/subdivisions", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("objects", subdivisionRepository.findAll());
        return "subdivision/list";
    }

    @RequestMapping("subdivision/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("object", subdivisionRepository.getOne(id));
        model.addAttribute("districts", districtRepository.findAll());
        return "subdivision/form";
    }

    @RequestMapping("subdivision/delete/{id}")
    public String delete(@PathVariable Integer id){
        subdivisionRepository.delete(id);
        return "redirect:/subdivisions";
    }
}
