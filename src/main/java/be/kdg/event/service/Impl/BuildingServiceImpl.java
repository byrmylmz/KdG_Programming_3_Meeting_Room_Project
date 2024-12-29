package be.kdg.event.service.Impl;

import be.kdg.event.mappers.BuildingMapper;
import be.kdg.event.model.Building;
import be.kdg.event.repository.BuildingRepository;
import be.kdg.event.service.BuildingService;
import be.kdg.event.viewmodels.BuildingViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Profile("!spring-data-jpa")
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
            logger.info("Building with ID {} has been saved successfully.", building.getId());
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

    @Override
    public Building updateBuilding(Long id, BuildingViewModel viewModel) {
        logger.debug("Updating building with ID: {}", id);

        try {
            Building existingBuilding = buildingRepository.findById(id);
            if (existingBuilding == null) {
                logger.warn("Building with ID {} not found.", id);
                throw new RuntimeException("Building not found");
            }

            Building updatedBuilding = BuildingMapper.toEntity(viewModel);

            buildingRepository.update(updatedBuilding);

            logger.info("Building with ID {} has been updated successfully.", id);
            return updatedBuilding;

        } catch (Exception e) {
            logger.error("Failed to update building with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to update building", e);
        }
    }

    @Override
    public void deleteBuilding(Long id) {
        logger.debug("Attempting to delete building with ID: {}", id);
        try {
            Building building = buildingRepository.findById(id);
            if (building == null) {
                logger.warn("Building with ID {} not found. Deletion cancelled.", id);
                throw new RuntimeException("Building not found");
            }
            buildingRepository.delete(id);
            logger.info("Building with ID {} has been successfully deleted.", id);
        } catch (Exception e) {
            logger.error("Failed to delete building with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to delete building", e);
        }
    }



}