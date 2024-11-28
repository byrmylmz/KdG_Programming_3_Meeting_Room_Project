package be.kdg.event.controller;

import be.kdg.event.model.Room;
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


@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;
    private final SessionHistoryService sessionHistoryService;


    @Autowired
    public RoomController(RoomService roomService, SessionHistoryService sessionHistoryService) {
        this.roomService = roomService;
        this.sessionHistoryService = sessionHistoryService;
    }

    @GetMapping
    public String getAllRooms(Model model, HttpSession session) {
        sessionHistoryService.trackPageVisit(session, "/rooms");

        model.addAttribute("rooms", roomService.getAllRooms());
        return "rooms/list";
    }

    @GetMapping("/add")
    public String addRoomForm(Model model,HttpSession session) {
        sessionHistoryService.trackPageVisit(session, "/rooms/add");

        model.addAttribute("room", new RoomViewModel());
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

    @GetMapping("/edit/{id}")
    public String editRoomForm(@PathVariable Long id, Model model) {
        Room room = roomService.getRoomById(id);
        model.addAttribute("room", room);
        return "rooms/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateRoom(@PathVariable Long id, @Valid @ModelAttribute("room") RoomViewModel room, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("room", room);
            return "rooms/edit";
        }
        room.setRoomID(id);
        roomService.saveRoom(room);
        return "redirect:/rooms";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return "redirect:/rooms";
    }
}
