package com.example.ontology.tool.demo.service;

import com.example.ontology.tool.demo.service.model.Ontology;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class OLSSearchService {
    private static final Logger logger = LoggerFactory.getLogger(OLSSearchService.class);

    private final static String OLS_URL = "https://www.ebi.ac.uk/ols4/api/ontologies/{ontologyId}";
    private final static String ONTOLOGY_CONFIGURATION_PATH = "config";
    private final static String ID_PATH = "id";
    private final static String TITLE_PATH = "title";
    private final static String DESCRIPTION_PATH = "description";
    private final static String DEFINITION_PROPERTIES_PATH = "definitionProperties";
    private final static String SYNONYM_PROPERTIES_PATH = "synonymProperties";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    OLSSearchService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public Optional<Ontology> retrieveOntology(String ontologyId) {
        try {
            var response = restTemplate.getForObject(OLS_URL, String.class, ontologyId);

            var responseJson = objectMapper.readTree(response).path(ONTOLOGY_CONFIGURATION_PATH);

            return Optional.of(
                    new Ontology(
                            responseJson.path(ID_PATH).asText(),
                            responseJson.path(TITLE_PATH).asText(),
                            responseJson.path(DESCRIPTION_PATH).asText(),
                            objectMapper.readerForListOf(String.class).readValue(responseJson.path(DEFINITION_PROPERTIES_PATH)),
                            objectMapper.readerForListOf(String.class).readValue(responseJson.path(SYNONYM_PROPERTIES_PATH))
                    )
            );
        } catch (Exception e) {
            logger.error("Error during loading data from OLS", e);
        }

        return Optional.empty();
    }
}
