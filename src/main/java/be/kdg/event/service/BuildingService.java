package be.kdg.event.service;



import be.kdg.event.model.Building;
import be.kdg.event.viewmodels.BuildingViewModel;

import java.util.List;

public interface BuildingService {
    List<Building> getAllBuildings();
    Building saveBuilding(Building building);
    Building getBuildingById(Long id);
    void addBuilding(BuildingViewModel viewModel);

    Building updateBuilding(Long id, BuildingViewModel viewModel);

    void deleteBuilding(Long id);
}
