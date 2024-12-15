package be.kdg.event.repository.memory.staticData;

import be.kdg.event.model.Building;
import be.kdg.event.model.Room;
import java.util.List;

public class RoomStaticData {
    private static final Building building1 = BuildingStaticData.buildings.get(0);
    private static final Building building2 = BuildingStaticData.buildings.get(1);
    private static final Building building3 = BuildingStaticData.buildings.get(2);

    public static final List<Room> rooms = List.of(
            Room.builder()
                    .roomID(1L)
                    .roomNumber("101")
                    .capacity(50)
                    .type("Conference Room")
                    .building(building1)
                    .build(),
            Room.builder()
                    .roomID(2L)
                    .roomNumber("102")
                    .capacity(30)
                    .type("Meeting Room")
                    .building(building1)
                    .build(),
            Room.builder()
                    .roomID(3L)
                    .roomNumber("201")
                    .capacity(20)
                    .type("Office")
                    .building(building2)
                    .build(),
            Room.builder()
                    .roomID(4L)
                    .roomNumber("202")
                    .capacity(100)
                    .type("Hall")
                    .building(building3)
                    .build()
    );
}