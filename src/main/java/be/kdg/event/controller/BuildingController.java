package be.kdg.event.controller;

import be.kdg.event.mappers.BuildingMapper;
import be.kdg.event.model.Building;
import be.kdg.event.service.BuildingService;
import be.kdg.event.service.SessionHistoryService;
import be.kdg.event.viewmodels.BuildingViewModel;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class BuildingController {
    private final BuildingService buildingService;
    private final SessionHistoryService sessionHistoryService;

    public BuildingController(BuildingService buildingService, SessionHistoryService sessionHistoryService) {
        this.buildingService = buildingService;
        this.sessionHistoryService = sessionHistoryService;
    }

    @GetMapping("/buildings")
    public String listBuildings(Model model, HttpSession session) {
        sessionHistoryService.trackPageVisit(session, "/buildings");
        List<Building> buildings = buildingService.getAllBuildings();
        model.addAttribute("buildings", buildings);
        return "buildings/list";
    }

    @GetMapping("/buildings/add")
    public String addBuildingForm(Model model, HttpSession session) {
        sessionHistoryService.trackPageVisit(session, "/buildings/add");
        model.addAttribute("building", new BuildingViewModel());
        return "buildings/add";
    }


    @PostMapping("/buildings/add")
    public String addBuilding(@Valid @ModelAttribute("building") BuildingViewModel viewModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("building", viewModel);
            return "buildings/add";
        }

        buildingService.addBuilding(viewModel);
        return "redirect:/buildings";
    }

    @GetMapping("/buildings/update/{id}")
    public String updateBuildingForm(@PathVariable Long id, Model model, HttpSession session) {
        sessionHistoryService.trackPageVisit(session, "/buildings/update/" + id);

        Building building = buildingService.getBuildingById(id);
        if (building == null) {
            return "redirect:/buildings";
        }

        BuildingViewModel viewModel = BuildingMapper.toViewModel(building);
        model.addAttribute("building", viewModel);
        model.addAttribute("buildingId", id);
        return "buildings/update";
    }

    @PostMapping("/buildings/update/{id}")
    public String updateBuilding(@PathVariable Long id, @Valid @ModelAttribute("building") BuildingViewModel viewModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("buildingId", id);
            return "buildings/update";
        }

        buildingService.updateBuilding(id, viewModel);
        return "redirect:/buildings";
    }

    @GetMapping("/buildings/{id}/rooms")
    public String viewBuildingDetails(@PathVariable Long id, Model model, HttpSession session) {
        sessionHistoryService.trackPageVisit(session, "/buildings/" + id);

        Building building = buildingService.getBuildingById(id);
        if (building == null) {
            return "redirect:/buildings";
        }

        model.addAttribute("building", building);
        model.addAttribute("rooms", building.getRooms());

        return "buildings/room-list";
    }

    @PostMapping("/buildings/delete/{id}")
    public String deleteBuilding(@PathVariable Long id) {
        buildingService.deleteBuilding(id);

        return "redirect:/buildings";
    }
}