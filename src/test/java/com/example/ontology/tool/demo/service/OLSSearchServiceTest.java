package com.example.ontology.tool.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.example.ontology.tool.demo.TestUtils.olsOntologyMock;
import static com.example.ontology.tool.demo.TestUtils.ontologyMockObject;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class OLSSearchServiceTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

    private OLSSearchService olsSearchService = new OLSSearchService(restTemplate, objectMapper);
    @Test
    void retrieveOntology_shouldSuccessfullyLoadOntology_returnOntology() {
        when(restTemplate.getForObject(anyString(), any(), anyString())).thenReturn(olsOntologyMock);

        var result = olsSearchService.retrieveOntology("efo");

        verify(restTemplate, times(1)).getForObject(anyString(), any(), anyString());
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(ontologyMockObject);
    }

    @Test
    void retrieveOntology_shouldFailOnParsingWrongJsonObject_returnNull() {
        when(restTemplate.getForObject(anyString(), any(), anyString())).thenReturn(ResponseEntity.notFound());

        var result = olsSearchService.retrieveOntology("efo");

        verify(restTemplate, times(1)).getForObject(anyString(), any(), anyString());
        assertThat(result.isPresent()).isFalse();
    }
}