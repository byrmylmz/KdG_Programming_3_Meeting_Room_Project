package be.kdg.event.repository.memory;

import be.kdg.event.model.Room;
import be.kdg.event.repository.RoomRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Predicate;

@Repository
@Profile("memory")
public class RoomCollectionRepository implements RoomRepository {
    private final Map<Long, Room> roomStore = new HashMap<>();
    private long idCounter = 1;

    @Override
    public List<Room> findAll() {
        return new ArrayList<>(roomStore.values());
    }

    @Override
    public Room findById(Long id) {
        return roomStore.get(id);
    }

    @Override
    public void save(Room room) {
        if (room.getId() == null) {
            room.setId(idCounter++);
        }
        roomStore.put(room.getId(), room);
    }

    @Override
    public void delete(Long id) {
        roomStore.remove(id);
    }

    @Override
    public void update(Room room) {

    }

    @Override
    public Room findByRoomIdWithEvents(Long roomId) {
        return null;
    }

    @Override
    public long countRoom() {
        return roomStore.size();
    }
}