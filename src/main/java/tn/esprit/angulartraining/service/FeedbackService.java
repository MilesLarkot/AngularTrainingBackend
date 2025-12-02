package tn.esprit.angulartraining.service;

import tn.esprit.angulartraining.model.Feedback;
import tn.esprit.angulartraining.dto.FeedbackDto;
import java.util.List;

public interface FeedbackService {
    Feedback createFeedback(FeedbackDto feedbackDto);
    Feedback updateFeedback(Long id, FeedbackDto feedbackDto);
    void deleteFeedback(Long id);
    Feedback getFeedbackById(Long id);
    List<Feedback> getAllFeedbacks();
}
