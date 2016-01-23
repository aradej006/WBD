package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.Developer;
import com.pw.wbd.domain.repositories.DeveloperRepository;
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
 * Created by arade on 19-Jan-16.
 */
@Controller
@Transactional
public class DeveloperController {

    @Autowired
    private DeveloperRepository developerRepository;

    @RequestMapping("/developer/new")
    public String newDeveloper(Model model) {
        model.addAttribute("object", new Developer());
        return "developer/form";
    }

    @RequestMapping(value = "developer", method = RequestMethod.POST)
    public String saveDeveloper(Developer developer) {
        developerRepository.save(developer);
        return "redirect:/developer/get/" + developer.getId();
    }

    @RequestMapping("developer/get/{id}")
    public String showDeveloper(@PathVariable Integer id, Model model) {
        Developer dev = developerRepository.findOne(id);
        model.addAttribute("object", dev);
        return "developer/show";
    }

    @RequestMapping(value = "/developers", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("objects", developerRepository.findAll());
        List<String> attrs = new LinkedList<>();
        for (Field field : Developer.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
        return "developer/list";
    }

    @RequestMapping(value = "/developers/{attribute}/{value}", method = RequestMethod.GET)
    public String listFiltered(@PathVariable String attribute, @PathVariable String value, Model model) {
        List<Developer> objects = new LinkedList<>();
        if(attribute.equals("id")){
            objects.add(developerRepository.findOne(Integer.parseInt(value)));
            if(objects.get(0)==null) objects.remove(0);
        }else if(attribute.equals("name")){
            objects = developerRepository.findByName(value);
        }

        model.addAttribute("objects", objects);
        List<String> attrs = new LinkedList<>();
        for (Field field : Developer.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
        return "developer/list";
    }

    @RequestMapping("developer/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("object", developerRepository.getOne(id));
        return "developer/form";
    }

    @RequestMapping("developer/delete/{id}")
    public String delete(@PathVariable Integer id) {
        developerRepository.delete(id);
        return "redirect:/developers";
    }

}
