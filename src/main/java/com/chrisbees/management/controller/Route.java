package com.chrisbees.management.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletResponse;

@Configuration
public class Route implements WebMvcConfigurer {
 //regex expression to match all url paths without /api/ prefix to the index.html file
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/{[^\\.]*}").setViewName("forward:/index.html");
    }

}
