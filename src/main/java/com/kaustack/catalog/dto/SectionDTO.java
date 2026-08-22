package com.kaustack.catalog.dto;

import lombok.Data;
import java.util.List;

@Data
public class SectionDTO {
    private String id;
    private Integer crn;
    private String sectionCode;

    private String courseId;
    private String courseTitle;
    private String courseCode;
    private String courseNumber;

    private String termName;
    private String termCode;

    // Primary Section Instructor
    private String instructorId;
    private String instructorName;
    private String instructorEmail;

    private String branch;
    private String scheduleType;
    private String instructionMethod;
    private String level;
    private Integer credits;

    private List<ScheduleDTO> schedules;

    @Data
    public static class ScheduleDTO {
        private String id;
        private String type;
        private String days;
        private String time;
        private Integer startTime;
        private Integer endTime;
        private String room;
        private String dateRange;
        private String instructor;
    }
}
