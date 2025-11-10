package tn.esprit.angulartraining.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.angulartraining.dto.EventDto;
import tn.esprit.angulartraining.mapper.EventMapper;
import tn.esprit.angulartraining.model.Event;
import tn.esprit.angulartraining.payload.ApiResponse;
import tn.esprit.angulartraining.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<ApiResponse<EventDto>> createEvent(@Valid @RequestBody EventDto eventDto) {
        Event event = eventService.createEvent(EventMapper.toEntity(eventDto));
        EventDto dto = EventMapper.toDto(event);
        return ResponseEntity.ok(new ApiResponse<>(true, "Event created successfully", dto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EventDto>>> getAllEvents() {
        List<EventDto> events = eventService.getAllEvents()
                .stream().map(EventMapper::toDto).toList();
        return ResponseEntity.ok(new ApiResponse<>(true, "All events retrieved", events));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EventDto>> getEventById(@PathVariable Long id) {
        EventDto dto = EventMapper.toDto(eventService.getEventById(id));
        return ResponseEntity.ok(new ApiResponse<>(true, "Event retrieved", dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EventDto>> updateEvent(@PathVariable Long id, @Valid @RequestBody EventDto eventDto) {
        EventDto dto = EventMapper.toDto(eventService.updateEvent(id, EventMapper.toEntity(eventDto)));
        return ResponseEntity.ok(new ApiResponse<>(true, "Event updated successfully", dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Event deleted successfully", null));
    }
}
