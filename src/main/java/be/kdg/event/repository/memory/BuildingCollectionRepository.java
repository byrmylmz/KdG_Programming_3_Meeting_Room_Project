package be.kdg.event.repository.memory;

import be.kdg.event.model.Building;
import be.kdg.event.repository.BuildingRepository;
import be.kdg.event.repository.memory.staticData.BuildingStaticData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("memory")
public class BuildingCollectionRepository implements BuildingRepository {
    private static final Logger logger = LoggerFactory.getLogger(BuildingCollectionRepository.class);
    private final Map<Long, Building> buildingStore = new HashMap<>();
    private long idCounter = 1;

    public BuildingCollectionRepository() {
        init();
    }

    private void init() {
        for (Building building : BuildingStaticData.buildings) {

            Building eagerBuilding = new Building();
            eagerBuilding.setId(building.getId());
            eagerBuilding.setName(building.getName());
            eagerBuilding.setAddress(building.getAddress());
            buildingStore.put(building.getId(), eagerBuilding);

            idCounter = Math.max(idCounter, building.getId() + 1);
        }
    }

    @Override
    public List<Building> findAll() {
        logger.info("Fetching all buildings (Eager Loading - Rooms not fetched)");
        return new ArrayList<>(buildingStore.values());
    }

    @Override
    public Building findById(Long id) {
        logger.info("Fetching building with ID: {} (Eager Loading - Rooms not fetched)", id);
        return buildingStore.get(id);
    }

    @Override
    public long countBuildings() {
        return buildingStore.size();
    }

    @Override
    public void save(Building building) {
        if (building.getId() == null) {
            building.setId(idCounter++);
        }
        logger.info("Saving building with ID: {}", building.getId());
        buildingStore.put(building.getId(), building);
    }

    @Override
    public void delete(Long id) {
        logger.info("Deleting building with ID: {}", id);
        buildingStore.remove(id);
    }

    @Override
    public void update(Building building) {
        logger.info("Updating building with ID: {}", building.getId());
        buildingStore.put(building.getId(), building);
    }
}