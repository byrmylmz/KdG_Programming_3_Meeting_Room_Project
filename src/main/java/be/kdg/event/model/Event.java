package be.kdg.event.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "EVENTS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(name = "start_date_time", nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "end_date_time", nullable = false)
    private LocalDateTime endDateTime;

    @ManyToMany
    @JoinTable(
            name = "EVENTS_ROOMS",
            joinColumns = @JoinColumn(name = "event_id", foreignKey = @ForeignKey(name = "FK_EVENT_ROOM_EVENT")),
            inverseJoinColumns = @JoinColumn(name = "room_id", foreignKey = @ForeignKey(name = "FK_EVENT_ROOM_ROOM"))
    )
    private List<Room> rooms = new ArrayList<>();

    @Setter
    @Transient // This field is not part of the database schema
    private List<Long> roomIdList;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void addRoom(Room room) {
        rooms.add(room);
        room.getEvents().add(this);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
        room.getEvents().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) &&
                Objects.equals(name, event.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public List<Long> getRoomIdList() {
        if (roomIdList == null) {
            return rooms.stream()
                    .map(Room::getId)
                    .filter(Objects::nonNull)
                    .toList();
        }
        return roomIdList;
    }

}
