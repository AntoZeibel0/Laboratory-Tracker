package com.example.laboratorytrackersd.controllers;

import com.example.laboratorytrackersd.model.Assignment;
import com.example.laboratorytrackersd.model.Attendance;
import com.example.laboratorytrackersd.services.AssignmentService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@EnableMethodSecurity
public class AssignmentController {
    @Autowired
    AssignmentService assignmentService;

    @GetMapping("/assignment/getAssignments")
    //@PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<List<Assignment>> getAssignments(){
        return ResponseEntity.ok().body(assignmentService.fetchAssignments());
    }

    @PostMapping("/assignment/createAssignment/{lab_id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public Assignment createAssignment(@RequestBody Assignment assignment,
                                       @PathVariable(name = "lab_id") Integer labId) {
        return assignmentService.createAssignment(assignment, labId);
    }

    @DeleteMapping("/assignment/deleteAssignment/{ass_id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<?> deleteAssignment(@PathVariable(name = "ass_id") Integer assId) {
        assignmentService.deleteAssignment(assId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/assignment/updateAssignment/{ass_id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public Assignment updateAssignment(@RequestBody Assignment assignment, @PathVariable(name = "ass_id") Integer assId) {
        return assignmentService.updateAssignment(assignment, assId);
    }

}
