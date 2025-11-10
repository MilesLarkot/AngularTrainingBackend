package tn.esprit.angulartraining.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.angulartraining.exception.ResourceNotFoundException;
import tn.esprit.angulartraining.model.Ticket;
import tn.esprit.angulartraining.repository.TicketRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(Long id, Ticket ticketDetails) {
        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id " + id));

        existingTicket.setPrice(ticketDetails.getPrice());
        existingTicket.setUserId(ticketDetails.getUserId());
        existingTicket.setDate(ticketDetails.getDate());
        existingTicket.setValid(ticketDetails.getValid());
        existingTicket.setDescription(ticketDetails.getDescription());

        return ticketRepository.save(existingTicket);
    }

    @Override
    public void deleteTicket(Long id) {
        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id " + id));
        ticketRepository.delete(existingTicket);
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id " + id));
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> getTicketsByEventId(Long eventId) {
        return ticketRepository.findByEventId(eventId);
    }
}
