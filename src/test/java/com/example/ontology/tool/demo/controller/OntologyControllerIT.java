package com.example.ontology.tool.demo.controller;

import com.example.ontology.tool.demo.BaseIT;
import com.example.ontology.tool.demo.controller.model.OntologyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;

import static com.example.ontology.tool.demo.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class OntologyControllerIT extends BaseIT {
    @Test
    void get_ontologyById_shouldSuccessfullyLoadOntologyFromOLS_emptyDB() {
        var response = getRestTemplate().getForEntity("/ontology/efo", OntologyDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(ontologyMockAPIObject);
    }

    @Test
    void get_ontologyById_shouldSuccessfullyLoadOntologyFromDB_dbContainsObject() {
        ontologyRepository.save(ontologyMockEntityObject);

        var response = getRestTemplate().getForEntity("/ontology/efo", OntologyDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(ontologyMockAPIObject);
    }

    @Test
    void get_ontologyById_shouldSNotFoundOntologyInDataSources_emptyDBAndOlsReturnNotFound() {
        when(restTemplate.getForObject(anyString(), any(), anyString())).thenReturn(ResponseEntity.notFound());

        assertThrows(HttpClientErrorException.NotFound.class, () -> {
            getRestTemplate().getForObject("/ontology/1", OntologyDTO.class);
        });
    }

    @Test
    void get_ontologyById_shouldSNotFoundOntologyInDataSources_idNotProvidedd() {
        when(restTemplate.getForObject(anyString(), any(), anyString())).thenReturn(ResponseEntity.notFound());

        assertThrows(HttpClientErrorException.NotFound.class, () -> {
            getRestTemplate().getForObject("/ontology/efo", OntologyDTO.class);
        });
    }

    @Test
    void post_ontology_shouldSuccessfullySaveToDB_emptyDB() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var httpEntity = new HttpEntity<>(ontologyMockAPIObject, headers);

        var response = getRestTemplate().postForEntity("/ontology/", httpEntity, OntologyDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(ontologyMockAPIObject);

        var ontologyEntity = ontologyRepository.findById(ontologyMockAPIObject.id());
        assertThat(ontologyEntity.isPresent()).isTrue();
        assertThat(ontologyEntity.get()).isEqualTo(ontologyMockEntityObject);
    }

    @Test
    void post_ontology_shouldFailWithErrorBadRequest_dbHasData_entityDuplication() {
        ontologyRepository.save(ontologyMockEntityObject);

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var httpEntity = new HttpEntity<>(ontologyMockAPIObject, headers);

        assertThrows(HttpClientErrorException.BadRequest.class, () -> {
            getRestTemplate().postForEntity("/ontology/", httpEntity, OntologyDTO.class);
        });
    }

    @Test
    void post_ontology_shouldFailWithErrorBadRequest_idNotProvided() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var httpEntity = new HttpEntity<>(new OntologyDTO(null, "", "", Collections.emptyList(), Collections.emptyList()), headers);

        assertThrows(HttpClientErrorException.BadRequest.class, () -> {
            getRestTemplate().postForEntity("/ontology/", httpEntity, OntologyDTO.class);
        });
    }
}
