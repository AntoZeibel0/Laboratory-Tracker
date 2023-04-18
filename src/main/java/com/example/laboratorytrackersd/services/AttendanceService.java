package com.example.laboratorytrackersd.services;

import com.example.laboratorytrackersd.model.Attendance;
import com.example.laboratorytrackersd.model.Laboratory;
import com.example.laboratorytrackersd.model.User;
import com.example.laboratorytrackersd.repo.AttendanceRepo;
import com.example.laboratorytrackersd.repo.LaboratoryRepo;
import com.example.laboratorytrackersd.repo.UserRepo;
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
public class AttendanceService {
    @Autowired
    private AttendanceRepo attendanceRepo;

    @Autowired
    private LaboratoryRepo laboratoryRepo;

    @Autowired
    private UserRepo userRepo;

    public Attendance createAttendance(Integer labId, Integer userId) {
        Laboratory laboratory = laboratoryRepo.findById(labId).orElseThrow();
        User user = userRepo.findById(userId).orElseThrow();

        return attendanceRepo.save(Attendance.builder()
                .labAttendance(laboratory)
                .userId(user)
                .build());
    }

    public List<Attendance> fetchAttendances(){
        return (List<Attendance>) attendanceRepo.findAll();
    }

    public void deleteAttendanceById(Integer id) {
        attendanceRepo.delete(attendanceRepo.findById(id).get());
    }

    public Attendance updateAttendance(Integer attendanceId, Integer labId, Integer userId) {
        Attendance attendanceDB = attendanceRepo.findById(attendanceId).orElseThrow();
        Laboratory laboratory = laboratoryRepo.findById(labId).orElseThrow();
        User user = userRepo.findById(userId).orElseThrow();
        attendanceDB.setLabAttendance(laboratory);
        attendanceDB.setUserId(user);
        return attendanceRepo.save(attendanceDB);
    }
}
