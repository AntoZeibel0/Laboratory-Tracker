package com.example.laboratorytrackersd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assignments")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer assignmentId;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "lab_assignment")
    @JsonIgnore
    private Laboratory labAssignment;

    @OneToMany(mappedBy = "submissionAssignment")
    @JsonIgnore
    private List<Submission> submissionList;
}
