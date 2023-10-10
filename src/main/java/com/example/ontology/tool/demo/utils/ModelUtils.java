package com.example.ontology.tool.demo.utils;

import com.example.ontology.tool.demo.controller.model.OntologyDTO;
import com.example.ontology.tool.demo.repository.model.OntologyEntity;
import com.example.ontology.tool.demo.service.model.Ontology;

public class ModelUtils {
    public static Ontology fromApiToServiceModel(OntologyDTO ontologyDTO) {
        return new Ontology(
                ontologyDTO.id(),
                ontologyDTO.title(),
                ontologyDTO.description(),
                ontologyDTO.definitionProperties(),
                ontologyDTO.synonymProperties()
        );
    }

    public static OntologyDTO fromServiceToApiModel(Ontology ontology) {
        return new OntologyDTO(
                ontology.id(),
                ontology.title(),
                ontology.description(),
                ontology.definitionProperties(),
                ontology.synonymProperties()
        );
    }

    public static OntologyEntity fromServiceToDbModel(Ontology ontology) {
        return new OntologyEntity(
                ontology.id(),
                ontology.title(),
                ontology.description(),
                ontology.definitionProperties(),
                ontology.synonymProperties()
        );
    }

    public static Ontology fromDbToServiceModel(OntologyEntity ontologyEntity) {
        return new Ontology(
                ontologyEntity.id(),
                ontologyEntity.title(),
                ontologyEntity.description(),
                ontologyEntity.definitionProperties(),
                ontologyEntity.synonymProperties()
        );
    }
}
