package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.Preference;
import com.pw.wbd.domain.repositories.PreferenceRepository;
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
public class PreferenceController {

    @Autowired
    private PreferenceRepository preferenceRepository;

    @RequestMapping("/preference/new")
    public String newPreference(Model model) {
        model.addAttribute("object", new Preference());
        return "preference/form";
    }

    @RequestMapping(value = "preference", method = RequestMethod.POST)
    public String savePreference(Preference preference) {
        preferenceRepository.save(preference);
        return "redirect:/preference/get/" + preference.getId();
    }

    @RequestMapping("preference/get/{id}")
    public String showPreference(@PathVariable Integer id, Model model) {
        model.addAttribute("object", preferenceRepository.findOne(id));
        return "preference/show";
    }

    @RequestMapping(value = "/preferences", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("objects", preferenceRepository.findAll());
        List<String> attrs = new LinkedList<>();
        for (Field field : Preference.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
        return "preference/list";
    }

    @RequestMapping(value = "/preferences/{attribute}/{value}", method = RequestMethod.GET)
    public String listFiltered(@PathVariable String attribute, @PathVariable String value, Model model) {
        List<Preference> objects = new LinkedList<>();
        if(attribute.equals("id")){
            objects.add(preferenceRepository.findOne(Integer.parseInt(value)));
            if(objects.get(0)==null) objects.remove(0);
        }else if(attribute.equals("name")){
            objects = preferenceRepository.findByName(value);
        }

        model.addAttribute("objects", objects);
        List<String> attrs = new LinkedList<>();
        for (Field field : Preference.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
        return "preference/list";
    }

    @RequestMapping("preference/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("object", preferenceRepository.getOne(id));
        return "preference/form";
    }

    @RequestMapping("preference/delete/{id}")
    public String delete(@PathVariable Integer id){
        preferenceRepository.delete(id);
        return "redirect:/preferences";
    }


}
