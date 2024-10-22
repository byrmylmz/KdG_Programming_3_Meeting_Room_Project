package be.kdg.event.controller;

import be.kdg.event.service.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final StatisticsService statisticsService;
    public HomeController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Long> counts = statisticsService.getTotalCounts();
        model.addAttribute("totalRoomNumber", counts.getFirst());
        model.addAttribute("totalBuildingNumber", counts.get(1));
        model.addAttribute("totalEventNumber", counts.get(2));
        return "index";
    }



}
