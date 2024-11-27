package be.kdg.event.service;


import be.kdg.event.model.Event;
import be.kdg.event.viewmodels.EventViewModel;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    void addEvent(EventViewModel viewModel);

}
