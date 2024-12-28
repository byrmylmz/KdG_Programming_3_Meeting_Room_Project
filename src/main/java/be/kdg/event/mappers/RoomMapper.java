package be.kdg.event.mappers;

import be.kdg.event.model.Room;
import be.kdg.event.viewmodels.RoomViewModel;

import java.util.Objects;

public class RoomMapper {

    public static Room toEntity(RoomViewModel viewModel) {
        if(Objects.isNull(viewModel)){
            return null;
        }
        Room room = new Room();
        room.setId(viewModel.getId());
        room.setName(viewModel.getName());
        room.setNumber(viewModel.getNumber());
        room.setCapacity(viewModel.getCapacity());
        room.setBuilding(BuildingMapper.toEntity(viewModel.getBuilding()));
        return room;
    }

    public static RoomViewModel toViewModel(Room room){
        if(Objects.isNull(room)){
            return null;
        }
        RoomViewModel viewModel = new RoomViewModel();
        viewModel.setId(room.getId());
        viewModel.setName(room.getName());
        viewModel.setNumber(room.getNumber());
        viewModel.setCapacity(room.getCapacity());
        viewModel.setBuilding(BuildingMapper.toViewModel(room.getBuilding()));
        return viewModel;

    }
}
