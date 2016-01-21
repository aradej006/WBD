package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.Offer;
import com.pw.wbd.domain.repositories.*;
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
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SegmentRepository segmentRepository;

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private HouseRepository houseRepository;

    @RequestMapping("/offer/new")
    public String newOffer(Model model) {
        model.addAttribute("object", new Offer());
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("houses", houseRepository.findAll());
        model.addAttribute("segments", segmentRepository.findAll());
        model.addAttribute("flats", flatRepository.findAll());
        return "offer/form";
    }

    @RequestMapping(value = "offer", method = RequestMethod.POST)
    public String saveOffer(Offer offer) {
        employeeRepository.save(offer.getEmployee());
        if(offer.getFlat()!=null) flatRepository.save(offer.getFlat());
        if(offer.getHouse()!=null) houseRepository.save(offer.getHouse());
        if(offer.getSegment()!=null) segmentRepository.save(offer.getSegment());
        offerRepository.save(offer);
        return "redirect:/offer/get/" + offer.getId();
    }

    @RequestMapping("offer/get/{id}")
    public String showOffer(@PathVariable Integer id, Model model) {
        model.addAttribute("object", offerRepository.findOne(id));
        return "offer/show";
    }

    @RequestMapping(value = "/offers", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("objects", offerRepository.findAll());
        return "offer/list";
    }

    @RequestMapping("offer/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("object", offerRepository.getOne(id));
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("houses", houseRepository.findAll());
        model.addAttribute("segments", segmentRepository.findAll());
        model.addAttribute("flats", flatRepository.findAll());
        return "offer/form";
    }

    @RequestMapping("offer/delete/{id}")
    public String delete(@PathVariable Integer id){
        offerRepository.delete(id);
        return "redirect:/offers";
    }

}
