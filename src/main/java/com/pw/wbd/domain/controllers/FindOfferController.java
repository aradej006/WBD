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
 * Created by arade on 23-Jan-16.
 */
@Controller
@Transactional
public class FindOfferController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private SegmentRepository segmentRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private OfferStatusRepository offerStatusRepository;

    @RequestMapping(value = "/findOffer", method = RequestMethod.GET)
    public String selectCustomer(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "findOffer/chooseCustomer";
    }

    @RequestMapping(value = "/findOffer/{custId}", method = RequestMethod.GET)
    public String selectDeveloper(@PathVariable Integer custId, Model model) {
        model.addAttribute("customerId", custId);
        model.addAttribute("column", "Projects");
        model.addAttribute("columnName", "Get Projects");
        model.addAttribute("nextUrl", "project");
        model.addAttribute("data", developerRepository.findAll());
        return "findOffer/chooseDev";
    }


    @RequestMapping(value = "/findOffer/{custId}/project/{devId}", method = RequestMethod.GET)
    public String selectProject(@PathVariable(value = "custId") Integer custId, @PathVariable(value = "devId") Integer devId, Model model) {
        model.addAttribute("data", projectRepository.findByDeveloper_Id(devId));
        model.addAttribute("customerId", custId);
        model.addAttribute("column", "Buildings");
        model.addAttribute("columnName", "Get Projects");
        model.addAttribute("nextUrl", "building");
        return "findOffer/chooseDev";
    }

    @RequestMapping(value = "/findOffer/{custId}/building/{buildingId}", method = RequestMethod.GET)
    public String selectBuilding(@PathVariable(value = "custId") Integer custId, @PathVariable(value = "buildingId") Integer buildingId, Model model) {
        model.addAttribute("data", buildingRepository.findByProject_Id(buildingId));
        model.addAttribute("customerId", custId);
        model.addAttribute("column", "Building");
        model.addAttribute("columnName", "Buildings");
        model.addAttribute("nextUrl", "rp");
        return "findOffer/chooseDev";
    }

    @RequestMapping(value = "/findOffer/{custId}/rp/{rpId}", method = RequestMethod.GET)
    public String selectRP(@PathVariable(value = "custId") Integer custId, @PathVariable(value = "rpId") Integer rpId, Model model) {
        if (!houseRepository.findByBuilding_Id(rpId).isEmpty()){
            model.addAttribute("data", houseRepository.findByBuilding_Id(rpId));
        }else if (!flatRepository.findByBuilding_Id(rpId).isEmpty()){
            model.addAttribute("data", flatRepository.findByBuilding_Id(rpId));
        }else if (!segmentRepository.findByBuilding_Id(rpId).isEmpty()){
            model.addAttribute("data", segmentRepository.findByBuilding_Id(rpId));
        }
        model.addAttribute("customerId", custId);
        model.addAttribute("column", "Offers");
        model.addAttribute("columnName", "Get Offer");
        model.addAttribute("nextUrl", "offer");
        return "findOffer/chooseDev";
    }

    @RequestMapping(value = "/findOffer/{custId}/offer/{offerId}", method = RequestMethod.GET)
    public String selectOffer(@PathVariable(value = "custId") Integer custId, @PathVariable(value = "offerId") Integer offerId, Model model) {
        if (!offerRepository.findByFlat_Id(offerId).isEmpty()){
            model.addAttribute("data", offerRepository.findByFlat_Id(offerId));
        }else if (!offerRepository.findByHouse_Id(offerId).isEmpty()){
            model.addAttribute("data", offerRepository.findByHouse_Id(offerId));
        }else if (!offerRepository.findBySegment_Id(offerId).isEmpty()){
            model.addAttribute("data", offerRepository.findBySegment_Id(offerId));
        }
        model.addAttribute("customerId", custId);
        model.addAttribute("column", "Offers");
        model.addAttribute("columnName", "Get Offer");
        model.addAttribute("nextUrl", "add");
        model.addAttribute("status", "");
        model.addAttribute("offerStatus",new OfferStatus());
        return "findOffer/addOfferStatus";
    }

    @RequestMapping(value = "/findOffer/{custId}/offer/{offerId}/add/{status}", method = RequestMethod.GET)
    public String addStatus(@PathVariable(value = "custId") Integer custId,
                            @PathVariable(value = "offerId") Integer offerId,
                            @PathVariable(value = "status") String status,
                            Model model) {
        OfferStatus offerStatus = new OfferStatus();
        offerStatus.setCustomer(customerRepository.findOne(custId));
        offerStatus.setOffer(offerRepository.findOne(offerId));
        offerStatus.setStatus(status);
        offerStatusRepository.save(offerStatus);
        return "index";
    }

}
