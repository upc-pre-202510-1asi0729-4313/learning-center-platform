package com.acme.center.platform.learning.domain.model.aggregates;

import com.acme.center.platform.learning.domain.model.events.TutorialCompleteEvent;
import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.center.platform.learning.domain.model.valueobjects.EnrollmentStatus;
import com.acme.center.platform.learning.domain.model.valueobjects.ProgressRecord;
import com.acme.center.platform.learning.domain.model.valueobjects.TutorialId;
import com.acme.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
public class Enrollment extends AuditableAbstractAggregateRoot<Enrollment> {

    @Getter
    @Embedded
    private AcmeStudentRecordId auditableStudentRecordId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Embedded
    private ProgressRecord progressRecord;

    private EnrollmentStatus status;

    public Enrollment() {

    }

    public Enrollment(AcmeStudentRecordId acmeStudentRecordId, Course course) {
        this.auditableStudentRecordId = acmeStudentRecordId;
        this.course = course;
        this.status = EnrollmentStatus.REQUESTED;
        this.progressRecord = new ProgressRecord();
    }

    public void confirm() {
        this.status = EnrollmentStatus.CONFIRMED;
        //this.progressRecord.initializeProgressRecord(this, course.getLearningPath());
    }

    public void completeTutorial(TutorialId tutorialId) {
        this.progressRecord.completeTutorial(tutorialId, course.getLearningPath());
        this.registerEvent(new TutorialCompleteEvent(this, this.getId(), tutorialId));
    }


}
