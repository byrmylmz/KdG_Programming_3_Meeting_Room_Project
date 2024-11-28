package be.kdg.event.viewmodels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class EventViewModel {

    private Long eventID;

    @NotBlank(message = "Event name cannot be empty")
    @Size(min = 3, max = 100, message = "Event name must be between 3 and 100 characters")
    private String eventName;

    @NotNull(message = "Start date and time cannot be null")
    private LocalDateTime startDateTime;

    @NotNull(message = "End date and time cannot be null")
    private LocalDateTime endDateTime;

    @NotBlank(message = "Organizer cannot be empty")
    private String organizer;

    @NotBlank(message = "Description cannot be empty")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;
}
