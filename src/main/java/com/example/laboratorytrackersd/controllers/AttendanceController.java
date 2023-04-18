package com.example.laboratorytrackersd.controllers;

import com.example.laboratorytrackersd.model.Attendance;
import com.example.laboratorytrackersd.services.AttendanceService;
import com.example.laboratorytrackersd.services.LaboratoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@EnableMethodSecurity
public class AttendanceController {
    @Autowired
    AttendanceService attendanceService;

    @Autowired
    private LaboratoryService laboratoryService;

    @GetMapping("/attendance/getAttendances")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<List<Attendance>> getAttendances(){
       return ResponseEntity.ok().body(attendanceService.fetchAttendances());
    }

    @PostMapping("/attendance/createAttendance/{lab_id}/{user_id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public Attendance createAttendance(@PathVariable(name = "lab_id") Integer labId,
                                       @PathVariable(name = "user_id") Integer userId) {
        return attendanceService.createAttendance(labId, userId);
    }

    @DeleteMapping("/attendance/deleteAttendance/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<?>deleteAttendance(@PathVariable("id") Integer id){
        attendanceService.deleteAttendanceById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/attendance/updateAttendance/{attendance_id}/{lab_id}/{user_id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public Attendance updateAttendance(@PathVariable(name = "attendance_id") Integer attendanceId,
                                       @PathVariable(name = "lab_id") Integer labId,
                                       @PathVariable(name = "user_id") Integer userId) {
        return attendanceService.updateAttendance(attendanceId, labId, userId);
    }

}
