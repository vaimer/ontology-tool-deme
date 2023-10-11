package com.example.ontology.tool.demo.service.model.exception;

public class NullOntologyIdException extends Exception {
    private final static String message = "Ontology id must be provided";

    public NullOntologyIdException() {
        super(message);
    }
}