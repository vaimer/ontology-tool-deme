package com.example.ontology.tool.demo.service.model.exception;

public class OntologyDuplicationException extends Exception {
    private final static String message = "Ontology with associated id already exists in the system";

    public OntologyDuplicationException() {
        super(message);
    }
}
