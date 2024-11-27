package be.kdg.event.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@Entity
public class Room {
    // Getters and Setters
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
    private List<Event> events = new ArrayList<>();

    @Override
    public String toString() {
        return "Room{" +
                "roomID=" + roomID +
                ", roomNumber='" + roomNumber + '\'' +
                ", capacity=" + capacity +
                ", type='" + type + '\'' +
//                ", building=" + building +
//                ", events=" + events +
                '}';
    }
}
