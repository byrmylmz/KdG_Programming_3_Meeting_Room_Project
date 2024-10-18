package org.example.eventmanagement.service.Impl;

import org.example.eventmanagement.model.Building;
import org.example.eventmanagement.repository.BuildingRepository;
import org.example.eventmanagement.service.BuildingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    private static final Logger logger = LoggerFactory.getLogger(BuildingServiceImpl.class);
    private final BuildingRepository buildingRepository;

    public BuildingServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @Override
    public List<Building> getAllBuildings() {
        logger.debug("Fetching all buildings from the database.");
        List<Building> buildings = buildingRepository.findAll();
        logger.debug("Found {} buildings.", buildings.size());
        return buildings;
    }

    @Override
    public Building saveBuilding(Building building) {
        logger.debug("Saving building: {}", building);
        Building savedBuilding = buildingRepository.save(building);
        logger.info("Building with ID {} has been saved successfully.", savedBuilding.getBuildingID());
        return savedBuilding;
    }

    @Override
    public Building getBuildingById(Long id) {
        logger.debug("Fetching building with ID: {}", id);
        Building building = buildingRepository.findById(id).orElse(null);
        if (building != null) {
            logger.info("Building with ID {} found.", id);
        } else {
            logger.warn("Building with ID {} not found.", id);
        }
        return building;
    }

    @Override
    public void addBuilding(Building building) {
        logger.debug("Adding a new building: {}", building);
        buildingRepository.save(building);
        logger.info("Building has been added successfully.");
    }
}
