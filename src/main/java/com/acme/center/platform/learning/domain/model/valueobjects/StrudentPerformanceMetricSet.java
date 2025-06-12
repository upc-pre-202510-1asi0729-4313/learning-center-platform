package com.acme.center.platform.learning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record StrudentPerformanceMetricSet(
        Integer totalCompletedCourses,
        Integer totalCompletedTutorials
) {

    public StrudentPerformanceMetricSet {
        if (totalCompletedCourses == null || totalCompletedCourses < 0)
            throw new IllegalArgumentException("Total completed courses cannot be null or less than 0");
        if (totalCompletedTutorials == null || totalCompletedTutorials < 0)
            throw new IllegalArgumentException("Total completed tutorials cannot be null or less than 0");
    }

    public StrudentPerformanceMetricSet() {
        this(0, 0);
    }
}
