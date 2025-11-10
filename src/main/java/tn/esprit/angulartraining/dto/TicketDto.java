package tn.esprit.angulartraining.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Data
public class TicketDto {
    private Long id;

    @Positive
    private Double price;

    @NotNull
    private Long userId;

    @NotNull
    private LocalDate date;

    @NotNull
    private Boolean valid;

    private String description;

    @NotNull
    private Long eventId;
}
