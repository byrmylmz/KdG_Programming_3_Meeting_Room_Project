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
        try {
            List<Building> buildings = buildingRepository.findAll();
            logger.debug("Found {} buildings.", buildings.size());
            return buildings;
        } catch (Exception e) {
            logger.error("Failed to fetch buildings: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch buildings", e);
        }
    }

    @Override
    public Building saveBuilding(Building building) {
        logger.debug("Saving building: {}", building);
        try {
            buildingRepository.save(building);
            logger.info("Building with ID {} has been saved successfully.", building.getBuildingID());
            return building;
        } catch (Exception e) {
            logger.error("Failed to save building: {}", e.getMessage());
            throw new RuntimeException("Failed to save building", e);
        }
    }

    @Override
    public Building getBuildingById(Long id) {
        logger.debug("Fetching building with ID: {}", id);
        try {
            Building building = buildingRepository.findById(id);
            if (building == null) {
                logger.warn("Building with ID {} not found.", id);
                throw new RuntimeException("Building not found");
            }
            logger.info("Building with ID {} found.", id);
            return building;
        } catch (Exception e) {
            logger.error("Failed to fetch building with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to fetch building", e);
        }
    }

    @Override
    public void addBuilding(BuildingViewModel viewModel) {
        logger.debug("Adding a new building: {}", viewModel);
        try {
            Building building = BuildingMapper.toEntity(viewModel);
            if (Objects.nonNull(building)) {
                buildingRepository.save(building);
                logger.info("Building has been added successfully.");
            } else {
                logger.error("Building could not be saved. Mapped entity is null.");
                throw new RuntimeException("Building data is invalid");
            }
        } catch (Exception e) {
            logger.error("Failed to add building: {}", e.getMessage());
            throw new RuntimeException("Failed to add building", e);
        }
    }
}