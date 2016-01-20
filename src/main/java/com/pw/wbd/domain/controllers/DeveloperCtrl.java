package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.Developer;
import com.pw.wbd.domain.repositories.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by arade on 19-Jan-16.
 */
@Controller
@Transactional
@RequestMapping("/developer")
public class DeveloperCtrl {

    @Autowired
    private DeveloperRepository developerRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String init() {
        Developer developer = new Developer();
        developer.setName("dev2");
        developerRepository.save(developer);
        System.out.println("OK");
        return "done";
    }

}
