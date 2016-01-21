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
