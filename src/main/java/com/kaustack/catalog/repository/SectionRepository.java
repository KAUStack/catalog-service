package com.kaustack.catalog.repository;

import com.kaustack.catalog.model.Course;
import com.kaustack.catalog.model.Instructor;
import com.kaustack.catalog.model.Section;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section, String>, JpaSpecificationExecutor<Section> {
    @EntityGraph(attributePaths = {"course", "instructor"})
    List<Section> findByTermId(String termId);

    @Query("""
            SELECT s.course.id AS id,
                   s.course.code AS code,
                   s.course.number AS number,
                   s.course.title AS title,
                   MAX(s.credits) AS credits
            FROM Section s
            WHERE s.term.id = :termId
            GROUP BY s.course.id, s.course.code, s.course.number, s.course.title
            """)
    List<CourseSummary> findCourseSummariesByTerm(@Param("termId") String termId);

    @Query("SELECT DISTINCT s.instructor FROM Section s WHERE s.term.id = :termId AND s.instructor IS NOT NULL")
    List<Instructor> findUniqueInstructorsByTerm(@Param("termId") String termId);

    @Query("SELECT DISTINCT s.course FROM Section s WHERE s.course.id = :courseId")
    Optional<Course> findCourseById(@Param("courseId") String courseId);

    @Query("SELECT MAX(s.credits) FROM Section s WHERE s.course.id = :courseId")
    Optional<Integer> findCreditsByCourseId(@Param("courseId") String courseId);

    @EntityGraph(attributePaths = {"course", "term", "instructor"})
    List<Section> findByTermIdAndCourseId(String termId, String courseId);
}
