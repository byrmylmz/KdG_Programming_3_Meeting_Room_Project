package be.kdg.event.repository.memory.staticData;

import be.kdg.event.model.Building;
import java.util.List;

public class BuildingStaticData {
    public static final List<Building> buildings = List.of(
            Building.builder()
                    .id(1L)
                    .name("Central Building")
                    .address("123 Main St")
                    .build(),
            Building.builder()
                    .id(2L)
                    .name("North Wing")
                    .address("456 Elm St")
                    .build(),
            Building.builder()
                    .id(3L)
                    .name("South Tower")
                    .address("789 Oak St")
                    .build()
    );
}