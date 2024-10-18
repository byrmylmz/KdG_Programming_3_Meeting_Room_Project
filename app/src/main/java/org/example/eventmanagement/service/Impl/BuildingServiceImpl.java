package org.example.eventmanagement.service.Impl;

import org.example.eventmanagement.model.Building;
import org.example.eventmanagement.repository.BuildingRepository;
import org.example.eventmanagement.service.BuildingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    private final BuildingRepository buildingRepository;

    public BuildingServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @Override
    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }

    @Override
    public Building saveBuilding(Building building) {
        return buildingRepository.save(building);
    }

    @Override
    public Building getBuildingById(Long id) {
        return buildingRepository.findById(id).orElse(null);
    }

    @Override
    public void addBuilding(Building building) {
        buildingRepository.save(building);
    }
}
