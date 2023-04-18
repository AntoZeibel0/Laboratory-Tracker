package com.example.laboratorytrackersd.repo;

import com.example.laboratorytrackersd.model.Assignment;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface AssignmentRepo extends JpaRepository<Assignment, Integer>, CrudRepository<Assignment, Integer> {
    Optional<Assignment> findById(Integer id);
}
