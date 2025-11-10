package tn.esprit.angulartraining.mapper;

import tn.esprit.angulartraining.dto.EventDto;
import tn.esprit.angulartraining.model.Event;

public class EventMapper {
    public static EventDto toDto(Event event) {
        EventDto dto = new EventDto();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setDate(event.getDate());
        dto.setPrice(event.getPrice());
        dto.setLocation(event.getLocation());
        dto.setOrganizerId(event.getOrganizerId());
        dto.setImageUrl(event.getImageUrl());
        dto.setNbrPlaces(event.getNbrPlaces());
        dto.setNbrLikes(event.getNbrLikes());
        return dto;
    }

    public static Event toEntity(EventDto dto) {
        Event event = new Event();
        event.setId(dto.getId());
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setDate(dto.getDate());
        event.setPrice(dto.getPrice());
        event.setLocation(dto.getLocation());
        event.setOrganizerId(dto.getOrganizerId());
        event.setImageUrl(dto.getImageUrl());
        event.setNbrPlaces(dto.getNbrPlaces());
        event.setNbrLikes(dto.getNbrLikes());
        return event;
    }
}
