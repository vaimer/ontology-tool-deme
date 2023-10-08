package com.example.ontology.tool.demo.controller;

import com.example.ontology.tool.demo.controller.model.OntologyDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller("/ontology")
public class OntologyController {

    @GetMapping("/{ontologyId}")
    public OntologyDTO retrieveOntology(@PathVariable("ontologyId") String ontologyId) {
        return new OntologyDTO("id", "title", "description", Collections.emptyList(), Collections.emptyList());
    }

    @PostMapping
    public OntologyDTO saveOntology() {
        return new OntologyDTO("id", "title", "description", Collections.emptyList(), Collections.emptyList());
    }
}
