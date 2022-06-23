package com.chrisbees.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Route {

        @RequestMapping(value = "/**")
        public String redirect() {
            // Forward to home page so that angular routing is preserved.
            return "forward:/index.html";
        }
    }
