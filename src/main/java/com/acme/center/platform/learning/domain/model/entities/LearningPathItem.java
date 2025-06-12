package com.acme.center.platform.learning.domain.model.entities;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import com.acme.center.platform.learning.domain.model.valueobjects.TutorialId;
import com.acme.center.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
public class LearningPathItem extends AuditableModel {

    @ManyToOne
    @JoinColumn(name = "cource_id")
    @NotNull
    private Course course;

    @NotNull
    @Embedded
    @Column(name = "titorial_id")
    private TutorialId tutorialId;

    @ManyToOne
    @JoinColumn(name = "next_item_id")
    private LearningPathItem nextItem;

    public LearningPathItem(Course course, TutorialId tutorialId, LearningPathItem nextItem) {
        this.course = course;
        this.tutorialId = tutorialId;
        this.nextItem = nextItem;
    }

    public LearningPathItem() {
        this.tutorialId = new TutorialId(0L);
        this.nextItem = null;
    }

    public void updateNextItem(LearningPathItem nextItem) {
        this.nextItem = nextItem;
    }
}
