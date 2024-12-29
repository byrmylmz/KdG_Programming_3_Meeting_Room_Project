package be.kdg.event.controller;

import be.kdg.event.jsonexport.dto.BuildingDTO;
import be.kdg.event.jsonexport.dto.EventDTO;
import be.kdg.event.jsonexport.dto.RoomDTO;
import be.kdg.event.model.Building;
import be.kdg.event.model.Event;
import be.kdg.event.model.Room;
import be.kdg.event.service.BuildingService;
import be.kdg.event.service.EventService;
import be.kdg.event.service.RoomService;
import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExportController {
    private final BuildingService buildingService;
    private final RoomService roomService;
    private final EventService eventService;
    private final Gson gson;

    public ExportController(BuildingService buildingService, RoomService roomService, EventService eventService, Gson gson) {
        this.buildingService = buildingService;
        this.roomService = roomService;
        this.eventService = eventService;
        this.gson = gson;
    }

    private <E> ResponseEntity<String> exportEntities(List<E> entities, String filename) {
        String json = gson.toJson(entities);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + filename);
        return ResponseEntity.ok().headers(headers).body(json);
    }

    @GetMapping("/export/buildings")
    public ResponseEntity<String> exportBuildings() {
        List<BuildingDTO> buildings = buildingService.getAllBuildings().stream().map(BuildingDTO::new).toList();
        return exportEntities(buildings, "buildings.json");
    }

    @GetMapping("/export/rooms")
    public ResponseEntity<String> exportRooms() {
        List<RoomDTO> rooms = roomService.getAllRooms().stream().map(RoomDTO::new).toList();
        return exportEntities(rooms, "rooms.json");
    }

    @GetMapping("/export/events")
    public ResponseEntity<String> exportEvents() {
        List<EventDTO> events = eventService.getAllEvents().stream().map(EventDTO::new).toList();
        return exportEntities(events, "events.json");
    }
}
