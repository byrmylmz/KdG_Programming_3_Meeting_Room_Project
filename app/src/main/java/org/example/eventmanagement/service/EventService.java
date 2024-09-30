package org.example.eventmanagement.service;


import org.example.eventmanagement.model.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    Event saveEvent(Event event);
}
