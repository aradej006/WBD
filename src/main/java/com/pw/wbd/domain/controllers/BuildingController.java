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
        return "building/show";
    }

    @RequestMapping(value = "/buildings", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("objects", buildingRepository.findAll());
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
