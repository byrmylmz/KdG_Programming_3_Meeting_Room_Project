package be.kdg.event.service.Impl;

import be.kdg.event.mappers.EventMapper;
import be.kdg.event.model.Event;
import be.kdg.event.repository.EventRepository;
import be.kdg.event.service.EventService;
import be.kdg.event.viewmodels.EventViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getAllEvents() {
        logger.debug("Fetching all events from the database.");
        List<Event> events = eventRepository.findAll();
        logger.debug("Found {} events.", events.size());
        return events;
    }

    @Override
    public void addEvent(EventViewModel viewModel) {
        logger.debug("Adding a new viewModel: {}", viewModel);
        Event event = EventMapper.toEntity(viewModel);
        eventRepository.save(event);
        logger.info("Event with ID {} has been added successfully.", event.getEventID());
    }
}