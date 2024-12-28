package be.kdg.event.viewmodels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventViewModel {

    private Long id;

    @NotBlank(message = "Event name cannot be empty")
    @Size(min = 3, max = 100, message = "Event name must be between 3 and 100 characters")
    private String name;

    @NotNull(message = "Start date and time cannot be null")
    private LocalDateTime startDateTime;

    @NotNull(message = "End date and time cannot be null")
    private LocalDateTime endDateTime;

    @Setter
    private List<Long> roomIdList;


}
