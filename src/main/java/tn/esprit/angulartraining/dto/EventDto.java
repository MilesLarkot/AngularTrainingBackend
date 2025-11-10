package tn.esprit.angulartraining.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Data
public class EventDto {
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @Future(message = "Date must be in the future")
    private LocalDate date;

    @PositiveOrZero(message = "Price must be positive")
    private Double price;

    @NotBlank(message = "Location is required")
    private String location;

    private Long organizerId;
    private String imageUrl;

    @Positive(message = "Number of places must be bigger than 0")
    private Integer nbrPlaces;

    @PositiveOrZero
    private Integer nbrLikes;
}
