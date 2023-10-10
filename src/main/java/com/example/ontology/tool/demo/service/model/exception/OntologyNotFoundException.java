package com.example.ontology.tool.demo.service.model.exception;

public class OntologyNotFoundException extends Exception {
    private final static String message = "Ontology was not found in the system";

    public OntologyNotFoundException() {
        super(message);
    }
}
