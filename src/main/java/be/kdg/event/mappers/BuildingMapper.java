package be.kdg.event.mappers;

import be.kdg.event.entity.BuildingEntity;
import be.kdg.event.model.Building;
import be.kdg.event.viewmodels.BuildingViewModel;

import java.util.Objects;

public class BuildingMapper {

    public static Building toEntity(BuildingViewModel viewModel) {
        if (Objects.isNull(viewModel)) {
            return null;
        }
        Building building = new Building();
        building.setId(viewModel.getId());
        building.setName(viewModel.getName());
        building.setAddress(viewModel.getAddress());
        return building;
    }



    public static BuildingViewModel toViewModel(Building entity) {
        if (entity == null) {
            return null;
        }

        BuildingViewModel viewModel = new BuildingViewModel();
        viewModel.setId(entity.getId());
        viewModel.setName(entity.getName());
        viewModel.setAddress(entity.getAddress());
        return viewModel;
    }

    public static BuildingEntity toBuildingEntity(BuildingViewModel viewModel) {
        if (Objects.isNull(viewModel)) {
            return null;
        }
        BuildingEntity building = new BuildingEntity();
        building.setId(viewModel.getId());
        building.setName(viewModel.getName());
        building.setAddress(viewModel.getAddress());
        return building;
    }

    public static BuildingViewModel toBuildingViewModel(BuildingEntity entity) {
        if (entity == null) {
            return null;
        }

        BuildingViewModel viewModel = new BuildingViewModel();
        viewModel.setId(entity.getId());
        viewModel.setName(entity.getName());
        viewModel.setAddress(entity.getAddress());
        return viewModel;
    }



}
