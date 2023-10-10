package com.example.ontology.tool.demo.service.model.exception;

public class OlsDataLoadingException extends Exception {
    private final static String message = "Error during loading data from OLS";

    public OlsDataLoadingException() {
        super(message);
    }
}