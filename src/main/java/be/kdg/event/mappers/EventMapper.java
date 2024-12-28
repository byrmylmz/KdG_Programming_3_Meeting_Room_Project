package be.kdg.event.mappers;

import be.kdg.event.model.Event;
import be.kdg.event.model.Room;
import be.kdg.event.viewmodels.EventViewModel;

import java.util.stream.Collectors;

public class EventMapper {

    public static EventViewModel toViewModel(Event event) {
        if (event == null) {
            return null;
        }
        EventViewModel viewModel = new EventViewModel();
        viewModel.setId(event.getId());
        viewModel.setName(event.getName());
        viewModel.setStartDateTime(event.getStartDateTime());
        viewModel.setEndDateTime(event.getEndDateTime());
        viewModel.setRoomIdList(event.getRoomIdList());
        return viewModel;
    }
    public static Event toEntity(EventViewModel viewModel) {
        if (viewModel == null) {
            return null;
        }
        Event event = new Event();
        event.setId(viewModel.getId());
        event.setName(viewModel.getName());
        event.setStartDateTime(viewModel.getStartDateTime());
        event.setEndDateTime(viewModel.getEndDateTime());
        event.setRoomIdList(viewModel.getRoomIdList());
        return event;
    }
}
