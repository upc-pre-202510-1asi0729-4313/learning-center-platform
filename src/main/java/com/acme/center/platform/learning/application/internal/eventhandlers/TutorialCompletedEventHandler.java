package com.acme.center.platform.learning.application.internal.eventhandlers;

import com.acme.center.platform.learning.domain.model.events.TutorialCompleteEvent;
import com.acme.center.platform.learning.domain.model.queries.GetEnrollmentByIdQuery;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class TutorialCompletedEventHandler {


    @EventListener(TutorialCompleteEvent.class)
    public void on(TutorialCompleteEvent event) {
        var getEnrollmentId = new GetEnrollmentByIdQuery(event.getEnrollmentId());
        var tutorialId = event.getTutorialId();
        // demas ops
    }
}
