package com.chrisbees.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
    public class RouteController {
        @RequestMapping(value = "/{path:[^\\.]*}")
        public void redirect(HttpServletResponse response) throws IOException {
            response.sendRedirect("/index.html");
        }
    }
