package be.kdg.event.controller;


import be.kdg.event.model.Building;
import be.kdg.event.service.BuildingService;
import be.kdg.event.viewmodels.BuildingViewModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class BuildingController {
    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("/buildings")
    public String listBuildings(Model model) {
        List<Building> buildings = buildingService.getAllBuildings();
        model.addAttribute("buildings", buildings);
        return "buildings/list";
    }

    @GetMapping("/buildings/add")
    public String addBuildingForm(Model model) {
        model.addAttribute("building", new BuildingViewModel());
        return "buildings/add";
    }

    @PostMapping("/buildings/add")
    public String addBuilding(BuildingViewModel viewModel) {
        log.info("addBuilding {}", viewModel);
        buildingService.addBuilding(viewModel);
        return "redirect:/buildings";
    }
}