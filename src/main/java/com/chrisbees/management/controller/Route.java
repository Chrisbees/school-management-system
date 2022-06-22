package com.chrisbees.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class Route {

        @RequestMapping(value = "/**{path:[^\\\\.]*}")
        public void redirect(HttpServletResponse response) throws IOException {
            // Forward to home page so that angular routing is preserved.
            response.sendRedirect("/");
        }
    }
