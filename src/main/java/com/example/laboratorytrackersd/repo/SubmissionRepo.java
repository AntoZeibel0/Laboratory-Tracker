package com.example.laboratorytrackersd.repo;

import com.example.laboratorytrackersd.model.Assignment;
import com.example.laboratorytrackersd.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubmissionRepo extends JpaRepository<Submission, Integer>, CrudRepository<Submission, Integer> {
    Optional<Submission> findById(Integer id);
}
