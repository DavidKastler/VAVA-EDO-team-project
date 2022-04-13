package vava.edo.service;

import org.springframework.stereotype.Service;
import vava.edo.model.Relationships;
import vava.edo.repository.RelationshipsRepository;
import vava.edo.repository.UserRepository;

import java.util.List;

@Service
public class RelationshipsService {

    private final RelationshipsRepository relationshipsRepository;

    public RelationshipsService(RelationshipsRepository relationshipsRepository) {
        this.relationshipsRepository = relationshipsRepository;
    }


    /**
     * Method that returns all relationships
     * @return list of all relationships
     */
    public List<Relationships> getAllRelationships() {
        return relationshipsRepository.findAll();}
}
