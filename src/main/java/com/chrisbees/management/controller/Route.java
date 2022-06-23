package com.chrisbees.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Route {

    //regex to match url pattern for all requests except /api requests (i.e. /, /login, /register)
    @RequestMapping(value = "{_:^((?!api).)*$}")
    public String index() {
        return "forward:/";
    }

    //route requests like /login, /register. /, to index.html

}
