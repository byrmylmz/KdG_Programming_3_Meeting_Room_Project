package be.kdg.event.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EVENTS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_ID")
    private Long id;

    @Column(name = "EVENT_NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "START_DATE_TIME", nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "END_DATE_TIME", nullable = false)
    private LocalDateTime endDateTime;

    @ManyToMany
    @JoinTable(
            name = "EVENTS_ROOMS",
            joinColumns = @JoinColumn(name = "EVENT_ID", foreignKey = @ForeignKey(name = "FK_EVENTS_ROOMS_EVENT")),
            inverseJoinColumns = @JoinColumn(name = "ROOM_ID", foreignKey = @ForeignKey(name = "FK_EVENTS_ROOMS_ROOM"))
    )
    private Set<RoomEntity> roomEntities = new HashSet<>();
}
