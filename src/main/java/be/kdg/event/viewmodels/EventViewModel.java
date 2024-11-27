package be.kdg.event.viewmodels;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class EventViewModel {
    private Long eventID;
    private String eventName;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String organizer;
    private String description;
}
