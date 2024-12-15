package be.kdg.event.service.Impl;

import be.kdg.event.mappers.RoomMapper;
import be.kdg.event.model.Room;
import be.kdg.event.repository.RoomRepository;
import be.kdg.event.service.RoomService;
import be.kdg.event.viewmodels.RoomViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        try {
            List<Room> rooms = roomRepository.findAll();
            logger.debug("Found {} rooms.", rooms.size());
            return rooms;
        } catch (Exception e) {
            logger.error("Failed to fetch rooms: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch rooms", e);
        }
    }

    @Override
    public Room getRoomById(Long id) {
        logger.debug("Fetching room with ID: {}", id);
        try {
            Room room = roomRepository.findById(id);
            if (room == null) {
                logger.error("Room with ID {} not found.", id);
                throw new RuntimeException("Room not found");
            }
            logger.info("Room with ID {} found.", id);
            return room;
        } catch (Exception e) {
            logger.error("Error fetching room with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to fetch room", e);
        }
    }

    @Override
    public void saveRoom(RoomViewModel room) {
        logger.debug("Saving room: {}", room);
        try {
            Room roomSave = RoomMapper.toEntity(room);
            if (Objects.nonNull(roomSave)) {
                roomRepository.save(roomSave);
                logger.info("Room has been saved successfully.");
            } else {
                logger.error("Room could not be saved. Room entity is null.");
                throw new RuntimeException("Room could not be saved. Invalid data provided.");
            }
        } catch (Exception e) {
            logger.error("Error saving room: {}", e.getMessage());
            throw new RuntimeException("Failed to save room", e);
        }
    }

    @Override
    public void deleteRoom(Long id) {
        logger.debug("Deleting room with ID: {}", id);
        try {
            Room room = roomRepository.findById(id);
            if (room == null) {
                logger.error("Room with ID {} not found. Cannot delete.", id);
                throw new RuntimeException("Room not found");
            }
            roomRepository.delete(id);
            logger.info("Room with ID {} has been deleted successfully.", id);
        } catch (Exception e) {
            logger.error("Error deleting room with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to delete room", e);
        }
    }
}