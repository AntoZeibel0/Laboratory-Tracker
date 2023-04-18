package com.example.laboratorytrackersd.repo;

import com.example.laboratorytrackersd.model.Attendance;
import com.example.laboratorytrackersd.model.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Integer>, CrudRepository<Attendance, Integer> {
    Optional<Attendance> findById(Integer id);
}
