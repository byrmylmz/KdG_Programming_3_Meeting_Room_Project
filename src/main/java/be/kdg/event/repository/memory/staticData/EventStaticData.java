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
                    .eventID(1L)
                    .eventName("Tech Conference 2024")
                    .startDateTime(LocalDateTime.of(2024, 5, 20, 9, 0))
                    .endDateTime(LocalDateTime.of(2024, 5, 20, 17, 0))
                    .organizer("TechCorp")
                    .description("An annual tech conference for the latest in the industry.")
                    .rooms(List.of(room1, room2))
                    .build(),
            Event.builder()
                    .eventID(2L)
                    .eventName("Business Meeting")
                    .startDateTime(LocalDateTime.of(2024, 6, 15, 10, 0))
                    .endDateTime(LocalDateTime.of(2024, 6, 15, 12, 0))
                    .organizer("Global Enterprises")
                    .description("Quarterly business meeting.")
                    .rooms(List.of(room3))
                    .build(),
            Event.builder()
                    .eventID(3L)
                    .eventName("Wedding Ceremony")
                    .startDateTime(LocalDateTime.of(2024, 7, 10, 15, 0))
                    .endDateTime(LocalDateTime.of(2024, 7, 10, 20, 0))
                    .organizer("Family")
                    .description("A wedding celebration event.")
                    .rooms(List.of(room4))
                    .build()
    );
}