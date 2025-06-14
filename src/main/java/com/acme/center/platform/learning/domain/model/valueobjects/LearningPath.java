package com.acme.center.platform.learning.domain.model.valueobjects;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import com.acme.center.platform.learning.domain.model.entities.LearningPathItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Embeddable
public class LearningPath {

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<LearningPathItem> learningPathItems;

    public LearningPath() {
        this.learningPathItems = List.of();
    }

    public void addItem(Course course, TutorialId tutorialId) {
        LearningPathItem learningPathItem = new LearningPathItem(course, tutorialId, null);
        LearningPathItem originalLastItem = null;
        if (!isEmpty()) originalLastItem = getLastItemInLearningPath();
        learningPathItems.add(learningPathItem);
        if (!Objects.isNull(originalLastItem)) originalLastItem.updateNextItem(learningPathItem);
    }

    private LearningPathItem getFirstItemInLearningPathWhere(Predicate<LearningPathItem> predicate) {
        return learningPathItems.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }
    private LearningPathItem getLastItemInLearningPath() {
        return this.getFirstItemInLearningPathWhere(item -> Objects.isNull(item.getNextItem()));
    }

    public boolean isEmpty() {
        return learningPathItems.isEmpty();
    }
}
