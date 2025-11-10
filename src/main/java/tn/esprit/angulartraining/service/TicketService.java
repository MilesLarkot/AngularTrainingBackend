package tn.esprit.angulartraining.service;

import tn.esprit.angulartraining.model.Ticket;

import java.util.List;

public interface TicketService {
    Ticket createTicket(Ticket ticket);
    Ticket updateTicket(Long id, Ticket ticket);
    void deleteTicket(Long id);
    Ticket getTicketById(Long id);
    List<Ticket> getAllTickets();
    List<Ticket> getTicketsByEventId(Long eventId);
}
