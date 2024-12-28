package be.kdg.event.service.Impl;

import be.kdg.event.mappers.EventMapper;
import be.kdg.event.model.Event;
import be.kdg.event.repository.EventRepository;
import be.kdg.event.service.EventService;
import be.kdg.event.viewmodels.EventViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
    private final EventRepository eventRepository;

    @Override
    public Event getEventById(Long id) {
        logger.debug("Fetching event with ID {} from the database.", id);
        return eventRepository.findById(id).orElseThrow(() -> {
            logger.error("Event with ID {} not found.", id);
            return new RuntimeException("Event not found.");
        });
    }

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
        logger.info("Event with ID {} has been added successfully.", event.getId());
    }
    @Override
    public void updateEvent(Long id, EventViewModel viewModel) {
        logger.debug("Updating event with ID {} using viewModel: {}", id, viewModel);
        Event existingEvent = eventRepository.findById(id).orElseThrow(() -> {
            logger.error("Unable to update. Event with ID {} not found.", id);
            return new RuntimeException("Event not found.");
        });

        Event updatedEvent = EventMapper.toEntity(viewModel);
        updatedEvent.setId(existingEvent.getId());

        eventRepository.update(updatedEvent);
        logger.info("Event with ID {} has been updated successfully.", id);
    }

    @Override
    public void deleteEventById(Long id) {
        logger.debug("Deleting event with ID {} from the database.", id);
        Event eventToDelete = eventRepository.findById(id).orElseThrow(() -> {
            logger.error("Event with ID {} not found. Unable to delete.", id);
            return new RuntimeException("Event not found.");
        });

        eventRepository.delete(eventToDelete.getId());
        logger.info("Event with ID {} has been deleted successfully.", id);
    }
    
    
    
}