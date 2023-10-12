package com.example.ontology.tool.demo.repository;

import com.example.ontology.tool.demo.repository.model.OntologyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OntologyRepository extends MongoRepository<OntologyEntity, String> {

}
