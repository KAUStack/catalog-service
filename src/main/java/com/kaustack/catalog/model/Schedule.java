package com.kaustack.catalog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "schedule")
public class Schedule {
    @Id
    private String id;

    private String type;

    @Column(name = "start_time")
    private Integer startTime;

    @Column(name = "end_time")
    private Integer endTime;

    @Column(name = "raw_time")
    private String rawTime;

    private String days;

    private String location;

    @Column(name = "date_range")
    private String dateRange;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", nullable = false)
    @JsonIgnoreProperties("schedules")
    private Section section;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    @JsonIgnoreProperties("schedules")
    private Instructor instructor;
}
