package org.example.eventmanagement.controller;


import org.example.eventmanagement.model.Building;
import org.example.eventmanagement.service.BuildingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
        model.addAttribute("building", new Building());
        return "buildings/add";
    }

    @PostMapping("/buildings/add")
    public String addBuilding(Building building) {
        buildingService.addBuilding(building);
        return "redirect:/buildings";
    }
}