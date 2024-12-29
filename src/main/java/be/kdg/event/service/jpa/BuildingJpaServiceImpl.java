package be.kdg.event.service.jpa;

import be.kdg.event.mappers.BuildingMapper;
import be.kdg.event.model.Building;
import be.kdg.event.repository.BuildingJpaRepository;
import be.kdg.event.service.BuildingService;
import be.kdg.event.viewmodels.BuildingViewModel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("spring-data-jpa")
public class BuildingJpaServiceImpl implements BuildingService {
    private final BuildingJpaRepository buildingJpaRepository;

    public BuildingJpaServiceImpl(BuildingJpaRepository buildingJpaRepository) {
        this.buildingJpaRepository = buildingJpaRepository;
    }

    @Override
    public List<Building> getAllBuildings() {
        return buildingJpaRepository.findAll();
    }

    @Override
    public Building saveBuilding(Building building) {
        return buildingJpaRepository.save(building);
    }

    @Override
    public Building getBuildingById(Long id) {
        return buildingJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Building not found"));
    }

    @Override
    public void addBuilding(BuildingViewModel viewModel) {
        Building building = BuildingMapper.toEntity(viewModel);
        buildingJpaRepository.save(building);
    }

    @Override
    public Building updateBuilding(Long id, BuildingViewModel viewModel) {
        Building existingBuilding = buildingJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Building not found"));
        Building updatedBuilding = BuildingMapper.toEntity(viewModel);
        updatedBuilding.setId(existingBuilding.getId());
        return buildingJpaRepository.save(updatedBuilding);
    }

    @Override
    public void deleteBuilding(Long id) {
        buildingJpaRepository.deleteById(id);
    }
}
