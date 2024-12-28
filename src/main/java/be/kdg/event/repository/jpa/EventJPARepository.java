package be.kdg.event.repository.jpa;


import be.kdg.event.model.Event;
import be.kdg.event.repository.EventRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Profile({"dev", "prod"})
@Transactional
public class EventJPARepository implements EventRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Event> findAll() {
        return entityManager.createQuery("SELECT e FROM Event e", Event.class).getResultList();
    }

    @Override
    public Optional<Event> findById(Long id) {
        Event event = entityManager.find(Event.class, id);
        return Optional.ofNullable(event);
    }

    @Override
    public void save(Event event) {
        entityManager.persist(event);
    }

    @Override
    public void update(Event event) {
        entityManager.merge(event);
    }

    @Override
    public void delete(Long id) {
        Event event = entityManager.find(Event.class, id);
        if (event != null) {
            entityManager.remove(event);
        }
    }

    @Override
    public long countEvents() {
        return entityManager.createQuery("SELECT COUNT(e) FROM Event e", Long.class).getSingleResult();
    }
}

