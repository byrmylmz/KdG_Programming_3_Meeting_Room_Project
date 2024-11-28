package be.kdg.event.controller;

import be.kdg.event.dto.EventDto;
import be.kdg.event.model.Event;
import be.kdg.event.service.EventService;
import be.kdg.event.service.SessionHistoryService;
import be.kdg.event.viewmodels.EventViewModel;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EventController {

    private final EventService eventService;
    private final SessionHistoryService sessionHistoryService;

    @Autowired
    public EventController(EventService eventService, SessionHistoryService sessionHistoryService) {
        this.eventService = eventService;
        this.sessionHistoryService = sessionHistoryService;
    }

    @GetMapping("/events")
    public String listEvents(Model model, HttpSession session) {
        sessionHistoryService.trackPageVisit(session, "/events");

        List<Event> events = eventService.getAllEvents();
        List<EventDto> eventDtos = events.stream()
                .map(event -> EventDto.builder()
                        .eventName(event.getEventName())
                        .organizer(event.getOrganizer())
                        .description(event.getDescription())
                        .startDateTime(event.getStartDateTime())
                        .endDateTime(event.getEndDateTime())
                        .build())
                .collect(Collectors.toList());

        model.addAttribute("events", eventDtos);
        return "events/list";
    }

    @GetMapping("/events/add")
    public String addEventForm(Model model,HttpSession session) {
        sessionHistoryService.trackPageVisit(session, "/events/add");
        model.addAttribute("event", new EventViewModel());
        return "events/add";
    }

    @PostMapping("/events/add")
    public String addEvent(@Valid @ModelAttribute("event") EventViewModel viewModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("event", viewModel);
            return "events/add";
        }

        eventService.addEvent(viewModel);
        return "redirect:/events";
    }
}
