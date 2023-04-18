package com.example.laboratorytrackersd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "labs")
public class Laboratory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer labId;

    @Column(name = "number")
    private Integer number;

    @Column(name = "title")
    private String title;

    @Column(name = "curricula")
    private String curricula;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDate date;

    @OneToOne(mappedBy = "labAssignment")
    private Assignment assignment;

    @OneToMany(mappedBy = "labAttendance", fetch = FetchType.LAZY, orphanRemoval = false, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Attendance> attendanceList;

}
