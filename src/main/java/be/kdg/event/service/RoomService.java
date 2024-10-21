package be.kdg.event.service;



import be.kdg.event.model.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();
    Room getRoomById(Long id);
    Room saveRoom(Room room);
    void deleteRoom(Long id);
}
