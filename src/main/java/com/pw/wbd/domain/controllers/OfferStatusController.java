package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.OfferStatus;
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
public class OfferStatusController {

    @Autowired
    private OfferStatusRepository offerStatusRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OfferRepository offerRepository;

    @RequestMapping("/offerStatus/new")
    public String newOfferStatus(Model model) {
        model.addAttribute("object", new OfferStatus());
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("offers", offerRepository.findAll());
        return "offerStatus/form";
    }

    @RequestMapping(value = "offerStatus", method = RequestMethod.POST)
    public String saveOfferStatus(OfferStatus offerStatus) {
        customerRepository.save(offerStatus.getCustomer());
        offerRepository.save(offerStatus.getOffer());
        offerStatusRepository.save(offerStatus);
        return "redirect:/offerStatus/get/" + offerStatus.getId();
    }

    @RequestMapping("offerStatus/get/{id}")
    public String showOfferStatus(@PathVariable Integer id, Model model) {
        model.addAttribute("object", offerStatusRepository.findOne(id));
        return "offerStatus/show";
    }

    @RequestMapping(value = "/offerStatuses", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("objects", offerStatusRepository.findAll());
        return "offerStatus/list";
    }

    @RequestMapping("offerStatus/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("object", offerStatusRepository.getOne(id));
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("offers", offerRepository.findAll());
        return "offerStatus/form";
    }

    @RequestMapping("offerStatus/delete/{id}")
    public String delete(@PathVariable Integer id){
        offerStatusRepository.delete(id);
        return "redirect:/offerStatuses";
    }


}
