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
        room.setRoomNumber(viewModel.getRoomNumber());
        room.setType(viewModel.getType());
        room.setCapacity(viewModel.getCapacity());
        room.setRoomID(viewModel.getRoomID());
        return room;
    }

    public static RoomViewModel toViewModel(Room room){
        if(Objects.isNull(room)){
            return null;
        }
        RoomViewModel viewModel = new RoomViewModel();
        viewModel.setRoomNumber(room.getRoomNumber());
        viewModel.setType(room.getType());
        viewModel.setCapacity(room.getCapacity());
        viewModel.setRoomID(room.getRoomID());
        return viewModel;

    }
}
