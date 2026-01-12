package com.kaustack.catalog.repository;

import com.kaustack.catalog.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface CourseRepository extends JpaRepository<Course, UUID> {
    Optional<Course> findById(UUID id);
    Optional<Course> findByCodeAndNumber(String code, int number);
}
