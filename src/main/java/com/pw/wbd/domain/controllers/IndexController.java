package com.pw.wbd.domain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by arade on 20-Jan-16.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    String index() {
        return "index";
    }
}
