package com.kaustack.catalog.model;

import jakarta.persistence.*;

import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "courses", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"code", "number"}, name = "uk_course_code_number")
})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false, unique = true)
    private UUID id;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @Column(nullable = false)
    private String code;

    @Setter
    @Column(nullable = false)
    private int number;

}

