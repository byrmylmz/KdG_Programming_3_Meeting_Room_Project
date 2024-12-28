package be.kdg.event.controller;

import be.kdg.event.dto.EventDto;
import be.kdg.event.model.Event;
import be.kdg.event.service.EventService;
import be.kdg.event.service.RoomService;
import be.kdg.event.service.SessionHistoryService;
import be.kdg.event.viewmodels.EventViewModel;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class EventController {

    @GetMapping("/events/{id}")
    public String eventDetails(@PathVariable("id") Long id, Model model, HttpSession session) {
        sessionHistoryService.trackPageVisit(session, "/events/" + id);
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "events/event-details";
    }

    private final EventService eventService;
    private final RoomService roomService;
    private final SessionHistoryService sessionHistoryService;

    @Autowired
    public EventController(EventService eventService, RoomService roomService, SessionHistoryService sessionHistoryService) {
        this.eventService = eventService;
        this.roomService = roomService;
        this.sessionHistoryService = sessionHistoryService;
    }

    @GetMapping("/events")
    public String listEvents(Model model, HttpSession session) {
        sessionHistoryService.trackPageVisit(session, "/events");
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "events/list";
    }

    @GetMapping("/events/add")
    public String addEventForm(Model model,HttpSession session) {
        sessionHistoryService.trackPageVisit(session, "/events/add");
        model.addAttribute("event", new EventViewModel());
        model.addAttribute("rooms", roomService.getAllRooms());
        return "events/add";
    }

    @GetMapping("/events/update/{id}")
    public String editEventForm(@PathVariable("id") Long id, Model model, HttpSession session) {
        sessionHistoryService.trackPageVisit(session, "/events/update");
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        model.addAttribute("allRooms", roomService.getAllRooms());
        List<Long> selectedRoomIds = event.getRoomIdList() != null ? event.getRoomIdList() : List.of();
        model.addAttribute("selectedRoomIds", selectedRoomIds);
        return "events/update";
    }


    @PostMapping("/events/add")
    public String addEvent(
            @Valid @ModelAttribute("event") EventViewModel viewModel,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors detected:");
            bindingResult.getFieldErrors().forEach(error -> {
                System.out.println("Field: " + error.getField() + ", Error: " + error.getDefaultMessage());
            });

            model.addAttribute("rooms", roomService.getAllRooms());
            return "events/add";
        }

        eventService.addEvent(viewModel);
        return "redirect:/events";
    }

    @PostMapping("/events/update/{id}")
    public String updateEvent(
            @PathVariable("id") Long id,
            @Valid @ModelAttribute("event") EventViewModel viewModel,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors detected:");
            bindingResult.getFieldErrors().forEach(error -> {
                System.out.println("Field: " + error.getField() + ", Error: " + error.getDefaultMessage());
            });

            model.addAttribute("rooms", roomService.getAllRooms());
            return "events/update";
        }

        viewModel.setId(id);
        eventService.updateEvent(id,viewModel);
        return "redirect:/events";
    }
    
    @PostMapping("/events/delete/{id}")
    public String deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteEventById(id);
        return "redirect:/events";
    }
    
}
