package com.example.ontology.tool.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResourcesController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
