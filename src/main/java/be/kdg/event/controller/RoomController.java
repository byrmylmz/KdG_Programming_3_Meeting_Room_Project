package be.kdg.event.controller;

import be.kdg.event.model.Room;
import be.kdg.event.service.RoomService;
import be.kdg.event.viewmodels.RoomViewModel;
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

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String getAllRooms(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "rooms/list";
    }

    @GetMapping("/add")
    public String addRoomForm(Model model) {
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
