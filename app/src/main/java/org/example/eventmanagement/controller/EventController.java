package org.example.eventmanagement.controller;

import org.example.eventmanagement.model.Event;
import org.example.eventmanagement.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public String listEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "events/list"; 
    }

    @GetMapping("/events/add")
    public String addEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "events/add";
    }

    @PostMapping("/events/add")
    public String addEvent(Event event) {
        eventService.addEvent(event);
        return "redirect:/events";
    }

}