package be.kdg.event.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Profile;

import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "ROOMS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
    private Long id;

    @Column(name = "ROOM_NAME", length = 255)
    private String roomName;

    @Column(name = "ROOM_NUMBER", nullable = false, length = 100)
    private String roomNumber;

    @Column(name = "ROOM_CAPACITY", nullable = false)
    private Integer roomCapacity;

    @ManyToOne
    @JoinColumn(name = "BUILDING_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_ROOMS_BUILDING"))
    private BuildingEntity buildingEntity;

    @ManyToMany(mappedBy = "roomEntities")
    private Set<EventEntity> eventEntities = new HashSet<>();
}
