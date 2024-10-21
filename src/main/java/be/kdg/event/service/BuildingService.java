package be.kdg.event.service;



import be.kdg.event.model.Building;

import java.util.List;

public interface BuildingService {
    List<Building> getAllBuildings();
    Building saveBuilding(Building building);
    Building getBuildingById(Long id);
    void addBuilding(Building building);
}
