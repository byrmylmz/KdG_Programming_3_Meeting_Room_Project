package be.kdg.event.repository.jpa;


import be.kdg.event.model.Room;
import be.kdg.event.repository.RoomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Profile({"dev", "prod"})
@Transactional
public class RoomJPARepository implements RoomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Room> findAll() {
        return entityManager.createQuery("SELECT r FROM Room r", Room.class).getResultList();
    }

    public Room findById(Long id) {
        return entityManager.find(Room.class, id);
    }

    @Override
    public long countRoom() {
        return entityManager.createQuery("SELECT COUNT(r) FROM Room r", Long.class).getSingleResult();
    }

    public void save(Room room) {
        entityManager.persist(room);
    }

    public void update(Room room) {
        entityManager.merge(room);
    }

    @Override
    public Room findByRoomIdWithEvents(Long roomId) {
        return entityManager.createQuery(
                        "SELECT r FROM Room r LEFT JOIN FETCH r.events WHERE r.id = :id", Room.class)
                .setParameter("id", roomId)
                .getSingleResult();
    }

    public void delete(Long id) {
        Room room = findById(id);
        if (room != null) {
            entityManager.remove(room);
        }
    }
}
