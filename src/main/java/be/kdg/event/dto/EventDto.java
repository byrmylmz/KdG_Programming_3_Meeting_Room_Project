package be.kdg.event.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class EventDto {
    public String eventName;
    public LocalDateTime startDateTime;
    public LocalDateTime endDateTime;
    public String organizer;
    public String description;
}
