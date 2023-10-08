package com.example.ontology.tool.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResourceController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
