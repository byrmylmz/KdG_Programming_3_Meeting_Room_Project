package be.kdg.event.repository.memory.staticData;

import be.kdg.event.model.Building;
import java.util.List;

public class BuildingStaticData {
    public static final List<Building> buildings = List.of(
            Building.builder()
                    .buildingID(1L)
                    .name("Central Building")
                    .address("123 Main St")
                    .numberOfFloors(5)
                    .build(),
            Building.builder()
                    .buildingID(2L)
                    .name("North Wing")
                    .address("456 Elm St")
                    .numberOfFloors(3)
                    .build(),
            Building.builder()
                    .buildingID(3L)
                    .name("South Tower")
                    .address("789 Oak St")
                    .numberOfFloors(10)
                    .build()
    );
}