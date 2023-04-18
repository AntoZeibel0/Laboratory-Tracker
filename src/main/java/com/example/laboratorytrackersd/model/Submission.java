package com.example.laboratorytrackersd.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "submissions")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer submissionId;

    @Column(name = "link")
    private String link;

    @Column(name = "comment")
    private String comment;

    @Column(name = "grade")
    private Float grade;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "submission_assignment")
    private Assignment submissionAssignment;
}
