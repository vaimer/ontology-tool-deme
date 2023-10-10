package com.example.ontology.tool.demo.service;

import com.example.ontology.tool.demo.repository.OntologyRepository;
import com.example.ontology.tool.demo.service.model.Ontology;
import com.example.ontology.tool.demo.service.model.exception.OntologyDuplicationException;
import com.example.ontology.tool.demo.service.model.exception.OntologyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.example.ontology.tool.demo.utils.ModelUtils.fromDbToServiceModel;
import static com.example.ontology.tool.demo.utils.ModelUtils.fromServiceToDbModel;

@Service
public class OntologyService {

    private static final Logger logger = LoggerFactory.getLogger(OntologyService.class);
    private final OntologyRepository ontologyRepository;
    private final OLSSearchService olsSearchService;

    OntologyService(OntologyRepository ontologyRepository, OLSSearchService olsSearchService) {
        this.ontologyRepository = ontologyRepository;
        this.olsSearchService = olsSearchService;
    }

    public Ontology retrieveOntology(String ontologyId) throws OntologyNotFoundException {
        var localOntology = ontologyRepository.findById(ontologyId);

        if (localOntology.isPresent()) {
            return fromDbToServiceModel(localOntology.get());
        }

        var olsOntology = olsSearchService.retrieveOntology(ontologyId);

        if (olsOntology.isPresent()) {
            return olsOntology.get();
        }

        throw new OntologyNotFoundException();
    }

    public Ontology saveOntology(Ontology ontology) throws OntologyDuplicationException {
        var ontologyEntity = ontologyRepository.findById(ontology.id());

        if (ontologyEntity.isPresent()) {
            throw new OntologyDuplicationException();
        }

        return fromDbToServiceModel(ontologyRepository.save(fromServiceToDbModel(ontology)));
    }

}
