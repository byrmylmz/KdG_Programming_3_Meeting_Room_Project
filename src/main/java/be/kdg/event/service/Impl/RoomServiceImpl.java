package be.kdg.event.service.Impl;

import be.kdg.event.mappers.RoomMapper;
import be.kdg.event.model.Room;
import be.kdg.event.repository.RoomRepository;
import be.kdg.event.service.BuildingService;
import be.kdg.event.service.RoomService;
import be.kdg.event.viewmodels.RoomViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    private static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);
    private final RoomRepository roomRepository;
    private final BuildingService buildingService;

    public RoomServiceImpl(RoomRepository roomRepository, BuildingService buildingService) {
        this.roomRepository = roomRepository;
        this.buildingService = buildingService;
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
    public List<Room> getRoomsByIds(List<Long> ids) {
        logger.debug("Fetching rooms for IDs: {}", ids);
        if (ids == null || ids.isEmpty()) {
            logger.warn("Provided IDs list is null or empty. Returning an empty list.");
            return List.of();
        }
        try {
            List<Room> rooms = ids.stream()
                    .map(roomRepository::findById)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            logger.debug("Found rooms for IDs: {}", ids);
            return rooms;
        } catch (Exception e) {
            logger.error("Error fetching rooms for IDs {}: {}", ids, e.getMessage());
            throw new RuntimeException("Failed to fetch rooms by IDs", e);
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

    @Override
    public void updateRoom(Long id, RoomViewModel roomViewModel) {
        logger.debug("Updating room with ID: {}", id);
        try {
            Room room = roomRepository.findById(id);
            if (room == null) {
                logger.error("Room with ID {} not found. Cannot update.", id);
                throw new RuntimeException("Room not found");
            }

            room.setName(roomViewModel.getName());
            room.setNumber(roomViewModel.getNumber());
            room.setCapacity(roomViewModel.getCapacity());
            room.setBuilding(buildingService.getBuildingById(roomViewModel.getBuilding().getId()));

            roomRepository.update(room);
            logger.info("Room with ID {} has been updated successfully.", id);
        } catch (Exception e) {
            logger.error("Error updating room with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to update room", e);
        }
    }

    @Override
    public Room getRoomWithEvents(Long roomId) {
        logger.debug("Fetching room with events for ID: {}", roomId);
        try {
            Room room = roomRepository.findByRoomIdWithEvents(roomId);
            if (room == null) {
                logger.error("Room with ID {} not found.", roomId);
                throw new RuntimeException("Room not found");
            }

            room.getEvents();

            logger.info("Room with ID {} and its events fetched successfully.", roomId);
            return room;
        } catch (Exception e) {
            logger.error("Error fetching room with events for ID {}: {}", roomId, e.getMessage());
            throw new RuntimeException("Failed to fetch room with events", e);
        }
    }
}