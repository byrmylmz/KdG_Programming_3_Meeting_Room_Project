package be.kdg.event.jsonexport.dto;
import be.kdg.event.model.Building;
import lombok.Data;

import java.util.List;

@Data
public class BuildingDTO {
    private Long id;
    private String name;
    private String address;
    private List<RoomDTO> rooms;

    public BuildingDTO(Building building) {
        this.id = building.getId();
        this.name = building.getName();
        this.address = building.getAddress();
        this.rooms = building.getRooms().stream().map(RoomDTO::new).toList();
    }
}
