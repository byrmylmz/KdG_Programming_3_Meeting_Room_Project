    package be.kdg.event.repository;


    import be.kdg.event.model.Event;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.stereotype.Repository;

    import java.util.List;
    import java.util.Optional;

    @Repository
    public interface EventRepository {
        List<Event> findAll();

        Optional<Event> findById(Long id);

        void save(Event event);

        void delete(Long id);

        long countEvents();

        void update(Event event);
    }
