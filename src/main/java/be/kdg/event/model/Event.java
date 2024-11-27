package be.kdg.event.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
public class Event {
    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventID;

    private String eventName;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String organizer;
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "event_room",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private List<Room> rooms = new ArrayList<>();

    @Override
    public String toString() {
        return "Event{" +
                "eventID=" + eventID +
                ", eventName='" + eventName + '\'' +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", organizer='" + organizer + '\'' +
                ", description='" + description + '\'' +
//                ", rooms=" + rooms +
                '}';
    }
}
