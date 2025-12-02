package tn.esprit.angulartraining.mapper;

import tn.esprit.angulartraining.dto.FeedbackDto;
import tn.esprit.angulartraining.model.Feedback;
import tn.esprit.angulartraining.model.User;
import tn.esprit.angulartraining.model.Event;

public class FeedbackMapper {
    public static FeedbackDto toDto(Feedback feedback) {
        if (feedback == null) return null;
        FeedbackDto dto = new FeedbackDto();
        dto.setId(feedback.getId());
        dto.setComment(feedback.getComment());
        dto.setRating(feedback.getRating());
        dto.setUserId(feedback.getUser() != null ? feedback.getUser().getId() : null);
        dto.setEventId(feedback.getEvent() != null ? feedback.getEvent().getId() : null);
        return dto;
    }
    public static Feedback toEntity(FeedbackDto dto, User user, Event event) {
        if (dto == null) return null;
        Feedback feedback = new Feedback();
        feedback.setId(dto.getId());
        feedback.setComment(dto.getComment());
        feedback.setRating(dto.getRating());
        feedback.setUser(user);
        feedback.setEvent(event);
        return feedback;
    }
}
