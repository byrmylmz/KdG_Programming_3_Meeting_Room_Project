package be.kdg.event.service;


import be.kdg.event.model.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    void addEvent(Event event);

}
