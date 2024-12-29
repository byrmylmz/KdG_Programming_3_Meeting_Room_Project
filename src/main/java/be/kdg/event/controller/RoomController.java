package be.kdg.event.controller;

import be.kdg.event.exception.BuildingNotFoundException;
import be.kdg.event.model.Room;
import be.kdg.event.service.BuildingService;
import be.kdg.event.service.RoomService;
import be.kdg.event.service.SessionHistoryService;
import be.kdg.event.viewmodels.RoomViewModel;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;
    private final SessionHistoryService sessionHistoryService;
    private final BuildingService buildingService;
    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);


    @Autowired
    public RoomController(RoomService roomService, SessionHistoryService sessionHistoryService, BuildingService buildingService) {
        this.roomService = roomService;
        this.sessionHistoryService = sessionHistoryService;
        this.buildingService = buildingService;
    }

    @GetMapping
    public String getAllRooms(Model model, HttpSession session) {
        sessionHistoryService.trackPageVisit(session, "/rooms");

        model.addAttribute("rooms", roomService.getAllRooms());
        return "rooms/list";
    }

    @GetMapping("/add")
    public String addRoomForm(Model model, HttpSession session) {
        sessionHistoryService.trackPageVisit(session, "/rooms/add");
        model.addAttribute("room", new RoomViewModel());
        model.addAttribute("buildings", buildingService.getAllBuildings());
        return "rooms/add";
    }

    @PostMapping("/add")
    public String saveRoom(@Valid @ModelAttribute("room") RoomViewModel room, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "rooms/add";
        }
        roomService.saveRoom(room);
        return "redirect:/rooms";
    }

    @ExceptionHandler(BuildingNotFoundException.class)
    public String handleBuildingNotFoundException(BuildingNotFoundException e, Model model) {
        logger.error("Error occurred: {}", e.getMessage());
        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("url", "/rooms/add");
        return "errors/generic";
    }

    @GetMapping("/update/{id}")
    public String editRoomForm(@PathVariable Long id, Model model) {
        Room room = roomService.getRoomById(id);
        model.addAttribute("room", room);
        model.addAttribute("buildings", buildingService.getAllBuildings());
        return "rooms/update";
    }

    @PostMapping("/update/{id}")
    public String updateRoom(@PathVariable Long id, @Valid @ModelAttribute("room") RoomViewModel room, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("room", room);
            return "rooms/update";
        }
        room.setId(id);

        roomService.updateRoom(id, room);
        return "redirect:/rooms";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return "redirect:/rooms";
    }

    @GetMapping("view/{id}")
    public String getRoomDetails(@PathVariable Long id, Model model) {
        Room room = roomService.getRoomWithEvents(id);
        model.addAttribute("room", room);
        model.addAttribute("events", room.getEvents());
        return "rooms/event-list";
    }
}
