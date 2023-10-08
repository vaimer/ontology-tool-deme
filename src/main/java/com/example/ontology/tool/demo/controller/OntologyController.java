package com.example.ontology.tool.demo.controller;

import com.example.ontology.tool.demo.controller.model.OntologyDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
public class OntologyController {
    private static final Logger logger = LogManager.getLogger(OntologyController.class);

    @GetMapping("/ontology/{ontologyId}")
    @ResponseBody
    public OntologyDTO retrieveOntology(@PathVariable("ontologyId") String ontologyId) {
        logger.info("Trigger retrieveOntology");
        return new OntologyDTO("id", "title", "description", Collections.emptyList(), Collections.emptyList());
    }

    @PostMapping("/ontology/")
    @ResponseBody
    public OntologyDTO saveOntology(@RequestBody OntologyDTO ontology) {
        logger.info("Trigger saveOntology");
        return new OntologyDTO("id", "title", "description", Collections.emptyList(), Collections.emptyList());
    }
}
