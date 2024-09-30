package org.example.eventmanagement.service;



import org.example.eventmanagement.model.Building;

import java.util.List;

public interface BuildingService {
    List<Building> getAllBuildings();
    Building saveBuilding(Building building);
    Building getBuildingById(Long id);
}
