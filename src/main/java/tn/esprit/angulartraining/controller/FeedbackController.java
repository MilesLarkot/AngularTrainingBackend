package tn.esprit.angulartraining.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.angulartraining.dto.FeedbackDto;
import tn.esprit.angulartraining.model.Feedback;
import tn.esprit.angulartraining.service.FeedbackService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import tn.esprit.angulartraining.payload.ApiResponse;
import tn.esprit.angulartraining.mapper.FeedbackMapper;

@RestController
@RequestMapping("/api/feedbacks")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<ApiResponse<FeedbackDto>> createFeedback(@Valid @RequestBody FeedbackDto feedbackDto) {
        Feedback feedback = FeedbackMapper.toEntity(
            feedbackDto,
            null, // User and Event will be set in service
            null
        );
        Feedback saved = feedbackService.createFeedback(feedback);
        FeedbackDto dto = FeedbackMapper.toDto(saved);
        return ResponseEntity.ok(new ApiResponse<>(true, "Feedback created successfully", dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FeedbackDto>> getFeedbackById(@PathVariable Long id) {
        FeedbackDto dto = FeedbackMapper.toDto(feedbackService.getFeedbackById(id));
        return ResponseEntity.ok(new ApiResponse<>(true, "Feedback retrieved", dto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<FeedbackDto>>> getAllFeedbacks() {
        List<FeedbackDto> feedbacks = feedbackService.getAllFeedbacks().stream().map(FeedbackMapper::toDto).toList();
        return ResponseEntity.ok(new ApiResponse<>(true, "All feedbacks retrieved", feedbacks));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<FeedbackDto>> updateFeedback(@PathVariable Long id, @Valid @RequestBody FeedbackDto feedbackDto) {
        Feedback feedback = FeedbackMapper.toEntity(
            feedbackDto,
            null, // User and Event will be set in service
            null
        );
        Feedback updated = feedbackService.updateFeedback(id, feedback);
        FeedbackDto dto = FeedbackMapper.toDto(updated);
        return ResponseEntity.ok(new ApiResponse<>(true, "Feedback updated successfully", dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Feedback deleted", null));
    }
}
