package be.kdg.event.repository.memory;

import be.kdg.event.model.Building;
import be.kdg.event.repository.BuildingRepository;
import be.kdg.event.repository.memory.staticData.BuildingStaticData;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("memory")
public class BuildingCollectionRepository implements BuildingRepository {
    private final Map<Long, Building> buildingStore = new HashMap<>();
    private long idCounter = 1;

    public BuildingCollectionRepository() {
        init();
    }

    private void init() {
        for (Building building : BuildingStaticData.buildings) {
            buildingStore.put(building.getBuildingID(), building);
            idCounter = Math.max(idCounter, building.getBuildingID() + 1);
        }
    }

    @Override
    public List<Building> findAll() {
        return new ArrayList<>(buildingStore.values());
    }

    @Override
    public Building findById(Long id) {
        return buildingStore.get(id);
    }

    @Override
    public long countBuildings() {
        return buildingStore.size();
    }

    @Override
    public void save(Building building) {
        if (building.getBuildingID() == null) {
            building.setBuildingID(idCounter++);
        }
        buildingStore.put(building.getBuildingID(), building);
    }

    @Override
    public void delete(Long id) {
        buildingStore.remove(id);
    }

    @Override
    public void update(Building building) {
        buildingStore.put(building.getBuildingID(), building);
    }
}