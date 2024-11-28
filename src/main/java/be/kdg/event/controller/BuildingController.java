package be.kdg.event.controller;


import be.kdg.event.model.Building;
import be.kdg.event.service.BuildingService;
import be.kdg.event.viewmodels.BuildingViewModel;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String addBuilding(@Valid @ModelAttribute("building") BuildingViewModel viewModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("building", viewModel);
            return "buildings/add";  // If validation fails, show the form again with error messages
        }

        buildingService.addBuilding(viewModel);
        return "redirect:/buildings";  // Redirect to the buildings list page
    }
}