package com.acme.center.platform.learning.domain.queries;

import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

/**
 * Query to get all enrollments by Acme student record id.
 * @param studentRecordId Acme student record id.
 */
public record GetAllEnrollmentsByAcmeStudentRecordIdQuery(AcmeStudentRecordId studentRecordId) {
    public GetAllEnrollmentsByAcmeStudentRecordIdQuery {
        if (studentRecordId == null || studentRecordId.studentRecordId() == null || studentRecordId.studentRecordId().isBlank())
            throw new IllegalArgumentException("Student record id is required.");
    }
}
