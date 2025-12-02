package tn.esprit.angulartraining.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.angulartraining.exception.ResourceNotFoundException;
import tn.esprit.angulartraining.model.Feedback;
import tn.esprit.angulartraining.model.User;
import tn.esprit.angulartraining.model.Event;
import tn.esprit.angulartraining.repository.FeedbackRepository;
import tn.esprit.angulartraining.repository.UserRepository;
import tn.esprit.angulartraining.repository.EventRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public Feedback createFeedback(Feedback feedback) {
        // Ensure user and event exist
        User user = userRepository.findById(feedback.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Event event = eventRepository.findById(feedback.getEvent().getId()).orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        feedback.setUser(user);
        feedback.setEvent(event);
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Feedback not found"));
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback updateFeedback(Long id, Feedback feedbackDetails) {
        Feedback feedback = feedbackRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Feedback not found"));
        User user = userRepository.findById(feedbackDetails.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Event event = eventRepository.findById(feedbackDetails.getEvent().getId()).orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        feedback.setComment(feedbackDetails.getComment());
        feedback.setRating(feedbackDetails.getRating());
        feedback.setUser(user);
        feedback.setEvent(event);
        return feedbackRepository.save(feedback);
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
