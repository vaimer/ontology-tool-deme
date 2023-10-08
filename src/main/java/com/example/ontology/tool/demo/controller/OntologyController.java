package com.example.ontology.tool.demo.controller;

import com.example.ontology.tool.demo.controller.model.OntologyDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;

@Controller
public class OntologyController {

    @GetMapping("/ontology/all")
    @ResponseBody
    public OntologyDTO retrieveOntology1() {
        return new OntologyDTO("id", "title", "description", Collections.emptyList(), Collections.emptyList());
    }

    @GetMapping("/ontology/{ontologyId}")
    @ResponseBody
    public OntologyDTO retrieveOntology(@PathVariable("ontologyId") String ontologyId) {
        return new OntologyDTO("id", "title", "description", Collections.emptyList(), Collections.emptyList());
    }

    @PostMapping("/ontology")
    @ResponseBody
    public OntologyDTO saveOntology() {
        return new OntologyDTO("id", "title", "description", Collections.emptyList(), Collections.emptyList());
    }
}
