package tn.esprit.angulartraining.service;

import tn.esprit.angulartraining.dto.FeedbackDto;
import tn.esprit.angulartraining.model.Feedback;
import java.util.List;

public interface FeedbackService {
    Feedback createFeedback(Feedback feedback);
    Feedback getFeedbackById(Long id);
    List<Feedback> getAllFeedbacks();
    Feedback updateFeedback(Long id, Feedback feedback);
    void deleteFeedback(Long id);
}
