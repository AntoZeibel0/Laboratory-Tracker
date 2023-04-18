package com.example.laboratorytrackersd.services;

import com.example.laboratorytrackersd.model.Assignment;
import com.example.laboratorytrackersd.model.Laboratory;
import com.example.laboratorytrackersd.model.Submission;
import com.example.laboratorytrackersd.repo.AssignmentRepo;
import com.example.laboratorytrackersd.repo.LaboratoryRepo;
import com.example.laboratorytrackersd.repo.SubmissionRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class AssignmentService {

    @Autowired
    private AssignmentRepo assignmentRepo;

    @Autowired
    private LaboratoryRepo laboratoryRepo;

    @Autowired
    private SubmissionRepo submissionRepo;

    public List<Assignment> fetchAssignments() {
        return (List<Assignment>) assignmentRepo.findAll();
    }

    public Assignment createAssignment(Assignment assignment, Integer lab_id) {
        Laboratory laboratory = laboratoryRepo.findById(lab_id).orElseThrow();
        assignment.setLabAssignment(laboratory);
        return assignmentRepo.save(assignment);
    }

    public void deleteAssignment(Integer id) {
        Assignment assignment = assignmentRepo.findById(id).orElseThrow();
        assignmentRepo.delete(assignment);
    }

    public Assignment updateAssignment(Assignment assignment, Integer assId) {
        Assignment assignmentDB = assignmentRepo.findById(assId).orElseThrow();
        if(Objects.nonNull(assignment.getDeadline())) {
            assignmentDB.setDeadline(assignment.getDeadline());
        }
        if(Objects.nonNull(assignment.getDescription())){
            assignmentDB.setDescription(assignment.getDescription());
        }
        return assignmentRepo.save(assignmentDB);
    }
}
