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

import java.lang.reflect.Field;
import java.util.LinkedList;
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
        List<String> attrs = new LinkedList<>();
        for (Field field : House.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
        return "house/list";
    }

    @RequestMapping(value = "/houses/{attribute}/{value}", method = RequestMethod.GET)
    public String listFiltered(@PathVariable String attribute, @PathVariable String value, Model model) {
        List<House> objects = new LinkedList<>();
        if(attribute.equals("id")){
            objects.add(houseRepository.findOne(Integer.parseInt(value)));
            if(objects.get(0)==null) objects.remove(0);
        }else if(attribute.equals("area")){
            objects = houseRepository.findByArea(Double.parseDouble(value));
        }else if(attribute.equals("landArea")){
            objects = houseRepository.findByLandArea(Double.parseDouble(value));
        }else if(attribute.equals("pricePerMeter")){
            objects = houseRepository.findByPricePerMeter(Double.parseDouble(value));
        }else if(attribute.equals("roomsQunatity")){
            objects = houseRepository.findByRoomsQuantity(Integer.parseInt(value));
        }else if(attribute.equals("state")){
            objects = houseRepository.findByState(value);
        }else if(attribute.equals("building")){
            objects = houseRepository.findByBuilding_Id(Integer.parseInt(value));
        }else if(attribute.equals("employee")){
            objects = houseRepository.findByEmployee_Id(Integer.parseInt(value));
        }

        model.addAttribute("objects", objects);
        List<String> attrs = new LinkedList<>();
        for (Field field : House.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
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
