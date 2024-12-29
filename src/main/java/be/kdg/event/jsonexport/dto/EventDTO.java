package be.kdg.event.jsonexport.dto;

import be.kdg.event.model.Event;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDTO {
    private Long id;
    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public EventDTO(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.startDateTime = event.getStartDateTime();
        this.endDateTime = event.getEndDateTime();
    }
}