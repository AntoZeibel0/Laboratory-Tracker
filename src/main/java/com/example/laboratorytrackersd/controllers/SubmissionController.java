package com.example.laboratorytrackersd.controllers;

import com.example.laboratorytrackersd.model.Assignment;
import com.example.laboratorytrackersd.model.Submission;
import com.example.laboratorytrackersd.model.User;
import com.example.laboratorytrackersd.services.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@EnableMethodSecurity
public class SubmissionController {
    @Autowired
    SubmissionService submissionService       ;

    @GetMapping("/submission/getSubmissions")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<List<Submission>> getSubmissions(){
        return ResponseEntity.ok().body(submissionService.fetchSubmissions());
    }

    @PostMapping("/submission/createSubmission/{ass_id}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public Submission createSubmission(@RequestBody Submission submission,
                                       @PathVariable(name = "ass_id") Integer assId,
                                       Authentication authentication) {
        return submissionService.createSubmission((User)authentication.getPrincipal(),submission, assId);
    }

    @DeleteMapping("/submission/deleteSubmission/{sub_id}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<?> deleteSubmission(@PathVariable(name = "sub_id") Integer subId) {
        submissionService.deleteSubmission(subId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/submission/updateSubmission/{sub_id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public Submission updateSubmission(@RequestBody Submission submission, @PathVariable(name = "sub_id") Integer subId) {
        return submissionService.updateSubmission(submission, subId);
    }

}
