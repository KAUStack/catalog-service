package com.kaustack.catalog.repository;

/**
 * Projection for the course list. Credits live on {@code section} in the
 * KIndex schema, so a course's credits are aggregated from its sections.
 */
public interface CourseSummary {
    String getId();
    String getCode();
    String getNumber();
    String getTitle();
    Integer getCredits();
}
