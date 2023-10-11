package com.example.ontology.tool.demo;

import com.example.ontology.tool.demo.controller.model.OntologyDTO;
import com.example.ontology.tool.demo.repository.model.OntologyEntity;
import com.example.ontology.tool.demo.service.model.Ontology;

import java.util.List;


public class TestUtils {

    public static final OntologyEntity ontologyMockEntityObject = new OntologyEntity(
            "efo",
            "Experimental Factor Ontology",
            "The Experimental Factor Ontology (EFO) provides a systematic description of many experimental variables available in EBI databases, and for external projects such as the NHGRI GWAS catalogue. It combines parts of several biological ontologies, such as anatomy, disease and chemical compounds. The scope of EFO is to support the annotation, analysis and visualization of data handled by many groups at the EBI and as the core ontology for OpenTargets.org",
            List.of("http://purl.obolibrary.org/obo/IAO_0000115", "http://www.ebi.ac.uk/efo/definition"),
            List.of("http://www.ebi.ac.uk/efo/alternative_term", "http://www.geneontology.org/formats/oboInOwl#hasExactSynonym")

    );

    public static final OntologyDTO ontologyMockAPIObject = new OntologyDTO(
            "efo",
            "Experimental Factor Ontology",
            "The Experimental Factor Ontology (EFO) provides a systematic description of many experimental variables available in EBI databases, and for external projects such as the NHGRI GWAS catalogue. It combines parts of several biological ontologies, such as anatomy, disease and chemical compounds. The scope of EFO is to support the annotation, analysis and visualization of data handled by many groups at the EBI and as the core ontology for OpenTargets.org",
            List.of("http://purl.obolibrary.org/obo/IAO_0000115", "http://www.ebi.ac.uk/efo/definition"),
            List.of("http://www.ebi.ac.uk/efo/alternative_term", "http://www.geneontology.org/formats/oboInOwl#hasExactSynonym")

    );

    public static final Ontology ontologyMockObject = new Ontology(
            "efo",
            "Experimental Factor Ontology",
            "The Experimental Factor Ontology (EFO) provides a systematic description of many experimental variables available in EBI databases, and for external projects such as the NHGRI GWAS catalogue. It combines parts of several biological ontologies, such as anatomy, disease and chemical compounds. The scope of EFO is to support the annotation, analysis and visualization of data handled by many groups at the EBI and as the core ontology for OpenTargets.org",
            List.of("http://purl.obolibrary.org/obo/IAO_0000115", "http://www.ebi.ac.uk/efo/definition"),
            List.of("http://www.ebi.ac.uk/efo/alternative_term", "http://www.geneontology.org/formats/oboInOwl#hasExactSynonym")

    );

    public static final String olsOntologyMock = "{\n" +
            "  \"languages\" : [ \"en\" ],\n" +
            "  \"lang\" : \"en\",\n" +
            "  \"ontologyId\" : \"efo\",\n" +
            "  \"loaded\" : \"2023-10-09T13:03:25.301744133\",\n" +
            "  \"updated\" : \"2023-10-09T13:03:25.301744133\",\n" +
            "  \"status\" : \"LOADED\",\n" +
            "  \"message\" : \"\",\n" +
            "  \"version\" : \"3.58.0\",\n" +
            "  \"fileHash\" : null,\n" +
            "  \"loadAttempts\" : 0,\n" +
            "  \"numberOfTerms\" : 50795,\n" +
            "  \"numberOfProperties\" : 224,\n" +
            "  \"numberOfIndividuals\" : 0,\n" +
            "  \"config\" : {\n" +
            "    \"id\" : \"efo\",\n" +
            "    \"versionIri\" : \"http://www.ebi.ac.uk/efo/releases/v3.58.0/efo.owl\",\n" +
            "    \"namespace\" : \"efo\",\n" +
            "    \"preferredPrefix\" : \"EFO\",\n" +
            "    \"title\" : \"Experimental Factor Ontology\",\n" +
            "    \"description\" : \"The Experimental Factor Ontology (EFO) provides a systematic description of many experimental variables available in EBI databases, and for external projects such as the NHGRI GWAS catalogue. It combines parts of several biological ontologies, such as anatomy, disease and chemical compounds. The scope of EFO is to support the annotation, analysis and visualization of data handled by many groups at the EBI and as the core ontology for OpenTargets.org\",\n" +
            "    \"homepage\" : \"http://www.ebi.ac.uk/efo\",\n" +
            "    \"version\" : \"3.58.0\",\n" +
            "    \"mailingList\" : \"efo-users@lists.sourceforge.net\",\n" +
            "    \"tracker\" : null,\n" +
            "    \"logo\" : null,\n" +
            "    \"creators\" : [ ],\n" +
            "    \"annotations\" : null,\n" +
            "    \"fileLocation\" : \"http://www.ebi.ac.uk/efo/efo.owl\",\n" +
            "    \"oboSlims\" : false,\n" +
            "    \"labelProperty\" : \"http://www.w3.org/2000/01/rdf-schema#label\",\n" +
            "    \"definitionProperties\" : [ \"http://purl.obolibrary.org/obo/IAO_0000115\", \"http://www.ebi.ac.uk/efo/definition\" ],\n" +
            "    \"synonymProperties\" : [ \"http://www.ebi.ac.uk/efo/alternative_term\", \"http://www.geneontology.org/formats/oboInOwl#hasExactSynonym\" ],\n" +
            "    \"hierarchicalProperties\" : [ \"http://purl.obolibrary.org/obo/BFO_0000050\", \"http://purl.obolibrary.org/obo/RO_0002202\" ],\n" +
            "    \"baseUris\" : [ \"http://www.ebi.ac.uk/efo/EFO_\" ],\n" +
            "    \"hiddenProperties\" : [ \"http://www.ebi.ac.uk/efo/has_flag\" ],\n" +
            "    \"preferredRootTerms\" : [ ],\n" +
            "    \"isSkos\" : false,\n" +
            "    \"allowDownload\" : false\n" +
            "  },\n" +
            "  \"baseUris\" : [ \"http://www.ebi.ac.uk/efo/EFO_\" ],\n" +
            "  \"_links\" : {\n" +
            "    \"self\" : {\n" +
            "      \"href\" : \"https://www.ebi.ac.uk/ols4/api/ontologies/efo?lang=en\"\n" +
            "    },\n" +
            "    \"terms\" : {\n" +
            "      \"href\" : \"https://www.ebi.ac.uk/ols4/api/ontologies/efo/terms\"\n" +
            "    },\n" +
            "    \"properties\" : {\n" +
            "      \"href\" : \"https://www.ebi.ac.uk/ols4/api/ontologies/efo/properties\"\n" +
            "    },\n" +
            "    \"individuals\" : {\n" +
            "      \"href\" : \"https://www.ebi.ac.uk/ols4/api/ontologies/efo/individuals\"\n" +
            "    }\n" +
            "  }\n" +
            "}";
}
