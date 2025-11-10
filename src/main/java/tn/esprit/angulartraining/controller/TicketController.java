package tn.esprit.angulartraining.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.angulartraining.dto.TicketDto;
import tn.esprit.angulartraining.mapper.TicketMapper;
import tn.esprit.angulartraining.model.Event;
import tn.esprit.angulartraining.payload.ApiResponse;
import tn.esprit.angulartraining.service.EventService;
import tn.esprit.angulartraining.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<ApiResponse<TicketDto>> createTicket(@Valid @RequestBody TicketDto ticketDto) {
        Event event = eventService.getEventById(ticketDto.getEventId());
        var saved = ticketService.createTicket(TicketMapper.toEntity(ticketDto, event));
        return ResponseEntity.ok(new ApiResponse<>(true, "Ticket created successfully", TicketMapper.toDto(saved)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TicketDto>>> getAllTickets() {
        List<TicketDto> tickets = ticketService.getAllTickets()
                .stream()
                .map(TicketMapper::toDto)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(true, "All tickets retrieved", tickets));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TicketDto>> getTicketById(@PathVariable Long id) {
        var ticket = TicketMapper.toDto(ticketService.getTicketById(id));
        return ResponseEntity.ok(new ApiResponse<>(true, "Ticket retrieved", ticket));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TicketDto>> updateTicket(@PathVariable Long id,
                                                               @Valid @RequestBody TicketDto ticketDto) {
        Event event = eventService.getEventById(ticketDto.getEventId());
        var updated = ticketService.updateTicket(id, TicketMapper.toEntity(ticketDto, event));
        return ResponseEntity.ok(new ApiResponse<>(true, "Ticket updated successfully", TicketMapper.toDto(updated)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Ticket deleted successfully", null));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<ApiResponse<List<TicketDto>>> getTicketsByEvent(@PathVariable Long eventId) {
        List<TicketDto> tickets = ticketService.getTicketsByEventId(eventId)
                .stream()
                .map(TicketMapper::toDto)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(true, "Tickets retrieved for event " + eventId, tickets));
    }

}
