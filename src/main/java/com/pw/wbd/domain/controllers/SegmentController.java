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

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

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
        List<String> attrs = new LinkedList<>();
        for (Field field : Segment.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
        return "segment/list";
    }

    @RequestMapping(value = "/segments/{attribute}/{value}", method = RequestMethod.GET)
    public String listFiltered(@PathVariable String attribute, @PathVariable String value, Model model) {
        List<Segment> objects = new LinkedList<>();
        if(attribute.equals("id")){
            objects.add(segmentRepository.findOne(Integer.parseInt(value)));
            if(objects.get(0)==null) objects.remove(0);
        }else if(attribute.equals("area")){
            objects = segmentRepository.findByArea(Double.parseDouble(value));
        }else if(attribute.equals("roomsQuantity")){
            objects = segmentRepository.findByRoomsQuantity(Integer.parseInt(value));
        }else if(attribute.equals("flor")){
            objects = segmentRepository.findByFlor(Integer.parseInt(value));
        }else if(attribute.equals("pricePerMeter")){
            objects = segmentRepository.findByPricePerMeter(Double.parseDouble(value));
        }else if(attribute.equals("building")){
            objects = segmentRepository.findByBuilding_Id(Integer.parseInt(value));
        }else if(attribute.equals("employee")){
            objects = segmentRepository.findByEmployee_Id(Integer.parseInt(value));
        }

        model.addAttribute("objects", objects);
        List<String> attrs = new LinkedList<>();
        for (Field field : Segment.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
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
