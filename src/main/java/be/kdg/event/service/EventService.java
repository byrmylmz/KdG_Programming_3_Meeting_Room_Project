package be.kdg.event.service;


import be.kdg.event.model.Event;
import be.kdg.event.viewmodels.EventViewModel;

import java.util.List;

public interface EventService {
    Event getEventById(Long id);

    List<Event> getAllEvents();
    void addEvent(EventViewModel viewModel);

    void updateEvent(Long id, EventViewModel viewModel);

    void deleteEventById(Long id);
}
