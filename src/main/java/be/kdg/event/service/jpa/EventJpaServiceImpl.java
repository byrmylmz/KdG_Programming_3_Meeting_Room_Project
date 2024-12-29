package be.kdg.event.service.jpa;

import be.kdg.event.mappers.EventMapper;
import be.kdg.event.model.Event;
import be.kdg.event.model.Room;
import be.kdg.event.repository.EventJpaRepository;
import be.kdg.event.service.EventService;
import be.kdg.event.viewmodels.EventViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("spring-data-jpa")
public class EventJpaServiceImpl implements EventService {
    private static final Logger logger = LoggerFactory.getLogger(EventJpaServiceImpl.class);
    private final EventJpaRepository eventJpaRepository;

    public EventJpaServiceImpl(EventJpaRepository eventJpaRepository) {
        this.eventJpaRepository = eventJpaRepository;
    }

    @Override
    public Event getEventById(Long id) {
        logger.debug("Fetching event with ID {} from the database.", id);
        return eventJpaRepository.findById(id).orElseThrow(() -> {
            logger.error("Event with ID {} not found.", id);
            return new RuntimeException("Event not found.");
        });
    }

    @Override
    public List<Event> getAllEvents() {
        logger.debug("Fetching all events from the database.");
        List<Event> events = eventJpaRepository.findAll();
        logger.debug("Found {} events.", events.size());
        return events;
    }

    @Override
    public void addEvent(EventViewModel viewModel) {
        logger.debug("Adding a new viewModel: {}", viewModel);
        Event event = EventMapper.toEntity(viewModel);

        if (viewModel.getRoomIdList() != null) {
            List<Room> rooms = viewModel.getRoomIdList().stream()
                    .map(roomId -> {
                        Room room = new Room();
                        room.setId(roomId);
                        return room;
                    }).collect(Collectors.toList());
            event.setRooms(rooms);
        }

        eventJpaRepository.save(event);
        logger.info("Event with ID {} has been added successfully.", event.getId());
    }

    @Override
    public void updateEvent(Long id, EventViewModel viewModel) {
        logger.debug("Updating event with ID {} using viewModel: {}", id, viewModel);
        Event existingEvent = eventJpaRepository.findById(id).orElseThrow(() -> {
            logger.error("Unable to update. Event with ID {} not found.", id);
            return new RuntimeException("Event not found.");
        });

        Event updatedEvent = EventMapper.toEntity(viewModel);
        updatedEvent.setId(existingEvent.getId());

        if (viewModel.getRoomIdList() != null) {
            List<Room> rooms = viewModel.getRoomIdList().stream()
                    .map(roomId -> {
                        Room room = new Room();
                        room.setId(roomId);
                        return room;
                    }).collect(Collectors.toList());
            updatedEvent.setRooms(rooms);
        } else {
            updatedEvent.setRooms(existingEvent.getRooms());
        }

        eventJpaRepository.save(updatedEvent);
        logger.info("Event with ID {} has been updated successfully.", id);
    }

    @Override
    public void deleteEventById(Long id) {
        logger.debug("Deleting event with ID {} from the database.", id);
        eventJpaRepository.findById(id).orElseThrow(() -> {
            logger.error("Event with ID {} not found. Unable to delete.", id);
            return new RuntimeException("Event not found.");
        });

        eventJpaRepository.deleteById(id);
        logger.info("Event with ID {} has been deleted successfully.", id);
    }
}
