package be.kdg.event.service.jpa;

import be.kdg.event.mappers.RoomMapper;
import be.kdg.event.model.Room;
import be.kdg.event.repository.RoomJpaRepository;
import be.kdg.event.service.RoomService;
import be.kdg.event.viewmodels.RoomViewModel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("spring-data-jpa")
public class RoomJpaServiceImpl implements RoomService {
    private final RoomJpaRepository roomJpaRepository;

    public RoomJpaServiceImpl(RoomJpaRepository roomJpaRepository) {
        this.roomJpaRepository = roomJpaRepository;
    }

    @Override
    public List<Room> getAllRooms() {
        return roomJpaRepository.findAll();
    }

    @Override
    public List<Room> getRoomsByIds(List<Long> ids) {
        return roomJpaRepository.findAllById(ids);
    }

    @Override
    public Room getRoomById(Long id) {
        return roomJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    @Override
    public void saveRoom(RoomViewModel roomViewModel) {
        Room room = RoomMapper.toEntity(roomViewModel);
        roomJpaRepository.save(room);
    }

    @Override
    public void deleteRoom(Long id) {
        roomJpaRepository.deleteById(id);
    }

    @Override
    public void updateRoom(Long id, RoomViewModel roomViewModel) {
        Room existingRoom = roomJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        Room updatedRoom = RoomMapper.toEntity(roomViewModel);
        updatedRoom.setId(existingRoom.getId());
        roomJpaRepository.save(updatedRoom);
    }

    @Override
    public Room getRoomWithEvents(Long roomId) {
        return roomJpaRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
    }
}
