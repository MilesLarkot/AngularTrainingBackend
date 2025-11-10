package tn.esprit.angulartraining.mapper;

import tn.esprit.angulartraining.dto.TicketDto;
import tn.esprit.angulartraining.model.Event;
import tn.esprit.angulartraining.model.Ticket;

public class TicketMapper {
    public static TicketDto toDto(Ticket ticket) {
        TicketDto dto = new TicketDto();
        dto.setId(ticket.getId());
        dto.setPrice(ticket.getPrice());
        dto.setUserId(ticket.getUserId());
        dto.setDate(ticket.getDate());
        dto.setValid(ticket.getValid());
        dto.setDescription(ticket.getDescription());
        dto.setEventId(ticket.getEvent().getId());
        return dto;
    }

    public static Ticket toEntity(TicketDto dto, Event event) {
        Ticket ticket = new Ticket();
        ticket.setId(dto.getId());
        ticket.setPrice(dto.getPrice());
        ticket.setUserId(dto.getUserId());
        ticket.setDate(dto.getDate());
        ticket.setValid(dto.getValid());
        ticket.setDescription(dto.getDescription());
        ticket.setEvent(event);
        return ticket;
    }
}
