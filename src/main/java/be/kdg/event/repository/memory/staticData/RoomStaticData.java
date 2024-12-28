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
                    .id(1L)
                    .name("Conference Room")
                    .number("101")
                    .capacity(50)
                    .building(building1)
                    .build(),
            Room.builder()
                    .id(2L)
                    .name("Conference Room 2")
                    .number("102")
                    .capacity(30)
                    .building(building1)
                    .build(),
            Room.builder()
                    .id(3L)
                    .name("Conference Room 3")
                    .number("201")
                    .capacity(20)
                    .building(building2)
                    .build(),
            Room.builder()
                    .id(4L)
                    .name("Conference Room 4")
                    .number("202")
                    .capacity(100)
                    .building(building3)
                    .build()
    );
}