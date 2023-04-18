package com.example.laboratorytrackersd.services;

import com.example.laboratorytrackersd.model.Assignment;
import com.example.laboratorytrackersd.model.Submission;
import com.example.laboratorytrackersd.model.User;
import com.example.laboratorytrackersd.repo.AssignmentRepo;
import com.example.laboratorytrackersd.repo.SubmissionRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.type.descriptor.java.ObjectJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class SubmissionService {

    @Autowired
    SubmissionRepo submissionRepo;

    @Autowired
    AssignmentRepo assignmentRepo;
    public List<Submission> fetchSubmissions() {
        return submissionRepo.findAll();
    }

    public Submission createSubmission(User user, Submission submission, Integer assId) {
        Assignment assignment = assignmentRepo.findById(assId).orElseThrow();
        submission.setUserId(user);
        submission.setSubmissionAssignment(assignment);
        submission.setGrade(0.0f);
        return submissionRepo.save(submission);
    }

    public void deleteSubmission(Integer subId) {
        submissionRepo.delete(submissionRepo.findById(subId).orElseThrow());
    }

    public Submission updateSubmission(Submission submission, Integer subId) {
        Submission submissionDB = submissionRepo.findById(subId).orElseThrow();
//        if(Objects.nonNull(submission.getComment()) && !submission.getComment().equalsIgnoreCase("")) {
//            submissionDB.setComment(submission.getComment());
//        }
        if(Objects.nonNull(submission.getGrade()) && submission.getGrade() > 0 && submission.getGrade() < 11) {
            submissionDB.setGrade(submission.getGrade());
        }
//        if(Objects.nonNull(submission.getLink()) && !submission.getLink().equalsIgnoreCase("")) {
//            submissionDB.setLink(submission.getLink());
//        }

        return submissionRepo.save(submissionDB);
    }
}
