package be.kdg.event.jsonexport.dto;

import be.kdg.event.model.Room;
import lombok.Data;

import java.util.List;

@Data
public class RoomDTO {
    private Long id;
    private String name;
    private String number;
    private Integer capacity;
    private List<EventDTO> events;

    public RoomDTO(Room room) {
        this.id = room.getId();
        this.name = room.getName();
        this.number = room.getNumber();
        this.capacity = room.getCapacity();
        this.events = room.getEvents().stream().map(EventDTO::new).toList();
    }
}
