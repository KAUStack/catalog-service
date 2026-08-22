package com.kaustack.catalog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "section",
        uniqueConstraints = {
                @UniqueConstraint(name = "section_crn_term_id_key", columnNames = {"crn", "term_id"})
        },
        indexes = {
                @Index(name = "idx_term_course", columnList = "term_id, course_id")
        })
public class Section {
    @Id
    private String id;

    private Integer crn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id", nullable = false)
    @JsonIgnoreProperties("sections")
    private Term term;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    @JsonIgnoreProperties("sections")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    @JsonIgnoreProperties({"sectionsTaught", "schedules"})
    private Instructor instructor;

    private String code;
    private String branch;

    @Column(name = "schedule_type")
    private String scheduleType;

    @Column(name = "instruction_method")
    private String instructionMethod;

    private String level;

    private Integer credits;

    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties("section")
    private List<Schedule> schedules;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
