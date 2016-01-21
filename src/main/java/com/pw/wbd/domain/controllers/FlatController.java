package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.Flat;
import com.pw.wbd.domain.repositories.BuildingRepository;
import com.pw.wbd.domain.repositories.EmployeeRepository;
import com.pw.wbd.domain.repositories.FlatRepository;
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
public class FlatController {


    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping("/flat/new")
    public String newFlat(Model model) {
        model.addAttribute("object", new Flat());
        model.addAttribute("buildings", buildingRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        return "flat/form";
    }

    @RequestMapping(value = "flat", method = RequestMethod.POST)
    public String saveFlat(Flat flat) {
        buildingRepository.save(flat.getBuilding());
        if(flat.getEmployee()!=null) employeeRepository.save(flat.getEmployee());
        flatRepository.save(flat);
        return "redirect:/flat/get/" + flat.getId();
    }

    @RequestMapping("flat/get/{id}")
    public String showFlat(@PathVariable Integer id, Model model) {
        model.addAttribute("object", flatRepository.findOne(id));
        return "flat/show";
    }

    @RequestMapping(value = "/flats", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("objects", flatRepository.findAll());
        return "flat/list";
    }

    @RequestMapping("flat/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("object", flatRepository.getOne(id));
        model.addAttribute("buildings", buildingRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        return "flat/form";
    }

    @RequestMapping("flat/delete/{id}")
    public String delete(@PathVariable Integer id){
        flatRepository.delete(id);
        return "redirect:/flats";
    }

}
