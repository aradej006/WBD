package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.*;
import com.pw.wbd.domain.repositories.*;
import oracle.jdbc.proxy.annotation.Pre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by arade on 22-Jan-16.
 */
@Controller
@Transactional
public class FindByPreferencesController {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private SegmentRepository segmentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PreferenceRepository preferenceRepository;

    @Autowired
    private ValueCustomerNumberRepository valueCustomerNumberRepository;

    @Autowired
    private ValueCustomerTextRepository valueCustomerTextRepository;

    @RequestMapping("/findByPreference/new")
    public String newDeveloper(Model model) {
        model.addAttribute("object", new Developer());
        return "findByPreference/form";
    }

    @RequestMapping(value = "/findByPreferences", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "findByPreference/chooseCustomer";
    }

    @RequestMapping(value = "/findByPreference/flat/{id}", method = RequestMethod.GET)
    public String getFlats(@PathVariable Integer id, Model model) {
        Customer customer = customerRepository.findOne(id);
        model.addAttribute("customer", customer);
        List<Preference> preferences = preferenceRepository.findAll();
        double startArea =0 , endArea=0, startPrice=0, endPrice=0,startFlor=0, endFlor=0;
        List<ValueCustomerText> districts = new LinkedList<>();
        for (Preference preference : preferences) {
            if(preference.getName().equals("Piętro")){
                List<ValueCustomerNumber> flors = valueCustomerNumberRepository.findByCustomerAndPreference(customer, preference);
                if(!flors.isEmpty()){
                    if(flors.get(0).getValue() > flors.get(1).getValue()){
                        endFlor = flors.get(0).getValue();
                        startFlor = flors.get(1).getValue();
                    }else{
                        endFlor = flors.get(1).getValue();
                        startFlor = flors.get(0).getValue();
                    }
                }
            }else if(preference.getName().equals("Cena za metr")){
                List<ValueCustomerNumber> price = valueCustomerNumberRepository.findByCustomerAndPreference(customer, preference);
                if(!price.isEmpty()){
                    if(price.get(0).getValue() > price.get(1).getValue()){
                        endPrice = price.get(0).getValue();
                        startPrice = price.get(1).getValue();
                    }else{
                        endPrice = price.get(1).getValue();
                        startPrice = price.get(0).getValue();
                    }
                }
            }else if(preference.getName().equals("Metraż")){
                List<ValueCustomerNumber> area = valueCustomerNumberRepository.findByCustomerAndPreference(customer, preference);
                if(!area.isEmpty()){
                    if(area.get(0).getValue() > area.get(1).getValue()){
                        endArea = area.get(0).getValue();
                        startArea = area.get(1).getValue();
                    }else{
                        endArea = area.get(1).getValue();
                        startArea = area.get(0).getValue();
                    }
                }
            }else if(preference.getName().equals("District")){
                districts = valueCustomerTextRepository.findByCustomerAndPreference(customer,preference);

            }
        }
        List<Flat> flats = new LinkedList<>();
        for (ValueCustomerText district : districts) {
            flats.addAll(flatRepository.findByPricePerMeterBetweenAndAreaBetweenAndFlorBetweenAndBuilding_District(
                    startPrice,endPrice,startArea,endArea,(int)startFlor,(int)endFlor,district.getValue()));
        }


        model.addAttribute("startPrice", startPrice);
        model.addAttribute("endPrice", endPrice);
        model.addAttribute("startArea", startArea);
        model.addAttribute("endArea", endArea);
        model.addAttribute("startFlor", startFlor);
        model.addAttribute("endFlor", endFlor);
        model.addAttribute("districts", districts);
        model.addAttribute("flats", flats);

        return "findByPreference/flat";
    }

