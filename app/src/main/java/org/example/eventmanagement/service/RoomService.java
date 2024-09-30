package org.example.eventmanagement.service;



import org.example.eventmanagement.model.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();
    Room saveRoom(Room room);
}
