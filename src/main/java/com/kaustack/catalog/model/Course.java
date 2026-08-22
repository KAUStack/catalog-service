package com.kaustack.catalog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "course", uniqueConstraints = {
        @UniqueConstraint(name = "course_code_number_key", columnNames = {"code", "number"})
})
public class Course {
    @Id
    private String id;

    private String title;
    private String code;
    private String number;

    @OneToMany(mappedBy = "course")
    @JsonIgnoreProperties("course")
    private List<Section> sections;
}
