package be.kdg.event.mappers;

import be.kdg.event.model.Event;
import be.kdg.event.viewmodels.EventViewModel;

public class EventMapper {

    public static EventViewModel toViewModel(Event event) {
        if (event == null) {
            return null;
        }
        EventViewModel viewModel = new EventViewModel();
        viewModel.setEventID(event.getEventID());
        viewModel.setEventName(event.getEventName());
        viewModel.setStartDateTime(event.getStartDateTime());
        viewModel.setEndDateTime(event.getEndDateTime());
        viewModel.setOrganizer(event.getOrganizer());
        viewModel.setDescription(event.getDescription());
        return viewModel;
    }
    public static Event toEntity(EventViewModel viewModel) {
        if (viewModel == null) {
            return null;
        }
        Event event = new Event();
        event.setEventID(viewModel.getEventID());
        event.setEventName(viewModel.getEventName());
        event.setStartDateTime(viewModel.getStartDateTime());
        event.setEndDateTime(viewModel.getEndDateTime());
        event.setOrganizer(viewModel.getOrganizer());
        event.setDescription(viewModel.getDescription());
        return event;
    }
}
