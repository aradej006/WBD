package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.House;
import com.pw.wbd.domain.repositories.BuildingRepository;
import com.pw.wbd.domain.repositories.EmployeeRepository;
import com.pw.wbd.domain.repositories.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by arade on 21-Jan-16.
 */
@Controller
@Transactional
public class HouseController {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping("/house/new")
    public String newHouse(Model model) {
        model.addAttribute("object", new House());
        model.addAttribute("buildings", buildingRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        return "house/form";
    }

    @RequestMapping(value = "house", method = RequestMethod.POST)
    public String saveHouse(House house) {
        buildingRepository.save(house.getBuilding());
        if(house.getEmployee()!=null) employeeRepository.save(house.getEmployee());
        houseRepository.save(house);
        return "redirect:/house/get/" + house.getId();
    }

    @RequestMapping("house/get/{id}")
    public String showHouse(@PathVariable Integer id, Model model) {
        model.addAttribute("object", houseRepository.findOne(id));
        return "house/show";
    }

    @RequestMapping(value = "/houses", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("objects", houseRepository.findAll());
        return "house/list";
    }

    @RequestMapping("house/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("object", houseRepository.getOne(id));
        model.addAttribute("buildings", buildingRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        return "house/form";
    }

    @RequestMapping("house/delete/{id}")
    public String delete(@PathVariable Integer id){
        houseRepository.delete(id);
        return "redirect:/houses";
    }
}
