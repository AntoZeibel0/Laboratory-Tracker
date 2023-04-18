package com.example.laboratorytrackersd.repo;

import com.example.laboratorytrackersd.model.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LaboratoryRepo extends JpaRepository<Laboratory, Integer>, CrudRepository<Laboratory, Integer> {
    Optional<Laboratory> findById(Integer id);
}
