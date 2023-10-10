package com.example.ontology.tool.demo.controller;

import com.example.ontology.tool.demo.controller.model.OntologyDTO;
import com.example.ontology.tool.demo.service.OntologyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.example.ontology.tool.demo.utils.ModelUtils.fromApiToServiceModel;
import static com.example.ontology.tool.demo.utils.ModelUtils.fromServiceToApiModel;


@Controller
public class OntologyController {
    private static final Logger logger = LoggerFactory.getLogger(OntologyController.class);

    private final OntologyService ontologyService;

    public OntologyController(OntologyService ontologyService) {
        this.ontologyService = ontologyService;
    }

    @GetMapping("/ontology/{ontologyId}")
    @ResponseBody
    public OntologyDTO retrieveOntology(@PathVariable("ontologyId") String ontologyId) throws Exception {
        logger.info("Retrieve ontology request with id = {}", ontologyId);
        return fromServiceToApiModel(ontologyService.retrieveOntology(ontologyId));
    }

    @PostMapping("/ontology/")
    @ResponseBody
    public OntologyDTO saveOntology(@RequestBody OntologyDTO ontology) throws Exception {
        logger.info("Save ontology request with object = {}", ontology);
        var savingResult = ontologyService.saveOntology(fromApiToServiceModel(ontology));
        logger.info("Ontology successfully save to DB, result = {}", ontology);
        return fromServiceToApiModel(savingResult);
    }
}
