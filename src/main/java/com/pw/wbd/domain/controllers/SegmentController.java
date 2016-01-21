package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.Segment;
import com.pw.wbd.domain.repositories.BuildingRepository;
import com.pw.wbd.domain.repositories.EmployeeRepository;
import com.pw.wbd.domain.repositories.SegmentRepository;
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
public class SegmentController {

    @Autowired
    private SegmentRepository segmentRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping("/segment/new")
    public String newSegment(Model model) {
        model.addAttribute("object", new Segment());
        model.addAttribute("buildings", buildingRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        return "segment/form";
    }

    @RequestMapping(value = "segment", method = RequestMethod.POST)
    public String saveSegment(Segment segment) {
        buildingRepository.save(segment.getBuilding());
        if (segment.getEmployee() != null) employeeRepository.save(segment.getEmployee());
        segmentRepository.save(segment);
        return "redirect:/segment/get/" + segment.getId();
    }

    @RequestMapping("segment/get/{id}")
    public String showSegment(@PathVariable Integer id, Model model) {
        model.addAttribute("object", segmentRepository.findOne(id));
        return "segment/show";
    }

    @RequestMapping(value = "/segments", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("objects", segmentRepository.findAll());
        return "segment/list";
    }

    @RequestMapping("segment/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("object", segmentRepository.getOne(id));
        model.addAttribute("buildings", buildingRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        return "segment/form";
    }

    @RequestMapping("segment/delete/{id}")
    public String delete(@PathVariable Integer id) {
        segmentRepository.delete(id);
        return "redirect:/segments";
    }

}
