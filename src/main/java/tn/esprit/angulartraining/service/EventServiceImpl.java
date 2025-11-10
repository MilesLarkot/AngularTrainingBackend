package tn.esprit.angulartraining.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.angulartraining.exception.ResourceNotFoundException;
import tn.esprit.angulartraining.model.Event;
import tn.esprit.angulartraining.repository.EventRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long id, Event eventDetails) {
        Event existingEvent = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event not found with id " + id) );

        existingEvent.setTitle(eventDetails.getTitle());
        existingEvent.setDescription(eventDetails.getDescription());
        existingEvent.setDate(eventDetails.getDate());
        existingEvent.setPrice(eventDetails.getPrice());
        existingEvent.setLocation(eventDetails.getLocation());
        existingEvent.setOrganizerId(eventDetails.getOrganizerId());
        existingEvent.setImageUrl(eventDetails.getImageUrl());
        existingEvent.setNbrPlaces(eventDetails.getNbrPlaces());
        existingEvent.setNbrLikes(eventDetails.getNbrLikes());

        return eventRepository.save(existingEvent);
    }

    @Override
    public void deleteEvent(Long id) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id " + id));
        eventRepository.delete(existingEvent);
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id " + id));
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}

