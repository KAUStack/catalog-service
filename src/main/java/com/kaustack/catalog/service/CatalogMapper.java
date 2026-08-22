package com.kaustack.catalog.service;

import com.kaustack.catalog.dto.SectionDTO;
import com.kaustack.catalog.model.Section;
import com.kaustack.catalog.model.Schedule;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CatalogMapper {

    public SectionDTO toDTO(Section section) {
        SectionDTO dto = new SectionDTO();
        dto.setId(section.getId());
        dto.setCrn(section.getCrn());
        dto.setSectionCode(section.getCode());
        dto.setBranch(section.getBranch());
        dto.setScheduleType(section.getScheduleType());
        dto.setInstructionMethod(section.getInstructionMethod());
        dto.setLevel(section.getLevel());
        dto.setCredits(section.getCredits());

        if (section.getCourse() != null) {
            dto.setCourseId(section.getCourse().getId());
            dto.setCourseTitle(section.getCourse().getTitle());
            dto.setCourseCode(section.getCourse().getCode());
            dto.setCourseNumber(section.getCourse().getNumber());
        }

        if (section.getTerm() != null) {
            dto.setTermName(section.getTerm().getName());
            dto.setTermCode(section.getTerm().getTermCode());
        }

        if (section.getInstructor() != null) {
            dto.setInstructorId(section.getInstructor().getId());
            dto.setInstructorName(section.getInstructor().getName());
            dto.setInstructorEmail(section.getInstructor().getEmail());
        } else {
            dto.setInstructorName("TBA");
        }

        if (section.getSchedules() != null) {
            dto.setSchedules(section.getSchedules().stream()
                    .map(this::toScheduleDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    private SectionDTO.ScheduleDTO toScheduleDTO(Schedule schedule) {
        SectionDTO.ScheduleDTO dto = new SectionDTO.ScheduleDTO();
        dto.setId(schedule.getId());
        dto.setType(schedule.getType());
        dto.setDays(schedule.getDays());
        dto.setTime(schedule.getRawTime());
        dto.setStartTime(schedule.getStartTime());
        dto.setEndTime(schedule.getEndTime());
        dto.setRoom(schedule.getLocation());
        dto.setDateRange(schedule.getDateRange());

        // Schedule-specific instructor (may differ from the section's primary instructor)
        if (schedule.getInstructor() != null) {
            dto.setInstructor(schedule.getInstructor().getName());
        } else {
            dto.setInstructor("TBA");
        }

        return dto;
    }
}
