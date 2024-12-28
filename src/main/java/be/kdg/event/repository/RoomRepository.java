package be.kdg.event.repository;


import be.kdg.event.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository {
    List<Room> findAll();

    Room findById(Long id);

    long countRoom();

    void save(Room room);

    void delete(Long id);

    void update(Room room);

    Room findByRoomIdWithEvents(Long roomId);
}
