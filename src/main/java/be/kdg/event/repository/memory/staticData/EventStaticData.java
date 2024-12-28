package be.kdg.event.repository.memory.staticData;

import be.kdg.event.model.Event;
import be.kdg.event.model.Room;

import java.time.LocalDateTime;
import java.util.List;

public class EventStaticData {
    private static final Room room1 = RoomStaticData.rooms.get(0);
    private static final Room room2 = RoomStaticData.rooms.get(1);
    private static final Room room3 = RoomStaticData.rooms.get(2);
    private static final Room room4 = RoomStaticData.rooms.get(3);

    public static final List<Event> events = List.of(
            Event.builder()
                    .id(1L)
                    .name("Tech Conference 2024")
                    .startDateTime(LocalDateTime.of(2024, 5, 20, 9, 0))
                    .endDateTime(LocalDateTime.of(2024, 5, 20, 17, 0))
                    .roomIdList(List.of(room1.getId(), room2.getId()))
                    .build(),
            Event.builder()
                    .id(2L)
                    .name("Business Meeting")
                    .startDateTime(LocalDateTime.of(2024, 6, 15, 10, 0))
                    .endDateTime(LocalDateTime.of(2024, 6, 15, 12, 0))
                    .roomIdList(List.of(room3.getId()))
                    .build(),
            Event.builder()
                    .id(3L)
                    .name("Wedding Ceremony")
                    .startDateTime(LocalDateTime.of(2024, 7, 10, 15, 0))
                    .endDateTime(LocalDateTime.of(2024, 7, 10, 20, 0))
                    .roomIdList(List.of(room4.getId()))
                    .build()
    );
}