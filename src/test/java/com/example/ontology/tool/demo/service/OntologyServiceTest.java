package com.example.ontology.tool.demo.service;

import com.example.ontology.tool.demo.repository.OntologyRepository;
import com.example.ontology.tool.demo.service.model.Ontology;
import com.example.ontology.tool.demo.service.model.exception.NullOntologyIdException;
import com.example.ontology.tool.demo.service.model.exception.OntologyDuplicationException;
import com.example.ontology.tool.demo.service.model.exception.OntologyNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.example.ontology.tool.demo.TestUtils.ontologyMockEntityObject;
import static com.example.ontology.tool.demo.TestUtils.ontologyMockObject;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class OntologyServiceTest {

    private final OLSSearchService olsSearchService = Mockito.mock(OLSSearchService.class);
    private final OntologyRepository ontologyRepository = Mockito.mock(OntologyRepository.class);

    private final OntologyService ontologyService = new OntologyService(ontologyRepository, olsSearchService);

    private final static String ONTOLOGY_ID = "efo";

    @Test
    void retrieveOntology_shouldSuccessfullyRetrieveOntologyFromDB() throws OntologyNotFoundException, NullOntologyIdException {
        when(ontologyRepository.findById(anyString())).thenReturn(Optional.of(ontologyMockEntityObject));

        var result = ontologyService.retrieveOntology(ONTOLOGY_ID);

        assertThat(result).isEqualTo(ontologyMockObject);
        verify(ontologyRepository, times(1)).findById(anyString());
        verify(olsSearchService, times(0)).retrieveOntology(anyString());
    }

    @Test
    void retrieveOntology_shouldSuccessfullyRetrieveOntologyFromOLS() throws OntologyNotFoundException, NullOntologyIdException {
        when(ontologyRepository.findById(anyString())).thenReturn(Optional.empty());
        when(olsSearchService.retrieveOntology(anyString())).thenReturn(Optional.of(ontologyMockObject));

        var result = ontologyService.retrieveOntology(ONTOLOGY_ID);

        assertThat(result).isEqualTo(ontologyMockObject);
        verify(ontologyRepository, times(1)).findById(anyString());
        verify(olsSearchService, times(1)).retrieveOntology(anyString());
    }

    @Test
    void retrieveOntology_shouldThrowNullOntologyIdException_ontologyIdIsNull() {
        when(ontologyRepository.findById(anyString())).thenReturn(Optional.empty());
        when(olsSearchService.retrieveOntology(anyString())).thenReturn(Optional.of(ontologyMockObject));

        assertThrows(NullOntologyIdException.class, () -> ontologyService.retrieveOntology(null));

        verify(ontologyRepository, times(0)).findById(anyString());
        verify(olsSearchService, times(0)).retrieveOntology(anyString());
    }

    @Test
    void retrieveOntology_shouldThrowOntologyNotFoundException_ontologyDoesNotExist() {
        when(ontologyRepository.findById(anyString())).thenReturn(Optional.empty());
        when(olsSearchService.retrieveOntology(anyString())).thenReturn(Optional.empty());

        assertThrows(OntologyNotFoundException.class, () -> ontologyService.retrieveOntology(ONTOLOGY_ID));

        verify(ontologyRepository, times(1)).findById(anyString());
        verify(olsSearchService, times(1)).retrieveOntology(anyString());
    }

    @Test
    void saveOntology_shouldSuccessfullySaveOntologyToLocalDB() throws NullOntologyIdException, OntologyDuplicationException {
        when(ontologyRepository.save(any())).thenReturn(ontologyMockEntityObject);

        var result = ontologyService.saveOntology(ontologyMockObject);

        assertThat(result).isEqualTo(ontologyMockObject);

        verify(ontologyRepository, times(1)).save(any());
    }

    @Test
    void saveOntology_shouldThrowNullOntologyIdException_ontologyIdIsNull() {

        assertThrows(NullOntologyIdException.class, () -> ontologyService.saveOntology(new Ontology(null, "1", "2", emptyList(), emptyList())));


        verify(ontologyRepository, times(0)).save(any());
    }

    @Test
    void saveOntology_shouldThrowOntologyDuplicationException_ontologyAlreadyExistsInDb() {
        when(ontologyRepository.findById(anyString())).thenReturn(Optional.of(ontologyMockEntityObject));

        assertThrows(OntologyDuplicationException.class, () -> ontologyService.saveOntology(ontologyMockObject));


        verify(ontologyRepository, times(0)).save(any());
    }
}