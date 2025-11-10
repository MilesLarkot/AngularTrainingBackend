package tn.esprit.angulartraining.service;

import tn.esprit.angulartraining.model.Event;

import java.util.List;

public interface EventService {
    Event createEvent(Event event);
    Event updateEvent(Long id, Event event);
    void deleteEvent(Long id);
    Event getEventById(Long id);
    List<Event> getAllEvents();
}
