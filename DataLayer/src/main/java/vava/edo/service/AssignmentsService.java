package vava.edo.service;

import org.springframework.stereotype.Service;
import vava.edo.model.Assignments;
import vava.edo.repository.AssignmentsRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service that operates over 'assignments' database table
 */
@Service
public class AssignmentsService {
    private final AssignmentsRepository assignmentsRepository;

    public AssignmentsService(AssignmentsRepository assignmentsRepository) {
        this.assignmentsRepository = assignmentsRepository;
    }


    /**
     * Method returns all assignments in database
     *
     * @return list of all assignments
     */
    public List<Assignments> getAllAssignments() {
        return assignmentsRepository.findAll();
    }


    /**
     * Method returns assignment with given ID
     *
     * @param assId getting assignment ID
     * @return assignment
     */
    public Optional<Assignments> getAssignmentsById(int assId) {
        return assignmentsRepository.findById(assId);
    }
}
