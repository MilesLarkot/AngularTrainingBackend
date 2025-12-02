package tn.esprit.angulartraining.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class FeedbackDto {
    private Long id;

    @NotBlank(message = "Comment is required")
    private String comment;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private int rating;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Event ID is required")
    private Long eventId;
}
