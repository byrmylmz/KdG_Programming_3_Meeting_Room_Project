package be.kdg.event.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomID;

    private String roomNumber;
    private int capacity;
    private String type;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @ManyToMany(mappedBy = "rooms")
    @ToString.Exclude
    private List<Event> events = new ArrayList<>();
}
