package tn.esprit.angulartraining.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;
    private Long userId;
    private LocalDate date;
    private Boolean valid;
    private String description;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
