package org.example.eventmanagement.service;



import org.example.eventmanagement.model.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();
    Room getRoomById(Long id);
    Room saveRoom(Room room);
    void deleteRoom(Long id);
}
