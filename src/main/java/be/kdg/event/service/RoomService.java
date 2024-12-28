package be.kdg.event.service;



import be.kdg.event.model.Room;
import be.kdg.event.viewmodels.RoomViewModel;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();

    List<Room> getRoomsByIds(List<Long> ids);

    Room getRoomById(Long id);
    void saveRoom(RoomViewModel room);
    void deleteRoom(Long id);

    void updateRoom(Long id, RoomViewModel roomViewModel);

    Room getRoomWithEvents(Long roomId);
}
