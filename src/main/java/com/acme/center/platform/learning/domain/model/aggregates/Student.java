package com.acme.center.platform.learning.domain.model.aggregates;

import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.center.platform.learning.domain.model.valueobjects.ProfileId;
import com.acme.center.platform.learning.domain.model.valueobjects.StrudentPerformanceMetricSet;
import com.acme.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Student extends AuditableAbstractAggregateRoot<Student> {

    @Getter
    @Embedded
    @Column(name = "acme_student_id")
    private AcmeStudentRecordId acmeStudentRecordId;

    @Embedded
    private ProfileId profileId;

    @Embedded
    private StrudentPerformanceMetricSet performanceMetricSet;

    public Student() {
        super();
        this.acmeStudentRecordId = new AcmeStudentRecordId();
        this.performanceMetricSet = new StrudentPerformanceMetricSet();
    }
}
