package be.kdg.event.service.Impl;

import be.kdg.event.model.Room;
import be.kdg.event.repository.RoomRepository;
import be.kdg.event.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> getAllRooms() {
        logger.debug("Fetching all rooms from the database.");
        List<Room> rooms = roomRepository.findAll();
        logger.debug("Found {} rooms.", rooms.size());
        return rooms;
    }

    @Override
    public Room getRoomById(Long id) {
        logger.debug("Fetching room with ID: {}", id);
        Room room = roomRepository.findById(id).orElseThrow(() -> {
            logger.error("Room with ID {} not found", id);
            return new RuntimeException("Room not found");
        });
        logger.info("Room with ID {} found.", id);
        return room;
    }

    @Override
    public Room saveRoom(Room room) {
        logger.debug("Saving room: {}", room);
        Room savedRoom = roomRepository.save(room);
        logger.info("Room with ID {} has been saved successfully.", savedRoom.getRoomID());
        return savedRoom;
    }

    @Override
    public void deleteRoom(Long id) {
        logger.debug("Deleting room with ID: {}", id);
        try {
            roomRepository.deleteById(id);
            logger.info("Room with ID {} has been deleted successfully.", id);
        } catch (Exception e) {
            logger.error("Error deleting room with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to delete room", e);
        }
    }
}