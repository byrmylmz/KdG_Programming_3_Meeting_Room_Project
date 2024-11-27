package be.kdg.event.service.Impl;

import be.kdg.event.mappers.BuildingMapper;
import be.kdg.event.model.Building;
import be.kdg.event.repository.BuildingRepository;
import be.kdg.event.service.BuildingService;
import be.kdg.event.viewmodels.BuildingViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public void addBuilding(BuildingViewModel viewModel) {
        logger.debug("Adding a new building: {}", viewModel);
        Building building = BuildingMapper.toEntity(viewModel);
        if(Objects.nonNull(building)) {
            buildingRepository.save(building);
            logger.info("Building has been added successfully.");
        }else {
            logger.error("Building could not saved.");
        }


    }
}
