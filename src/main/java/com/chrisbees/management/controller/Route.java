package com.chrisbees.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class Route {
 //route all non-api requests to index.html with regex matching
    @RequestMapping(value = "/{[^\\.]*}")
    public String index() {
        return "forward:/index.html";
    }
    

}
