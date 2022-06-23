package com.chrisbees.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Route {
 //non-restful routes here (e.g. login, register, etc.)
    @RequestMapping("/")
    public String index() {
        return "forward:/index.html";
    }

}
