package be.kdg.event.repository;


import be.kdg.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository {
    List<Event> findAll();

    Event findById(Long id);

    void save(Event event);

    void delete(Long id);

    long countEvents();
}
