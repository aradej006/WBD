package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.Building;
import com.pw.wbd.domain.entities.Customer;
import com.pw.wbd.domain.repositories.BuildingRepository;
import com.pw.wbd.domain.repositories.ProjectRepository;
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
public class BuildingController {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @RequestMapping("/building/new")
    public String newBuilding(Model model) {
        model.addAttribute("object", new Building());
        model.addAttribute("projects", projectRepository.findAll());
        return "building/form";
    }

    @RequestMapping(value = "building", method = RequestMethod.POST)
    public String saveBuilding(Building building) {
        if (building.getProject() != null) projectRepository.save(building.getProject());
        buildingRepository.save(building);
        return "redirect:/building/get/" + building.getId();
    }

    @RequestMapping("building/get/{id}")
    public String showBuilding(@PathVariable Integer id, Model model) {
        model.addAttribute("object", buildingRepository.findOne(id));
        List<String> attrs = new LinkedList<>();
        for (Field field : Building.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
        return "building/show";
    }

    @RequestMapping(value = "/buildings", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("objects", buildingRepository.findAll());
        return "building/list";
    }

    @RequestMapping(value = "/buildings/{attribute}/{value}", method = RequestMethod.GET)
    public String listFiltered(@PathVariable String attribute, @PathVariable String value, Model model) {
        List<Building> objects = new LinkedList<>();
        if(attribute.equals("id")){
            objects.add(buildingRepository.findOne(Integer.parseInt(value)));
            if(objects.get(0)==null) objects.remove(0);
        }else if(attribute.equals("city")){
            objects = buildingRepository.findByCity(value);
        }else if(attribute.equals("street")){
            objects = buildingRepository.findByStreet(value);
        }else if(attribute.equals("buildingNumber")){
            objects = buildingRepository.findByBuildingNumber(value);
        }else if(attribute.equals("postCode")){
            objects = buildingRepository.findByPostCode(value);
        }else if(attribute.equals("district")){
            objects = buildingRepository.findByDistrict(value);
        }else if(attribute.equals("florQuantity")){
            objects = buildingRepository.findByFlorQuantity(Integer.parseInt(value));
        }else if(attribute.equals("type")){
            objects = buildingRepository.findByType(value);
        }else if(attribute.equals("project")){
            objects = buildingRepository.findByProject_Id(Integer.parseInt(value));
        }

        model.addAttribute("objects", objects);
        List<String> attrs = new LinkedList<>();
        for (Field field : Building.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
        return "building/list";
    }


    @RequestMapping("building/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("object", buildingRepository.getOne(id));
        model.addAttribute("projects", projectRepository.findAll());
        return "building/form";
    }

    @RequestMapping("building/delete/{id}")
    public String delete(@PathVariable Integer id) {
        buildingRepository.delete(id);
        return "redirect:/buildings";
    }

}