    @RequestMapping(value = "/findByPreference/segment/{id}", method = RequestMethod.GET)
    public String getSegments(@PathVariable Integer id, Model model) {
        Customer customer = customerRepository.findOne(id);
        model.addAttribute("customer", customer);
        List<Preference> preferences = preferenceRepository.findAll();
        double startArea =0 , endArea=0, startPrice=0, endPrice=0,startFlor=0, endFlor=0;
        List<ValueCustomerText> districts = new LinkedList<>();
        for (Preference preference : preferences) {
            if(preference.getName().equals("Piętro")){
                List<ValueCustomerNumber> flors = valueCustomerNumberRepository.findByCustomerAndPreference(customer, preference);
                if(!flors.isEmpty()){
                    if(flors.get(0).getValue() > flors.get(1).getValue()){
                        endFlor = flors.get(0).getValue();
                        startFlor = flors.get(1).getValue();
                    }else{
                        endFlor = flors.get(1).getValue();
                        startFlor = flors.get(0).getValue();
                    }
                }
            }else if(preference.getName().equals("Cena za metr")){
                List<ValueCustomerNumber> price = valueCustomerNumberRepository.findByCustomerAndPreference(customer, preference);
                if(!price.isEmpty()){
                    if(price.get(0).getValue() > price.get(1).getValue()){
                        endPrice = price.get(0).getValue();
                        startPrice = price.get(1).getValue();
                    }else{
                        endPrice = price.get(1).getValue();
                        startPrice = price.get(0).getValue();
                    }
                }
            }else if(preference.getName().equals("Metraż")){
                List<ValueCustomerNumber> area = valueCustomerNumberRepository.findByCustomerAndPreference(customer, preference);
                if(!area.isEmpty()){
                    if(area.get(0).getValue() > area.get(1).getValue()){
                        endArea = area.get(0).getValue();
                        startArea = area.get(1).getValue();
                    }else{
                        endArea = area.get(1).getValue();
                        startArea = area.get(0).getValue();
                    }
                }
            }else if(preference.getName().equals("District")){
                districts = valueCustomerTextRepository.findByCustomerAndPreference(customer,preference);

            }
        }
        List<Segment> flats = new LinkedList<>();
        for (ValueCustomerText district : districts) {
            flats.addAll(segmentRepository.findByPricePerMeterBetweenAndAreaBetweenAndFlorBetweenAndBuilding_District(startPrice,endPrice,startArea,endArea,(int)startFlor,(int)endFlor, district.getValue()));
        }


        model.addAttribute("startPrice", startPrice);
        model.addAttribute("endPrice", endPrice);
        model.addAttribute("startArea", startArea);
        model.addAttribute("endArea", endArea);
        model.addAttribute("startFlor", startFlor);
        model.addAttribute("endFlor", endFlor);
        model.addAttribute("districts", districts);
        model.addAttribute("segments", flats);

        return "findByPreference/segment";
    }

    @RequestMapping(value = "/findByPreference/house/{id}", method = RequestMethod.GET)
    public String getHouses(@PathVariable Integer id, Model model) {
        Customer customer = customerRepository.findOne(id);
        model.addAttribute("customer", customer);
        List<Preference> preferences = preferenceRepository.findAll();
        double startArea =0 , endArea=0, startPrice=0, endPrice=0,startLandArea=0, endLandArea=0;
        List<ValueCustomerText> districts = new LinkedList<>();
        for (Preference preference : preferences) {
            if(preference.getName().equals("Metraż Działki")){
                List<ValueCustomerNumber> flors = valueCustomerNumberRepository.findByCustomerAndPreference(customer, preference);
                if(!flors.isEmpty()){
                    if(flors.get(0).getValue() > flors.get(1).getValue()){
                        endLandArea = flors.get(0).getValue();
                        startLandArea = flors.get(1).getValue();
                    }else{
                        endLandArea = flors.get(1).getValue();
                        startLandArea = flors.get(0).getValue();
                    }
                }
            }else if(preference.getName().equals("Cena za metr")){
                List<ValueCustomerNumber> price = valueCustomerNumberRepository.findByCustomerAndPreference(customer, preference);
                if(!price.isEmpty()){
                    if(price.get(0).getValue() > price.get(1).getValue()){
                        endPrice = price.get(0).getValue();
                        startPrice = price.get(1).getValue();
                    }else{
                        endPrice = price.get(1).getValue();
                        startPrice = price.get(0).getValue();
                    }
                }
            }else if(preference.getName().equals("Metraż")){
                List<ValueCustomerNumber> area = valueCustomerNumberRepository.findByCustomerAndPreference(customer, preference);
                if(!area.isEmpty()){
                    if(area.get(0).getValue() > area.get(1).getValue()){
                        endArea = area.get(0).getValue();
                        startArea = area.get(1).getValue();
                    }else{
                        endArea = area.get(1).getValue();
                        startArea = area.get(0).getValue();
                    }
                }
            }else if(preference.getName().equals("District")){
                districts = valueCustomerTextRepository.findByCustomerAndPreference(customer,preference);

            }
        }
        List<House> flats = new LinkedList<>();
        for (ValueCustomerText district : districts) {
            flats.addAll(houseRepository.findByPricePerMeterBetweenAndAreaBetweenAndLandAreaBetweenAndBuilding_District(startPrice,endPrice,startArea,endArea,startLandArea,endLandArea,district.getValue()));
        }


        model.addAttribute("startPrice", startPrice);
        model.addAttribute("endPrice", endPrice);
        model.addAttribute("startArea", startArea);
        model.addAttribute("endArea", endArea);
        model.addAttribute("startLandArea", startLandArea);
        model.addAttribute("endLandArea", endLandArea);
        model.addAttribute("districts", districts);
        model.addAttribute("houses", flats);

        return "findByPreference/house";
    }

}
