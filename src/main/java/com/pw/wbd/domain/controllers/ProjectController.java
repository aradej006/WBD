package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.Project;
import com.pw.wbd.domain.repositories.DeveloperRepository;
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
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private DeveloperRepository developerRepository;

    @RequestMapping("/project/new")
    public String newProject(Model model) {
        model.addAttribute("object", new Project());
        model.addAttribute("developers", developerRepository.findAll());
        return "project/form";
    }

    @RequestMapping(value = "project", method = RequestMethod.POST)
    public String saveProject(Project project) {
        if (project.getDeveloper()!= null) developerRepository.save(project.getDeveloper());
        projectRepository.save(project);
        return "redirect:/project/get/" + project.getId();
    }

    @RequestMapping("project/get/{id}")
    public String showProject(@PathVariable Integer id, Model model) {
        model.addAttribute("object", projectRepository.findOne(id));
        return "project/show";
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("objects", projectRepository.findAll());
        return "project/list";
    }

    @RequestMapping("project/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("object", projectRepository.getOne(id));
        model.addAttribute("developers", developerRepository.findAll());
        return "project/form";
    }

    @RequestMapping("project/delete/{id}")
    public String delete(@PathVariable Integer id){
        projectRepository.delete(id);
        return "redirect:/projects";
    }
    
}
