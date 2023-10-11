package com.example.ontology.tool.demo.controller.exception;

import com.example.ontology.tool.demo.service.model.exception.NullOntologyIdException;
import com.example.ontology.tool.demo.service.model.exception.OntologyDuplicationException;
import com.example.ontology.tool.demo.service.model.exception.OntologyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(OntologyDuplicationException.class)
    public ResponseEntity<Exception> handle(OntologyDuplicationException exception) {
        return handle(exception, exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OntologyNotFoundException.class)
    public ResponseEntity<Exception> handle(OntologyNotFoundException exception) {
        return handle(exception, exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullOntologyIdException.class)
    public ResponseEntity<Exception> handle(NullOntologyIdException exception) {
        return handle(exception, exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Exception> handle(Exception exception, String formattedErrorResponse, HttpStatus status) {
        if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
            logger.error("Unexpected exception returned from a controller", exception);
        } else {
            logger.warn("Response {} - {}, caused by {}", status, formattedErrorResponse, exception.getMessage());
        }

        return new ResponseEntity<>(exception, status);
    }
}
