package be.kdg.event.repository.memory;

import be.kdg.event.model.Event;
import be.kdg.event.repository.EventRepository;
import be.kdg.event.repository.memory.staticData.EventStaticData;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Primary
public class EventCollectionRepository implements EventRepository {
    private final Map<Long, Event> eventStore = new HashMap<>();
    private long idCounter = 1;

    public EventCollectionRepository() {
        init();
    }

    private void init() {
        List<Event> events = EventStaticData.events;
        for (Event event : events) {
            eventStore.put(event.getEventID(), event);
            idCounter = Math.max(idCounter, event.getEventID() + 1);
        }
    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(eventStore.values());
    }

    @Override
    public Event findById(Long id) {
        return eventStore.get(id);
    }

    @Override
    public void save(Event event) {
        if (event.getEventID() == null) {
            event.setEventID(idCounter++);
        }
        eventStore.put(event.getEventID(), event);
    }

    @Override
    public void delete(Long id) {
        eventStore.remove(id);
    }
    @Override
    public long countEvents() {
        return eventStore.size();
    }
}